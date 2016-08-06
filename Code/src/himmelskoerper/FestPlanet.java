package himmelskoerper;

import global.Agregat;
import map.Karte;

/**
 * Ein Planet aus Festen Materialien
 * Betretbar, theoretisch bewohn- und bebaubar
 * 
 * @author Thomas
 *
 */
public class FestPlanet extends Planet implements Betretbar {
	/**
	 * Karte, die betreten werden kann
	 */
	private Karte karte;
	
	/**
	 * Konstruktor
	 * 
	 * @param bezugsKoerper
	 * @param distanz
	 * @param masse
	 */
	public FestPlanet(Stern bezugsKoerper, double distanz, double masse, double radius) {
		super(bezugsKoerper, distanz, masse, radius, Agregat.FEST);
		
		int breite;
		//Größe der Karte aus dem Radius ermitteln
		//breite = Umfang;
		breite = (int) Math.round(2 * Math.PI * this.getRadius());
		
		//TODO Karte erstellen mit (Typ) und Bodenschaetzen
		//karte = new Karte(breite, hoehe);
	}
	
	public FestPlanet(Stern bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
	}

	@Override
	public Karte getKarte() {
		return karte;
	}

	@Override
	protected void generate() {
		/*
		 * Mittlere Materiedichte: p = 3 m/ (4* pi* r^3)
		 * p >= 3 g/cm^3 für FestPlaneten
		 */
		// TODO Auto-generated method stub
		
	}

}
