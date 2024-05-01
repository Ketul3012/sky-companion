/**
 * This file contains the implementation of the ModifyProfileService interface.
 * It provides the functionality to modify the user profile by updating the user's personal information,
 * address, and preferred languages.
 * <p>
 * The ModifyProfileService is an important component of the authentication module in the Sky Companion project.
 * It allows authenticated users to update their profile information, ensuring that the user's data is up-to-date
 * and accurate.
 * <p>
 * The ModifyProfileServiceImpl class implements the ModifyProfileService interface and provides the actual
 * implementation of the modifyProfile() method. This method retrieves the authenticated user's information,
 * updates the necessary fields, and saves the changes to the database.
 * <p>
 * Additionally, it handles the updating of the user's address and preferred languages, ensuring that the changes
 * are reflected correctly in the database.
 * <p>
 * This file is crucial to the project as it enables users to modify their profile information, providing a
 * seamless and user-friendly experience.
 */
package com.sky.companion.authentication.modify_profile.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.AddressEntity;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.entity.UserLanguageEntity;
import com.sky.companion.authentication.common.entity.UserLanguageId;
import com.sky.companion.authentication.common.repository.AddressRepository;
import com.sky.companion.authentication.common.repository.LanguageRepository;
import com.sky.companion.authentication.common.repository.UserLanguageRepository;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.get_user.service.GetUserService;
import com.sky.companion.authentication.modify_profile.request.ModifyProfileRequest;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModifyProfileServiceImpl implements ModifyProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private UserLanguageRepository userLanguageRepository;

    @Autowired
    private GetUserService getUserService;

    private static AddressEntity updateAddressEntityAsPerRequest(ModifyProfileRequest modifyProfileRequest,
                                                                 UserEntity userEntity) {
        AddressEntity addressEntity = userEntity.getAddress();


        if (addressEntity == null) {
            addressEntity = new AddressEntity();
        }

        addressEntity.setAddressLine1(modifyProfileRequest.getAddressLine1());
        addressEntity.setAddressLine2(modifyProfileRequest.getAddressLine2());
        addressEntity.setPostalCode(modifyProfileRequest.getPostalCode());
        addressEntity.setCity(modifyProfileRequest.getCity());
        addressEntity.setProvince(modifyProfileRequest.getProvince());
        addressEntity.setCountry(modifyProfileRequest.getCountry());
        return addressEntity;
    }

    @Override
    public HttpSuccessResponse<String> modifyProfile(ModifyProfileRequest modifyProfileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((UserDTO) authentication.getPrincipal()).getId();
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            userEntity.setFirstName(modifyProfileRequest.getFirstname());
            userEntity.setLastName(modifyProfileRequest.getLastname());
            userEntity.setDob(modifyProfileRequest.getDob());
            userEntity.setMobileNumber(modifyProfileRequest.getMobileNumber());

            AddressEntity addressEntity = updateAddressEntityAsPerRequest(modifyProfileRequest, userEntity);

            addressRepository.save(addressEntity);
            userEntity.setAddress(addressEntity);
            userRepository.save(userEntity);

            userLanguageRepository.deleteAll(userLanguageRepository.findLanguageEntitiesForUser(userEntity.getId()));
            List<Integer> languages = modifyProfileRequest.getLanguages();

            userLanguageRepository.saveAll(
                    languages.stream().map((id) -> new UserLanguageEntity(new UserLanguageId(userEntity.getId(), id)))
                            .collect(Collectors.toList()));

            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.SUCCESS, "User profile updated successfully",
                    null);
        } else {
            return new HttpSuccessResponse<>(ResponseStatusCodeConstants.BAD_REQUEST, "User not found", null);
        }
    }
}
