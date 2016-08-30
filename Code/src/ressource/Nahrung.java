package ressource;

/**
 * Interface f�r Ressourcen, die essbar sein sollen.
 * Alle daf�r n�tigen Methoden sollen enthalten sein
 * 
 * @author Thomas
 *
 */
public interface Nahrung {
	/**
	 * Gibt den N�hrwert pro Kiste wider
	 * Nahrungspunkte pro Kiste/Einheit von dieser Ressource
	 * @return den N�hrwert der Ressource
	 */
	public float getNaehrwert();
	
	/**
	 * Gibt die Zeitspanne zur�ck, f�r die die Nahrung haltbar ist
	 * -1 steht f�r eine unendliche haltbarkeit
	 * @return die Haltbarkeit der Ressource
	 */
	public long getHaltbarkeit();
}