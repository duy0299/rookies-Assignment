package com.rookies.assignment.controller;

import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserInfoServiceImpl userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public UserInfoResponseDto login(@RequestParam(name="email", required = true) String email,
                                     @RequestParam(name="password", required = true) String password){

        return null;
    }
}
