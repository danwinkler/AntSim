package simant;

public class NurseAnt extends Ant
{
	
	boolean onAuto = true;
	
	public NurseAnt()
	{
		super();
		health = A.o.getI( "nurseHealth" );
	}
	
	public void update()
	{
		
	}
}
