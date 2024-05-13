/**
 * Sunith Francis Areng
 * MSCI 240: Final Project
 * 10th December 2021
 * 
 * This test program contains 36 unit tests that tests different instances where the code 
 * should work and extreme cases where the code should not work throwing exception
 * 
 * Input: This program takes no input
 * Output: This program has no output
 */


import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIntGraphList {
	
	// Checks if an empty map is created
	@Test
	public void consTestEmpty() {
		IntGraphList list = new IntGraphList();
		assertEquals(0, list.getNumVertices());
		assertEquals(0, list.getNumEdges());
	}

	// Checks if the number of vertices created is the same as specified
	@Test
	public void consTestCheckVerticeNumber() {
		IntGraphList list = new IntGraphList(4);
		assertEquals(4, list.getNumVertices());
	}

	// Checks if the number of edges is 0 when no edge has been added
	@Test
	public void consTestCheckEdgeNumber() {
		IntGraphList list = new IntGraphList(4);
		assertEquals(0, list.getNumEdges());
	}

	// checks if the correct number of edges are added
	@Test
	public void addEdgeTest() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		assertEquals(1, list.getNumEdges());
	}

	// Checks if a self loop is prevented
	@Test
	public void addLoopEdge() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 1);
		assertEquals(0, list.getNumEdges());
	}

	// Checks if a repeated edge between two nodes is prevented
	@Test
	public void addRepeatEdge() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		list.addEdge(4, 1);
		list.addEdge(4, 1);
		list.addEdge(4, 1);
		assertEquals(1, list.getNumEdges());
	}

	// checks if an edge can be added to an empty map
	@Test(expected = IllegalArgumentException.class)
	public void AddingEdgeWithNoVerticesInMap() {
		IntGraphList list = new IntGraphList();
		list.addEdge(4, 1);
	}
	
	// checks if an edge can be added to a node that does not exist in the map
	@Test(expected = IllegalArgumentException.class)
	public void AddingEdgeWithNoVertex() {
		IntGraphList list = new IntGraphList(2);
		list.addEdge(4, 1);
	}

	// adding vertex manually using an instance method
	@Test
	public void AddingVertexManually() {
		IntGraphList list = new IntGraphList();
		list.addVertex(1);
		assertEquals(1, list.getNumVertices());
	}

	// adding few vertices manually using an instance method
	@Test
	public void AddingVertexManually2() {
		IntGraphList list = new IntGraphList();
		list.addVertex(1);
		list.addVertex(2);
		list.addVertex(3);
		list.addVertex(4);
		assertEquals(4, list.getNumVertices());
	}

	// adding a large vertex number
	@Test
	public void AddingVertexManually3() {
		IntGraphList list = new IntGraphList();
		list.addVertex(1);
		list.addVertex(2);
		list.addVertex(3);
		list.addVertex(40);
		assertEquals(4, list.getNumVertices());
	}

	// adding same vertex twice
	@Test(expected = IllegalArgumentException.class)
	public void AddingVertexManuallyError() {
		IntGraphList list = new IntGraphList(1);
		list.addVertex(1);
	}

	// checking adjacency between two vertices
	@Test
	public void adjacentEdgeTest() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		assertEquals(true, list.checkAdjacent(1, 4));
	}

	// checking adjacent b/w two vertices
	@Test
	public void adjacentEdgeTest2() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		assertEquals(false, list.checkAdjacent(1, 3));
	}

	// checking if an edge exists between two vertices that do not exist
	@Test(expected = IllegalArgumentException.class)
	public void adjacentEdgeTest_WhenNoNodeExists() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(5, 1);
	}

	// checking if an edge is removed
	@Test
	public void RemoveEdge() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		list.removeEdge(1, 4);
		assertEquals(2, list.getNumEdges());
	}

	// removing an non existent edge should return original number of edges
	@Test
	public void RemoveEdgeWhenNoEdge() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		list.removeEdge(4, 1);
		assertEquals(2, list.getNumEdges());
	}

	// removing a self loop edge
	@Test
	public void RemoveSelfLoopEdge() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		list.removeEdge(1, 1);
		assertEquals(2, list.getNumEdges());
	}

	// removing edges between nodes that do not exist
	@Test(expected = IllegalArgumentException.class)
	public void RemoveEdgeForNoNode() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		list.removeEdge(5, 6);
	}
	
	// Remove edges for an empty map
	@Test(expected = IllegalArgumentException.class)
	public void RemoveEdgeInEmptyMap() {
		IntGraphList list = new IntGraphList();
		list.removeEdge(5, 6);
	}

	// checking the correct degree of a vertex
	@Test
	public void getDegreeTest() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		assertEquals(3, list.getDegree(4));
		assertEquals(1, list.getDegree(1));
		assertEquals(1, list.getDegree(2));
		assertEquals(1, list.getDegree(3));
	}

	// getting degree for a node that does not exist
	@Test(expected = IllegalArgumentException.class)
	public void getDegreeTest_WhenNoNodeExists() {
		IntGraphList list = new IntGraphList(2);
		list.getDegree(60);
	}

	// checking if the correct adjacentList of a node is obtained
	@Test
	public void getAdjacentListTest() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		List<Integer> a = new LinkedList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		List<Integer> b = new LinkedList<>();
		b.add(4);
		assertEquals(a, list.getAdjacencyList(4));
		assertEquals(b, list.getAdjacencyList(1));
		assertEquals(b, list.getAdjacencyList(2));
		assertEquals(b, list.getAdjacencyList(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getAdjacentList_WhenNoNodesExist() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(4, 1);
		list.addEdge(4, 2);
		list.addEdge(4, 3);
		list.getAdjacencyList(6);
	}

	// checks if the same vertices are created in the map
	@Test
	public void getVertices() {
		IntGraphList list = new IntGraphList(4);
		Set<Integer> x = new HashSet<>();
		x.add(1);
		x.add(2);
		x.add(3);
		x.add(4);
		assertEquals(x, list.getVertices());
	}

	// checks if the same vertices are created in the map when the vertices are manually added
	@Test
	public void getVertices2() {
		IntGraphList list = new IntGraphList();
		list.addVertex(1);
		list.addVertex(2);
		list.addVertex(3);
		list.addVertex(40);
		Set<Integer> x = new HashSet<>();
		x.add(1);
		x.add(2);
		x.add(3);
		x.add(40);
		assertEquals(x, list.getVertices());
	}

	// checks if all nodes are connected
	@Test
	public void depthSearchTest_AllNodesConnected() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(2, 3);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		assertEquals(true, list.depthFirstSearch(1));
	}

	// checking for false when graph is not connected
	@Test
	public void depthSearchTest_OneNodeNotConnected() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(2, 3);
		list.addEdge(1, 3);
		// false because node 4 is not connected to the graph
		assertEquals(false, list.depthFirstSearch(1));
	}

	// checks the effect of removal of node
	@Test
	public void depthSearchTest_EffectOfEdgeRemoval() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(2, 3);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		list.removeEdge(1, 2);
		// still true as 2 and 3 are connected
		assertEquals(true, list.depthFirstSearch(1));
	}

	// checks the effect of removal of node
	@Test
	public void depthSearchTest_EdgeRemovedAgain() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		list.removeEdge(1, 3);
		assertEquals(false, list.depthFirstSearch(1));
	}

	// checking if the depthSearch fails when the source node does not exist
	@Test(expected = IllegalArgumentException.class)
	public void depthSearchTestNoSourceNode() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		list.depthFirstSearch(9);
	}

	// checks if the degreeSet maintained by the object is same as sorted degree set
	@Test
	public void degreeSetTest() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(2, 3);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		ArrayList<Integer> x = new ArrayList<>();
		x.add(1);
		x.add(2);
		x.add(2);
		x.add(3);
		Collections.sort(x);;
		assertEquals(x, list.degreeSet());
	}

	// checks for the degree set of an empty map
	@Test
	public void degreeSetTest_EmptyMap() {
		IntGraphList list = new IntGraphList();
		ArrayList<Integer> x = new ArrayList<>();
		assertEquals(x, list.degreeSet());
	}

	// checks the degree set of a map that has no edges
	@Test
	public void degreeSetTest_MapWithNoEdges() {
		IntGraphList list = new IntGraphList(2);
		ArrayList<Integer> x = new ArrayList<>();
		x.add(0);
		x.add(0);
		assertEquals(x, list.degreeSet());
	}

	// This stream holds the output of the PrintStream, this has to be converted to
	// String later for our assertions
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	// This printStream will keep a reference to the original System.Out Stream
	// Note: This is important to save the old System.out
	private final PrintStream originalOut = System.out;

	// This method sets up the Stream to capture the printed content by System.out
	// to outContent
	// This method is called before each unit test to capture the printed output
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	// This method resets the Stream to System.out to the way it was
	// This method is called after every test methods
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	// Tests if the answer to the challenge is the same as we expect
	@Test
	public void answerIntCheck() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(2, 3);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		assertEquals(4, list.GraphOperation(System.out));
	}
	
	// Tests if the println answers are the same as expected based on the println statements
	@Test
	public void answerCheckPrintln() {
		IntGraphList list = new IntGraphList(4);
		list.addEdge(1, 2);
		list.addEdge(2, 3);
		list.addEdge(1, 3);
		list.addEdge(1, 4);
		list.GraphOperation(System.out);
		assertEquals("Remove the edge 1 - 2 and put it between 3 - 4\n"
				+ "Remove the edge 1 - 3 and put it between 2 - 4\n"
				+ "Remove the edge 2 - 3 and put it between 2 - 4\n"
				+ "Remove the edge 2 - 3 and put it between 3 - 4\n"
				+ "The total number of possible combinations are: 4\n"
				+ "\n"
				+ "The removing edge 1 - 4 is invalid!\n"
				+ "Reason: The graph does not remain connected when this edge is removed.\n", outContent.toString());
	}
	
}
