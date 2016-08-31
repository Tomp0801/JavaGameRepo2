package himmelskoerper;

import global.Agregat;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import map.Karte;

/**
 * Ein Himmelskörper, bewohnbar, wenn fest
 * Umkreist einen Planeten
 * 
 * @author Thomas
 * @version 1.0
 */
public class Mond extends InOrbit implements Betretbar
{
	/**
	 * Die Karte der Oberfläche des Mondes
	 */
	private Karte karte;

	/**
	 * Manueller Konstruktor
	 * 
	 * @param bezugsKoerper
	 * @param distanz
	 * @param masse
	 * @param radius
	 */
	public Mond(Planet bezugsKoerper, double distanz, double masse, double radius) {
		super(bezugsKoerper, distanz, masse, radius, Agregat.FEST);
		
		int breite = getKartenBreite();		
		karte = new Karte(this, breite, breite, getPRNG().randomInt());
	}
	
	/**
	 * Zufalls Konstruktor
	 * 
	 * @param bezugsKoerper
	 * @param seed
	 */
	public Mond(Planet bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
		int breite = getKartenBreite();
		karte = new Karte(this, breite, breite, getPRNG().randomInt());
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
		
		double maxMasse = 1.5 * Math.pow(10, 23);	
		double minMasse = 1.5 * Math.pow(10, 12);					
		double maxRadius = 2631;
		double minRadius = 0.25;
														
		
		//Masse Zufällig
		double masse = getPRNG().random(minMasse, maxMasse);
		//Radius passend zur masse
		double radius = getPRNG().random(minRadius, maxRadius);
		//Temperatur dieselbe wie bezugsPlanet
		float temperatur = this.getBezugsKoerper().getOberflaechenTemperatur();
		
		setMasse(masse);
		setRadius(radius);
		setOberflaechenTemperatur(temperatur);
		setArt(Agregat.FEST);
		
		//Distanz zum bezugsKoerper zufällig
		//TODO nicht zufällig?
		//TODO passend einstellen
		double distanz = getPRNG().random(getBezugsKoerper().getRadius() * 10, getBezugsKoerper().getRadius() * 100);//distanz zwischen 10 und 100 fachem des bezugsKörper-radius
		
		setOrbitRadius(distanz);
		
	}

	@Override
	public Karte getKarte() {
		return karte;
	}

	@Override
	public int getKartenBreite() {
		int breite;
		//Größe der Karte aus dem Radius ermitteln
		//breite = Umfang; Karte quadratisch (der einfachheit halber)
		//ein planet so groß wie die erde soll in etwa eine Karte von 10 * 10 haben, deswegen '/ 4000'
		breite = (int) Math.round(2 * Math.PI * this.getRadius() / 4000);
		return breite;
	}

	@Override
	public PhongMaterial getAussehn() {
		 PhongMaterial material = new PhongMaterial();
		    material.setDiffuseColor(Color.GREY);
		    material.setSpecularColor(Color.GREY);
			return material;
	}

}
