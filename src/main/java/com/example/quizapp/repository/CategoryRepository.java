package com.example.quizapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByCreatorId(Long id);

	Long countByCreatorId(Long id);

	Page<Category> findByCreatorId(Long id, Pageable pageable);
}
