package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.RatingRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.plugins.CheckRole;
import com.rookies.assignment.service.impl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {
    @Autowired
    private RatingServiceImpl service;
    @Autowired
    private CheckRole checkRole;


    @GetMapping("")
    @ResponseBody
    public ResponseDto get(@RequestParam(name = "id", required = true) Integer id){
        return service.getById(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody RatingRequestDto dto, HttpSession session){
//        if(!checkRole.checkLevelUser(2, session)){
//            throw new ForbiddenException("Bạn không phải User");
//        }
        return service.insert(dto);
    }

    @PutMapping("")
    @ResponseBody
    public ResponseDto update(@Valid @RequestBody RatingRequestDto dto, HttpSession session){
        if(!checkRole.checkLevelUser(2, session)){
            throw new ForbiddenException("Bạn không phải User");
        }
        return service.update(dto);
    }


    @PutMapping("/status")
    @ResponseBody
    public ResponseDto updateStatus(@Valid @RequestBody RatingRequestDto dto, HttpSession session){
        if(!checkRole.checkLevelUser(1, session)){
            throw new ForbiddenException("Bạn không phải User");
        }
        return service.updateStatus(dto);
    }


//    @DeleteMapping("")
//    @ResponseBody
//    public ResponseDto delete(@RequestParam(name = "id", required = true) Integer id){
//        return service.delete(id);
//    }
}
