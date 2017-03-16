package gameMaker.model;

import java.io.File;
import java.util.ArrayList;

import gameMaker.view.GameObjektZuordnung;
import ioHandler.SerializableNew;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.welt.map.Region;

public class RegionMaker implements SerializableNew{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2989151760861971508L;

	/**
	 * falls aenderungen an der Region vorgenommen wurde, muss das durtyBit auf true gesetzt werden, um 
	 * bei eventuellen neu laden oder Beenden ect. Nach dem Speichern gefragt werden kann. 
	 */
	private Boolean durtyBit = false; 
	
	private Region region = new Region();
	
	/**
	 * enthaehlt den weg zum Verzeichnis in dem gespeichert wird.
	 */
	private String verzeichnisPath = System.getProperty("user.home"); //TODO 
	
	/**
	 * der String enthaelt den namen der Datei, die verwendet wird um die Region zu speichern
	 */
	private File fileRegion;
	
//	/**
//	 * hier werden die GameObjekte drin gespeichert, die sich auf der Region befinden
//	 */
//	private transient ArrayList<GameObjektZuordnung> gameObjekteOnRegion = new ArrayList<GameObjektZuordnung>(); 

//	public synchronized ArrayList<GameObjektZuordnung> getGameObjekteOnRegion() {
//		return gameObjekteOnRegion;
//	}

//	public synchronized void setGameObjekteOnRegion(ArrayList<GameObjektZuordnung> gameObjekteOnRegion) {
//		this.gameObjekteOnRegion = gameObjekteOnRegion;
//	}
	
	public synchronized Boolean getDurtyBit() {
		return durtyBit;
	}

	public synchronized String getVerzeichnisPath() {
		return verzeichnisPath;
	}

	public synchronized File getFileRegion() {
		return fileRegion;
	}

	public synchronized void setDurtyBit(Boolean durtyBit) {
		this.durtyBit = durtyBit;
	}

	public synchronized void setVerzeichnisPath(String verzeichnisPath) {
		this.verzeichnisPath = verzeichnisPath;
	}

	public synchronized void setFileRegion(File fileRegion) {
		this.fileRegion = fileRegion;
	}
	
	public synchronized Region getRegion() {
		return region;
	}

	public synchronized void setRegion(Region region) {
		this.region = region;
	}


	@Override
	public void serializ() {
		region.serializ();		
	}

	@Override
	public void deserializ() {
		region.deserializ();
//		gameObjekteOnRegion = new ArrayList<GameObjektZuordnung>();
//		for (int i = 0; region.getChildren().size() > i ; i++){
//			
//		}
	}

	/**
	 * remove a GameObjekt from the Modelclass 
	 * @param objekt
	 */
	public void removeGameObjekt(GameObjekt objekt) {
		this.region.getChildren().remove(objekt);//TODO eventuell noch AuswahlObjekt entferen
	}
}
