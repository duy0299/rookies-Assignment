package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.request.RatingRequestInsertDto;
import com.rookies.assignment.dto.request.RatingRequestUpdateDto;
import com.rookies.assignment.dto.response.RatingResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface IRatingService {

	public ResponseDto<RatingResponseDto> insert(RatingRequestInsertDto dto, HttpServletRequest request);

	public ResponseDto<RatingResponseDto> update(RatingRequestUpdateDto dto);

	public ResponseDto<RatingResponseDto> updateStatus(RatingRequestUpdateDto dto);
	
	public ResponseDto delete(Integer id);
	
	public ResponseDto<RatingResponseDto> getById(Integer id);

	public ResponseByPageDto<List<RatingResponseDto>> listAll(int page, int size);

}
