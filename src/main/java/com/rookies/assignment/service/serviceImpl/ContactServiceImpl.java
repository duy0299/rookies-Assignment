package com.rookies.assignment.service.serviceImpl;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import com.rookies.assignment.entity.Contact;
import com.rookies.assignment.service.IContactService;
import org.springframework.stereotype.Service;

import com.rookies.assignment.entity.Contact;

@Service
public class ContactServiceImpl implements IContactService {
	
//	@Autowired
//	private IContactRepository contactRepository;
	

	@Override
	public Contact update(int id, Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> get() {
//		contactRepository.findById(id);
		return null;
	}
	
	public boolean deleteContact(int id) {
		
		return true;
	}


}
