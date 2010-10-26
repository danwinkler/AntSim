package simant;

public class AntWorldLocalGame implements AntWorldModifier
{
	AntWorldUI ui;
	
	AntWorld world;
	
	public AntWorldLocalGame()
	{
		world = AntWorld.createWorld( 2 );
		
		Team team1 = new Team( 0 );
		world.teams.add( team1 );
		
		team1.units.add( new WorkerAnt( team1 ) );
		team1.units.get( 0 ).targetLocation = new Location( 186, 20, world.nests.get( 0 ) );
		
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
