package controller;

import java.util.ArrayList;

import global.GameTime;
import himmelskoerper.InOrbit;
import himmelskoerper.SchwarzesLoch;
import view.hauptmenu.SceneEnum;
import view.hauptmenu.StageController;

/**
 * Im GameManager wird der spielfluss kontrolliert
 * @author Dennis / Thomas
 *
 */
public final class GameManager 
{
	/**
	 * instance vom GameManage
	 */
	private static GameManager instance; 
	
	/**
	 * um dieses SchwarzeLoch drehen sich die Sonnen
	 */
	private static SchwarzesLoch schwarzezLoch; 
	
	/**
	 * in diesr Liste befinden sich alle InOrbit Objecte dessen Position in einer while(true) Schleife 
	 * staendig erneuert werden solll 
	 */
	private ArrayList<InOrbit> positionsRechnerListe = new ArrayList<InOrbit>();
	
	/**
	 * is true wenn das Spiel gestartet ist
	 */
	private static Boolean gameStart = false;

	/**
	 * erstllt einen GameManager. ein Object dieser Klasse wird nur durch die Methode getInstance erstellt
	 */
	private GameManager()
	{
		instance = this; 
	}
	
	
	/**
	 * gibt eine INstance vom GameManager wieder. Gibt es noch keine, dann wird eine erstellt
	 * @return instce vom GameManager
	 */
	public static GameManager getInstance()
	{
		if (instance == null)
		{
			new GameManager();
		}
		 return instance; 
	}
	
	
	/**
	 * startet das Spiel
	 * @param seed ist eine Zahl die eine Karte zufaellig generrieren laesst 
	 */
	public void starteSpiel(int seed)
	{	
		if (gameStart == false)
		{	
			schwarzezLoch = new SchwarzesLoch(seed);
			StageController.getInstance().wechselScene(SceneEnum.WELTRAUMSICHT);
			GameTime.getInstance().setZeitFaktor(100000);
			gameStart = true;
			positionsRechner();
		}
	}
	
	
	/**
	 * @return gibt das Schwarzeloch zurueck um dennen sich alle sterne bewegen.
	 */
	public SchwarzesLoch getSchwarzelochSystem()
	{
		return schwarzezLoch;
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
	 * diese Methode rechnet in einer Dauerschleife alle Positionen von InOrbit Objekten durch die sich
	 * in der ArrayList positionsRechnerListe befinden
	 */
	private void positionsRechner()
	{
		Thread rechner = new Thread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				while(true)
				{	
					try {
						Thread.sleep(50);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					for (int i = 0; positionsRechnerListe.size() > i ; i++)
					{
						positionsRechnerListe.get(i).refresh();
					}
				}	
			}
		});		
		rechner.setDaemon(true);
		rechner.start();
	}
	
	
	/**
	 * @return gibt true zurueck wenn bereits ein Spiel gestartet hat
	 */
	public static Boolean isGameStart()
	{
		return gameStart;
	}
}
