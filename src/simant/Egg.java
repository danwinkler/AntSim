package simant;

public class Egg extends Ant
{
	int timeLeft;
	
	public Egg()
	{
		timeLeft = AntWorld.world.options.getI( "eggTimeLeft" );
	}
	
	public void update()
	{
		
	}
}
