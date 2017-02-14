package draufsicht.map;

/**
 * In einem objekt dieser Klasse befinden sich die Daten des Spiels 
 * 
 * @author Dennis
 *
 */
public class Spielwelt 
{
	/**
	 * Verweis auf die Spielwelt 
	 */
	private static Spielwelt instance; 
	
	/**
	 * erstelt eine Object der Klasse Spielwelt 
	 */
	private Spielwelt()
	{
		instance = this; 
	}
	
	/**
	 * 
	 * @return eine Verweis auf die Spielwelt 
	 */
	public static Spielwelt getInstance()
	{
		if (instance == null)
			new Spielwelt(); 
	
		return instance;
	}	
}
