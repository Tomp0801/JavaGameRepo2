package draufsicht.model;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wohnhaus implements Placeable {
	
	private static final String NAME = "Wohnhaus"; 
	
	private Feld feld;
	
	private int maxBewohner;
	
	private int freieArbeiter;
	
	private Rectangle grafics;
	
	public Wohnhaus() {
		maxBewohner = 20;
		freieArbeiter = maxBewohner;
		
		grafics = new Rectangle(15, 15, Color.BLACK);
	}
	
	public void reserveArbeiter(Verbraucher objekt, int anzahl) {
		//TODO
	}
	
	/**
	 * Gibt die Anzahl an freien Arbeitern zurück, maximal so viele wie angegeben 
	 * @param anzahl maximal benötigte anzahl an arbeitern
	 * @return verfügbareAnzahl an arbeitern
	 */
	public int requestArbeiter(int anzahl) {
		if (freieArbeiter >= anzahl) {
			return anzahl;
		} else {
			return freieArbeiter;
		}
	}
	
	public void upgrade() {
		maxBewohner += 10;
		freieArbeiter += 10;
	}
	
	public int getMaxBewohner() {
		return maxBewohner;
	}
	
	public int getFreieArbeiter() {
		return freieArbeiter;
	}

	@Override
	public Feld getGrund() {
		return feld;
	}

	@Override
	public void place(Feld feld) {
		feld.place(this);
		this.feld = feld;
	}

	@Override
	public void place(Map parent, Point2D position) throws IllegalArgumentException {
		place(parent.getFeld(position));
	}

	@Override
	public Node getGrafics() {
		return grafics;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void unplace() {
		feld.removeObject();
		feld = null;
	}

}
