package simant;

import com.phyloa.dlib.dui.DButton;
import com.phyloa.dlib.dui.DUI;
import com.phyloa.dlib.dui.DUIEvent;
import com.phyloa.dlib.dui.DUIListener;
import com.phyloa.dlib.renderer.Graphics2DRenderer;

public class AntWorldUI extends Graphics2DRenderer implements DUIListener
{
	public AntWorld world;
	
	AntWorldModifier mod;
	
	DUI dui;
	
	DButton menu;
	
	Location cameraLoc = new Location();
	
	public AntWorldUI( AntWorldModifier mod, AntWorld world )
	{
		this.mod = mod;
		this.world = world;
	}
	
	public void initialize() 
	{
		size( 800, 600 );
		
		dui = new DUI( canvas );
		
		dui.addDUIListener( this );
		
		menu = new DButton( "Menu", 0, 0, 100, 30 );
		dui.add( menu );
	}

	public void update() 
	{
		dui.update();
		
		if( world != null )
		{
			world.update();
		}
		
		AntWorldRenderer.render( this, cameraLoc, getWidth(), getHeight() );
		dui.render( this );
	}

	public void event( DUIEvent e )
	{
		
	}
}
