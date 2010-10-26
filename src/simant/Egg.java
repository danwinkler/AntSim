package simant;

public class Egg extends Ant
{
	public static int hatchTime;
	
	static
	{
		hatchTime = A.o.getI( "hatchTime" );
	}
	
	int timeLeft;
	
	public Egg( Team t )
	{
		super( t );
		timeLeft = hatchTime;
	}
	
	public void update()
	{
		timeLeft--;
		if( timeLeft <= 0 )
		{
			this.alive = false;
		}
	}
}
