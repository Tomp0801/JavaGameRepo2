package view.weltraum;

import controller.Bewegungsmanager;
import global.Constants;
import himmelskoerper.Himmelskoerper;
import himmelskoerper.Mond;
import himmelskoerper.Planet;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;

public class Planetensystem extends WeltraumSystem
{
	private Planet planet; 
	
	private KameraPlanetensystem kamera;
	
	public Planetensystem(Planet planet)
	{   	
		kamera = new KameraPlanetensystem(this.getScene());
		this.planet = planet; 
		this.getSubScene().setCamera(kamera.getKamera());
		//zeichenet das Planetensystem mit den Monden und allem was Raumschiffen ect. 
		zeichnePlanetensystem(this.planet); 
    }
	
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
			//TODO weiter Onjekte wie Raumschiff und Raumbassisin hinzufuegen
   	 	}
	}
	
	
	/**
	 * zeichnet den Planeten
	 * @param planet
	 */
	private void zeichnePlanet(Planet planet)
	{				
   	 	//erstelle einen Himmerlskoerper
   	 	Sphere himmelskoerper = new Sphere();
   	 	
   	 	//TODO die groﬂe muss noch angepasst werden
   	 	himmelskoerper.setRadius(planet.getRadius());
   	 	
   	 	//setzt die Position in den Mittelpunkt
   	 	himmelskoerper.setTranslateX(0);
   	 	himmelskoerper.setTranslateY(0);
   	 	himmelskoerper.setTranslateZ(0);
   	 	
   	    this.getSubSceneRoot().getChildren().add(himmelskoerper);	 	        
   	 	
   	             
   	    //setzt das aussehen der Kugel
   	    himmelskoerper.setMaterial(planet.getAussehn());
	        
   	    //macht den Himelskoerper anklickbar
   	    himmelskoerper.setOnMouseClicked(new EventHandler<MouseEvent>()
   	    {
   	    	@Override
   	    	public void handle(MouseEvent event) 
   	    	{					
   	    		if (event.getClickCount() == 2)
   	    		{
   	    			//TODO kamera auf dem Planeten zentrieren
   	    			ladeInformationen(planet);
   	    		}
   	    		else
   	    		{
   	    			ladeInformationen(planet);
   	    		}
   	    	}
   	    });     
	}
	
	
	/**
	 * zeichnet einen Mond
	 * @param mond der gezeichnet werden soll
	 */
	private void zeichneMond(Mond mond)
	{
		//erstelle einen Mond
	 	Sphere himmelskoerper = new Sphere();
	 	
	 	//TODO Der Radius muss noch angepasst werden
	 	himmelskoerper.setRadius(mond.getRadius());
	 	System.out.println(himmelskoerper.getRadius());
	 	this.getSubSceneRoot().getChildren().add(himmelskoerper);	
	    Bewegungsmanager.getInstance().addInOrbitObjectToPositionsRechner(mond);  	        
	   
	    himmelskoerper.translateXProperty().bind(mond.getPositionProperty()[0]);
	    himmelskoerper.translateYProperty().bind(mond.getPositionProperty()[1]);
	    himmelskoerper.translateZProperty().bind(mond.getPositionProperty()[2]);	
         
		System.out.println(himmelskoerper.getTranslateX()+"    "+himmelskoerper.getTranslateY()+"    "+himmelskoerper.getTranslateZ());
        //setzt das aussehen der Kugel
        himmelskoerper.setMaterial(mond.getAussehn());
        
        //macht den Himelskoerper anklickbar
        himmelskoerper.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
			@Override
			public void handle(MouseEvent event) 
			{					
				if (event.getClickCount() == 2)
				{
					ladeInformationen(mond);
				}
				else
				{
					ladeInformationen(mond);
				}
			}
        });     
	}
	
	
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
	}
}
