package com.rookies.assignment.controller;

import com.rookies.assignment.dto.request.*;
import com.rookies.assignment.dto.request.productmodel.ModelAndProductRequestInsertDto;
import com.rookies.assignment.dto.request.productmodel.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.productmodel.ModelRequestUpdateDto;
import com.rookies.assignment.dto.request.productmodel.ModelRequestUpdateImageDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
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
@CrossOrigin
public class ProductModelController {

    @Autowired
    private IProductModelService service;

    @GetMapping("/model/{id}")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> get(@PathVariable("id") UUID id){
        return service.getById(id);
    }

    @GetMapping("/models/all-status")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> listAll(){
        return service.listAll();
    }

    @PostMapping("/model-with-products")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> insertModelAndProduct(
               @RequestParam(name="images")List<MultipartFile> images,
               @RequestParam(name="modelName")String modelName,             @RequestParam(name="priceRoot")BigDecimal priceRoot,
               @RequestParam(name="description")String description,         @RequestParam(name="categoriesID")Integer categoriesID,
               @RequestParam(name="sizeID")List<Integer> sizeID,            @RequestParam(name="priceSale") List<BigDecimal> priceSale,
               @RequestParam(name="productName")List<String> productName,   @RequestParam(name="saleType")List<String> saleType,
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

        ModelAndProductRequestInsertDto dto = new ModelAndProductRequestInsertDto();
        dto.setCategoriesID(categoriesID);
        dto.setName(modelName);
        dto.setDescription(description);
        dto.setListImages(images);
        dto.setListProduct(listProduct);
        dto.setPriceRoot(priceRoot);

        return service.insertModelAndProduct(dto);
    }

    @PostMapping("/model")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> insert(@RequestParam(name="images")List<MultipartFile> images,
                  @RequestParam(name="name")String name,             @RequestParam(name="priceRoot")BigDecimal priceRoot,
                  @RequestParam(name="description")String description,   @RequestParam(name="categoriesID")Integer categoriesID){
        ModelRequestInsertDto dto = new ModelRequestInsertDto();
        dto.setCategoriesID(categoriesID);
        dto.setName(name);
        dto.setDescription(description);
        dto.setListImages(images);
        dto.setPriceRoot(priceRoot);

        return service.insert(dto);
    }


    @PutMapping("/model/{id}/info")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> updateInfo( @PathVariable("id")UUID id,
                    @RequestParam(name="modelName")String modelName, @RequestParam(name="priceRoot")BigDecimal priceRoot,
                    @RequestParam(name="description")String description,  @RequestParam(name="categoriesID")Integer categoriesID){
        ModelRequestUpdateDto dto = new ModelRequestUpdateDto();
        dto.setId(id);
        dto.setCategoriesID(categoriesID);
        dto.setName(modelName);
        dto.setDescription(description);
        dto.setPriceRoot(priceRoot);
        return service.update(dto);
    }

    @PutMapping("/model/{id}/images")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> updateImage( @PathVariable("id")UUID id, @RequestParam(name="images")List<MultipartFile> images){
        ModelRequestUpdateImageDto dto = new ModelRequestUpdateImageDto();
        dto.setId(id);
        dto.setListImages(images);
        return service.updateImage(dto);
    }

    @PutMapping("/model/{id}/status")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> updateStatus( @PathVariable("id")UUID id, @RequestParam(name="status")boolean status){
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/model/{id}")
    @ResponseBody
    public ResponseDto<ProductModelResponseDto> delete( @PathVariable("id")UUID id){
        return service.delete(id);
    }


    @GetMapping("/models")
    @ResponseBody
    public ResponseByPageDto<List<ProductModelResponseDto>> searchName(@RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listByPage(page-1, size);
    }

    @GetMapping("/models/search")
    @ResponseBody
    public ResponseByPageDto<List<ProductModelResponseDto>> searchName(@RequestParam(name="search")String name,
                                                                 @RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listByName(name, page-1, size);
    }

    @GetMapping("/models/by-category/{id}")
    @ResponseBody
    public ResponseByPageDto<List<ProductModelResponseDto>> listByCategories(@PathVariable("id")Integer categoryID,
                                                                       @RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listByCategories(categoryID, page-1, size);
    }

    @GetMapping("/models/price-range")
    @ResponseBody
    public ResponseByPageDto<List<ProductModelResponseDto>> listByPriceRange(@RequestParam(name="priceFrom")BigDecimal priceFrom, @RequestParam(name="priceTo")BigDecimal priceTo,
                                                                       @RequestParam(name="page")int page, @RequestParam(name="size")int size){
        return service.listByPriceRange(priceTo, priceFrom, page-1, size);
    }

    @GetMapping("/models/new")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> listNewProduct(){
        return service.listNewProduct();
    }

    @GetMapping("/models/most-popular")
    @ResponseBody
    public ResponseDto<List<ProductModelResponseDto>> listMostPopularProduct(){
        return service.listMostPopularProduct();
    }

}
