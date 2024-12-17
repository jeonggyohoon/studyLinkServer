package com.jhcompany.studyLink.recruit.repository;


import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPostEntity, Integer> {

}
