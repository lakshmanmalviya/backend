package com.example.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.dto.EducatorProfileDataResponse;
import com.example.quizapp.dto.PageResponse;
import com.example.quizapp.dto.UnifiedResponse;
import com.example.quizapp.dto.UpdateUserRequest;
import com.example.quizapp.entity.User;
import com.example.quizapp.enums.Role;
import com.example.quizapp.service.UserService;
import com.example.quizapp.util.CommonHelper;
import com.example.quizapp.util.ResponseBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	CommonHelper commonHelper;

	@GetMapping("/currentUser")
	public ResponseEntity<UnifiedResponse<User>> getUserInformation() {
		return ResponseBuilder.buildOKResponse(userService.getUserInformation());
	}

	@GetMapping("/educatorProfileData")
	public ResponseEntity<UnifiedResponse<EducatorProfileDataResponse>> getEducatorProfileInformation() {
		return ResponseBuilder.buildOKResponse(userService.getEducatorProfileInformation());
	}

	@PutMapping("/update")
	public ResponseEntity<UnifiedResponse<User>> updateUser(@Valid @RequestBody UpdateUserRequest request) {
		return ResponseBuilder.buildOKResponse(userService.updateUser(request));
	}

	@PutMapping("/logout")
	public ResponseEntity<UnifiedResponse<Void>> doUserLogout() {
		return ResponseBuilder.buildOKResponse(userService.logout());
	}

	@GetMapping("/filters")
	public ResponseEntity<UnifiedResponse<PageResponse<User>>> findUsersByFilters(
			@RequestParam(required = false) String query, @RequestParam(required = false) String sort,
			@RequestParam(required = false) Role role, @RequestParam(required = false) String start,
			@RequestParam(required = false) String end, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = Integer.MAX_VALUE + "") int size) {
		return ResponseBuilder.buildOKResponse(
				userService.findUsersByFilters(role,query, start, end, sort, commonHelper.makePageReq(page, size)));
	}
}
