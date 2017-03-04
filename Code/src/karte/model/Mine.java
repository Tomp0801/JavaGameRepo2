package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import karte.view.MineGrafics;
/**
 * Ein Geb�ude, das Rohstoffe aus Feldern einer Karte abbauen kann
 * Vorraussetzung f�r das Laufen der Mine sind Arbeitskr�fte
 * 
 * @author Thomas
 *
 */
public class Mine extends RunningObject implements Placeable {
	
	private Feld feld;
	
	private MineGrafics grafics;
	
	/**
	 * die Felder der Karte, auf die diese Mine zugreifen kann
	 * TODO  Nutzen?
	 */
	protected Area sources;
	
	private int benoetigteArbeiter;
	
	private int arbeiter;
	
	/**
	 * Erstellt eine Mine
	 */
	public Mine() {
		loadGrafics();
		benoetigteArbeiter = 10;
		arbeiter = 0;
	}
	
	private boolean canRun() {
		if (arbeiter == benoetigteArbeiter) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Erstellt ein neues Grafikobjekt das zu dieser Mine passt
	 */
	public void loadGrafics() {
		grafics = new MineGrafics(this);
	}
	
	@Override
	public void run() {
		
		//TODO nur vorl�ufig gef�llt
		long time = getPassedTime();
				
		//check Umgebung nach Arbeitskr�ften
		if (canRun()) {
			System.out.println("Auf dem Feld wurde f�r die Zeit von " + time + " ms Rohstoffe gemint.");
		} else {
			System.out.println("Nicht gen�gend Arbeiter, um die Mine zu betreiben");
		}
		
	}


	@Override
	public Group getGrafics() {
		return grafics;
	}

	@Override
	public String getName() {
		return "Mine";
	}

	@Override
	public Feld getFeld() {
		return feld;
	}

	@Override
	public void place(Feld feld) {
		feld.place(this);
		this.feld = feld;
	}

	@Override
	public void place(Map parent, Point2D position) throws ArrayIndexOutOfBoundsException {
		place(parent.getFeld(position));
	}

	@Override
	public void unplace() {
		feld.removeObject();
		feld = null;
	}

}
