package com.rookies.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.entity.ModelImage;

@Repository
public interface IModelImageRepository extends JpaRepository<ModelImage, Integer>{

}
