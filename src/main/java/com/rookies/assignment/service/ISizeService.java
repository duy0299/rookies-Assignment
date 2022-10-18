package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Size;

@Component
public interface ISizeService {

	public ResponseDto<Size> insert(SizeDtoFlat dto);

	public ResponseDto<Size> update(SizeDtoFlat dto);
	
	public ResponseDto<Size> delete(Integer id);
	
	public ResponseDto<Size> getById(Integer id);

	public ResponseDto<List<Size>> listAll();
	
}
