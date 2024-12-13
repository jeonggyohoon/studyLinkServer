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

    // 로그인
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

    // 회원가입
    @Transactional
    public ResponseMessage signUpUser(UserDto userDto) {

        UserEntity userentity = userRepository.findByUserId(userDto.getUserId());

        if(userentity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 아이디가 존재합니다.");

        } else {
            userentity.setUserId(userDto.getUserId());
            userentity.setNickName("user" + String.valueOf(userRepository.findMaxIndex() + 1));
            userentity.setUserEmail(userDto.getUserEmail());
            userentity.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

            return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("회원가입 성공").build();
        }

    }

    // 회원정보수정
    @Transactional
    public ResponseMessage updateUser(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());

       if(userEntity != null && passwordEncoder.matches(userDto.getUserPassword(), userEntity.getUserPassword())) {

           userEntity.setNickName(userDto.getNickName());
           userEntity.setUserEmail(userDto.getUserEmail());
           userEntity.setUserPassword(passwordEncoder.encode(userDto.getUpdatePassword()));
           userEntity.setTags(String.join(",",userDto.getTags()));

           return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("회원정보 수정 성공").build();

       } else {
           if (userEntity == null){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일치하는 회원정보가 존재하지 않습니다.");
           }
           if (!passwordEncoder.matches(userDto.getUserPassword(), userEntity.getUserPassword())){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 확인해 주세요");
           }
       }
        return ResponseMessage.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("오류 발생")
                .build();
    }

}
