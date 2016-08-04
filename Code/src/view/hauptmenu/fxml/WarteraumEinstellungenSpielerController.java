package view.hauptmenu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.FlowPane;

public class WarteraumEinstellungenSpielerController implements Initializable
{
	/**
	 * Name des Spielers
	 */
	@FXML
	private Label nameSpieler;
	
	/**
	 * Teams die ein Spieler auswaehlen kann
	 */
	@FXML
	private ChoiceBox<String> auswahlTeam;
	
	/**
	 * wenn der Button beteatigt ist, dann ist der Spieler bereit
	 */
	@FXML
	private CheckBox bereit;
	
	/**
	 * wird benotigt um die Einstellungsmoegligkeiten zu deaktiviern
	 */
	@FXML
	private FlowPane pane; 
	
	/**
	 * wird informietr, wenn sich an den Enistellungenwas geaendert hat.
	 */
	private NetzwerkspielEingangsraumController spielerEinstellungenObserver;
	
	/**
	 * initialiesert den Controller
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{}
	
	
	/**
	 * setze Name des Spielers
	 * @param name
	 */
	protected void setName(String name)
	{
		nameSpieler.setText(name);
	}
	
	
	/**
	 * 
	 * @return die Box fuer die Auswahl der Teams
	 */
	protected ChoiceBox<String> getAuswahlTeamBox() 
	{
		return auswahlTeam;
	}
	
	/**
	 * Gehoert dise Einstellungen nicht den eigenen Spieler, so wird die Moeglichkeit verweigert die 
	 * Einstellungen zu aendern. 
	 */
	protected void setAndererSpieler()
	{
		pane.setMouseTransparent(false);
	}
	
	
	/**
	 * makiert das Team, das der Spieler ausgeweahlt hat
	 */
	protected void selecedTeam(int team)
	{
		if (auswahlTeam.getItems().size() > team)		
			auswahlTeam.getSelectionModel().select(team);
	}
	
	/**
	 * @param isBereit setzt den Bereit Button auf true oder false
	 */
	protected void setBereit(boolean isBereit)
	{
		bereit.setSelected(isBereit);
	}

	
	/**
	 * fuegt einen Observer hinzu,der Informiert wird, wenn eine aenderung statfindet
	 */
	protected void addObserver(NetzwerkspielEingangsraumController spielerEinstellungen)
	{
		this.spielerEinstellungenObserver = spielerEinstellungen; 
	}
	
	
	@FXML
	private void actionHandlerButton(ActionEvent e)
	{
		this.spielerEinstellungenObserver.veraendereZustand();
	}
	
	
	@FXML
	private void actionHandlerChoiceBox(DragEvent e)
	{
		this.spielerEinstellungenObserver.veraendereZustand();
	}
	
	
	/**
	 * 
	 * @return gibt das mackierte Team zurueck
	 */
	protected int  getSelecedTeam()
	{
		return auswahlTeam.getSelectionModel().getSelectedIndex();
	}
	
	protected boolean isBereit()
	{
		return bereit.isSelected();
	}
}
