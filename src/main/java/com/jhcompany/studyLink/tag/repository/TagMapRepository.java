package com.jhcompany.studyLink.tag.repository;

import com.jhcompany.studyLink.tag.entity.TagMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMapRepository extends JpaRepository<TagMapEntity, Integer> {

}
