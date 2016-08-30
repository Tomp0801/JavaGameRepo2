package view.weltraum;

import controller.Bewegungsmanager;
import controller.GameManager;
import himmelskoerper.InOrbit;
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
	private KameraSonnensystem kamera = new KameraSonnensystem();
	
	public Sonnensystem(Stern stern)
	{
		zeichneSonnensystem(stern);
		this.getSubScene().setCamera(kamera.getKamera());
	}
	
	
	/**
	 * zeichnet ein Sonnensystem
	 * 
	 * @param stern
	 */
	public void zeichneSonnensystem(Stern stern)
	{
		//dies entspricht 100% 
		double orbitRadius = stern.getOrbitRadius();
		//Der OrbitRadius wird auf 4000 beschrenkt. 
		double maxOrbitRadius = 4000; 
		//Der Groe�te Plante ist 100 und der das minimum liegt bei 10 //TODO das verhaehltnis muss noch angepasst werden
		double groe�terPlanet = 0;
		//Die maximale Groe�e eines Planeten
		double maxGroe�e = 50; 
		//zeichnet die Sonne
		
		
		this.zeichneSonne(stern, maxGroe�e);
		//sucht den groe�ten Planet und speichert die Gro�e in die Variable. Dies entspricht dann 100% 
		for (int i = 0; stern.getChildren().size() > i ; i++)
		{
			if (stern.getChild(i).getRadius() > groe�terPlanet)
			{
				groe�terPlanet = stern.getChild(i).getRadius();
			}
		}

		for (int j = 0; stern.getChildren().size() > j; j++)
		{		
			//hier wird die Groe�e der Kugel berechnet, die den Planeten darstellen soll. 
			double radius = (groe�terPlanet/stern.getChild(j).getRadius()) * maxGroe�e;
			
			stern.getChild(j).refresh();
			double t = stern.getChild(j).getPositionPolar() //TODO
			double orbitRadiusPlanet = (orbitRadius/stern.getChild(j).refresh();)
			
   	 		//erstelle einen Planeten
   	 		Sphere himmelskoerper = new Sphere();
   	 		himmelskoerper.setRadius(radius);
   	       
   	 		this.getSubSceneRoot().getChildren().add(himmelskoerper);	
   	        SimpleDoubleProperty[] posi = Bewegungsmanager.getInstance().addPlanetToPositionsRechnerWithAdapta(orbitRadius, orbitRadiusPlanet);  	        
   	        himmelskoerper.translateXProperty().bind(stern.getChild(j).getPositionProperty()[0]);
   	        himmelskoerper.translateYProperty().bind(stern.getChild(j).getPositionProperty()[1]);
   	        himmelskoerper.translateZProperty().bind(stern.getChild(j).getPositionProperty()[2]);	

   	        //TODO
//   	    this.zeichneUmlaufbahn( , stern.getChild(j));
   	        
   	        System.out.println( "Postion des K�rpers:   X: "+himmelskoerper.getTranslateX()+"   Y: "+himmelskoerper.getTranslateY()+"   Z: "+himmelskoerper.getTranslateZ()+"    Radius: "+himmelskoerper.getRadius());
   	        
   	        //setzt das aussehen der Kugel
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
	 * @param maxGroe�e
	 */
	private void zeichneSonne(Stern stern , double maxGroe�e)
	{
   	 	//erstelle einen Sonne
   	 	Sphere sonne = new Sphere();
   	 	sonne.setRadius(maxGroe�e*2);
   	       
   	 	this.getSubSceneRoot().getChildren().add(sonne);	  
   	    sonne.setTranslateX(0);
   	    sonne.setTranslateY(0);
   	    sonne.setTranslateZ(0); 	     
   	        
   	    //setzt das aussehen der Kugel
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
		//TODO 
	}
}
