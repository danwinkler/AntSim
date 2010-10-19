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
	
	boolean localGame = false;
	
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
		
		cameraLoc.x = (world.width * world.xTileSize) / 2;
		cameraLoc.y = (world.height * world.yTileSize) / 2;
		
		for( int i = 0; i < world.nests.size(); i++ )
		{
			dui.add( new DButton( "Nest " + (i+1), 100 + 100*i, 0, 100, 30 ) );
		}
	}

	public void update() 
	{
		dui.update();
		
		if( world != null && localGame )
		{
			world.update();
		}
		
		AntWorldRenderer.render( this, cameraLoc, getWidth(), getHeight() );
		dui.render( this );
		
		if( !dui.isHover() )
		{
			if( m.x < 60 && cameraLoc.x > 0 )
			{
				cameraLoc.x -= 2;
			}
			if( m.x > canvas.getWidth() - 60 && cameraLoc.x < world.width * world.xTileSize  )
			{
				cameraLoc.x += 2;
			}
			if( m.y < 60 && cameraLoc.y > 0 )
			{
				cameraLoc.y -= 2;
			}
			if( m.y > canvas.getHeight() - 60 && cameraLoc.y < world.height * world.yTileSize )
			{
				cameraLoc.y += 2;
			}
		}
	}

	public void event( DUIEvent e )
	{
		
	}
}
