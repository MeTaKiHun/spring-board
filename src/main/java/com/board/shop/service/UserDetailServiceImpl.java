package com.board.shop.service;

import com.board.shop.DTO.MyUserDetails;
import com.board.shop.Entity.MemberEntity;
import com.board.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        System.out.println(userid);
        MemberEntity mem = memberRepository.findByUserid(userid);
        System.out.println(mem);
        if(mem!=null) {

            return new MyUserDetails(mem.getUserid(), mem.getPwd(), mem.getGrade(), mem.getName(), mem.getEmail(), mem.getIdx());
        }else{
            throw new UsernameNotFoundException("아이디를 확인하세요");
        }
    }
}
