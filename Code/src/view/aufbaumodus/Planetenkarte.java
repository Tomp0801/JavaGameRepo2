package view.aufbaumodus;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.Bereich;
import map.Karte;
import view.aufbaumodus.fxml.SpielUmgebungController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Eine Karte eines Planeten
 * 
 * @author Dennis
 *
 */
public class Planetenkarte
{
	private final int MINSIZEBEREICH = 50;
	
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
		
	private HBox[] zeile;
	
	private AnchorPane subSceneRoot = new AnchorPane();
		
	private VBox vBox = new VBox();
	
	/**
	 * die Kamera zur Steuerung der Ansicht
	 */
	private KameraPlanetenkarte kamera;
	
	/**
	 * erstellt ein Objekt einer Planetenkarte
	 * @param karte
	 */
	public Planetenkarte(Karte karte)
	{		
		initScene();
		
		kamera = new KameraPlanetenkarte(this.scene);
		
		subScene.setCamera(kamera.getKamera());

		initBox(karte);
			
		subSceneRoot.setPrefSize(karte.getBreite() * MINSIZEBEREICH +10, karte.getHoehe()*MINSIZEBEREICH+10);
		
		if (subSceneRoot.getPrefHeight() < this.controller.getStackPaneZentrum().getPrefHeight())
			subSceneRoot.setPrefHeight(this.controller.getStackPaneZentrum().getPrefHeight());
		if (subSceneRoot.getPrefWidth() < this.controller.getStackPaneZentrum().getPrefWidth())
			subSceneRoot.setPrefWidth(this.controller.getStackPaneZentrum().getPrefWidth());
		
		subSceneRoot.getChildren().add(vBox);	
		vBox.setPrefSize(subSceneRoot.getPrefWidth(), subSceneRoot.getPrefHeight());
		
		initKarte(karte);
	}

	/**
	 * initialisiert die Scene
	 */
	private void initScene()
	{
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		this.subScene = new SubScene(subSceneRoot , 1000 , 1000, true, SceneAntialiasing.BALANCED);
		this.subScene.setDepthTest(DepthTest.ENABLE);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}	
		this.controller = loader.getController();

		this.controller.wechsleZentrum(subScene);
		this.scene = new Scene(loader.getRoot() , 1000 , 1400 , true, SceneAntialiasing.BALANCED);	
	}
	
	
	private void initBox(Karte karte)
	{
		zeile = new HBox[karte.getHoehe()];

		
		for (int i = 0 ; karte.getHoehe() > i ; i++)
		{
			zeile[i] = new HBox();
			this.vBox.getChildren().add(zeile[i]);	
		}
	}
	
	
	private void initKarte(Karte karte)
	{
		for(int i = 0 ; karte.getHoehe() > i ; i++)
		{
			for (int j = 0; karte.getBreite() > j ; j++ )
			{
				Bereich bereich = karte.getBereich(i, j);
				
				Canvas canvas = new Canvas();
								
				canvas.setHeight(subSceneRoot.getPrefHeight()/karte.getHoehe());
				canvas.setWidth(subSceneRoot.getPrefWidth()/karte.getBreite());
			
				bereich.getAussehen(canvas);
				
				zeile[i].getChildren().add(canvas);
				
				canvas.setOnMouseClicked(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent e) 
					{
						if (e.getClickCount() == 1)
						{
							// TODO lade informationen
						}
						else if (e.getClickCount() == 2)
						{
							//TODO oeffne den Bereich
						}
					}			
				});
			}
		}
	}
	
	public Scene getScene()
	{
		return this.scene;
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
	private class KameraPlanetenkarte extends Kamera
	{
		KameraPlanetenkarte(Scene scene)
		{
			this.setPosition(-100,-500, -100);
			
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
				veraenderePosition(0 ,-5);
				break;
			case S:
				veraenderePosition(0 , +5);
				break;
			case A:
				veraenderePosition(+5 , 0);
				break;
			case D:
				veraenderePosition(-5 , 0);
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
