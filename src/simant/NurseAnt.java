package simant;

public class NurseAnt extends Ant
{	
	public static int maxNurseHealth;
	
	static
	{
		maxNurseHealth = A.o.getI( "nurseHealth" );
	}
	
	public NurseAnt( Team t )
	{
		super( t );
		health = maxNurseHealth;
	}
	
	public NurseAnt( Team t, Location loc )
	{
		this( t );
		this.loc = new Location( loc );
	}
	
	public void update()
	{
		
	}

	public int getMaxHealth()
	{
		return maxNurseHealth;
	}
}
