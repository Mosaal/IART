package com.iart.rushhour.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Stack;

import com.iart.rushhour.game.Block;
import com.iart.rushhour.game.Board;

public class Algorithms {

	// Static variables
	public static final int DISTANCE_HEURISTIC = 0;
	public static final int NUM_BLOCKING_HEURISTIC = 1;
	public static final int DISTANCE_NUM_BLOCKING_HEURISTIC = 2;

	// Static methods
	/**
	 * Returns all the adjacent nodes of a given node
	 * @param curr the node whose adjacent nodes will be returned
	 */
	private static ArrayList<Board> getAdjacentNodes(Board curr) {
		ArrayList<Board> adjacents = new ArrayList<Board>();

		// Try to move each block on the board
		for (Entry<Integer, Block> block: curr.getBlocks().entrySet()) {
			// Try to move it in any of the four directions
			for (int i = 0; i < 4; i++) {
				// Check if it can be moved
				if (curr.canBlockBeMoved(block.getKey().intValue(), i)) {
					Board aux = new Board(curr);

					// Set move and parent of adjacent node
					aux.setMove(new Move(block.getKey().intValue(), i));
					aux.moveBlock(block.getKey().intValue(), i);
					aux.setG(curr.getG() + 1);
					aux.setParent(curr);

					// Add it
					adjacents.add(aux);
				}
			}
		}

		return adjacents;
	}

	/**
	 * A Breadth First Search algorithm implementation<br>
	 * Guarantees the minimum amount of moves to the exit
	 * @param board the initial node
	 */
	public static Board BFS(Board board) {
		// Number of visited nodes
		int visitedNodes = 0;

		// Store all visited nodes
		ArrayList<Board> visited = new ArrayList<Board>();
		visited.add(board);

		// Queue the first node
		LinkedList<Board> queue = new LinkedList<Board>();
		queue.add(board);

		// Loop the queue as long as it isn't empty
		while (!queue.isEmpty()) {
			// Increment number of visited nodes
			visitedNodes++;

			// Get the node at the front of the queue
			Board front = queue.removeFirst();

			// Check if the goal is achieved
			if (front.isGameOver()) {
				front.setVisitedNodes(visitedNodes);
				return front;
			}

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
	 * A Depth First Search algorithm implementation<br>
	 * Does not guarantee the minimum amount of moves to the exit
	 * @param board the initial node
	 */
	public static Board DFS(Board board) {
		// Number of visited nodes
		int visitedNodes = 0;

		// Store all visited nodes
		ArrayList<Board> visited = new ArrayList<Board>();
		visited.add(board);

		// Stack the first node
		Stack<Board> stack = new Stack<Board>();
		stack.push(board);

		// Loop the stack as long as it isn't empty
		while (!stack.isEmpty()) {
			// Increment number of visited nodes
			visitedNodes++;

			// Get the node at the top of the stack
			Board top = stack.pop();

			// Check if the goal is achieved
			if (top.isGameOver()) {
				top.setVisitedNodes(visitedNodes);
				return top;
			}

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
	 * Returns the node with the lowest f value in the given list
	 * @param open the list to be searched through
	 */
	private static Board lowestF(ArrayList<Board> open) {
		Board temp = null;

		for (int i = 0; i < open.size(); i++) {
			if (temp == null)
				temp = open.get(i);
			else if (open.get(i).getF() < temp.getF())
				temp = open.get(i);
		}

		return temp;
	}

	/**
	 * Returns the value of the chosen heuristic
	 * @param board the current node
	 * @param heuristic the heuristic to be calculated
	 */
	private static int heuristic(Board board, int heuristic) {
		int h = 0;
		Block block = board.getBlockByID(Block.MAIN_BLOCK_ID);

		// Check the chosen heuristic
		if (heuristic == DISTANCE_HEURISTIC) {
			// Count every cell different from the main block
			for (int i = block.getCol(); i < board.getWidth(); i++)
				if (board.getGrid()[block.getRow()][i] != Block.MAIN_BLOCK_ID)
					h++;
		} else if (heuristic == NUM_BLOCKING_HEURISTIC) {
			// Count every cell different from the main block and different from an empty cell
			for (int i = block.getCol(); i < board.getWidth(); i++)
				if (board.getGrid()[block.getRow()][i] != 0 && board.getGrid()[block.getRow()][i] != Block.MAIN_BLOCK_ID)
					h++;
		} else if (heuristic == DISTANCE_NUM_BLOCKING_HEURISTIC) {
			// Count every cell different from the main block and add one for every cell in the way
			for (int i = block.getCol(); i < board.getWidth(); i++) {
				if (board.getGrid()[block.getRow()][i] != Block.MAIN_BLOCK_ID) {
					if (board.getGrid()[block.getRow()][i] != 0)
						h++;
					h++;
				}
			}
		}

		return h;
	}

	/**
	 * An A* algorithm implementation<br>
	 * Guarantees the minimum amount of moves to the exit<br>
	 * The following are the admissible heuristics:<br>
	 * - the distance of the main block to the exit<br>
	 * - the number of blocks blocking the way to the exit<br>
	 * - the distance of the main block to the exit plus the the number of blocks blocking the way to the exit
	 * @param board the initial node
	 * @param heuristic the chosen heuristic
	 */
	public static Board AStar(Board board, int heuristic) {
		// Number of visited nodes
		int visitedNodes = 0;

		// Initialize open and closed lists
		ArrayList<Board> open = new ArrayList<Board>();
		ArrayList<Board> closed = new ArrayList<Board>();

		// Set the first node
		board.setG(0);
		board.setF(board.getG() + heuristic(board, heuristic));

		// Add it to the open list
		open.add(board);

		// Loop the open list as long as it isn't empty
		while (!open.isEmpty()) {
			// Increment number of visited nodes
			visitedNodes++;

			// Get the node with the lowest f value
			Board lowF = lowestF(open);

			// Check if it is the goal
			if (lowF.isGameOver()) {
				lowF.setVisitedNodes(visitedNodes);
				return lowF;
			}

			// Add it to the closed list and remove it from the open list
			closed.add(lowF);
			open.remove(lowF);

			// Get the adjacent nodes
			ArrayList<Board> adj = getAdjacentNodes(lowF);

			// Check each adjacent node not on the closed list
			for (int i = 0; i < adj.size(); i++) {
				if (!closed.contains(adj.get(i))) {
					// Set this node's f value
					adj.get(i).setF(adj.get(i).getG() + heuristic(adj.get(i), heuristic));

					// Check if it is on the open list
					if (!open.contains(adj.get(i))) {
						// Add it if it isn't
						open.add(adj.get(i));
					} else {
						// Get the one on the open list
						Board temp = open.get(open.indexOf(adj.get(i)));

						// Check which one has the lowest g value
						if (adj.get(i).getG() < temp.getG()) {
							temp.setG(adj.get(i).getG());
							temp.setParent(adj.get(i).getParent());
						}
					}
				}
			}
		}

		return null;
	}
}
