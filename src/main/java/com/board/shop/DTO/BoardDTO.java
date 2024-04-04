package com.board.shop.DTO;

import com.board.shop.Entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long boardIdx;

    private Long memberIdx;

    private Long fileIdx;

    private Long prefaceIdx;

    private String title;

    private String content;


    public BoardEntity toEntity(){
        return  BoardEntity.builder()
                .boardIdx(boardIdx)
                .title(title)
                .content(content)
                .build();
    }
}

