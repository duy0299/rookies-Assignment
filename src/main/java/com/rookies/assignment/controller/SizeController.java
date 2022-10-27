package com.rookies.assignment.controller;

import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.SizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SizeController {

    @Autowired
    private SizeServiceImpl service;

    @GetMapping("/size/{id}")
    @ResponseBody
    public ResponseDto get( @PathVariable("id") Integer id){
        return service.getById(id);
    }

    @GetMapping("/sizes")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("/size")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody SizeDtoFlat dto){
        return service.insert(dto);
    }

    @PutMapping("/size")
    @ResponseBody
    public ResponseDto update(@Valid @RequestBody SizeDtoFlat dto){
        return service.update(dto);
    }

    @DeleteMapping("/size")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) Integer id){
        return service.delete(id);
    }
}
