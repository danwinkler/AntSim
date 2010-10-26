package simant;

import java.util.ArrayList;

public class Team 
{
	int color;
	ArrayList<Ant> units = new ArrayList<Ant>();
	int side = 0; //0 left, 1 right
	Nest n;
	
	public Team()
	{
		
	}
	
	public Team( int color )
	{
		this();
		this.color = color;
	}
	
	public Team( int color, int side )
	{
		this( color );
		this.side = side;
	}

	public void update( AntWorld antWorld )
	{
		for( int i = 0; i < units.size(); i++ )
		{
			units.get( i ).update();
		}
	}
}
