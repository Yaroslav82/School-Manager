package com.hillel.multi.configuration.security.filters;

import com.hillel.multi.service.utils.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorisation");
        String jwtToken = jwtTokenUtils.getTokenFromHeader(authHeader);
        String username;

        try {
            if (jwtToken != null) {
                username = jwtTokenUtils.getUsername(jwtToken);
                userDetailsService.loadUserByUsername(username);
            }
            filterChain.doFilter(request, response);
        } catch (JwtException | UsernameNotFoundException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
