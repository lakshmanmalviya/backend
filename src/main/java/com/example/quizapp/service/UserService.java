package com.example.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizapp.dto.UpdateUserRequest;
import com.example.quizapp.entity.MyUser;
import com.example.quizapp.exception.ResourceNotFoundException;
import com.example.quizapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public MyUser getUser(Long id) {
		try {
			MyUser user = userRepository.findByUserId(id);
			return user;
		} catch (Exception e) {
			throw new ResourceNotFoundException("User Not Found" + e.getMessage());
		}
	}

	public MyUser updateUserById(Long id, UpdateUserRequest request) {
		try {
			MyUser user = userRepository.findByUserId(id);
			user.setBio(request.getBio());
			user.setName(request.getName());
			user.setPassword(request.getPassword());
			user = userRepository.save(user);
			return user;
		} catch (Exception e) {
			throw new ResourceNotFoundException("User Not Found" + e.getMessage());
		}
	}

}
