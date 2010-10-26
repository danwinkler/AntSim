package simant;

public class WorkerAnt extends Ant 
{
	boolean hasFood;
	
	public WorkerAnt( Team t )
	{
		super( t );
		health = A.o.getI( "workerHealth" );
	}
	
	public void update()
	{
		if( onAuto )
		{
			findFood();
		}
		
		super.update();
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
