package com.iart.rushhour.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Stack;

import com.iart.rushhour.game.Block;
import com.iart.rushhour.game.Board;

public class Algorithms {

	/**
	 * Returns all the adjacent nodes to a given node
	 * @param curr the node whose adjacent nodes will be returned
	 */
	private static ArrayList<Board> getAdjacentNodes(Board curr) {
		ArrayList<Board> adjacents = new ArrayList<Board>();

		for (Entry<Integer, Block> block: curr.getBlocks().entrySet()) {
			for (int i = 0; i < 4; i++) {
				if (curr.canBlockBeMoved(block.getKey().intValue(), i)) {
					Board aux = new Board(curr);
					aux.setMove(new Move(block.getKey().intValue(), i));
					aux.moveBlock(block.getKey().intValue(), i);
					aux.setParent(curr);
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
		ArrayList<Board> visited = new ArrayList<Board>();

		// Initialize queue and add initial node to it
		LinkedList<Board> queue = new LinkedList<Board>();
		queue.add(board);

		// Loop the queue while it isn't empty
		while (!queue.isEmpty()) {
			Board curr = queue.removeFirst();

			// Check if current node is the goal
			if (curr.isGameOver())
				return curr;

			// Get adjacent nodes
			ArrayList<Board> adjacents = getAdjacentNodes(curr);

			// Check if it has been visited
			if (!visited.contains(curr)) {
				visited.add(curr);

				// Check each adjacent node
				for (int i = 0; i < adjacents.size(); i++)
					if (!visited.contains(adjacents.get(i)))
						queue.add(adjacents.get(i));
			}
		}

		return null;
	}

	/**
	 * A Depth First Search algorithm implementation
	 * @param board the initial node
	 */
	public static Board DFS(Board board) {
		ArrayList<Board> visited = new ArrayList<Board>();

		// Initialize stack and add initial node to it
		Stack<Board> stack = new Stack<Board>();
		stack.push(board);

		// Loop the stack while it isn't empty
		while (!stack.isEmpty()) {
			Board curr = stack.pop();

			// Check if current node is the goal
			if (curr.isGameOver())
				return curr;

			// Get adjacent nodes
			ArrayList<Board> adjacents = getAdjacentNodes(curr);

			// Check if it has been visited
			if (!visited.contains(curr)) {
				visited.add(curr);

				// Check each adjacent node
				for (int i = 0; i < adjacents.size(); i++)
					if (!visited.contains(adjacents.get(i)))
						stack.push(adjacents.get(i));
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
