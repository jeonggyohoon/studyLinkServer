package com.jhcompany.studyLink.recruit.service;

import com.jhcompany.studyLink.common.ResponseMessage;
import com.jhcompany.studyLink.recruit.dto.ReplyDto;
import com.jhcompany.studyLink.recruit.entity.RecruitPostEntity;
import com.jhcompany.studyLink.recruit.entity.ReplyPostEntity;
import com.jhcompany.studyLink.recruit.repository.RecruitPostRepository;
import com.jhcompany.studyLink.recruit.repository.ReplyPostRepository;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyPostRepository replyPostRepository;
    private final RecruitPostRepository recruitPostRepository;

    // 댓글 or 답댓글 쓰기
    public ResponseMessage replyWrite(Long postIndex, ReplyDto replyDto) {

        Optional<RecruitPostEntity> recruitPostEntity = recruitPostRepository.findById(postIndex);

        if (recruitPostEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제된 게시물입니다.");
        }

        ReplyPostEntity pReplyPostEntity = null;

        if (replyDto.getReplyPid() != null) {
            Optional<ReplyPostEntity> parentReplyPostEntity = replyPostRepository.findById(replyDto.getReplyPid());
            if (parentReplyPostEntity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제된 댓글입니다.");
            }
            pReplyPostEntity = parentReplyPostEntity.get();
        }

        ReplyPostEntity replyPostEntity = ReplyPostEntity.builder()
                .replyPostEntity(pReplyPostEntity)
                .recruitPost(recruitPostEntity.get())
                .replyContent(replyDto.getContent())  // 댓글 내용
                .cid(replyDto.getCid())  // 유저 ID
                .endYn("n")
                .createDatetime(LocalDateTime.now().toString())
                .modifyDatetime(LocalDateTime.now().toString())
                .build();

        replyPostRepository.save(replyPostEntity);

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message("저장 성공").build();
    }

    // 댓글 리스트
    public List<ReplyDto> getReplyList(Long postIndex) {
        // 최상위 댓글
        List<ReplyPostEntity> replyPostEntityList = replyPostRepository.findByPid(null,postIndex);

        // 하위댓글 조회
        List<ReplyDto> result = new ArrayList<>();
        for (ReplyPostEntity replyPostEntity : replyPostEntityList) {
            result.add(replyDtoPid(replyPostEntity, postIndex));
        }

        return result;
    }

    private ReplyDto replyDtoPid(ReplyPostEntity replyPostEntity, Long postIndex) {

        ReplyDto replyDto = new ReplyDto(
                replyPostEntity.getReplyIndex(),
                replyPostEntity.getReplyPostEntity() != null ? replyPostEntity.getReplyPostEntity().getReplyIndex() : null,
                replyPostEntity.getReplyContent(),
                replyPostEntity.getEndYn(),
                replyPostEntity.getCreateDatetime(),
                replyPostEntity.getModifyDatetime(),
                replyPostEntity.getCid(),
                new ArrayList<>() //  필드 초기화
        );

        // 하위 댓글 조회
        List<ReplyPostEntity> cidReplyList = replyPostRepository.findByPid(replyPostEntity.getReplyIndex(), postIndex);
        List<ReplyDto> replyDtos = new ArrayList<>();
        for (ReplyPostEntity cidReply : cidReplyList) {
            // 하위 댓글 리스트에 추가
            replyDtos.add(replyDtoPid(cidReply, postIndex));
        }

        // 최상위 댓글의 하위 댓글리스트 전체를 추가
        replyDto.setReplies(replyDtos);

        return replyDto;
    }


}
