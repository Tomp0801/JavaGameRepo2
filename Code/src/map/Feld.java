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
	private HashMap<Bodenschatz, Float> rohstoffe;
	
	/**
	 * Platz, an dem ein Geb�ude o.�. errichtet werden kann
	 * Kann auch durch nat�rliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;
	
	Feld(Bereich bereich, ArrayList<Bodenschatz> bodenschaetze) {
		this.parentBereich = bereich;
		
		rohstoffe = new HashMap<Bodenschatz, Float>();
		
		//rohstoffe generieren
		//Alle Bodenschaetze, die dem Feld in der ArrayList �bergeben wurden
		Iterator<Bodenschatz> iterator = bodenschaetze.iterator();
		Bodenschatz temp;
		double random;
		while (iterator.hasNext()) {
			temp = iterator.next();
			
			//Vorkommenswahrscheinlichkeit
			random = Math.random();
			if (random <= temp.getVorkommensWkeit() * parentBereich.getTyp().getBodenReichtum()) {	//ist Random kleiner/gleich w-keit
				setRohstoff(temp, (float)1); //1 entspricht 100%
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
	public void setRohstoff(Bodenschatz rohstoff, Float menge) {
		rohstoffe.put(rohstoff, menge);
	}
	
	/**
	 * @return the bereich
	 */
	public Bereich getParentBereich() {
		return parentBereich;
	}

	/**
	 * @return the rohstoffe
	 */
	public HashMap<Bodenschatz, Float> getRohstoffe() {
		return rohstoffe;
	}
}
