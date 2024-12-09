package com.jhcompany.studyLink.login.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_information")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;

    @Column(columnDefinition = "varchar(50) comment '유저ID'",nullable = false, unique = true)
    private String user_id;

    @Column(columnDefinition = "varchar(50) comment '유저비밀번호'",nullable = false)
    private String user_password;

    @Column(columnDefinition = "char(1) comment '사용여부'")
    @ColumnDefault("1")
    private String use_yn;

    @Column(columnDefinition = "datetime default current_timestamp comment '생성일자'")
    private String create_datetime;

    @Column(columnDefinition = "int(11) comment '수정자인식번호'")
    private int modify_index;

    @Column(columnDefinition = "datetime comment '수정일자'")
    private String modify_datetime;

    @PreUpdate
    public void preUpdate() {
        this.modify_datetime = LocalDateTime.now().toString();
    }

    // 수정자에는 어떻게 업데이트 할까?
}
