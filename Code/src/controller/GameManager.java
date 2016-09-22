package controller;

import global.Constants;
import global.GameTime;
import himmelskoerper.Planet;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;
import view.hauptmenu.StageController;
import view.weltraum.Sonnensystem;

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
		schwarzesLoch = new SchwarzesLoch(seed);
		//TODO DEMO 
		Sonnensystem system = new Sonnensystem((Stern) schwarzesLoch.getChild(0));
		Stern stern = (Stern) schwarzesLoch.getChild(0);
		Planet planet = (Planet) stern.getChild(0);	
//		Planetensystem systemPlanet = new Planetensystem(planet);
		
//		Sternensystem system = new Sternensystem(schwarzesLoch ,0 ,0 ,0);
		
		StageController.getInstance().setScene(system.getScene());
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
