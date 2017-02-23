package ressource;

//import draufsicht.map.objekte.Platzierbar;

/**
 * Interface f�r alle Ressourcen, die zur Gewinnung von Energie verwendet werden k�nnen
 * Energie wird zB zur Herstellung, Produktion, Abbau, Fortbewegung ben�tigt
 * 
 * @author Thomas
 *
 */
public interface Energie {
	/**
	 * gibt Energie zur�ck, die dieses Material pro Einheit enth�lt
	 * @return float die GesamtEnergie pro Einheit
	 */
	public float getEnergie();

	/**
	 * gibt Energie an, die maximal pro stunde abgegeben werden kann
	 * @return float max. Energie-Abgabe pro Stunde
	 */
	public float getEnergieAbgabe();
	
	/**
	 * TODO nicht Platzierbar Objekt
	 * @return die Art Geb�ude, die zur Energie Gewinnung mit diesem Material ben�tigt wird
	 */
	//public Platzierbar getGebaeude();
}

