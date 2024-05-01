package com.sky.companion.authentication.modify_profile.service;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.common.entity.AddressEntity;
import com.sky.companion.authentication.common.entity.UserEntity;
import com.sky.companion.authentication.common.repository.AddressRepository;
import com.sky.companion.authentication.common.repository.LanguageRepository;
import com.sky.companion.authentication.common.repository.UserLanguageRepository;
import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.get_user.service.GetUserService;
import com.sky.companion.authentication.modify_profile.request.ModifyProfileRequest;
import com.sky.companion.common.constants.ResponseStatusCodeConstants;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ModifyProfileServiceTest {
    final private static List<Integer> languageList = Arrays.asList(1, 2, 3);

    @InjectMocks
    private ModifyProfileServiceImpl modifyProfileService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private UserLanguageRepository userLanguageRepository;

    @Mock
    private GetUserService getUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void setUpAuthenticationContext(UserDTO userDTO) {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDTO);
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        return userEntity;
    }

    @Test
    void testModifyProfileSuccess() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        setUpAuthenticationContext(userDTO);

        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        modifyProfileRequest.setLastname("Doee");
        modifyProfileRequest.setDob(new Date());
        modifyProfileRequest.setMobileNumber("1234567890");
        modifyProfileRequest.setAddressLine1("addressLine1");
        modifyProfileRequest.setAddressLine2("addressLine2");
        modifyProfileRequest.setPostalCode("12345");
        modifyProfileRequest.setCity("city");
        modifyProfileRequest.setProvince("province");
        modifyProfileRequest.setCountry("country");
        modifyProfileRequest.setLanguages(languageList);

        UserEntity userEntity = createUserEntity();
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userEntity));

        HttpSuccessResponse<String> response = modifyProfileService.modifyProfile(modifyProfileRequest);

        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }

    @Test
    void testModifyProfileInvalidUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        setUpAuthenticationContext(userDTO);

        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        modifyProfileRequest.setLastname("Doee");
        modifyProfileRequest.setDob(new Date());
        modifyProfileRequest.setMobileNumber("1234567890");
        modifyProfileRequest.setAddressLine1("addressLine1");
        modifyProfileRequest.setAddressLine2("addressLine2");
        modifyProfileRequest.setPostalCode("12345");
        modifyProfileRequest.setCity("city");
        modifyProfileRequest.setProvince("province");
        modifyProfileRequest.setCountry("country");
        modifyProfileRequest.setLanguages(languageList);

        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        HttpSuccessResponse<String> response = modifyProfileService.modifyProfile(modifyProfileRequest);

        assertEquals(ResponseStatusCodeConstants.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testValidAddressEntity() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        setUpAuthenticationContext(userDTO);

        ModifyProfileRequest modifyProfileRequest = new ModifyProfileRequest();
        modifyProfileRequest.setFirstname("John");
        modifyProfileRequest.setLastname("Doee");
        modifyProfileRequest.setDob(new Date());
        modifyProfileRequest.setMobileNumber("1234567890");
        modifyProfileRequest.setAddressLine1("addressLine1");
        modifyProfileRequest.setAddressLine2("addressLine2");
        modifyProfileRequest.setPostalCode("12345");
        modifyProfileRequest.setCity("city");
        modifyProfileRequest.setProvince("province");
        modifyProfileRequest.setCountry("country");
        modifyProfileRequest.setLanguages(languageList);

        UserEntity userEntity = createUserEntity();
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userEntity));

        AddressEntity addressEntity = new AddressEntity();
        userEntity.setAddress(addressEntity);

        HttpSuccessResponse<String> response = modifyProfileService.modifyProfile(modifyProfileRequest);

        assertEquals(ResponseStatusCodeConstants.SUCCESS, response.getStatusCode());
    }
}

