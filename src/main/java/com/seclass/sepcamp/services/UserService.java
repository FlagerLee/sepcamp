package com.seclass.sepcamp.services;

import com.seclass.sepcamp.configs.RSAConfig;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.models.UserRegister;
import com.seclass.sepcamp.models.UserRegisterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;

    @Value("${rsa.private-key}")
    private String private_key;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("邮箱或密码错误");
        }
        return user;
    }

    public UserRegisterResult register(UserRegister register) {
        User user_by_email = userDao.getUserByEmail(register.getEmail());
        if(user_by_email != null) {
            return new UserRegisterResult("该邮箱已被注册", false);
        }
        // TODO: 频繁查找数据库带来的性能开销极大，不能每次注册都在数据库中查找
        User user_by_username = userDao.getUserByUsername(register.getUsername());
        if(user_by_username != null) {
            return new UserRegisterResult("该用户名已被占用", false);
        }
        String password = register.getPassword();
        try {
            String plainText = RSAConfig.RSADecrypt(password, private_key);
            register.setPassword(Encrypt(plainText));
            int register_result = userDao.register(register);
            if(register_result > 0) return new UserRegisterResult("用户注册成功", true);
            else return new UserRegisterResult("用户注册失败", false);
        }
        catch (Exception e) {
            throw new AuthenticationException("RSA decrypt failed") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }

    private String Encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
