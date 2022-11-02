package com.rookies.assignment.controller;


import com.rookies.assignment.dto.request.CartRequestDto;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServiceImpl service;

    @GetMapping("/carts")
    @ResponseBody
    public ResponseDto<List<CartDto>> get(HttpServletRequest request){
        return service.get(request);
    }

    @PostMapping("/cart")
    @ResponseBody
    public ResponseDto<List<CartDto>> add(@Valid @RequestBody CartRequestDto dto, HttpServletRequest request){
        return service.addToCartByMethod(dto, request, "default");
    }

    @PostMapping("/cart/up")
    @ResponseBody
    public ResponseDto<List<CartDto>> up(@Valid @RequestBody CartRequestDto dto, HttpServletRequest request){
        return service.addToCartByMethod(dto, request, "up");
    }

    @PostMapping("/cart/down")
    @ResponseBody
    public ResponseDto<List<CartDto>> down(@Valid @RequestBody CartRequestDto dto, HttpServletRequest request){
        return service.addToCartByMethod(dto, request, "down");
    }

    @PostMapping("/cart/change")
    @ResponseBody
    public ResponseDto<List<CartDto>> change(@Valid @RequestBody CartRequestDto dto, HttpServletRequest request){
        return service.addToCartByMethod(dto, request, "change");
    }
}
