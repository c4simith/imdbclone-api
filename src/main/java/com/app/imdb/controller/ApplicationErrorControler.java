package com.app.imdb.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.imdb.constants.ApplicationConstants;

import ch.qos.logback.classic.Logger;

@Controller
public class ApplicationErrorControler implements ErrorController{

	private static final Logger logger = (Logger) LoggerFactory.getLogger(ApplicationErrorControler.class);
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request,Model model) {
		
		logger.error("Application error occured");
		String errorMessage = ApplicationConstants.MESSAGE_ERROR_GENERIC;
	    Object statusCodeObject = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (statusCodeObject != null) {
	        Integer statusCode = Integer.valueOf(statusCodeObject.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	errorMessage = ApplicationConstants.MESSAGE_ERROR_PAGENOTFOUND;
	        	logger.error(errorMessage);
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	errorMessage = ApplicationConstants.MESSAGE_ERROR_INTERNALSERVER;
	        	logger.error(errorMessage);
	        }else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
	        	errorMessage = ApplicationConstants.MESSAGE_ERROR_TITLENOTFOUND;
	        	logger.error(errorMessage);
	        }
	    }
	    model.addAttribute("errorMessage", errorMessage);
	    return "error";
	}
}
