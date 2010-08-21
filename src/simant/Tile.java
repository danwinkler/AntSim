package simant;

public class Tile 
{
	int type = 0; //0 dirt, 1 grass, 2 anthole
	boolean walkable = true;
	
	Nest nest;
	
	public boolean getClear()
	{
		return walkable;
	}
}
