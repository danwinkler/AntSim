package simant;

import com.phyloa.dlib.renderer.Graphics2DRenderer;

public class AntWorldUI extends Graphics2DRenderer
{
	public AntWorld world;
	
	AntWorldModifier mod;
	
	public AntWorldUI( AntWorldModifier mod )
	{
		this.mod = mod;
	}
	
	public void initialize() 
	{
		size( 800, 600 );
	}

	public void update() 
	{
		if( world != null )
		{
			world.update();
		}
	}
}
