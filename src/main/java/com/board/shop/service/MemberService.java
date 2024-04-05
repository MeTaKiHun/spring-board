package com.board.shop.service;

import com.board.shop.DTO.MemberDTO;
import com.board.shop.Entity.MemberEntity;
import com.board.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
