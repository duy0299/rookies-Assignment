package com.rookies.assignment.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.Size;

@Repository
public interface ISizeRepository  extends JpaRepository<Size, Integer>{

}
