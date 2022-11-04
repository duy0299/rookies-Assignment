package com.rookies.assignment.controller;


import com.rookies.assignment.data.entity.Contact;
import com.rookies.assignment.dto.request.ContactRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/contact")
@CrossOrigin
public class ContactController {
    @Autowired
    private ContactServiceImpl service;

    @GetMapping("")
    @ResponseBody
    public ResponseDto<Contact> get(){
        return service.get();
    }

    @PutMapping("")
    @ResponseBody
    public ResponseDto<Contact> put(@Valid @RequestBody ContactRequestDto dto){
        return service.update(dto);
    }
}
