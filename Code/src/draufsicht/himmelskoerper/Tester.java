package draufsicht.himmelskoerper;

import java.util.Iterator;

import draufsicht.global.GameTime;

/**
 * Klasse für reine Testzwecke
 * 
 * Soll ein Sonnensystem mit Stern und Planeten und Monden sein
 * 
 * @author Thomas
 *
 */
public class Tester {
	

	int seed = 5;
	
	SchwarzesLoch SL = new SchwarzesLoch(seed);

	public Tester()
	{
		System.out.println("Anzahl Sterne: " + SL.getChildren().size());
		int planeten = 0;
		Stern currentStern;
		int monde = 0;
		Planet currentPlanet;
		for (Iterator<InOrbit> it = SL.getChildren().iterator(); it.hasNext();) {
			currentStern = (Stern) it.next();
			planeten = planeten + currentStern.getChildren().size();
			
			for (Iterator<InOrbit> it2 = currentStern.getChildren().iterator(); it2.hasNext();) {
				currentPlanet = (Planet) it2.next();
				monde = monde + currentPlanet.getChildren().size();
			}
		}
		System.out.println("Anzahl Planeten: " + planeten);
		System.out.println("Anzahl Monde: " + monde);
		
		Mond currentMond;
		long time;
		//"programmschleife"
		while (true) {
					
			time = System.currentTimeMillis();
			for (Iterator<InOrbit> itSterne = SL.getChildren().iterator(); itSterne.hasNext();) {
				currentStern = (Stern) itSterne.next();
				currentStern.bewegen();
				
				for (Iterator<InOrbit> itPlaneten = currentStern.getChildren().iterator(); itPlaneten.hasNext();) {
					currentPlanet = (Planet) itPlaneten.next();
					currentPlanet.bewegen();
					for (Iterator<InOrbit> itMonde = currentPlanet.getChildren().iterator(); itMonde.hasNext();) {
						currentMond = (Mond) itMonde.next();
						currentMond.bewegen();
					}
				}
			}
			
			currentStern = (Stern)(SL.getChild(0));
			currentPlanet = (Planet) currentStern.getChild(0);
			currentMond = (Mond)currentPlanet.getChild(0);
			
			System.out.println("SchwarzesLoch:");
			SL.printStatus();
			System.out.println("Stern:");
			currentStern.printStatus();
			System.out.println("Planet:");
			currentPlanet.printStatus();
			System.out.println("Mond:");
			currentMond.printStatus();
			
			System.out.println("Alle bewegungen berechnet in: " + (System.currentTimeMillis() - time) + " Millisekunden");
			System.out.println(GameTime.getInstance().timeMillis());
			System.out.println("___________________________________________________________");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
