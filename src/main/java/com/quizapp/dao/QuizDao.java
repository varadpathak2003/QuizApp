package com.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.entities.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
