package com.example.blackbell_user.security.filter;

import com.example.blackbell_user.constant.ResultType;
import com.example.blackbell_user.dto.AccountDTO.*;
import com.example.blackbell_user.exception.BaseException;
import com.example.blackbell_user.service.AccountService;
import com.example.blackbell_user.vo.AccountVO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final Environment env;

    private final AccountService accountService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestVO loginAuthentication = objectMapper.readValue(request.getInputStream(), LoginRequestVO.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginAuthentication.getEmail(),
                            loginAuthentication.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        String email = ((User)authentication.getPrincipal()).getUsername();
        GetResponseDTO responseDTO = accountService.getUserDetailsByEmail(email);

        String token = Jwts.builder()
                .setSubject(responseDTO.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration-time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("Authorization", token);
        response.addHeader("Account-ID", responseDTO.getAccountId());
    }
}
