package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "a_board_comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_board_comseq")
    @SequenceGenerator(name = "a_board_comseq", sequenceName = "a_board_comseq", allocationSize = 1)
    @Column(name = "comment_idx")
    private Long commentIdx;

    @Setter
    @Column(name = "userid")
    private String userid;

    @Setter
    @Column(name = "ccontent")
    private String content;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_idx")
    private BoardEntity board;

    @Setter
    @Column(name = "reply")
    private int reply;

}
