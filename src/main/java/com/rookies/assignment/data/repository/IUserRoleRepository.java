package com.rookies.assignment.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rookies.assignment.data.entity.UserRole;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Integer>{

}
