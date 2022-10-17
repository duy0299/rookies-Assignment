package com.rookies.assignment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.request.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface IProductModelService {

	public ResponseDto<ProductModelResponseDto> insert(ModelRequestInsertDto model);
	
	public ResponseDto<ProductModelResponseDto> update(ModelRequestUpdateDto model);

	public ResponseDto<ProductModelResponseDto> changeStatusDelete(UUID id);
	
	public ResponseDto<ProductModelResponseDto> getById(UUID id);

	public ResponseDto<List<ProductModelResponseDto>> listAll();

	public ResponseDto<List<ProductModelResponseDto>> listByName(String name);
	public ResponseDto<List<ProductModelResponseDto>> listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom);
}
