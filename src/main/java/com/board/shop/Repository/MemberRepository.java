package com.board.shop.Repository;
import com.board.shop.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByUserid(String userid);
    MemberEntity findByIdx(Long idx);
}
