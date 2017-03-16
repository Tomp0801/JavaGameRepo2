package draufsicht.himmelskoerper;

import java.util.Iterator;
import java.util.LinkedList;

import draufsicht.global.Agregat;

/**
 * Ein Himmelskörper, bewohnbar, wenn fest
 * Umkreist einen Stern
 * 
 * Gibt auch Planeten ohne Sonne : Planemos
 * 
 * @author Thomas
 * @version 1.0
 */
public abstract class Planet extends InOrbit implements Orbitable
{
	/**
	 * Liste der Monde des Planeten
	 */
	LinkedList<InOrbit> monde;
	
	/**
	 * Konstruktor 
	 * @param bezugsKoerper
	 * @param distanz
	 * @param masse
	 * @param typ der Typ des Planeten (gas oder fest)
	 */
	public Planet(Stern bezugsKoerper, double distanz, double masse, double radius, Agregat art) {
		super(bezugsKoerper, distanz, masse, radius, art);
		
		monde = new LinkedList<InOrbit>();
	}
	
	public Planet(Stern bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
		
		monde = new LinkedList<InOrbit>();
		generateChildren();
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
			Iterator<InOrbit> iterator = monde.iterator();
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
		Mond aeussersterMond = (Mond)monde.getLast();		//Mond mit größter Umlaufbahn
		return aeussersterMond.getOrbitRadius() + aeussersterMond.getRadius() / 2;
	}
	
	@Override
	public LinkedList<InOrbit> getChildren() {
		return monde;
	}
	
	@Override
	public InOrbit getChild(int index) {
		if (index < monde.size()) {
			return monde.get(index);
		} else {
			return null;
		}
	}
}
