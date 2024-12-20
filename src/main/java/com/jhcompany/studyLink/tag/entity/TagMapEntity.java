package com.jhcompany.studyLink.tag.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag_map")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagMapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagIndex;

    @Column(name = "tag_division", columnDefinition = "varchar(50) comment '태그분류'")
    private String tagDivision;

    @Column(name = "tag_name", columnDefinition = "varchar(255) comment '태그명칭'",nullable = false,unique = true)
    private String tagName;


}
