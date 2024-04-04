package com.board.shop.Repository;

import com.board.shop.Entity.BoardEntity;
import com.board.shop.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    CommentEntity findByBoard(BoardEntity board);

    @Query("select c from CommentEntity c join c.board b where b.boardIdx = :boardIdx")
    List<CommentEntity> findCommentByBoard(@Param("boardIdx") Long boardIdx);

    CommentEntity findByCommentIdx(Long idx);
}
