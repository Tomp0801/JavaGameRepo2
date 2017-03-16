package personensicht.view.gameObjekte;

import javafx.scene.Node;

/**
 * Ein Item ist etwas, was ein Spieler aufnehmen und im Inventar aufbewahren kann. 
 * Allgemein gesagt, bedeutet das Interface nur, dass sich das GameObjekt zusaetzlich noch in einen Inventar 
 * aufhalten kann. 
 * Dazu zaehlt zum Beispiel ein Handy, Essen, Trinken ect.
 * 
 * Ein Item kann im Inventar ein ander Node haben als auf einer Region, deswegen wird zusaetzlich noch eine weiter
 * Methode zum laden von Items benoetigt
 * @author Dennis
 *
 */
public interface Item {
	
	/**
	 * @return eine 2D Node um das Item im Inventar dazustellen
	 */
	public abstract Node getItemNode();
}
