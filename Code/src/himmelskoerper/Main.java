package himmelskoerper;

/**
 * Klasse für reine Testzwecke
 * 
 * Soll ein Sonnensystem mit Stern und Planeten und Monden sein
 * 
 * @author Thomas
 *
 */
public class Main {
	
	public static void main(String[] args) {
		/**ffg
		 * Sonne des Sonnensystems
		 */
		Stern sonne;

		double masse = (1.989 * Math.pow(10, 30)); 
		float radius = 695700;
		sonne = new Stern(null, 0, masse, radius);		//Sonne nicht in einem Orbit
		
		//Planeten hinzufügen
		FestPlanet erde = new FestPlanet(sonne, 149600000, 5.972 * Math.pow(10, 24));
		
		Mond mond = new Mond(erde, 384400, 7.349 * Math.pow(10, 22));
		
		//"programmschleife"
		while (true) {
			erde.refresh();
			System.out.println("Erde: ");
			erde.printStatus();
			
			mond.bewegen();
			System.out.println("Mond: ");
			mond.printStatus();
			
			sonne.bewegen();
			System.out.println("Sonne: ");
			sonne.printStatus();
			
			System.out.println("___________________________________________________________");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
