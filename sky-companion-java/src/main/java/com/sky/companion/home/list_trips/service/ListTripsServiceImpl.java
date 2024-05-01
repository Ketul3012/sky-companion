/**
 * This class is an implementation of the ListTripsService interface.
 * It provides methods to generate predicates based on given requests and perform SQL operations like AND, LIKE, OR, and JOIN based searches.
 * The ListTripsServiceImpl class is important to this project as it handles the logic for listing trips based on various criteria.
 * It utilizes the EntityManager and CriteriaBuilder to construct queries and retrieve trip entities from the database.
 * The generated predicates are used to filter the trips based on the request parameters such as source, destination, date, maximum cost, and user details.
 * The resulting trips are then mapped to DTOs and returned as a response.
 */

package com.sky.companion.home.list_trips.service;

import com.sky.companion.authentication.common.entity.AddressEntity;
import com.sky.companion.authentication.common.entity.LanguageEntity;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.common.constants.ResponseMessagesConstants;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.dto.CompanionProviderTripDto;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.common.mapper.CompanionProviderTripMapper;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class implements the {@link ListTripsService}
 */
@Service
public class ListTripsServiceImpl implements ListTripsService {

    @Autowired
    private EntityManager entityManager;

    /**
     * This method generate {@link Predicate} based on given request with various
     * SQL operations like AND, LIKE, OR, also JOIN based searches
     *
     * @param listTripsRequest request for which predicates needs to
     *                         be generated
     * @param criteriaBuilder  builder used to generate
     *                         {@link Predicate}
     * @param root             entity root used to perform joins and
     *                         create predicate based on columns
     * @return list of generated {@link Predicate}
     */
    private static List<Predicate> generatedCompanionEntityRelatedPredicates(ListTripsRequest listTripsRequest,
                                                                             CriteriaBuilder criteriaBuilder, Root<CompanionProviderTripEntity> root) {
        List<Predicate> allPredicates = new ArrayList<>();
        if (listTripsRequest.getFrom() != null && !listTripsRequest.getFrom().isEmpty()) {
            allPredicates.add(criteriaBuilder.like(root.get("source"),
                    "%" + listTripsRequest.getFrom().toLowerCase() + "%"));
        }

        if (listTripsRequest.getTo() != null && !listTripsRequest.getTo().isEmpty()) {
            allPredicates.add(criteriaBuilder.like(root.get("destination"),
                    "%" + listTripsRequest.getTo().toLowerCase() + "%"));
        }

        if (listTripsRequest.getDate() != null) {
            allPredicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("departure"),
                    listTripsRequest.getDate()));
        }

        if (listTripsRequest.getMaximumCost() != null) {
            allPredicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
                    listTripsRequest.getMaximumCost()));
        }
        return allPredicates;
    }

    /**
     * This method generate {@link Predicate} based on given request with various
     * SQL operations like AND, LIKE, OR, also JOIN based searches
     *
     * @param listTripsRequest request for which predicates needs to be generated
     * @param criteriaBuilder  builder used to generate {@link Predicate}
     * @param userEntityJoin   join entity used to perform joins and create
     *                         predicate based on columns
     * @return list of generated {@link Predicate}
     */
    private static List<Predicate> generatedUserEntityRelatedPredicates(ListTripsRequest listTripsRequest,
                                                                        CriteriaBuilder criteriaBuilder, Join<CompanionProviderTripEntity, UserEntity> userEntityJoin) {
        List<Predicate> allPredicates = new ArrayList<>();

        // This part break the userName using space and create like predicates for each
        // part, this is done to improve userName based search
        if (listTripsRequest.getUser() != null && !listTripsRequest.getUser().isEmpty()) {
            String[] userNames = listTripsRequest.getUser().trim().toLowerCase().split(" ");
            for (String userName : userNames) {
                List<Predicate> userNamePredicates = new ArrayList<>();
                userNamePredicates.add(criteriaBuilder.like(userEntityJoin.get("firstName"), "%" + userName + "%"));
                userNamePredicates.add(criteriaBuilder.like(userEntityJoin.get("lastName"), "%" + userName + "%"));
                allPredicates.add(criteriaBuilder.or(userNamePredicates.toArray(new Predicate[1])));
            }
        }
        return allPredicates;
    }

    /**
     * This method generate {@link Predicate} based on given request with various
     * SQL operations like AND, LIKE, OR, also JOIN based searches
     *
     * @param listTripsRequest   request for which predicates needs to be generated
     * @param languageEntityJoin join entity used to perform joins and create
     *                           predicate based on columns
     * @return list of generated {@link Predicate}
     */
    private static List<Predicate> generateLanguageEntityRelatedPredicates(ListTripsRequest listTripsRequest,
                                                                           Join<UserEntity, LanguageEntity> languageEntityJoin) {
        List<Predicate> allPredicates = new ArrayList<>();
        if (listTripsRequest.getLanguageIds() != null && !listTripsRequest.getLanguageIds().isEmpty()) {
            allPredicates.add(languageEntityJoin.get("id").in(listTripsRequest.getLanguageIds()));
        }
        return allPredicates;
    }

    /**
     * This method generate {@link Predicate} based on given request with various
     * SQL operations like AND, LIKE, OR, also JOIN based searches
     *
     * @param listTripsRequest  request for which predicates needs to be generated
     * @param criteriaBuilder   builder used to generate {@link Predicate}
     * @param addressEntityJoin join entity used to perform joins and create
     *                          predicate based on columns
     * @return list of generated {@link Predicate}
     */
    private static List<Predicate> generatedAddressEntityRelatedPredicates(ListTripsRequest listTripsRequest,
                                                                           CriteriaBuilder criteriaBuilder, Join<UserEntity, AddressEntity> addressEntityJoin) {
        List<Predicate> allPredicates = new ArrayList<>();
        if (listTripsRequest.getCompanionCity() != null && !listTripsRequest.getCompanionCity().isEmpty()) {
            allPredicates.add(criteriaBuilder.like(addressEntityJoin.get("city"),
                    "%" + listTripsRequest.getCompanionCity().trim().toLowerCase() + "%"));
        }
        return allPredicates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpSuccessResponse<ListTripsResponse> listTrips(ListTripsRequest listTripsRequest) {
        ListTripsResponse listTripsResponse = new ListTripsResponse();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanionProviderTripEntity> query = criteriaBuilder
                .createQuery(CompanionProviderTripEntity.class);

        Root<CompanionProviderTripEntity> root = query
                .from(CompanionProviderTripEntity.class);
        Join<CompanionProviderTripEntity, UserEntity> userEntityJoin = root.join("user",
                JoinType.LEFT);
        Join<UserEntity, AddressEntity> addressEntityJoin = userEntityJoin.join("address", JoinType.LEFT);
        Join<UserEntity, LanguageEntity> languageEntityJoin = userEntityJoin.join("languages", JoinType.LEFT);

        List<Predicate> allPredicates = new ArrayList<>();
        allPredicates.addAll(generatedCompanionEntityRelatedPredicates(listTripsRequest, criteriaBuilder,
                root));
        allPredicates.addAll(generatedUserEntityRelatedPredicates(listTripsRequest, criteriaBuilder, userEntityJoin));
        allPredicates
                .addAll(generatedAddressEntityRelatedPredicates(listTripsRequest, criteriaBuilder, addressEntityJoin));
        allPredicates.addAll(generateLanguageEntityRelatedPredicates(listTripsRequest, languageEntityJoin));

        if (!allPredicates.isEmpty()) {
            query.select(root)
                    .where(criteriaBuilder.and(allPredicates.toArray(new Predicate[1])));
        }

        Set<CompanionProviderTripEntity> tripsEntities = new HashSet<>(entityManager.createQuery(query).getResultList());

        List<CompanionProviderTripDto> tripDTOs = tripsEntities.stream().map(CompanionProviderTripMapper::entityToDto)
                .collect(Collectors.toList());
        listTripsResponse.setCount(tripDTOs.size());
        listTripsResponse.setTrips(tripDTOs);

        return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, ResponseMessagesConstants.SUCCESS,
                listTripsResponse);
    }
}
