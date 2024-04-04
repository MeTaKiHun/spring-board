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

    private String prefacegrade;

    public PrefeceEntity toEntity(){
        return PrefeceEntity.builder()
                .prefaceIdx(prefaceIdx)
                .prefacegrade(prefacegrade)
                .prefacename(prefacename)
                .build();
    }
}
