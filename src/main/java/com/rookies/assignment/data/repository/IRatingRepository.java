package com.rookies.assignment.data.repository;

import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.Rating;

import java.util.UUID;

@Repository
public interface IRatingRepository  extends JpaRepository<Rating, Integer>{
    @Query(value = "SELECT * from rating  WHERE user_id=?1 AND product_model_id=?2", nativeQuery = true)
    Rating findByUserAndMode(UUID userId, UUID modelId);
}
