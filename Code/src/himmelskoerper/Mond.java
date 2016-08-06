package himmelskoerper;

import global.Agregat;

/**
 * Ein Himmelskörper, bewohnbar, wenn fest
 * Umkreist einen Planeten
 * 
 * @author Thomas
 * @version 1.0
 */
public class Mond extends InOrbit
{

	public Mond(Planet bezugsKoerper, double distanz, double masse, double radius) {
		super(bezugsKoerper, distanz, masse, radius, Agregat.FEST);
		// TODO Auto-generated constructor stub
	}
	
	public Mond(Planet bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
	}

	@Override
	protected void generate() {
		/*
		 * Quelle für Monddaten: 
		 * https://de.wikipedia.org/wiki/Liste_der_Monde_von_Planeten_und_Zwergplaneten#Tabelle_mit_Daten
		 * Monde aus unserem Sonnensystem
		 * radius : 0.25 - 2631 km
		 * masse : < 1,5*10^12 - 1,5*10^23 kg
		 * Abstand zu Planet : 9.378 - 48.387.000 km (die größten 300.000 - 2.000.000)
		 */ 
		
		// TODO Auto-generated method stub
		
	}

}
