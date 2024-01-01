package com.we.UniGather.services;

import com.we.UniGather.models.User;
import com.we.UniGather.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JavaMailSender javaMailSender;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(String email, String password) {
        // 检查邮箱是否已被注册且用户已激活
        User existingUser = userRepository.findByEmailAndEnabledEquals(email, true);
        if (existingUser != null) {
            throw new IllegalArgumentException("Email is already registered and activated");
        }

        String encryptedPassword = passwordEncoder.encode(password);

        // Create user and save to database (password hashing omitted for simplicity)
        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setEnabled(false); // The account is initially disabled until email verification
        userRepository.save(user);

        // Send verification email
        sendVerificationEmail(email);
    }

    // Method to send verification email
    public String sendVerificationEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new RuntimeException("未填写收件人邮箱");
        }
        // 定义Redis的key
        String key = "msg_" + email;

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String verifyCode = valueOperations.get(key);
        if (verifyCode == null) {
            // 随机生成一个6位数字型的字符串
            verifyCode = generateVerificationCode();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Verification Code for UniGather");
            mailMessage.setText("Your verification code is: " + verifyCode + "\n" +
                    "Please enter this code to activate your account." + "\n" +
                    "If you did not register for UniGather, please ignore this email." + "\n" +
                    "The verification code will expire in 5 minutes.");

            try {
                // 对方看到的发送邮箱
                mailMessage.setFrom(new InternetAddress(MimeUtility.encodeText("UniGather官方") + "<yexing_work@163.com>").toString());
                // 发送邮件
                javaMailSender.send(mailMessage);
                // 将生成的验证码存入Redis数据库中，并设置过期时间
                valueOperations.set(key, verifyCode, 5L, TimeUnit.MINUTES);
                return "邮件发送成功";
            } catch (Exception e) {
                return "邮件发送失败";
                //e.printStackTrace();
                //throw new RuntimeException(e);
            }
        }else{
            return "验证码已发送至您的邮箱，请注意查收";
        }
    }

    // Method to generate verification code (implementation omitted for brevity)
    private String generateVerificationCode() {
        // Generate a random verification code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    /*public boolean verify(String email, String enteredCode) {
        // 根据邮箱从数据库或缓存中获取之前发送的验证码
        User user = userRepository.findByEmail(email);
        if (user != null && user.getVerificationCode().equals(enteredCode)) {
            // 验证码匹配，验证通过
            user.setEnabled(true); // 激活用户
            userRepository.save(user); // 更新用户状态
            return true;
        }
        return false;
    }*/

    public void activateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public String getVerificationCode(String email) {
        // 从Redis中获取验证码
        String key = "msg_" + email;
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public boolean verifyPassword(String email, String password) {
        // 从数据库中获取用户信息
        User user = userRepository.findByEmail(email);

        if (user != null && user.isEnabled()) { // 检查用户是否存在且已激活
            // 应该使用密码加密器来验证密码
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    private final String secretKey = "your_secret_key";

    public String generateToken(String email){
        // 使用密钥创建签名密钥

    }

    public void clearUserToken(){

    }

}
