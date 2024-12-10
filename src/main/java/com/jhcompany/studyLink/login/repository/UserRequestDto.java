package com.jhcompany.studyLink.login.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String userId;
    private String userPassword;
    private String useYn;


}
