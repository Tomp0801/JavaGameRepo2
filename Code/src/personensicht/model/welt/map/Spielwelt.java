package personensicht.model.welt.map;

public class Spielwelt 
{
	private Region[][] welt;
	
	public Spielwelt(Region[][] orte)
	{
		this.welt = orte;
	}
	
	
	public Region[][] getWelt()
	{
		return this.welt; 
	}
	
}
