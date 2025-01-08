package com.jhcompany.studyLink.recruit.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recruit_post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIndex;

    @Column(columnDefinition = "varchar(50) comment '유저ID'", nullable = false)
    private String cid;

    @Column(columnDefinition = "varchar(255) comment '제목'", nullable = false)
    private String title;

    @Column(columnDefinition = "varchar(500) comment '내용'")
    private String content;

    @Column(name = "poster_tag", columnDefinition = "varchar(500) comment '기술태그'")
    private String posterTag;

    @Column(name = "dead_line", columnDefinition = "varchar(10) comment '마감기한'", nullable = false)
    private String deadLine;

    @Column(name = "reqruiter_number", columnDefinition = "int(11) comment '모집인원'", nullable = false)
    private int reqruiterNumber;

    @Column(columnDefinition = "varchar(255) comment '신청자'")
    private String proposer;

    @Column(name = "reqruit_status", columnDefinition = "varchar(1) comment '모집상태'")
    private String reqruitStatus;
}
