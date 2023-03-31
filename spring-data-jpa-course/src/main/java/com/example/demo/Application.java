package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Faker faker = Faker.instance();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@amigoscode.edu", firstName.toLowerCase(), lastName.toLowerCase());
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(16, 60));

			student.addBook(new Book(
					faker.book().title(),
					LocalDateTime.now().minusDays(faker.number().numberBetween(1, 20)))
			);
			student.addBook(new Book(
					faker.book().title(),
					LocalDateTime.now().minusDays(faker.number().numberBetween(1, 20)))
			);
			student.addBook(new Book(
					faker.book().title(),
					LocalDateTime.now().minusYears(faker.number().numberBetween(1, 20)))
			);

			StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
			student.setStudentIdCard(studentIdCard);

			student.addEnrollment(new Enrollment(
					new EnrollmentId(1l, 1l),
					student,
					new Course("Computer Science", "IT"),
					LocalDateTime.now().minusYears(4))
			);
			student.addEnrollment(new Enrollment(
					new EnrollmentId(1l, 2l),
					student,
					new Course("Amigoscode Spring Data JPA", "IT"),
					LocalDateTime.now()));
			student.addEnrollment(new Enrollment(
					new EnrollmentId(1l, 3l),
					student,
					new Course("Maths", "Maths"),
					LocalDateTime.now().minusDays(14))

			);

			studentRepository.save(student);

			studentRepository.findById(1L).ifPresent(s -> {
				System.out.println("fetch book lazy...");
				List<Book> books = student.getBooks();
				books.forEach(book -> System.out.println(s.getFirstName() + " borrowed " + book.getBookName()));
			});

//			studentIdCardRepository.findById(1L).ifPresent(System.out::println);
//			studentRepository.deleteById(1l);
		};
	}

	private static void paging(StudentRepository studentRepository) {
		PageRequest pageRequest = PageRequest.of(
				0,
				5,
				Sort.by("firstName").ascending());
		Page<Student> page = studentRepository.findAll(pageRequest);
		System.out.println(page);
	}

	private static void sorting(StudentRepository studentRepository) {
		Sort sort = Sort.by("firstName").ascending()
				.and(Sort.by("age").descending());
		studentRepository.findAll(sort)
				.forEach(student -> System.out.println(student.getFirstName() + " " + student.getAge()));
	}

	private static void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = Faker.instance();
		for (int i = 0; i < 20; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@amigoscode.edu", firstName.toLowerCase(), lastName.toLowerCase());
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(16, 60));
			studentRepository.save(student);
		}
	}
}
