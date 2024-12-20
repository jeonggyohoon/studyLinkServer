package com.jhcompany.studyLink.tag.controller;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.tag.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Tag(name = "Tag API", description = "태그정보 API")
public class TagController {

    private final TagService tagService;

    @GetMapping("/getAllTag")
    @Operation(summary = "GetAllTag API", description = "모든태그리스트")
    public ResponseMessage getAllTag() {
        return tagService.allTagName();
    }
}
