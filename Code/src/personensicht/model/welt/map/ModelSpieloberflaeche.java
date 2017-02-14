package personensicht.model.welt.map;

public class ModelSpieloberflaeche 
{
	/**
	 * verweist auf die akutelle Position
	 */
	private Region position;

	public ModelSpieloberflaeche(Region startOrt) 
	{
		this.position = startOrt; 
	}

	public synchronized Region getPosition() {
		return position;
	}

	public synchronized void setPosition(Region position) {
		this.position = position;
	}
	
	
}
