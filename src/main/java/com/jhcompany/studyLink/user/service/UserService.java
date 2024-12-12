package com.jhcompany.studyLink.user.service;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.user.entity.UserEntity;
import com.jhcompany.studyLink.user.dto.UserDto;
import com.jhcompany.studyLink.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

//    public void findByUserId(String userId) {
//        return userRepository.findByUserIdAndUseYn(userId, "Y").map(userEntity -> {
//            UserDto dto = new UserDto();
//            dto.setUserId(userEntity.getUserId());
//            dto.setUserPassword(userEntity.getUserPassword());
//            dto.setUseYn(userEntity.getUseYn());
//            return dto;
//        });
//    }
    @Transactional
    public ResponseMessage userLogin(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());
        if(userEntity != null) {

            if(passwordEncoder.matches(userDto.getUserPassword(), userEntity.getUserPassword())) {

                userEntity.setLastConnectedDatetime(LocalDateTime.now());
//                userRepository.save(userEntity);

                return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("로그인 완료").build();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 틀렸음");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "뭐야 이거 아이디 제대로 써라잉");
        }
    }

    public Optional<UserDto> createUser(UserDto userDto) {
        UserEntity userentity = UserEntity.builder().userId(userDto.getUserId())
                                                .userPassword(userDto.getUserPassword())
                                                .useYn(userDto.getUseYn()).build();

        UserEntity savedUser = userRepository.save(userentity);

        UserDto savedDto = new UserDto();
        savedDto.setUserId(savedUser.getUserId());
        savedDto.setUserPassword(savedUser.getUserPassword());
        savedDto.setUseYn(savedUser.getUseYn());

        return Optional.of(savedDto);
    }

    public Optional<UserDto> updateUser(String userId, UserDto userDto) {
        return userRepository.findByUserIdAndUseYn(userId, "Y").map(user -> {
            user.setUserPassword(userDto.getUserPassword());
            user.setUseYn(userDto.getUseYn());

            UserEntity updatedEntity = userRepository.save(user);

            UserDto updatedDto = new UserDto();
            updatedDto.setUserId(updatedEntity.getUserId());
            updatedDto.setUserPassword(updatedEntity.getUserPassword());
            updatedDto.setUseYn(updatedEntity.getUseYn());

            return updatedDto;
        });
    }

}
