package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.RatingRequestInsertDto;
import com.rookies.assignment.dto.request.RatingRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
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
    public ResponseByPageDto listAll(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listAll(page-1, size);
    }

    @PostMapping("/rating")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody RatingRequestInsertDto dto, HttpServletRequest request){
        return service.insert(dto, request);
    }

    @PutMapping("/rating/{id}")
    @ResponseBody
    public ResponseDto update(@PathVariable("id") Integer id, @Valid @RequestBody RatingRequestUpdateDto dto){
        dto.setId(id);
        return service.update(dto);
    }


    @PutMapping("/rating/{id}/status")
    @ResponseBody
    public ResponseDto updateStatus(@PathVariable("id") Integer id, @Valid @RequestBody RatingRequestUpdateDto dto){
        dto.setId(id);
        return service.updateStatus(dto);
    }


    @DeleteMapping("/rating/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }
}
