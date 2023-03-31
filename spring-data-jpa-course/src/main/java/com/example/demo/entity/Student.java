package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor


@Entity(name = "Student")
@Table(name = "student",
		uniqueConstraints = {
			@UniqueConstraint(name = "student_email_unique", columnNames = "email")
		})
public class Student {
	@Id
	@SequenceGenerator(name = "student_id_sequence",sequenceName = "student_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
	private String firstName;

	@Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
	private String lastName;

	@Column(name = "email", nullable = false, columnDefinition = "TEXT")
	private String email;

	@Column(name = "age", nullable = false)
	private Integer age;

	@OneToOne(mappedBy = "student",
			orphanRemoval = true,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
	) // Student is owning entity
	private StudentIdCard studentIdCard;

	@OneToMany(mappedBy = "student",
			orphanRemoval = true,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
	)
	private final List<Book> books = new ArrayList<>();

//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//	@JoinTable(
//			name = "enrollment",
//			joinColumns = @JoinColumn(
//					name = "student_id",
//					foreignKey = @ForeignKey(name = "fk_enrolment_student_id")
//			),
//			inverseJoinColumns = @JoinColumn(
//					name = "course_id",
//					foreignKey = @ForeignKey(name = "fk_enrolment_course_id")
//			)
//	)
//	private List<Course> courses = new ArrayList<>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
			mappedBy = "student"
	)
	private List<Enrollment> enrollments = new ArrayList<>();

	public void addEnrollment(Enrollment enrollment){
		if(!enrollments.contains(enrollment)){
			enrollments.add(enrollment);
			enrollment.setStudent(this);
		}
	}

	public void removeEnrollment(Enrollment enrollment){
		if(enrollments.contains(enrollment)){
			enrollments.remove(enrollment);
			enrollment.setStudent(null);
		}
	}

	public Student(String firstName, String lastName, String email, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

	public void addBook(Book book){
		if(!this.books.contains(book)) {
			this.books.add(book);
			book.setStudent(this);
		}
	}

	public void removeBook(Book book){
		if(this.books.contains(book)){
			this.books.remove(book);
			book.setStudent(null);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Student student = (Student) o;
		return getId() != null && Objects.equals(getId(), student.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", age=" + age +
				'}';
	}
}
