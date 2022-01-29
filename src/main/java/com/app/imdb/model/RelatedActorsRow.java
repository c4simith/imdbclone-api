package com.app.imdb.model;

/**
 * Representation of Actor Relation. An actor is related to another one if they are part of the same title.
 */
public interface RelatedActorsRow {

	String getActor1();
	String getActor2();

}
