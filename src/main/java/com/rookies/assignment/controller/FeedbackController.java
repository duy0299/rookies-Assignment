package com.rookies.assignment.controller;

import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.request.FeedbackRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class FeedbackController {
    @Autowired
    private IFeedbackService service;

    @GetMapping("/feedback")
    @ResponseBody
    public ResponseDto get(@RequestParam(name = "id", required = true) Integer id){
        return service.getById(id);
    }

    @GetMapping("/feedbacks")
    @ResponseBody
    public ResponseDto listAll(){
        return service.listAll();
    }

    @DeleteMapping("/feedback")
    @ResponseBody
    public ResponseDto delete(@RequestParam(name = "id", required = true) Integer id){
        return service.delete(id);
    }

    @PostMapping("/feedback")
    @ResponseBody
    public ResponseDto insert(@Valid @RequestBody FeedbackRequestDto dto){
        return service.insert(dto);
    }

    @PutMapping("/feedback")
    @ResponseBody
    public ResponseDto update(@Valid @RequestBody FeedbackRequestDto dto){
        return service.updateStatus(dto);
    }
}
