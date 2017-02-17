package karte.model;

import java.util.Iterator;

import javafx.geometry.Point2D;
/**
 * Ein Gebäude, das Rohstoffe aus Feldern einer Karte abbauen kann
 * @author Thomas
 *
 */
public class Mine extends Building {

	/**
	 * die Felder der Karte, auf die diese Mine zugreifen kann
	 */
	protected Area sources;
	
	/**
	 * Erstellt eine Mine mit angegebenen Maßen
	 * @param width Breite der Mine
	 * @param height Höhe der Mine
	 */
	public Mine(int width, int height) {
		super (width, height);
	}
	
	@Override
	public void run() {
		
		//TODO nur vorläufig gefüllt
		long time = getPassedTime();
		
		int count = 0;
		
		//check Area for Rohstoffe
		for (Iterator<Feld> it = sources.iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		System.out.println("Auf " +count+" Feldern wurden für die Zeit von " + time + " ms Rohstoffe gemint.");
	}
	
	@Override
	public void place(Map parent, Point2D position) {
		super.place(parent, position);
		sources = new Area(parent, position, body);
	}

}
