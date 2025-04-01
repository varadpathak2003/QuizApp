package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.dao.QuestionDao;
import com.quizapp.entities.Questions;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	
	public ResponseEntity< List<Questions>> getAllQuestions() {
		try {	
			return  new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public  ResponseEntity< List<Questions>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>( questionDao.findByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> addQuestion(Questions question) {
		try {
			return new ResponseEntity<>(questionDao.save(question).toString(),HttpStatus.CREATED);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
	}
}
