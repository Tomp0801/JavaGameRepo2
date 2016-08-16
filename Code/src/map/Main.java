package map;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import global.Random;
import himmelskoerper.FestPlanet;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;
import speicherverwaltung.Deserializer;
import speicherverwaltung.RessourcenObjekte;
import speicherverwaltung.Serializer;

/**
 * Main Klasse für reine Testzwecke
 * @author Thomas
 *
 */
public class Main {

	public static void main(String[] args) {		
		SchwarzesLoch SL = new SchwarzesLoch(342);
		
		Stern sonne = (Stern)SL.getChild(0);
		int i = 0;
		while (!sonne.getChild(i).getClass().equals(FestPlanet.class)) {
			i++;
		}
		FestPlanet planet = (FestPlanet) sonne.getChild(i);
		
		
		for (int j = 0; j < planet.getKarte().getBodenschaetze().size(); j++) {
			System.out.println(planet.getKarte().getBodenschaetze().get(j).getName());
		}
		
		for (BodenMaterial b : planet.getKarte().getBereich(0, 0).getFeld(3, 3).getRohstoffe().keySet()) {
			System.out.println(b.getName() + ": " + planet.getKarte().getBereich(0, 0).getFeld(3, 3).getRohstoffe().get(b));
		}
		
		//Programmschleife
		while (true) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
