package com.ananya.springboot.thymeleafdemo.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ananya.springboot.thymeleafdemo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public List<Customer> findAllByOrderByLastNameAsc();

	// add a method to search by first name and last name
	public List<Customer> findByFirstNameContainsAndLastNameContainsAllIgnoreCase(
			String theFirstName, String theLastName);
			
}
