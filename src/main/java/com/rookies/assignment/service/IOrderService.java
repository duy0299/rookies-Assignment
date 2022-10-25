package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.request.OrderRequestDto;
import com.rookies.assignment.dto.request.OrderRequestUpdateDto;
import com.rookies.assignment.dto.response.OrderResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Order;

import javax.servlet.http.HttpSession;

@Component
public interface IOrderService {

	public ResponseDto<OrderResponseDto> insert(OrderRequestDto order, HttpSession session);
	
	public ResponseDto<OrderResponseDto> updateStatus(OrderRequestUpdateDto order);
	
	public ResponseDto<OrderResponseDto> delete(UUID id);
	
	public ResponseDto<OrderResponseDto> getById(UUID id);

	public ResponseDto<List<OrderResponseDto>> listAll();
	
	
}
