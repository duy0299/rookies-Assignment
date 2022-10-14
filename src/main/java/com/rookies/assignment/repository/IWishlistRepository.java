package com.rookies.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.entity.Wishlist;

@Repository
public interface IWishlistRepository extends JpaRepository<Wishlist, Integer>{

}
