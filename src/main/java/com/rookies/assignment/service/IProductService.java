package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateDto;
import com.rookies.assignment.dto.response.ProductResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface IProductService {

//	insert a list Product with the same ProductModel
	public ResponseDto<ProductResponseDto>  insert(ProductRequestInsertDto listDto);
	
	public ResponseDto<ProductResponseDto>  update(ProductRequestUpdateDto dto);

	public ResponseDto<ProductResponseDto> updateAvatar(ProductRequestUpdateAvatarDto dto);

	public ResponseDto<ProductResponseDto> updateStatus(UUID productID);

	public ResponseDto<ProductResponseDto> delete(UUID id);
	
	public ResponseDto<ProductResponseDto> getById(UUID id);

	public ResponseDto<List<ProductResponseDto>> listAll();
}
