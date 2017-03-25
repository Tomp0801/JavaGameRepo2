package spielmodel.spielermodel.charakter;

import personensicht.model.gameObjekte.lebewesen.mensch.Koerper;

/**
 * Die Avatar Klasse beschreibt den Avatar des Spielers
 * 
 * @author Dennis
 *
 */
public class Avatar {

	/**
	 * Name des Avatars
	 */
	private String name = "Avatarname";
	
	private Koerper koerper = new Koerper();
	
	/**
	 * verweist auf die Datei der Position des Avatars
	 */
	private String regionPosition;
	
	/**
	 * erstellt einen Default Avatar 
	 */
	public Avatar(){
		//setze den Avatar auf eine Position
	}
	
	/**
	 * erstellt einen Avatar mit den Eigenschaften aus einer Datei
	 * @param path Weg zur Datei, aus der dieser Avatar geladen werden soll
	 */
	public Avatar(String path){
		//lade den Avatar TODO
	}
}
