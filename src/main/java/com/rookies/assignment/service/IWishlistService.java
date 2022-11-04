package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.request.WishlistRequestInsertDto;
import com.rookies.assignment.dto.request.WishlistRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.WishlistResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface IWishlistService {

	public ResponseDto<WishlistResponseDto> insert(WishlistRequestInsertDto dto, HttpServletRequest request);
	
	public ResponseDto<WishlistResponseDto> updateStatus(WishlistRequestUpdateDto dto);
	
	public ResponseDto delete(Integer id);
	
	public ResponseDto<WishlistResponseDto> getById(Integer id);

	public ResponseDto<List<WishlistResponseDto>> listAll();

	public ResponseDto<List<WishlistResponseDto>> listByUser(HttpServletRequest request);
}
