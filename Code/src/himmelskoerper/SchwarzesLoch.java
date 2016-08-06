package himmelskoerper;

import java.util.Iterator;
import java.util.LinkedList;

import global.Agregat;
import global.Constants;

public class SchwarzesLoch extends Himmelskoerper implements Orbitable {
	/**
	 * Liste alle Sterne, die um das Schwarze Loch kreisen
	 * in aufsteigender Reihenfolge nach dem orbitRadius
	 */
	private LinkedList<InOrbit> sterne;
	
	public SchwarzesLoch(double masse, float radius) {
		super(masse, radius, Agregat.FEST);
		
		sterne = new LinkedList<InOrbit>();
	}
	
	public SchwarzesLoch(int seed) {
		super(seed);
		
		sterne = new LinkedList<InOrbit>();
		
		generate();
	}

	@Override
	public void add(InOrbit objectInOrbit) {
		Stern newStern = (Stern) objectInOrbit;
		
		if (sterne.isEmpty()) {		//wenn die Liste noch leer ist
			sterne.add(newStern);
		} else {		//wenn schon Planeten vorhanden sind, sortiert einfügen
			Iterator<InOrbit> iterator = sterne.iterator();
			int index = 0;
											//solange die Distanz zum neuen Planeten kleiner ist als die des nächsten
			while (iterator.hasNext() && newStern.getOrbitRadius() < iterator.next().getOrbitRadius()) {
				index++;		//index wird parallel mitgezählt
			}
			if (index >= sterne.size()) {	//wenn ganz bis zum Ende gelaufen bei der Suche
				sterne.addLast(newStern);
			} else {
				sterne.add(index, newStern);
			}
		}
		
	}
	
	@Override
	public double getSystemRadius() {
		Stern aeussersterStern = (Stern)sterne.getLast();		//Stern mit größter Umlaufbahn
		return aeussersterStern.getOrbitRadius() + aeussersterStern.getRadius() / 2;
	}

	@Override
	protected void generate() {
		double maxMasse = Math.pow(10, 10) * Constants.M;	//Supermassereiche Schwarze Löcher : bis ~10^10 SonnenMassen (M)
		double minMasse = 10 * Constants.M;					//Stellare Schwarze Löcher : ~10 SonnenMassen
		double maxRadius = 400 * 149597870.7; 				//Supermassereiche Schwarze Löcher : bis 400 Astronomische Einheiten (149 597 870 700m)
															//Stellare Schwarze Löcher : ~30km
		
		//Masse Zufällig
		double masse = getPRNG().random(minMasse, maxMasse);
		//Radius aus Masse berechnen
		double radius = masse / maxMasse * maxRadius;
		setMasse(masse);
		setRadius(radius);
		setArt(Agregat.FEST);
	}

	@Override
	public void generateChildren() {
		//TODO wieviele Sterne?
		int numSterne = (int) getPRNG().random(0, 100);
		
		//Planeten generieren
		for (int i = 0; i <= numSterne; i++) {
			//Sterne werden mit Zufalls Konstruktor erstellt
			add(new Stern(this, getPRNG().randomInt()));
		}
	}

	@Override
	public LinkedList<InOrbit> getChildren() {
		return sterne;
	}

}
