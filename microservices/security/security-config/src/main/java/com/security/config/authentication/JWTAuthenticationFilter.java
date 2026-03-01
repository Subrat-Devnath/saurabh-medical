package com.security.config.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.config.service.JwtService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {

            filterChain.doFilter(request, response);

            return;
        }

        String token = header.substring(7);

        if (token == null || !jwtService.isAccessToken(token)) {

            filterChain.doFilter(request, response);

            return;
        }

        Claims payload = jwtService.parse(token).getPayload();

        String userName = payload.getSubject();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, null,
                null);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            // Final line
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String uri = request.getServletPath(); // use servletPath instead
        return uri.equals("/api/v1/login") || uri.equals("/api/v1/validate/user") || uri.equals("/api/v1/register-normal-user");
    }
}
