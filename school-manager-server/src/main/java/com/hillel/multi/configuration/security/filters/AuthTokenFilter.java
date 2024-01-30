package com.hillel.multi.configuration.security.filters;

import com.hillel.multi.service.utils.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorisation");
        String username = null;
        String jwtToken = null;

        try {
            if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
                jwtToken = authHeader.substring(7);
                username = jwtTokenUtils.getUsername(jwtToken);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        jwtTokenUtils.getRoles(jwtToken).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
            filterChain.doFilter(request, response);
        } catch (JwtException | AccessDeniedException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }

    }

}
