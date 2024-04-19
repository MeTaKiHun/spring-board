package com.board.shop.Repository;

import com.board.shop.Entity.PrefaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefaceRepository extends JpaRepository<PrefaceEntity,Long> {
    PrefaceEntity findByPrefaceIdx(Long idx);
}
