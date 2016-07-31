package himmelskoerper;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Ein Himmelsk�rper, bewohnbar, wenn fest
 * Umkreist einen Stern
 * 
 * TODO rotation kl�ren
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
	 * f�gt Monde aufsteigend sortiert nach der Distanz zum Planeten ein
	 * @param onjectInOrbit der Mond, der hinzugef�gt werden soll
	 */
	@Override
	public void add(InOrbit objectInOrbit) {
		Mond newMoon = (Mond) objectInOrbit;
		
		if (monde.isEmpty()) {		//wenn die Liste noch leer ist
			monde.add(newMoon);
		} else {		//wenn schon Monde vorhanden sind
			Iterator<Mond> iterator = monde.iterator();
			int index = 0;
											//solange die Distanz zum neuen Mond kleiner ist als die des n�chsten
			while (iterator.hasNext() && newMoon.getOrbitRadius() < iterator.next().getOrbitRadius()) {
				index++;		//index wird parallel mitgez�hlt
			}
			if (index >= monde.size()) {	//wenn ganz bis zum Ende gelaufen bei der Suche
				monde.addLast(newMoon);	//hinten anf�gen
			} else {
				monde.add(index, newMoon);
			}
		}
	}
	
	@Override
	public double getSystemRadius() {
		Mond aeussersterMond = monde.getLast();		//Mond mit gr��ter Umlaufbahn
		return aeussersterMond.getOrbitRadius() + aeussersterMond.getRadius() / 2;
	}
	
}
