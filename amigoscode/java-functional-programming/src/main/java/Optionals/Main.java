package Optionals;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
//        Optional.ofNullable(null)
//                        .ifPresentOrElse(
//                                email -> System.out.println("Sending email to " + email),
//                                () -> System.out.println("Cannot send email"));

//        Optional<String> hello = Optional.of("Hello");
//        Optional<String> hello = Optional.ofNullable(null);
//        System.out.println(hello.isPresent());
//        System.out.println(hello.isEmpty());
//
//        hello.ifPresentOrElse(System.out::println, () -> System.out.println("world"));
        Person person = new Person("person", null);
//        String email = person.getEmail()
//                .map(String::toUpperCase)
//                .orElse("email not provided");
//        System.out.println(email);

        if(person.getEmail().isPresent()){
            String email = person.getEmail().get();
            System.out.println(email.toUpperCase(Locale.ROOT));
        }else{
            System.out.println("email not provided");
        }


    }

}
class Person{
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
