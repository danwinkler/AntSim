package simant;

public class WarriorAnt extends Ant
{
	public WarriorAnt( Team t )
	{
		super( t );
		health = A.o.getI( "warriorHealth" );
	}
	
	public void update()
	{
		
	}
}
