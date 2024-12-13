package com.jhcompany.studyLink.user.repository;

import com.jhcompany.studyLink.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserIdAndUseYn(String userId, String useYn);

    UserEntity findByUserId(String userId);

    @Query("SELECT MAX(u.index) FROM UserEntity u")
    Integer findMaxIndex();
}
