package com.board.shop.service;

import com.board.shop.Repository.PrefaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceTest {

    @Autowired AdminService adminService;
    @Autowired
    PrefaceRepository prefaceRepository;


    @Test
    void test() {
        prefaceRepository.findAll();
    }

}