package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.request.RegisterRequestDto;
import com.rookies.assignment.dto.response.JwtResponse;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.security.userpincal.UserPrinciple;
import com.rookies.assignment.service.IRoleService;
import com.rookies.assignment.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServiceImpl service;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    @ResponseBody
    public ResponseDto login(@RequestBody LoginRequestDto dto, HttpServletRequest req){

//        check trên database
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
//        đăng ký qua SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        tạo token
        String token = jwtProvider.createToken(authentication);
//        Lấy UserPrinciple
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setName(userPrinciple.getEmail());
        jwtResponse.setToken(token);
        jwtResponse.setListRole(userPrinciple.getAuthorities());
        return new ResponseDto(jwtResponse);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseDto logout(HttpServletRequest req){
        return service.logout(req);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseDto register(@RequestBody RegisterRequestDto dto){
        System.out.println("test ");
        return service.register(dto);
    }


}
