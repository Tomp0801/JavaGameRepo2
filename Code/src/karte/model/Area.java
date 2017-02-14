package karte.model;

import java.util.Iterator;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

/**
 * Ein Abschnitt einer Karte, der aus beliebigen Feldern besteht
 * Die Klasse ist dazu gedacht, die Felder zu repräsentieren, auf die ein bestimmtes Objekt zugriff hat
 * und auf die es einwirken kann.
 * Wie diese Felder-Anordnung aussieht, hängt ganz von den Eigenschaften des Objekts ab und ist frei wählbar
 * 
 * @author Thomas
 *
 */
public class Area implements Iterable<Feld> {
	/**
	 * Die Felder, die zu dieser Gegend gehören
	 */
	private Feld[] felder;
		
	/**
	 * Erstellt ein Area-Objekt aus dem Array der angegebenen Punkte
	 * @param parent Karte, auf die sich die Area bezieht
	 * @param auswahl Punkte, die zu der Area gehören sollen
	 */
	public Area(Map parent, Point2D[] auswahl) {
		felder = new Feld[auswahl.length];
		
		for (int i = 0; i < auswahl.length; i++) {
			felder[i] = parent.getFeld(auswahl[i]);
		}
	}
	
	public Area(Map parent, Point2D rectPos, Rectangle rect) {
		int width = (int) Math.round(rect.getWidth());
		int height = (int) Math.round(rect.getHeight());
		int posX = (int) Math.round(rectPos.getX());
		int posY = (int) Math.round(rectPos.getY());
		int i = 0;
		
		felder = new Feld[width * height];
		
		for (int x = posX; x < posX + width; x++) {
			for (int y = posY; y < posY + height; y++) {
				felder[i] = parent.getFeld(x, y);
				i++;
			}
		}
	}

	@Override
	public Iterator<Feld> iterator() {
		return new Iterator<Feld>() {
			private int i = 0;
			private int size = felder.length;
			
			@Override
			public boolean hasNext() {
				if (i < size) 
					return true;
				return false;
			}

			@Override
			public Feld next() {
				Feld next = felder[i];
				i++;
				return next;
			}
		};
	}
}
