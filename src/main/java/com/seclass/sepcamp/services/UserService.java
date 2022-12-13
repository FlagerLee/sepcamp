package com.seclass.sepcamp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.seclass.sepcamp.configs.RSAConfig;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.models.UserRegister;
import com.seclass.sepcamp.models.UserRegisterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${register.token.expire-date}")
    private long expire_date;
    @Value("${register.token.token-secret}")
    private String token_secrete;
    @Value("${register.email}")
    private String email_addr;

    @Value("${rsa.private-key}")
    private String private_key;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDao.getUserByUsername(username);
            if(user == null) {
                throw new UsernameNotFoundException("邮箱或密码错误");
            }
            if(!user.getEnabled()) {
                throw new UsernameNotFoundException("用户未验证");
            }
            return user;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("邮箱或密码错误");
        }
        if(!user.getEnabled()) {
            throw new UsernameNotFoundException("用户未验证");
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
            assert(register.getPriority() <= 2 && register.getPriority() >= 0);
            String plainText = RSAConfig.RSADecrypt(password, private_key);
            register.setPassword(Encrypt(plainText));

            int register_result = userDao.register(register);
            if(register_result < 0) return new UserRegisterResult("用户注册失败", false);
            else sendVerificationEmail(register.getEmail(), register.getUsername(), register.getSite_url());
            return new UserRegisterResult("用户注册成功", true);
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

    private void sendVerificationEmail(String email, String username, String site_url) throws MessagingException, UnsupportedEncodingException {
        String fromAddress = email_addr;
        String senderName = "SEPCAMP";
        String subject = "Please verify your registration";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        String verify_url = site_url + "register/verify?code=" + generateVerificationToken(email);

        String content = "新用户" + username + "您好，请点击下方链接以完成注册：<br><a href="
                + verify_url + ">" + verify_url + "</a>";

        helper.setText(content, true);

        mailSender.send(message);
    }

    private String generateVerificationToken(String email) {
        try {
            Date expire_date = new Date(System.currentTimeMillis() + this.expire_date);
            Algorithm algorithm = Algorithm.HMAC256(this.token_secrete);
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("email", email)
                    .withExpiresAt(expire_date)
                    .sign(algorithm);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean registerVerify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.token_secrete);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT result = verifier.verify(token);
            if(result.getExpiresAt().before(new Date())) return false;
            String email = result.getClaim("email").asString();
            User user = userDao.getUserByEmail(email);
            if(user == null || user.getEnabled()) return false; // should be unverified
            return userDao.setVerified(email) != 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String Encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getUserByIds(List<Integer> userId) {
        List<User> userList = new ArrayList<>();
        for(int id: userId) {
            userList.add(userDao.getUserByUserId(id));
        }
        return userList;
    }
}
