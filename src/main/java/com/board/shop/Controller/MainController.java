package com.board.shop.Controller;

import com.board.shop.Config.CustomAuthenticationFailureHandler;
import com.board.shop.DTO.MemberDTO;
import com.board.shop.DTO.MyUserDetails;
import com.board.shop.Entity.BoardEntity;
import com.board.shop.service.BoardService;
import com.board.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final BoardService boardService;
    private CustomAuthenticationFailureHandler handler;


    //main
    @GetMapping("/main/main")
    @Transactional
    public String main(Model model, @PageableDefault(page = 0, size = 5, sort = "boardIdx", direction = Sort.Direction.DESC) Pageable pageable,@AuthenticationPrincipal MyUserDetails userDetails) {
        Page<BoardEntity> boardall= boardService.boardall(pageable);
        model.addAttribute("boardall", boardall);
        List<BoardEntity> notice = boardService.boardnotice();
        model.addAttribute("notice", notice );
        model.addAttribute("grade",userDetails.getGrade());
        
        return "main/main";
    }

    @GetMapping("/main/view") // 마이페이지
    public String view(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails);
        return "main/view";
    }

    @GetMapping("/main/modify") // 회원정보 수정
    public String modify(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails);
        return "main/modify";
    }

    @PostMapping("/main/modify_proc") // 수정 정보 저장
    public String modifyproc(MemberDTO dto) {
        memberService.update(dto);
        return "redirect:/logout";
    }

    @GetMapping("/main/delete")
    public String memberdelete(@AuthenticationPrincipal MyUserDetails userDetails) {
        Long idx = userDetails.getIdx();
        memberService.delete(idx);
        return "redirect:/logout";
    }

    @GetMapping("/join/login") // 로그인
    public String login() {
        return "/join/login";
    }

    @GetMapping("/join/join")
    public String join() {
        return "join/join";
    }

    @PostMapping("/join/join")
    public String joincheck(@Valid MemberDTO dto, BindingResult bindingResult, Model model) {
        int i = memberService.joinCheck(model,bindingResult);
        if(i==0) {
            return "redirect:/join/login";
        }else{
            return "/join/join";
        }
    }

    @GetMapping("/admin/list")
    public String list(){

        return "admin/list";
    }
    @GetMapping("/vip/gold")
    public String gold(){

        return "vip/gold";
    }

}