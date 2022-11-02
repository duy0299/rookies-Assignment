package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.RatingRequestInsertDto;
import com.rookies.assignment.dto.request.RatingRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RatingController {
    @Autowired
    private RatingServiceImpl service;

    @GetMapping("/rating/{id}")
    @ResponseBody
    public ResponseDto get(@PathVariable("id") Integer id){
        return service.getById(id);
    }

    @GetMapping("/ratings")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("/rating")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody RatingRequestInsertDto dto, HttpServletRequest request){
        return service.insert(dto, request);
    }

    @PutMapping("/rating")
    @ResponseBody
    public ResponseDto update(@Valid @RequestBody RatingRequestUpdateDto dto){
        return service.update(dto);
    }


    @PutMapping("/rating/status")
    @ResponseBody
    public ResponseDto updateStatus(@Valid @RequestBody RatingRequestUpdateDto dto){
        return service.updateStatus(dto);
    }


    @DeleteMapping("")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) Integer id){
        return service.delete(id);
    }
}
