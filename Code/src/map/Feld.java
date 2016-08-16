package map;

import java.util.HashMap;

/**
 * Ein Feld eines gewissen typs, das bebaut werden kann und/oder Objekte enthalten kann
 * 
 * @author Thomas
 *
 */
public class Feld {
	/**
	 * Bereich, zu dem dieses Feld gehört
	 */
	private Bereich parentBereich;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enthält
	 */
	private HashMap<BodenMaterial, Float> bodenschatzVorkommen;
	
	/**
	 * Material aus dem der Boden hier ist
	 */
	private BodenMaterial bodentyp;
	
	/**
	 * Platz, an dem ein Gebäude o.ä. errichtet werden kann
	 * Kann auch durch natürliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;

	/**
	 * Zufalls Konstruktor
	 * 
	 * @param bereich Bereich, zu dem das Feld gehört
	 * @param bodenschaetze Mögliche bodenschatze
	 * @param bodentypen Mögliche Bodenarten
	 */
	Feld(Bereich bereich) {
		this.parentBereich = bereich;
		
		bodenschatzVorkommen = new HashMap<BodenMaterial, Float>();
		
		//rohstoffe generieren
		//Alle Bodenschaetze, die dem Feld in der ArrayList übergeben wurden
		double menge;
		for (BodenMaterial temp : parentBereich.getBodenschaetze().keySet()) {
			//Vorkommenswahrscheinlichkeit und varietät mit einbringen
			menge = parentBereich.getParentKarte().getVarierteRandom() * temp.getVorkommensWkeit();
			if (menge > 0.001) {	//wenn unter bestimmten schwellenwert, rauslassen.
				setRohstoff(temp, (float)menge); //1 entspricht 100%
			}
		}
		
		//bodenart generieren; sich für einen Boden entscheiden
		//für jede Art eine zahl aus zufall und vorkommensWkeit generieren
		//größte Zahl wird ausgewählt
		BodenMaterial chosen = null;
		double largest = 0;
		double random;
		for (BodenMaterial temp : parentBereich.getBodenarten().keySet()) {
			random = temp.getVorkommensWkeit() * parentBereich.getParentKarte().getVarierteRandom(); 
			if (random > largest) {
				random = largest;
				chosen = temp;
			}
		}
		bodentyp = chosen;
	}
	
	/**
	 * Stellt die Menge des angegebenen Rohstoffs für das Feld ein
	 * Wenn ein vorheriger Wert für diesen Rohstoff existiert, wird er überschrieben
	 * 
	 * @param rohstoff Art des Rohstoffs
	 * @param menge Menge des Rostoffs
	 */
	public void setRohstoff(BodenMaterial rohstoff, Float menge) {
		bodenschatzVorkommen.put(rohstoff, menge);
	}

	/**
	 * @return the rohstoffe
	 */
	public HashMap<BodenMaterial, Float> getRohstoffe() {
		return bodenschatzVorkommen;
	}
	
	/**
	 * @return the bereich
	 */
	public Bereich getParentBereich() {
		return parentBereich;
	}

	/**
	 * 
	 * @return the bodenschatzVorkommen
	 */
	public HashMap<BodenMaterial, Float> getBodenschatzVorkommen() {
		return bodenschatzVorkommen;
	}

	/**
	 * @return the bodentyp
	 */
	public BodenMaterial getBodentyp() {
		return bodentyp;
	}

	/**
	 * @return the bauplatz
	 */
	public Platzierbar getBauplatz() {
		return bauplatz;
	}

	/**
	 * @param bauplatz the bauplatz to set
	 * TODO bedingungen für den Bau eines Objekts
	 */
	public void setBauplatz(Platzierbar bauplatz) {
		this.bauplatz = bauplatz;
	}
}
