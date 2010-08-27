package simant;

import java.util.ArrayList;

public class Team 
{
	int color;
	ArrayList<Ant> units = new ArrayList<Ant>();
	
	public Team()
	{
		
	}
	
	public Team( int color )
	{
		this.color = color;
	}
}
