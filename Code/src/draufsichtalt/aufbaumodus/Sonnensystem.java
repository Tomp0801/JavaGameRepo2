package draufsicht.view.aufbaumodus;

import draufsicht.controller.BewegungsmanagerHimmelskoerper;
import draufsicht.controller.StageController;
import draufsicht.himmelskoerper.Himmelskoerper;
import draufsicht.himmelskoerper.Planet;
import draufsicht.himmelskoerper.Stern;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;	
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;



/**
 * Diese Klasse zeichnet ein Sonnensystem um in diesem zu navigieren. 
 *  
 * @author Demix
 *
 */
public class Sonnensystem extends AufbaumodusSichtweiseWeltraum
{
	/**
	 * Kamera zur navigation 
	 */
	private KameraSonnensystem kamera = new KameraSonnensystem(this.getScene());
	
	
	/**
	 * erstellt ein Object vom Sonnensystem
	 * @param stern 
	 */
	public Sonnensystem(Stern stern)
	{
		zeichneSonnensystem(stern);
		this.getSubScene().setCamera(kamera.getKamera());
		kamera.addNode(this.getSubSceneRoot());
	}
	
	
	/**
	 * zeichnet ein Sonnensystem
	 * 
	 * @param stern desssen Planeten gezeichnet werden sollen
	 */
	public void zeichneSonnensystem(Stern stern)
	{
		//dies entspricht 100% des maximalen OrbitRadius des Sternes
		double orbitRadius = 0;
		//Der OrbitRadius wird auf 5000 beschrenkt. Dies sind die 100% des Orbitradius. die Groﬂe des originals wird angepasst 
		double maxOrbitRadius = 5000; 
		//Der Groeﬂte Plante ist 100 und der das minimum liegt bei 10
		double groeﬂterPlanet = 0;
		//Die maximale Groeﬂe eines Planeten
		double maxGroeﬂe = 60; 
		
		//zeichnet die Sonne
		this.zeichneSonne(stern, maxGroeﬂe);
		
		//sucht den groeﬂten Planet und speichert die Groﬂe in die Variable groeﬂter Planet. Dies entspricht dann 100% 
		for (int i = 0; stern.getChildren().size() > i ; i++)
		{
			if (stern.getChild(i).getRadius() > groeﬂterPlanet)
			{
				groeﬂterPlanet = stern.getChild(i).getRadius();
			}
		}
		//sucht den entferntesten Planeten und speichert diese in die Variable orbitRadius. Dies entspricht dann 100% 
		for (int i = 0; stern.getChildren().size() > i ; i++)
		{
			Point3D entfernung = new Point3D (  stern.getChild(i).getPositionPolar().get(0) ,stern.getChild(i).getPositionPolar().get(1) ,stern.getChild(i).getPositionPolar().get(2)  );
				
			if (entfernung.distance(0 ,0 ,0) > orbitRadius)
			{
				orbitRadius = entfernung.distance(0 ,0 ,0);
			}
		}

		//nun wird das System gezeichnet
		for (int j = 0; stern.getChildren().size() > j; j++)
		{		
			if (stern.getChild(j) instanceof Himmelskoerper)
			{
				//hier wird die Groeﬂe der Kugel berechnet, die den Planeten darstellen soll. 
				double radius = (stern.getChild(j).getRadius())/groeﬂterPlanet* maxGroeﬂe;
				//hier wird die Position des Planeten aktualiesiert
				stern.getChild(j).refresh();
				//Die aktulle position wird in dieser Variable gepeichert (Polarefrom)
				Point3D point = new Point3D (  stern.getChild(j).getPositionPolar().get(0) ,stern.getChild(j).getPositionPolar().get(1) ,stern.getChild(j).getPositionPolar().get(2));
				//hier wird die entfernung zur Sonne angepasst
				double orbitRadiusPlanet = ((point.distance(0 , 0 , 0) / orbitRadius)*maxOrbitRadius)+maxGroeﬂe*2;
	
	   	 		//erstelle nun den Planeten
	   	 		Sphere himmelskoerper = new Sphere();
	   	 		//setzt den radius
	   	 		if (radius < 10)
	   	 		{
	   	 			radius=10;
	   	 		}
	   	 		
	   	 		himmelskoerper.setRadius(radius);
	   	 	
	   	 		//bindet die Position ein einen Wert	
	   	        SimpleDoubleProperty[] posi = BewegungsmanagerHimmelskoerper.getInstance().addPlanetToPositionsRechnerWithAdapta(orbitRadiusPlanet , (Planet) stern.getChild(j));  	        
	   	        himmelskoerper.translateXProperty().bind(posi[0]);
	   	        himmelskoerper.translateYProperty().bind(posi[1]);
	   	        himmelskoerper.translateZProperty().bind(posi[2]);	
	   	        //setzt den Planeten ins Universum
	   	        this.getSubSceneRoot().getChildren().add(himmelskoerper);
	   	        //sagt das die positions staendig aktualliesiert werden soll
	   	        BewegungsmanagerHimmelskoerper.getInstance().addInOrbitObjectToBewegungsRechner(stern.getChild(j));
	   	         
	   	        //gibt den Planeten sein Aussehen
		        himmelskoerper.setMaterial(stern.getChild(j).getAussehn());
		        
		        Planet planetObjekt = (Planet) stern.getChild(j);
		        
		        //macht den Himelskoerper anklickbar
		        himmelskoerper.setOnMouseClicked(new EventHandler<MouseEvent>()
		        {
		        	Planet planet = planetObjekt;
		        	
		        	@Override
					public void handle(MouseEvent event) 
					{		
						if (event.getClickCount() == 2)
						{
							kamera.setDrehpunkt(new Point3D(himmelskoerper.getTranslateX() , himmelskoerper.getTranslateY() , himmelskoerper.getTranslateZ()));	
							ladeInformationen(planet);
						}
						else
						{
							ladeInformationen(planet);
						}
					}
		        });  
		        
	//	        stern.getChild(j).getBewegungsVektor();
	   	 	}
		}
	}
	
	
	/**
	 * laed informationen des Planeten
	 * @param planet
	 */
	private void ladeInformationen(Planet planet)
	{
		this.getSpielUmgebungController().clearInformationen();
		
		Label name = new Label("Name: "+planet.getName());
		Label groeﬂe = new Label("Radius: "+ (int)planet.getRadius());
		Label position = new Label("Position X: "+(int) planet.getPositionAbsolute().getX()+" Y: "+(int) planet.getPositionAbsolute().getY()+" Z: "+(int) planet.getPositionAbsolute().getZ());
		Label temperratur = new Label("Oberfl‰chentemperratur "+planet.getOberflaechenTemperatur());
		Label masse = new Label("Masse: "+(int) planet.getMasse());
		Label  monde = new Label("Monde: "+planet.getChildren().size());
		
		this.getSpielUmgebungController().setzeInformationen(name);
		this.getSpielUmgebungController().setzeInformationen(groeﬂe);
		this.getSpielUmgebungController().setzeInformationen(position);
		this.getSpielUmgebungController().setzeInformationen(temperratur);
		this.getSpielUmgebungController().setzeInformationen(masse);
		this.getSpielUmgebungController().setzeInformationen(monde);
		
//		for (int i = 0 ; planet.getChildren().size() > i ;i++)
//		{
//			Button button = new Button(planet.getChild(i).getName());
//			
//			button.setOnAction(new EventHandler<ActionEvent>() 
//			{
//
//				@Override
//				public void handle(ActionEvent event) 
//				{
//					//lade Informationen des Mondes
//				}
//			});
//			
//			this.getSpielUmgebungController().setzeInformationen(button);		
//		}
		
		
		Button oeffnePlanetensystem = new Button("zum Planetensystem");
		
		oeffnePlanetensystem.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) 
			{
				Planetensystem planetsystem = new Planetensystem(planet);
				StageController.getInstance().setScene(planetsystem.getScene());
			}	
		});
		
		
		this.getSpielUmgebungController().setzeInformationen(oeffnePlanetensystem);		
	}
	
	
	/**
	 * zeichnet die Sonne
	 * @param stern
	 * @param maxGroeﬂe
	 */
	private void zeichneSonne(Stern stern , double maxGroeﬂe)
	{
   	 	//erstelle einen Sonne
   	 	Sphere sonne = new Sphere();
   	 	//uebergibt den Radius. dies hat die Doppelte groﬂe des groeﬂten Planeten
   	 	sonne.setRadius(maxGroeﬂe*2);
   	    //fuegt die Sonne dem Universum hinzu 
   	 	this.getSubSceneRoot().getChildren().add(sonne);	
   	 	//positioniert die Sonne in der Mitte 
   	    sonne.setTranslateX(0);
   	    sonne.setTranslateY(0);
   	    sonne.setTranslateZ(0); 	     
   	        
   	    //gibt der Sonne eine Aussehen
   	    sonne.setMaterial(stern.getAussehn());
	        
	    //macht den Himelskoerper anklickbar
   	    sonne.setOnMouseClicked(new EventHandler<MouseEvent>()
	    {
			@Override
			public void handle(MouseEvent event) 
			{		
				if (event.getClickCount() == 2)
				{
					kamera.setDrehpunkt(new Point3D(sonne.getTranslateX() , sonne.getTranslateY() , sonne.getTranslateZ()));
				}
				else
				{
					//lade informationen vom koerper
				}
			}
	    });     
	}
	

	
	//¥--------------------------------Kamera-dieser-Scene--------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	
	
	
	

	/**
	 * Dies Klasse kann Nods mit der Maus zum rotieren bringen
	 * dazu werden Nods einer Liste zugeordnet und alle Elemente dieser Liste werden dann bei 
	 * gedrucktter Maustaste bewegt. 
	 *  
	 * @author Demix
	 *
	 */
	public class KameraSonnensystem extends Kamera
	{
		/**
		 * eine Liste mit den Nodes die sich bei benutzung der linken Maustaste rotiert
		 */
		private ArrayList<Node> nodeList = new ArrayList<Node>();

		/**
		 * zur rotation der Node um die X Achse
		 */
		private Rotate rotationX = new Rotate(0 , 0, 0, 0 , Rotate.X_AXIS);
		
		/**
		 * zur rotation der Node um die Y Achse
		 */
		private Rotate rotationY = new Rotate(0 , 0, 0, 0 , Rotate.Y_AXIS);
		
		/**
		 * ertellt ein Objekt von NodeRotationkamera
		 */
		public KameraSonnensystem(Scene scene)
		{
			this.getKamera().setTranslateZ(-100);
			this.getKamera().setTranslateX(-Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
			this.getKamera().setTranslateY(-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
			this.getKamera().setFarClip(10000000);
			this.getKamera().setNearClip(0.1);
			this.getKamera().setFieldOfView(35);
			initEventHandler(scene);
		}
		
		
		/**
		 * erstellt ein Objekt von NodeRotationkamera 
		 * @param nodeList mit nodes die sich rotieren sollen. Auf der erste Node in der Liste werden die MausEvents abgefangen
		 */
		public KameraSonnensystem(ArrayList<Node> nodeList)
		{
			this.addNode(nodeList); 
		}
		
		
		/**
		 * fuegt eine Node hinzu die rotieren soll
		 * @param node
		 */
		public void addNode(Node node)
		{
			this.nodeList.add(node);
			node.getTransforms().addAll(rotationX , rotationY);
		}
		
		
		/**
		 * fuegt eine liste von Nodes die sich rotieren sollen hinzu
		 * @param nodeList
		 */
		public void addNode(ArrayList<Node> nodeList)
		{
			for (int i = 0; nodeList.size() > i ; i++)
				this.addNode(nodeList.get(i));
		}
		
		
		/**
		 * 
		 * @param eventNode
		 */
		public void initEventHandler(Scene scene)
		{   	
			scene.setOnKeyPressed(new EventHandler<KeyEvent>()
			{

				@Override
				public void handle(KeyEvent e)
				{
					if (e.getCode() == KeyCode.W)
					{
						doZoomen(100);
					}
					else if (e.getCode() == KeyCode.S)
					{
						doZoomen(-100);
					}
					else if (e.getCode() == KeyCode.ESCAPE)
					{
						StageController.getInstance().openLastScene();
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
		}
		
		
		/**
		 * rotiert die Node
		 */
		public void rotation(double mouseDeltaY , double mouseDeltaX)
		{	
			for (int i = 0 ; nodeList.size() > i ; i++)
			{
				this.rotationX.setAngle(rotationX.getAngle() - mouseDeltaX*2);
				this.rotationY.setAngle(rotationY.getAngle() + mouseDeltaY*2);
			}
		}
		
		
		/**
		 * setzt einen Punkt um den sich die Node drehen soll
		 * @param drehpunkt
		 */
		protected void setDrehpunkt(Point3D drehpunkt)
		{
			rotationX.setPivotX(drehpunkt.getX());
			rotationX.setPivotY(drehpunkt.getY());
			rotationX.setPivotZ(drehpunkt.getZ());
			
			rotationY.setPivotX(drehpunkt.getX());
			rotationY.setPivotY(drehpunkt.getY());
			rotationY.setPivotZ(drehpunkt.getZ());
			
			ArrayList<PathTransition> newPosi = new ArrayList<PathTransition>();
			
			for (int i = 0; nodeList.size() > i ; i++)
			{
				Path path = new Path();
				path.getElements().add(new MoveTo(drehpunkt.getX()-Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 ,drehpunkt.getY()-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2));
				newPosi.add(new PathTransition(Duration.millis(2000), path , this.getKamera()));		
			}	
			
			for (int i = 0; newPosi.size() > i ; i++)
			{
				newPosi.get(i).play();
			}
		}
		
		
		/**
		 * setzt einen Punkt um den sich die Node drehen soll
		 * @param drehpunkt
		 */
		protected void setDrehpunkt(double posiX , double posiY , double posiZ)
		{
			this.setDrehpunkt(new Point3D(posiX , posiY , posiZ));
		}
		
		
		/**
		 * zoomt naher ran oder weiter weg. kommt gant auf die uebergebenen Werte drauf an
		 * @param zoome
		 */
		private void doZoomen(double zoome)
		{
			if (this.getKamera().getTranslateZ() <5000)
			{
				this.getKamera().setTranslateZ(this.getKamera().getTranslateZ()+zoome);
			}
		}
	}

}
