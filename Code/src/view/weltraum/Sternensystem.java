package view.weltraum;

import controller.Bewegungsmanager;
import controller.GameManager;
import himmelskoerper.SchwarzesLoch;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;

/*
 * 
 */
public class Sternensystem extends WeltraumSystem
{
	private KameraSternensystem kamera = new KameraSternensystem();
	
	public Sternensystem(SchwarzesLoch zentrum)
	{
		zeichneSternsystem(zentrum);
	}
	
	/**
	 * zeichnet ein Sonnensystem
	 * 
	 * @param stern
	 */
	public void zeichneSternsystem(SchwarzesLoch zentrum)
	{
		//diese groeße entsrpcht 100% der maximalen raduisGroeße
		double maxRadius = 50; 
		//die groeßte Sonne entspricht 100%
		double groeßteSonne = 0;
		
		//dieser SystemRadius entspricht 100% und wird auf 1000 beschraenkt
		double orbitRadius = zentrum.getSystemRadius();
		//Der OrbitRadius wird auf 1000 beschrenkt. 
		double maxOrbitRadius = 1000; 
							
		this.zeichneSchwarzesLoch(zentrum);

		for (int i = 0; zentrum.getChildren().size() > i ; i++)
		{
			if (zentrum.getChild(i).getRadius() > groeßteSonne)
			{
				groeßteSonne = zentrum.getChild(i).getRadius();
			}
		}
		
		for (int j = 0; zentrum.getChildren().size() > j; j++)
		{		
			double radius = (groeßteSonne/zentrum.getChild(j).getRadius()) * maxRadius;
			
   	 		//erstelle einen Planeten
   	 		Sphere himmelskoerper = new Sphere();
   	 		himmelskoerper.setRadius(radius);
   	       
   	 		this.getSubSceneRoot().getChildren().add(himmelskoerper);	
   	        Bewegungsmanager.getInstance().addInOrbitObjectToPositionsRechner(zentrum.getChild(j));  	        
   	       
   	        himmelskoerper.translateXProperty().bind(zentrum.getChild(j).getPositionProperty()[0]);
   	        himmelskoerper.translateYProperty().bind(zentrum.getChild(j).getPositionProperty()[1]);
   	        himmelskoerper.translateZProperty().bind(zentrum.getChild(j).getPositionProperty()[2]);	

   	        System.out.println( "Postion des Körpers:   X: "+himmelskoerper.getTranslateX()+"   Y: "+himmelskoerper.getTranslateY()+"   Z: "+himmelskoerper.getTranslateZ()+"    Radius: "+himmelskoerper.getRadius());
   	        
   	        //setzt das aussehen der Kugel
	        himmelskoerper.setMaterial(zentrum.getChild(j).getAussehn());
	        
	        //macht den Himelskoerper anklickbar
	        himmelskoerper.setOnMouseClicked(new EventHandler<MouseEvent>()
	        {
				@Override
				public void handle(MouseEvent event) 
				{					
					if (event.getClickCount() == 2)
					{
						kamera.setPosition(new Point3D(himmelskoerper.getTranslateX() , himmelskoerper.getTranslateY() , himmelskoerper.getTranslateZ()));	
					}
					else
					{
						
						//Lade Informationen
					}
				}
	        });     
   	 	}
	}

	private void zeichneSchwarzesLoch(SchwarzesLoch zentrum) 
	{
		
	}
}
