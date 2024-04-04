package com.board.shop.service;

import com.board.shop.DTO.NoticeDTO;
import com.board.shop.DTO.NoticeSettingDTO;
import com.board.shop.DTO.PrefaceDTO;
import com.board.shop.Entity.MemberEntity;
import com.board.shop.Entity.NoticeEntity;
import com.board.shop.Entity.NoticeSettingEntity;
import com.board.shop.Entity.PrefeceEntity;
import com.board.shop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;
    private final SettingRepository noticeSetting;
    private final PrefaceRepository prefaceRepository;
    private final BoardRepository boardRepository;

    public List<NoticeEntity> boardfind() {
        return noticeRepository.findAll();
    }


    public List<MemberEntity> MemberList() {
        return memberRepository.findAll();
    }

    public void boardsave(NoticeDTO noticeDTO, NoticeSettingDTO settingDTO) {
        NoticeSettingEntity setting = settingDTO.toEntity();
        NoticeEntity notice = noticeDTO.toEntity(setting);
        noticeSetting.save(setting);
        noticeRepository.save(notice);
    }

    public void prefacesave(PrefaceDTO prefaceDTO) {
        prefaceRepository.save(prefaceDTO.toEntity());
    }

    public List<PrefeceEntity> prefeceall() {
        return prefaceRepository.findAll(Sort.by(Sort.Direction.DESC, "belong"));
    }

}