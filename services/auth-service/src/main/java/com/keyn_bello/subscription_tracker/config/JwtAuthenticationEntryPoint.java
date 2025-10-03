package com.keyn_bello.subscription_tracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyn_bello.subscription_tracker.dto.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("WWW-Authenticate", "Bearer");
        var body = new ErrorResponse("Unauthorized access", 401, LocalDateTime.now(), request.getRequestURI());
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
