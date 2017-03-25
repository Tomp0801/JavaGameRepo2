package spielmodel.spielermodel;

import java.util.ArrayList;

import spielmodel.spielermodel.charakter.Avatar;

/**
 * Hier befinden sich Informationen zu einem Spieler
 * 
 * @author Dennis
 *
 */
public class Spieler {

	/**
	 * Name des Spielers
	 */
	private String name = "Spielername";
	
	/**
	 * Eine Liste von Avataren die zu diesem Spieler gehoeren.
	 * Der Spieler beginnt mit einem Avatar. Im Laufe des Spieles sollen mehrere Avatare moeglich sin
	 */
	private ArrayList<Avatar> avatare = new ArrayList<Avatar>();
	
	/**
	 * erstellt einen Spieler
	 */
	public Spieler(){ //TODO es fehlen noch Parameter wie Name ect.
		//setzt den ersten Avatar
		avatare.add(new Avatar());
	}
}
