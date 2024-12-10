package com.jhcompany.studyLink.login.service;

import com.jhcompany.studyLink.login.entity.UserEntity;
import com.jhcompany.studyLink.login.repository.UserRepository;
import com.jhcompany.studyLink.login.repository.UserRequestDto;
import com.jhcompany.studyLink.login.repository.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserResponseDto> findByUserId(String userId) {
        return userRepository.findByUserIdAndUseYn(userId, "1").map(this::toResponseDto);
    }

    private UserResponseDto toResponseDto(UserEntity userEntity) {
        return UserResponseDto.builder()
                .index(userEntity.getIndex())
                .userId(userEntity.getUserId())
                .useYn(userEntity.getUseYn())
                .createDatetime(userEntity.getCreateDatetime())
                .build();
    }

    public Optional<UserResponseDto> creatUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = UserEntity.builder()
                .userId(userRequestDto.getUserId())
                .userPassword(userRequestDto.getUserPassword())
                .useYn(userRequestDto.getUseYn())
                .build();
        UserEntity savedEntity = userRepository.save(userEntity);
        return Optional.of(toResponseDto(savedEntity));
    }


}
