package com.board.shop.service;

import com.board.shop.DTO.LoginDTO;
import com.board.shop.DTO.MemberDTO;
import com.board.shop.Entity.MemberEntity;
import com.board.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;

    public void joinsave(MemberDTO dto){
            dto.setGrade(1);
            MemberEntity memberEntity = dto.toEntity();
            memberRepository.save(memberEntity);
    }

    public void delete(Long idx){
        MemberEntity memberEntity = memberRepository.findByIdx(idx);
        memberRepository.delete(memberEntity);
    }
    public void update(MemberDTO dto) {
        MemberEntity memberUpdate = memberRepository.findByIdx(dto.getIdx());
            if (memberUpdate != null && dto.getPwd1().equals(dto.getPwd())) {
                memberUpdate.setName(dto.getName());
                memberUpdate.setEmail(dto.getEmail());
                memberUpdate.setPwd(dto.getPwd());
                memberRepository.save(memberUpdate);
            }
    }

    public String loginproc(LoginDTO loginDTO) {

        MemberEntity member = null;
        if (loginDTO.getUserid() != null) {
             member = memberRepository.findByUserid(loginDTO.getUserid());
            return "아이디를 확인하세요.";
        } else {
            if (!Objects.equals(member.getPwd(), loginDTO.getPassword())) {
                return "비밀번호가 일치하지 않습니다.";
            }else{
                return "로그인이 완료되었습니다.";
            }
        }
    }
}
