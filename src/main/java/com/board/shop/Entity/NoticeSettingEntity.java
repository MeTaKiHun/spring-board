
package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "a_notice_setting")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class NoticeSettingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_notice_setseq")
    @SequenceGenerator(name = "a_notice_setseq", sequenceName = "a_notice_setseq", allocationSize = 1)
    @Column(name = "noticesetting_idx")
    private Long setiingIdx;

    @OneToMany(mappedBy = "setting")
    private List<NoticeEntity> notice = new ArrayList<>();

    @Column(name = "settingname")
    private String settingname;

    @Column(name = "listnumber")
    private int listnumber;

    @Column(name = "writegrade")
    private String writegrade;

    @Column(name = "readgrade")
    private String readgrade;

}

