package controller;

import java.util.ArrayList;
import java.util.Vector;

import himmelskoerper.Stern;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;

/**
 * Die Klasse verwaltet die Berechnungen der Positionen der Sterne
 * @author Demix
 *
 */
public class BewegungsmanagerStern 
{
	/**
	 * Alle Positionen der Sterne die dieser Liste hinzugefuegt wurden, 
	 * werden in einem festgelegten zeitlichen Abstand neu berechnet.
	 */
	private ArrayList<Stern> bewegungsRechnerStern = new ArrayList<Stern>();
	
	
	private ArrayList<BewegungsadapterStern> bewegungsadapterSternsystemRechner = new ArrayList<BewegungsadapterStern>();
	
	
	/**
	 * die Instance des BewegungsmanagerStern.
	 * Sie wird erst zugewissen, wenn die Methoode getInstace aufgerufen wird
	 */
	private static BewegungsmanagerStern instance;
	
	/**
	 * der Thread der fuer die Berechnungen zusteandig ist.
	 */
	private Thread rechnerThread;
	
	/**
	 * der Wert von sleepTime beschreibt die Wartezeit zwischen einzelne Berechnungen 
	 */
	private int sleepTime = 10000;
	
	/**
	 * erstellt ein Objekt vom Bewwegungsmanager. Dies kann nur durch die static methode getBewegungsmanager geschehen
	 */
	private BewegungsmanagerStern()
	{
		instance = this;
		
		rechnerThread = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while(true)
				{
					try {
						
					for (int i = 0; bewegungsRechnerStern.size() > i ; i++)
					{
						
						Thread.sleep(sleepTime);
						bewegungsRechnerStern.get(i).refresh();	
						
					}
					
					for (int i = 0; bewegungsRechnerStern.size() > i ; i++)
					{
						
						Thread.sleep(sleepTime);
						bewegungsadapterSternsystemRechner.get(i).refresh();	
						
					}
					
					if (bewegungsRechnerStern.size() <= 0)
					{
						Thread.sleep(sleepTime*2);
					}
					
					
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}	
			}
		});
		
		rechnerThread.setDaemon(true);
		rechnerThread.start();
	}
	
	
	/**
	 * @return den Thread, der die Berechnungen ausfuehrt
	 */
	public Thread getRechnerThread()
	{
		return this.rechnerThread;
	}
	
	/**
	 * 
	 * @return die Instance vom Bewegungsmanager
	 */
	public static BewegungsmanagerStern getInstance()
	{
		if (instance == null)
			new BewegungsmanagerStern(); 
		return instance;
	}
	
	
	/**
	 * gibt die Liste zurueck in der Verweise auf die Sterne befinden dessen position neu berechnet werden soll
	 * @return ArrayList<Stern>
	 */
	public ArrayList<Stern> getBewegungsRechnerStern()
	{
		return this.bewegungsRechnerStern;	
	}

	
	/**
	 * 
	 * @return gibt die Zeit zurueck die der RechnerThread still steht bevor er die naechste Position berechnet
	 */
	public int getSleepTime()
	{
		return sleepTime;
	}


	/**
	 * setzt eine Zeitdie der RechnerThread still steht bevor er die naechste Position berechnet
	 */
	public void setSleepTime(int sleepTime) 
	{
		this.sleepTime = sleepTime;
	}
	
	
	/**
	 * fuegt einen Stern zum bewegungsadapta hinzu 
	 * @param stern
	 * @return gibg SimpleDoubleProperty zurueck an dessen Werten sich ein Stern binden kann. [0] = posiX [1] = posiY [2] = posiZ
	 */
	public SimpleDoubleProperty[] addToBewegungsSternsystemRechnerAdapa(Stern stern)
	{
		this.bewegungsadapterSternsystemRechner.add(new BewegungsadapterStern(stern));
		this.bewegungsadapterSternsystemRechner.get(this.bewegungsadapterSternsystemRechner.size()-1).refresh();
		return new SimpleDoubleProperty[]{bewegungsadapterSternsystemRechner.get(bewegungsadapterSternsystemRechner.size()-1).posiX , bewegungsadapterSternsystemRechner.get(bewegungsadapterSternsystemRechner.size()-1).posiY  ,bewegungsadapterSternsystemRechner.get(bewegungsadapterSternsystemRechner.size()-1).posiZ };
	}
	
	
	
	/**
	 * Wenn ein Stern sich den aktuellen Koardinaten anpassen soll, muss er sich an die Werte
	 *  eines Bewegungsadapters binden
	 * 
	 * @author Demix
	 *
	 */
	private class BewegungsadapterStern
	{
		/**
		 * X Koardinate das auf die view eines Sternensystems angepasst ist
		 */
		private SimpleDoubleProperty posiX = new SimpleDoubleProperty(); 
		
		/**
		 * Y Koardinate das auf die view eines Sternensystems angepasst ist
		 */
		private SimpleDoubleProperty posiY = new SimpleDoubleProperty();
		
		/**
		 * Z Koardinate das auf die view eines Sternensystems angepasst ist
		 */
		private SimpleDoubleProperty posiZ = new SimpleDoubleProperty();
		
		/**
		 * der Stern der sich an diesem adapter bindet
		 */
		private Stern stern;
		
		/**
		 * der Wert der Variable entspricht eine angepasste Entfernung zum Zentrum. 
		 * Mit diesem Wert, wird die neue anepasste position berechnet
		 */
		private double entfernungZumZentrum; 
		
		/**
		 * die entfernung zum Zentrum wird um den Wert der Variabe verkleinert
		 */
		private final int VERKLEINERUNGSFAKTOR = 1000000000;
		
		/**
		 * erstellt ein Objekt vom BewegungsadapterStern
		 * @param stern
		 */
		public BewegungsadapterStern(Stern stern) 
		{
			this.stern = stern; 
			stern.refresh();
			Point3D point = new Point3D(stern.getPositionKartesisch().getX(), stern.getPositionKartesisch().getY(), stern.getPositionKartesisch().getZ());
			entfernungZumZentrum = (point.distance(0 , 0,  0) / VERKLEINERUNGSFAKTOR);
			System.out.println("Angepasste entfrenung im Sternensystem zum Zentrum:  "+entfernungZumZentrum+" ");
		}
		
		
		/**
		 * aktuallisiert die Position auf der view
		 */
		public synchronized void refresh()
		{
			double x = 0; 
			double y = 0; 
			double z = 0; 
			
			Vector<Double> positionsVector = this.stern.getPositionPolar();
			
			x = entfernungZumZentrum * Math.sin(positionsVector.get(2)) * Math.cos(positionsVector.get(1));
			y = entfernungZumZentrum * Math.sin(positionsVector.get(2)) * Math.sin(positionsVector.get(1));
			z = entfernungZumZentrum * Math.cos(positionsVector.get(2));			
			System.out.println("neue Position: X "+x+" Y "+y+" Z "+z);
			
			posiX.set(x);
			posiY.set(y);
			posiZ.set(z);
		}
	}
}
