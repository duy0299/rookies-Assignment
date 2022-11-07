package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.request.OrderRequestInsertDto;
import com.rookies.assignment.dto.request.OrderRequestUpdateDto;
import com.rookies.assignment.dto.response.OrderResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface IOrderService {

	public ResponseDto<OrderResponseDto> insert(OrderRequestInsertDto order, HttpServletRequest request);
	
	public ResponseDto<OrderResponseDto> updateStatus(OrderRequestUpdateDto order);
	
	public ResponseDto<OrderResponseDto> delete(UUID id);
	
	public ResponseDto<OrderResponseDto> getById(UUID id);

	public ResponseByPageDto<List<OrderResponseDto>> listAll(int page, int size);
	
	
}
