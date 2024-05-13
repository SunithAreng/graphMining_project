
/**
 * Sunith Francis Areng
 * MSCI 240: Final Project
 * 10th December 2021
 * This program aims to solve a codeChef problem @ https://www.codechef.com/problems/NINENINE
 * The objective is to create a similar graph to the existing one by removing an edge and placing
 * it between two unconnected nodes while keeping the same set of degrees for vertices of the new graph 
 * same as the the original graph.
 * Input: This program takes a text file as its input to create a graph
 * Output: This program outputs a number for the possible changes that can be made to the graph and the
 * edges that can be removed and placed between other nodes to a new graph.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphMain {

	public static final String FILENAME = "input.txt";

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File(FILENAME));

		// The below statements are necessary as we need to make sure that there are
		// integers in the text file
		if (!scanner.hasNextInt()) {
			throw new IllegalArgumentException("The file is empty!");
		}
		int Vertices = scanner.nextInt(); // gets the total number of vertices
		int Edges = scanner.nextInt(); // get the total number of edges
		if (Vertices < 0) {
			throw new IllegalArgumentException("The input can not have negative number of vertices!");
		}

		// Checks if the constraints outlined by the challenge holds true
		if (!(Vertices - 1 <= Edges) || !(Vertices <= 100000) || !(Edges <= 100000)) {
			throw new IllegalArgumentException("The text file does not meet the constraints!");
		}

		// One assumption we make is that we do not have a node with 0 as its value
		// Lowest node starts from 1 and highest node represents the total number of
		// nodes. Also, all the keys in the map are equal to the value of the nodes.
		IntGraphList list = createGraph(scanner, Vertices);

		System.out.println("Total number of Vertices: " + Vertices);
		if (Edges == list.getNumEdges()) {
			System.out.println("Total number of Edges: " + Edges);
		} else {
			throw new IllegalArgumentException("There is an error in the text file!");
		}

		System.out.println();
		System.out.println(list.toString());
		System.out.println();
		// Calling the graph operation method to get the required output
		list.GraphOperation(System.out);

	}

	/**
	 * This method creates a new IntGraphList object using input from the scanner
	 * 
	 * @param scanner  reads the input.txt file for nodes and their edges
	 * @param vertices creates an object with the size of number of vertices
	 * @return returns the newly created IntGraphList object
	 */
	public static IntGraphList createGraph(Scanner scanner, int vertices) {
		IntGraphList list = new IntGraphList(vertices);
		
		// This loop goes over the text file to create a new graph
		while (scanner.hasNextInt()) {
			int v1 = scanner.nextInt();
			if (!scanner.hasNextInt() || v1 < 0) {
				throw new IllegalArgumentException("The file has missing data or has negative inputs!");
			} else {
				int v2 = scanner.nextInt();
				if (v1 < 0 || v2 < 0) {
					throw new IllegalArgumentException("The file contains negative inputs!");
				} else {
					list.addEdge(v1, v2);
				}
			}
		}
		return list;
	}
}
