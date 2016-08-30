package ressource;

/**
 * Interface für Ressourcen, die essbar sein sollen.
 * Alle dafür nötigen Methoden sollen enthalten sein
 * 
 * @author Thomas
 *
 */
public interface Nahrung {
	/**
	 * Gibt den Nährwert pro Kiste wider
	 * Nahrungspunkte pro Kiste/Einheit von dieser Ressource
	 * @return den Nährwert der Ressource
	 */
	public float getNaehrwert();
	
	/**
	 * Gibt die Zeitspanne zurück, für die die Nahrung haltbar ist
	 * -1 steht für eine unendliche haltbarkeit
	 * @return die Haltbarkeit der Ressource
	 */
	public long getHaltbarkeit();
}