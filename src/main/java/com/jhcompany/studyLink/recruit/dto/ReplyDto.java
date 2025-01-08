package com.jhcompany.studyLink.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private Long replyCid; // 댓글 index
    private Long replyPid;  // 부모댓글 index
    private String content; // 내용
    private String endYn;  // 삭제 여부
    private String createDatetime;  // 댓글 생성 시간
    private String modifyDatetime;  // 댓글 수정 시간
    private String cid;  // 댓글 작성자 ID
    private List<ReplyDto> replies;  // 하위 댓글 (답글) 리스트
}