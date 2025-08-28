package com.my.board.dto;

import com.my.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String nickName;
    private String body;

    public static CommentDto fromComment(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getNickName(),
                comment.getBody());
    }
    public static Comment fromDto(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setNickName(dto.getNickName());
        comment.setBody(dto.getBody());
        return comment;
    }
}
