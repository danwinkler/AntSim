package simant;

public class Nest 
{
	int width;
	int height;
	float xTileSize;
	float yTileSize;
	int[][] tiles; //0 dirt, 1 air
	
	Location hole;
	
	public Nest()
	{
		width = AntWorld.world.options.getI( "nestWidth" );
		height = AntWorld.world.options.getI( "nestHeight" );
		xTileSize = AntWorld.world.options.getF( "xNestTileSize" );
		yTileSize = AntWorld.world.options.getF( "yNestTileSize" );
		tiles = new int[width][height];
		hole = new Location( width/2, 0, this );
	}
	
	public boolean getClear( float x, float y )
	{
		return tiles[(int)(x/xTileSize)][(int)(y/yTileSize)] == 0;
	}
}
