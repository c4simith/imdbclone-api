package com.app.imdb.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

import com.app.imdb.model.RelatedActorsRow;
import com.app.imdb.model.TConstRow;
import com.app.imdb.repository.ArtistRepository;
import com.app.imdb.service.ArtistService;
import com.app.imdb.utility.GraphOperations;

@SpringBootTest
@WebAppConfiguration
class ArtistControllerTest {
	
	@Autowired
	private ArtistService artistService;
	
	@MockBean
	private GraphOperations graphOperations;
	
	@MockBean
	private ArtistRepository artistRepository;
	
	@Test
	void testCalculateBaconNumber() {
		
		String baconNconst = "Bconst";
		String artistNconst = "Aconst";
		List<TConstRow> titlesList = new ArrayList<>();
		TConstRow tConstRow = new TConstRow() {
			public String getTconst() {
				return "tconst";
			}
			
		};
		titlesList.add(tConstRow);
		
		List<RelatedActorsRow> relatedActorsList = new ArrayList<>();
		RelatedActorsRow relatedActorsRow = new RelatedActorsRow() {

			public String getActor1() {
				return "Actor1";
			}

			public String getActor2() {
				return "Actor2";
			}
			
		};
		relatedActorsList.add(relatedActorsRow);
		
		when(artistRepository.findTconstCount()).thenReturn(100000L);
		when(artistRepository.findAllTitlesByLimit(100000L,0L)).thenReturn(titlesList);
		when(artistRepository.findAllTitlesByLimit(100000L,100000L)).thenReturn(titlesList);
		when(artistRepository.findRelatedArtistsByTitle("tconst")).thenReturn(relatedActorsList);
		when(artistRepository.findNconstByName("Kevin")).thenReturn("Bconst");
		when(artistRepository.findNconstByName("Artist")).thenReturn("Aconst");
		when(graphOperations.calculateBaconNumber(baconNconst, artistNconst)).thenReturn(1);
		
		int baconNummber = artistService.calculateBaconNumber("Artist");
		assertTrue(baconNummber>=0);
		
	}
}
