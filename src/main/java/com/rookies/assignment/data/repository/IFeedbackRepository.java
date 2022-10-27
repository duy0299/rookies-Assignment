package com.rookies.assignment.data.repository;

import com.rookies.assignment.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rookies.assignment.data.entity.Feedback;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer>{
    Page<Feedback> findAll(Pageable pageable);
}
