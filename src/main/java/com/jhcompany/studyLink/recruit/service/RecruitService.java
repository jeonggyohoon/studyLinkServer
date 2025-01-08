package com.jhcompany.studyLink.recruit.service;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.recruit.dto.RecruitDto;
import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import com.jhcompany.studyLink.recruit.repository.RecruitPostRepository;
import com.jhcompany.studyLink.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitPostRepository recruitPostRepository;

    public ResponseMessage postWrite(RecruitDto recruitPDto) {
        RecruitPostEntity recruitPostEntity = RecruitPostEntity.builder()
                .cid(recruitPDto.getCid())
                .title(recruitPDto.getTitle())
                .content(recruitPDto.getContent())
                .posterTag(String.join("," ,recruitPDto.getPosterTag()))
                .deadLine(recruitPDto.getDeadLine())
                .reqruiterNumber(recruitPDto.getRecruiterNumber())
                .build();

        recruitPostRepository.save(recruitPostEntity);

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("저장 성공").build();
    }

    public ResponseMessage allPostList() {
        List<RecruitPostEntity> recruitPostEntityList = recruitPostRepository.findAll();
        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("리스트 출력 성공").recruitPostEntities(recruitPostEntityList).build();
    }

    // 게시물 조회
    public ResponseMessage DetailPostList(Long postIndex) {
        Optional <RecruitPostEntity> recruitPostEntity = recruitPostRepository.findById(postIndex);

        if (recruitPostEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제된 게시물입니다.");
        }
        List<String> tagList = new ArrayList<>();

        if(recruitPostEntity.get().getPosterTag() != null && !recruitPostEntity.get().getPosterTag().isEmpty()) {
            tagList = Arrays.asList(recruitPostEntity.get().getPosterTag().split(","));
        }

        RecruitDto recruitDto = new RecruitDto(
                recruitPostEntity.get().getCid(),
                recruitPostEntity.get().getTitle(),
                recruitPostEntity.get().getContent(),
                tagList,
                recruitPostEntity.get().getDeadLine(),
                recruitPostEntity.get().getReqruiterNumber()
        );

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("게시물 조회 성공").recruitInformation(recruitDto).build();




    }

}
