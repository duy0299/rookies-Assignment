package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.SizeResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Size;

@Component
public interface ISizeService {

	public ResponseDto<SizeResponseDto> insert(SizeDtoFlat dto);

	public ResponseDto<SizeResponseDto> update(SizeDtoFlat dto);
	
	public ResponseDto<SizeResponseDto> delete(Integer id);
	
	public ResponseDto<SizeResponseDto> getById(Integer id);

	public ResponseDto<List<SizeResponseDto>> listAll();
	
}
