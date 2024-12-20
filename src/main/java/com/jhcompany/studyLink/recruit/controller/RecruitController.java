package com.jhcompany.studyLink.recruit.controller;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.recruit.dto.RecruitDto;
import com.jhcompany.studyLink.recruit.service.RecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
@Tag(name = "Recruit API", description = "모집공고 API")
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping("/postWrite")
    @Operation(summary = "PostWrite API",description = "모집공고 작성")
    public ResponseMessage postWrite(@RequestBody RecruitDto recruitDto) {
        return recruitService.postWrite(recruitDto);
    }

    @GetMapping("/getAllPost")
    @Operation(summary = "GetAllPost API", description = "모집공고 리스트")
    public ResponseMessage getAllPost() {
        return recruitService.allPostList();
    }
}
