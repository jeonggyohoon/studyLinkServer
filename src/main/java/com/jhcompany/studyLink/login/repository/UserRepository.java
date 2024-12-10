package com.jhcompany.studyLink.login.repository;

import com.jhcompany.studyLink.login.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserIdAndUseYn(String userId, String useYn);
}
