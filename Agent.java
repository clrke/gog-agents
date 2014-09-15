package sample17;

public class Agent {
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

	public String placePieces() {
		myPieces = new Pieces(new int[][] {
			{gen5, col2, priv, majr, capt, priv, col1},
			{gen3, lut1, priv, priv, spyy, priv, gen4},
			{gen1, lut2, spyy, flag, serg, priv, gen2}
		});

		return myPieces.toString();
	}
}