package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.FeedbackRequestInsertDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class FeedbackController {
    @Autowired
    private IFeedbackService service;

    @GetMapping("/feedback/{id}")
    @ResponseBody
    public ResponseDto get(@PathVariable("id") Integer id){
        return service.getById(id);
    }

    @GetMapping("/feedbacks")
    @ResponseBody
    public ResponseByPageDto listAll(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listAll(page-1, size);
    }

    @DeleteMapping("/feedback/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }

    @PostMapping("/feedback")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody FeedbackRequestInsertDto dto, HttpServletRequest request){
        return service.insert(dto, request);
    }

    @PutMapping("/feedback/status/{id}")
    @ResponseBody
    public ResponseDto update(@PathVariable("id") int id,
                              @RequestParam(name = "status", required = true) short status){
        return service.updateStatus(id, status);
    }
}
