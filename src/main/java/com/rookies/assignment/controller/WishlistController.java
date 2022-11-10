package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.WishlistRequestInsertDto;
import com.rookies.assignment.dto.request.WishlistRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class WishlistController {
    @Autowired
    private WishlistServiceImpl service;

    @GetMapping("/wishlist/{id}")
    @ResponseBody
    public ResponseDto get(@PathVariable("id") Integer id){
        return service.getById(id);
    }

    @GetMapping("/wishlists")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("/wishlist")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody WishlistRequestInsertDto dto, HttpServletRequest request){
        return service.insert(dto,  request);
    }

    @PutMapping("/wishlist/{id}/status")
    @ResponseBody
    public ResponseDto update(@PathVariable("id") Integer id,  @RequestParam(name="page")Boolean status){
        WishlistRequestUpdateDto dto = new WishlistRequestUpdateDto();
        dto.setId(id);
        dto.setStatus(status);
        return service.updateStatus(dto);
    }

    @DeleteMapping("/wishlist/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }
}
