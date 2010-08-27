package simant;

public class StandardGame
{
	public static void BasicSetup( AntWorld world )
	{
		world.teams.clear();
		
		Team team1 = new Team( 0 );
		world.teams.add( team1 );
		
		Nest nest1 = new Nest();
		
		
		Team team2 = new Team( 1 );
		world.teams.add( team2 );
	}
}