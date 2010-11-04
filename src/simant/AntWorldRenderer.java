package simant;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.phyloa.dlib.renderer.Graphics2DRenderer;
import com.phyloa.dlib.renderer.Renderer;
import com.phyloa.dlib.util.DFile;
import com.phyloa.dlib.util.DGraphics;
import com.phyloa.dlib.util.DMath;

public class AntWorldRenderer 
{
	static AntPartSystem aps;
	
	static
	{
		aps = new AntPartSystem();
	}
	
	//Render the antworld using the passed Renderer and viewport, centered around the loc
	public static void render( Renderer r, Location loc, int width, int height )
	{		
		r.color( 0, 0, 0 );
		r.fillRect( 0, 0, width, height );
		
		r.pushMatrix();
		
		//Render ground
		AntWorld w = AntWorld.world;
		
		for( int i = 0; i < w.renderEvents.size(); i++ )
		{
			RenderEvent re = w.renderEvents.get( i );
			switch( re.type )
			{
			case ANT_DIE:
				for( int j = 0; j < 10; j++ )
				{
					aps.add( re.loc, re.team );
				}
				break;
			}
		}
		w.renderEvents.clear();
		
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
		
		for( int i = 0; i < w.teams.size(); i++ )
		{
			Team t = w.teams.get( i );
			setColorForTeam( t, r );
			for( int j = 0; j < t.units.size(); j++ )
			{
				Ant a = t.units.get( j );
				if( a.loc.underground == loc.underground )
				{
					if( a.loc.nest == loc.nest )
					{
						if( a instanceof WorkerAnt )
						{
							r.pushMatrix();
							r.translate( a.loc.x, a.loc.y );
							r.rotate( a.heading );
							r.fillOval( -5, -2, 10, 4 );
							r.popMatrix();
						}
						else if( a instanceof WarriorAnt )
						{
							
						}
						else if( a instanceof NurseAnt )
						{
							
						}
						else if( a instanceof Queen )
						{
							r.pushMatrix();
							r.translate( a.loc.x, a.loc.y );
							r.rotate( a.heading );
							r.fillOval( -6, -3, 12, 6 );
							r.popMatrix();
						}
						else if( a instanceof Egg )
						{
							r.fillOval( a.loc.x-2, a.loc.y-2, 4, 4 );
						}	
					}
				}
			}
		}
		aps.update();
		aps.render( r, loc );
		
		r.popMatrix();
	}
	
	
	public static class AntPartSystem
	{
		ArrayList<AntPart> antParts = new ArrayList<AntPart>();
		
		public void update()
		{
			for( int i = 0; i < antParts.size(); i++ )
			{
				antParts.get( i ).update();
				if( antParts.get( i ).life <= 0 )
				{
					antParts.remove( i );
					i--;
				}
			}
		}
		
		public void add( Location loc, Team team )
		{
			antParts.add( new AntPart( loc, team ) );
		}

		public void render( Renderer r, Location loc )
		{
			for( int i = 0; i < antParts.size(); i++ )
			{
				AntPart ap = antParts.get( i );
				if( ap.loc.nest == loc.nest )
				{
					antParts.get( i ).render( r );
				}
			}
		}
	}
	
	public static class AntPart
	{		
		public static int maxLife;
		
		static
		{
			maxLife = A.o.getI( "antPartDecay" );
		}
		
		Location loc;
		Vector2f speed;
		Team t;
		int life;
		float angle;
		float angleSpeed;
		int type;
		
		public AntPart( Location loc, Team t )
		{
			this.loc = new Location( loc );
			this.speed = new Vector2f( DMath.randomf( -3, 3 ), DMath.randomf( -3, 3 ) );
			life = maxLife;
			this.t = t;
			
			angle = DMath.randomf( 0, (float)(Math.PI * 2) );
			angleSpeed = DMath.randomf( -.3f, .3f );
			type = DMath.randomi( 0, 2 );
		}
		
		public void update()
		{
			loc.add( speed );
			angle += angleSpeed;
			
			speed.scale( .9f );
			angleSpeed *= .9f;
			
			life--;
		}
		
		public void render( Renderer r )
		{
			setColorForTeam( t, r, ((float)life / maxLife) * 255 );
			switch( type )
			{
			case 1: ((Graphics2DRenderer)r).g.draw( new Line2D.Float( loc.x, loc.y, loc.x + DMath.cosf( angle ) * 3, loc.y + DMath.sinf( angle ) * 3 ) ); break;
			case 2: ((Graphics2DRenderer)r).g.fill( new Ellipse2D.Float( loc.x - 1.5f, loc.y -1.5f, 3, 3 ) ); break;
			}
		}
	}
	
	public static void setColorForTeam( Team t, Renderer r )
	{
		switch( t.color )
		{
		case 0: r.color( 0, 0, 255 ); break;
		case 1: r.color( 255, 0, 0 ); break;
		case 2: r.color( 255, 255, 0 ); break;
		case 3: r.color( 0, 0, 0 ); break;
		}
	}
	
	public static void setColorForTeam( Team t, Renderer r, float alpha )
	{
		switch( t.color )
		{
		case 0: r.color( 0, 0, 255, alpha ); break;
		case 1: r.color( 255, 0, 0, alpha ); break;
		case 2: r.color( 255, 255, 0, alpha ); break;
		case 3: r.color( 0, 0, 0, alpha ); break;
		}
	}
	
}
