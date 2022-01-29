package com.app.imdb.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.imdb.service.TitleService;

import ch.qos.logback.classic.Logger;

/**
 * 
 * Controller class for operations on Title Entity
 *
 */
@Controller
public class TitleController{
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TitleController.class);
	
	@Autowired
	private TitleService titleService;
	
	/**
	 * Provides the related information of Title including cast and crew details.
	 * URL Format : http://localhost:8080/title/movie?name=Titanic
	 * @param titleType : Type of title, eg movie/tvshow
	 * @param titleName : Name of title, eg:Titanic
	 */
	@GetMapping("/title/{titleType}")
	public String getTitleDetails(@PathVariable String titleType, @RequestParam(name="name") String titleName, Model model) {
		logger.info("Getting Title details");
		model.addAttribute("titleDetails", titleService.getTitleDetails(titleType, titleName));
		logger.info("Title details fetched");
		return "titledetails";
	} 
	
	/**
	 * Provides the list of top rated titles based on genre
	 * URL Format : http://localhost:8080/title/toprated/movie?genre=Horror&count=10
	 * @param titleType : type of title, eg movie/tvshow
	 * @param genre : Genre of title, eg:Horror
	 * @param count : Count of no of top titles required, eg: 10 returns top 10 titles
	 */
	@GetMapping("/title/toprated/{titleType}")
	public String getTopRatedTitles(@PathVariable String titleType, @RequestParam(name="genre") String genre, @RequestParam(name="count") int count, Model model) {
		logger.info("Getting Top Rated titles");
		model.addAttribute("toprated",titleService.getTopRatedTitles(titleType, genre, count)); 
		logger.info("Fetched Top Rated titles");
		return "toprated";
	}
}
