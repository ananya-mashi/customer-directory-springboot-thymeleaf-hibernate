package com.ananya.springboot.thymeleafdemo.service;

import java.util.List;

import com.ananya.springboot.thymeleafdemo.entity.Customer;


public interface CustomerService {
	
	public List<Customer> findAll();

	public Customer findById(int id);
	

	public void deleteById(int id);

	void save(Customer customer);

	public List<Customer> searchBy(String theFirstName, String theLastName);

	
}
