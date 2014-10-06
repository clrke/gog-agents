package ayee;

import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Method;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Agent
{
	/*
		SECTION :
		MEMBER  :
			1. Bercasio, Charlene C.
			2. Casing, Patricia Vianca N.
			3. Ferrer, Mea May F.
			4. Rolluqui, Divane Mae O.
		ALGORITHM USED
			1. Genetic Algorithm
			2. Bayesian Network
			3. Hidden Markov Model
	*/

	private int 
		priv = 0,	serg = 1,
		lut2 = 2,	lut1 = 3, 	
		capt = 4,	majr = 5, 	
		col1 = 6,	col2 = 7, 	
		gen1 = 8,	gen2 = 9, 	
		gen3 = 10,	gen4 = 11, 	
		gen5 = 12,	spyy = 13,	
		flag = 14;

	private Enemy[][] enemies;
	private Piece[][] myPieces;

	public JButton[][] InstantiatePiece(int iAI, JButton[][] Board,ImageIcon[] images)
	{
		/*
			0	PRIVATE
			1	SERGEANT
			2	2nd LIEUTENANT
			3	1st LIEUTENANT
			4	CAPTAIN
			5	MAJOR
			6	LT COLONEL
			7	COLONEL
			8	1st GENERAL
			9	2nd GENERAL
			10	3rd GENERAL
			11	4th GENERAL
			12	5th GENERAL
			13	SPY
			14	FLAG
		*/

		int[][] myPieces = placePieces();
		this.myPieces = new Piece[8][9];

		if(iAI == 1)
		{
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 7; j++) {
					Board[5+i][1+j].setIcon(images[myPieces[i][j]]);
					this.myPieces[5+i][1+j] = new Piece(i, j, myPieces[i][j]);
				}
			}
		}// if iAI == 1
		else
		{
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 7; j++) {
					Board[2-i][1+j].setIcon(images[myPieces[i][j]]);
					this.myPieces[2-i][1+j] = new Piece(i, j, myPieces[i][j]);
				}
			}
		}// if iAI == 2
		/*
			EXAMPLE
				Board[0][0].setIcon(images[0])
					-> Private is on the Board[0][0]
		*/
		return Board;
	}// InstantiatePiece

	public int[][] AIMove(int iAI, JButton[][] Board)
	{
		int Move[][] = new int[2][2];
		/*
			Move[0][0] = initial value of row
			Move[0][1] = initial value of column
			Move[1][0] = in what row the piece will be place
			Move[1][1] = in what column the piece will be place
		*/

		bayesianNetworkAndHiddenMarkovModel(iAI, Board);

		if(iAI == 1)
		{
			for (int i = 7; i >= 0; i--) {
				for (int j = 8; j >= 0; j--) {
					if(pieceIsEnemys(iAI, Board[i][j])) {
						int[][] move = findAttacker(iAI, Board, i, j);

						if(pieceIsEnemys(iAI, Board[move[1][0]][move[1][1]])) {
							enemies[move[1][0]][move[1][1]].attacked = true;
							enemies[move[1][0]][move[1][1]].attacker = this.myPieces[move[0][0]][move[0][1]].rank;
						}

						this.myPieces[move[1][0]][move[1][1]] = this.myPieces[move[0][0]][move[0][1]];
						this.myPieces[move[0][0]][move[0][1]] = null;

						return move;
					}
				}
			}
			// for(int i = 1; i < 8; i++) {
			// 	for (int j = 0; j < 9; j++) {
			// 		if(pieceIsMine(iAI, Board[i][j]) && ! pieceIsMine(iAI, Board[i-1][j]))
			// 		{
			// 			if(pieceIsEnemys(iAI, Board[i-1][j])) {
			// 				enemies[i-1][j].attacked = true;
			// 				enemies[i-1][j].attacker = this.myPieces[i][j].rank;
			// 			}

			// 			this.myPieces[i-1][j] = this.myPieces[i][j];
			// 			this.myPieces[i][j] = null;

			// 			return new int[][] {
			// 				{i, j},
			// 				{i-1, j}
			// 			};
			// 		}
			// 	}
			// }
		}// if iAI == 1
		else
		{
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(pieceIsEnemys(iAI, Board[i][j])) {
						int[][] move = findAttacker(iAI, Board, i, j);

						if(pieceIsEnemys(iAI, Board[move[1][0]][move[1][1]])) {
							enemies[move[1][0]][move[1][1]].attacked = true;
							enemies[move[1][0]][move[1][1]].attacker = this.myPieces[move[0][0]][move[0][1]].rank;
						}

						this.myPieces[move[1][0]][move[1][1]] = this.myPieces[move[0][0]][move[0][1]];
						this.myPieces[move[0][0]][move[0][1]] = null;

						return move;
					}
				}
			}
			// for(int i = 6; i >= 0; i--) {
			// 	for (int j = 8; j >= 0; j--) {
			// 		if(pieceIsMine(iAI, Board[i][j]) && ! pieceIsMine(iAI, Board[i+1][j]))
			// 		{
			// 			if(pieceIsEnemys(iAI, Board[i-1][j])) {
			// 				enemies[i-1][j].attacked = true;
			// 				enemies[i-1][j].attacker = this.myPieces[i][j].rank;
			// 			}

			// 			this.myPieces[i+1][j] = this.myPieces[i][j];
			// 			this.myPieces[i][j] = null;
						
			// 			return new int[][] {
			// 				{i, j},
			// 				{i+1, j}
			// 			};
			// 		}
			// 	}
			// }
		}// if iAI == 2

		return Move;
	}// AIMove

	public int[][] findAttacker(int ai, JButton[][] board, int x, int y) {
		int rank = enemies[x][y].mostProbable();

		if(ai == 1) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(pieceIsMine(ai, board[i][j]) && defeats(myPieces[i][j].rank, rank)) {
						if(y < j && ! pieceIsMine(ai, board[i][j-1])) {
							return new int[][] {
				 				{i, j},
								{i, j-1}
							};		
						}
						else if(y > j && ! pieceIsMine(ai, board[i][j+1])) {
							return new int[][] {
				 				{i, j},
								{i, j+1}
							};		
						}
						else if(x < i && ! pieceIsMine(ai, board[i-1][j])) {
							return new int[][] {
				 				{i, j},
								{i-1, j}
							};		
						}
						else if(x > i && ! pieceIsMine(ai, board[i+1][j])) {
							return new int[][] {
				 				{i, j},
								{i+1, j}
							};
						}
					}
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(pieceIsMine(ai, board[i][j]) && myPieces[i][j].rank != 14) {
						if(y < j && ! pieceIsMine(ai, board[i][j-1])) {
							return new int[][] {
				 				{i, j},
								{i, j-1}
							};		
						}
						else if(y > j && ! pieceIsMine(ai, board[i][j+1])) {
							return new int[][] {
				 				{i, j},
								{i, j+1}
							};		
						}
						else if(x < i && ! pieceIsMine(ai, board[i-1][j])) {
							return new int[][] {
				 				{i, j},
								{i-1, j}
							};		
						}
						else if(x > i && ! pieceIsMine(ai, board[i+1][j])) {
							return new int[][] {
				 				{i, j},
								{i+1, j}
							};
						}
					}
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(pieceIsMine(ai, board[i][j])) {
						if(y < j && ! pieceIsMine(ai, board[i][j-1])) {
							return new int[][] {
				 				{i, j},
								{i, j-1}
							};		
						}
						else if(y > j && ! pieceIsMine(ai, board[i][j+1])) {
							return new int[][] {
				 				{i, j},
								{i, j+1}
							};		
						}
						else if(x < i && ! pieceIsMine(ai, board[i-1][j])) {
							return new int[][] {
				 				{i, j},
								{i-1, j}
							};		
						}
						else if(x > i && ! pieceIsMine(ai, board[i+1][j])) {
							return new int[][] {
				 				{i, j},
								{i+1, j}
							};
						}
					}
				}
			}
		}
		else {
			for (int i = 7; i >= 0; i--) {
				for (int j = 8; j >= 0; j--) {
					if(pieceIsMine(ai, board[i][j]) && defeats(myPieces[i][j].rank, rank)) {
						if(y < j && ! pieceIsMine(ai, board[i][j-1])) {
							return new int[][] {
				 				{i, j},
								{i, j-1}
							};		
						}
						else if(y > j && ! pieceIsMine(ai, board[i][j+1])) {
							return new int[][] {
				 				{i, j},
								{i, j+1}
							};		
						}
						else if(x < i && ! pieceIsMine(ai, board[i-1][j])) {
							return new int[][] {
				 				{i, j},
								{i-1, j}
							};		
						}
						else if(x > i && ! pieceIsMine(ai, board[i+1][j])) {
							return new int[][] {
				 				{i, j},
								{i+1, j}
							};
						}
					}
				}
			}
			for (int i = 7; i >= 0; i--) {
				for (int j = 8; j >= 0; j--) {
					if(pieceIsMine(ai, board[i][j]) && myPieces[i][j].rank != 14) {
						if(y < j && ! pieceIsMine(ai, board[i][j-1])) {
							return new int[][] {
				 				{i, j},
								{i, j-1}
							};		
						}
						else if(y > j && ! pieceIsMine(ai, board[i][j+1])) {
							return new int[][] {
				 				{i, j},
								{i, j+1}
							};		
						}
						else if(x < i && ! pieceIsMine(ai, board[i-1][j])) {
							return new int[][] {
				 				{i, j},
								{i-1, j}
							};		
						}
						else if(x > i && ! pieceIsMine(ai, board[i+1][j])) {
							return new int[][] {
				 				{i, j},
								{i+1, j}
							};
						}
					}
				}
			}
			for (int i = 7; i >= 0; i--) {
				for (int j = 8; j >= 0; j--) {
					if(pieceIsMine(ai, board[i][j])) {
						if(y < j && ! pieceIsMine(ai, board[i][j-1])) {
							return new int[][] {
				 				{i, j},
								{i, j-1}
							};		
						}
						else if(y > j && ! pieceIsMine(ai, board[i][j+1])) {
							return new int[][] {
				 				{i, j},
								{i, j+1}
							};		
						}
						else if(x < i && ! pieceIsMine(ai, board[i-1][j])) {
							return new int[][] {
				 				{i, j},
								{i-1, j}
							};		
						}
						else if(x > i && ! pieceIsMine(ai, board[i+1][j])) {
							return new int[][] {
				 				{i, j},
								{i+1, j}
							};
						}
					}
				}
			}
		}
		return new int[][] {
			{0, 0},
			{0, 0}
		};
	}
	public int[][] placePieces() 
	{
		// ArrayList for all game pieces
		ArrayList<Integer> pieces = new ArrayList<Integer>();

		pieces.add(gen5); pieces.add(gen4);
		pieces.add(gen3); pieces.add(gen2);
		pieces.add(gen1); pieces.add(col2);
		pieces.add(priv); pieces.add(priv);
		pieces.add(col1); pieces.add(majr);
		pieces.add(capt); pieces.add(lut1);
		pieces.add(lut2); pieces.add(serg);
		pieces.add(priv); pieces.add(priv);
		pieces.add(spyy); pieces.add(spyy);
		pieces.add(priv); pieces.add(priv);
		pieces.add(flag);

		int[][] generatedPlaces = new int[3][7];

		// Perform genetic algorithm to place pieces
		geneticAlgorithm(pieces, generatedPlaces);

		Pieces myPieces = new Pieces(generatedPlaces);

		return myPieces.data;
	}

	public void geneticAlgorithm(ArrayList<Integer> pieces, int[][] places) 
	{
		// for each piece in pieces, place it in one of the possible places
		for (int piece : pieces)
			place(piece, places);
	}

	public void place(int piece, int[][] places) {
		// Distance from edge ay yung layo mula sa gilid
		// Bale lagay muna sa mga pinakagilid, tapos kung di na available lahat
		// ng nandun, sa next naman na pinakagilid

		for(int distanceFromEdge = 0; distanceFromEdge < 4; distanceFromEdge++)
		{
			for (int row = 0; row < 3; row++) {
				int col;

				// Try muna sa pinakakaliwa
				col = distanceFromEdge;
				if(places[row][col] == 0) // if place is available, place it and return.
				{
					places[row][col] = piece;
					return;
				}

				// Tapos sa pinakakanan
				col = places[row].length - distanceFromEdge - 1;
				if(places[row][col] == 0) // if place is available, place it and return.
				{
					places[row][col] = piece;
					return;
				}
			}
		}
	}

	public void bayesianNetworkAndHiddenMarkovModel(int ai, JButton[][] board) {
		if(enemies == null) {
			enemies = new Enemy[8][9];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(pieceIsEnemys(ai, board[i][j]))
						enemies[i][j] = new Enemy();
					else
						enemies[i][j] = null;
				}
			}
		}
		else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if (enemies[i][j] != null && ! pieceIsEnemys(ai, board[i][j])) {
						if(i != 0 && enemies[i-1][j] == null && pieceIsEnemys(ai, board[i-1][j])) {
							enemies[i-1][j] = enemies[i][j];
						}
						else if(i != 7 && enemies[i+1][j] == null && pieceIsEnemys(ai, board[i+1][j])) {
							enemies[i+1][j] = enemies[i][j];
						}
						else if(j != 0 && enemies[i][j-1] == null && pieceIsEnemys(ai, board[i][j-1])) {
							enemies[i][j-1] = enemies[i][j];
						}
						else if(j != 8 && enemies[i][j+1] == null && pieceIsEnemys(ai, board[i][j+1])) {
							enemies[i][j+1] = enemies[i][j];
						}
						enemies[i][j] = null;
					}
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(myPieces[i][j] != null && ! pieceIsMine(ai, board[i][j])) {
						if(pieceIsEnemys(ai, board[i][j])) {
							if(enemies[i][j].attacked)
								enemies[i][j].setProbabilities(-1);
							else
								enemies[i][j].setProbabilities(myPieces[i][j].rank);	
						}
						myPieces[i][j] = null;
					}
				}
			}	
		}
		for (int i = 0; i < 8; i++) 
		{
			System.out.print("\n|");
			for (int j = 0; j < 9; j++) 
			{
				if(this.myPieces[i][j] != null)
					System.out.print(String.format("A%2d|", myPieces[i][j].rank));
				else if(enemies[i][j] != null)
					System.out.print(String.format("E%2d|", enemies[i][j].mostProbable()));
				else
					System.out.print("   |");
			}
		}
		System.out.println();
	}

	private boolean defeats(int rank1, int rank2) {
		if(rank1 == 0 && rank2 == 13)
			return true;
		if(rank2 == 0 && rank1 == 13)
			return false;
		if(rank1 == 14) 
			return false;
		if(rank1 > rank2)
			return true;
		return false;
	}
	public boolean pieceIsMine(int ai, JButton button) {
		return button.getIcon().toString().contains(ai == 1? "red" : "blue");
	}

	public boolean pieceIsEnemys(int ai, JButton button) {
		return button.getIcon().toString().contains(ai == 1? "blue" : "red");
	}

	public boolean blank(JButton button) {
		return button.getIcon().toString().contains("brown");
	}
}// class DummyAgent