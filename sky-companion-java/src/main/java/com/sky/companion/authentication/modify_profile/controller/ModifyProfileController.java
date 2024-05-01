/**
 * This class is a controller that handles the modification of user profiles.
 * It is responsible for receiving HTTP POST requests to modify the user profile
 * and delegating the request to the ModifyProfileService for processing.
 * <p>
 * The ModifyProfileController is an important component of the project as it
 * provides the API endpoint "/modify-profile" for users to update their profiles.
 * It ensures that the ModifyProfileRequest is valid by applying validation rules
 * using the @Valid annotation. It also retrieves the authenticated user's information
 * from the UserDTO object using the @AuthenticationPrincipal annotation.
 * <p>
 * The modifyProfile method receives the ModifyProfileRequest and the authenticated user,
 * and then calls the modifyProfile method of the ModifyProfileService to perform the
 * actual modification. The result is returned as an HttpSuccessResponse<String> object.
 */
package com.sky.companion.authentication.modify_profile.controller;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.modify_profile.request.ModifyProfileRequest;
import com.sky.companion.authentication.modify_profile.service.ModifyProfileService;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ModifyProfileController {

    @Autowired
    private ModifyProfileService modifyProfileService;

    /**
     * Handles the HTTP POST request to modify the user profile.
     *
     * @param modifyProfileRequest The request object containing the updated profile
     *                             information.
     * @param user                 The authenticated user's information.
     * @return An HttpSuccessResponse<String> object indicating the success of the
     * modification.
     */
    @PostMapping(path = "/modify-profile")
    public HttpSuccessResponse<String> modifyProfile(@Valid @RequestBody ModifyProfileRequest modifyProfileRequest,
                                                     @AuthenticationPrincipal UserDTO user) {
        return modifyProfileService.modifyProfile(modifyProfileRequest);
    }
}
