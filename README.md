# NINENINE Graph Manipulation Challenge

This repository contains the solution to a programming challenge undertaken as part of the Final Project from CODECHEF. The challenge involves manipulating a graph representing a map to create a new graph with specific properties.

## Problem Description

The challenge requires manipulating an undirected graph representing a map by removing an edge and placing it elsewhere in the graph, ensuring that the graph remains connected throughout the process. The goal is to create a new graph with the same number of nodes and edges, where the count of vertices having a particular degree remains the same as the original graph.

## Solution Overview

The solution is implemented in Java and utilizes an object called IntGraphList to represent the graph with an adjacency list. The IntGraphList object extends an abstract class called IntGraph, which provides methods for graph manipulation. The program includes 36 unit tests to validate the solution methods and handle unexpected inputs.

## Running the Program

To run the program, provide an input text file containing details of the original graph, including the number of vertices, edges, and edge connections. The program will output the edge that should be removed and where it should be placed to create a valid new graph. Additionally, it will display the total number of possible graphs with the same set of degrees as the original graph.

## Notes

- The program is sensitive to the input text file format and requires accurate graph details.
- Due to the nature of the solution, the program may have a potentially high runtime, especially for larger graphs.
- Care has been taken to handle potential double counts and reduce program complexity.
