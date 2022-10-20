package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.request.FeedbackRequestDto;
import com.rookies.assignment.dto.response.FeedbackResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Feedback;

@Component
public interface IFeedbackService {

	public ResponseDto<FeedbackResponseDto> insert(FeedbackRequestDto feedback);

	
	public ResponseDto<FeedbackResponseDto> delete(Integer id);

	public ResponseDto<FeedbackResponseDto> updateStatus(FeedbackRequestDto feedback);
	
	public ResponseDto<FeedbackResponseDto> getById(Integer id);

	public ResponseDto<List<FeedbackResponseDto>> listAll();
	
}
