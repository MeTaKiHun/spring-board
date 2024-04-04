package com.board.shop.Repository;

import com.board.shop.Entity.PrefeceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefaceRepository extends JpaRepository<PrefeceEntity,Long> {
    PrefeceEntity findByPrefaceIdx(Long idx);
}
