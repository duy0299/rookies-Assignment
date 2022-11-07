package com.rookies.assignment.controller;

import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.user.UserRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private IUserInfoService service;
    @Autowired
    private JwtProvider jwtProvider;


    @PutMapping("/user/{id}/info")
    @ResponseBody
    public ResponseDto updateInfo(@PathVariable("id")UUID id, @Valid @RequestBody UserInfoDtoFlat dto, HttpSession session){
        dto.setId(id);
        return service.update(dto);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable("id")UUID id){
        return service.delete(id);
    }

    @PutMapping("/user/{id}/status")
    @ResponseBody
    public ResponseDto updateStatus(@PathVariable("id")UUID id, @RequestParam(name = "status" )boolean status){
        System.out.println("start");
        return service.updateStatus(id, status);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseDto getById(@PathVariable("id") UUID id){
        return service.getById(id);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseByPageDto listAll(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listAll(page-1, size);
    }

    @GetMapping("/user/with-token")
    @ResponseBody
    public ResponseDto getByToken(HttpServletRequest request){
        return service.getByToken(request);
    }

    @PutMapping("/user/{id}/password")
    @ResponseBody
    public ResponseDto updatePassword(@PathVariable("id")UUID id, @Valid @RequestBody UserRequestUpdatePasswordDto dto, HttpSession session){
        dto.setUserID(id);
        return service.updatePassword(dto);
    }

    @PutMapping("/user/{id}/roles")
    @ResponseBody
    public ResponseDto updateRole(@PathVariable("id")UUID id, @Valid @RequestBody UserRequestUpdateRoleDto dto, HttpSession session){
        dto.setUserID(id);
        return service.updateRole(dto);
    }

    @PutMapping("/user/{id}/avatar")
    @ResponseBody
    public ResponseDto updateAvatar(@PathVariable("id") UUID id,
                                    @RequestParam(name = "fileAvatar" )MultipartFile fileAvatar, HttpSession session){
        UserRequestUpdateAvatarDto dto = new UserRequestUpdateAvatarDto();
        dto.setUserID(id);
        dto.setFileAvatar(fileAvatar);
        return service.updateAvatar(dto);
    }
}
