package himmelskoerper;

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
	public FestPlanet(Stern bezugsKoerper, double distanz, double masse) {
		super(bezugsKoerper, distanz, masse, Constants.FEST);
		
		int breite, hoehe;
		//Größe der Karte aus dem Radius ermitteln
		//breite = Umfang; TODO hoehe vielleicht ändern
		breite = (int) Math.round(2 * Math.PI * this.getRadius());
		hoehe = breite;
		
		//TODO Karte erstellen mit (Typ) und Bodenschaetzen
		//karte = new Karte(breite, hoehe);
	}

	@Override
	public Karte getKarte() {
		return karte;
	}

}
