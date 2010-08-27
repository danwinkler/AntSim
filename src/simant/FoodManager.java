package simant;

import javax.vecmath.Point2f;

public class FoodManager
{
	static float foodRad;
	static float foodAmt;
	Point2f foodLoc;
	
	static 
	{
		foodRad = A.o.getF( "foodRad" );
		foodAmt = A.o.getF( "foodAmt" );
	}
}
