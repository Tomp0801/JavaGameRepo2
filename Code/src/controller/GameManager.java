package controller;

import himmelskoerper.SchwarzesLoch;
import view.hauptmenu.SceneEnum;
import view.hauptmenu.StageController;

/**
 * Im GameManager wird der spielfluss kontrolliert
 * @author Dennis / Thomas
 *
 */
public class GameManager 
{
	
	private static GameManager instance; 
	
	private static SchwarzesLoch schwarzezLoch; 
	
	private GameManager()
	{
		this.instance = this; 
	}
	
	public static GameManager getInstance()
	{
		if (instance == null)
		{
			new GameManager();
		}
		 return instance; 
	}
	
	public void starteSpiel(int seed)
	{
		schwarzezLoch = new SchwarzesLoch(seed);
		StageController.getInstance().wechselScene(SceneEnum.WELTRAUMSICHT);
	}
	
	
	public SchwarzesLoch getSchwarzelochSystem()
	{
		return schwarzezLoch;
	}
}
