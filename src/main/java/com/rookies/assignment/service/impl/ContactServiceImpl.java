package com.rookies.assignment.dto.response.impl;

import java.util.List;
import java.util.Optional;

import com.rookies.assignment.dto.request.ContactRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import com.rookies.assignment.data.entity.Contact;
import com.rookies.assignment.data.repository.IContactRepository;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.IContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {
	
	@Autowired
	private IContactRepository contactRepository;


	@Override
	public ResponseDto<Contact> update(ContactRequestDto contact) {
		Optional<List<Contact>> contactOld = Optional.ofNullable(contactRepository.findAll());
		if(contactOld.isEmpty()){
			throw new ResourceFoundException("hiện chưa có contact");
		}
		int contact_id = contactOld.get().get(0).getId();
		Long view = contactOld.get().get(0).getViewPage();
		Contact contactNew = contact.setContact(contact_id, view);
		contactRepository.save(contactNew);
		return new ResponseDto<Contact>("thành công", contactNew);
	}

	@Override
	public ResponseDto<Contact> get() {
		Optional<List<Contact>> contact = Optional.ofNullable(contactRepository.findAll());
		if(contact.isEmpty()){
			throw new ResourceFoundException("hiện chưa có contact");
		}
		Contact contactResult = contact.get().get(0);
		return new ResponseDto<Contact>("thành công", contactResult);
	}
	



}
