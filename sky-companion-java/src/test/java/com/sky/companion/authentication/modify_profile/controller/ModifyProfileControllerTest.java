package com.sky.companion.authentication.modify_profile.controller;

import com.sky.companion.authentication.modify_profile.request.ModifyProfileRequest;
import com.sky.companion.authentication.modify_profile.service.ModifyProfileService;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case class for {@link ModifyProfileController} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
class ModifyProfileControllerTest {
    final private static List<Integer> languageList = Arrays.asList(1, 2, 3);
    @Mock
    private ModifyProfileService modifyProfileService;

    @InjectMocks
    private ModifyProfileController modifyProfileController;

    private static ModifyProfileRequest getModifyProfileRequest() {
        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        modifyProfileRequest.setLastname("Doe");
        modifyProfileRequest.setDob(new Date());
        modifyProfileRequest.setMobileNumber("1234567890");
        modifyProfileRequest.setAddressLine1("Address Line 1");
        modifyProfileRequest.setAddressLine2("Address Line 2");
        modifyProfileRequest.setPostalCode("12345");
        modifyProfileRequest.setCity("City");
        modifyProfileRequest.setProvince("Province");
        modifyProfileRequest.setCountry("Country");
        modifyProfileRequest.setLanguages(languageList);
        return modifyProfileRequest;
    }

    /**
     * Test case for modifyProfile endpoint
     *
     * @throws Exception
     */
    @Test
    void testModifyProfileEndpoint() {
        ModifyProfileRequest modifyProfileRequest = getModifyProfileRequest();


        HttpSuccessResponse<String> httpSuccessResponse = new HttpSuccessResponse<>(
                ResponseStatusCodeConstants.SUCCESS,
                "User profile updated successfully",
                null
        );

        Mockito.when(modifyProfileService.modifyProfile(modifyProfileRequest)).thenReturn(httpSuccessResponse);

        HttpSuccessResponse<String> response = modifyProfileController.modifyProfile(modifyProfileRequest, null);

        assertEquals("User profile updated successfully", response.getStatusMessage());
    }
}
