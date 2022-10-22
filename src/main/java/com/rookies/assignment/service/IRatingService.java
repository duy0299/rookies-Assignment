package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.request.RatingRequestDto;
import com.rookies.assignment.dto.response.RatingResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Rating;

@Component
public interface IRatingService {

	public ResponseDto<RatingResponseDto> insert(RatingRequestDto dto);

	public ResponseDto<RatingResponseDto> update(RatingRequestDto dto);

	public ResponseDto<RatingResponseDto> updateStatus(RatingRequestDto dto);
	
//	public ResponseDto delete(Integer id);
	
	public ResponseDto<RatingResponseDto> getById(Integer id);

	public ResponseDto<List<RatingResponseDto>>  listAll();

}
