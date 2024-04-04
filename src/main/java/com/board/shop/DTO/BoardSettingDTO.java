package com.board.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSettingDTO {

    private String settingname;

    private int listnumber;

    private String writegrade;

    private String readgrade;

}
