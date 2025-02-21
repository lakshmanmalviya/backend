package com.example.quizapp.controller;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.dto.AuthResponse;
import com.example.quizapp.dto.LoginRequest;
import com.example.quizapp.dto.SignupRequest;
import com.example.quizapp.dto.UnifiedResponse;
import com.example.quizapp.service.AuthService;
import com.example.quizapp.util.ResponseBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<UnifiedResponse<AuthResponse>> register(@Valid @RequestBody SignupRequest registerUser) {
		System.out.println("Data of  the user is coming like " + registerUser.getName());
		return ResponseBuilder.buildResponse(HttpStatus.CREATED, authService.register(registerUser));
	}

	@PostMapping("/login")
	public ResponseEntity<UnifiedResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest authRequest) {
		return ResponseBuilder.buildOKResponse(authService.login(authRequest));
	}
}
