package simant;

public class Nest 
{
	static float xTileSize;
	static float yTileSize;
	
	static
	{
		xTileSize = A.o.getF( "xNestTileSize" );
		yTileSize = A.o.getF( "yNestTileSize" );
	}
	
	int width;
	int height;
	
	int[][] tiles; //0 dirt, 1 air
	
	Location hole;
	
	public Nest()
	{
		width = A.o.getI( "nestWidth" );
		height = A.o.getI( "nestHeight" );
		tiles = new int[width][height];
		hole = new Location( (int)(width/2), 0, this );
		for( int i = 0; i < 6; i++ )
		{
			tiles[(int)hole.x][i] = 1;
		}
		for( int i = 0; i < width; i++ )
		{
			tiles[i][0] = 2;
		}
	}
	
	public boolean getClear( float x, float y )
	{
		return tiles[(int)(x/xTileSize)][(int)(y/yTileSize)] == 0;
	}
}
