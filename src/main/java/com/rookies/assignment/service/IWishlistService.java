package com.rookies.assignment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Wishlist;

@Component
public interface IWishlistService {

	public Wishlist insert(Wishlist wishlist);
	
	public Wishlist update(Wishlist Wishlist);
	
	public void delete(Integer id);
	
	public Wishlist getById(Integer id);

	public List<Wishlist> listAll();
}
