package spielctr.controller;

import draufsicht.crt.DraufsichtCtr;
import personensicht.crt.PersonensichtCtr;

/**
 * Der Boss Kontroller. 
 * Dieser Kontroller ist eine Verbindung zwischen Kontroller Klassen zum Beispiel von Kontroller in 
 * der draufsicht und von Kontroller in der Personensicht
 * 
 * 
 * @author Dennis
 *
 */
public class SpielCtr {

	private static SpielCtr instance; 
	private PersonensichtCtr personensichtCtr = PersonensichtCtr.getInsance(); 
	private DraufsichtCtr draufsichtCtr = DraufsichtCtr.getInstance(); 
	
	private SpielCtr(){
		instance = this; 
	}
	
	public SpielCtr getInstance(){
		if (instance == null){
			new SpielCtr();
		}
		return instance; 
	}
	
	/**
	 * Diese Methode startet ein neues Spiel
	 */
	public void starteNeuesSpiel(){
		//erstellte eine Spielwelt
		//erstelle einen Spieler
	}
}
