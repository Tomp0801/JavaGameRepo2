package view.aufbaumodus;

import controller.BewegungsmanagerStern;
import controller.StageController;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Demix
 *
 */
public class Sternensystem extends AufbaumodusSichtweiseWeltraum
{
	/**
	 * Kamera die die Navigation im Sternesystem ermoeglicht
	 */
	private KameraSternensystem kamera;
		
	/**
	 * die groeße eines Sternes wird auf 50 Pixel festgelegt.
	 */
	private final int STERNSIZE = 10;
	
	/**
	 * die Position der Kamera wird mit diesem Label gezeigt
	 */
	private Label infoPosition = new Label("Position");
	
	/**
	 * in dieser VBox werden informationen einer Sonne angezeigt
	 */
	private VBox infoBox = new VBox();
	
	/**
	 * erstellt ein Objekt vom Sternensystem. In diesem System befinden sich Sterne die um ein schwarzes Loch kreisen
	 * 
	 * @param zentrum
	 */
	public Sternensystem(SchwarzesLoch zentrum)
	{	
		kamera = new KameraSternensystem(this.getScene());
		this.getSubScene().setCamera(kamera.getKamera());
		zeichneSternsystem(zentrum);
		
		infoPosition.setTextFill(Color.WHITE);
		this.getSpielUmgebungController().getMenuOnTop().getChildren().add(infoPosition);
		
		infoBox.setMouseTransparent(true);
		infoBox.setSpacing(20);
		this.getSpielUmgebungController().getStackPaneZentrum().getChildren().add(infoBox);		
	}
	
	
	public void zeichneSternsystem(SchwarzesLoch zentrum)
	{							
		this.zeichneSchwarzesLoch(zentrum);
		
		//geht alle Sterne durch
		for (int j = 0; zentrum.getChildren().size() > j; j++)
		{		
			if (zentrum.getChild(j) instanceof Stern)
			{
				Stern sonne = (Stern) zentrum.getChild(j);
				zentrum.getChild(j).refresh();
	   	 		//erstelle ein Stern
	   	 		Sphere stern = new Sphere();
//	   	 		Sphere sternUmgebung = new Sphere(); 
//	   	 		sternUmgebung.setOpacity(0.2);
	   	 		
	   	 		stern.setRadius(STERNSIZE);
//	   	 		sternUmgebung.setRadius(STERNSIZE*2);
	   	 		
	   	        //setzt das aussehen der Kugel
		        stern.setMaterial(zentrum.getChild(j).getAussehn());
//		        sternUmgebung.setMaterial(zentrum.getChild(j).getAussehn());
	   	 		this.getSubSceneRoot().getChildren().add(stern);	
//	   	 		this.getSubSceneRoot().getChildren().add(sternUmgebung);
	   	 		
	   	 		BewegungsmanagerStern.getInstance().getBewegungsRechnerStern().add(sonne);
	   	       
	   	 		// bindet die position eines Sternes an eine Wert 
	   	 		SimpleDoubleProperty[] propertyPosition =	BewegungsmanagerStern.getInstance().addToBewegungsSternsystemRechnerAdapa(sonne);
	   	        stern.translateXProperty().bind(propertyPosition[0]);
	   	        stern.translateYProperty().bind(propertyPosition[1]);
	   	        stern.translateZProperty().bind(propertyPosition[2]);	
//	   	         
//	   	        sternUmgebung.translateXProperty().bind(propertyPosition[0]);
//	   	        sternUmgebung.translateYProperty().bind(propertyPosition[1]);
//	   	        sternUmgebung.translateZProperty().bind(propertyPosition[2]);	
//	   	        
		        //macht den Stern anklickbar
		        stern.setOnMouseClicked(new EventHandler<MouseEvent>()
		        {
					@Override
					public void handle(MouseEvent event) 
					{		
						if (event.getClickCount() == 2)
						{
							Sonnensystem sonnesystem = new Sonnensystem(sonne);
							StageController.getInstance().setScene(sonnesystem.getScene());
						}
						else
						{
							ladeInformationen(sonne);			
						}
					}
		        });     
	   	 	}
		}
	}

	private void zeichneSchwarzesLoch(SchwarzesLoch zentrum) 
	{
		
	}
	
	/**
	 * laed informationen ueber einen Stern der angeklickt wurde
	 */
	private void ladeInformationen(Stern stern)
	{	
		
		infoBox.getChildren().clear();
		
		Label name = new Label(stern.getName());
		name.setTextFill(Color.BLUE);
		Label masse = new Label("Masse "+stern.getMasse());
		masse.setTextFill(Color.BLUE);
		Label planeten = new Label(stern.getChildren().size()+ " Planeten");
		planeten.setTextFill(Color.BLUE);
		Label radius = new Label("Radius "+stern.getRadius());
		radius.setTextFill(Color.BLUE);
		Label systemRadius = new Label("Systemradius "+stern.getSystemRadius());
		systemRadius.setTextFill(Color.BLUE);
		Label temperatur = new Label("Oberflächentemeratur "+stern.getOberflaechenTemperatur());
		temperatur.setTextFill(Color.BLUE);
		
		infoBox.getChildren().addAll(name, masse ,planeten, radius, systemRadius, temperatur);

		
	}
	
	
	//´--------------------------------Kamera-dieser-Scene--------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	

	/**
	 * Eine Kamera, die das navigieren im Sternensystem ermoeglicht. 
	 * durch das nutzen von den Tasten Q,W,E,A,S,D wird eine benachbarte Sektion ausgeweahlt, dessen
	 * Sterne in den Mittelpunkt gesetzt werden. Alle Sterne die sich nicht in den festgelegten Bereich befinden,
	 * werden verblasst angezeigt
	 * 
	 * @author Dennis
	 */
	private class KameraSternensystem extends Kamera
	{
		/**
		 * dieser int Array ist zum navigieren in einem Sternensystem. (x , y , z) die koaardinaten gehort zu der Sektion die angesprochen wird.
		 */
		private int[] sektion = new int[3]; 
		
		/**
		 * Alle Sterne die sich in der Reichweite einer Sektion befinden werden gezeichnet und sind anklickbar 
		 */
		private int SEKTIONSIZE = 100; 
		
		/**
		 * zuer behandlung von Events 
		 */
		private Scene scene; 
		
		public KameraSternensystem(Scene scene)
		{
//			legt die Sichtweite fest
			this.getKamera().setFarClip(10000000);
			//maximale naehe
			this.getKamera().setNearClip(0.1);
			this.getKamera().setFieldOfView(35);
			this.scene = scene;
			initEventBehandlung();
			
			this.setPosition(sektion[0]*SEKTIONSIZE, sektion[1]*SEKTIONSIZE, sektion[2]*SEKTIONSIZE);
		}

		private void initEventBehandlung() 
		{
			this.scene.setOnKeyPressed(new EventHandler<KeyEvent>()
			{
				@Override
				public void handle(KeyEvent event)
				{
					int x = 0;
					int y = 0;
					int z = 0;
					
					switch (event.getCode())
					{
						case W:
							z++;
							break;
						case S:
							z--;
							break;
						case A:
							x--;
							break;
						case D:
							x++;
							break;
						case Q:
							y--;
							break;
						case E:
							y++;
							break;
						default:
							break;
					}
					
					setNewPosition(x, y, z);	
				}		
			});		
		}
		
		private void setNewPosition(int x, int y, int z)
		{
			sektion[0]=sektion[0]+x;
			sektion[1]=sektion[1]+y;
			sektion[2]=sektion[2]+z;
			
//			Path path = new Path();
//			path.getElements().add(new MoveTo(0 ,0 , 0));
//			path.getElements().add(new )
//			
//			PathTransition bewegung = new PathTransition(Duration.seconds(2) ,path, this.getKamera());
	//
//			bewegung.play();
			infoPosition.setText("X: "+sektion[0]+" Y: "+sektion[1]+" Z: "+sektion[2]);
			this.setPosition(sektion[0]*SEKTIONSIZE, sektion[1]*SEKTIONSIZE, sektion[2]*SEKTIONSIZE);
		}
	}

}
