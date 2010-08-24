package simant;

import java.util.ArrayList;

public abstract class Ant 
{
	Location loc;
	
	Location targetLocation;
	
	float heading;
	float speed;	
	int health;
	
	ArrayList<Location> path = new ArrayList<Location>();
	
	public Ant()
	{
		speed = A.o.getF( "antSpeed" );
	}
	
	public void update()
	{
		if( targetLocation != null )
		{
			pathFind();
		}
	}
	
	public void render()
	{
		
	}
	
	public void pathFind()
	{
		if( !(loc.underground || targetLocation.underground) )
		{
			//Both above ground
			
		}
		else if( loc.underground && !targetLocation.underground )
		{
			//NEED TO GET ABOVE GROUND
			nestPathFind( loc.nest.hole );
		}
		else if( !loc.underground && targetLocation.underground )
		{
			//NEED TO GET UNDERGROUND
		}
		else
		{
			if( loc.nest == targetLocation.nest )
			{
				//NAVIGATE through nest
				nestPathFind( targetLocation );
			}
			else
			{
				//NEED TO GET ABOVE GROUND
				nestPathFind( loc.nest.hole );
			}
		}
		
		if( !loc.equals( targetLocation ) && path != null )
		{
			if( path.size() > 0 )
			{
				Location next = path.get( path.size() - 1 );
				heading = (float) Math.atan2( next.x - loc.x, next.y - loc.y );
				
				loc.x += Math.cos( heading ) * speed;
				loc.y += Math.sin( heading ) * speed;
			}
		}
	}
	
	//-----------------------------------------------------------------------------------
	//BRUTE FORCE SEARCH ALGORITHM. COULD BE VASTLY IMPROVED
	//-----------------------------------------------------------------------------------
	
	public void nestPathFind( Location loc )
	{
		assert( loc.underground );
		path = distanceUnderground( loc, 0, new ArrayList<Location>() );
	}
	
	public ArrayList<Location> distanceUnderground( Location target, int currentDistance, ArrayList<Location> searched )
	{
		searched.add( target );
		ArrayList<Location> minDist = null;
		if( target.x < target.nest.width-1 )
		{
			Location searchLoc = new Location( target.x+1, target.y, target.nest );
			if( target.nest.getClear( target.x+1, target.y ) && !searched.contains( searchLoc ) )
			{
				ArrayList<Location> list = distanceUnderground( searchLoc, currentDistance + 1, searched );
				if( minDist != null )
				{
					if( minDist.size() > list.size() )
					{
						minDist = list;
					}
				}
				else
				{
					minDist = list;
				}
			}
		}
		if( target.y < target.nest.height-1 )
		{
			Location searchLoc = new Location( target.x, target.y+1, target.nest );
			if( target.nest.getClear( target.x, target.y+1 ) && !searched.contains( searchLoc ) )
			{
				ArrayList<Location> list = distanceUnderground( searchLoc, currentDistance + 1, searched );
				if( minDist != null )
				{
					if( minDist.size() > list.size() )
					{
						minDist = list;
					}
				}
				else
				{
					minDist = list;
				}
			}
		}
		if( target.x > 0 )
		{
			Location searchLoc = new Location( target.x-1, target.y, target.nest );
			if( target.nest.getClear( target.x-1, target.y ) && !searched.contains( searchLoc ) )
			{
				ArrayList<Location> list = distanceUnderground( searchLoc, currentDistance + 1, searched );
				if( minDist != null )
				{
					if( minDist.size() > list.size() )
					{
						minDist = list;
					}
				}
				else
				{
					minDist = list;
				}
			}
		}
		if( target.y >0 )
		{
			Location searchLoc = new Location( target.x, target.y-1, target.nest );
			if( target.nest.getClear( target.x, target.y-1 ) && !searched.contains( searchLoc ) )
			{
				ArrayList<Location> list = distanceUnderground( searchLoc, currentDistance + 1, searched );
				if( minDist != null )
				{
					if( minDist.size() > list.size() )
					{
						minDist = list;
					}
				}
				else
				{
					minDist = list;
				}
			}
		}
		
		if( minDist != null )
		{
			minDist.add( target );
		}
		
		return minDist;
	}
	
	public void surfacePathFind( Location loc )
	{
		assert( !loc.underground );
	}
}
