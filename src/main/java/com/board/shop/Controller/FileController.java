package com.board.shop.Controller;

import com.board.shop.DTO.BoardDTO;
import com.board.shop.DTO.FileDTO;
import com.board.shop.DTO.MyUserDetails;
import com.board.shop.service.BoardService;
import com.board.shop.service.FileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
public class FileController {
    private final FileService fileService;
    private final BoardService boardService;

    @GetMapping("/filedelete")
    public String filedelete(@RequestParam("fileidx") Long fileidx, @RequestParam("boardidx") Long boardidx) {
        System.out.println("fileidx = " + fileidx);
        fileService.fileDelete(fileidx);
        return "redirect:/board/modify?boardidx=" + boardidx;
    }

    @PostMapping("/modify_proc")
    public String modifyporc(BoardDTO bdto, FileDTO fdto, HttpSession session, @RequestParam(value = "files[]",required = false) List<MultipartFile> files, @AuthenticationPrincipal MyUserDetails userDetails) throws Exception {
        Long bcidx = (Long) session.getAttribute("bcidx");
        boardService.boardSave(bdto, userDetails.getIdx(), fdto, files, bcidx);
        return "redirect:/board/board?bcidx=" + bcidx;
    }
    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("file", fileService.fileGallery());
        return "/board/gallery";
    }
}
