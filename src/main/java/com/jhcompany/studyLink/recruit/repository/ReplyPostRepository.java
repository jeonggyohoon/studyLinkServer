package com.jhcompany.studyLink.recruit.repository;

import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import com.jhcompany.studyLink.recruit.entity.ReplyPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyPostRepository extends JpaRepository<ReplyPostEntity, Long> {
    @Query("SELECT r FROM ReplyPostEntity r WHERE r.recruitPost.postIndex = :postIndex AND (CASE WHEN :pid is null THEN r.replyPostEntity IS NULL ELSE r.replyPostEntity.replyIndex = :pid END)")
    List<ReplyPostEntity> findByPid( Long pid, Long postIndex);
}
