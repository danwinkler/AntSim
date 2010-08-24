package simant;

import javax.vecmath.Point2f;

public class Food
{
	public static int MAX_FOOD = 4;
	Point2f loc;
	int amt;
	
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
