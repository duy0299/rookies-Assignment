package com.rookies.assignment.service;

import com.rookies.assignment.dto.request.ContactRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Contact;

@Component
public interface IContactService {
	
	public ResponseDto<Contact> update(ContactRequestDto contact);
	
	public ResponseDto<Contact> get();
}
