package com.rookies.assignment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.entity.Role;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, UUID>{

}
