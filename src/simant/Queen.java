package simant;

public class Queen extends Ant
{
	public static int EGG_TIME;
	public static int maxQueenHealth;
	
	State state = State.LAYING_EGGS;
	
	static
	{
		EGG_TIME = A.o.getI( "eggTime" );
		maxQueenHealth = A.o.getI( "queenHealth" );
	}
	
	public Queen( Team t )
	{
		super( t );
		health = maxQueenHealth;
	}

	public Queen( Team team, Location location )
	{
		this( team );
		this.loc = new Location( location );
	}
	
	int eggTimer = EGG_TIME;
	
	public void update()
	{
		if( eggTimer <= 0 )
		{
			t.units.add( new Egg( t, loc ) );
			eggTimer = EGG_TIME;
		}
		
		eggTimer--;
		
		switch( state )
		{
		case LAYING_EGGS:
			if( hunger < hungerMax/2 )
			{
				state = State.GETTING_FOOD;
				findFood();
			}
			else
			{
				//Walk around
			}
			break;
		case GETTING_FOOD:
			if( hunger > hungerMax/2 )
			{
				state = State.LAYING_EGGS;
			}
			else
			{
				if( targetLocation != null )
				{
					if( targetLocation.equals( this.loc ) )
					{
						findFood();
					}
				}
				else
				{
					findFood();
				}
				
				for( int i = 0; i < AntWorld.world.food.size(); i++ )
				{
					Food f = AntWorld.world.food.get( i );
					if( f.loc.equals( f.loc ) )
					{
						eatFood( f );
					}
				}
			}
			break;
		}
		
		super.update();
	}

	@Override
	public int getMaxHealth()
	{
		return maxQueenHealth;
	}
	
	enum State
	{
		LAYING_EGGS,
		GETTING_FOOD
	}
	
	public void findFood()
	{
		for( int i = 0; i < AntWorld.world.food.size(); i++ )
		{
			Food f = AntWorld.world.food.get( i );
			if( f.loc.nest == this.loc.nest )
			{
				targetLocation = new Location( f.loc );
				break;
			}
		}
	}
}
