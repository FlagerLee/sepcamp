package com.seclass.sepcamp.components;

import com.seclass.sepcamp.configs.RSAConfig;
import com.seclass.sepcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${rsa.private-key}")
    private String private_key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password_encrypted = (String) authentication.getCredentials();
        UserDetails user = userService.loadUserByUsername(username);

        String password;

        try {
            password = RSAConfig.RSADecrypt(password_encrypted, private_key);
        }
        catch (Exception e) {
            throw new AuthenticationException("RSA decrypt failed") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }


        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new CredentialsExpiredException("Error password or username");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
