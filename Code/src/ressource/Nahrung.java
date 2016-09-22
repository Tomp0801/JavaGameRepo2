package ressource;

/**
 * Interface f�r Materialien, die essbar sein sollen.
 * Alle daf�r n�tigen Methoden sollen enthalten sein
 * 
 * @author Thomas
 *
 */
public interface Nahrung {
	/**
	 * Gibt den N�hrwert pro Kiste wider
	 * Nahrungspunkte pro Kiste/Einheit von diesem Material
	 * @return den N�hrwert der Material
	 */
	public float getNaehrwert();
	
	/**
	 * Gibt die Zeitspanne zur�ck, f�r die die Nahrung haltbar ist
	 * -1 steht f�r eine unendliche haltbarkeit
	 * @return die Haltbarkeit der Material
	 */
	public long getHaltbarkeit();
}