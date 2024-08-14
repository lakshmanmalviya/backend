package com.example.quizapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterUser {

	@Size(min = 3, max = 100, message = "Name should have at least 3 characters and max 100")
	private String name;

	@Size(min = 3, max = 100, message = "Username should have at least 3 characters and max 100")
	private String username;
	
	@Size(min = 6, message = "Password should have at least 6 characters")
	private String password;
	
	@NotBlank(message = "Role is required")
	private String role;
	
	private String profilePic;
	
	@Size(max = 250, message = "Bio should not exceed 250 characters")
	private String bio;
}
