package com.board.shop.DTO;

import com.board.shop.Entity.PrefeceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrefaceDTO {

    private Long prefaceIdx;

    private String prefacename;

    private Long writegrade;

    private Long readgrade;
    public PrefeceEntity toEntity(){
        return PrefeceEntity.builder()
                .prefaceIdx(prefaceIdx)
                .writegrade(writegrade)
                .readgrade(readgrade)
                .prefacename(prefacename)
                .build();
    }
}
