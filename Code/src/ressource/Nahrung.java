package ressource;

/**
 * Interface für Materialien, die essbar sein sollen.
 * Alle dafür nötigen Methoden sollen enthalten sein
 * 
 * @author Thomas
 *
 */
public interface Nahrung {
	/**
	 * Gibt den Nährwert pro Kiste wider
	 * Nahrungspunkte pro Kiste/Einheit von diesem Material
	 * @return den Nährwert der Material
	 */
	public float getNaehrwert();
	
	/**
	 * Gibt die Zeitspanne zurück, für die die Nahrung haltbar ist
	 * -1 steht für eine unendliche haltbarkeit
	 * @return die Haltbarkeit der Material
	 */
	public long getHaltbarkeit();
}