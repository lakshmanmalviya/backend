package com.example.quizapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthRequest {

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Please enter a valid email address.")
	@Size(min = 10, max = 100, message = "Email should have at least 10 characters and at most 100 characters")
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Please enter a valid email address.")
	private String email;

	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, max = 50, message = "Password should have at least 8 characters and max 50 ")
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", 
	message = "Password must be at least 8 characters long and max 50 and include at least one number, one uppercase letter, and one lowercase letter.")
	private String password;
}