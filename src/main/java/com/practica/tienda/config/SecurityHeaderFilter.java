package com.practica.tienda.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Slf4j
@Component
public class SecurityHeaderFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        jakarta.servlet.http.HttpServletRequest httpRequest = (jakarta.servlet.http.HttpServletRequest) request;

        // SESIÓN SEGURA ---
        // Si el usuario no tiene una cookie de sesión, le asignamos una
        if (httpRequest.getCookies() == null ||
                java.util.Arrays.stream(httpRequest.getCookies()).noneMatch(c -> c.getName().equals("SESSION_ID"))) {

            String randomId = java.util.UUID.randomUUID().toString();

            // Creamos una Cookie con protecciones
            String cookieHeader = String.format(
                    "SESSION_ID=%s; Path=/; HttpOnly; SameSite=Strict; Max-Age=3600",
                    randomId
            );
            httpResponse.addHeader("Set-Cookie", cookieHeader);
            log.info("Cookie creada correctamente {}",cookieHeader);
        }

        // --- TUS CABECERAS EXISTENTES ---
        String cspPolicy = "default-src 'self'; " +
                "script-src 'self' 'unsafe-inline'; " +
                "style-src 'self' 'unsafe-inline'; " +
                "img-src 'self' data: https:; " +
                "connect-src 'self' http://localhost:5173;";

        httpResponse.setHeader("Content-Security-Policy", cspPolicy);
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpResponse.setHeader("X-Frame-Options", "DENY");
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");

        chain.doFilter(request, response);
    }
}
