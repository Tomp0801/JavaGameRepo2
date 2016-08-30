package map.objekte;

import java.util.ArrayList;
import java.util.HashMap;

import map.Feld;
import ressource.Ressource;

/**
 * Interface für Objekte, die auf einem Feld platzierbar sein sollen
 * z.B. Pflanzen, Gebäude...
 * 
 * @author Thomas
 *
 */
public interface Platzierbar {
	/**
	 * lässt das Objekt berechnen, was es alles in der Zeit seit der letzten Berechnung 
	 * produziert hat.
	 * @return eine HashMap mit den Materialien und ihren Mengen, die gewonnen wurden durch die Produktion
	 */
	public HashMap<Ressource, Double> run();
	
	/**
	 * gibt zurück, welche Materialien hier gewonnen werden
	 * @return eine ArrayList von Materialien
	 */
	public ArrayList<Ressource> getOutputs();
	
	/**
	 * gibt zurück, welche Materialien bei der Produktion verbraucht werden
	 * @return
	 */
	public ArrayList<Ressource> getInputs();
	
	/**
	 * @return das Feld, auf dem Das Objekt steht
	 */
	public Feld getFeld();
	
	/**
	 * 
	 * @return den Namen des platzierbaren Objekts
	 */
	public String getName();
}
