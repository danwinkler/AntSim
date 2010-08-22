package simant;

public class WorkerAnt extends Ant 
{
	boolean hasFood;
	
	boolean onAuto = true;
	
	public WorkerAnt()
	{
		super();
		health = A.o.getI( "workerHealth" );
	}
	
	public void update()
	{
		if( onAuto )
		{
			findFood();
		}
	}
	
	public void findFood()
	{
		if( !hasFood )
		{
			//GO FIND FOOD
			
		}
		else
		{
			//DROP OFF FOOD
		}
	}
}
