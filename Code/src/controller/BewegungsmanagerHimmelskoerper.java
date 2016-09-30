package controller;

import java.util.ArrayList;
import java.util.Vector;

import himmelskoerper.InOrbit;
import himmelskoerper.Planet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * der Bewegungsmanager kuemmert sich um die berechnung der Positionen von Himmerlskoerpern die sich beim
 * Bewegungsmanager angemeldet haben. 
 * 
 * @author Dennis 
 *
 */
public class BewegungsmanagerHimmelskoerper 
{
	/**
	 * instance vom Bewegingsmanager
	 */
	private static BewegungsmanagerHimmelskoerper instance;
	
	/**
	 * in diesr Liste befinden sich alle InOrbit Objekte dessen Position in einer while(true) Schleife 
	 * staendig erneuert werden solll 
	 */
	private ArrayList<InOrbit> bewegungsRechnerListe = new ArrayList<InOrbit>();
	
	/**
	 * in dieser Liste befinden sich Paneten eines Sonnensystems dessen position sich ein
	 */
	private ArrayList<BewegungsadaptaSonnensystem> adaptaSonnensystemRechner = new ArrayList<BewegungsadaptaSonnensystem>();
	
	
	/**
	 * Konsturktor vom Bewegungsmanager
	 */
	private BewegungsmanagerHimmelskoerper()
	{
		instance = this; 	
		this.reellepositionRechner();
	}
	
	
	/**
	 * gibt die Instance vom Bewegungsmanager zurueck
	 * @return
	 */
	public static BewegungsmanagerHimmelskoerper getInstance()
	{
		if (instance == null)
		{
			new BewegungsmanagerHimmelskoerper();
		}
		return instance;	
	}
	
	
	/**
	 * fuegt in InOrbit Objekt in den piritionsrechnerListe hinzu. Dann wird die Position
	 * von diesem Object in einer dauerschleife neu berechnet..
	 * @param inOrbit das Objekt was hinzugefuegt werden soll.
	 */
	public void addInOrbitObjectToBewegungsRechner(InOrbit inOrbit)
	{
		this.bewegungsRechnerListe.add(inOrbit);
	}
	
	
	/**
	 * loescht in InOrbit objekt von der positionsRechnerListe.
	 * Die position des Objektes wird nun nicht weiter berechnet
	 * @param koerper
	 */
	public void delateFromPositionsRechner(InOrbit koerper)
	{
		bewegungsRechnerListe.remove(koerper);
	}
	

	/**
	 * 
	 * @param maxRadius
	 * @param radius
	 * @return
	 */
	public SimpleDoubleProperty[] addPlanetToPositionsRechnerWithAdapta(double radius, Planet planet)
	{
		adaptaSonnensystemRechner.add(new BewegungsadaptaSonnensystem(radius , planet));
		adaptaSonnensystemRechner.get(adaptaSonnensystemRechner.size()-1).refresh();
		return new SimpleDoubleProperty[] {adaptaSonnensystemRechner.get(adaptaSonnensystemRechner.size()-1).posiX , adaptaSonnensystemRechner.get(adaptaSonnensystemRechner.size()-1).posiY , adaptaSonnensystemRechner.get(adaptaSonnensystemRechner.size()-1).posiZ}; 
	}
	
	
	/**
	 * diese Methode rechnet in einer Dauerschleife alle Positionen von InOrbit Objekten durch die sich
	 * in der ArrayList positionsRechnerListe befinden
	 */
	private void reellepositionRechner()
	{
		Thread rechner = new Thread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				while(true)
				{	
					try {Thread.sleep(50);} catch (InterruptedException e){e.printStackTrace();}
					
					for (int i = 0; bewegungsRechnerListe.size() > i ; i++)
					{
						bewegungsRechnerListe.get(i).refresh();
					}
					for (int i = 0; adaptaSonnensystemRechner.size() > i ; i++)
					{
						adaptaSonnensystemRechner.get(i).refresh();
					}
				}	
			}
		});		
		rechner.setDaemon(true);
		rechner.start();
	}
	
	
	/**
	 * Diese Klasse passt die reelle Position von Planeten eines Sonnensystems einer virtuellen version an um diese
	 * einfacher darzustellen 
	 * 
	 * @author Dennis
	 *
	 */
	public class BewegungsadaptaSonnensystem
	{
		/**
		 * X Koardinate das auf die view eines Sonnensystems angepasst ist
		 */
		private SimpleDoubleProperty posiX = new SimpleDoubleProperty(); 
		
		/**
		 * Y Koardinate das auf die view eines Sonnensystems angepasst ist
		 */
		private SimpleDoubleProperty posiY = new SimpleDoubleProperty();
		
		/**
		 * Z Koardinate das auf die view eines Sonnensystems angepasst ist
		 */
		private SimpleDoubleProperty posiZ = new SimpleDoubleProperty();
		
		/**
		 * dieser Radius ist die angepasste entfernung zum Zentrum des Sonnensystems.
		 * Mit dieser wird eine angepasste position berechnet
		 */
		private double radius;
		
		/**
		 * der Planet der sich an die Werte des adapters bindet
		 */
		private Planet planet; 
		
		/**
		 * erstellt ein Objekt vom BewegungsadaptaSonnensystems
		 * 
		 * @param radiusieser Radius ist die angepasste entfernung zum Zentrum des Sonnensystems.Mit dieser wird eine angepasste position berechnet
		 * @param planet der Planet der sich an die Werte des adapters bindet
		 */
		BewegungsadaptaSonnensystem(double radius, Planet planet)
		{
			this.planet = planet;
			this.radius = radius;
		}
		
		
		/**
		 * aktuallisiert die Position auf der view
		 */
		public synchronized void refresh()
		{
			double x = 0; 
			double y = 0; 
			double z = 0; 
			
			Vector<Double> positionsVector = this.planet.getPositionPolar();
			
			x = radius * Math.sin(positionsVector.get(2)) * Math.cos(positionsVector.get(1));
			y = radius * Math.sin(positionsVector.get(2)) * Math.sin(positionsVector.get(1));
			z = radius * Math.cos(positionsVector.get(2));			
			
			posiX.set(x);
			posiY.set(y);
			posiZ.set(z);
		}
	}
}
