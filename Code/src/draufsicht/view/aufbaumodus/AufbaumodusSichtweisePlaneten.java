package draufsicht.view.aufbaumodus;

import java.io.IOException;

import draufsicht.controller.StageController;
import draufsicht.view.aufbaumodus.fxml.SpielUmgebungController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class AufbaumodusSichtweisePlaneten 
{
	
	/**
	 * die Kamera zur Steuerung der Ansicht
	 */
	private KameraPlanetenkarteBereich kamera;
	
	/**
	 * der Controller um MenuButtons zu kontrollieren
	 */
	private SpielUmgebungController controller; 
	
	/**
	 * Der SubScene wird einer Kamera hinzugefuegt um die navigation des Spielers zu ermoeglichen
	 */
	private SubScene subScene; 
	
	/**
	 * dieser AnchorPane befindet sich auf der SubScene. 
	 * Die Karte des Planeten wird hier drauf abgebildet. 
	 */
	private AnchorPane subSceneRoot = new AnchorPane();
	
	/**
	 * die Scene die zur darstellung verwendet wird
	 */
	private Scene scene; 
	
	
	public AufbaumodusSichtweisePlaneten() 
	{
		
		initScene();
		
		kamera = new KameraPlanetenkarteBereich(this.scene);	
		subScene.setCamera(kamera.getKamera());
	}

	
	private void initScene() 
	{
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		this.subScene = new SubScene(subSceneRoot , 1000 , 1000, true, SceneAntialiasing.BALANCED);
		this.subScene.setDepthTest(DepthTest.ENABLE);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aufbaumodus/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}	
		this.controller = loader.getController();

		this.controller.wechsleZentrum(subScene);
		this.scene = new Scene(loader.getRoot() , 1000 , 1400 , true, SceneAntialiasing.BALANCED);
	}
	
	
	/**
	 * 
	 * @return gibt den subSceneRoot zurueck
	 */
	public AnchorPane getSubSceneRoot()
	{
		return this.subSceneRoot;
	}
	
	
	/**
	 * 
	 * @return gibt die verwendete Scene zurueck
	 */
	public Scene getScene()
	{
		return this.scene;
	}
	
	public SubScene getSubScene()
	{
		return this.subScene;
	}
	
	
	/**
	 * 
	 * @return gibt den controller dier SpielUmgebung zurueck
	 */
	public SpielUmgebungController getController()
	{
		return this.controller;
	}
	
	
	
	//´--------------------------------Kamera-dieser-Scene--------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//
		/**
		 * erstellt ein Objekt der Kamera
		 * @author Dennis
		 *
		 */
		private class KameraPlanetenkarteBereich extends Kamera
		{
			KameraPlanetenkarteBereich(Scene scene)
			{
				this.setPosition(0,0, -100);
				
				//legt die Sichtweite fest
				this.getKamera().setFarClip(10000000);
				//maximale naehe
				this.getKamera().setNearClip(0.1);
				this.getKamera().setFieldOfView(35);
				
				initEventScene(scene);
			}
			
			
			/**
			 * ermoglicht das behandeln von KeyEvents auf der im Konstruktor uebergebenden Scene
			 * @param scene
			 */
			private void initEventScene(Scene scene)
			{
				scene.setOnKeyPressed(new EventHandler<KeyEvent>()
				{
					@Override
					public void handle(KeyEvent e) 
					{
						doBewegung(e.getCode());
					}		
				});
			}

			
			/**
			 * behandelt die Events zur Bewegung der Kamera
			 * @param e
			 */
			protected void doBewegung(KeyCode e) 
			{
				switch (e)
				{
				case W:
					veraenderePosition(0 ,-10);
					break;
				case S:
					veraenderePosition(0 , +10);
					break;
				case A:
					veraenderePosition(-10 , 0);
					break;
				case D:
					veraenderePosition(+10 , 0);
					break;
				case ESCAPE:
					StageController.getInstance().openLastScene();
					break;
				default:
					break;
				}
			}
			
			/**
			 * veraendert die Position der Kameraa
			 * @param x
			 * @param y
			 */
			private void veraenderePosition(int x, int y)
			{
				this.getKamera().setTranslateX(this.getKamera().getTranslateX()+x);
				this.getKamera().setTranslateY(this.getKamera().getTranslateY()+y);
			}
		}

}
