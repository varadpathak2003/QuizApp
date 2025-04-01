package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.dao.QuestionDao;
import com.quizapp.dao.QuizDao;
import com.quizapp.entities.QuestionWrapper;
import com.quizapp.entities.Questions;
import com.quizapp.entities.Quiz;
import com.quizapp.entities.Response;



@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			Pageable pageable = PageRequest.of(0, numQ);
			List<Questions> questions = questionDao.findRandomQuestionsByCategory("Java", pageable);

			Quiz quiz=new Quiz();
			quiz.setCategory(category);
			quiz.setTitle(title);
			quiz.setNumQ(numQ);
			quiz.setQuestions(questions);
			return new ResponseEntity<>(quizDao.save(quiz).toString(),HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Cannot Create",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		try {
			Optional<Quiz> quiz=quizDao.findById(id);
			List<Questions> getFromDB=quiz.get().getQuestions();
			List<QuestionWrapper> questionsForUser=new ArrayList<>();
			for (Questions q:getFromDB) {
				QuestionWrapper qw=new QuestionWrapper();
				qw.setId(q.getId());
				qw.setOption1(q.getOption1());
				qw.setOption2(q.getOption2());
				qw.setOption3(q.getOption3());
				qw.setOption4(q.getOption4());
				qw.setQuestionTitle(q.getQuestionTitle());
				questionsForUser.add(qw);
			}
			return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
		}catch (Exception e) {
			
		}
		return null;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		try {
			Optional<Quiz> quiz=quizDao.findById(id);
			List <Questions> questions =quiz.get().getQuestions();
			int i=0;
			int correct=0;
			for (Response response: responses) {
				if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
					correct++;
				}
				i++;
			}
			
			return new ResponseEntity<>(correct,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
		
	}	
}
