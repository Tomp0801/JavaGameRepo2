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
	 * Bereich, zu dem dieses Feld gehört
	 */
	private Bereich parentBereich;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enthält
	 */
	private HashMap<Bodenschatz, Float> rohstoffe;
	
	/**
	 * Platz, an dem ein Gebäude o.ä. errichtet werden kann
	 * Kann auch durch natürliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;
	
	Feld(Bereich bereich, ArrayList<Bodenschatz> bodenschaetze) {
		this.parentBereich = bereich;
		
		rohstoffe = new HashMap<Bodenschatz, Float>();
		
		//rohstoffe generieren
		//Alle Bodenschaetze, die dem Feld in der ArrayList übergeben wurden
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
	 * Stellt die Menge des angegebenen Rohstoffs für das Feld ein
	 * Wenn ein vorheriger Wert für diesen Rohstoff existiert, wird er überschrieben
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
