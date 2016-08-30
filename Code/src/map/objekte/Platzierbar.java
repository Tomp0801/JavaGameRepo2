package map.objekte;

import java.util.ArrayList;
import java.util.HashMap;

import map.Feld;
import ressource.Ressource;

/**
 * Interface f�r Objekte, die auf einem Feld platzierbar sein sollen
 * z.B. Pflanzen, Geb�ude...
 * 
 * @author Thomas
 *
 */
public interface Platzierbar {
	/**
	 * l�sst das Objekt berechnen, was es alles in der Zeit seit der letzten Berechnung 
	 * produziert hat.
	 * @return eine HashMap mit den Materialien und ihren Mengen, die gewonnen wurden durch die Produktion
	 */
	public HashMap<Ressource, Double> run();
	
	/**
	 * gibt zur�ck, welche Materialien hier gewonnen werden
	 * @return eine ArrayList von Materialien
	 */
	public ArrayList<Ressource> getOutputs();
	
	/**
	 * gibt zur�ck, welche Materialien bei der Produktion verbraucht werden
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
