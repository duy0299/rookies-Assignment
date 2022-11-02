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


    @PutMapping("/user/info")
    @ResponseBody
    public ResponseDto updateInfo(@Valid @RequestBody UserInfoDtoFlat dto, HttpSession session){
//        session.getAttribute()
        return service.update(dto);
    }

    @DeleteMapping("/user")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "userID" )UUID id, HttpSession session){
//        if(!checkRole.checkLevelUser(1, 7, session)){
//            throw new ForbiddenException("Bạn không có đủ quyền để thực hiện");
//        }
        return service.delete(id);
    }

    @PutMapping("/user/status")
    @ResponseBody
    public ResponseDto updateStatus(@RequestParam(name = "userID" )UUID id, @RequestParam(name = "status" )boolean status){
        System.out.println("start");
        return service.updateStatus(id, status);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseDto get(@PathVariable("id") UUID id){
        return service.getById(id);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseByPageDto listAll(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listAll(page, size);
    }



    @PutMapping("/user/password")
    @ResponseBody
    public ResponseDto updatePassword(@Valid @RequestBody UserRequestUpdatePasswordDto dto, HttpSession session){

        return service.updatePassword(dto);
    }

    @PutMapping("/user/roles")
    @ResponseBody
    public ResponseDto updateRole(@Valid @RequestBody UserRequestUpdateRoleDto dto, HttpSession session){
        return service.updateRole(dto);
    }

    @PutMapping("/user/avatar")
    @ResponseBody
    public ResponseDto updateAvatar(@RequestParam(name = "userID" ) UUID id,
                                    @RequestParam(name = "fileAvatar" )MultipartFile fileAvatar, HttpSession session){
        UserRequestUpdateAvatarDto dto = new UserRequestUpdateAvatarDto();
        dto.setUserID(id);
        dto.setFileAvatar(fileAvatar);
        return service.updateAvatar(dto);
    }
}
