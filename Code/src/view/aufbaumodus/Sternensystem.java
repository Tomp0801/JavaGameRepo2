package view.aufbaumodus;

import controller.Bewegungsmanager;
import himmelskoerper.SchwarzesLoch;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;


import java.awt.Toolkit;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/*
 * 
 */
public class Sternensystem extends AufbaumodusSichtweiseWeltraum
{
	private KameraSternensystem kamera;
	
	private SchwarzesLoch zentrum;
	
	public Sternensystem(SchwarzesLoch zentrum , int x , int y , int z)
	{	
		KameraSternensystem kamera = new KameraSternensystem(this.getScene(), zentrum.getSystemRadius());
		this.zentrum = zentrum;
		this.getSubScene().setCamera(kamera.getKamera());
		zeichneSternsystem(zentrum);
	}
	
	
	public void zeichneSternsystem(SchwarzesLoch zentrum)
	{
		//diese groeße entspricht 100% der maximalen raduisGroeße
		double maxRadius = 100; 
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
			if (radius > 50 )
				radius = 50; 
			
   	 		//erstelle ein Stern
   	 		Sphere stern = new Sphere();
   	 		stern.setRadius(radius);
   	       
   	 		this.getSubSceneRoot().getChildren().add(stern);	
   	 		
   	 		// TODO zu einen rechner hinzufuegen der nicht so heufig rechnet
   	        Bewegungsmanager.getInstance().addInOrbitObjectToPositionsRechner(zentrum.getChild(j));  	        
   	       
   	        stern.translateXProperty().bind(zentrum.getChild(j).getPositionProperty()[0]);
   	        stern.translateYProperty().bind(zentrum.getChild(j).getPositionProperty()[1]);
   	        stern.translateZProperty().bind(zentrum.getChild(j).getPositionProperty()[2]);	

   	        System.out.println( "Postion des Körpers:   X: "+stern.getTranslateX()+"   Y: "+stern.getTranslateY()+"   Z: "+stern.getTranslateZ()+"    Radius: "+stern.getRadius());
   	        
   	        //setzt das aussehen der Kugel
	        stern.setMaterial(zentrum.getChild(j).getAussehn());
	        
	        //macht den Himelskoerper anklickbar
	        stern.setOnMouseClicked(new EventHandler<MouseEvent>()
	        {
				@Override
				public void handle(MouseEvent event) 
				{					
					if (event.getClickCount() == 2)
					{
						//TODO lade infos 	
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
	

	
	
	//´--------------------------------Kamera-dieser-Scene--------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//	
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	//------------------------------------------------------------------------------------------------------------------------------------------------//
	

	/**
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
		private int SEKTIONSIZE = 1000; 
		
		/**
		 * zuer behandlung von Events 
		 */
		private Scene scene; 
		
		public KameraSternensystem(Scene scene,double sizeSystem)
		{
			initEventBehandlung();
			this.getKamera().setTranslateZ(-100);
			this.getKamera().setTranslateX(-Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
			this.getKamera().setTranslateY(-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		}

		private void initEventBehandlung() 
		{
			this.scene.setOnKeyTyped(new EventHandler<KeyEvent>()
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
					
					setNewPosition(sektion[0]+x, sektion[1]+y, sektion[2]+z);	
				}		
			});		
		}
		
		private void setNewPosition(int x, int y, int z)
		{
//			Path path = new Path();
//			path.getElements().add(new MoveTo(0 ,0 , 0));
//			path.getElements().add(new )
//			
//			PathTransition bewegung = new PathTransition(Duration.seconds(2) ,path, this.getKamera());
	//
//			bewegung.play();
			
			this.setPosition(x*SEKTIONSIZE, y*SEKTIONSIZE, z*SEKTIONSIZE);
		}
	}

}
