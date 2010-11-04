package simant;

public class RenderEvent
{
	public RenderEventType type;
	public Team team;
	public Location loc;
	
	public RenderEvent( RenderEventType type, Team team, Location loc )
	{
		this.team = team;
		this.type = type;
		this.loc = new Location( loc );
	}
}
