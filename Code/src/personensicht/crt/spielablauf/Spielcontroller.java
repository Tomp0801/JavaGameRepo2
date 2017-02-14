package personensicht.crt.spielablauf;

import personensicht.crt.viewCrt.SpieloberflaecheCrt;
import personensicht.crt.viewCrt.StageCrt;
import personensicht.model.spieler.Spieler;
import personensicht.model.welt.Spielzustand;
import personensicht.model.welt.map.Region;
import personensicht.view.SpielerfigurErstellenScene;
import spielgenerator.SpielweltGenerator;

public class Spielcontroller 
{
	private static Spielcontroller instance;
	private ZeitCrt zeitcontroller; 
	private Spieler spieler;
	

	private Spielcontroller()
	{
		instance = this; 
	}
	
	/**
	 * startet das Spiel
	 * @param spieler
	 */
	public void spielStarten(Spieler spieler)
	{
		this.spieler = spieler;
		Region startOrt = SpielweltGenerator.generriereDemoWelt();
		Spielzustand.setOrt(startOrt);
		SpieloberflaecheCrt.getInstance().startView(startOrt);
		zeitcontroller = new ZeitCrt(spieler);
		zeitcontroller.startZeit();
	}
	
	
	/**
	 * beeendet das Spiel und geht zurueck ins Startmenu
	 */
	public void spielBeenden()
	{
			StageCrt.getInstance().setScene(new SpielerfigurErstellenScene().getScene());	
	}
	
	public void spielVerloren()
	{
		zeitcontroller.beenden(); 
	}
	
	
	public static Spielcontroller getInstance()
	{
		if(instance == null)
		{
			new Spielcontroller();
		}
		return instance;
	}
	
	public synchronized Spieler getSpieler() {
		return spieler;
	}
}
