package com.board.shop.DTO;

import com.board.shop.Entity.PrefaceEntity;
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
    public PrefaceEntity toEntity(){
        return PrefaceEntity.builder()
                .prefaceIdx(prefaceIdx)
                .writegrade(writegrade)
                .readgrade(readgrade)
                .prefacename(prefacename)
                .build();
    }
}
