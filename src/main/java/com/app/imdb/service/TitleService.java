package com.app.imdb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.imdb.exception.TitleNotFoundException;
import com.app.imdb.model.ArtistFromTitleRow;
import com.app.imdb.model.Title;
import com.app.imdb.model.TopRatedTitleRow;
import com.app.imdb.repository.TitleRepository;

/**
 * Service class for Title. Service layer implements the business logic.
 * Uses repository object for database operations.
 */
@Service
public class TitleService {

	@Autowired
	private TitleRepository titleRepository;
	
	/**
	 * This method get the related information of Title including cast and crew details.
	 * Returns the list of map objects with Title and Artist details.
	 * Artist refers to the representation of Roles like Actor/Director/Writer/Technician etc.
	 * @param titleType : Type of title, eg movie/tvshow
	 * @param titleName : Name of title, eg:Titanic
	 */
	public Map<Title,List<ArtistFromTitleRow>> getTitleDetails(String titleType, String titleName){
		Map<Title,List<ArtistFromTitleRow>> mapOfTitles = new HashMap<>();
		List<ArtistFromTitleRow> artistList;
		
		List<Title> listTitle = titleRepository.findTitlesByTypeAndName(titleType,titleName);
		if(listTitle.isEmpty()) {
			throw new TitleNotFoundException("No matching title found");
		}
		for (Title title : listTitle) {
			String tconst = title.getTconst();
			artistList = titleRepository.findArtistDetails(tconst);
			mapOfTitles.put(title, artistList);
		}
		return mapOfTitles;
	}
	
	/**
	 * This method get the list of top rated titles
	 * @param titleType : type of title, eg movie/tvshow
	 * @param genre : Genre of title, eg:Horror
	 * @param count : Count of no of top titles required, eg: 10 returns top 10 titles
	 */
	public List<TopRatedTitleRow> getTopRatedTitles(String titleType, String genre, int count){
		return titleRepository.findTopRatedTitles(titleType, genre, count);
	}
}
