package map;

import java.util.ArrayList;

import map.objekte.Mine;
import ressource.BodenMaterial;
import speicherverwaltung.IOHandler;

/**
 * Tester Klasse für reine Testzwecke
 * @author Thomas
 *
 */
public class Tester {

	public Tester() {		
		System.out.println("starte Tester in map paket");
		
		int seed = 10;
		Karte karte = new Karte(null, 10, 10, seed);
		ArrayList<BodenMaterial> buffer = IOHandler.getInstance().readArrayList("bodenschaetze");
		Mine mine1 = new Mine(buffer.get(0), (float)0.5);
		mine1.platziere(karte.getBereich(0, 0).getFeld(0, 0));
		
		System.out.println(mine1.getName());
		System.out.println(mine1.getArt().getName());
		for (BodenMaterial b : mine1.getFeld().getBodenschatzVorkommen().keySet())
		{
			System.out.println(b.getName() + ": " + mine1.getFeld().getBodenschatzVorkommen().get(b));
		}
		
		System.out.println("working: "+mine1.isWorking());
				
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
