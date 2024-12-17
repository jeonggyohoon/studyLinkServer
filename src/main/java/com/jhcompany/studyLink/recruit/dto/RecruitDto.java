package com.jhcompany.studyLink.recruit.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecruitDto {
    private String cid;
    private String title;
    private String content;
    private List<String> posterTag;
    private String deadLine;
    private int recruiterNumber;
}
