package com.keyn_bello.subscription_tracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyn_bello.subscription_tracker.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationEntryPointTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException authException;
    @Mock
    private PrintWriter writer;

    @Captor
    private ArgumentCaptor<ErrorResponse> errorResponseCaptor;

    @InjectMocks
    private JwtAuthenticationEntryPoint entryPoint;

    @Test
    void shouldHandleUnauthorizedAccess() throws Exception {
        when(request.getRequestURI()).thenReturn("/api/test");
        when(response.getWriter()).thenReturn(writer);
        when(objectMapper.writeValueAsString(errorResponseCaptor.capture())).thenReturn("{}");

        entryPoint.commence(request, response, authException);

        verify(response).setContentType("application/json");
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setHeader("WWW-Authenticate", "Bearer");
        verify(writer).write("{}");

        ErrorResponse capturedError = errorResponseCaptor.getValue();
        assertEquals("Unauthorized access", capturedError.getMessage());
        assertEquals(401, capturedError.getStatus());
        assertEquals("/api/test", capturedError.getPath());
        assertNotNull(capturedError.getTimestamp());
    }
}
