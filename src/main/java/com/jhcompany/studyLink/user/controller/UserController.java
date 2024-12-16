package com.jhcompany.studyLink.user.controller;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.user.dto.UserDto;
import com.jhcompany.studyLink.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User API", description = "유저 정보 API")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    @Operation(summary = "signUp API", description = "회원가입 API")
    public ResponseMessage signUp(@RequestBody UserDto userDto) {
        return userService.signUpUser(userDto);
    }

    @PutMapping("/login")
    public ResponseMessage login(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }


    @PutMapping("/updateUser")
    public ResponseMessage updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
