package com.jhcompany.studyLink.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_information")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;

    @Column(name = "user_id",columnDefinition = "varchar(50) comment '유저ID'",nullable = false, unique = true)
    private String userId;

    @Column(name = "user_password", columnDefinition = "varchar(50) comment '유저비밀번호'",nullable = false)
    private String userPassword;

    @Column(name = "use_yn",columnDefinition = "char(1) comment '사용여부'")
    @ColumnDefault("Y")
    private String useYn;

    @Column(name = "create_datetime",columnDefinition = "datetime default current_timestamp comment '생성일자'")
    private String createDatetime;

    @Column(name = "modify_index",columnDefinition = "int(11) comment '수정자인식번호'")
    private int modifyIndex;

    @Column(name = "modify_datetime",columnDefinition = "datetime comment '수정일자'")
    private String modifyDatetime;

    @Column(name = "last_connected_datetime",columnDefinition = "datetime comment '접속로그'")
    private LocalDateTime lastConnectedDatetime;

    @PreUpdate
    public void preUpdate() {
        this.modifyDatetime = LocalDateTime.now().toString();
//        this.lastConnectedDatetime = LocalDateTime.now();
    }

    // 수정자에는 어떻게 업데이트 할까?
}
