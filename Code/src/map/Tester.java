package map;

import himmelskoerper.FestPlanet;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;

/**
 * Tester Klasse für reine Testzwecke
 * @author Thomas
 *
 */
public class Tester {

	public Tester() {		
		System.out.println("starte Tester");
		SchwarzesLoch SL = new SchwarzesLoch(81776);
		
		Stern sonne = (Stern)SL.getChild(0);
		int i = 0;
		while (!sonne.getChild(i).getClass().equals(FestPlanet.class)) {
			i++;
		}
		FestPlanet planet = (FestPlanet) sonne.getChild(i);
		
		System.out.println("Karte: varietaet: " + planet.getKarte().getVarietaet());
		for (int j = 0; j < planet.getKarte().getBodenschaetze().size(); j++) {
			System.out.println(planet.getKarte().getBodenschaetze().get(j).getName());
		}
		for (int j = 0; j < planet.getKarte().getBodenarten().size(); j++) {
			System.out.println(planet.getKarte().getBodenarten().get(j).getName());
		}
//		System.out.println("Bereiche: ");
//		for (int x = 0; x < planet.getKarte().getBreite(); x++)
//		{
//			for (int y = 0; y < planet.getKarte().getHoehe(); y++)
//			{
//				System.out.println("Bereich " + x  + " / "+ y + ": ");
//				for (BodenMaterial b : planet.getKarte().getBereich(x, y).getBodenschaetze().keySet()) {
//					System.out.println(b.getName() + ": " + planet.getKarte().getBereich(x, y).getBodenschaetze().get(b));
//				}
//			}
//		}
		
		
		//Programmschleife
		while (true) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
