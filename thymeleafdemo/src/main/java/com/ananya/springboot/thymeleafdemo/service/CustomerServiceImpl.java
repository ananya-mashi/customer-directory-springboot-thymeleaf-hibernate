package com.ananya.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ananya.springboot.thymeleafdemo.dao.CustomerRepository;
import com.ananya.springboot.thymeleafdemo.entity.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerrepo;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository thecustomerrepo){
	customerrepo=thecustomerrepo;
	}
	@Override
	//remove @transactional  ,done by jpa repo
	public List<Customer> findAll() {
	return customerrepo.findAll();
	}
	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> result = customerrepo.findById(id);
		Customer c=null;
		if(result.isPresent()){
			c=result.get();
		}
		
		else{
			throw new RuntimeException("Did not found customerid  "+id);
		}
		return c;
	}

	
	@Override
	public void deleteById(int id) {
		customerrepo.deleteById(id);
	}
	@Override
	public void save(Customer customer) {
		customerrepo.save(customer);
	}
	@Override
	public List<Customer> searchBy(String theFirstName, String theLastName) {

		return customerrepo.findByFirstNameContainsAndLastNameContainsAllIgnoreCase(
						theFirstName, theLastName);	
	}


}
