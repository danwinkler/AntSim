package simant;

import java.util.ArrayList;

public abstract class Ant 
{
	public static int hungerMax;
	public static float fightDistance;
	
	static
	{
		hungerMax = A.o.getI( "antHungerTime" );
		fightDistance = A.o.getF( "fightDistance" );
	}
	
	Location loc;
	
	Location targetLocation;
	
	float heading;
	float speed;	
	int health;
	int hunger;
	boolean alive = true;
	
	boolean onAuto = true;
	
	ArrayList<Location> path = null;
	
	Team t;
	
	SuperState ss = SuperState.IDLE;
	
	public Ant( Team t )
	{
		speed = A.o.getF( "antSpeed" );
		hunger = hungerMax;
		loc = new Location();
		this.t = t;
	}
	
	public void update()
	{
		if( health > 0 && hunger > 0 )
		{
			if( ss == SuperState.IDLE )
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
									float dist = loc.distanceSquared( point );
									if( dist < speed * speed )
									{
										loc.set( point );
									}
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
			
			//FIGHT!
			AntWorld w = AntWorld.world;
			ss = SuperState.IDLE;
			stopFighting:
			for( int i = 0; i < w.teams.size(); i++ )
			{
				Team team = w.teams.get( i );
				if( team != this.t )
				{
					for( int j = 0; j < team.units.size(); j++ )
					{
						Ant a = team.units.get( j );
						if( a.loc.underground == this.loc.underground )
						{
							if( a.loc.nest == this.loc.nest || !this.loc.underground )
							{
								float distance = a.loc.distance( this.loc );
								if( distance < fightDistance )
								{
									fight( a );
									break stopFighting;
								}
							}
						}
					}
				}
			}
		}
		
		hunger--;
		if( hunger <= 0 )
		{
			alive = false;
			AntWorld.world.renderEvents.add( new RenderEvent( RenderEventType.ANT_STARVE, t, loc ) );
		}
		else if( health <= 0 )
		{
			alive = false;
			AntWorld.world.renderEvents.add( new RenderEvent( RenderEventType.ANT_DIE, t, loc ) );
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
	
	public void eatFood( Food f )
	{
		hunger = hungerMax;
		health = getMaxHealth();
		f.amt--;
	}
	
	public void fight( Ant a )
	{
		a.health--;
		this.health--;
		ss = SuperState.FIGHTING;
	}
	
	public abstract int getMaxHealth();
	
	enum SuperState
	{
		IDLE,
		FIGHTING
	}
}
