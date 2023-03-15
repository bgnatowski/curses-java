package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            LocalDate studentMiriamBD = LocalDate.of(2000, Month.JANUARY, 5);
            Student studentMiriam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    studentMiriamBD
            );

            LocalDate studentAlexBD = LocalDate.of(2001, Month.MARCH, 16);

            Student studentAlex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    studentAlexBD
            );

            repository.saveAll(List.of(studentMiriam, studentAlex));
        };
    }

}
