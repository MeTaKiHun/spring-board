package com.board.shop.Repository;

import com.board.shop.Entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
    NoticeEntity findByNoticeIdx(Long idx);
}
