package com.jhcompany.studyLink.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitDto {
    private String cid;
    private String title;
    private String content;
    private List<String> posterTag;
    private String deadLine;
    private int recruiterNumber;
}
