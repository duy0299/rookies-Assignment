package com.rookies.assignment.controller;

import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.RatingRequestDto;
import com.rookies.assignment.dto.request.UserRequestDto;
import com.rookies.assignment.dto.request.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.plugins.CheckRole;
import com.rookies.assignment.service.impl.RatingServiceImpl;
import com.rookies.assignment.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserInfoServiceImpl service;
    @Autowired
    private CheckRole checkRole;

    @GetMapping("")
    @ResponseBody
    public ResponseDto get(@RequestParam(name = "id", required = true) UUID id){
        return service.getById(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PutMapping("/info")
    @ResponseBody
    public ResponseDto updateInfo(@Valid @RequestBody UserInfoDtoFlat dto, HttpSession session){
//        session.getAttribute()
        return service.update(dto);
    }

    @PutMapping("/password")
    @ResponseBody
    public ResponseDto updatePassword(@Valid @RequestBody UserRequestUpdatePasswordDto dto, HttpSession session){
//        session.getAttribute()
        return service.updatePassword(dto);
    }

    @PutMapping("/role")
    @ResponseBody
    public ResponseDto updateRole(@Valid @RequestBody UserRequestUpdateRoleDto dto, HttpSession session){
//        if(!checkRole.checkLevelUser(1, 7, session)){
//            throw new ForbiddenException("Bạn không phải Admin");
//        }
        return service.updateRole(dto);
    }
}
