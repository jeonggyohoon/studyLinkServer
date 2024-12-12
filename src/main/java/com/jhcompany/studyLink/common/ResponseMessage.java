package com.jhcompany.studyLink.common;


import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMessage {
    // 통신상태 httpstatus
    private HttpStatus httpStatus;
    // 메세지
    private String message;

    // 요 두개만 만들어!



}
