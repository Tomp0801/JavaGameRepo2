package gameMaker.model;

/**
 * hier werden Daten gespeichert, die fuer den GameMaker von bedeutung sind. 
 * @author Dennis
 *
 */
public class GameMaker 
{
	/**
	 * der String enthaelt den namen der Datei, die verwendet wird um die Region zu speichern
	 */
	private String path;
	
	/**
	 * falls aenderungen an der Region vorgenommen wurde, muss das durtyBit auf true gesetzt werden, um 
	 * bei eventuellen neu laden oder Beenden ect. Nach dem Speichern gefragt werden kann. 
	 */
	private Boolean durtyBit = false; 
	
	/**
	 * @return ein String mit den Daten die gespeichert werden sollen
	 */
	public String save(){
		durtyBit = false;
		//TODO 
		//speichert deine Region
		return "default";
	}
}
