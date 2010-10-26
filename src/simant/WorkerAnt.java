package simant;

import com.phyloa.dlib.util.DMath;

public class WorkerAnt extends Ant 
{
	boolean hasFood;
	Food target = null;
	
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
			if( target == null )
			{
				findTarget();
			}
			else
			{
				if( target.amt <= 0 )
				{
					findTarget();
				}
				
				if( loc.equals( target.loc ) )
				{
					target.amt--;
					target = null;
					hasFood = true;
				}
			}
		}
		else
		{
			//DROP OFF FOOD
			if( targetLocation != null )
			{
				if( targetLocation.nest != t.n || !targetLocation.underground )
				{
					targetLocation = new Location( t.n.width/2, t.n.height/2, t.n );
				}
			}
			else
			{
				targetLocation = new Location( t.n.width/2, t.n.height/2, t.n );
			}
			
			if( loc.equals( targetLocation ) )
			{
				AntWorld.world.food.add( new Food( loc ) );
				hasFood = false;
			}
		}
	}

	private void findTarget()
	{
		target = AntWorld.world.food.get( DMath.randomi( 0, AntWorld.world.food.size()-1 ) );
		targetLocation = target.loc;
	}
}
