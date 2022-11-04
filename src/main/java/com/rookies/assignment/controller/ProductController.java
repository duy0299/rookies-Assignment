package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateDto;
import com.rookies.assignment.dto.response.ProductResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseDto<List<ProductResponseDto>> all(){
        return service.listAll();
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

    @PutMapping(value = "/product")
    @ResponseBody
    public ResponseDto<ProductResponseDto> updateNoAvatar( @RequestParam(name="sizeID")int sizeID,      @RequestParam(name="modelID")UUID modelID,
                                                           @RequestParam(name="name")String name,       @RequestParam(name="saleType")String saleType,
                                                           @RequestParam(name="quantity")int quantity,  @RequestParam(name="productID" )UUID productID,
                                                           @RequestParam(name="priceSale") BigDecimal priceSale ){
        ProductRequestUpdateDto dto = new ProductRequestUpdateDto();
        dto.setId(productID);
        dto.setName(name);
        dto.setQuantity(quantity);
        dto.setSizeID(sizeID);
        dto.setModelID(modelID);
        dto.setSaleType(saleType);
        dto.setPriceSale(priceSale);
        return service.update(dto);
    }

    @PutMapping(value = "/product/avatar")
    @ResponseBody
    public ResponseDto<ProductResponseDto> updateAvatar( @RequestParam(name="productID" )UUID productID,
                                                         @RequestParam(name="fileAvatar")MultipartFile fileAvatar ){
        ProductRequestUpdateAvatarDto dto = new ProductRequestUpdateAvatarDto();
        dto.setProductID(productID);
        dto.setFileAvatar(fileAvatar);
        return service.updateAvatar(dto);
    }

    @PutMapping(value = "/product/status")
    @ResponseBody
    public ResponseDto<ProductResponseDto> updateStatus( @RequestParam(name="productID" )UUID productID){
        return service.updateStatus(productID);
    }

    @DeleteMapping(value = "/product")
    @ResponseBody
    public ResponseDto<ProductResponseDto> delete(@RequestParam(name = "id", required = true) UUID id){
        return service.delete(id);
    }
}
