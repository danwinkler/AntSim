package simant;

import java.util.ArrayList;

import com.phyloa.dlib.renderer.Renderer;
import com.phyloa.dlib.util.DMath;
import com.phyloa.dlib.util.DOptions;

public class AntWorld
{
	//Singleton
	public static AntWorld world;
	
	int width;
	int height;
	Tile[][] tiles;
	ArrayList<Team> teams;
	ArrayList<Nest> nests;
	
	float xTileSize;
	float yTileSize;
	
	ArrayList<Food> food;
	
	public void update()
	{
		if( food.size() < 10 )
		{
			float x = DMath.randomf( 0, width * xTileSize );
			float y = DMath.randomf( 0, height * yTileSize );
			int amt = A.o.getI( "foodAmt" );
			float foodRad = A.o.getF( "foodRad" );
			for( int i = 0; i < amt; i++ )
			{
				float a = DMath.randomf( 0, (float)(Math.PI*2) );
				float mag = DMath.randomf( 0, foodRad );
				float dx = DMath.cosf( a ) * mag;
				float dy = DMath.sinf( a ) * mag;
				float xx = x + dx;
				float yy = y + dy;
				int foodQuant = (int)(Food.MAX_FOOD - ((mag / foodRad) * (Food.MAX_FOOD - 1)));
				if( xx > 0 && xx < width * (xTileSize-1) && yy > yTileSize && yy < height * (yTileSize-1) )
				{
					Tile t = tiles[(int)(xx/xTileSize)][(int)(yy/yTileSize)];
					if( t.type == 0 )
					{
						food.add( new Food( x + dx, y + dy, foodQuant ) );
					}
				}
			}
		}
		
		for( int i = 0; i < teams.size(); i++ )
		{
			teams.get( i ).update( this );
		}
	}
	
	private AntWorld( int teamCount )
	{
		width = A.o.getI( "surfaceWidth" );
		height = A.o.getI( "surfaceHeight" );
		xTileSize = A.o.getF( "surfaceXTileSize" );
		yTileSize = A.o.getF( "surfaceYTileSize" );
		tiles = new Tile[width][height];
		
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				tiles[x][y] = new Tile( DMath.randomi( 0, 1 ) );
			}
		}
		
		teams = new ArrayList<Team>();
		nests = new ArrayList<Nest>();
		food = new ArrayList<Food>();
		
		for( int i = 0; i < teamCount; i++ )
		{
			int side =  i < (teamCount/2) ? 0 : 1;
			teams.add( new Team( i, side ) );
			Nest n = new Nest();
			nests.add( n );
			
			while( true )
			{
				int xx = DMath.randomi(0, (width/2) - 1) + (side == 1 ? width/2 : 0);
				int yy = DMath.randomi(0, height-1);
				Tile t = tiles[xx][yy];
				if( t.type != 2 )
				{
					tiles[xx][yy] = new Tile( n );
					n.surfaceLoc = new Location( (xx*xTileSize) + xTileSize/2, (yy*yTileSize) + yTileSize/2 );
					break;
				}
			}
		}
	}
	
	public static AntWorld createWorld( int teamCount )
	{
		world = new AntWorld( teamCount );
		return world;
	}
	
	public static void setWorld( AntWorld w )
	{
		world = w;
	}
	
	public boolean getClear( int x, int y )
	{
		return tiles[x][y].getClear();
	}
}
