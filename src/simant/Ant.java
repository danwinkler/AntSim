package simant;

import java.util.ArrayList;

public abstract class Ant 
{
	Location loc;
	
	Location targetLocation;
	
	float heading;
	float speed;	
	int health;
	boolean alive = true;
	
	boolean onAuto = true;
	
	ArrayList<Location> path = null;
	
	Team t;
	
	public Ant( Team t )
	{
		speed = A.o.getF( "antSpeed" );
		loc = new Location();
		this.t = t;
	}
	
	public void update()
	{
		if( targetLocation != null )
		{
			if( !loc.equals( targetLocation ) )
			{
				if( path == null )
				{
					pathFind();
				}
				else
				{
					if( path.size() == 0 )
					{
						pathFind();
					}
					else
					{
						Location point = path.get( 0 );
						if( !loc.equals( point ) )
						{
							moveTowards( point );
						}
						else
						{
							path.remove( 0 );
						}
					}
				}
			}
		}
	}
	
	public void pathFind()
	{
		path = new ArrayList<Location>();
		if( loc.underground )
		{
			if( targetLocation.underground )
			{
				if( loc.nest == targetLocation.nest )
				{
					undergroundPathAdd( targetLocation );
				}
				else
				{
					if( loc.equals( loc.nest.hole ) )
					{
						loc = new Location( loc.nest.surfaceLoc );
					}
					else
					{
						undergroundPathAdd( loc.nest.hole );
					}
				}
			}
			else
			{
				undergroundPathAdd( loc.nest.hole );
			}
		}
		else
		{
			if( targetLocation.underground )
			{
				if( loc.equals( targetLocation.nest.surfaceLoc ) )
				{
					loc = new Location( targetLocation.nest.hole );
				}
				else
				{
					surfacePathAdd( targetLocation.nest.surfaceLoc );
				}
			}
			else
			{
				surfacePathAdd( targetLocation );
			}
		}
	}
	
	private void undergroundPathAdd( Location targetLocation2 )
	{
		//TODO: actually do pathfinding
		if( targetLocation2 != null )
		{
			path.add( new Location( targetLocation2 ) );
		}
		else
		{
			System.err.println( "undergroundPathAdd was handed a null Location" );
		}
	}
	
	private void surfacePathAdd( Location targetLocation2 )
	{
		//TODO: actually do pathfinding
		if( targetLocation2 != null )
		{
			path.add( new Location( targetLocation2 ) );
		}
		else
		{
			System.err.println( "surfacePathAdd was handed a null Location" );
		}
	}

	public void moveTowards( Location next )
	{
		if( next != null )
		{
			heading = (float) Math.atan2( next.y - loc.y, next.x - loc.x );
	
			loc.x += Math.cos( heading ) * speed;
			loc.y += Math.sin( heading ) * speed;
		}
		else
		{
			System.err.println( "moveTowards was handed a null Location" );
		}
	}
}
