package com.rookies.assignment.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookies.assignment.exceptions.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Check xem token có chết hay ko
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException, ForbiddenException {
        System.out.println("JwtEntryPoint 1");
        logger.error("Authentication Error message: " + authException.getMessage());
//        throw new ForbiddenException("quyền truy cập không đủ");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
//        đăng nhập sai
        body.put("status", HttpStatus.FORBIDDEN);
        body.put("error", "FORBIDDEN");
//        body.put("message", authException.getMessage());
        body.put("message", "Bạn chưa đủ quyền thực hiện");
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }




}
