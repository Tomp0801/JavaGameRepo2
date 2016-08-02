package himmelskoerper;

import java.util.LinkedList;

public class SchwarzesLoch extends Himmelskoerper implements Orbitable {
	/**
	 * Liste alle Sterne, die um das Schwarze Loch kreisen
	 * in aufsteigender Reihenfolge nach dem orbitRadius
	 */
	private LinkedList<Stern> sterne;
	
	public SchwarzesLoch(double masse, float radius) {
		super(masse, radius, Constants.FEST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getMasse() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(InOrbit objectInOrbit) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public double getSystemRadius() {
		Stern aeussersterStern = sterne.getLast();		//Mond mit größter Umlaufbahn
		return aeussersterStern.getOrbitRadius() + aeussersterStern.getRadius() / 2;
	}

}
