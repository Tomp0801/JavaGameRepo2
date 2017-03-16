package gameMaker.controll;

public class GameMakerCrt 
{
	
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
}
