package com.board.shop.service;

import com.board.shop.DTO.BoardDTO;
import com.board.shop.DTO.FileDTO;
import com.board.shop.Entity.*;
import com.board.shop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;
    private final NoticeRepository noticeRepository;
    private final PrefaceRepository prefaceRepository;

    public Page<BoardEntity> board(Long bcidx, Pageable pageable) {
            return boardRepository.findByNotice(bcidx,pageable);
    }

    @Transactional
    public Page<BoardEntity> boardall(Pageable pageable){
        return boardRepository.findAll(pageable);
    }
    @Transactional
    public BoardEntity boardView(Long boardidx) {
        return boardRepository.findByeeeee(boardidx);
    }


    @Transactional
    public List<BoardEntity> boardnotice(){
        return boardRepository.findByPrefacePrefaceIdx(1L);
    }

    @Transactional
    public void boardSave(BoardDTO bdto, Long idx, FileDTO fdto, List<MultipartFile> files, Long bcidx) throws Exception {
        MemberEntity memberEntity = memberRepository.findByIdx(idx);
        BoardEntity boardEntity = bdto.toEntity();
        PrefeceEntity prefece = prefaceRepository.findByPrefaceIdx(bdto.getPrefaceIdx());
        NoticeEntity notice = noticeRepository.findById(bcidx).orElseThrow(() -> new IllegalStateException(bcidx+"에 해당하는 데이터가 없습니다."));
        boardEntity.setNotice(notice);
        boardEntity.setPreface(prefece);
        boardEntity.setMember(memberEntity);
        if(files.size()>=0) {
            saveFile(boardEntity, fdto, files);
        }else{
            boardRepository.save(boardEntity);
        }
    }

    @Transactional
    public void saveFile(BoardEntity bdto, FileDTO fdto, List<MultipartFile> files) throws Exception {
            BoardEntity bEntity = boardRepository.save(bdto);
            fileService.fileSave(fdto,files,bEntity);
    }

    public void boarDelete(Long boardIdx){
        BoardEntity board=boardRepository.findByBoardIdx(boardIdx);
        for (int i = 0; i < board.getFiles().size(); i++) {
            File file = new File(board.getFiles().get(i).getFilepath());
            if(board.getFiles()!=null){
                file.delete();
            }
        }
        boardRepository.deleteById(boardIdx);
    }


    public void page(Page<BoardEntity> result, Model model,Long bcidx){
        int boardall = boardRepository.findByNo(bcidx).size();
        List<BoardEntity> boardnotice = new ArrayList<>();
        for (int i = 0; i < boardall; i++) {
            boardnotice = boardRepository.findByPrefacePrefaceIdx(1L);
        }
        int j = 2; // 페이지 양 옆 크기
        int linkpage = result.getPageable().getPageNumber(); // 현재페이지 -1;
        int nowpage = result.getPageable().getPageNumber() + 1;// 현재 페이지
        int startpage = Math.max(nowpage - j, 1);//시작페이지
        int endpage = 0;//끝페이지
        int pagesize = 5 * linkpage;//글 번호 지정을 위한 숫자
        model.addAttribute("pagesize",pagesize);
        if (result.getTotalPages() != 0) {
            endpage = Math.min(nowpage + j, result.getTotalPages());
        } else {
            endpage = 1;
        }
        int maxpage = result.getTotalPages();
        model.addAttribute("boardnotice",boardnotice);
        model.addAttribute("number",boardall);
        model.addAttribute("bcidx", bcidx);
        model.addAttribute("board", result);
        model.addAttribute("linkpage", linkpage);
        model.addAttribute("j", j);
        model.addAttribute("nowpage", nowpage);
        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);
        model.addAttribute("maxpage", maxpage);
    }

    @Transactional
    public String getuserid(Long boardidx){
        return boardRepository.findByBoardIdx(boardidx).getMember().getName();

    }
}