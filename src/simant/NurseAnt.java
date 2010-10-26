package simant;

public class NurseAnt extends Ant
{	
	public NurseAnt( Team t )
	{
		super( t );
		health = A.o.getI( "nurseHealth" );
	}
	
	public void update()
	{
		
	}
}
