package view.hauptmenu.fxml;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;

/**
 * Hier finden sich seamtliche Einstelungsmoegligkeiten fuer ein Spieler
 * 
 * @author Dennis
 */
public class WarteraumEinstellungenSpieler
{
	/**
	 * Controller der Noodes
	 */
	private WarteraumSpielereinstellungenController controller;

	/**
	 * In dieser Liste werden alle erstellten Objekte der Klasse drin gespeichert. Dies ist notwendig 
	 * um eine korrekte anzahl von Teams zu ermoeglichen 
	 */
	private static ArrayList<WarteraumEinstellungenSpieler> listeWarteraum = new ArrayList<WarteraumEinstellungenSpieler>();  
	
	/**
	 * FXMLLoader zum laden von Informationen
	 */
	private FXMLLoader loader;
	
	/**
	 * Eine Box mit der Auswahl an Teams 
	 */
	private ChoiceBox<String> auswahlTeam;
	
	/**
	 * addresse die zu diesem Spieler gehoert
	 */
	private int spielerID;
	
	/**
	 * erstellt ein Objekt dier Klasse
	 * @param name
	 * @param NetzwerkspielEingangsraumController muss mit uebergeben werden um eine benachrichtung ueber veraenderungen zu erhalten
	 */
	protected WarteraumEinstellungenSpieler(String name, NetzwerkspielEingangsraumController observer) 
	{
		loader = new  FXMLLoader(getClass().getResource("WarteraumEinstellungenSpielerFXML.fxml"));
		try 
		{
			loader.load();
			controller = loader.getController();
		} 
		catch (IOException e) 
		{	
			e.printStackTrace();
		}
		controller.setName(name);
		
		listeWarteraum.add(this);
			
		//-----------------------------Team-Auswahl-------------------------------------
		this.auswahlTeam = controller.getAuswahlTeamBox();
		ArrayList<String> teamListe = new ArrayList<String>();
		for (int i = 0 ; listeWarteraum.size() > i ; i++)
		{
			teamListe.add("Team "+i);
		}
		this.auswahlTeam.getItems().addAll(teamListe);
		
		for (int i = 0 ; listeWarteraum.size() > i ; i++)
		{
			listeWarteraum.get(i).addNewTeam();
		}
		//------------------------------------------------------------------------------

		controller.addObserver(observer);
	}
	
	
	/**
	 * fuegt ein neue weiteres Team hinzu
	 */
	private void addNewTeam()
	{
		if (listeWarteraum.size() > controller.getAuswahlTeamBox().getItems().size())
			controller.getAuswahlTeamBox().getItems().add("Team "+listeWarteraum.size());
	}

	
	/**
	 * @return Parent von der FXML Datei
	 */
	protected Parent getRoot()
	{
		return loader.getRoot();
	}
	
	/**
	 * Gehoert dise Einstellungen nicht den eigenen Spieler, so wird die Moeglichkeit verweigert die 
	 * Einstellungen zu aendern. 
	 */
	protected void setAndererSpieler()
	{
		controller.setAndererSpieler();
	}
	
	/**
	 * makiert das Team, das der Spieler ausgeweahlt hat
	 */
	protected void selecedTeam(int team)
	{
		controller.selecedTeam(team);
	}

	
	/**
	 * @return gibt das ausgewaehlte Team zurueck
	 */
	protected int getSelecedTeam()
	{
		return controller.getSelecedTeam();
	}
	
	
	/**
	 * @param isBereit setzt den Bereit Button auf true oder false
	 */
	protected void setBereit(boolean isBereit)
	{
		controller.setBereit(isBereit);
	}
	
	
	protected Boolean isBereit()
	{
		return controller.isBereit();
	}
	
	/**
	 * die Addresse, die zum Spieler dieser EInstellungen gehoert.
	 * @param isBereit
	 */
	protected void setSpielerID(int spielerID)
	{
		this.spielerID = spielerID;
	}	
	
	
	/**
	 * @return die ID des Spielers
	 */
	protected int getSpielerID()
	{
		return this.spielerID;
	}
}
