package controller;

import java.util.ArrayList;

import global.Constants;
import global.GameTime;
import himmelskoerper.InOrbit;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;
import view.hauptmenu.SceneEnum;
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
			schwarzesLoch = new SchwarzesLoch(seed);
//			StageController.getInstance().wechselScene(SceneEnum.WELTRAUMSICHT);
			GameTime.getInstance().setZeitFaktor(Constants.ZEITFAKTOR);
			
			Sonnensystem system = new Sonnensystem((Stern) schwarzesLoch.getChild(0));
			StageController.getInstance().setScene(system.getScene());
			
			
			gameStart = true;
		}
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
