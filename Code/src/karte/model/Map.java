package karte.model;

import javafx.geometry.Point2D;
import karte.view.*;

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
		
	/**
	 * Erstellt eine Karte mit angegebener Breite und Höhe
	 * @param width breite der Karte
	 * @param height höhe der Karte
	 */
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		felder = new Feld[width][height];
	
		init();
	}
	
	/**
	 * erstellt alle Feld-Objekte der Karte
	 */
	private void init() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				felder[x][y] = new Feld(this, x, y);
			}
		}
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
	
	public MapGrafics getGrafics() {
		return grafics;
	}
	
	public void setGrafics(MapGrafics mapGrafics) {
		grafics = mapGrafics;
	}
}
