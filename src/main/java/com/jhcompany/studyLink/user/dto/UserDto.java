package com.jhcompany.studyLink.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userId;
    private String userPassword;
    private String nickName;
    private String userEmail;
    private List<String> tags;
    private String updatePassword;
    private Boolean firstCheck;
    private int myProposePostCount;
    private int myProjectCount;
    private int myFavoritePostCount;
}
