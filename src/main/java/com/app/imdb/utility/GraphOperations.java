package com.app.imdb.utility;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Component;

import com.app.imdb.model.RelatedActorsRow;

/**
 * This utility class is used for Graph operations required for operations for Bacon Number calculation.
 * Uses JGraphT API for Graph operations (org.jgrapht)
 */
@Component
public class GraphOperations {

	private Graph<String, DefaultEdge> actorGraph;
	
	public GraphOperations() {
		this.actorGraph = new SimpleGraph<>(DefaultEdge.class);
	}
	
	/**
	 * This method add a list of vertices to the graph
	 * For the bacon number implementation, vertices are actors.
	 * @param vertexList
	 */
	public void addToGraph(List<RelatedActorsRow> vertexList) {
		String actor1;
		String actor2;
		
		for (RelatedActorsRow actorRelationRow : vertexList) {
			actor1 = actorRelationRow.getActor1();
			actor2 = actorRelationRow.getActor2();
			if(!actorGraph.containsEdge(actor1, actor2)) {
				actorGraph.addVertex(actor1);
				actorGraph.addVertex(actor2);
				actorGraph.addEdge(actor1,actor2);
			}
		}
	}
	
	/**
	 * This method calculates the bacon number using the shortest path algorithm, DijkstraShortestPath algorithm
	 * DijkstraShortestPath algorithm is out of the box implementation from JGraphT API
	 * @param baconNconst : nconst value of Kevin Bacon
	 * @param artistNconst : nconst value of artist whose Bacon number to be calculated
	 * @return
	 */
	public int calculateBaconNumber(String baconNconst, String artistNconst) {
		int shortestPathLength;
		DijkstraShortestPath<String, DefaultEdge> shortestPath = new DijkstraShortestPath<>(actorGraph);
		shortestPathLength = shortestPath.getPath(baconNconst, artistNconst).getLength();
		return shortestPathLength;
	}
}
