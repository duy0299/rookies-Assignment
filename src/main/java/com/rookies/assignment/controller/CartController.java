package com.rookies.assignment.controller;


import com.rookies.assignment.dto.request.CartRequestDto;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServiceImpl service;

    @GetMapping("/carts")
    @ResponseBody
    public ResponseDto<List<CartDto>> get(HttpSession session){
        return service.get(session);
    }

    @PostMapping("/cart")
    @ResponseBody
    public ResponseDto<List<CartDto>> add(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "default");
    }

    @PostMapping("/cart/up")
    @ResponseBody
    public ResponseDto<List<CartDto>> up(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "up");
    }

    @PostMapping("/cart/down")
    @ResponseBody
    public ResponseDto<List<CartDto>> down(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "down");
    }

    @PostMapping("/cart/change")
    @ResponseBody
    public ResponseDto<List<CartDto>> change(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "change");
    }
}
