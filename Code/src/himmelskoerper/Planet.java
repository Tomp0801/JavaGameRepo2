package himmelskoerper;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Ein Himmelskörper, bewohnbar, wenn fest
 * Umkreist einen Stern
 * 
 * TODO rotation klären
 * 
 * @author Thomas
 * @version 1.0
 */
public class Planet extends InOrbit implements Orbitable
{
	/**
	 * Liste der Monde des Planeten
	 */
	LinkedList<Mond> monde;
	
	/**
	 * Konstruktor 
	 * @param bezugsKoerper
	 * @param distanz
	 * @param masse
	 * @param typ der Typ des Planeten (gas oder fest)
	 */
	public Planet(Stern bezugsKoerper, double distanz, double masse, String art) {
		super(bezugsKoerper, distanz, masse, art);
		
		monde = new LinkedList<Mond>();
	}

	/**
	 * berechnet die aktuelle Position und Ausrichtung des Planeten neu
	 */
	public void refresh()
	{
		this.bewegen();  //position aktualisieren
		//TODO Rotation
	}

	/**
	 * fügt Monde aufsteigend sortiert nach der Distanz zum Planeten ein
	 * @param onjectInOrbit der Mond, der hinzugefügt werden soll
	 */
	@Override
	public void add(InOrbit objectInOrbit) {
		Mond newMoon = (Mond) objectInOrbit;
		
		if (monde.isEmpty()) {		//wenn die Liste noch leer ist
			monde.add(newMoon);
		} else {		//wenn schon Monde vorhanden sind
			Iterator<Mond> iterator = monde.iterator();
			int index = 0;
											//solange die Distanz zum neuen Mond kleiner ist als die des nächsten
			while (iterator.hasNext() && newMoon.getOrbitRadius() < iterator.next().getOrbitRadius()) {
				index++;		//index wird parallel mitgezählt
			}
			if (index >= monde.size()) {	//wenn ganz bis zum Ende gelaufen bei der Suche
				monde.addLast(newMoon);	//hinten anfügen
			} else {
				monde.add(index, newMoon);
			}
		}
	}
	
	@Override
	public double getSystemRadius() {
		Mond aeussersterMond = monde.getLast();		//Mond mit größter Umlaufbahn
		return aeussersterMond.getOrbitRadius() + aeussersterMond.getRadius() / 2;
	}
	
}
