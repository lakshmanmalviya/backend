package com.example.quizapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quizapp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Long countByCreatorId(Long id);

	Page<Question> findByQuizId(Long id, Pageable pageable);

	boolean existsById(Long id);

	Page<Question> findByCreatorId(Long creatorId, Pageable pageable);

	@Query("SELECT q FROM Question q WHERE q.creator.id = :creatorId AND q.createdAt BETWEEN :startDate AND :endDate")
	Page<Question> findByCreatorIdAndDateBetween(@Param("creatorId") Long creatorId,
			@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

	Page<Question> findByCreatorIdAndTextContainingIgnoreCaseOrQuestionTypeContainingIgnoreCase(Long creatorId,
			String text, String questionType, Pageable pageable);

}
