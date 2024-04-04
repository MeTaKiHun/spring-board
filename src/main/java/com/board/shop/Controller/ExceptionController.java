package com.board.shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Controller
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String allException(Exception e){
        String allmessege = "오류 발생<br>"+e.getMessage();
        return allmessege;
    }

    @ExceptionHandler(DataIntegrityViolationException.class )
    public String nullException(DataIntegrityViolationException n){
        String nullmessege = "값이 비어있어 처리에 실패하였습니다.<br>"+n.getMessage();
        return nullmessege;
    }

    @RequestMapping("/error-page/404")
    public String error404(HttpServletRequest req, HttpServletResponse resp) {
        log.info("errorPage 404");
        printErrorInfo(req);
        return "error-page/404";
    }
    @RequestMapping("/error-page/403")
    public String error403(HttpServletRequest req, HttpServletResponse resp) {
        log.info("errorPage 403");
        printErrorInfo(req);
        return "권한이 부족합니다";
    }

    @RequestMapping("/error-page/500")
    public String error500(HttpServletRequest req, HttpServletResponse resp) {
        log.info("errorPage 500");
        printErrorInfo(req);
        return "error-page/500";
    }

    private void printErrorInfo(HttpServletRequest req) {
        log.info("dispatchTypes= {}", req.getDispatcherType());
    }
}
