package com.rookies.assignment.controller;


import com.rookies.assignment.data.entity.Contact;
import com.rookies.assignment.dto.request.CartRequestDto;
import com.rookies.assignment.dto.request.ContactRequestDto;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.CartServiceImpl;
import com.rookies.assignment.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartServiceImpl service;

    @GetMapping("")
    @ResponseBody
    public ResponseDto<List<CartDto>> get(HttpSession session){
        return service.get(session);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseDto<List<CartDto>> add(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "default");
    }

    @PostMapping("/up")
    @ResponseBody
    public ResponseDto<List<CartDto>> up(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "up");
    }

    @PostMapping("/down")
    @ResponseBody
    public ResponseDto<List<CartDto>> down(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "down");
    }

    @PostMapping("/change")
    @ResponseBody
    public ResponseDto<List<CartDto>> change(@Valid @RequestBody CartRequestDto dto, HttpSession session){
        return service.addToCartByMethod(dto, session, "change");
    }
}
