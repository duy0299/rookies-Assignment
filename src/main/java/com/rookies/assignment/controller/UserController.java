package com.rookies.assignment.controller;

import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.user.UserRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private IUserInfoService service;


    @PutMapping("/user/with-token/info")
    @ResponseBody
    public ResponseDto<UserInfoResponseDto> updateInfo(@Valid @RequestBody UserInfoDtoFlat dto, HttpServletRequest request){
        return service.update(dto, request);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable("id")UUID id){
        return service.delete(id);
    }

    @PutMapping("/user/{id}/status")
    @ResponseBody
    public ResponseDto<UserInfoResponseDto> updateStatus(@PathVariable("id")UUID id, @RequestParam(name = "status" )boolean status){
        System.out.println("start");
        return service.updateStatus(id, status);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN', 'USER_MANAGER')")
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
    public ResponseDto<UserInfoResponseDto> getByToken(HttpServletRequest request){
        return service.getByToken(request);
    }

    @PutMapping("/user/with-token/password")
    @ResponseBody
    public ResponseDto<UserInfoResponseDto> updatePassword( @Valid @RequestBody UserRequestUpdatePasswordDto dto, HttpServletRequest request){
        return service.updatePassword(dto, request);
    }

    @PutMapping("/user/{id}/roles")
    @ResponseBody
    public ResponseDto<UserInfoResponseDto> updateRole(@PathVariable("id")UUID id, @Valid @RequestBody UserRequestUpdateRoleDto dto){
        dto.setUserID(id);
        return service.updateRole(dto);
    }

    @PutMapping("/user/with-token/avatar")
    @ResponseBody
    public ResponseDto<UserInfoResponseDto> updateAvatar(@RequestParam(name = "fileAvatar" )MultipartFile fileAvatar, HttpServletRequest request){
        UserRequestUpdateAvatarDto dto = new UserRequestUpdateAvatarDto();
        dto.setFileAvatar(fileAvatar);
        return service.updateAvatar(dto, request);
    }
}
