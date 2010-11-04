package simant;

public class Egg extends Ant
{
	public static int hatchTime;
	public static int maxEggHealth;
	
	static
	{
		hatchTime = A.o.getI( "hatchTime" );
		maxEggHealth = A.o.getI( "eggHealth" );
	}
	
	int timeLeft;
	
	Ant toHatch;
	
	public Egg( Team t, Location loc )
	{
		super( t );
		timeLeft = hatchTime;
		this.loc = new Location( loc );
		double rand = Math.random();
		if( rand < 1.f/3.f )
		{
			toHatch = new WorkerAnt( t, loc );
		}
		else if( rand < 2.f/3.f )
		{
			toHatch = new WarriorAnt( t, loc );
		}
		else
		{
			toHatch = new NurseAnt( t, loc );
		}
	}
	
	public void update()
	{
		timeLeft--;
		if( timeLeft == 0 )
		{
			this.alive = false;
			t.units.add( toHatch );
		}
		hunger = 10000;
	}

	public int getMaxHealth()
	{
		return maxEggHealth;
	}
}
