package com.board.shop.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        // 로그인 실패 시 수행할 작업
        loginfail(request,exception);
        // 실패 페이지로 리다이렉트 또는 다른 로직 구현
        response.sendRedirect("/join/login?error=true");
    }

    // 기존에 정의한 loginfail 메서드
    public void loginfail(HttpServletRequest request,Exception exception) throws IOException {
        String errorMessage = "로그인에 실패하였습니다.";
        String id = request.getParameter("userid");
        String pwd = request.getParameter("password");
        if(id.equals("")||id==null){
            errorMessage = "아이디를 입력해주세요";
        }else if(pwd.equals("")||pwd==null){
            errorMessage = "비밀번호를 입력해주세요";
        }else if(exception instanceof BadCredentialsException){
            errorMessage = "아이디 또는 비밀번호가 잘못되었습니다.";
        }
        request.getSession().setAttribute("loginerror",errorMessage);
    }
}
