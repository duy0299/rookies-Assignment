package com.rookies.assignment.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesProductModelRepository extends JpaRepository<IContactRepository, Integer>{
	
	
}
