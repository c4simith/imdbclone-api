package com.app.imdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.imdb.model.ArtistFromTitleRow;
import com.app.imdb.model.Title;
import com.app.imdb.model.TopRatedTitleRow;

public interface TitleRepository extends JpaRepository<Title, String> {

	/**
	 * Fetch the details of Title based on Primary Title or Original Title
	 * @param titleType : Type of title, eg:movie/tvshow
	 * @param title : Name of title, either Primary Title or Original Title
	 */
	@Query(name = "Title.titlesByTypeAndNameQuery", nativeQuery = true)
	List<Title> findTitlesByTypeAndName(String titleType, String title);
	
	
	/**
	 * Fetch the Artist details who were part of the title.
	 * Artist refers to the representation of Roles like Actor/Director/Writer/Technician etc.
	 * @param tconst : Unique ID of Title
	 */
	@Query(name = "Title.artistDetailsQuery", nativeQuery = true)
	List<ArtistFromTitleRow> findArtistDetails(String tconst);
	
	
	/**
	 * Fetch the details of Top rated title
	 * @param titleType : type of title, eg movie/tvshow
	 * @param genre : Genre of title, eg:Horror
	 * @param count : Count of no of top titles required, eg: 10 returns top 10 titles
	 */	
	@Query(name="Title.topRatedTitleQuery", nativeQuery = true)
	List<TopRatedTitleRow> findTopRatedTitles(String titleType, String genre, int count);
}