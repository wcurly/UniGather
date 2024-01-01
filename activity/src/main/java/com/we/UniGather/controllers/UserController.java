package com.we.UniGather.controllers;

import com.we.UniGather.models.User;
import com.we.UniGather.models.UserLoginDTO;
import com.we.UniGather.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String email, @RequestParam String password) {
        // 检查邮箱是否已被注册
        if (userService.findByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered");
        }

        // 注册新用户
        userService.registerUser(email, password);

        return ResponseEntity.ok("Successfully sent verification code");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String email, @RequestParam String verificationCode) {
        // 从 Redis 中获取验证码
        String redisVerificationCode = userService.getVerificationCode(email);

        // 检查验证码是否正确
        if (redisVerificationCode == null || !redisVerificationCode.equals(verificationCode)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code");
        }

        // 验证码正确，激活用户
        userService.activateUser(email);

        return ResponseEntity.ok("Successfully activated user");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {

        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        // 验证用户输入的密码是否与数据库中存储的密码匹配
        if (userService.verifyPassword(email, password)) {
            // 登录成功，返回成功信息
            String token = userService.generateToken(userLoginDTO.getEmail());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            // 密码不匹配，返回错误信息
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        userService.clearUserToken(); // 清除用户 Token 信息
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }

}
