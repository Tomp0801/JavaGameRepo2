package karte.model;

import java.util.HashMap;

import javafx.geometry.Point2D;
import karte.view.*;
import obersteEbene.controller.Random;
import ressource.model.Material;

/**
 * Eine 2 Dimensionale Karte, bestehend aus einzelnen Feldern
 * 
 * @author Thomas
 *
 */
public class Map {
	/**
	 * Array der Felder, die die Karte aufbauen
	 */
	private Feld[][] felder;
	
	/**
	 * Breite der Karte
	 */
	private int width;
	
	/**
	 * Höhe der Karte
	 */
	private int height;
	
	private MapGrafics grafics;
	
	private Random prng;
	
	private HashMap<Material, Float> bodenschaetze;
		
	/**
	 * Erstellt eine Karte mit angegebener Breite und Höhe
	 * @param width breite der Karte in Feldern
	 * @param height höhe der Karte in Feldern
	 */
	public Map(int width, int height, int seed, HashMap<Material, Float> bodenschaetze) {
		this.prng = new Random(seed);
		this.width = width;
		this.height = height;
		this.bodenschaetze = bodenschaetze;
		felder = new Feld[width][height];
	
		loadGrafics();
	}
	
	/**
	 * Erstellt ein neues Grafikobjekt das zu dieser Karte passt
	 */
	public void loadGrafics() {
		grafics = new MapGrafics(this);
	}
	
	/**
	 * Erstellt das Feld an angegebener Stelle
	 * Sollte nur von der Generatorklasse verwendet werden!
	 * 
	 * @param x x-Postion des Feldes
	 * @param y y-Position des Feldes
	 * @param grundMaterial BodenMaterial von dem Feld
	 */
	void initFeld(int x, int y, Material grundMaterial) {
		felder[x][y] = new Feld(this, x, y, grundMaterial);
	}
	
	/**
	 * gibt das Feld an der angegebenen Stelle zurück
	 * @param x x-Position des Felds
	 * @param y y-Position des Felds
	 * @return das Feld an der angegebenen Stelle
	 */
	public Feld getFeld(int x, int y) throws ArrayIndexOutOfBoundsException {
		if (x >= this.width) {
			throw new ArrayIndexOutOfBoundsException("Punkt liegt nicht auf der Karte. Zu weit rechts.");
		} else if (x < 0) {
			throw new ArrayIndexOutOfBoundsException("Punkt liegt nicht auf der Karte. Zu weit links.");
		} else if (y >= this.height) {
			throw new ArrayIndexOutOfBoundsException("Punkt liegt nicht auf der Karte. Zu weit unten.");
		} else if (y < 0) {
			throw new ArrayIndexOutOfBoundsException("Punkt liegt nicht auf der Karte. Zu weit oben.");
		}
		return felder[x][y];
	}
	
	/**
	 * gibt das Feld an der angegebenen Stelle zurück
	 * @param punkt Position des Felds
	 * @return das Feld an der angegebenen Stelle
	 */
	public Feld getFeld(Point2D punkt) throws ArrayIndexOutOfBoundsException {
		int x = (int) Math.round(punkt.getX());
		int y = (int) Math.round(punkt.getY());
		return getFeld(x, y);
	}
	
	/**
	 * Gibt die Breite der Karte an
	 * @return die Breite der Karte
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gibt die Höhe der Karte an
	 * @return die Höhe der Karte
	 */
	public int getHeight() {
		return height;
	}
	
	public HashMap<Material, Float> getBodenschaetze() {
		return bodenschaetze;
	}

	/**
	 * Gibt die Grafikkomponente dieser Map zurück
	 * @return das MapGrafics-Objekt das zu dieser Karte gehört
	 */
	public MapGrafics getGrafics() {
		return grafics;
	}

	public Random getPrng() {
		return prng;
	}
}
