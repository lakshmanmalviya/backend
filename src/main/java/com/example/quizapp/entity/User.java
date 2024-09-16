package com.example.quizapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.example.quizapp.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@Column(nullable = false)
	private boolean isLogout;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(name = "profile_pic", columnDefinition = "TEXT")
	private String profilePic;

	@Column(name = "education")
	private String education;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	private String bio;

	@OneToMany(mappedBy = "creator")
	@JsonBackReference
	private List<Category> categories;

	@OneToMany(mappedBy = "creator")
	@JsonBackReference
	private List<Quiz> quizzes;

	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Result> results;

	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Feedback> feedbacks;

	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Bookmark> bookmarks;

	@OneToMany(mappedBy = "creator")
	@JsonBackReference
	private List<Question> questions;

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
