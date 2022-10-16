package com.rookies.assignment.controller;

import com.rookies.assignment.data.entity.Contact;
import com.rookies.assignment.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactServiceImpl service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> index(){


        return service.get();
    }
}
