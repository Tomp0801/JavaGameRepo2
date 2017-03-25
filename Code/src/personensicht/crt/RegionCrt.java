package personensicht.crt;

import personensicht.model.welt.map.Region;

/**
 * bietet Methoden zur veraltungen von Regionen an
 * @author Dennis
 *
 */
public class RegionCrt {

	private static RegionCrt instance; 
	
	private RegionCrt(){
		instance = this; 
	}
	
	public static RegionCrt getInstance(){
		if (instance == null){
			new RegionCrt();
		}
		return instance;
	}
	
	
	/**
	 * Wenn eine Region bereits in diesem Spiel geladen wurde, dann gebe true zureuck
	 */
	public Boolean exsistRegion(long id){ //eine ID muss mit uebergeben werden.
									//Ist die Region bereits geladen wurden, dann ist die ID in einer dazugehoerigen 
									//Textdatei abgespichert
		
		//oeffne textdatei
		//schaue seystematisch nach ob in dieser Datei die ID vergeben ist
		//TODO entwicklung einer ID Erkennung
		return true;
	}
	
	/**
	 * generriert eine neue Region
	 * @return
	 */
	private Region generriereRegion(long id){ //Typ der Region muss uebergeben werden. (Raumschiff, Haus, Miene, ect.) 
		//welche Regionstyp soll erstellt werden? 
		//ist der Typ bekannt, soll aus einer Datei(noch nicht erstellt) 
		//eine zufallige Variante des Regionstyp erstellt werden
		
		return new Region(); //TODO
	}
}
