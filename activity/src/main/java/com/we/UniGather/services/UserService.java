package com.we.UniGather.services;

import com.we.UniGather.models.User;
import com.we.UniGather.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(String email, String password) {
        // 检查邮箱是否已被注册且用户已激活
        User existingUser = userRepository.findByEmailAndIsEnabled(email, true);
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

        // Generate verification code
        String verificationCode = generateVerificationCode();

        // Send verification email
        sendVerificationEmail(email, verificationCode);
    }

    // Method to send verification email (implementation omitted for brevity)
    private void sendVerificationEmail(String email, String verificationCode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Verification Code for UniGather");
        mailMessage.setText("Your verification code is: " + verificationCode);

        javaMailSender.send(mailMessage);
    }

    // Method to generate verification code (implementation omitted for brevity)
    private String generateVerificationCode() {
        // Generate a random verification code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public boolean verify(String email, String enteredCode) {
        // 根据邮箱从数据库或缓存中获取之前发送的验证码
        User user = userRepository.findByEmail(email);
        if (user != null && user.getVerificationCode().equals(enteredCode)) {
            // 验证码匹配，验证通过
            user.setEnabled(true); // 激活用户
            userRepository.save(user); // 更新用户状态
            return true;
        }
        return false;
    }
}
