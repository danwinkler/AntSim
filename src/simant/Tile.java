package simant;

public class Tile 
{
	int type = 0; //0 dirt, 1 grass, 2 anthole
	boolean walkable = true;
	
	Nest nest;
	
	public Tile()
	{
		
	}
	
	public Tile( int type )
	{
		this.type = type;
	}
	
	public Tile( Nest nest )
	{
		this.type = 2;
		this.nest = nest;
	}
	
	public boolean getClear()
	{
		return walkable;
	}
}
