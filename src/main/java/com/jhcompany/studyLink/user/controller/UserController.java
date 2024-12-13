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



    @PutMapping("/login")
    public ResponseMessage login(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    @PostMapping("/signUp")
    public ResponseMessage signUp(@RequestBody UserDto userDto) {
        return userService.signUpUser(userDto);
    }

    @PostMapping("/updateUser")
    public ResponseMessage updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
