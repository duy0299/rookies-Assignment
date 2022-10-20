package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.dto.request.WishlistRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.WishlistResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Wishlist;

@Component
public interface IWishlistService {

	public ResponseDto<WishlistResponseDto> insert(WishlistRequestDto dto);
	
	public ResponseDto<WishlistResponseDto> updateStatus(WishlistRequestDto dto);
	
	public ResponseDto delete(Integer id);
	
	public ResponseDto<WishlistResponseDto> getById(Integer id);

	public ResponseDto<List<WishlistResponseDto>> listAll();
}
