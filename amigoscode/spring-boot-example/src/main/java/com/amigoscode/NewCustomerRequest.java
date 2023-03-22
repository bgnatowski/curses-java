package com.amigoscode;

public record NewCustomerRequest(
		String name,
		String email,
		Integer age
) {

}
