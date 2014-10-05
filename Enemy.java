package ayee;

import java.util.ArrayList;

class Enemy extends Piece {
	private int[] probability = new int[15];
	public boolean attacked = false;
	public int attacker = 0;

	public Enemy() {
		for (int i = 0; i < 15; i++) {
			probability[i] = 1;
		}

		this.rank = -1;
	}

	public void setProbabilities(int opponent) {
		if(opponent == 0)
			probability[13] = 0;
		else if(opponent == 13)
			for (int i = 1; i < 15; i++) {
				probability[i] = 0;
			}
		else for (int i = 0; i < opponent; i++) {
			probability[i] = 0;
		}

		if(attacked)
		{
			opponent = attacker;

			if(opponent == 0)
				probability[13] = 0;
			else if(opponent == 13)
				for (int i = 1; i < 15; i++) {
					probability[i] = 0;
				}
			else for (int i = 0; i < opponent; i++) {
				probability[i] = 0;
			}

			attacked = false;
			attacker = 0;
		}
	}

	public int mostProbable() {
		if(probability[1] > 0)
			return 0;
		else if(probability[10] > 0)
			return 12;
		else if(probability[13] > 0)
			return 13;
		else
			for (int i = 0; i < 15; i++) {
				if(probability[i] > 0)
					return i;
			}

		return 14;
	}
}