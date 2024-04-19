package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "a_board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_board_seq")
    @SequenceGenerator(name = "a_board_seq", sequenceName = "a_board_seq", allocationSize = 1)
    @Column(name = "board_idx")
    private Long boardIdx;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "content")
    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="prefaceIdx")
    private PrefaceEntity preface;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx",referencedColumnName = "idx")
    private MemberEntity member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeIdx")
    private NoticeEntity notice;

    @Setter
    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "commentIdx",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    public BoardEntity(String title, String content, MemberEntity member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
