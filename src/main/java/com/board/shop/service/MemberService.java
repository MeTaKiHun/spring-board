package com.board.shop.service;

import com.board.shop.DTO.MemberDTO;
import com.board.shop.Entity.MemberEntity;
import com.board.shop.Repository.MemberRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;

    public void joinsave(MemberDTO dto){

            MemberEntity memberEntity = dto.toEntity();
            memberRepository.save(memberEntity);
    }

    public int joinCheck(MemberDTO dto, Model model) {
        // dto 객체의 클래스로부터 모든 필드를 가져옴
        Field[] fields = dto.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotBlank.class)) {
                NotBlank notBlank = field.getAnnotation(NotBlank.class);
                System.out.println(field.getName() + " : " + notBlank.message());
                model.addAttribute(field.getName()+"error",notBlank.message());
            }else if (field.isAnnotationPresent(Email.class)) {
                Email email = field.getAnnotation(Email.class);
                System.out.println(field.getName() + " : " + email.message());
                model.addAttribute(field.getName()+"error",email.message());
            }else if (field.isAnnotationPresent(Pattern.class)) {
                Pattern pattern = field.getAnnotation(Pattern.class);
                System.out.println(field.getName() + " : " + pattern.message());
                model.addAttribute(field.getName()+"error",pattern.message());
            }
        }
        return 0;
    }


/*    public int joincheck(MemberDTO dto,Model model) {
        int i = 0;
        String messege = "";
        if (dto.getUserid() == null) {
            messege = "아이디를 입력해주세요";
            model.addAttribute("iderror", messege);
            i--;
        } else if (!dto.getUserid().matches("^[a-zA-Z0-9]+$")) {
            messege = "아이디는 영문과 숫자로만 입력이 가능합니다.";
            model.addAttribute("iderror", messege);
            i--;
        } else if(memberRepository.findByUserid(dto.getUserid())!=null){
            messege = "아이디가 중복 됩니다.";
            model.addAttribute("iderror", messege);
            i--;
        } else {
            i++;
        }
        if (dto.getEmail() == null) {
            messege = "이메일을 입력해주세요.";
            model.addAttribute("emailerror", messege);
            i--;
        } else if (!dto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            messege = "이메일의 형태가 아닙니다.";
            model.addAttribute("emailerror", messege);
            i--;
        } else {
            i++;
        }

        if (dto.getName() == null) {
            messege = "이름을 입력해주세요";
            model.addAttribute("nameerror", messege);
            i--;
        } else if (!dto.getName().matches("^[가-힣]+$")) {
            messege = "이름은 한글로 입력해주어야합니다.";
            model.addAttribute("nameerror", messege);
            i--;
        } else {
            i++;
        }
        if (dto.getPwd() == null) {
            messege = "비밀번호를 입력해주세요.";
            model.addAttribute("pwderror", messege);
            i--;
        } else if (dto.getPwd().length() < 8 || dto.getPwd().length() > 15) {
            messege = "비밀번호의 자릿수는 8~15자 로 해야합니다.";
            model.addAttribute("pwderror", messege);
            i--;
        } else {
            i++;
        }
        if (dto.getPwd1() == null) {
            messege = "비밀번호확인을 입력해주세요";
            model.addAttribute("pwd1error", messege);
            i--;
        }else if(dto.getPwd1().length() < 8 || dto.getPwd1().length() > 16){
            messege = "비밀번호확인의 자릿수는 8~15자 로 해야합니다.";
            model.addAttribute("pwd1error", messege);
            i--;
        }else if(!Objects.equals(dto.getPwd(), dto.getPwd1())){
            messege = "비밀번호가 일치하지 않습니다.";
            model.addAttribute("pwd1error", messege);
            i--;
        }else {
            i++;
        }
        System.out.println("dto = " + dto);
            model.addAttribute("formdata", dto);
        return i;
    }*/


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
