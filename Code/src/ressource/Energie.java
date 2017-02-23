package ressource;

//import draufsicht.map.objekte.Platzierbar;

/**
 * Interface für alle Ressourcen, die zur Gewinnung von Energie verwendet werden können
 * Energie wird zB zur Herstellung, Produktion, Abbau, Fortbewegung benötigt
 * 
 * @author Thomas
 *
 */
public interface Energie {
	/**
	 * gibt Energie zurück, die dieses Material pro Einheit enthält
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
	 * @return die Art Gebäude, die zur Energie Gewinnung mit diesem Material benötigt wird
	 */
	//public Platzierbar getGebaeude();
}

