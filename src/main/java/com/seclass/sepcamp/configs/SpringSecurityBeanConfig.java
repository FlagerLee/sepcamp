package com.seclass.sepcamp.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seclass.sepcamp.components.JwtTokenUtils;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.models.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SpringSecurityBeanConfig {
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if(response.isCommitted()) return;

            User principle = (User) authentication.getPrincipal();
            String token = jwtTokenUtils.generate(principle.getUsername(), principle.getPassword());

            // generate json response
            ObjectMapper objectMapper = new ObjectMapper();
            UserLoginResult result = new UserLoginResult();
            result.setSuccess(true);
            result.setToken(token);

            response.getWriter().print(objectMapper.writeValueAsString(result));
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            if(response.isCommitted()) return;

            // generate response json
            ObjectMapper objectMapper = new ObjectMapper();
            UserLoginResult result = new UserLoginResult();
            result.setSuccess(false);
            result.setMessage(exception.getMessage());

            response.getWriter().write(objectMapper.writeValueAsString(result));
        };
    }
}
