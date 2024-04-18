package com.board.shop.DTO;

import com.board.shop.Entity.MemberEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디 형식이 올바르지 않습니다.")
    private String userid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",message = "비밀번호 형식이 올바르지 않습니다.")
    private String pwd;

    @NotBlank(message = "비밀번호확인을 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",message = "비밀번호 형식이 올바르지 않습니다.")
    private String pwd1;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "(?=.*[가-힣]).{1,20}",message = "이름 형식이 올바르지 않습니다.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
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
