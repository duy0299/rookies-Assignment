package com.rookies.assignment.data.repository;



import com.rookies.assignment.data.entity.CategoriesProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesProductModelRepository extends JpaRepository<CategoriesProductModel, Integer>{
	
	
}
