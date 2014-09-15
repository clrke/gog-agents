package sample17;

import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Method;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Agent {
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
			for(int i = 1; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					if(pieceIsMine(iAI, Board[i][j]) && ! pieceIsMine(iAI, Board[i-1][j]))
					{
						return new int[][] {
							{i, j},
							{i-1, j}
						};
					}
				}
			}
		}// if iAI == 1
		else
		{
			for(int i = 6; i >= 0; i--) {
				for (int j = 8; j >= 0; j--) {
					if(pieceIsMine(iAI, Board[i][j]) && ! pieceIsMine(iAI, Board[i+1][j]))
					{
						return new int[][] {
							{i, j},
							{i+1, j}
						};
					}
				}
			}
		}// if iAI == 2


		return Move;
	}// AIMove

    public int[][] placePieces() {
	    Pieces myPieces = new Pieces(new int[][] {
	        {gen5, col2, priv, majr, capt, priv, col1},
	        {gen3, lut1, priv, priv, spyy, priv, gen4},
	        {gen1, lut2, spyy, flag, serg, priv, gen2}
    	});

        return myPieces.data;
	}

	public boolean pieceIsMine(int ai, JButton button) {
		return button.getIcon().toString().contains(ai == 1? "red" : "blue");
	}

	public boolean blank(JButton button) {
		return button.getIcon().toString().contains("brown");
	}
}