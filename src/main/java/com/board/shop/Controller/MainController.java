package com.board.shop.Controller;

import com.board.shop.DTO.MemberDTO;
import com.board.shop.DTO.MyUserDetails;
import com.board.shop.Entity.BoardEntity;
import com.board.shop.service.BoardService;
import com.board.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final BoardService boardService;

    //main
    @RequestMapping("/main/main")
    public String main(HttpSession session, Model model,
                       @PageableDefault(page = 0, size = 5, sort = "boardIdx", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(name = "bcidx", required = false, defaultValue = "1") Long bcidx) {
        Page<BoardEntity> boardall= boardService.boardall(pageable);
        model.addAttribute("boardall", boardall);
        Page<BoardEntity> result = boardService.board(bcidx, pageable);
        boardService.page(result, model, bcidx);
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
        System.out.println(dto.toEntity());
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
        return "join/login";
    }

    @GetMapping("/join/join")
    public String join() {
        return "join/join";
    }

    @PostMapping("/join/join_proc")
    public String joinproc(MemberDTO dto) {
        memberService.joinsave(dto);
        return "redirect:/join/login";
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