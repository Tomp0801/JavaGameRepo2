package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Ein Feld eines gewissen typs, das bebaut werden kann und/oder Objekte enthalten kann
 * 
 * @author Thomas
 *
 */
public class Feld {
	/**
	 * Bereich, zu dem dieses Feld geh�rt
	 */
	private Bereich parentBereich;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enth�lt
	 */
	private HashMap<BodenMaterial, Float> bodenschatzVorkommen;
	
		/**
	 * Platz, an dem ein Geb�ude o.�. errichtet werden kann
	 * Kann auch durch nat�rliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;

	
	Feld(Bereich bereich, ArrayList<BodenMaterial> bodenschaetze) {
		this.parentBereich = bereich;
		
		bodenschatzVorkommen = new HashMap<BodenMaterial, Float>();
		
		//rohstoffe generieren
		//Alle Bodenschaetze, die dem Feld in der ArrayList �bergeben wurden
		Iterator<BodenMaterial> iterator = bodenschaetze.iterator();
		BodenMaterial temp;
		double random;
		while (iterator.hasNext()) {
			temp = iterator.next();
			
			//Vorkommenswahrscheinlichkeit mit einbringen
			random = Math.random();
			if (random <= temp.getVorkommensWkeit()) {
				setRohstoff(temp, (float)1); //1 entspricht 100%
				//TODO Menge einstellen
				/*
				 * idee:
				 * Menge doch Vorkommens W-keit
				 * wenn unter bestimmten schwellenwert, rauslassen. ANsonsten immer vorhanden
				 * in ermittelter Menge
				 */
			}
		}
	}
	
	/**
	 * Stellt die Menge des angegebenen Rohstoffs f�r das Feld ein
	 * Wenn ein vorheriger Wert f�r diesen Rohstoff existiert, wird er �berschrieben
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
	 * @return the bauplatz
	 */
	public Platzierbar getBauplatz() {
		return bauplatz;
	}

	/**
	 * @param bauplatz the bauplatz to set
	 * TODO beingungen f�r den Bau eines Objekts
	 */
	public void setBauplatz(Platzierbar bauplatz) {
		this.bauplatz = bauplatz;
	}
}
