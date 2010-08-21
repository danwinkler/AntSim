package simant;

public class WarriorAnt extends Ant
{
	public WarriorAnt()
	{
		super();
		health = AntWorld.world.options.getI( "warriorHealth" );
	}
	
	public void update()
	{
		
	}
}
