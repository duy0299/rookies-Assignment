package com.rookies.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rookies.assignment.entity.Feedback;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer>{

}