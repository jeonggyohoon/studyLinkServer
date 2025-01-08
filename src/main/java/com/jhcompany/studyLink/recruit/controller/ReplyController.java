package com.jhcompany.studyLink.recruit.controller;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.recruit.dto.RecruitDto;
import com.jhcompany.studyLink.recruit.dto.ReplyDto;
import com.jhcompany.studyLink.recruit.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit/reply")
@RequiredArgsConstructor
@Tag(name = "Reply API", description = "댓글 API")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replyWrite")
    @Operation(summary = "ReplyWrite API",description = "댓글 작성")
    public ResponseMessage replyWrite(@RequestParam Long postIndex, @RequestBody ReplyDto replyDto) {
        return replyService.replyWrite(postIndex, replyDto);
    }

    @GetMapping("/replyList")
    @Operation(summary = "ReplyList API",description = "댓글 list")
    public List<ReplyDto> replyList(@RequestParam Long postIndex) {
        return replyService.getReplyList(postIndex);
    }
}
