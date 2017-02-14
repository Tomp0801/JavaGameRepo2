package personensicht.crt.spielablauf;

import java.util.ArrayList;

import personensicht.crt.InventarCrt;
import personensicht.crt.viewCrt.SpieloberflaecheCrt;
import personensicht.model.aktionen.Aktion;
import personensicht.model.aktionen.AktionEssen;
import personensicht.model.aktionen.AktionInventarOeffnen;
import personensicht.model.aktionen.AktionNehmen;
import personensicht.model.aktionen.AktionSchlafen;
import personensicht.model.aktionen.AktionTrinken;
import personensicht.model.gameObjekte.Inventar;
import personensicht.model.gameObjekte.lebewesen.GespraechAktion;
import personensicht.model.spieler.Spieler;
import personensicht.view.gameObjekte.lebewesen.GespraechView;
import personensicht.view.inventar.InventarView;

public abstract class AktionsCrt 
{
	public static void doAktion(Aktion aktion)
	{
		switch (aktion.getTyp())
		{
		case Essen:
		{
			AktionEssen aktione = (AktionEssen) aktion;
			InventarCrt.getInstance().removeItemFromInventar(aktione.getItem());
			Spielcontroller.getInstance().getSpieler().changeHunger(aktione.getNaehrwert());
		}
			break; 
		case Trinken:
		{
			AktionTrinken aktione = (AktionTrinken) aktion;
			InventarCrt.getInstance().removeItemFromInventar(aktione.getItem());
			Spielcontroller.getInstance().getSpieler().changeDurst(aktione.getDurstLoescher());
		}
			break;
		case OeffneInventar:
		{
			AktionInventarOeffnen aktione = (AktionInventarOeffnen) aktion;
			oeffneInventar(aktione.getInventar());
		}
			break;
		case nehmen:
		{
			AktionNehmen aktione = (AktionNehmen) aktion;
			try 
			{
				InventarCrt.getInstance().verschiebeItem(Spielcontroller.getInstance().getSpieler().getInventar(),aktione.getItem());
			} 
			catch (Exception e) 
			{
				
			}
		} 
			break; 
		case schlafen:
		{
			AktionSchlafen aktione = (AktionSchlafen) aktion;
			Spielcontroller.getInstance().getSpieler().changeMuedigkeit(aktione.getErholung());
			Spielcontroller.getInstance().getSpieler().changeAusdauer(10);
			Spielcontroller.getInstance().getSpieler().changeHunger(-5);
			Spielcontroller.getInstance().getSpieler().changeDurst(-5);
			break;
		}
		case reden:
		{
			GespraechAktion aktione = (GespraechAktion) aktion;
			GespraechView viewGe = new GespraechView(aktione.getTexte() , aktione.getAntwortListe());
			SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(viewGe.getRoot());
			break;
		}
		default:
			break; 
		}
	}
		
	
	private static void oeffneInventar(ArrayList<Inventar> inventar)
	{
		ArrayList<Inventar> inventar2 = new ArrayList<Inventar>();
		for (int i = 0; inventar.size() > i ; i++)
		{
			inventar2.add(inventar.get(i));
		}
		inventar2.add(Spielcontroller.getInstance().getSpieler().getInventar());
		SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(new InventarView(inventar2));
	}
}
