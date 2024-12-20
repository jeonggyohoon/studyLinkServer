package com.jhcompany.studyLink.recruit.service;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.recruit.dto.RecruitDto;
import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import com.jhcompany.studyLink.recruit.repository.RecruitPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
