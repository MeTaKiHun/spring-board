
package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "a_notice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_notice_seq")
    @SequenceGenerator(name = "a_notice_seq", sequenceName = "a_notice_seq", allocationSize = 1)
    @Column(name = "notice_idx")
    private Long noticeIdx;

    @OneToMany(mappedBy = "boardIdx")
    private List<BoardEntity> boards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setting")
    private NoticeSettingEntity setting;

    @Column(name = "noticename")
    private String noticename;

}

