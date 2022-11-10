package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateDto;
import com.rookies.assignment.dto.response.ProductResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping(value = "/product/{id}")
    @ResponseBody
    public ResponseDto<ProductResponseDto> get(@PathVariable("id") UUID id){
        return service.getById(id);
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseByPageDto<List<ProductResponseDto>> all(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listAll(page-1, size);
    }

    @PostMapping(value = "/product")
    @ResponseBody
    public ResponseDto<ProductResponseDto> insert( @RequestParam(name="sizeID")int sizeID,      @RequestParam(name="modelID")UUID modelID,
                                                   @RequestParam(name="name")String name,       @RequestParam(name="saleType")String saleType,
                                                   @RequestParam(name="quantity")int quantity,  @RequestParam(name="fileAvatar" )MultipartFile fileAvatar,
                                                   @RequestParam(name="priceSale") BigDecimal priceSale ){
        ProductRequestInsertDto dto = new ProductRequestInsertDto();
        dto.setName(name);
        dto.setQuantity(quantity);
        dto.setSaleType(saleType);
        dto.setPriceSale(priceSale);

        dto.setFileAvatar(fileAvatar);
        dto.setSizeID(sizeID);
        dto.setModelID(modelID);
        return service.insert(dto);
    }

    @PutMapping(value = "/product/{id}")
    @ResponseBody
    public ResponseDto<ProductResponseDto> updateNoAvatar(@PathVariable("id") UUID productID,
                                                          @Valid @RequestBody ProductRequestUpdateDto request){
        request.setId(productID);
        return service.update(request);
    }

    @PutMapping(value = "/product/{id}/avatar")
    @ResponseBody
    public ResponseDto<ProductResponseDto> updateAvatar( @PathVariable("id")UUID productID,
                                                         @RequestParam(name="fileAvatar")MultipartFile fileAvatar ){
        ProductRequestUpdateAvatarDto dto = new ProductRequestUpdateAvatarDto();
        dto.setProductID(productID);
        dto.setFileAvatar(fileAvatar);
        return service.updateAvatar(dto);
    }

    @PutMapping(value = "/product/{id}/status")
    @ResponseBody
    public ResponseDto<ProductResponseDto> updateStatus( @PathVariable("id")UUID id,
                                                         @RequestParam(name = "status", required = true) boolean status){
        return service.updateStatus(id, status);
    }

    @DeleteMapping(value = "/product/{id}")
    @ResponseBody
    public ResponseDto<ProductResponseDto> delete(@PathVariable("id") UUID id){
        return service.delete(id);
    }
}
