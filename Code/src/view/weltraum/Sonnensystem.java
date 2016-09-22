package view.weltraum;

import controller.Bewegungsmanager;
import himmelskoerper.Himmelskoerper;
import himmelskoerper.InOrbit;
import himmelskoerper.Planet;
import himmelskoerper.Stern;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;
import view.hauptmenu.StageController;

/**
 * Diese Klasse zeichnet ein Sonnensystem um in diesem zu navigieren. 
 *  
 * @author Demix
 *
 */
public class Sonnensystem extends WeltraumSystem
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
				Point3D point = new Point3D (  stern.getChild(j).getPositionPolar().get(0) ,stern.getChild(j).getPositionPolar().get(1) ,stern.getChild(j).getPositionPolar().get(2)  );
				//hier wird die entfernung zur Sonne angepasst
				double orbitRadiusPlanet = (point.distance(0 , 0 , 0) / orbitRadius)*maxOrbitRadius;
	
	   	 		//erstelle nun den Planeten
	   	 		Sphere himmelskoerper = new Sphere();
	   	 		//setzt den radius
	   	 		himmelskoerper.setRadius(radius);
	   	 	
	   	 		//bindet die Position ein einen Wert	
	   	        SimpleDoubleProperty[] posi = Bewegungsmanager.getInstance().addPlanetToPositionsRechnerWithAdapta(orbitRadiusPlanet , (Planet) stern.getChild(j));  	        
	   	        himmelskoerper.translateXProperty().bind(posi[0]);
	   	        himmelskoerper.translateYProperty().bind(posi[1]);
	   	        himmelskoerper.translateZProperty().bind(posi[2]);	
	   	        //setzt den Planeten ins Universum
	   	        this.getSubSceneRoot().getChildren().add(himmelskoerper);
	   	        //sagt das die positions staendig aktualliesiert werden soll
	   	        Bewegungsmanager.getInstance().addInOrbitObjectToPositionsRechner(stern.getChild(j));
	   	         
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
//					//TODO 		lade Informationen des Mondes
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
	
	
	/**
	 * zeichnet die Umlaufbahn eines Planeten
	 * 
	 * @param radius
	 * @param planet
	 */
	private void zeichneUmlaufbahn(double entfernungZurSonne, InOrbit planet)
	{
		//TODO hier soll die Umlaufbahn einzelner Planeten gezechnet werden
	}
}
