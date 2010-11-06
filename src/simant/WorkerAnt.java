package simant;

import com.phyloa.dlib.util.DMath;

public class WorkerAnt extends Ant 
{
	public static int maxWorkerHealth;
	
	static
	{
		maxWorkerHealth = A.o.getI( "workerHealth" );
	}
	
	boolean hasFood;
	Food target = null;
	
	enum State
	{
		IDLE,
		GETTING_FOOD,
		RETURNING_WITH_FOOD,
		ATTACKING,
		DIGGING
	}
	
	State state = State.IDLE;
	
	public WorkerAnt( Team t )
	{
		super( t );
		health = maxWorkerHealth;
	}
	
	public WorkerAnt( Team t, Location loc )
	{
		this( t );
		this.loc = new Location( loc );
	}

	public void update()
	{
		if( onAuto )
		{
			findFood();
		}
		
		super.update();
	}
	
	public void findFood()
	{
		if( onAuto )
		{
			switch( state )
			{
			case IDLE:
				findFoodTarget();
				state = State.GETTING_FOOD;
				break;
			case GETTING_FOOD:
				if( target == null )
				{
					findFoodTarget();
				}
				else
				{
					if( target.amt <= 0 )
					{
						findFoodTarget();
					}
					else if( loc.equals( target.loc ) )
					{
						target.amt--;
						target = null;
						hasFood = true;
						state = State.RETURNING_WITH_FOOD;
						findDropOffLocation();
					}
				}
				break;
			case RETURNING_WITH_FOOD:
				if( hunger < hungerMax/2 )
				{
					state = State.IDLE;
					hasFood = false;
				}
				if( loc.equals( targetLocation ) )
				{
					AntWorld.world.food.add( new Food( loc ) );
					hasFood = false;
					state = State.IDLE;
				}
			}
		}
	}

	private void findFoodTarget()
	{
		target = AntWorld.world.food.get( DMath.randomi( 0, Math.max( AntWorld.world.food.size()-1, 0 ) ) );
		if( target.loc.underground )
		{
			target = null;
		}
		else
		{
			targetLocation = target.loc;
		}
	}
	
	private void findDropOffLocation()
	{
		targetLocation = null;
		escape:
		for( int y = 0; y < t.n.height; y++ )
		{
			for( int x = 0; x < t.n.width; x++ )
			{
				if( t.n.tiles[x][y] == 1 /*AIR*/ )
				{
					boolean clear = true;
					Location tileLoc = new Location( (x * Nest.xTileSize) + Nest.xTileSize/2, (y * Nest.yTileSize) + Nest.yTileSize/2, t.n );
					for( int i = 0; i < AntWorld.world.food.size(); i++ )
					{
						Food f = AntWorld.world.food.get( i );
						if( f.loc.underground && f.loc.nest == t.n )
						{
							if( f.loc.equals( tileLoc ) )
							{
								clear = false;
								break;
							}
						}
					}
					
					if( clear )
					{
						targetLocation = new Location( tileLoc );
						break escape;
					}
				}
			}
		}
	
		if( targetLocation == null )
		{
			targetLocation = new Location( DMath.randomf( 0, Nest.xTileSize * t.n.width ), DMath.randomf( 0, Nest.yTileSize * t.n.height ), t.n );
		}
	}

	@Override
	public int getMaxHealth()
	{
		return maxWorkerHealth;
	}
}
