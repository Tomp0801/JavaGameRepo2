package gameMaker.controll;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gameMaker.model.*;
import gameMaker.view.*;
import gameMaker.view.dialog.MapSizeDialog;
import javafx.stage.FileChooser;
import personensicht.model.gameObjekte.GameObjekt;

public class RegionMakerCrt {
	
	private static RegionMakerCrt instance;
	
	/**
	 * the view of the RegionMaker
	 */
	private RegionMakerScene regionMakerScene;
	
	/**
	 * the modell class RegionMaker
	 */
	private RegionMaker regionMaker;
	
	private RegionMakerCrt(){
		instance = this; 
		regionMaker = new RegionMaker();
		regionMakerScene = new RegionMakerScene();
	}
	
	/**
	 * loescht ein GameObjekt vom Model und von der View
	 * @param objekt
	 */
	public void removeGameOjekt(GameObjekt objekt){
		regionMaker.removeGameObjekt(objekt);
		regionMakerScene.removeGameObjekt(objekt);
	}

	/**
	 * gibt eine Instance ders Controlllers zuruck
	 * @return
	 */
	public static synchronized RegionMakerCrt getInstance() {
		if (instance == null){
			new RegionMakerCrt();
		}
		return instance;
	}
	
	/**
	 * startet den RegionMaker.
	 * Zum starten wird die Scene der View auf die Stage gesetzt.
	 */
	public void startGameMakerCrt(){
		StageCrt.getInstance().setScene(this.regionMakerScene.getScene());
	}
	
	
	/**
	 * erstellt eine Neue Region. 
	 * RegionMakerScene (viewclass) und RegionMaker (modelclass) werden neu erstellt.
	 * anschließend wird dies geladen, in dem die Methide startGameMakerCrt() aufgerufen wird. 
	 */
	public void erstelleNeueRegion(){
		//nach dem Speichern fragen, falls was geaendert wurde
		//oeffne eine Wizard um dort die Einstellungen fuer die neue Region fest zu legen
		regionMakerScene = new RegionMakerScene();
		regionMaker = new RegionMaker();
		startGameMakerCrt();
	}
	
	/**
	 * lade eine Region mit hilfe eines FileChoosers
	 */
   public void ladeRegion(){ 
	   //erstellt erst mal eine neue Region.
		//Oeffne ein Ordner in dennen Regionen gespeichert sind
		//nach dem auswahlen einer Datei, nach speichern fragen, falls was geaendert wurde
	   	FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Laden");
	    FileChooser.ExtensionFilter extFilter = 
               new FileChooser.ExtensionFilter("REGION" , "*.region");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialDirectory(
		            new File(regionMaker.getVerzeichnisPath()) 
		        );
		File file = fileChooser.showOpenDialog(StageCrt.getInstance().getStage());
	   
		if (file != null){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream (file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream (fis);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		    try {
			this.regionMaker = (RegionMaker) ois.readObject (); 
			regionMaker.deserializ();
			this.regionMakerScene = new RegionMakerScene(regionMaker);
			
		    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		    System.out.println("geladen");
		    erstelleNeueRegion();
		}
	}

   /**
	 * speichert die erstellte Region in die Datei, die vorher verwendet wurde
	 */
	public void fastSave(){
		//nur moeglich wenn was geandert wurde. Sonst ausgegraut oder nicht anklickbar
		//speichert unter den verwendeten namen
		if (regionMaker.getDurtyBit() == true){
			if (regionMaker.getFileRegion() != null){
				speichern(regionMaker.getFileRegion());
			}
			else{
				System.out.println("keine Speicherung Möglich. Path fehlt");
			}
		}
	}

	/**
	 * oeffnet einen FileChooser, mit der die Region gespeichert werden kann.
	 */
	public void fileChooserSave(){  
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Speichern");
	    FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("REGION" , "*.region");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialDirectory(
		            new File(regionMaker.getVerzeichnisPath()) 
		        );
		File file = fileChooser.showSaveDialog(StageCrt.getInstance().getStage());
		speichern(file);
	}
	
	private void speichern(File file){
		if (file != null){	
			FileOutputStream fos = null;
			//Modelklasse Region erstellen und die Region speichern
//			ArrayList<GameObjekt> objektListe = new ArrayList<GameObjekt>();
//			for (int i = 0; this.regionMaker.getGameObjekteOnRegion().size() > i ; i++){
//				objektListe.add(regionMaker.getGameObjekteOnRegion().get(i).getGameObjekt());
//			}
//			regionMaker = new RegionMaker(); 
//			regionMaker.getRegion().setHeight(this.regionPane.getHeight());
//			regionMaker.getRegion().setWidth(this.regionPane.getWidth());
			regionMaker.serializ();
			regionMaker.setVerzeichnisPath(file.getParentFile().toPath().toString());
			regionMaker.setFileRegion(file);
			try 
			{
				fos = new FileOutputStream (file.getAbsoluteFile()); 
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		    ObjectOutputStream oos = null;
			
		    try 
			{
				oos = new ObjectOutputStream (fos);
				oos.writeObject(regionMaker);
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
			}	
		    System.out.println("gespeichert");
		}
	}

	public void einstellungRegionSize() {
		MapSizeDialog dialog = new MapSizeDialog(regionMakerScene.getRegionPane().getPrefWidth(), regionMakerScene.getRegionPane().getPrefHeight()); 
        dialog.show();
	}

	public synchronized RegionMakerScene getRegionMakerScene() {
		return regionMakerScene;
	}

	public synchronized RegionMaker getRegionMaker() {
		return regionMaker;
	}

	/**
	 * setzt ein GameObjekt auf die Region
	 * @param objekt
	 */
	public void setGameObjekt(GameObjekt objekt) {
		//makiert die Region als bearbeitet
		regionMaker.setDurtyBit(true); 
		//fuegt der Region dieses neue Objekt hinzu.
		regionMaker.getRegion().getChildren().add(objekt);
		//fuegt der View dieses neue Objekt hinzu.
		objekt.ladeNodeObjekt();
		regionMakerScene.getRegionPane().getChildren().add(objekt.getNodeObjekt().getNode());
		//sucht die passende EventHandler und setzt diese zu dem Objekt
		EventCrt.getInstance().setNodePassendenEventHandler(objekt);
	}
	
	public void setGameObjekt(ArrayList<GameObjekt> arrayList) {
		for (int i = 0; arrayList.size() > i; i++){
			setGameObjekt(arrayList.get(i));
		}		
	}
}
