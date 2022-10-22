package com.rookies.assignment.service;

import com.rookies.assignment.data.entity.Contact;
import com.rookies.assignment.dto.request.CartRequestDto;
import com.rookies.assignment.dto.request.ContactRequestDto;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Component
public interface ICartService {
	
	public ResponseDto<List<CartDto>> get(HttpSession session);
	
	public ResponseDto<List<CartDto>> addToCartByMethod(CartRequestDto dto, HttpSession session,String method);

}
