package com.app.imdb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

import com.app.imdb.model.ArtistFromTitleRow;
import com.app.imdb.model.Title;
import com.app.imdb.model.TopRatedTitleRow;
import com.app.imdb.repository.TitleRepository;
import com.app.imdb.service.TitleService;

@SpringBootTest
@WebAppConfiguration
class TitleControllerTest {
	
	@Autowired
	private TitleService titleService;
	
	@MockBean
	private TitleRepository titleRepository;
	
	@Test
	void testGetTitleDetails() {
		
		Title title = new Title();
		title.setTconst("tconst");
		title.setPrimaryTitle("Titanic");
		
		ArtistFromTitleRow artistRow = new ArtistFromTitleRow() {
			public String getNconst() {
				return "tconst";
			}
			public String getPrimaryName() {
				return "name";
			}
			public String getCategory() {
				return "category";
			}
		};
		
		List<ArtistFromTitleRow> artistList = new ArrayList<ArtistFromTitleRow>();
		artistList.add(artistRow);
		when(titleRepository.findArtistDetails("tconst")).thenReturn(artistList);
		List<Title> titleList = new ArrayList<Title>();
		titleList.add(title);
		when(titleRepository.findTitlesByTypeAndName("type", "Titanic")).thenReturn(titleList);
		
		Map<Title,List<ArtistFromTitleRow>> mapTitles = titleService.getTitleDetails("type", "Titanic");
		assertEquals(1,mapTitles.size());
	}

	@Test
	void testGetTopRatedTitles() {
		
		List<TopRatedTitleRow> listTopRated= new ArrayList<TopRatedTitleRow>();
		TopRatedTitleRow topRatedTitleRow = new TopRatedTitleRow() {
			public String getPrimaryTitle() {
				return "title";
			}
			public Double getAverageRating() {
				return 9.5;
			}
		};
		listTopRated.add(topRatedTitleRow);
		when(titleRepository.findTopRatedTitles("type", "genre", 1)).thenReturn(listTopRated);
		
		List<TopRatedTitleRow> listTopRatedActual = titleService.getTopRatedTitles("type", "genre", 1);
		
		assertEquals(listTopRated.size(),listTopRatedActual.size());
		
	}
}
