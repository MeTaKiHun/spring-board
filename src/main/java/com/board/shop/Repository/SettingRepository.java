package com.board.shop.Repository;

import com.board.shop.Entity.NoticeSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<NoticeSettingEntity,Long> {
}
