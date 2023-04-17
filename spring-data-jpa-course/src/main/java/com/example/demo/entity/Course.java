package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Course")
@Table(name = "course")
public class Course {
	@Id
	@SequenceGenerator(name = "course_id_sequence",sequenceName = "course_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", columnDefinition = "TEXT", nullable = false)
	private String name;

	@Column(name = "department", columnDefinition = "TEXT", nullable = false)
	private String department;

//	@ManyToMany(mappedBy = "courses") //List<Course> course <- this name from Student
//	private List<Student> students = new ArrayList<>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
			mappedBy = "course"
	)
	private List<Enrollment> enrollments = new ArrayList<>();

	public void addEnrollment(Enrollment enrollment){
		if(!enrollments.contains(enrollment)){
			enrollments.add(enrollment);
			enrollment.setCourse(this);
		}
	}

	public void removeEnrollment(Enrollment enrollment){
		if(enrollments.contains(enrollment)){
			enrollments.remove(enrollment);
			enrollment.setCourse(null);
		}
		String str = "dupa";
		String[] words = str.split(" ");
		Arrays.stream(words)
				.collect(Collectors.groupingBy(Course::getDistinctLetters)
				);
	}
	private static long getDistinctLetters(String word) {
		long count = word.chars()
				.filter(Character::isAlphabetic)
				.distinct()
				.count();

		return count;
	}

	public Course(String name, String department) {
		this.name = name;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", name='" + name + '\'' +
				", department='" + department + '\'' +
				'}';
	}
}
