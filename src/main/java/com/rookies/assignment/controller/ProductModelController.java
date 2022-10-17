package com.rookies.assignment.controller;

import com.rookies.assignment.data.entity.Contact;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.ProductModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/productmodel")
public class ProductModelController {

    @Autowired
    private ProductModelServiceImpl  service;

    @GetMapping("/with-dto/all")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> list(){
        return service.listAll();
    }

}
