package gameMaker.view;

import gameMaker.view.einstellungGameObjekte.EinstellungBett;
import gameMaker.view.einstellungGameObjekte.EinstellungGameObjekt;
import gameMaker.view.einstellungGameObjekte.EinstellungMauer;
import gameMaker.view.einstellungGameObjekte.EinstellungMensch;
import gameMaker.view.einstellungGameObjekte.EinstellungSchrank;
import gameMaker.view.einstellungGameObjekte.EinstellungStuhl;
import gameMaker.view.einstellungGameObjekte.EinstellungTisch;
import gameMaker.view.einstellungGameObjekte.EinstellungTuer;
import javafx.scene.Node;
import personensicht.model.gameObjekte.Bett;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.model.gameObjekte.Mauer;
import personensicht.model.gameObjekte.Schrank;
import personensicht.model.gameObjekte.Stuhl;
import personensicht.model.gameObjekte.Tisch;
import personensicht.model.gameObjekte.Tuer;
import personensicht.model.gameObjekte.lebewesen.Mensch;

/**
 * Ermoeglicht das Auswaehlen von GameObjekten. Diese Funktion wird im GameMaker auf der linken Seite gezeigt
 * @author Dennis
 */
public class AuswahlGameObjekt {
	
	private Node node; 
	/**
	 * Rechte Anzeige, mit der Einstellungen am GameObjet unternommen werden koennen
	 */
	private EinstellungGameObjekt einstellungGameObjket; 
	
	
	AuswahlGameObjekt(GameObjektType type) 
	{
		switch (type)
		{
		case Bett:
			Bett bett = new Bett(); 
			einstellungGameObjket = new EinstellungBett(bett); 
			break; 
		case Item:
			break;
		case Mensch:
			Mensch mensch = new Mensch("DefaultName");
			einstellungGameObjket = new EinstellungMensch(mensch);
			break;
		case Schrank:
			Schrank schrank = new Schrank();
			einstellungGameObjket = new EinstellungSchrank(schrank);
			break;
		case Tuer:
			Tuer tuer = new Tuer();
			einstellungGameObjket = new EinstellungTuer(tuer); 
			break;
		case Stuhl:
			Stuhl stuhl = new Stuhl();
			einstellungGameObjket = new EinstellungStuhl(stuhl); 
			break;
		case Tisch:
			Tisch tisch = new Tisch();
			einstellungGameObjket = new EinstellungTisch(tisch); 
			break;
		case Mauer:
			Mauer mauer = new Mauer();
			einstellungGameObjket = new EinstellungMauer(mauer); 
			break;
		default:
			break;
		}
		this.node = einstellungGameObjket.getGameObjekt().ladeNodeObjekt(); 
	}
	
	public synchronized EinstellungGameObjekt getEinstellungGameObjket() 
	{
		return einstellungGameObjket;
	}

	public synchronized Node getNode() {
		return node;
	}	
}		

