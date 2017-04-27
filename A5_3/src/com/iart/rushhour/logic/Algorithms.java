package com.iart.rushhour.logic;

// import java.util.ArrayList;
import java.util.List;
// import java.util.Map.Entry;

import com.iart.rushhour.game.*;

public class Algorithms {

//	public static List<Move> DFS(Board board) {
//
//		//Falta por o "board_aux" independente do "board" e vice-versa
//		//Falta o moves_aux independente
//		
//		List<Board> to_visit = new ArrayList<Board>(); //lista de tabuleiros que irá guardar todos os tabuleiros a visitar
//		List<Board> visited = new ArrayList<Board>(); //lista de tabuleiros que já estiveram ou estão na lista "to_visit"
//
//		//Variaveis "Move" que serao utilizadas para guardar as varias sequencias possiveis de movimentos 
//		List<List<Move>> move_matrix = new ArrayList<List<Move>>();
//		List<Move> move_list_aux = new ArrayList<Move>();
//		Move move_aux = null;
//
//		to_visit.add(board);
//		visited.add(board);
//		move_matrix.add(move_list_aux);
//		
//		Board board_aux = new Board(board); //Board auxiliar incializado como c�pia do board original
//		while(!to_visit.isEmpty()) {
//			board_aux = new Board(to_visit.get(0));
//			move_list_aux = new List<Move>(move_matrix.get(0));
//			System.out.println(visited.get(0).toString());
//			//Verifica se o tabuleiro atual e a solucao do algoritmo
//			if(board_aux.isGameOver()){
//				return move_matrix.get(0);
//			} else {
//				for (Entry<Integer, Block> block: board_aux.getBlocks().entrySet()) {  //Percorre todos os blocos do estado de tabuleiro
//					for (int i = 0; i <= 3; i++) { //Testa todas as direcoes para o "block" em questao
//						//0 - Cima, 1 - Baixo, 2 - Esquerda, 3 - Direita
//						if(board_aux.canBlockBeMoved(block.getValue(), i)) {
//							board_aux.moveBlock(block.getValue(), i);
//							
//							
//							if(!visited.contains(board_aux)) { //verifica se o novo estado de tabuleiro nao foi vericado anteriormente
//								to_visit.add(1,board_aux);	//adiciona no inicio da lista a seguir ao atual
//								visited.add(board_aux);
//								
//								//adicionar ao moves
//								move_aux = new Move(block.getValue().getID(),i);
//								//Para o primeiro caso, acrescenta apenas uma lista com o movimento atual
//								if(move_matrix.size() == 1){
//									System.out.println("lul");
//									move_list_aux.add(move_aux);
//									move_matrix.add(move_list_aux);
//								}
//								//Se nao, vai buscar a lista com os movimentos utilizados e acrescenta-lhe o movimento atual
//								else {
//									move_list_aux = (List<Move>) move_matrix.get(0).clone();
//									move_list_aux.add(move_aux);
//									move_matrix.add(1,move_list_aux);
//								}
//							}
//							board_aux = new Board(to_visit.get(0)); //Da-se reset ao board_aux para este ser utilizado para os restantes movimentos possiveis do "block"
//						}
//					}		
//				}
//				System.out.println(move_matrix.size());
//				to_visit.remove(0);
//				move_matrix.remove(0);
//			}
//		}		
//		return null;
//	}
//	
//	public static List<Move> BFS(Board board) {
//		List<Board> to_visit = new ArrayList<Board>(); //lista de tabuleiros que irá guardar todos os tabuleiros a visitar
//		List<Board> visited = new ArrayList<Board>(); //lista de tabuleiros que já estiveram ou estão na lista "to_visit"
//
//		//Variaveis Move que serão utilizadas para guardar as várias sequências possíveis de movimentos 
//		List<List<Move>> move_matrix = new ArrayList<List<Move>>();
//		List<Move> move_list_aux = new ArrayList<Move>();
//		Move move_aux = null;
//
//		to_visit.add(board);
//		visited.add(board);
//		int aux = 0;
//		Board board_aux = new Board(board.getWidth(), board.getHeight()); //Board auxiliar incializado com valores iguais ao board utilizado
//		while(!to_visit.isEmpty()) {
//			board_aux = to_visit.get(aux);
//			//Verifica se o tabuleiro atual é solução do algoritmo
//			if(board_aux.isGameOver()){
//				return move_matrix.get(aux);
//			}
//			else {
//				for (Entry<Integer, Block> block: board_aux.getBlocks().entrySet()) {  //Percorre todos os blocos do estado de tabuleiro
//					for (int i = 0; i <= 3; i++) { //Testa todas as direções para o "block" em questão
//						//0 - Cima, 1 - Baixo, 2 - Esquerda, 3 - Direita
//						if(board_aux.canBlockBeMoved(block.getValue(), i)) {
//							board_aux.moveBlock(block.getValue(), i);
//							if(!visited.contains(board_aux)) { //verifica se o novo estado de tabuleiro não foi vericado anteriormente
//								to_visit.add(board_aux); //adiciona no final da lista	
//								visited.add(board_aux);
//
//								//adicionar ao moves
//
//								move_aux = new Move(block.getValue().getID(),i);
//								//Se a matriz estiver vazia, acrescenta apenas uma lista com o movimento atual
//								if(move_matrix.isEmpty()){
//									move_list_aux.add(move_aux);
//									move_matrix.add(move_list_aux);
//								}
//								//Se não, vai buscar a lista com os movimentos utilizados e acrescenta-lhe o movimento atual
//								else{
//									move_list_aux = move_matrix.get(aux);
//									move_list_aux.add(move_aux);
//									move_matrix.add(move_list_aux);
//								}
//							}
//							board_aux = to_visit.get(aux); //Dá-se reset ao board_aux para este ser utilizado para os restantes movimentos possiveis do "block"
//						}
//						aux++;
//					}		
//				}
//				aux=0;
//				to_visit.remove(0);
//			}
//		}		
//		return null;
//	}
	
	// Neste caso, a euristica que está a ser aplicada é a seguinte: 
	//À distância do jogador à meta, soma-se 1 por cada carro que se encontre no seu caminho
	public static List<Move> AStar(Board board) {
		return null;
	}
}
