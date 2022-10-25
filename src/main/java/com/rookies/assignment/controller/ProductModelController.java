package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateImageDto;
import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductModelController {

    @Autowired
    private IProductModelService service;

    @GetMapping("/product-model")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> get(@RequestParam(name = "id", required = true) UUID id){
        return service.getById(id);
    }

    @GetMapping("/product-models")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> listAll(){
        return service.listAll();
    }

    @PostMapping("/product-model")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> insert(@RequestParam(name="images")List<MultipartFile> images,
               @RequestParam(name="modelName")String modelName,             @RequestParam(name="priceRoot")BigDecimal priceRoot,
               @RequestParam(name="description")String description,         @RequestParam(name="categoriesID")List<Integer> categoriesID,
               @RequestParam(name="sizeID")List<Integer> sizeID,            @RequestParam(name="priceSale") List<BigDecimal> priceSale,
               @RequestParam(name="name")List<String> productName,          @RequestParam(name="saleType")List<String> saleType,
               @RequestParam(name="quantity")List<Integer> quantity,        @RequestParam(name="fileAvatar" )List<MultipartFile> fileAvatar ){
        List<ProductRequestInsertDto> listProduct = new ArrayList<>();
        for (int i=0; i<productName.size(); i++) {
            ProductRequestInsertDto productInsert  = new ProductRequestInsertDto();

            productInsert.setName(productName.get(i));
            productInsert.setQuantity(quantity.get(i));
            productInsert.setSizeID(sizeID.get(i));
            productInsert.setSaleType(saleType.get(i));
            productInsert.setPriceSale(priceSale.get(i));
            productInsert.setFileAvatar(fileAvatar.get(i));

            listProduct.add(productInsert);
        }
        ModelRequestInsertDto dto = new ModelRequestInsertDto();
        dto.setListCategoriesID(categoriesID);
        dto.setName(modelName);
        dto.setDescription(description);
        dto.setListImages(images);
        dto.setListProduct(listProduct);
        dto.setPriceRoot(priceRoot);
        return service.insert(dto);
    }


    @PutMapping("/product-model/info")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> updateInfo( @RequestParam(name="id")UUID id,
                                                            @RequestParam(name="modelName")String modelName, @RequestParam(name="priceRoot")BigDecimal priceRoot,
                                                            @RequestParam(name="description")String description,  @RequestParam(name="categoriesID")List<Integer> categoriesID){
        ModelRequestUpdateDto dto = new ModelRequestUpdateDto();
        dto.setId(id);
        dto.setListCategoriesID(categoriesID);
        dto.setName(modelName);
        dto.setDescription(description);
        dto.setPriceRoot(priceRoot);
        return service.update(dto);
    }

    @PutMapping("/product-model/images")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> updateImage( @RequestParam(name="id")UUID id, @RequestParam(name="images")List<MultipartFile> images){
        ModelRequestUpdateImageDto dto = new ModelRequestUpdateImageDto();
        dto.setId(id);
        dto.setListImages(images);
        return service.updateImage(dto);
    }

    @PutMapping("/product-model/status")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> updateStatus( @RequestParam(name="id")UUID id, @RequestParam(name="status")boolean status){
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/product-model/status")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> delete( @RequestParam(name="id")UUID id){
        return service.delete(id);
    }

    @GetMapping("/product-models/search-name")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> searchName(@RequestParam(name="search")String name){
        return service.listByName(name);
    }

    @GetMapping("/product-models/list-price-range")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> listByPriceRange(@RequestParam(name="priceFrom")BigDecimal priceFrom, @RequestParam(name="priceTo")BigDecimal priceTo){
        return service.listByPriceRange(priceTo, priceFrom);
    }

}
