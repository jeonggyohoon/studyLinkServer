package com.jhcompany.studyLink.common;


import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import com.jhcompany.studyLink.user.dto.UserDto;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMessage {
    // 통신상태 httpstatus
    private HttpStatus httpStatus;
    // 메세지
    private String message;
    // 성공여부
    private boolean success;
    // 추가정보
    private String addInformation;

    private UserDto userInformation;

    private List<String> throwList;

    private List<RecruitPostEntity> recruitPostEntities;
}
