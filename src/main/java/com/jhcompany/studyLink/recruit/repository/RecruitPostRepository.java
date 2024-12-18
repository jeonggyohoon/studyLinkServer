package com.jhcompany.studyLink.recruit.repository;


import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPostEntity, Integer> {
    RecruitPostEntity findByCid(String userId);

//    @Query("SELECT count(r) FROM RecruitPostEntity r WHERE r.cid = :cid")
//    Integer findProposePostCount(@Param("cid") String cid);
}
