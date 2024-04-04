package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "a_board_preface")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class PrefeceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "a_board_preseq")
    @SequenceGenerator(name = "a_board_preseq", sequenceName = "a_board_preseq", allocationSize = 1)
    @Column(name= "preface_idx")
    private Long prefaceIdx;

    @OneToMany(mappedBy = "preface")
    private List<BoardEntity> board;

    @Setter
    @Column(name = "prefacename")
    private String prefacename;

    @Setter
    @Column(name = "prefacegrade")
    private String prefacegrade;
}
