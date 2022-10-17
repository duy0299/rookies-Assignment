package com.rookies.assignment.service.impl;

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

    @Override
    public ResponseDto<ProductModelResponseDto> changeStatusDelete(UUID id) {
        return null;
    }

    @Override
    public ResponseDto<ProductModelResponseDto> getById(UUID id) {
        return null;
    }

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
        return new ResponseDto<>(listResult);
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listByName(String name) {
        return null;
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom) {
        return null;
    }


}
