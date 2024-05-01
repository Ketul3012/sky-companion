/**
 * The ModifyProfileService interface defines the contract for modifying user profiles.
 * It provides a method to modify the profile of a user based on the given ModifyProfileRequest.
 * The implementation of this interface is responsible for handling the logic to modify the profile.
 * <p>
 * This file is important to the project as it defines the service contract for modifying user profiles.
 * It allows other components of the system to interact with the ModifyProfileService and modify user profiles.
 * By adhering to this contract, different implementations of ModifyProfileService can be used interchangeably,
 * providing flexibility and modularity to the system.
 */
package com.sky.companion.authentication.modify_profile.service;

import com.sky.companion.authentication.modify_profile.request.ModifyProfileRequest;
import com.sky.companion.common.model.HttpSuccessResponse;

public interface ModifyProfileService {
    HttpSuccessResponse<String> modifyProfile(ModifyProfileRequest modifyProfileRequest);

}
