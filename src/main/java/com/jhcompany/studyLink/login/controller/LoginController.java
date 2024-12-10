package com.jhcompany.studyLink.login.controller;

import com.jhcompany.studyLink.login.entity.UserEntity;
import com.jhcompany.studyLink.login.repository.UserDto;
import com.jhcompany.studyLink.login.repository.UserRepository;
import com.jhcompany.studyLink.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user/")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {

        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

        Optional<UserDto> createdUser = userService.createUser(userDto);

        if (createdUser.isPresent()) {
            return ResponseEntity.ok("회원가입 완료");
        } else {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        Optional<UserDto> findUser = userService.findByUserId(userDto.getUserId());

        if (findUser.isPresent() && passwordEncoder
                                    .matches(userDto.getUserPassword(), findUser.get().getUserPassword())) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패");
        }
    }



}
