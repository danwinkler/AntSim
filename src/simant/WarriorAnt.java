package simant;

public class WarriorAnt extends Ant
{
	public static int maxWarriorHealth;
	
	static
	{
		maxWarriorHealth = A.o.getI( "warriorHealth" );
	}
	
	public WarriorAnt( Team t )
	{
		super( t );
		health = maxWarriorHealth;
	}
	
	public WarriorAnt( Team t, Location loc )
	{
		this( t );
		this.loc = new Location( loc );
	}
	
	public void update()
	{
		
	}

	@Override
	public int getMaxHealth()
	{
		return maxWarriorHealth;
	}
}
