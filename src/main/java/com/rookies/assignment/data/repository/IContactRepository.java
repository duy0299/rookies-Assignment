package com.rookies.assignment.data.repository;

import com.rookies.assignment.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {
    @Override
    List<Contact> findAll();


}
