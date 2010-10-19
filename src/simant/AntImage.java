package simant;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.phyloa.dlib.util.DFile;
import com.phyloa.dlib.util.DGraphics;

public class AntImage
{
	public static BufferedImage[] terrainImages;
	public static BufferedImage[] unitImages;
	public static BufferedImage[] undergroundImages;
	
	static
	{
		try
		{
			terrainImages = DGraphics.cut( DFile.loadImage( "terrain.png" ), (int)A.o.getF( "surfaceXTileSize" ), (int)A.o.getF( "surfaceYTileSize" ) );
			undergroundImages = DGraphics.cut( DFile.loadImage( "underground.png" ), 12, 12 );
			unitImages = DGraphics.cut( DFile.loadImage( "units.png" ), 12, 12 );
		} catch( IOException e )
		{
		}
	}
	
	public static BufferedImage getTerrain( int i )
	{
		return terrainImages[i];
	}
	
	public static BufferedImage getUnderground( int i )
	{
		return undergroundImages[i];
	}
	
	public static BufferedImage getUnits( int i )
	{
		return unitImages[i];
	}
}
