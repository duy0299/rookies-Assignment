package com.rookies.assignment.dto.response.impl;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.dto.request.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.IProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ProductModelServiceImpl implements IProductModelService {
    @Autowired
    private IProductModelRepository repository;

    @Override
    public ResponseDto<ProductModelResponseDto> insert(ModelRequestInsertDto model) {

        return null;
    }

    @Override
    public ResponseDto<ProductModelResponseDto> update(ModelRequestUpdateDto model) {
        return null;
    }

//    change satatus of this  product model  to false:  hide the product, don't sell anymore.
    @Override
    public ResponseDto<ProductModelResponseDto> changeStatusDelete(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Sản phẩm");
        }
        ProductModel model = optional.get();
        model.setStatus(false);
        repository.save(model);
        return new ResponseDto<ProductModelResponseDto>(new ProductModelResponseDto(model));
    }

//    get a model product by ID
    @Override
    public ResponseDto<ProductModelResponseDto> getById(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Sản phẩm");
        }
        return new ResponseDto<ProductModelResponseDto>(new ProductModelResponseDto(optional.get()));
    }


//    List all model product
    @Override
    public ResponseDto<List<ProductModelResponseDto>> listAll() {
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
//        ProductModel =>convert to ProductModelResponseDto => add to listResult
        for (ProductModel model: listOptional.get()) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return new ResponseDto<List<ProductModelResponseDto>>(listResult);
    }

//    Search product model by name
    @Override
    public ResponseDto<List<ProductModelResponseDto>> listByName(String name) {
        name = changeToText(name);
        ResponseDto<List<ProductModelResponseDto>> all = listAll();
        List<ProductModelResponseDto> result = new ArrayList<ProductModelResponseDto>();
        String check = "^.*"+name+".*$";
        for(ProductModelResponseDto p : all.getResult()) {
            if(Pattern.matches(check, changeToText(p.getName()))) {
                result.add(p);
            }
        }
        return new ResponseDto<List<ProductModelResponseDto>>(result);
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom) {
        ResponseDto<List<ProductModelResponseDto>> all = listAll();
        List<ProductModelResponseDto> result = new ArrayList<ProductModelResponseDto>();
        for (ProductModelResponseDto dto: all.getResult()) {
            if(dto.getPriceTo().compareTo(priceTo) != 1  && dto.getPriceFrom().compareTo(priceFrom)!=-1){
                result.add(dto);
            }
        }
        return new ResponseDto<List<ProductModelResponseDto>>(result);
    }

    //Change accented characters to unsigned
    public String changeToText(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("á|à|ả|ạ|ã|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ", "a");
        text = text.replaceAll("é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ", "e");
        text = text.replaceAll("i|í|ì|ỉ|ĩ|ị", "i");
        text = text.replaceAll("ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ", "o");
        text = text.replaceAll("ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự", "u");
        text = text.replaceAll("ý|ỳ|ỷ|ỹ|ỵ", "y");
        text = text.replaceAll("đ", "d");
        return text;
    }
}
