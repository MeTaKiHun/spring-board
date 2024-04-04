package com.board.shop.DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private String userid;
    private String content;
    private Long boardIdx;
    private Long bcidx;
}
