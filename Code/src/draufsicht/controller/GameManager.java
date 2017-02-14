package draufsicht.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import draufsicht.global.Constants;
import draufsicht.global.GameTime;
import draufsicht.himmelskoerper.FestPlanet;
import draufsicht.himmelskoerper.Planet;
import draufsicht.himmelskoerper.SchwarzesLoch;
import draufsicht.himmelskoerper.Stern;
import draufsicht.view.aufbaumodus.PlanetenkarteBereiche;
import draufsicht.view.aufbaumodus.Planetensystem;
import draufsicht.view.aufbaumodus.Sonnensystem;
import draufsicht.view.aufbaumodus.Sternensystem;
import draufsicht.view.hauptmenu.Ladebildschirm;

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

	private static SchwarzesLoch schwarzesLoch; 
	
	
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
			GameTime.getInstance().setZeitFaktor(Constants.ZEITFAKTOR);
			startDemoSpiel(seed);
			gameStart = true;
		}
	}
	
	
	/**
	 * startet ein Spiel. Diese Methode wird nur zum Testen von funktionen verwednet und hat keine bedeutung 
	 * fuer das speater Spiel
	 */
	private void startDemoSpiel(int seed)
	{
		Ladebildschirm.getInstance().oeffneLadebildschrim();
		
		schwarzesLoch = new SchwarzesLoch(seed);
		//TODO DEMO 
		Sternensystem sternsystem = new Sternensystem(schwarzesLoch);
		Sonnensystem system = new Sonnensystem((Stern) schwarzesLoch.getChild(0));
		Stern stern = (Stern) schwarzesLoch.getChild(0);
		
		StageController.getInstance().setScene(sternsystem.getScene());
		Ladebildschirm.getInstance().closeRotationThread();
		
//		Planet planet = (Planet) stern.getChild(0);	
		
//		for (int i = 0 ; stern.getChildren().size() > i ; i++)
//		{
//			if (stern.getChild(i).getClass() == FestPlanet.class)
//			{
//				
//				
//				FestPlanet f = (FestPlanet) stern.getChild(i);
//				
//				Planetensystem system2 = new Planetensystem(f);
//				
//				PlanetenkarteBereiche karte = new PlanetenkarteBereiche(f.getKarte());
//				
////				Planetensystem systemPlanet = new Planetensystem(planet);
//				
////				Sternensystem system = new Sternensystem(schwarzesLoch ,0 ,0 ,0);
//				
//				StageController.getInstance().setScene(system2.getScene());
//				
//				break;
//			}
//		}
	}
	
	
	
	
	/**
	 * @return gibt das Schwarzeloch zurueck um dennen sich alle sterne bewegen.
	 */
	public SchwarzesLoch getSchwarzesLochSystem()
	{
		return schwarzesLoch;
	}
	
	
	
	
	
	/**
	 * @return gibt true zurueck wenn bereits ein Spiel gestartet hat
	 */
	public static Boolean isGameStart()
	{
		return gameStart;
	}
}
