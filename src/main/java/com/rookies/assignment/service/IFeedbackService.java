package com.rookies.assignment.service;

import com.rookies.assignment.dto.request.FeedbackRequestInsertDto;
import com.rookies.assignment.dto.response.FeedbackResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public interface IFeedbackService {

	public ResponseDto<FeedbackResponseDto> insert(FeedbackRequestInsertDto dto, HttpServletRequest request);

	
	public ResponseDto<FeedbackResponseDto> delete(Integer id);

	public ResponseDto<FeedbackResponseDto> updateStatus(int id, short status);
	
	public ResponseDto<FeedbackResponseDto> getById(Integer id);

	public ResponseByPageDto<List<FeedbackResponseDto>> listAll(int page, int size);
	
}
