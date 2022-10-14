package com.rookies.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.entity.Rating;

@Repository
public interface IRatingRepository  extends JpaRepository<Rating, Integer>{

}
