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



    @GetMapping("/rating")
    @ResponseBody
    public ResponseDto get(@RequestParam(name = "id", required = true) Integer id){
        return service.getById(id);
    }

    @GetMapping("/ratings")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("/rating")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody RatingRequestDto dto){
        return service.insert(dto);
    }

    @PutMapping("/rating")
    @ResponseBody
    public ResponseDto update(@Valid @RequestBody RatingRequestDto dto){
        return service.update(dto);
    }


    @PutMapping("/rating/status")
    @ResponseBody
    public ResponseDto updateStatus(@Valid @RequestBody RatingRequestDto dto){
        return service.updateStatus(dto);
    }


    @DeleteMapping("")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) Integer id){
        return service.delete(id);
    }
}
