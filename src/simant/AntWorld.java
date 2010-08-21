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
	
	DOptions options = new DOptions( "antworld.cfg" );
	
	public void update()
	{
		
	}
	
	private AntWorld()
	{
		width = options.getI( "surfaceWidth" );
		height = options.getI( "surfaceHeight" );
		tiles = new Tile[width][height];
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
	
	public void loadOptions( String filename )
	{
		options = new DOptions( filename );
	}
	
	public boolean getClear( int x, int y )
	{
		return tiles[x][y].getClear();
	}
}
