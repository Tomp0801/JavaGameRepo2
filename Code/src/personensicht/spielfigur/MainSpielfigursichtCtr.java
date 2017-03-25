package personensicht.spielfigur;

/**
 * Boss kontrollerklasse fuer das Spiel aus der Sicht einer Spielfigur
 * @author Dennis
 *
 */
public class MainSpielfigursichtCtr {
	
	private static MainSpielfigursichtCtr instance; 
	
	private MainSpielfigursichtCtr(){
		instance = this; 
	}
	
	
	public static MainSpielfigursichtCtr getInstance(){
		if (instance == null){
			new MainSpielfigursichtCtr();
		}
		return instance; 
	}
	
	/**
	 *setzt eine Scene auf die aktuelle Stage
	 */
	public void startSceneSpielfugur(){ //TODO Die Spielfigur muss noch uebergeben werden
		//lade oder generiere die Region auf die sich die Spielfigur befindet
		//setze die Scene
	}
}
