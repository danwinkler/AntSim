package simant;

import javax.vecmath.Point2f;

import com.phyloa.dlib.util.DMath;

public class Food
{
	public static int MAX_FOOD = 4;
	Location loc;
	int amt;
	
	public Food( float x, float y, int amt )
	{
		loc = new Location( x, y );
		this.amt = amt;
	}
	
	public Food( float x, float y )
	{
		this( x, y, DMath.randomi( 1, MAX_FOOD ) );
	}

	public boolean addFood( int amt )
	{
		if( amt >= 4 )
		{
			return false;
		}
		else
		{
			amt++;
			return true;
		}
	}
	
	public boolean removeFood( int amt )
	{
		if( amt <= 0 )
		{
			return false;
		}
		else
		{
			amt--;
			return true;
		}
	}
}
