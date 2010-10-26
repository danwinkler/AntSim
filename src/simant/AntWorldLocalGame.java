package simant;

public class AntWorldLocalGame implements AntWorldModifier
{
	AntWorldUI ui;
	
	AntWorld world;
	
	public AntWorldLocalGame()
	{
		world = AntWorld.createWorld( 2 );
		
		Team team1 = world.teams.get( 0 );
		
		for( int i = 0; i < 1; i++ )
		{
			team1.units.add( new WorkerAnt( team1 ) );
		}
		
		ui = new AntWorldUI( this, world );
		ui.localGame = true;
		
		for( int i = 0; i < world.nests.size(); i++ )
		{
			assert( world.nests.get( i ).surfaceLoc != null );
		}
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
