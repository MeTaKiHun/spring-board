package com.board.shop.Controller;

import com.board.shop.DTO.CommentDTO;
import com.board.shop.DTO.CommentdelDTO;
import com.board.shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public void comment(@RequestBody CommentDTO commentDTO){
        commentService.commentSave(commentDTO);
    }

    @PostMapping("/modify")
    public void commentmodify(@RequestBody CommentdelDTO commentdelDTO){
        commentService.commentmodify(commentdelDTO);
    }

    @PostMapping("/delete")
    public void commentdelete(@RequestBody CommentdelDTO commentdelDTO){
        commentService.commentdel(commentdelDTO.getCommentIdx());
    }
}

