package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.OrderRequestInsertDto;
import com.rookies.assignment.dto.request.OrderRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private IOrderService service;

    @GetMapping("/order/{id}")
    @ResponseBody
    public ResponseDto get(@PathVariable("id") UUID id){
        return service.getById(id);
    }

    @GetMapping("/orders")
    @ResponseBody
    public ResponseByPageDto listAll(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listAll(page-1, size);
    }

    @PostMapping("/order")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody OrderRequestInsertDto dto, HttpServletRequest request){
        return service.insert(dto, request);
    }

    @PutMapping("/order/{id}/status")
    @ResponseBody
    public ResponseDto updateStatus(@PathVariable("id") UUID id, @Valid @RequestBody OrderRequestUpdateDto dto){
        dto.setOrder_id(id);
        return service.updateStatus(dto);
    }

    @DeleteMapping("/order")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) UUID id){
        return service.delete(id);
    }
}
