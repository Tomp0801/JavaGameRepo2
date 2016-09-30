package view.aufbaumodus;

import controller.BewegungsmanagerHimmelskoerper;
import controller.StageController;
import himmelskoerper.Betretbar;
import himmelskoerper.Himmelskoerper;
import himmelskoerper.Mond;
import himmelskoerper.Planet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Sphere;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.transform.Rotate;

public class Planetensystem extends AufbaumodusSichtweiseWeltraum
{
	/**
	 * ein Verweis auf dem Planeten, desssen System dargestellt wird. 
	 */
	private Planet planet; 
	
	/**
	 * in der navigationsBox werden alle Himmelskoerper des Planetensystems hinzugefuegt. 
	 * Diese werdern dann als Sphare unten angezeit. Bei einem Klick auf eins der Sphare richtet sich die 
	 * Kamera nach diesem Himmelskoerper aus. 
	 */
	private HBox navigationBox = new HBox();
	
	/**
	 * Kamera des Planetensystems. Mit dieser ist es dem Spieler moeglch durch das Planetensystem zu navigieren.
	 * Hierzu verwendet der Spieler die Maus um die Kamera zu rotieren und die Tasten Q,W,E,A,S,D um die Kamrea zu bewegen
	 */
	private KameraPlanetensystem kamera;
	
	
	/**
	 * erstellt ein Objekt eines Planetensystems
	 * @param planet
	 */
	public Planetensystem(Planet planet)
	{  
//		this.getSpielUmgebungController().setBottomPane(navigationBox);
//		navigationBox.setAlignment(Pos.CENTER);
//		navigationBox.setSpacing(10);

		kamera = new KameraPlanetensystem(this.getScene(), (int) planet.getRadius());
		this.planet = planet; 
		this.getSubScene().setCamera(kamera.getKamera());
		//zeichenet das Planetensystem mit den Monden und allem was Raumschiffen ect. 
		zeichnePlanetensystem(this.planet); 
    }
	
	
	/**
	 * zechnet den Planeten und deren Monde
	 * @param planet
	 */
	public void zeichnePlanetensystem(Planet planet)
	{
		//zeichnet den Planten
		zeichnePlanet(planet);
		
		for (int i = 0; planet.getChildren().size() > i; i++)
		{		
			if (planet.getChild(i).getClass() == Mond.class)
			{
				zeichneMond((Mond) planet.getChild(i));
			}
   	 	}
	}
	
	
	
	/**
	 * setzt die Position des Planeten
	 * @param planet
	 */
	private void zeichnePlanet(Planet planet)
	{				
   	 	//erstelle einen Himmerlskoerper
   	 	Sphere himmelskoerper = new Sphere();
   	 	
   	 	himmelskoerper.setRadius(planet.getRadius());
   	 	
   	 	//setzt die Position in den Mittelpunkt
   	 	himmelskoerper.setTranslateX(0);
   	 	himmelskoerper.setTranslateY(0);
   	 	himmelskoerper.setTranslateZ(0);
   	 	
   	    this.getSubSceneRoot().getChildren().add(himmelskoerper);	 	        
   	 	
   	    zeichenHimmelskoerper(planet, himmelskoerper);    
	}
	
	
	/**
	 * setzt die Position des Mondes
	 * @param mond der gezeichnet werden soll
	 */
	private void zeichneMond(Mond mond)
	{
		//erstelle einen Mond
	 	Sphere himmelskoerper = new Sphere();
	 	
	 	himmelskoerper.setRadius(mond.getRadius());
	 	
	 	this.getSubSceneRoot().getChildren().add(himmelskoerper);	
	    BewegungsmanagerHimmelskoerper.getInstance().addInOrbitObjectToBewegungsRechner(mond);  	        
	   
	    himmelskoerper.translateXProperty().bind(mond.getPositionProperty()[0]);
	    himmelskoerper.translateYProperty().bind(mond.getPositionProperty()[1]);
	    himmelskoerper.translateZProperty().bind(mond.getPositionProperty()[2]);
	    
	    zeichenHimmelskoerper(mond, himmelskoerper);
	}
	
	
	/**
	 * zechnet den Himmelskoerper
	 * @param himmelskoerper das gezeichet werden soll
	 * @param sphere Sphare das dazu verwendt wird
	 */
	private void zeichenHimmelskoerper(Himmelskoerper himmelskoerper, Sphere sphere)
	{
       //setzt das aussehen der Kugel
		sphere.setMaterial(himmelskoerper.getAussehn());
       
       //macht den Himelskoerper anklickbar
		sphere.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
			@Override
			public void handle(MouseEvent event) 
			{			
				if (event.getClickCount() == 2)
				{
					kamera.ausrichtenNachObjekt(new Point3D(sphere.getTranslateX(), sphere.getTranslateY(), sphere.getTranslateZ()),(int) sphere.getRadius());
				}
				else
				{
					ladeInformationen(himmelskoerper);
				}
			}
       });  
       
       Sphere infoZeichen = new Sphere();
       infoZeichen.setRadius(10);
       infoZeichen.setMaterial(himmelskoerper.getAussehn());
       Label name = new Label(himmelskoerper.getName());
       VBox himmelskoerperBox = new VBox(name,infoZeichen);
    
       navigationBox.getChildren().add(himmelskoerperBox);
       himmelskoerperBox.setOnMouseClicked(new EventHandler<MouseEvent>() 
       {

		@Override
		public void handle(MouseEvent event) 
		{
			kamera.ausrichtenNachObjekt(new Point3D(sphere.getTranslateX(), sphere.getTranslateY(), sphere.getTranslateZ()),(int) sphere.getRadius());
		}
	});
       
	}
	
	
	/**
	 * lade alle informationen die es ueber den Himmmelskoerper zuverfuegung gibt.
	 * @param himmelskoerper
	 */
	private void ladeInformationen(Himmelskoerper himmelskoerper)
	{
		this.getSpielUmgebungController().clearInformationen();
		
		Label name = new Label("Name: "+himmelskoerper.getName());
		Label groeﬂe = new Label("Radius: "+(int) himmelskoerper.getRadius());
		Label position = new Label("Position X: "+(int) himmelskoerper.getPositionAbsolute().getX()+" Y: "+(int) planet.getPositionAbsolute().getY()+" Z: "+(int) planet.getPositionAbsolute().getZ());
		Label temperratur = new Label("Oberfl‰chentemperratur "+(int) himmelskoerper.getOberflaechenTemperatur());
		Label masse = new Label("Masse: "+(int) himmelskoerper.getMasse());
			
		this.getSpielUmgebungController().setzeInformationen(name);
		this.getSpielUmgebungController().setzeInformationen(groeﬂe);
		this.getSpielUmgebungController().setzeInformationen(position);
		this.getSpielUmgebungController().setzeInformationen(temperratur);
		this.getSpielUmgebungController().setzeInformationen(masse);
		
		if (himmelskoerper instanceof Betretbar)
		{
			Button openKarte = new Button("gehe zum Planeten");
			openKarte.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					
		    			Betretbar betretbar = (Betretbar) himmelskoerper;
		    			PlanetenkarteBereiche karte = new PlanetenkarteBereiche(betretbar.getKarte()); 
		    			StageController.getInstance().setScene(karte.getScene());		
				}	
			});
			this.getSpielUmgebungController().setzeInformationen(openKarte);
		}
	}
	


	
	//¥--------------------------------Kamera-dieser-Scene--------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	
	

	private class KameraPlanetensystem extends Kamera
	{
		private double speed = 10000; 
		
		/**
		 * wenn sich die Kamera in Rotation befindet, ist isRotation = true
		 */
		private Boolean isRotation = false;
		
		/**
		 * rotations um die Achse 
		 */
		private Rotate rotationY = new Rotate(0, Rotate.Y_AXIS);
		
		/**
		 * rotation um die Z Achse
		 */
		private Rotate rotationX = new Rotate(0, Rotate.X_AXIS);
		
		/**
		 * erstellt eine Kamera
		 * @param scene auf der KeyEvents behandelt werden um die Kamera zubewegen
		 * @param position die Position der Kamera
		 */
		public KameraPlanetensystem(Scene scene, int radiusPlanet)
		{
			//position
			this.getKamera().setTranslateZ(-radiusPlanet*20);
			this.getKamera().setTranslateX(-Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
			this.getKamera().setTranslateY(-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
			//legt die Sichtweite fest
			this.getKamera().setFarClip(10000000);
			//maximale naehe
			this.getKamera().setNearClip(0.1);
			this.getKamera().setFieldOfView(35);
			this.getKamera().getTransforms().addAll(rotationY , rotationX);
			initEventScene(scene);
		}
		
		
		/**
		 * behandelt Eents zur bewegung der Kamera
		 * @param scene
		 */
		private void initEventScene(Scene scene)
		{
			Point positionMaus = new Point(0,0);
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		        {
					@Override
					public void handle(KeyEvent event) 
					{
						doBewegung(event.getCode());	
					}
		        });
		        
				scene.setOnMousePressed(new EventHandler<MouseEvent>()
		        {

					@Override
					public void handle(MouseEvent event) 
					{
						if (event.getButton() == MouseButton.PRIMARY)
						{
							positionMaus.setLocation(MouseInfo.getPointerInfo().getLocation());
							setRotaion(true);
						}
					}   	
		        });
		        	
				scene.setOnMouseDragged(new EventHandler<MouseEvent>() 
		        {
		        	double mouseOldX = 0; 
		        	double mouseOldY = 0;
		        	
		        	double mousePosX = 0;
		        	double mousePosY = 0;
		        	
		        	double mouseDeltaX = 0;
		        	double mouseDeltaY = 0;
		        	
					@Override
					public void handle(MouseEvent event) 
					{
			        	mouseOldX = mousePosX;
		                mouseOldY = mousePosY;
		                mousePosX = MouseInfo.getPointerInfo().getLocation().getX();
		                mousePosY = MouseInfo.getPointerInfo().getLocation().getY();
		                
		                mouseDeltaX = mousePosX - mouseOldX;
		                mouseDeltaY = mousePosY - mouseOldY;
		                   
		                if (mouseDeltaX >= 8 || mouseDeltaX <= -8)
		                {
		                	mouseDeltaX = 0;
		                }
		                if (mouseDeltaY >= 5 || mouseDeltaY <= -5  )
		                {
		                	mouseDeltaY = 0;
		                }
		                
						rotation(mouseDeltaX , mouseDeltaY );
					}
				});
		        
				scene.setOnMouseReleased(new EventHandler<MouseEvent>()
		        {
					@Override
					public void handle(MouseEvent event) 
					{
						if (event.getButton() == MouseButton.PRIMARY)
						{
							setRotaion(false);
						}
					}   	
		        });        
		}
		
//		/**
//		 * gibt die Position der Kamera zuruek
//		 * @return
//		 */
//		public Point3D getPosition()
//		{
//			return this.getPosition();
//		}
		
		
		/**
		 * veraendert die Position der Kamera. 
		 * 
		 * info: soll sich eine richtungskomponente nicht aendern, dann entspricht der Wert 0. 
		 * Beispiel veraenderePosition(10 , 0 , -5)
		 * 
		 * @param pointX in X Richtung
		 * @param pointY in Y Richtung
		 * @param pointZ in Z Richtung
		 */
		public void veraenderePosition(double pointX , double pointY , double pointZ)
		{
			this.setPosition(this.getKamera().getTranslateX() + pointX , this.getKamera().getTranslateY() + pointY, this.getKamera().getTranslateZ() + pointZ);
		}

		
		/**
		 * bewegt die Kamera in eine Bestimte richtung
		 * @param key W: nach vorne / S: zurueck / D: nach Rechts / A: nach Links / E: nach oben / Q: nach unten
		 */
		public void doBewegung(KeyCode key)
		{
			double a = speed*Math.cos(Math.toRadians(90-rotationY.getAngle()));
			double b = speed*Math.sin(Math.toRadians(-rotationX.getAngle()));
			double c = speed*Math.sin(Math.toRadians(90-rotationY.getAngle()));
			
			switch (key)
			{
			case W: 
				veraenderePosition(a, b, c);
				break;
			case S: 
				veraenderePosition(-a, -b, -c);
				break;
			case D: 
				veraenderePosition(c, 0, -a);
				break;
			case A: 
				veraenderePosition(-c, 0, a);
				break;
			case E: 
				veraenderePosition(0 , speed, 0);
				break;
			case Q: 
				veraenderePosition(0, -speed, 0);
				break;
				
			case NUMPAD1:
				if (speed < 90)
				speed = speed+ 10;
				break;
			case NUMPAD2:
				if (speed < 900)
				speed = speed+ 100;
				break;
			case NUMPAD3:
				if (speed < 40000)
				speed = speed+ 2000;
				break;
			case NUMPAD4:
				if (speed >= 10)
				speed = speed- 10;
				break;
			case NUMPAD5:
				if (speed >= 100)
				speed = speed- 100;
				break;
			case NUMPAD6:
				if (speed >= 1000)
				speed = speed- 1000;
				break;
			case ESCAPE:
				StageController.getInstance().openLastScene();
				break;
			default:
				break;
			}
		}
		

		/**
		 * rotiert die Kamera
		 * @param mouseDeltaY
		 * @param mouseDeltaX
		 */
		public void rotation(double mouseDeltaY , double mouseDeltaX)
		{	
			if(this.isRotation)
			{	
				this.rotationX.setAngle(rotationX.getAngle() - mouseDeltaX/1);
				this.rotationY.setAngle(rotationY.getAngle() + mouseDeltaY/1);
			}
		}
		
		/**
		 * setzt isRotaion bei rotation auf true
		 * @param isRotation
		 */
		public void setRotaion(Boolean isRotation)
		{
			this.isRotation = isRotation;
		}
		
		
//		/**
//		 * bewegt die Kamera zu einer neuen Position mit einer Tr
//		 * @param newPosition die Position zu der sich die Kamera bewegen soll
//		 */
//		public void geheZumPunkt(Vector<Double> newPosition)
//		{
//			//TODO Taransition hinzufuegen
//		}
		
		
		/**
		 * die Position der Kamera wird zu vereandert, das der Spieler das Objekt betratenkann,dessen 
		 * Punkt uebergeben wurde.
		 * @param point entwpricht den Koardinaten des Objektes, nach dem sich die Kamera ausrichten soll
		 * @param sizeObjekt groeﬂe des Objektes um die Kamere so auszurichten, das sie weit genug entfernt ist. 
		 */
		public void ausrichtenNachObjekt(Point3D point, int sizeObjekt)
		{	
			this.setPosition(point.getX(), point.getY(), point.getZ()-sizeObjekt*20);
			this.rotationX.setAngle(0);
			this.rotationY.setAngle(0);
			
//			double x  = Math.sin(Math.toRadians(rotationX.getAngle()))*Math.cos(Math.toRadians(rotationY.getAngle()));
//			double y = Math.sin(Math.toRadians(rotationX.getAngle()))*Math.sin(Math.toRadians(rotationY.getAngle()));
//			double z = Math.cos(Math.toRadians(rotationX.getAngle()));	
//			
//			Point3D ausrichtungKamera = new Point3D(x ,y , z);
//			Point3D richtungsVektor = point.subtract(ausrichtungKamera);
//			
//			RotateTransition rotation = new RotateTransition(Duration.seconds(3), this.getKamera());
//			rotation.setAxis(ausrichtungKamera.crossProduct(richtungsVektor));
//			rotation.setByAngle(ausrichtungKamera.angle(richtungsVektor));
//			rotation.play();
		}
	}
}
