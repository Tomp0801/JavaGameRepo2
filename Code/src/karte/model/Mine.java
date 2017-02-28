package karte.model;

import java.util.Iterator;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import karte.view.MineGrafics;
/**
 * Ein Geb�ude, das Rohstoffe aus Feldern einer Karte abbauen kann
 * @author Thomas
 *
 */
public class Mine extends Building {
	private MineGrafics grafics;
	
	/**
	 * die Felder der Karte, auf die diese Mine zugreifen kann
	 */
	protected Area sources;
	
	/**
	 * Erstellt eine Mine mit angegebenen Ma�en
	 * @param width Breite der Mine
	 * @param height H�he der Mine
	 */
	public Mine() {
		grafics = new MineGrafics(this);
	}
	
	@Override
	public void run() {
		
		//TODO nur vorl�ufig gef�llt
		long time = getPassedTime();
		
		int count = 0;
		
		//check Area for Rohstoffe
		for (Iterator<Feld> it = sources.iterator(); it.hasNext();) {
			it.next();
			count++;
		}
		
		System.out.println("Auf " +count+" Feldern wurden f�r die Zeit von " + time + " ms Rohstoffe gemint.");
	}
	
	@Override
	public void place(Map parent, Point2D position) {
		super.place(parent, position);
	}

	@Override
	public Group getGrafics() {
		return grafics;
	}

	@Override
	public String getName() {
		return "Mine";
	}

}
