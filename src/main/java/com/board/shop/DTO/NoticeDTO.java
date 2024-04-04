package com.board.shop.DTO;

import com.board.shop.Entity.NoticeEntity;
import com.board.shop.Entity.NoticeSettingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {

    private String noticename;

    private NoticeSettingEntity noticeSetting;

    public NoticeEntity toEntity(NoticeSettingEntity noticeSetting){

        return  NoticeEntity.builder()
                .noticename(noticename)
                .setting(noticeSetting)
                .build();
    }

}
