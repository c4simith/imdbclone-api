package com.app.imdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.imdb.model.RelatedActorsRow;
import com.app.imdb.model.TConstRow;
import com.app.imdb.model.Title;

public interface ArtistRepository extends JpaRepository<Title, String> {

	/**
	 * Fetch the count of titles; Required to set offset and limit for partial database update
	 */
	@Query(name="Artist.tconstCountQuery", nativeQuery = true)
	long findTconstCount();
	
	/**
	 * Fetch the title details.
	 * @param limit : No of rows per operation
	 * @param offset : Offset value to skip the previous rows
	 */
	@Query(name="Artist.allTitlesByLimitQuery", nativeQuery = true)
	List<TConstRow> findAllTitlesByLimit(long limit, long offset);
	
	/**
	 * Fetch the details of related artist if they were part of the same title
	 * @param title : tconst id of title
	 */
	@Query(name="Artist.relatedArtistsByTitleQuery", nativeQuery = true)
	List<RelatedActorsRow> findRelatedArtistsByTitle(String tconst);
	
	/**
	 * Fetch the list Titles in which artist is part of.
	 * @param artist : artist name. Should be actor or actress
	 */
	@Query(name="Artist.nconstByNameQuery", nativeQuery = true)
	String findNconstByName(String artist);
	
}
