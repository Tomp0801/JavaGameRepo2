package gameMaker.view;

import gameMaker.view.einstellungGameObjekte.EinstellungBett;
import gameMaker.view.einstellungGameObjekte.EinstellungGameObjekt;
import gameMaker.view.einstellungGameObjekte.EinstellungMauer;
import gameMaker.view.einstellungGameObjekte.EinstellungMensch;
import gameMaker.view.einstellungGameObjekte.EinstellungSchrank;
import gameMaker.view.einstellungGameObjekte.EinstellungStuhl;
import gameMaker.view.einstellungGameObjekte.EinstellungTisch;
import gameMaker.view.einstellungGameObjekte.EinstellungTuer;
import ioHandler.SerializableNew;
import javafx.scene.Node;
import personensicht.model.gameObjekte.Bett;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.model.gameObjekte.Mauer;
import personensicht.model.gameObjekte.Schrank;
import personensicht.model.gameObjekte.Stuhl;
import personensicht.model.gameObjekte.Tisch;
import personensicht.model.gameObjekte.Tuer;
import personensicht.model.gameObjekte.lebewesen.mensch.Koerper;

/**
 * Um Nods auf der Region den entsprechenden GameObjekten zuzuordnen, wird diese Klasse verwendet. 
 * @author Dennis
 */
public class GameObjektZuordnung{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node node; 
	/**
	 * Rechte Anzeige, mit der Einstellungen am GameObjet unternommen werden koennen
	 */
	private EinstellungGameObjekt einstellungGameObjket; 
	private GameObjekt gameObjekt; 
	private final GameObjektType TYPE;
	
	/**
	 * 
	 * @param type
	 */
	GameObjektZuordnung(GameObjektType type) //TODO
	{
		this.TYPE = type; 
		initEinstellungGameObjekt();
		this.node = einstellungGameObjket.getGameObjekt().ladeNodeObjekt(); 
	}
	
	/**
	 * ist das GameObjekt bereits beannt, dann muss dieser Konstuktor verwendet werden
	 * @param objekt
	 */
	GameObjektZuordnung(GameObjekt objekt) 
	{
		this.TYPE = objekt.getTyp();
		this.node = objekt.ladeNodeObjekt();
		this.gameObjekt = objekt; 
		initEinstellungGameObjekt();
	}
	
	/**
	 * erstellt eine Viewkomponente zum Einstellen des Objektes.
	 * ist noch keine Modelklasse des Objektes verfuegbar wird eine neue erstellt
	 */
	private void initEinstellungGameObjekt(){
		switch (TYPE)
		{
		case Bett:
			Bett bett;
			if (this.gameObjekt == null){
			bett = new Bett(); 
			gameObjekt = bett; 
			}
			else{
				bett = (Bett) gameObjekt;
			}
			einstellungGameObjket = new EinstellungBett(bett); 
			
			break; 
		case Item://TODO
			break;
		case Mensch:
			Koerper mensch;
			if (this.gameObjekt == null){
				mensch = new Koerper("DefaultName");
			}
			else{
				mensch = (Koerper) gameObjekt;
			}
			einstellungGameObjket = new EinstellungMensch(mensch);
			gameObjekt = mensch; 
			break;
		case Schrank:
			Schrank schrank;
			if (this.gameObjekt == null){
				schrank = new Schrank();
			}
			else{
				schrank = (Schrank) gameObjekt;
			}
			einstellungGameObjket = new EinstellungSchrank(schrank);
			gameObjekt = schrank; 
			break;
		case Tuer:
			Tuer tuer;
			if (this.gameObjekt == null){
				tuer = new Tuer();
			}
			else{
				tuer = (Tuer) gameObjekt; 
			}
			einstellungGameObjket = new EinstellungTuer(tuer); 
			gameObjekt = tuer; 
			break;
		case Stuhl:
			Stuhl stuhl;
			if (this.gameObjekt == null){
				stuhl = new Stuhl();
			}
			else{
				stuhl = (Stuhl) gameObjekt; 
			}
			einstellungGameObjket = new EinstellungStuhl(stuhl); 
			gameObjekt = stuhl;
			break;
		case Tisch:
			Tisch tisch;
			if (this.gameObjekt == null){
				tisch = new Tisch();
			}
			else{
				tisch = (Tisch) gameObjekt; 
			}
			einstellungGameObjket = new EinstellungTisch(tisch); 
			gameObjekt = tisch; 
			break;
		case Mauer:
			Mauer mauer;
			if (this.gameObjekt == null){
				mauer = new Mauer();
				gameObjekt = mauer; 
			}
			else{
				mauer = (Mauer) this.gameObjekt; 
			}
			einstellungGameObjket = new EinstellungMauer(mauer); 
			break;
		default:
			break;
		}
	}
	
	public synchronized EinstellungGameObjekt getEinstellungGameObjket() 
	{
		return einstellungGameObjket;
	}

	public synchronized Node getNode() {
		return node;
	}

	public synchronized GameObjektType getTYPE() {
		return TYPE;
	}

	public synchronized GameObjekt getGameObjekt() {
		return gameObjekt;
	}
}		

