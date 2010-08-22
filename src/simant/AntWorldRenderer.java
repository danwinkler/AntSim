package simant;

import com.phyloa.dlib.renderer.Renderer;
import com.phyloa.dlib.util.DGraphics;

public class AntWorldRenderer 
{
	//Render the antworld using the passed Renderer and viewport, centered around the loc
	public static void render( Renderer r, Location loc, int width, int height )
	{
		AntWorld w = AntWorld.world;
		float minX = loc.x - width/2;
		float minY = loc.y - height/2;
		float maxX = loc.x + width/2;
		float maxY = loc.y - height/2;
		if( loc.underground )
		{
			Nest nest = loc.nest;
			int minTileX = (int) Math.max( minX / nest.xTileSize, 0 );
			int minTileY = (int) Math.max( minY / nest.xTileSize, 0 );
			int maxTileX = (int) Math.min( maxX / nest.xTileSize, nest.width-1 );
			int maxTileY = (int) Math.min( maxY / nest.xTileSize, nest.height-1 );
			
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
					r.fillRect( x*nest.xTileSize, y*nest.yTileSize, nest.xTileSize, nest.yTileSize );
				}
			}
		}
	}
	
}
