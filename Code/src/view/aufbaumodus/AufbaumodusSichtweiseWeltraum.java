package view.aufbaumodus;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import view.aufbaumodus.fxml.SpielUmgebungController;

/**
 * Diese Klasse erstellt eine Scene auf der die Sichtweise vom Aufbaumodus dargestllt wird. 
 * Sie besteht auf einem Boarderpane. 
 * Oben befindet sich eine Menuleiste. Rechts und links lassen sich Nods platzieren.
 * In der Mitte laesst sicht eine SubScene setzen.
 * 
 * 
 * @author Dennis
 *
 */
public abstract class AufbaumodusSichtweiseWeltraum 
{
	/**
	 * die Scene die zur darstellung verwendet wird
	 */
	private Scene scene; 
	
	/**
	 * der Controller um MenuButtons zu kontrollieren
	 */
	private SpielUmgebungController controller; 
	
	/**
	 * Der SubScene wird einer Kamera hinzugefuegt um die navigation des Spielers zu ermoeglichen
	 */
	private SubScene subScene; 
	
	/**
	 * diese Elemente befinden sich in der SubScene. 
	 */
	private Group subSceneRoot = new Group();

	
	/**
	 * der konstruktor der Klasse
	 */
	public AufbaumodusSichtweiseWeltraum() 
	{
		initScene();
	}
	
	
	/**
	 * initialisiert die Scene
	 */
	private void initScene()
	{
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		this.subScene = new SubScene(subSceneRoot , 1000 , 1000, true, SceneAntialiasing.BALANCED);
		this.subScene.setFill(Color.BLACK); 
		this.subScene.setDepthTest(DepthTest.ENABLE);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aufbaumodus/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}	
		this.controller = loader.getController();

		this.controller.wechsleZentrum(subScene);
		this.scene = new Scene(loader.getRoot() , 1000 , 1400 , true, SceneAntialiasing.BALANCED);	
	}
	
	
	/**
	 * gibt die Scene zurueck
	 * @return Scene 
	 */
	public Scene getScene()
	{
		return this.scene;
	}
	
	
	/**
	 * gibt die SubScene wieder zurueck
	 * @return SubScene
	 */
	protected SubScene getSubScene()
	{
		return this.subScene;
	}
	
	
	/**
	 * gib die Group der SubScene zuruck
	 * @return Group
	 */
	protected Group getSubSceneRoot()
	{
		return this.subSceneRoot;
	}
	
	
	/**
	 * gib den Controller dir Spielumgebung zurueck 
	 * @return SpielUmgebungController
	 */
	protected SpielUmgebungController getSpielUmgebungController()
	{
		return this.controller;
	}
}
