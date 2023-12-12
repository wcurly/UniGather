package com.we.UniGather.services;

import com.we.UniGather.models.User;
import com.we.UniGather.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void registerUser(String email, String password) {
        // Check if the email is already registered
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Create user and save to database (password hashing omitted for simplicity)
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(false); // The account is initially disabled until email verification
        userRepository.save(user);

        // Generate verification code (you can use any logic to generate it)
        String verificationCode = generateVerificationCode();

        // Send verification email
        sendVerificationEmail(email, verificationCode);
    }

    // Method to send verification email (implementation omitted for brevity)
    private void sendVerificationEmail(String email, String verificationCode) {
        // Implement sending email logic here
    }

    // Method to generate verification code (implementation omitted for brevity)
    private String generateVerificationCode() {
        // Implement code generation logic here
        return "generated_verification_code";
    }
}
