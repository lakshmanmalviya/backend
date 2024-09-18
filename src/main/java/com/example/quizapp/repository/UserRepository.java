package com.example.quizapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizapp.entity.*;
import com.example.quizapp.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<User> findById(Long id);

	Page<User> findByRole(Role role, Pageable pageable);

	List<User> findAllByRole(Role role);
}