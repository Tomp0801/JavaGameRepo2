package map;

import java.util.HashMap;

/**
 * Main Klasse für reine Testzwecke
 * @author Thomas
 *
 */
public class Main {
	static final Standards standards = new Standards();

	public static void main(String[] args) {		
		Karte testKarte = new Karte(10, 10, standards.getBodenschaetze());
		
		Bereich testBereich = testKarte.getBereich(0, 0);
		Feld testFeld;
		HashMap<Bodenschatz, Float> bodenschaetze;
		int count = 0;
		
		for (int x = 0; x < testBereich.BREITE; x++) {
			for (int y = 0; y < testBereich.HOEHE; y++) {
				testFeld = testBereich.getFeld(x, y);
				bodenschaetze = testFeld.getRohstoffe();
				for (Bodenschatz b : bodenschaetze.keySet()) {
					System.out.println(count + ": " + b.getName());
					count++;
				}
			}
		}
		
		/*
		//Programmschleife
		while (true) {
			
		}
		*/
	}

}
