package simant;

public class AntWorldLocalGame implements AntWorldModifier
{
	AntWorldUI ui;
	
	AntWorld world;
	
	public AntWorldLocalGame()
	{
		world = AntWorld.createWorld( 2 );
		
		ui = new AntWorldUI( this, world );
		ui.localGame = true;
	}
	
	public void begin()
	{
		ui.begin();
	}
	
	public static void main( String[] args )
	{
		AntWorldLocalGame lg = new AntWorldLocalGame();
		lg.begin();
	}
	
	public void moveAnt( Ant ant, int region, int x, int y )
	{
		
	}
	
	public void setAntAuto( Ant ant, boolean auto )
	{
		
	}
}
