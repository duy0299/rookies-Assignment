package com.rookies.assignment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Contact;

@Component
public interface IContactService {
	
	public Contact update(int id, Contact contact);
	
	public List<Contact> get();
}
