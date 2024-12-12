package com.jhcompany.studyLink.user.repository;

import com.jhcompany.studyLink.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserIdAndUseYn(String userId, String useYn);

            // 자자자자자 ! 우리가 디비에서 끌고 올건? 뭘까요옹
    // 글죠~
    // 걔는 무슨형식으로 담을수 있을까용~
    UserEntity findByUserId(String userId);
}
