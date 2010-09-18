package simant;

import com.phyloa.dlib.renderer.Renderer;
import com.phyloa.dlib.util.DGraphics;

public class AntWorldRenderer 
{
	//Render the antworld using the passed Renderer and viewport, centered around the loc
	public static void render( Renderer r, Location loc, int width, int height )
	{
		//Render ground
		AntWorld w = AntWorld.world;
		float minX = loc.x - width/2;
		float minY = loc.y - height/2;
		float maxX = loc.x + width/2;
		float maxY = loc.y - height/2;
		if( loc.underground )
		{
			Nest nest = loc.nest;
			int minTileX = (int) Math.max( minX / Nest.xTileSize, 0 );
			int minTileY = (int) Math.max( minY / Nest.yTileSize, 0 );
			int maxTileX = (int) Math.min( maxX / Nest.xTileSize, nest.width-1 );
			int maxTileY = (int) Math.min( maxY / Nest.yTileSize, nest.height-1 );
			
			for( int x = minTileX; x < maxTileX; x++ )
			{
				for( int y = minTileY; y < maxTileY; y++ )
				{
					//TODO: Texture stuff
					if( nest.tiles[x][y] == 0)
					{
						r.color( DGraphics.rgb( 150, 60, 20 ) );
					}
					else
					{
						r.color( DGraphics.rgb( 200, 100, 40 ) );
					}
					r.fillRect( x*Nest.xTileSize, y*Nest.yTileSize, Nest.xTileSize, Nest.yTileSize );
				}
			}
		}
		else
		{
			int minTileX = (int) Math.max( minX / w.xTileSize, 0 );
			int minTileY = (int) Math.max( minY / w.yTileSize, 0 );
			int maxTileX = (int) Math.min( maxX / w.xTileSize, w.width-1 );
			int maxTileY = (int) Math.min( maxY / w.yTileSize, w.height-1 );
			
			for( int x = minTileX; x < maxTileX; x++ )
			{
				for( int y = minTileY; y < maxTileY; y++ )
				{
					//TODO: Texture stuff
					int type = w.tiles[x][y].type;
					if( type == 0 )
					{
						r.color( DGraphics.rgb( 150, 60, 20 ) );
					}
					else
					{
						r.color( DGraphics.rgb( 200, 100, 40 ) );
					}
					r.fillRect( x*w.xTileSize, y*w.yTileSize, w.xTileSize, w.yTileSize );
				}
			}
		}
		
		//Render ants
		
	}
	
}
