package com.rookies.assignment.data.repository;

import java.util.UUID;


import com.rookies.assignment.data.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoriesRepository  extends JpaRepository<Categories, Integer>{

	
}
