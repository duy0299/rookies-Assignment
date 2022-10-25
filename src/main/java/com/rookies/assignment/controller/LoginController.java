package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.request.RegisterRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl service;

    @PostMapping("/login")
    @ResponseBody
    public ResponseDto login(@RequestBody LoginRequestDto dto, HttpServletRequest req){
        return service.login(dto, req);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseDto logout(HttpServletRequest req){
        return service.logout(req);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseDto register(@RequestBody RegisterRequestDto dto, HttpServletRequest req){
        return service.register(dto, req);
    }


}
