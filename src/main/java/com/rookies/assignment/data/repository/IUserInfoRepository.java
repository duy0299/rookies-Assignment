package com.rookies.assignment.data.repository;

import java.util.UUID;

import com.rookies.assignment.data.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, UUID>{

    Page<UserInfo> findAll(Pageable pageable);
    public UserInfo findByEmailAndPassword(String email, String pass);

    public UserInfo findByEmail(String email);


}
