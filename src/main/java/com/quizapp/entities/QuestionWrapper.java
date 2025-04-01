package com.quizapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
	
	private int id;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	private String questionTitle;
}
