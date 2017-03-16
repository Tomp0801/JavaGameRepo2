package draufsicht.speicherverwaltung;

import java.util.ArrayList;

import draufsicht.ressource.BodenMaterial;
import draufsicht.ressource.Material;
import javafx.scene.paint.Color;

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
	 * für verschiedene Ressourcen, zb Wasser
	 */
	private ArrayList<Material> verschiedene;
	
	/**
	 * Konstruktor
	 */
	public RessourcenObjekte() {
		//Bodenschatze hinzufügen
		setBodenschaetze();
		setBodentypen();
		setVerschiedene();
	}
		
	/**
	 * stellt die Bodenschaetze ein
	 * TODO vervollständigen
	 */
	private void setBodenschaetze() {
		bodenschaetze = new ArrayList<BodenMaterial>();
		bodenschaetze.add(new BodenMaterial("Gold", Color.GOLD, (float)0.05));
		bodenschaetze.add(new BodenMaterial("Silber", Color.SILVER, (float)0.08));
		bodenschaetze.add(new BodenMaterial("Kupfer", Color.SANDYBROWN, (float)0.1));
		bodenschaetze.add(new BodenMaterial("Kohle", Color.BLACK, (float)0.4));
		bodenschaetze.add(new BodenMaterial("Diamant", Color.LIGHTSKYBLUE, (float)0.01));
		bodenschaetze.add(new BodenMaterial("Eisen", Color.DARKGRAY, (float)0.2));
	}
	
	/**
	 * stellt die Bodentypen ein
	 * TODO vervollständigen
	 */
	private void setBodentypen() {
		bodentypen = new ArrayList<BodenMaterial>();
		bodentypen.add(new BodenMaterial("Stein", Color.DIMGRAY, (float)0.95));
		bodentypen.add(new BodenMaterial("Erde", Color.SADDLEBROWN, (float)0.8));
		bodentypen.add(new BodenMaterial("Sand", Color.LIGHTGOLDENRODYELLOW, (float)0.75));
		bodentypen.add(new BodenMaterial("Kies", Color.LIGHTSLATEGRAY, (float)0.5));
		bodentypen.add(new BodenMaterial("Dreck", Color.ROSYBROWN, (float)0.8));
	}
	
	private void setVerschiedene()
	{
		verschiedene = new ArrayList<>();
		Color blauDurchsichtig = new Color(Color.DODGERBLUE.getRed(), Color.DODGERBLUE.getGreen(), Color.DODGERBLUE.getBlue(), 0.8);
		verschiedene.add(new Material("Wasser", blauDurchsichtig));
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
	
	/**
	 * @return the verscheidene
	 */
	public ArrayList<Material> getVerschiedene() {
		return verschiedene;
	}
}
