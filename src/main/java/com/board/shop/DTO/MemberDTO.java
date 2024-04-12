package com.board.shop.DTO;

import com.board.shop.Entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long idx;

    private String userid;

    private String pwd;

    private String pwd1;

    private String name;

    private String email;


    public MemberEntity toEntity(){
        return  MemberEntity.builder()
                .idx(idx)
                .userid(userid)
                .pwd(pwd)
                .name(name)
                .email(email)
                .grade(1)
                .build();
    }
}
