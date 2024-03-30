package com.example.searchteam.service.security;

import com.example.searchteam.service.domain.user.UserDomainService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_NAME = HttpHeaders.AUTHORIZATION;

    @Value("${jwt.properties.tokenPrefix}")
    private String prefixToken;

    private final JwtService jwtService;

    private final UserDomainService userDomainService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        var authHeader = request.getHeader(HEADER_NAME);

        if (authHeader == null  ||  authHeader.isEmpty()  ||  !authHeader.startsWith(prefixToken)){
            filterChain.doFilter(request,response);
            return;
        }

        var jwt = authHeader.substring(prefixToken.length() + 1);

        var username = jwtService.extractUserName(jwt);

        if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDomainService.getUserByLogin(username);

            if (jwtService.isTokenValid(jwt, userDetails)){

                SecurityContext context = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDomainService.getUserRoleByLogin(username).stream().map(SimpleGrantedAuthority::new).toList()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }

            filterChain.doFilter(request,response);
        }
    }
}
