package com.avanade.dio.jwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    /*Criamos aqui o autheticationManager*/
    public JWTAuthorizationFilter(AuthenticationManager authManager){
        super(authManager);
    }

    /*Aqui implementamos o nosso metodo*/
    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain
    ) throws IOException, ServletException {

        /*Recupero o header (HEADER_STRING = "Authorization";) SecurityConstants*/
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        /*Aqui verificamos se retorna o token ou o prefixo caso não, ele já cancela, não dando continuidade*/
        if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req,res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        /*Aqui pegamos o token*/
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if(token == null){
            return null;
        }

        /*Se o token não for nulo então convertemos o token em string de usuário usando o JWT*/
        //Parse the token
        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                .build()
                /*Verificando token e removendo o prefixo*/
                .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getSubject();

        if(user != null){
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }

        return null;

    }



}
