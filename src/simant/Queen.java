package simant;

public class Queen extends Ant
{
	public static int EGG_TIME;
	public static int maxQueenHealth;
	
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
		
		super.update();
	}

	@Override
	public int getMaxHealth()
	{
		return maxQueenHealth;
	}
}
