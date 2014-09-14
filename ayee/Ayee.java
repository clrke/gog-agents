package bscs.ayee;

import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Method;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ayee
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

		if(iAI == 1)
		{
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 7; j++) {
					Board[5+i][1+j].setIcon(images[myPieces[i][j]]);
				}
			}
		}// if iAI == 1
		else
		{
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 7; j++) {
					Board[2-i][1+j].setIcon(images[myPieces[i][j]]);
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

		if(iAI == 1)
		{
			/*
				Move of AI 1 is bottom up (optional) since AI 1's piece is on the lower part of the board
			*/
		}// if iAI == 1
		else
		{
			/*
				Move of AI 2 is top bottom (optional) since AI 2's piece is on the upper part of the board
			*/
		}// if iAI == 2

		/*
			EXAMPLE

				Move[0][0] = 0;
				Move[0][1] = 0;

				Move[1][0] = 1;
				Move[1][1] = 0;


				The piece on Board[Move[0][0]][Move[0][1]] will be place in Board[Move[1][0]][Move[1][1]]
		*/

		return Move;
	}// AIMove

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
}// class DummyAgent