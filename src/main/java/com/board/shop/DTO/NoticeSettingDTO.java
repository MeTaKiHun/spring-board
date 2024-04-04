package com.board.shop.DTO;

import com.board.shop.Entity.NoticeSettingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeSettingDTO {

    private String settingname;

    private int listnumber;

    private String writegrade;

    private String readgrade;

    public NoticeSettingEntity toEntity(){

        return  NoticeSettingEntity.builder()
                .settingname(settingname)
                .listnumber(listnumber)
                .writegrade(writegrade)
                .readgrade(readgrade)
                .build();
    }

}

