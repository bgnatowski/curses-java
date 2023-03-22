package com.amigoscode;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping()
	public List<Customer> getCustomer(){
		return customerService.findAll();
	}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerService.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerService.deleteById(id);
	}

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id,
							   @RequestBody NewCustomerRequest request){
		Customer customer = customerService.findById(id);
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerService.save(customer);
	}
}
