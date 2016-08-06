package himmelskoerper;

import java.util.Iterator;
import java.util.LinkedList;

import global.Agregat;
import global.Constants;


/**
 * Ein Stern, um den Planeten Kreisen k�nnen
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
	public Stern(SchwarzesLoch bezugsKoerper, double distanz, double masse, double radius) {
		super(bezugsKoerper, distanz, masse, radius, Agregat.GAS);
		
		planeten = new LinkedList<Planet>();
	}
	
	public Stern(SchwarzesLoch bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
		
		planeten = new LinkedList<Planet>();
	}

	/**
	 * f�gt Planeten aufsteigend sortiert nach der Distanz zur Sonne ein
	 * @param onjectInOrbit der Planet, der hinzugef�gt werden soll
	 */
	@Override
	public void add(InOrbit objectInOrbit) {
		Planet newPlanet = (Planet) objectInOrbit;
		
		if (planeten.isEmpty()) {		//wenn die Liste noch leer ist
			planeten.add(newPlanet);
		} else {		//wenn schon Planeten vorhanden sind, sortiert einf�gen
			Iterator<Planet> iterator = planeten.iterator();
			int index = 0;
											//solange die Distanz zum neuen Planeten kleiner ist als die des n�chsten
			while (iterator.hasNext() && newPlanet.getOrbitRadius() < iterator.next().getOrbitRadius()) {
				index++;		//index wird parallel mitgez�hlt
			}
			if (index >= planeten.size()) {	//wenn ganz bis zum Ende gelaufen bei der Suche
				planeten.addLast(newPlanet);
			} else {
				planeten.add(index, newPlanet);
			}
		}
	}
	
	@Override
	public double getSystemRadius() {
		Planet aeussersterPlanet = planeten.getLast();		//Planet mit gr��ter Umlaufbahn
		return aeussersterPlanet.getOrbitRadius() + aeussersterPlanet.getRadius() / 2;
	}

	@Override
	protected void generate() {
		/*
		 * Wikipedia : 
		 * Die Oberfl�chentemperaturen von Hauptreihensternen reichen von etwa 2200 K bis 45.000 K, 
		 * ihre Massen von 0,07 bis 120 Sonnenmassen 
		 * und ihre Radien von 0,1 bis 25 Sonnenradien. 
		 * Rote Riesen sind deutlich k�hler und k�nnen so gro� werden, dass die komplette Erdbahn in ihnen Platz h�tte. 
		 * Wei�e Zwerge haben Temperaturen bis zu 100.000 K = 99726,85�C, sind aber nur so klein wie die Erde, 
		 * obwohl ihre Masse mit der der Sonne vergleichbar ist.
		 */
		
		double maxMasse = 120 * Constants.M;	
		double minMasse = 0.07 * Constants.M;					
		double maxRadius = 25 * 695700; 	//Sonnenradius = 695.700 km
		double minRadius = 0.1 * 695700;
														
		
		//Masse Zuf�llig
		double masse = getRandom() * (maxMasse - minMasse) + minMasse;
		//Radius zuf�llig
		double radius = getRandom() * (maxRadius - minRadius) + minRadius;
		//grob : temperatur T ~ Volumen/Masse 
		float maxTemperatur = (float) ((4.0/3.0 * Math.PI * Math.pow(maxRadius, 3)) / maxMasse);	//maximal m�gliche Temperatur
		float temperatur = (float) ((4.0/3.0 * Math.PI * Math.pow(radius, 3)) / masse);
		//prozentZahl zur max. m�glichen Temp., mal max. reale Temp.
		temperatur = (float)(temperatur / maxTemperatur * 99726.85);
		
		setMasse(masse);
		setRadius(radius);
		setOberflaechenTemperatur(temperatur);
		setArt(Agregat.GAS);
		
		//Distanz zum bezugsKoerper zuf�llig
		//TODO nicht zuf�llig?
		double distanz = (getBezugsKoerper().getRadius() * 10) + getBezugsKoerper().getRadius() * getRandom() * 90;	//distanz zwischen 10 und 100 fachem des bezugsK�rper-radius
		
		setOrbitRadius(distanz);
	}

}
