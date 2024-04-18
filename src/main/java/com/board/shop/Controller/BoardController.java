package com.board.shop.Controller;

import com.board.shop.DTO.BoardDTO;
import com.board.shop.DTO.FileDTO;
import com.board.shop.DTO.MyUserDetails;
import com.board.shop.Entity.BoardEntity;
import com.board.shop.Entity.CommentEntity;
import com.board.shop.Entity.NoticeEntity;
import com.board.shop.service.AdminService;
import com.board.shop.service.BoardService;
import com.board.shop.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final AdminService adminService;
    private final CommentService commentService;

    @GetMapping("/board")
    public String board(HttpSession session, Model model,
                        @PageableDefault(page=0,size = 5,sort ="boardIdx",direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(name= "bcidx",required = false, defaultValue = "1")Long bcidx,
                        @RequestParam(name="search",required = false)String search,
                        @RequestParam(name="searchtype",required = false)String searchtype,
                        @RequestParam(name= "size",required = false)String size) {
        List<NoticeEntity> notice =adminService.boardfind();
        model.addAttribute("notice", notice);
        session.setAttribute("bcidx",bcidx);
        Page<BoardEntity> result= boardService.board(bcidx,pageable);
        boardService.page(result,model,bcidx,search,searchtype);
        if(search!=null&&searchtype!="none"){
            boardService.search(search, pageable, searchtype);
        }
        if(size!=null) {
            if (Integer.parseInt(size) >= 5) {
                pageable = PageRequest.of(pageable.getPageNumber(), Integer.parseInt(size), pageable.getSort());
            }
        }
        return "/board/board";
    }

    @GetMapping("/view")
    public String boardview(@RequestParam("boardIdx") Long boardIdx, Model model){
        BoardEntity board = boardService.boardView(boardIdx);
        List<CommentEntity> commentResult = commentService.findCommentsByBoardIdx(boardIdx);
        model.addAttribute("view", board);
        model.addAttribute("comment", commentResult);
        return "/board/view";

    }

    @GetMapping("/write")
    public String write(@AuthenticationPrincipal MyUserDetails user,Model model,HttpSession session) {
        model.addAttribute("user", user);
        model.addAttribute("preface",adminService.prefeceall());
        Long bcidx =(Long)session.getAttribute("bcidx");
        model.addAttribute("bcidx",bcidx);
        return "/board/write";
    }

    @PostMapping("/write_proc")
    public String writeproc(BoardDTO bdto,@RequestParam("idx")Long idx , FileDTO fdto, HttpSession session
            ,@RequestParam("files[]") List<MultipartFile> files ) throws Exception {
        Long bcidx =(Long)session.getAttribute("bcidx");
        boardService.boardSave(bdto,idx,fdto,files,bcidx);
        return "redirect:/board/board?bcidx="+bcidx;
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("boardIdx")Long boardidx, Model model, @AuthenticationPrincipal MyUserDetails userDetails){
        String boarduserid = boardService.getuserid(boardidx);
        if((userDetails.getName()).equals(boarduserid)) {
            BoardEntity board = boardService.boardView(boardidx);
            model.addAttribute("view", board);
            model.addAttribute("preface",adminService.prefeceall());
            return "/board/modify";
        }else {
            return "/board/modify";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("boardIdx")Long boardidx,HttpSession session){
        Long bcidx= (Long) session.getAttribute("bcidx");
        boardService.boarDelete(boardidx);
        return "/board/board?bcidx="+bcidx;
    }

}
