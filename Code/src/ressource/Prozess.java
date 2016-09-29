package ressource;

import java.util.HashMap;

/**
 * Ein Prozess braucht bestimmte Vorraussetzungen, die durch die Umgebung oder ein Geb�ude
 * erf�llt werden k�nnen.
 * Au�erdem werden input Materialien ben�tigt
 * 
 * Sind die Vorraussetzungen erf�llt, wird das Material in das entsprechende 
 * Material umgewandelt
 * 
 * @author Thomas
 *
 */
public class Prozess {
	/**
	 * ben�tigte Temperatur f�r den Prozess
	 */
	private int temperatur;
	
	/**
	 * ben�tigte zus�tze (zum beispiel luft, Wasser...) f�r den Prozess
	 * mit der ben�tigten Menge in Relation zu dem Hauptmaterial (kann variieren)
	 */
	private HashMap<Material, Float> zusatz;

	/**
	 * Prozentualer Anteil, der von der Input Masse als Output ankommt
	 */
	private float outputAnteil;
}