package com.amigoscode;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteById(Integer id) {
		customerRepository.deleteById(id);
	}

	public Customer findById(Integer id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Nie ma takiego klienta"));
	}
}
