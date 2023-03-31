package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode // embeddable have to has equals and hashcode

@Embeddable //when embeddable must implements serializable
public class EnrollmentId implements Serializable {

	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "course_id")
	private Long courseId;
}
