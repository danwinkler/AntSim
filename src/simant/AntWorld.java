package simant;

import java.util.ArrayList;

import com.phyloa.dlib.renderer.Renderer;
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
		
	}
	
	private AntWorld()
	{
		width = A.o.getI( "surfaceWidth" );
		height = A.o.getI( "surfaceHeight" );
		xTileSize = A.o.getF( "surfaceXTileSize" );
		yTileSize = A.o.getF( "surfaceYTileSize" );
		tiles = new Tile[width][height];
		
		teams = new ArrayList<Team>();
		nests = new ArrayList<Nest>();
		food = new ArrayList<Food>();
	}
	
	public static AntWorld createWorld()
	{
		world = new AntWorld();
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
