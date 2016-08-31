package view.weltraum;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import view.weltraum.fxml.SpielUmgebungController;

/**
 * eine Abstracte Klasse zur WeltraumSicht. Diese Klasse behinahltetdas grundgerüst der Sichtweisen. 
 * @author Demix
 *
 */
public abstract class WeltraumSystem 
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
	public WeltraumSystem() 
	{
		initScene();
	}
	
	
	/**
	 * initialisiert die Scene
	 */
	private void initScene()
	{
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		subScene = new SubScene(subSceneRoot , 500 , 500, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK); 
		subScene.setDepthTest(DepthTest.ENABLE);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}	
		SpielUmgebungController controller = loader.getController();
		scene = new Scene(loader.getRoot() , 800 , 800 , true, SceneAntialiasing.BALANCED);
		controller.wechsleZentrum(subScene);
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
