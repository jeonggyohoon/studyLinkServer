package com.jhcompany.studyLink.tag.service;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.tag.entity.TagMapEntity;
import com.jhcompany.studyLink.tag.repository.TagMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagMapRepository tagMapRepository;

    public ResponseMessage allTagName() {
        List<TagMapEntity> tagMapEntityList = tagMapRepository.findAll();

        List<String> tagNameList = tagMapEntityList.stream().map(TagMapEntity::getTagName).toList();

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("리스트 추출 성공").throwList(tagNameList).build();
    }
}
