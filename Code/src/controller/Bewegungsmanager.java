package controller;

import java.util.ArrayList;
import java.util.Vector;

import himmelskoerper.InOrbit;
import himmelskoerper.Planet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * der Bewegungsmanager kuemmert sich um die berechnung der Positionen von Himmerlskoerpern.
 * @author Demix
 *
 */
public class Bewegungsmanager 
{
	/**
	 * instance vom Bewegingsmanager
	 */
	private static Bewegungsmanager instance;
	
	/**
	 * in diesr Liste befinden sich alle InOrbit Objecte dessen Position in einer while(true) Schleife 
	 * staendig erneuert werden solll 
	 */
	private ArrayList<InOrbit> positionsRechnerListe = new ArrayList<InOrbit>();
	
	/**
	 * in dieser Liste befinden sich Paneten eines Sonnensystems dessen position sich ein
	 */
	private ArrayList<Bewegungsadapta> adaptaSonnensystemRechner = new ArrayList<Bewegungsadapta>();
	
	
	/**
	 * Konsturktor vom Bewegungsmanager
	 */
	private Bewegungsmanager()
	{
		instance = this; 	
		this.reellepositionRechner();
	}
	
	
	/**
	 * gibt die Instance vom Bewegungsmanager zurueck
	 * @return
	 */
	public static Bewegungsmanager getInstance()
	{
		if (instance == null)
		{
			new Bewegungsmanager();
		}
		return instance;	
	}
	
	
	/**
	 * fuegt in InOrbit Objekt in den piritionsrechnerListe hinzu. Dann wird die Position
	 * von diesem Object in einer dauerschleife neu berechnet..
	 * @param inOrbit das Objekt was hinzugefuegt werden soll.
	 */
	public void addInOrbitObjectToPositionsRechner(InOrbit inOrbit)
	{
		this.positionsRechnerListe.add(inOrbit);
	}
	
	
	/**
	 * loescht in InOrbit objekt von der positionsRechnerListe.
	 * Die position des Objektes wird nun nicht weiter berechnet
	 * @param koerper
	 */
	public void delateFromPositionsRechner(InOrbit koerper)
	{
		positionsRechnerListe.remove(koerper);
	}
	

	/**
	 * 
	 * @param maxRadius
	 * @param radius
	 * @return
	 */
	public SimpleDoubleProperty[] addPlanetToPositionsRechnerWithAdapta(double radius, Planet planet)
	{
		adaptaSonnensystemRechner.add(new Bewegungsadapta(radius , planet));
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
					
					for (int i = 0; positionsRechnerListe.size() > i ; i++)
					{
						positionsRechnerListe.get(i).refresh();
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
	public class Bewegungsadapta
	{
		private SimpleDoubleProperty posiX = new SimpleDoubleProperty(); 
		
		private SimpleDoubleProperty posiY = new SimpleDoubleProperty();
		
		private SimpleDoubleProperty posiZ = new SimpleDoubleProperty();
		
		private double radius;
		
		private Planet planet; 
		
		Bewegungsadapta(double radius, Planet planet)
		{
			this.planet = planet;
			this.radius = radius;
		}
		
		public synchronized DoubleProperty getPosiX() 
		{
			return posiX;
		}

		public synchronized DoubleProperty getPosiY() 
		{
			return posiY;
		}

		public synchronized DoubleProperty getPosiZ() 
		{
			return posiZ;
		}
		
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
