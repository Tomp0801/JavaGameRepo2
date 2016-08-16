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
	 * Material aus dem der Boden hier ist
	 */
	private BodenMaterial bodentyp;
	
	/**
	 * Platz, an dem ein Geb�ude o.�. errichtet werden kann
	 * Kann auch durch nat�rliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;

	/**
	 * Zufalls Konstruktor
	 * 
	 * @param bereich Bereich, zu dem das Feld geh�rt
	 * @param bodenschaetze M�gliche bodenschatze
	 * @param bodentypen M�gliche Bodenarten
	 */
	Feld(Bereich bereich, ArrayList<BodenMaterial> bodenschaetze, ArrayList<BodenMaterial> bodentypen) {
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
		
		//bodenart generieren; sich f�r einen Boden entscheiden
		//f�r jede Art eine zahl aus zufall und vorkommensWkeit generieren
		//gr��te Zahl wird ausgew�hlt
		double[] genZahl = new double[bodentypen.size()];
		for (int i = 0; i < bodentypen.size(); i++) {
			random = parentBereich.getParentKarte().getPrng().random();
			genZahl[i] = bodentypen.get(i).getVorkommensWkeit() * random;
		}
		int largest = 0;
		for (int i = 0; i < genZahl.length; i++) {
			if (genZahl[largest] < genZahl[i]) {
				largest = i;
			}
		}
		bodentyp = bodentypen.get(largest);
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
	 * TODO bedingungen f�r den Bau eines Objekts
	 */
	public void setBauplatz(Platzierbar bauplatz) {
		this.bauplatz = bauplatz;
	}
}
