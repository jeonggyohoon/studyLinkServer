package com.jhcompany.studyLink.user.controller;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.user.dto.UserDto;
import com.jhcompany.studyLink.user.repository.UserRepository;
import com.jhcompany.studyLink.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user/")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

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

    @PutMapping("/login")
    public ResponseMessage login(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }
}
