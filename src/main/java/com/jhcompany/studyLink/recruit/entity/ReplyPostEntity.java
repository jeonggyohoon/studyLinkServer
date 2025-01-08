package com.jhcompany.studyLink.recruit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "reply_post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "bigint comment '부모댓글 index'", name = "p_reply_Index", referencedColumnName = "replyIndex", nullable = true)
    private ReplyPostEntity replyPostEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "bigint comment '게시물 index'", name = "post_index", nullable = false)
    private RecruitPostEntity recruitPost;

    @Column(name = "reply_content", columnDefinition = "varchar(500) comment '내용'")
    private String replyContent;

    @Column(columnDefinition = "varchar(50) comment '유저ID'", nullable = false)
    private String cid;

    @Column(name = "end_yn", columnDefinition = "char(1) comment '삭제 여부'", nullable = false)
    @ColumnDefault("'n'")
    private String endYn;

    @Column(name = "create_datetime",columnDefinition = "datetime default current_timestamp comment '생성일자'")
    private String createDatetime;

    @Column(name = "modify_datetime",columnDefinition = "datetime comment '수정일자'")
    private String modifyDatetime;

    // 삭제된 댓글 처리
    public void markAsDeleted() {
        this.endYn = "y";  // 삭제된 댓글
        this.replyContent = "삭제된 댓글입니다";  // 삭제된 댓글 내용
    }
}
