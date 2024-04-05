package com.board.shop.DTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final String Userid;
    private final String pwd;
    private final int grade;
    private final String name;
    private final Long idx;
    private final String email;
    public MyUserDetails(String userid, String pwd, int grade, String name, String email, Long idx) {
        this.Userid = userid;
        this.pwd = pwd;
        this.grade = grade;
        this.name = name;
        this.email = email;
        this.idx = idx;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+grade));
        return authorities;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    public Long getIdx(){return idx;}

    public String getName(){
        return name;
    }

    public String getEmail(){return email;}

    public int getGrade(){
        return grade;
    }

    @Override
    public String getUsername() {
        return Userid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
