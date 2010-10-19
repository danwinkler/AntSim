package simant;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.phyloa.dlib.util.DFile;
import com.phyloa.dlib.util.DGraphics;

public class AntImage
{
	public static BufferedImage[] images;
	
	static
	{
		try
		{
			images = DGraphics.cut( DFile.loadImage( "terrain.png" ), 20, 20 );
		} catch( IOException e )
		{
		}
	}
	
	public static BufferedImage get( int i )
	{
		return images[i];
	}
}
