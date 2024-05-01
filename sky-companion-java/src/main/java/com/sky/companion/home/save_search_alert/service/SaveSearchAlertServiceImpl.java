/**
 * The SaveSearchAlertServiceImpl class is responsible for implementing the SaveSearchAlertService interface.
 * It provides methods for saving search alerts, synchronizing posts with search alerts, and generating search alert email links.
 * This class is an important part of the project as it handles the functionality related to search alerts and notifications.
 */

package com.sky.companion.home.save_search_alert.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.jackson.JacksonUtils;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.entity.SearchAlertsEntity;
import com.sky.companion.home.common.repository.SearchAlertsRepository;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import com.sky.companion.home.list_trips.service.ListTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements the {@link SaveSearchAlertService}
 */
@Service
public class SaveSearchAlertServiceImpl implements SaveSearchAlertService {
    @Autowired
    private SearchAlertsRepository searchAlertsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListTripsService listTripsService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${search-alert.email.link}")
    private String searchAlertEmailLink;

    /**
     * this method append all query parameters to string to generate url
     *
     * @param params params which are required to be appended in URL
     * @return generated query string
     */
    private static String buildQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return sb.toString();
    }

    /**
     * This method add params related to trip like from, to, date and cost to params map
     *
     * @param listTripsRequest request for which search params needs to be created
     * @param params           map in which params needs to be added
     */
    private static void addTripRelatedParams(ListTripsRequest listTripsRequest, Map<String, String> params) {
        if (listTripsRequest.getFrom() != null) {
            params.put("from", listTripsRequest.getFrom());
        }
        if (listTripsRequest.getTo() != null) {
            params.put("to", listTripsRequest.getTo());
        }
        if (listTripsRequest.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            params.put("date", dateFormat.format(listTripsRequest.getDate()));
        }
        if (listTripsRequest.getMaximumCost() != null) {
            params.put("maximum-cost", String.valueOf(listTripsRequest.getMaximumCost()));
        }
    }

    /**
     * This method add params related to user like user, companion-city, language-ids to params map
     *
     * @param listTripsRequest request for which search params needs to be created
     * @param params           map in which params needs to be added
     */
    private static void addUserRelatedParams(ListTripsRequest listTripsRequest, Map<String, String> params) {
        if (listTripsRequest.getUser() != null) {
            params.put("user", listTripsRequest.getUser());
        }
        if (listTripsRequest.getCompanionCity() != null) {
            params.put("companion-city", listTripsRequest.getCompanionCity());
        }
        if (listTripsRequest.getLanguageIds() != null) {
            params.put("language-ids", listTripsRequest.getLanguageIds().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param listTripsRequest request for which user want to create search alert
     * @return success or failure based on search alert creation
     */
    @Override
    public HttpSuccessResponse<String> saveSearchAlertService(ListTripsRequest listTripsRequest, UserDTO user) {
        if (user == null) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, ResponseMessagesConstants.VALIDATION_FAILED, "Invalid user session");
        }
        Optional<UserEntity> userEntity = userRepository.findById(user.getId());
        if (!userEntity.isPresent()) {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, ResponseMessagesConstants.VALIDATION_FAILED, "Invalid user session");
        }
        SearchAlertsEntity searchAlertsEntity = new SearchAlertsEntity();
        searchAlertsEntity.setUser(userEntity.get());
        searchAlertsEntity.setSearchJson(JacksonUtils.toJson(listTripsRequest));
        searchAlertsEntity.setIsUserNotified(Boolean.FALSE);
        searchAlertsRepository.save(searchAlertsEntity);
        return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS, "Search alert saved successfully");
    }

    /**
     * this method checks all search alerts with post and send mails to user whose
     * search alert matches
     */
    @Scheduled(fixedRateString = "${task.scheduling.time}")
    @Override
    public void syncPostsWithSearchAlerts() {
        List<SearchAlertsEntity> searchAlertsEntities = searchAlertsRepository.findAllByIsUserNotified(Boolean.FALSE);
        for (SearchAlertsEntity searchAlertsEntity : searchAlertsEntities) {
            ListTripsRequest listTripsRequest = JacksonUtils.fromJson(searchAlertsEntity.getSearchJson(), ListTripsRequest.class);
            if (listTripsRequest != null) {
                HttpSuccessResponse<ListTripsResponse> listTripsHttpSuccessResponse = listTripsService.listTrips(listTripsRequest);
                if (listTripsHttpSuccessResponse.getStatusCode() == ResponseStatusCodeConstants.SUCCESS && listTripsHttpSuccessResponse.getData().getCount() > 0) {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(searchAlertsEntity.getUser().getEmail());
                    message.setSubject("SkyCompanion Your matching posts are available");
                    String searchLink = generateSearchAlertEmailLink(listTripsRequest);
                    message.setText("Dear User,\nyou created a search alert on SkyCompanion, trips with similar criteria are available on platform kindly visit following link " + searchLink);
                    javaMailSender.send(message);
                    searchAlertsEntity.setIsUserNotified(Boolean.TRUE);
                    searchAlertsRepository.save(searchAlertsEntity);
                }
            }
        }
    }

    /**
     * this method generate search alert email link based on request
     *
     * @param listTripsRequest request for which search alert email link needs to be
     *                         created
     * @return search alert email link
     */
    private String generateSearchAlertEmailLink(ListTripsRequest listTripsRequest) {
        Map<String, String> params = new LinkedHashMap<>();
        addUserRelatedParams(listTripsRequest, params);
        addTripRelatedParams(listTripsRequest, params);
        return searchAlertEmailLink + buildQueryString(params);
    }
}
