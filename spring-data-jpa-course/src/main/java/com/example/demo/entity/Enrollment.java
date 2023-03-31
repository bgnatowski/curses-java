package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Enrollment")
@Table(name = "enrollment")
public class Enrollment {

	@EmbeddedId
	private EnrollmentId id;

	@ManyToOne
	@MapsId("studentId") //this is Long studentId from EnrollmentId
	@JoinColumn(name = "student_id",
			foreignKey = @ForeignKey(name = "fk_enrollment_student_id"))
	private Student student;

	@ManyToOne
	@MapsId("courseId")
	@JoinColumn(name = "course_id",
			foreignKey = @ForeignKey(name = "fk_enrollment_course_id"))
	private Course course;

	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime createdAt;
}
