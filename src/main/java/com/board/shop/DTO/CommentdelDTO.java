package com.board.shop.DTO;

import lombok.Data;

@Data
public class CommentdelDTO {
    private Long commentIdx;
    private Long boardIdx;
    private String content;
}
