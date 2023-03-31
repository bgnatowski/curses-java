package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Book")
@Table(name = "book")
public class Book {
	@Id
	@SequenceGenerator(name = "book_id_sequence",sequenceName = "book_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
	private String bookName;

	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(
			name = "student_id",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "fk_student_book")
	)
	private Student student;

	public Book(String bookName, LocalDateTime createdAt) {
		this.bookName = bookName;
		this.createdAt = createdAt;
		this.student = student;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", bookName='" + bookName + '\'' +
				", createdAt=" + createdAt +
				", student=" + student +
				'}';
	}
}
