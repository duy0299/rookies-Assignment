package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.WishlistRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WishlistController {
    @Autowired
    private WishlistServiceImpl service;

    @GetMapping("/wishlist")
    @ResponseBody
    public ResponseDto get(@RequestParam(name = "id", required = true) Integer id){
        return service.getById(id);
    }

    @GetMapping("/wishlists")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("/wishlist")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody WishlistRequestDto dto){
        return service.insert(dto);
    }

    @PutMapping("/wishlist")
    @ResponseBody
    public ResponseDto update(@Valid @RequestBody WishlistRequestDto dto){
        return service.updateStatus(dto);
    }

    @DeleteMapping("/wishlist")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) Integer id){
        return service.delete(id);
    }
}
