package com.my.board.api.controller;

import com.my.board.api.exception.ApiResponse;
import com.my.board.api.exception.BadRequestException;
import com.my.board.api.service.CommentService;
import com.my.board.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//모든 메서드가 @ResponsBody를 자동으로 갖도록 만듦
@RequestMapping("api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("exception")
    public String exHander(){
        throw new BadRequestException("Test");
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> commentSearch(@PathVariable Long commentId){
        Map<String,Object> result = commentService.findComment(commentId);
        CommentDto dto = (CommentDto) result.get("dto");
        if(ObjectUtils.isEmpty(dto))
        {
            String message = "댓글 조회 실패";
            throw new BadRequestException(message);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<?> commentUpdate(
            @PathVariable("commentId")Long commentId,
            @RequestBody CommentDto dto
        ){
        //1. commentId에 해당하는 Comment 객체 찾아옴
        Map<String,Object> result = commentService.findComment(commentId);
        CommentDto findDto = (CommentDto) result.get("dto");
        //2. null 이면 BadRequestException
        if(ObjectUtils.isEmpty(findDto))
        {
            String message = "댓글 조회 실패";
            throw new BadRequestException(message);
        }
        //3. 해당하는 comment 업데이트
        dto.setId(findDto.getId());
        commentService.updateComment(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .message("댓글 수정 성공")
                        .build());
    }

}
