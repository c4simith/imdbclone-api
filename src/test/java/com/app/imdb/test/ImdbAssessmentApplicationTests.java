package com.app.imdb.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.imdb.controller.TitleController;

@SpringBootTest
class ImdbAssessmentApplicationTests {

	@Autowired
	private TitleController titleController;
	
	@Test
	void contextLoads() {
		assertNotNull(titleController);
	}

}
