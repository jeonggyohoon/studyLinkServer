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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Transactional
    public ResponseMessage userLogin(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());

        validateUser(userEntity, userDto);

        userEntity.setLastConnectedDatetime(LocalDateTime.now());

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("로그인 완료").success(true).addInformation(userEntity.getNickName()).build();
    }

    // 회원가입
    public ResponseMessage signUpUser(UserDto userDto) {

        validateUserID(userDto);

        UserEntity userEntity = UserEntity.builder()
                .userId(userDto.getUserId())
                .nickName("user" + (userRepository.findMaxIndex() == null ? 1 : userRepository.findMaxIndex() + 1))
                .userEmail(userDto.getUserEmail())
                .userPassword(passwordEncoder.encode(userDto.getUserPassword()))
                .tags(null)
                .useYn("Y")
                .createDatetime(LocalDateTime.now().toString())
                .build();

        userRepository.save(userEntity);

        return ResponseMessage.builder()
                .httpStatus(HttpStatus.OK)
                .message("회원가입 성공")
                .build();
    }

    // 회원정보수정
    @Transactional
    public ResponseMessage updateUser(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());

        if(!userDto.getFirstCheck()) {
            validateUser(userEntity, userDto);
            
            userEntity.setNickName(userDto.getNickName());
            userEntity.setUserEmail(userDto.getUserEmail());
            userEntity.setUserPassword(passwordEncoder.encode(userDto.getUpdatePassword()));
        }

        userEntity.setTags(String.join(",", userDto.getTags()));

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("회원정보 수정 성공").build();

    }

    //회원 정보 조회(마이페이지)
    public UserEntity findUserInformation(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());

        userEntity.setUserEmail(userEntity.getUserEmail());

        return userEntity;
    }

    // 회원 정보 검증(아이디, 비밀번호) [로그인, 회원정보수정]
    private void validateUser(UserEntity userEntity, UserDto userDto) {
        // 페스워드 확인
        System.out.println(passwordEncoder.encode(userDto.getUserPassword()));
        if(userEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일치하는 회원 정보가 없습니다.");
        }
        if (!passwordEncoder.matches(userDto.getUserPassword(), userEntity.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 확인해 주세요");
        }
    }


    // 회원 가입에서 사용할 중복검사 로직 추가해야할것(완료)
    private void validateUserID(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());
        if(userEntity != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"동일 아이디가 존재합니다.");
        }
    }
}
