package com.board.shop.Controller;

import com.board.shop.DTO.NoticeDTO;
import com.board.shop.DTO.NoticeSettingDTO;
import com.board.shop.DTO.PrefaceDTO;
import com.board.shop.Entity.MemberEntity;
import com.board.shop.Entity.NoticeEntity;
import com.board.shop.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/main")
    public String main(){
        return "/admin/main";
    }

    @GetMapping("/memberlist")
    public String memberlist(Model model){
        List<MemberEntity> memberEntity = adminService.MemberList();
        model.addAttribute("member",memberEntity);
        return "/admin/memberlist";
    }

    @GetMapping("/boardlist")
    public String boardlist(Model model){
        List<NoticeEntity> notice = adminService.boardfind();
        model.addAttribute("notice", notice);
        return "/admin/boardlist";
    }

    @GetMapping("/boarddel")
    public String boarddel(){
        return "/admin/boarddel";
    }


    @GetMapping("/boardcreate")
    public String boardcreate(){return "/admin/boardcreate";}

    @GetMapping("/boardcreate_proc")
    public String boardsave(NoticeDTO noticeDTO, NoticeSettingDTO settingDTO){
        adminService.boardsave(noticeDTO,settingDTO);
        return "redirect:/admin/boardlist";
    }

    @GetMapping("/preface")
    public String preface(Model model){
        model.addAttribute("preface",adminService.prefeceall());
        return "/admin/preface";}

    @GetMapping("/preface_proc")
    public String prefacesave(PrefaceDTO prefaceDTO){
        adminService.prefacesave(prefaceDTO);
        return "redirect:/admin/preface";
    }
}

