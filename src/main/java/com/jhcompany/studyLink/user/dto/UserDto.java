package com.jhcompany.studyLink.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String userPassword;
    private String nickName;
    private String userEmail;
    private String tags;
    private String updatePassword;
}
