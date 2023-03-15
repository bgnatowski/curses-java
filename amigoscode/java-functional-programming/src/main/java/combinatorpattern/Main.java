package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;
import static combinatorpattern.CustomerRegistrationValidator.ValidationResult.SUCCESS;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(
                "Alice",
                "alice@gmail.com",
                "+012345679",
                LocalDate.of(2004, 1, 1));

        //using combinator pattern

        ValidationResult validationResult = isEmailValid()
                .and(isPhoneNumberIsValid())
                .and(isAnAdult())
                .apply(customer);

//        System.out.println(validationResult);

        if(validationResult != SUCCESS)
            throw new IllegalStateException(validationResult.name());
    }
}
