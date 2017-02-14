package gameMaker.controll;

import gameMaker.view.GameMaker;
import personensicht.crt.viewCrt.StageCrt;

public class GameMakerCrt 
{
	private GameMaker instanceOfGameMaker; 
	
	private static GameMakerCrt instance;
		
	private GameMakerCrt()
	{
		instance = this;
	}
	
	
	public static GameMakerCrt getInstance()
	{
		if (instance == null)
			new GameMakerCrt();
		return instance;
	}
	
	public void openGameMaker()
	{
		instanceOfGameMaker  = new GameMaker();
		StageCrt.getInstance().setScene(instanceOfGameMaker.getScene());
	}
}
