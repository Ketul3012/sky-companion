package com.sky.companion.authentication.email_verification.controller;

import com.sky.companion.authentication.common.repository.UserRepository;
import com.sky.companion.authentication.email_verification.service.EmailVerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmailVerificationControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailVerificationService emailVerificationService;

    @InjectMocks
    private EmailVerificationController emailVerificationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emailVerificationController).build();
    }

    @Test
    void testVerifyEmailSuccess() throws Exception {
        when(emailVerificationService.verifyEmail(anyString())).thenReturn("Email successfully verified");

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/verify").param("token", "validToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email successfully verified"))
                .andReturn().getResponse().getContentAsString();

        assertEquals("Email successfully verified", result);
    }

    @Test
    void testVerifyEmailAlreadyVerified() throws Exception {
        when(emailVerificationService.verifyEmail(anyString())).thenReturn("Email already verified");

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/verify").param("token", "alreadyVerifiedToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email already verified"))
                .andReturn().getResponse().getContentAsString();

        assertEquals("Email already verified", result);
    }

    @Test
    void testVerifyEmailInvalidToken() throws Exception {
        when(emailVerificationService.verifyEmail(anyString())).thenReturn("Invalid token");

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/verify").param("token", "invalidToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid token"))
                .andReturn().getResponse().getContentAsString();

        assertEquals("Invalid token", result);
    }


}
