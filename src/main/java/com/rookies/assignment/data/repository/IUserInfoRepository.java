package com.rookies.assignment.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, UUID>{


    public UserInfo findByEmailAndPassword(String email, String pass);

    public UserInfo findByEmail(String email);
}
