package com.app.imdb.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.imdb.service.ArtistService;

import ch.qos.logback.classic.Logger;

/**
 * 
 * Controller class for operations on Artist Entity
 *
 */
@Controller
public class ArtistController{
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ArtistController.class);
	
	@Autowired
	private ArtistService artistService;
	
	/**
	 * Calculate the Bacon Number of an artist
	 * URL Format : http://localhost:8080/artist/baconnumber?name=artistname
	 * @param artist : artist name, should be an actor or actress
	 */
	@GetMapping("/artist/baconnumber")
	public String calculateBaconNumber(@RequestParam(name="name") String artist, Model model) {
		logger.info("Calcualating Bacon Number of the artist");
		model.addAttribute("baconnumber",artistService.calculateBaconNumber(artist)); 
		logger.info("Calculated TBacon Number of the artist");
		return "baconnumber";
	}
}
