package simant;

public class NurseAnt extends Ant
{
	
	boolean onAuto = true;
	
	public NurseAnt()
	{
		super();
		health = AntWorld.world.options.getI( "nurseHealth" );
	}
	
	public void update()
	{
		
	}
}
