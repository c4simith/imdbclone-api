package com.app.imdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.imdb.constants.ApplicationConstants;
import com.app.imdb.model.RelatedActorsRow;
import com.app.imdb.model.TConstRow;
import com.app.imdb.repository.ArtistRepository;
import com.app.imdb.utility.GraphOperations;

/**
 * Service class for Artist Entity, Represents Actor,Director,Writer,Technician etc.
 * Service  layer Implements the business logic. Uses repository class for database operations.
 */
@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private GraphOperations graphOperations;
	
	/**
	 * This method calculates the bacon number of an artist
	 * @param artist : Name of artist
	 */
	public int calculateBaconNumber(String artist){
		
		/**
		 * This method is incomplete and need to be updated for optimization;
		 */
		
		int baconNumber;
		if(artist.equals(ApplicationConstants.ARTIST_KEVINBACON)) {
			baconNumber = 0;
			return baconNumber;
		}
		
		List<RelatedActorsRow> relatedActorsList;
		List<TConstRow> titlesList;
		long rowCount = artistRepository.findTconstCount();
		int i=0;
		long limit =ApplicationConstants.ROW_LIMIT;
		long offset;
		String tconst;
		while(rowCount>0) {
			offset = i * limit;
			i=i+1;
			rowCount=rowCount-limit;
			titlesList = artistRepository.findAllTitlesByLimit(limit,offset);
			
			for (TConstRow tConstRow : titlesList) {
				tconst = tConstRow.getTconst();
				relatedActorsList = artistRepository.findRelatedArtistsByTitle(tconst);
				graphOperations.addToGraph(relatedActorsList);
				relatedActorsList.clear();
			}
		}
		
		String artistNconst = artistRepository.findNconstByName(artist);
		String baconNconst = artistRepository.findNconstByName("Kevin Bacon");
		baconNumber = graphOperations.calculateBaconNumber(baconNconst, artistNconst);
		
		return baconNumber;
	}
}
