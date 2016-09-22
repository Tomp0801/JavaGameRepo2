package view.weltraum;

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
		
		
	}
}
