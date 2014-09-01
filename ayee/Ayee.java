package ayee;

import java.util.ArrayList;

public class Ayee {

	Pieces myPieces;
	Pieces urPieces;

	private int 
		flag = 0, 	priv = 1,
		serg = 2, 	lut2 = 3,
		lut1 = 4, 	capt = 5,
		majr = 6, 	col1 = 7,
		col2 = 8, 	gen1 = 9,
		gen2 = 10, 	gen3 = 11,
		gen4 = 12, 	gen5 = 13,
		spyy = 14;

	public String placePieces() 
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

		myPieces = new Pieces(generatedPlaces);

		return myPieces.toString();
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
}