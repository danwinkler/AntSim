package simant;

public class Queen extends Ant
{
	public Queen( Team t )
	{
		super( t );
	}

	public static int EGG_TIME;
	
	static
	{
		A.o.getI( "eggTime" );
	}
	
	int eggTimer = 0;
	
	public void update()
	{
		if( eggTimer <= 0 )
		{
			t.units.add( new Egg( t ) );
		}
		
		eggTimer--;
		
		super.update();
	}
}
