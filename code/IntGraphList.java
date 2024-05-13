
/**
 * Sunith Francis Areng
 * MSCI 240: Final Project
 * 10th December 2021
 * 
 * This class represents a graph stored using adjacency lists for each vertex.
 * This program has several classes that are called and used by the GraphMain method to create
 * a graph and perform operations on its properties. The program has been obtained from Codio Week 12
 * and modified to suit the solution to the challenge.
 * 
 * @author Mark Hancock
 * @author Sunith Areng
 * Input: This program takes no input
 * Output: This program has no output
 */

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntGraphList extends IntGraph {

	/**
	 * A map of adjacency lists for each vertex.
	 */
	private Map<Integer, LinkedList<Integer>> adjacencyList;

	/**
	 * Creates a new empty graph.
	 */
	public IntGraphList() {
		adjacencyList = new HashMap<Integer, LinkedList<Integer>>();
	}

	/**
	 * Creates a new graph with vertices numbered 1 to numVertices.
	 * 
	 * @param numVertices the number of vertices to add to this graph on creation
	 */
	public IntGraphList(int numVertices) {
		this();
		for (int i = 1; i <= numVertices; i++) {
			addVertex(i);
		}

	}

	/**
	 * This method counts the degrees of each of the nodes present in the graph
	 * 
	 * @return a sorted ArrayList containing degree of all nodes
	 */
	public ArrayList<Integer> degreeSet() {
		ArrayList<Integer> degreeCheck = new ArrayList<Integer>();
		for (int value : adjacencyList.keySet()) {
			degreeCheck.add(getDegree(value));
		}
		Collections.sort(degreeCheck);
		return degreeCheck;
	}

	/**
	 * Adds a vertex with a value to this graph.
	 * 
	 * @param value the integer value of this vertex
	 * @throws IllegalArgumentException if this vertex value has been added before
	 */
	@Override
	public void addVertex(int value) {
		if (hasVertex(value)) {
			throw new IllegalArgumentException(value + " is already in the graph");
		}
		adjacencyList.put(value, new LinkedList<Integer>());
	}

	/**
	 * Adds an edge from first to second to this graph.
	 * 
	 * @param first  the first vertex in the edge
	 * @param second the second vertex in the edge
	 * 
	 * @throws IllegalArgumentException if either first or second are not vertices
	 *                                  in the graph.
	 */
	@Override
	public void addEdge(int first, int second) {
		checkVertex(first);
		checkVertex(second);

		// preventing self loop edge
		if (first != second && !adjacencyList.get(first).contains(second)
				&& !adjacencyList.get(second).contains(first)) {
			adjacencyList.get(first).add(second);
			adjacencyList.get(second).add(first);
		}
	}

	/**
	 * This method checks if an edge exists between two nodes
	 * 
	 * @param v1 first node that should have one end of the node
	 * @param v2 second node that should have the second end of the node
	 * @return
	 */
	public boolean checkAdjacent(int v1, int v2) {
		checkVertex(v1);
		checkVertex(v2);
		if (adjacencyList.get(v1).contains(v2) && adjacencyList.get(v2).contains(v1)) {
			return true;
		}
		return false;
	}

	/**
	 * This method removes an edge between two nodes
	 * 
	 * @param first  node which is connected to one end of the edge
	 * @param second node which is connected to the second end of the edge
	 */
	public void removeEdge(int first, int second) {
		checkVertex(first);
		checkVertex(second);
		if (first != second && adjacencyList.get(first).contains(second) && adjacencyList.get(second).contains(first)) {
			int x1 = adjacencyList.get(first).indexOf(second);
			int x2 = adjacencyList.get(second).indexOf(first);
			adjacencyList.get(first).remove(x1);
			adjacencyList.get(second).remove(x2);
		}
	}

	/**
	 * This method returns the degree of a node
	 * 
	 * @param value is the vertex whose degree we wish to obtain
	 * @return integer value of the degree of the node
	 */
	public int getDegree(int value) {
		checkVertex(value);
		return adjacencyList.get(value).size();
	}

	/**
	 * This method returns the total number of edges in the graph
	 * 
	 * @return the integer value of the total number of edges
	 */
	public int getNumEdges() {
		int answer = 0;
		for (int value : adjacencyList.keySet()) {
			answer = answer + getDegree(value);
		}
		return answer / 2;
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the number of vertices in this graph.
	 */
	@Override
	public int getNumVertices() {
		return adjacencyList.size();
	}

	/**
	 * Returns an adjacency list for vertex v.
	 * 
	 * @param v the vertex for which to retrieve the adjacency list
	 * @return an adjacency list for vertex v.
	 */
	@Override
	public List<Integer> getAdjacencyList(int v) {
		checkVertex(v);
		return Collections.unmodifiableList(adjacencyList.get(v));
	}

	/**
	 * Returns the set of vertices in this graph.
	 * 
	 * @return the set of vertices in this graph.
	 */
	@Override
	public Set<Integer> getVertices() {
		return Collections.unmodifiableSet(adjacencyList.keySet());
	}

	/**
	 * Checks if this vertex is in the graph and returns true if it is, false if it
	 * is not.
	 * 
	 * @param v the vertex to check
	 * @return true if vertex v is in the graph, false otherwise.
	 */
	@Override
	public boolean hasVertex(int v) {
		return adjacencyList.containsKey(v);
	}

	/**
	 * Helper function to check if this vertex is in the graph and throw an error if
	 * not.
	 * 
	 * @param v the vertex to check.
	 */
	private void checkVertex(int v) {
		if (!hasVertex(v)) {
			throw new IllegalArgumentException("vertex " + v + " is not in the graph");
		}
	}

	/**
	 * This method conducts a depth First Search based on the input node (source/
	 * reference node) This is used to check if all the nodes in the list are
	 * connected
	 * 
	 * @param value the source node with which we wish to check the connectivity
	 * @return true if all the vertices in the graph are connected false if even one
	 *         vertex is not connected
	 */
	public boolean depthFirstSearch(int value) {
		HashSet<Integer> visited = new HashSet<>();
		Map<Integer, Integer> parents = new HashMap<>();
		boolean answer = true;

		checkVertex(value);
		// looks if the graph is still connected
		dfsVisit(value, visited, parents);
		for (int val : getVertices()) {
			if (visited.contains(val) == false) {
				answer = false;
			}
		}
		return answer;
	}

	/**
	 * The helper method updates the parameters from the parent method this method
	 * iterates through the nodes and checks if they are connected
	 * 
	 * @param u       the node that is being checked
	 * @param visited keeps track of the visited nodes
	 * @param parents to track the node that is first visited before finding the new
	 *                vertex
	 */
	private void dfsVisit(int u, Set<Integer> visited, Map<Integer, Integer> parents) {
		visited.add(u);
		for (int v : adjacencyList.get(u)) {
			if (!visited.contains(v)) {
				parents.put(v, u);
				dfsVisit(v, visited, parents);
			}
		}
	}

	/**
	 * This method conducts graph operation on the input list where one existing
	 * edge is removed and the removed edge is placed between two other nodes while
	 * maintaining the degrees of the nodes of the graph same are the original graph
	 * 
	 * @param stream takes in PrintStream to print output in organised manner
	 */
	public int GraphOperation(PrintStream stream) {

		int answer = 0;
		
		// ArrayList stores degrees of all vertices in the original graph
		// New ArrayList is needed as the degreeSet of the Map varies during the
		// operation. degreeSet() is always sorted in the ascending order
		ArrayList<Integer> degreeCheck = new ArrayList<Integer>(degreeSet());
		
		// This map keeps track of the nodes that should not be removed to preserve connectivity
		HashMap<Integer, LinkedList<Integer>> illegalRemoves = new HashMap<>();
		
		// keeps track of the examined vertices to avoid double count
		Set<Integer> checked = new HashSet<Integer>();

		for (int vertex : getVertices()) {
			List<Integer> adListofV1 = adjacencyList.get(vertex);

			// parent vertex is added to the checked set as their adjacent vertices are
			// tested
			checked.add(vertex);

			// we iterate over the adjacency list of this vertex
			// This is done by removing one edge and creating a new graph that satisfies the
			// conditions
			for (int i = 0; i < adListofV1.size(); i++) {
				// gets the nodes that vertex in question is connected to in order
				int vertex2 = adListofV1.get(i);

				// this statement checks if vertex has been examined before continuing further
				if (!checked.contains(vertex2)) {

					// gets the index of vertex 2 from the adjacency list of vertex 1
					int indexList2 = adjacencyList.get(vertex2).indexOf(vertex);

					// Removing the edge between two nodes
					removeEdge(vertex, vertex2);

					// Checks if all the nodes in the graph are connected to each other
					// through source node 1
					if (depthFirstSearch(1)) {
						// The checks for different combinations of edges that satisfies the requirements
						answer = combinatorics(stream,degreeCheck,answer,vertex, vertex2);
					} else {
						// This is just to keep track of edge removals that are invalid
						if (!illegalRemoves.containsKey(vertex)) {
							illegalRemoves.put(vertex, new LinkedList<Integer>());
							illegalRemoves.get(vertex).add(vertex2);
						}else {
							illegalRemoves.get(vertex).add(vertex2);
						}
					}
					
					// nodes are added back to their adjacency lists at their original indices
					// to preserve the original graph
					adListofV1.add(i, vertex2);
					adjacencyList.get(vertex2).add(indexList2, vertex);
				}
			}
		}
		stream.println("The total number of possible combinations are: " + answer);

		// Prints the invalid edges
		printIllegalRemoves(stream, illegalRemoves);

		return answer;
	}
	
	/**
	 * This method checks for different combinations that can satisfy the requirements 
	 * by placing the removed edge between two previously unconnected nodes.
	 * While also making sure that answer is not incremented twice, does not consider self loops,
	 * removed edge should not be placed back to count for the answer
	 * @param stream the printstream that prints out the statements
	 * @param degreeCheck the set of degrees of the original graph
	 * @param answer final answer that is incremented for each graph that holds true to the conditions
	 * @param vertex the parent node under check
	 * @param vertex2 the adjacent node that is connected to the parent
	 * @return
	 */
	private int combinatorics(PrintStream stream, ArrayList<Integer> degreeCheck, int answer, int vertex, int vertex2) {
		for (int j = 1; j < getNumVertices(); j++) {
			for (int t = j + 1; t <= getNumVertices(); t++) {

				// This statement avoids counting the removed line and checks if
				// an edge exists between the two given nodes
				if (((j != vertex || t != vertex2) && (j != vertex2 || t != vertex))
						&& !checkAdjacent(t, j)) {
					// new edge is added between two unconnected nodes
					addEdge(j, t);
					if (degreeSet().equals(degreeCheck)) {
						answer++;
						stream.print("Remove the edge " + vertex + " - " + vertex2);
						stream.print(" and put it between " + j + " - " + t);
						stream.println();
					}
					// newly added edge is removed after the tests
					removeEdge(j, t);
				}
			}
		}
		return answer;
	}
	
	
	/**
	 * This method breaks down the GraphOperation method into a smaller chunk
	 * @param stream takes the same printstream as the GraphOperation method
	 * @param illegalRemoves this map is read and its values are printed
	 */
	private void printIllegalRemoves(PrintStream stream, Map<Integer, LinkedList<Integer>> illegalRemoves) {
		if (!illegalRemoves.isEmpty()) {
			stream.println();
			for (int x : illegalRemoves.keySet()) {
				for (int i = 0; i < illegalRemoves.get(x).size(); i++) {
					stream.println("The removing edge " + x + " - " + illegalRemoves.get(x).get(i) + " is invalid!");
				}
			}
			String reason = "The graph does not remain connected when this edge is removed.";
			stream.println("Reason: " + reason);
		}
	}
}