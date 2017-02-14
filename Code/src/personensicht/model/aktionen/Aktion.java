package personensicht.model.aktionen;

/**
 * Aktionen werden in dieser Klasse beschrieben
 * @author Demix
 *
 */
public class Aktion 
{
	/**
	 * Name der fuer den Spieler sichtbar ist
	 */
	private String name;

	private Aktionstyp typ;
	
	protected Aktion(Aktionstyp typ)
	{
		this.typ = typ; 
	}
	
	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized Aktionstyp getTyp() {
		return typ;
	} 
}
