package com.jhcompany.studyLink.user.service;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.recruit.repository.RecruitPostRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RecruitPostRepository RecruitPostRepository;

    private final PasswordEncoder passwordEncoder;
    private final RecruitPostRepository recruitPostRepository;

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

        int nextIndex = userRepository.findFirstByOrderByUserIndexDesc() == null ? 1 : userRepository.findFirstByOrderByUserIndexDesc().getUserIndex() + 1;

        UserEntity userEntity = UserEntity.builder()
                .userId(userDto.getUserId())
                .nickName("user" + nextIndex)
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

    public ResponseMessage checkDuplicateName(String userId) {

        validateUserID(userId);

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("사용가능 아이디입니다.").build();

    }

    // 회원정보수정
    @Transactional
    public ResponseMessage updateUser(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());

        if (!userDto.getFirstCheck()) {
            validateUser(userEntity, userDto);

            userEntity.setNickName(userDto.getNickName());
            userEntity.setUserEmail(userDto.getUserEmail());
            userEntity.setUserPassword(passwordEncoder.encode(userDto.getUpdatePassword()));
        }

        userEntity.setTags(String.join(",", userDto.getTags()));

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("회원정보 수정 성공").build();

    }

    //회원 정보 조회(마이페이지)
    public ResponseMessage findUserInformation(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);

        UserDto userDtoView = new UserDto();

        userDtoView.setUserEmail(userEntity.getUserEmail());
        userDtoView.setNickName(userEntity.getNickName());

        if(userEntity.getTags() != null && !userEntity.getTags().isEmpty()) {
            List<String> tagList = Arrays.asList(userEntity.getTags().split(","));
            userDtoView.setTags(tagList);
        }
        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("회원 정보 조회 성공").userInformation(userDtoView).build();
    }

    // 회원 정보 검증(아이디, 비밀번호) [로그인, 회원정보수정]
    private void validateUser(UserEntity userEntity, UserDto userDto) {
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일치하는 회원 정보가 없습니다.");
        }
        if (!passwordEncoder.matches(userDto.getUserPassword(), userEntity.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 확인해 주세요");
        }
    }


    // 회원 가입에서 사용할 중복검사 로직 추가해야할것(완료)
    private void validateUserID(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        System.out.println("userId = " + userId);
        System.out.println("userEntity = " + userEntity);
        if (userEntity != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "동일 아이디가 존재합니다.");
        }
    }


}
