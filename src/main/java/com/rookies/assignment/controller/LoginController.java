package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.request.UserRegisterRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl service;

    @GetMapping("/login")
    @ResponseBody
    public ResponseDto login(@RequestBody LoginRequestDto dto, HttpServletRequest req){

        return service.login(dto, req);
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseDto logout(HttpServletRequest req){
        return service.logout(req);
    }

    @GetMapping("/register")
    @ResponseBody
    public ResponseDto register(@RequestBody UserRegisterRequestDto dto, HttpServletRequest req){
        return service.register(dto, req);
    }


}
