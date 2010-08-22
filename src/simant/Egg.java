package simant;

public class Egg extends Ant
{
	int timeLeft;
	
	public Egg()
	{
		timeLeft = A.o.getI( "eggTimeLeft" );
	}
	
	public void update()
	{
		
	}
}
