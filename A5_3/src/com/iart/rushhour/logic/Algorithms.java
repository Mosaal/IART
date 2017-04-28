package com.iart.rushhour.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Stack;

import com.iart.rushhour.game.Block;
import com.iart.rushhour.game.Board;

public class Algorithms {

	/**
	 * A Breadth First Search algorithm implementation
	 * @param board the initial node
	 */
	public static ArrayList<Move> BFS(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Board> visited = new ArrayList<Board>();

		// Initialize queue and add initial node to it
		LinkedList<Board> queue = new LinkedList<Board>();
		queue.add(board);

		// Loop the queue while it isn't empty
		while (!queue.isEmpty()) {
			Board curr = queue.removeFirst();
			ArrayList<Board> adjacents = new ArrayList<Board>();

			// Check if current node is the goal
			if (curr.isGameOver()) {
				System.out.print(curr.toString());
				return moves;
			}

			// Get adjacent nodes
			for (Entry<Integer, Block> block: curr.getBlocks().entrySet()) {
				for (int i = 0; i < 4; i++) {
					if (curr.canBlockBeMoved(block.getKey().intValue(), i)) {
						Board aux = new Board(curr);
						aux.moveBlock(block.getKey().intValue(), i);
						adjacents.add(aux);
					}
				}
			}

			// Check each adjacent node
			for (int i = 0; i < adjacents.size(); i++) {
				if (!visited.contains(adjacents.get(i))) {
					visited.add(adjacents.get(i));
					queue.add(adjacents.get(i));
				}
			}
		}

		return null;
	}

	/**
	 * A Depth First Search algorithm implementation
	 * @param board the initial node
	 */
	public static ArrayList<Move> DFS(Board board) {
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Board> visited = new ArrayList<Board>();

		// Initialize stack and add initial node to it
		Stack<Board> stack = new Stack<Board>();
		stack.push(board);

		// Loop the stack while it isn't empty
		while (!stack.isEmpty()) {
			Board curr = stack.pop();

			// Check if current node is the goal
			if (curr.isGameOver()) {
				System.out.print(curr.toString());
				return moves;
			}

			// Check if node was visited
			if (!visited.contains(curr)) {
				visited.add(curr);

				// Get adjacent nodes
				for (Entry<Integer, Block> block: curr.getBlocks().entrySet()) {
					for (int i = 0; i < 4; i++) {
						if (curr.canBlockBeMoved(block.getKey().intValue(), i)) {
							Board aux = new Board(curr);
							aux.moveBlock(block.getKey().intValue(), i);
							stack.push(aux);
						}
					}
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
