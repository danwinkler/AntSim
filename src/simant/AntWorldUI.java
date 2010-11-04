package simant;

import java.awt.RenderingHints;

import com.phyloa.dlib.dui.DButton;
import com.phyloa.dlib.dui.DUI;
import com.phyloa.dlib.dui.DUIElement;
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
		
		dui.add( new DButton( "Surface", 100, 0, 100, 30 ) );
		
		for( int i = 0; i < world.nests.size(); i++ )
		{
			dui.add( new DButton( "Nest " + (i+1), 200 + 100*i, 0, 100, 30 ) );
		}
	}

	public void update() 
	{
		g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON ); 
		g.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		
		dui.update();
		
		if( world != null && localGame )
		{
			world.update();
		}
		
		AntWorldRenderer.render( this, cameraLoc, getWidth(), getHeight() );
		dui.render( this );
		
		//Scroll
		if( !dui.isHover() )
		{
			if( m.x < 60 && cameraLoc.x > 0 && m.inside )
			{
				cameraLoc.x -= 2;
			}
			if( m.x > canvas.getWidth() - 60 && cameraLoc.x < world.width * world.xTileSize && m.inside )
			{
				cameraLoc.x += 2;
			}
			if( m.y < 60 && cameraLoc.y > 0 && m.inside )
			{
				cameraLoc.y -= 2;
			}
			if( m.y > canvas.getHeight() - 60 && cameraLoc.y < world.height * world.yTileSize && m.inside )
			{
				cameraLoc.y += 2;
			}
		}
		
		//Click on units or holes
	}

	public void event( DUIEvent e )
	{
		DUIElement element = e.getElement();
		if( element instanceof DButton )
		{
			DButton b = (DButton)element;
			if( b.getText().startsWith( "Nest" ) )
			{
				String[] parts = b.getText().split( " " );
				String num = parts[1].trim();
				int nestId = Integer.parseInt( num );
				Nest n = world.nests.get( nestId - 1 );
				cameraLoc.x = n.width * Nest.xTileSize * .5f;
				cameraLoc.y = n.height * Nest.yTileSize * .5f;
				cameraLoc.underground = true;
				cameraLoc.nest = n;
			}
			else if( b.getText().equals( "Surface" ) )
			{
				cameraLoc.x = world.width * world.xTileSize * .5f;
				cameraLoc.y = world.height * world.yTileSize * .5f;
				cameraLoc.underground = false;
				cameraLoc.nest = null;
			}
		}
	}
}
