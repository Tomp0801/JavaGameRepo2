package view.weltraum;

import map.Bereich;
import map.Karte;

public class Planetenkarte extends WeltraumSystem
{
	private Karte karte; 
	
	private KameraPlanetenkarte kamera = new KameraPlanetenkarte();
	
	protected Planetenkarte(Karte karte)
	{
		this.getSubScene().setCamera(kamera.getKamera());
		this.karte = karte; 
		initKarte(karte);
	}

	private void initKarte(Karte karte)
	{
		for(int i = 0 ; karte.getBreite() > i ; i++)
		{
			for (int j = 0; karte.getHoehe() > j ; j++ )
			{
				Bereich bereich = karte.getBereich(i, j);
				
			}
		}
		
	}
}
