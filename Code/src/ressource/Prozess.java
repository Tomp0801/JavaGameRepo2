package ressource;

import java.util.HashMap;

/**
 * Ein Prozess braucht bestimmte Vorraussetzungen, die durch die Umgebung oder ein Gebäude
 * erfüllt werden können.
 * Außerdem werden input Materialien benötigt
 * 
 * Sind die Vorraussetzungen erfüllt, wird das Material in das entsprechende 
 * Material umgewandelt
 * 
 * @author Thomas
 *
 */
public class Prozess {
	/**
	 * benötigte Temperatur für den Prozess
	 */
	private int temperatur;
	
	/**
	 * benötigte zusätze (zum beispiel luft, Wasser...) für den Prozess
	 * mit der benötigten Menge in Relation zu dem Hauptmaterial (kann variieren)
	 */
	private HashMap<Material, Float> zusatz;

	/**
	 * Prozentualer Anteil, der von der Input Masse als Output ankommt
	 */
	private float outputAnteil;
}