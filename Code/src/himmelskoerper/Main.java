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

		SchwarzesLoch SL = new SchwarzesLoch(123456789, 1234567);
		Stern sonne = new Stern(SL, 2435346);

		SL.add(sonne);

		//"programmschleife"
		while (true) {
					
			System.out.println("Anzahl Sterne: " + SL.getChildren().size());
			System.out.println("Anzahl Planeten: " + sonne.getChildren().size());
			
			System.out.println("Größe des Sonnensystems: " + sonne.getSystemRadius());
			
			
			sonne.printStatus();
			for (int i = 0; i < sonne.getChildren().size(); i++) {
				System.out.println(i);
				sonne.getChildren().get(i).printStatus();
			}
			
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
