package controller;

import global.GameTime;
import himmelskoerper.Tester;
import view.hauptmenu.StageController;
/**
 * 
 * Die Mainklasse des Spiels 
 * @author Dennis, Thomas
 *
 */
public class Main
{	
	public static GameTime time = new GameTime();
	
	public static void main (String[]args)
	{	
		Tester himmelskoerperTest = new Tester();
		
		StageController startGame = new StageController(); 
	}
}
