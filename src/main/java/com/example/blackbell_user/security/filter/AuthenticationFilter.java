package com.example.blackbell_user.security.filter;

import com.example.blackbell_user.constant.ResultType;
import com.example.blackbell_user.exception.BaseException;
import com.example.blackbell_user.vo.AccountVO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

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
                                            Authentication authResult) throws IOException, ServletException {
    }
}
