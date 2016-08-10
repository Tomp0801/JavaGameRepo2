package speicherverwaltung;

import java.util.ArrayList;

import map.BodenMaterial;

public class RessourcenObjekte {
	
	/**
	 * Liste der Standard Bodenschaetze
	 */
	private ArrayList<BodenMaterial> bodenschaetze;
	
	/**
	 * die Materialen aus denen der Boden auf Karten bestehen kann
	 */
	private ArrayList<BodenMaterial> bodentypen;
	
	/**
	 * Konstruktor
	 */
	public RessourcenObjekte() {
		//Bodenschatze hinzufügen
		setBodenschaetze();
		setBodentypen();
	}
		
	/**
	 * stellt die Bodenschaetze ein
	 * TODO vervollständigen
	 */
	private void setBodenschaetze() {
		bodenschaetze = new ArrayList<BodenMaterial>();
		bodenschaetze.add(new BodenMaterial("Gold", "Spitzhacke", (float)0.05));
		bodenschaetze.add(new BodenMaterial("Silber", "Spitzhacke", (float)0.08));
		bodenschaetze.add(new BodenMaterial("Kupfer", "Spitzhacke", (float)0.1));
		bodenschaetze.add(new BodenMaterial("Kohle", "Spitzhacke", (float)0.4));
		bodenschaetze.add(new BodenMaterial("Diamant", "Spitzhacke", (float)0.01));
		bodenschaetze.add(new BodenMaterial("Eisen", "Spitzhacke", (float)0.2));
	}
	
	/**
	 * stellt die Bodentypen ein
	 * TODO vervollständigen
	 */
	private void setBodentypen() {
		bodentypen = new ArrayList<BodenMaterial>();
		bodentypen.add(new BodenMaterial("Stein", "Spitzhacke", (float)0.3));
		bodentypen.add(new BodenMaterial("Erde", "Schaufel", (float)0.5));
		bodentypen.add(new BodenMaterial("Sand", "Schaufel", (float)0.3));
		bodentypen.add(new BodenMaterial("Kies", "Schaufel", (float)0.1));
		bodentypen.add(new BodenMaterial("Dreck", "Schaufel", (float)0.2));
	}
	
	/**
	 * getter für bodenschaetze
	 * @return bodenschaetze
	 */
	public ArrayList<BodenMaterial> getBodenschaetze() {
		return bodenschaetze;
	}

	/**
	 * @return the bodenTypen
	 */
	public ArrayList<BodenMaterial> getBodentypen() {
		return bodentypen;
	}
}
