package com.jhcompany.studyLink.login.repository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    private String userId;
    private String userPassword;
    private String useYn;


}
