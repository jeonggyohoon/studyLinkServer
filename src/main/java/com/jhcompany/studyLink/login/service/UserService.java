package com.jhcompany.studyLink.login.service;

import com.jhcompany.studyLink.login.entity.UserEntity;
import com.jhcompany.studyLink.login.repository.UserDto;
import com.jhcompany.studyLink.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;




    public Optional<UserDto> findByUserId(String userId) {
        return userRepository.findByUserIdAndUseYn(userId, "Y").map(userEntity -> {
            UserDto dto = new UserDto();
            dto.setUserId(userEntity.getUserId());
            dto.setUserPassword(userEntity.getUserPassword());
            dto.setUseYn(userEntity.getUseYn());
            return dto;
        });
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
