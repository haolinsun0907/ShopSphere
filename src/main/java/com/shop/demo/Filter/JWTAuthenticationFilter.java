package com.shop.demo.Filter;

import com.shop.demo.Repo.TokenRepository;
import com.shop.demo.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("implementing the filter {}",request.getAuthType());
        if(request.getServletPath().contains("/api/v1/auth")){
            filterChain.doFilter(request,response);
            log.info("getting rejected {}",request.getAuthType());
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authHeader ==  null|| !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null){
            log.info("userEmail is {} and getAuth is{}",userEmail,SecurityContextHolder.getContext().getAuthentication().getName());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            var isTokenVaild = tokenRepository.findByToken(jwt)
                    .map( t->!t.isExpired() && t.isRevoked())
                    .orElse(false);

            if(jwtService.isTokenValid(jwt,userDetails)&& isTokenVaild){
                log.info("token is {} and userdetail is{}",jwtService,userDetails);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);


            }

        }
        filterChain.doFilter(request, response);


    }
}
