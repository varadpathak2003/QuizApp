package com.quizapp.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizapp.entities.Questions;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {
	
	 List<Questions> findByCategory(String category);

	 @Query("SELECT q FROM Questions q WHERE q.category = :category ORDER BY FUNCTION('RAND')")
	 List<Questions> findRandomQuestionsByCategory(@Param("category") String category, Pageable pageable);


}
