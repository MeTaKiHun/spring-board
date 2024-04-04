package com.board.shop.Entity;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "a_board_member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_board_memberseq")
    @SequenceGenerator(name = "a_board_memberseq", sequenceName = "a_board_memberseq", allocationSize = 1)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "userid")
    private String userid;

    @Setter
    @Column(name = "pwd")
    private String pwd;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "grade")
    private int grade;

}
