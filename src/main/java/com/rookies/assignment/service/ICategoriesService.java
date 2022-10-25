package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.response.CategoriesResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface ICategoriesService {

	public ResponseDto<CategoriesResponseDto> insert(CategoriesDtoFlat dto);
	
	public ResponseDto<CategoriesResponseDto> update(CategoriesDtoFlat dto);
	
	public ResponseDto<CategoriesResponseDto> delete(Integer id);
	
	public ResponseDto<CategoriesResponseDto> getById(Integer id);

	public ResponseDto<List<CategoriesResponseDto>> listAll();
}
