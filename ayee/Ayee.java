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
			1.
			2.
			3.
			4.
		ALGORITHM USED
			1.
			2.
			3.
	*/

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
		if(iAI == 1)
		{
			/*
				Add your red pieces here
				Player 1 is on the lower part of the board
			*/
		}// if iAI == 1
		else
		{
			/*
				Add your blue pieces here
				Player 2 is on the upper part of the board
			*/
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
}// class DummyAgent