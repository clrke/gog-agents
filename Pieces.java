package sample17;

class Pieces 
{
	public int[][] data;

	public Pieces() 
	{

	}
	public Pieces(int[][] data) 
	{
		this.data = data;
	}

	public String toString() 
	{
		String string = "";

		for (int i = 0; i < data.length; i++) 
		{
			string += "\n|";
			for (int j = 0; j < data[i].length; j++)
				string += String.format("%2d|", data[i][j]);
		}

		return string;
	}
}