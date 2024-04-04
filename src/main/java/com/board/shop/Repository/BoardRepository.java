package com.board.shop.Repository;

import com.board.shop.Entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query(value = "SELECT distinct b FROM BoardEntity b LEFT JOIN FETCH b.notice n LEFT JOIN FETCH b.member m LEFT JOIN FETCH b.preface p WHERE n.noticeIdx = :noticeIdx")
    Page<BoardEntity> findByNotice(@Param("noticeIdx")Long idx,Pageable pageable);

    @Query(value = "SELECT b FROM BoardEntity b LEFT JOIN FETCH b.notice n LEFT JOIN FETCH b.member m WHERE n.noticeIdx = :noticeIdx")
    List<BoardEntity> findByNo(@Param("noticeIdx")Long idx);

    BoardEntity findByBoardIdx(Long boardidx);

    List<BoardEntity> findByPrefacePrefaceIdx(Long prefaceIdx);

    @EntityGraph(attributePaths = "member")
    Page<BoardEntity> findAll(Pageable pageable);

    @Query(value = "SELECT b FROM BoardEntity b LEFT JOIN FETCH b.files JOIN FETCH b.member LEFT JOIN FETCH b.preface p WHERE b.boardIdx = :boardIdx")
    BoardEntity findByeeeee(@Param("boardIdx")Long boardidx);


}
