package com.my.board.api.controller;

import com.my.board.api.service.CommentService;
import com.my.board.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//모든 메서드가 @ResponsBody를 자동으로 갖도록 만듦
@RequestMapping("api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> commentSearch(@PathVariable Long commentId){
        Map<String,Object> result = commentService.findComment(commentId);
        if(ObjectUtils.isEmpty(result))
        {
            return null;
        }

        CommentDto dto = (CommentDto) result.get("dto");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }
}
