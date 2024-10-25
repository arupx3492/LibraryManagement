package com.example.Library.management.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.security.Keys;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(null != authentication){
            SecretKey key= Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt= Jwts.builder()
                    .issuer("Arup Mahato")
                    .claim("username",authentication.getName())
                    .claim("roles",populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime()+30000000))
                    .signWith(key).compact();

            response.setHeader(SecurityConstant.JWT_HEADER,jwt);

        }

        filterChain.doFilter(request,response);
    }


    private String populateAuthorities(Collection<? extends  GrantedAuthority> collection){
        Set<String> authoritiesSet = new HashSet<String>();

        for(GrantedAuthority authority:collection){
            authoritiesSet.add(authority.getAuthority());
        }

        return String.join(",",authoritiesSet);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/signIn");
    }
}
