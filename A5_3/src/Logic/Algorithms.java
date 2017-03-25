package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import Game.*;

public class Algorithms {

	public static Move[] DFS(Board board) {
		//Move[] moves = null;
		List<Board> to_visit = new ArrayList<Board>();
		List<Board> visited = new ArrayList<Board>();
		to_visit.add(board);
		visited.add(board);
		int aux = 0;
		while(!to_visit.isEmpty()) {
			for (Entry<Integer, Block> block: to_visit.get(aux).getBlocks().entrySet()) {
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 0)) {
					to_visit.get(aux).moveBlock(block.getValue(), 0);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(1,to_visit.get(aux));	
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 1)) {
					to_visit.get(aux).moveBlock(block.getValue(), 1);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(1,to_visit.get(aux));	
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 2)) {
					to_visit.get(aux).moveBlock(block.getValue(), 2);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(1,to_visit.get(aux));
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 3)) {
					to_visit.get(aux).moveBlock(block.getValue(), 3);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(1,to_visit.get(aux));	
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}	
				to_visit.remove(0);
				aux++;
			}
			aux=0;
		}		
		return null;
	}
	
	public static Move[] BFS(Board board) {
		return null;
	}
	
	
	// Neste caso, o jogo apenas tem em conta o número de jogadas minimas a serem feitas, ou seja a profundidade na árvore
	// Por isso neste caso, o algoritmo a* é na verdade uma pesquisa em largura
	public static Move[] AStar(Board board) {
		//Move[] moves = null;
		List<Board> to_visit = new ArrayList<Board>();
		List<Board> visited = new ArrayList<Board>();
		to_visit.add(board);
		visited.add(board);
		int aux = 0;
		while(!to_visit.isEmpty()) {
			for (Entry<Integer, Block> block: to_visit.get(aux).getBlocks().entrySet()) {
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 0)) {
					to_visit.get(aux).moveBlock(block.getValue(), 0);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(to_visit.get(aux));
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 1)) {
					to_visit.get(aux).moveBlock(block.getValue(), 1);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(to_visit.get(aux));
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 2)) {
					to_visit.get(aux).moveBlock(block.getValue(), 2);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(to_visit.get(aux));
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}
				if(to_visit.get(aux).canBlockBeMoved(block.getValue(), 3)) {
					to_visit.get(aux).moveBlock(block.getValue(), 3);
					if(!visited.contains(to_visit.get(aux))){
						to_visit.add(to_visit.get(aux));
						visited.add(to_visit.get(aux));
						//adicionar ao moves
						if(to_visit.get(aux).wingame()){
							//return moves;
						}
					}
				}	
				to_visit.remove(0);
				aux++;
			}
			aux=0;
		}		
		return null;
	}
}
