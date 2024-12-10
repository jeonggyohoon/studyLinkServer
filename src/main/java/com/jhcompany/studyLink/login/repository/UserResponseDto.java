package com.jhcompany.studyLink.login.repository;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private int index;
    private String userId;
    private String useYn;
    private String createDatetime;
}
