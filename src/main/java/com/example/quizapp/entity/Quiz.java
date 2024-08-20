package com.example.quizapp.entity;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private Long timeLimit;
	private Boolean randomizeQuestions;
	private Long attemptedTimes;
	private Boolean isActive;

	@ManyToOne()
	@JoinColumn(name = "categroy_id", nullable = false)
	@JsonBackReference
	private Category category;

	@OneToMany(mappedBy = "quiz")
	@JsonBackReference
	private List<Question> questions;

	@OneToMany(mappedBy = "quiz")
	@JsonBackReference
	private List<QuizAttempt> quizzesAttempted;

	@OneToMany(mappedBy = "quiz")
	@JsonBackReference
	private List<Bookmark> bookmarks;

	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "creator_id", nullable = false)
	private MyUser creator;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
