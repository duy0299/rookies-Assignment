package com.rookies.assignment.data.repository;

import com.rookies.assignment.data.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IWishlistRepository extends JpaRepository<Wishlist, Integer>{
    @Query(value = "SELECT * from wishlist  WHERE user_id=?1 AND product_model_id=?2", nativeQuery = true)
    Wishlist findByUserAndMode(UUID userId,UUID modelId);

}
