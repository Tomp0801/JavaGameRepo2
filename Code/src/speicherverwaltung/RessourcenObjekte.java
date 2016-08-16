package speicherverwaltung;

import java.util.ArrayList;

import javafx.scene.paint.Color;
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
		bodenschaetze.add(new BodenMaterial("Gold", Color.GOLD, "Spitzhacke", (float)0.05));
		bodenschaetze.add(new BodenMaterial("Silber", Color.SILVER,  "Spitzhacke", (float)0.08));
		bodenschaetze.add(new BodenMaterial("Kupfer", Color.SANDYBROWN, "Spitzhacke", (float)0.1));
		bodenschaetze.add(new BodenMaterial("Kohle", Color.BLACK, "Spitzhacke", (float)0.4));
		bodenschaetze.add(new BodenMaterial("Diamant", Color.LIGHTSKYBLUE, "Spitzhacke", (float)0.01));
		bodenschaetze.add(new BodenMaterial("Eisen", Color.DARKGRAY, "Spitzhacke", (float)0.2));
	}
	
	/**
	 * stellt die Bodentypen ein
	 * TODO vervollständigen
	 */
	private void setBodentypen() {
		bodentypen = new ArrayList<BodenMaterial>();
		bodentypen.add(new BodenMaterial("Stein", Color.DIMGRAY, "Spitzhacke", (float)0.6));
		bodentypen.add(new BodenMaterial("Erde", Color.SADDLEBROWN, "Schaufel", (float)0.8));
		bodentypen.add(new BodenMaterial("Sand", Color.LIGHTGOLDENRODYELLOW, "Schaufel", (float)0.4));
		bodentypen.add(new BodenMaterial("Kies", Color.LIGHTSLATEGRAY, "Schaufel", (float)0.1));
		bodentypen.add(new BodenMaterial("Dreck", Color.ROSYBROWN, "Schaufel", (float)0.2));
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
