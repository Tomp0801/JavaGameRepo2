package personensicht.crt;

/**
 * Ein Controller der die Klassen in der Personensicht Kontrolliert
 * @author Dennis
 *
 */
public class PersonensichtCtr {
	
	private static PersonensichtCtr instance; 
	private static RegionCrt regionCrt;
	
	private PersonensichtCtr(){
		instance = this;
		regionCrt = RegionCrt.getInstance();
	}
	
	public static PersonensichtCtr getInsance(){
		if (instance == null){
			new PersonensichtCtr();	
		}
		return instance;
	}
	
	/**
	 * startet das Spiel in der Personensicht
	 */
	public void startGame(){
		//TODO startet ein DemoSpiel
		//lade die Region, auf dem der Avatar befindet 
		//oder starte ein neues Spiel 
	}

	/**
	 * oeffnet eine Region
	 * @param path der Pfad zur Regionsdatei 
	 */
	private void oeffneRegion(String path){
		//gebe den Befehl weiter zum RegionsCrt
	}
}
