package view.weltraum;

import controller.Bewegungsmanager;
import himmelskoerper.InOrbit;
import himmelskoerper.Planet;
import himmelskoerper.Stern;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;

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
		//Der OrbitRadius wird auf 4000 beschrenkt. Dies sind die 100% des Orbitradius. die Groﬂe des originals wird angepasst 
		double maxOrbitRadius = 4000; 
		//Der Groeﬂte Plante ist 100 und der das minimum liegt bei 10 //TODO das verhaehltnis muss noch angepasst werden
		double groeﬂterPlanet = 0;
		//Die maximale Groeﬂe eines Planeten
		double maxGroeﬂe = 50; 
		
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
			//hier wird die Groeﬂe der Kugel berechnet, die den Planeten darstellen soll. 
			double radius = (stern.getChild(j).getRadius())/groeﬂterPlanet* maxGroeﬂe;
			//hier wird die Position des Planeten aktualiesiert
			stern.getChild(j).refresh();
			//Die aktulle position wird in dieser Variable gepeichert (Polarefrom)
			Point3D point = new Point3D (  stern.getChild(j).getPositionPolar().get(0) ,stern.getChild(j).getPositionPolar().get(1) ,stern.getChild(j).getPositionPolar().get(2)  );
			//hier wird die entfernung zur Sonne angepasst
			double orbitRadiusPlanet = (point.distance(0 , 0 , 0) / orbitRadius)*maxOrbitRadius;
			System.out.println("Der Orbitradius: ergibt sich aus der Dechnug: point.distance(0 , 0 , 0): "+point.distance(0 , 0 , 0)+ " durch orbitRadius: "+orbitRadius+"* maxOrbitRadius: "+maxOrbitRadius+" = "+orbitRadiusPlanet);
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
   	        
   	        System.out.println( "Postion des Kˆrpers:   X: "+himmelskoerper.getTranslateX()+"   Y: "+himmelskoerper.getTranslateY()+"   Z: "+himmelskoerper.getTranslateZ()+"    Radius: "+himmelskoerper.getRadius());
   	        
   	        //gibt den Planeten sein Aussehen
	        himmelskoerper.setMaterial(stern.getChild(j).getAussehn());
	        
	        //macht den Himelskoerper anklickbar
	        himmelskoerper.setOnMouseClicked(new EventHandler<MouseEvent>()
	        {
				@Override
				public void handle(MouseEvent event) 
				{					
					if (event.getClickCount() == 2)
					{
						kamera.setDrehpunkt(new Point3D(himmelskoerper.getTranslateX() , himmelskoerper.getTranslateY() , himmelskoerper.getTranslateZ()));	
					}
					else
					{
						
						//Lade Informationen
					}
				}
	        });     
   	 	}
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
