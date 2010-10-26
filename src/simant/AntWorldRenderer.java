package simant;

import java.io.IOException;
import java.util.ArrayList;

import com.phyloa.dlib.renderer.Renderer;
import com.phyloa.dlib.util.DFile;
import com.phyloa.dlib.util.DGraphics;

public class AntWorldRenderer 
{
	//Render the antworld using the passed Renderer and viewport, centered around the loc
	public static void render( Renderer r, Location loc, int width, int height )
	{
		r.color( 0, 0, 0 );
		r.fillRect( 0, 0, width, height );
		
		r.pushMatrix();
		
		//Render ground
		AntWorld w = AntWorld.world;
		float minX = loc.x - width/2;
		float minY = loc.y - height/2;
		float maxX = loc.x + width/2;
		float maxY = loc.y + height/2;
		
		r.translate( -loc.x + width/2, -loc.y + height/2 );
		if( loc.underground )
		{
			Nest nest = loc.nest;
			int minTileX = (int) Math.max( minX / Nest.xTileSize, 0 );
			int minTileY = (int) Math.max( minY / Nest.yTileSize, 0 );
			int maxTileX = (int) Math.min( maxX / Nest.xTileSize, nest.width-1 );
			int maxTileY = (int) Math.min( maxY / Nest.yTileSize, nest.height-1 );
			
			for( int x = minTileX; x <= maxTileX; x++ )
			{
				for( int y = minTileY; y <= maxTileY; y++ )
				{
					//TODO: Texture stuff
					int type = nest.tiles[x][y];
					switch( type )
					{
					case 0: r.drawImage( AntImage.getUnderground( 0 ), x*Nest.xTileSize, y*Nest.yTileSize ); break;
					case 1: r.drawImage( AntImage.getUnderground( 1 ), x*Nest.xTileSize, y*Nest.yTileSize ); break;
					case 2: r.drawImage( AntImage.getUnderground( 2 ), x*Nest.xTileSize, y*Nest.yTileSize ); break;
					}
				}
			}
		}
		else
		{
			int minTileX = (int) Math.max( minX / w.xTileSize, 0 );
			int minTileY = (int) Math.max( minY / w.yTileSize, 0 );
			int maxTileX = (int) Math.min( maxX / w.xTileSize, w.width-1 );
			int maxTileY = (int) Math.min( maxY / w.yTileSize, w.height-1 );
			
			for( int x = minTileX; x <= maxTileX; x++ )
			{
				for( int y = minTileY; y <= maxTileY; y++ )
				{
					int type = w.tiles[x][y].type;
					switch( type )
					{
					case 0: r.drawImage( AntImage.getTerrain( 0 ), x*w.xTileSize, y*w.yTileSize ); break;
					case 1: r.drawImage( AntImage.getTerrain( 1 ), x*w.xTileSize, y*w.yTileSize ); break;
					case 2: r.drawImage( AntImage.getTerrain( 2 ), x*w.xTileSize, y*w.yTileSize ); break;
					}
				}
			}
		}
		
		//Render food
		r.color( 0, 255, 0 );
		for( int i = 0; i < w.food.size(); i++ )
		{
			Food f = w.food.get( i );
			if( f.loc.underground == loc.underground )
			{
				if( f.loc.nest == loc.nest )
				{
					switch( f.amt )
					{
					case 4: r.fillOval( f.loc.x, f.loc.y, 5, 5 );
					case 3: r.fillOval( f.loc.x - 5, f.loc.y, 5, 5 );
					case 2: r.fillOval( f.loc.x, f.loc.y - 5, 5, 5 );
					case 1: r.fillOval( f.loc.x - 5, f.loc.y - 5, 5, 5 );
					}
				}
			}
		}
		
		//Render ants
		r.color( 0, 0, 0 );
		for( int i = 0; i < w.teams.size(); i++ )
		{
			Team t = w.teams.get( i );
			for( int j = 0; j < t.units.size(); j++ )
			{
				Ant a = t.units.get( j );
				if( a.loc.underground == loc.underground )
				{
					if( a.loc.nest == loc.nest )
					{
						r.fillOval( a.loc.x-5, a.loc.y-5, 10, 10 );
					}
				}
			}
		}
		
		r.popMatrix();
	}
	
}
