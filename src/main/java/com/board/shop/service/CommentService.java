package com.board.shop.service;

import com.board.shop.DTO.CommentDTO;
import com.board.shop.DTO.CommentdelDTO;
import com.board.shop.Entity.BoardEntity;
import com.board.shop.Entity.CommentEntity;
import com.board.shop.Repository.BoardRepository;
import com.board.shop.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void commentSave(CommentDTO commentDTO){
        BoardEntity findBoard = boardRepository.findById(commentDTO.getBoardIdx()).orElseThrow(() -> new IllegalStateException("ㅁㅁ"));
        CommentEntity comment = CommentEntity.builder()
                .userid(commentDTO.getUserid())
                .content(commentDTO.getContent())
                .board(findBoard)
                .build();
        commentRepository.save(comment);
    }

    public List<CommentEntity> findCommentsByBoardIdx(Long boardIdx) {
        return commentRepository.findCommentByBoard(boardIdx);
    }

    public void commentdel(Long cidx){
        CommentEntity comment = commentRepository.findByCommentIdx(cidx);
        commentRepository.delete(comment);
    }

    public void commentmodify(CommentdelDTO commentdelDTO){
        CommentEntity comment = commentRepository.findByCommentIdx(commentdelDTO.getCommentIdx());
        comment.setContent(commentdelDTO.getContent());
        commentRepository.save(comment);
    }
}
