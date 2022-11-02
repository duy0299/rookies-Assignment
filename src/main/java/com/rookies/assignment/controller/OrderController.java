package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.OrderRequestDto;
import com.rookies.assignment.dto.request.OrderRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@RestController
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
    public ResponseDto listAll(){
        return service.listAll();
    }

    @PostMapping("/order")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody OrderRequestDto dto, HttpServletRequest request){
        return service.insert(dto, request);
    }

    @PutMapping("/order/status")
    @ResponseBody
    public ResponseDto updateStatus(@Valid @RequestBody OrderRequestUpdateDto dto){
        return service.updateStatus(dto);
    }

    @DeleteMapping("/order")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) UUID id){
        return service.delete(id);
    }
}
