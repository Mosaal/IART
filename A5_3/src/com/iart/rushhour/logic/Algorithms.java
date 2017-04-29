package com.iart.rushhour.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Stack;

import com.iart.rushhour.game.Block;
import com.iart.rushhour.game.Board;

public class Algorithms {

	/**
	 * Returns all the adjacent nodes of a given node
	 * @param curr the node whose adjacent nodes will be returned
	 */
	private static ArrayList<Board> getAdjacentNodes(Board curr) {
		ArrayList<Board> adjacents = new ArrayList<Board>();

		// Try to move each block on the board
		for (Entry<Integer, Block> block: curr.getBlocks().entrySet()) {
			for (int i = 0; i < 4; i++) {
				// Check if it can be moved
				if (curr.canBlockBeMoved(block.getKey().intValue(), i)) {
					Board aux = new Board(curr);

					// Set move and parent of adjacent node
					aux.setMove(new Move(block.getKey().intValue(), i));
					aux.moveBlock(block.getKey().intValue(), i);
					aux.setParent(curr);

					// Add it
					adjacents.add(aux);
				}
			}
		}

		return adjacents;
	}

	/**
	 * A Breadth First Search algorithm implementation
	 * @param board the initial node
	 */
	public static Board BFS(Board board) {
		// Store all visited nodes
		ArrayList<Board> visited = new ArrayList<Board>();
		visited.add(board);

		// Queue the first node
		LinkedList<Board> queue = new LinkedList<Board>();
		queue.add(board);

		// Loop the queue as long as it isn't empty
		while (!queue.isEmpty()) {
			// Get the node at the front of the queue
			Board front = queue.removeFirst();

			// Check if the goal is achieved
			if (front.isGameOver())
				return front;

			// Get adjacent nodes
			ArrayList<Board> adj = getAdjacentNodes(front);

			// Add each unvisited node to the queue
			for (int i = 0; i < adj.size(); i++) {
				if (!visited.contains(adj.get(i))) {
					visited.add(adj.get(i));
					queue.add(adj.get(i));
				}
			}
		}

		return null;
	}

	/**
	 * A Depth First Search algorithm implementation
	 * @param board the initial node
	 */
	public static Board DFS(Board board) {
		// Store all visited nodes
		ArrayList<Board> visited = new ArrayList<Board>();
		visited.add(board);

		// Stack the first node
		Stack<Board> stack = new Stack<Board>();
		stack.push(board);

		// Loop the stack as long as it isn't empty
		while (!stack.isEmpty()) {
			// Get the node at the top of the stack
			Board top = stack.pop();

			// Check if the goal is achieved
			if (top.isGameOver())
				return top;

			// Get adjacent nodes
			ArrayList<Board> adj = getAdjacentNodes(top);

			// Add the next unvisited node to the stack
			for (int i = 0; i < adj.size(); i++) {
				if (!visited.contains(adj.get(i))) {
					stack.push(top);
					stack.push(adj.get(i));
					visited.add(adj.get(i));
					break;
				}
			}
		}

		return null;
	}

	/**
	 * An A* algorithm implementation
	 * @param board the initial node
	 */
	public static ArrayList<Move> AStar(Board board) {
		return null;
	}
}
