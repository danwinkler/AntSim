package simant;

import javax.vecmath.Point2f;

public class Location extends Point2f
{
	/**
	 * 
	 */
	public static float equalsDist = -1f; 
	
	private static final long serialVersionUID = -3030289780766304308L;
	
	boolean underground;
	Nest nest;
	
	static 
	{
			equalsDist = A.o.getF( "locationEqualsDist" );
	}
	
	public Location()
	{
		super();
		underground = false;
	}
	
	public Location( Location loc )
	{
		if( loc != null )
		{
			this.x = loc.x;
			this.y = loc.y;
			this.underground = loc.underground;
			this.nest = loc.nest;
		}
	}
	
	public Location( float x, float y )
	{
		super( x, y );
	}
	
	public Location( float x, float y, Nest nest )
	{
		super( x, y );
		this.nest = nest;
		underground = true;
	}
	
	public Location( Point2f loc, Nest nest )
	{
		this( loc.x, loc.y, nest );
	}
	
	public boolean equals( Location location )
	{
		if( underground )
		{
			return nest.equals( location.nest ) && this.distance( location ) < equalsDist;
		}
		else
		{
			return this.distance( location ) < equalsDist;
		}
	}
	
	public void set( Location loc )
	{
		this.x = loc.x;
		this.y = loc.y;
		this.underground = loc.underground;
		this.nest = loc.nest;
	}
}
