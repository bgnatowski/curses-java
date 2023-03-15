package com.amigoscode.SpringDataMongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringDataMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("Poland", "Cracow", "31-542");
			final String email = "bgnatowski@gmail.com";
			Student student = new Student("Bartosz",
					"Gnatowski",
					email,
					Gender.MALE,
					address,
					List.of("Computer Science", "Science"),
					BigDecimal.TEN,
					LocalDateTime.now());

//			usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

			repository.findStudentByEmail(email).ifPresentOrElse(foundStudent -> {
				System.out.println(foundStudent + " already exist");
			}, () -> {
				System.out.println("Inserting student " + student);
				repository.insert(student);
			});
		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);
		if (students.size() > 1) {
			throw new IllegalStateException("found more than one students with email " + email);
		}
		if (students.isEmpty()) {
			System.out.println("Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println(student + " already exist");
		}
	}

}
