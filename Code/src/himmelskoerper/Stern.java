package himmelskoerper;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * Ein Stern, um den Planeten Kreisen können
 * 
 * @author Thomas
 * @version 0.0
 */
public class Stern extends InOrbit implements Orbitable
{
	/**
	 * Liste der Planeten, die den Stern umkreisen
	 */
	LinkedList<Planet> planeten;
	
	/**
	 * Konstruktor
	 * 
	 * @param masse sets masse of Stern
	 * @param radius sets radius of Stern
	 */
	Stern(SchwarzesLoch bezugsKoerper, double distanz, double masse, float radius) {
		//TODO radius
		super(bezugsKoerper, distanz, masse, Constants.GAS);
		
		planeten = new LinkedList<Planet>();
	}

	/**
	 * fügt Planeten aufsteigend sortiert nach der Distanz zur Sonne ein
	 * @param onjectInOrbit der Planet, der hinzugefügt werden soll
	 */
	@Override
	public void add(InOrbit objectInOrbit) {
		Planet newPlanet = (Planet) objectInOrbit;
		
		if (planeten.isEmpty()) {		//wenn die Liste noch leer ist
			planeten.add(newPlanet);
		} else {		//wenn schon Planeten vorhanden sind
			Iterator<Planet> iterator = planeten.iterator();
			int index = 0;
											//solange die Distanz zum neuen Planeten kleiner ist als die des nächsten
			while (iterator.hasNext() && newPlanet.getOrbitRadius() < iterator.next().getOrbitRadius()) {
				index++;		//index wird parallel mitgezählt
			}
			if (index >= planeten.size()) {	//wenn ganz bis zum Ende gelaufen bei der Suche
				planeten.addLast(newPlanet);
			} else {
				planeten.add(index, newPlanet);
			}
		}

	}

}
