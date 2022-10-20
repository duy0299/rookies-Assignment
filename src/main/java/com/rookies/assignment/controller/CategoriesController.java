package com.rookies.assignment.controller;

import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.response.CategoriesResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService service;

    @GetMapping(value = "")
    @ResponseBody
    public ResponseDto<CategoriesResponseDto>  get(@RequestParam(name = "id", required = true) int id){
        return service.getById(id);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public ResponseDto<List<CategoriesResponseDto>>  listAll(){
        return service.listAll();
    }

    @DeleteMapping(value = "")
    @ResponseBody
    public ResponseDto<CategoriesResponseDto>  delete(@RequestParam(name = "id", required = true) int id){
        return service.delete(id);
    }

    @PostMapping(value = "")
    @ResponseBody
    public ResponseDto<CategoriesResponseDto>  insert(@Valid @RequestBody CategoriesDtoFlat dto){
        return service.insert(dto);
    }

    @PutMapping(value = "")
    @ResponseBody
    public ResponseDto<CategoriesResponseDto>  update(@Valid @RequestBody CategoriesDtoFlat dto){
        return service.update(dto);
    }
}
