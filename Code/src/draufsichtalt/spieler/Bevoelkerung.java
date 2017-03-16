package draufsicht.spieler;

import java.util.ArrayList;

/**
 * 
 * @author Demix
 *
 */
public class Bevoelkerung 
{
	/**
	 * Die Loyalität hat eine Skaler von -100 bis 100%. Sie Skaler steigt durch: Bonusgüter, Krieg gegen 
	 * einen Spieler mit schlechten Diplomatischen Beziheungen, Wohlstand, Erfolgreiche 
	 * Kriege, Wenn ein Krieg zu verlieren droht. Propaganda und weiteres  
	 */
	private int loyalitaet; 
	
	/**
	 * Eine Liste der Rassen in einer Bevoelkerung
	 */
	private ArrayList<Rasse> rassenListe = new ArrayList<Rasse>();
	
	/**
	 * in dieser Liste befinden sich alle verhaehltnisse zu bekannten Civilisationen die endeckt wurden.
	 */
	private ArrayList<DiplomatieBevoelkerung> diplomatischeEinstellung = new ArrayList<DiplomatieBevoelkerung>();
	
	/**
	 * erstellt eine neue Bevoelkerung 
	 */
	public Bevoelkerung(ArrayList<Rasse> dieSiedler)
	{
		this.rassenListe = dieSiedler;
	}
}
