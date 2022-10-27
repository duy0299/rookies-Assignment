package com.rookies.assignment.service;

import com.rookies.assignment.dto.request.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateImageDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public interface IProductModelService {

	public ResponseDto<ProductModelResponseDto> insert(ModelRequestInsertDto model);
	
	public ResponseDto<ProductModelResponseDto> update(ModelRequestUpdateDto model);

	public ResponseDto<ProductModelResponseDto> updateImage(ModelRequestUpdateImageDto dto);

	public ResponseDto<ProductModelResponseDto> updateStatus(UUID id, boolean status);

	public ResponseDto<ProductModelResponseDto> delete(UUID id);
	
	public ResponseDto<ProductModelResponseDto> getById(UUID id);

	public ResponseDto<List<ProductModelResponseDto>> listAll();

	public ResponseByPageDto<List<ProductModelResponseDto>> listByPage(int page, int size);

	public ResponseByPageDto<List<ProductModelResponseDto>> listByName(String name, int page, int size);
	public ResponseByPageDto<List<ProductModelResponseDto>> listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom, int page, int size);
}
