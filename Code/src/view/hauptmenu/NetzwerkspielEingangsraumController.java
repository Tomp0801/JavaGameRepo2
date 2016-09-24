package view.hauptmenu;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.StageController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import netzwerk.ServerBefehle;
import netzwerk.StartNetzwerkspielServer;

/**
 * Eine GUI zum starten eines Netzwerkspieles.
 * 
 * @author Dennis
 *
 */
public class NetzwerkspielEingangsraumController implements Initializable
{
	
	//------------Buttons--------------------------------------------------------------------//
	/**
	 * wird dieser Button betaetigt, dann wird ein neues mehrspieler Spiel erstellt
	 */
	@FXML
	private Button spielErstellenButton;
	
	/**
	 * ein Button zum laden eines alten Spieles
	 */
	@FXML
	private Button spielLadenButton;
	
	/**
	 * ein Button um ein Spiel beizutreten
	 */
	@FXML
	private Button spielBeitretenButton;
	
	/**
	 * ein Button um zum Hauptmenu zureueck zu kehren
	 */
	@FXML
	private Button zurueckButton;
	
	/**
	 * ein Button um ein neu erstelltes Spiel zu starten
	 */
	@FXML 
	private Button starteSpielButton;
	
	/**
	 * ein Button um eine verbindung mit zu einem Server aufzubauen. 
	 * Dazu wird eine im TextFeld eingegebene IP Adresse verwendet
	 */
	@FXML
	private Button connect;
	
	/**
	 * ein Button zum senden einer Chatnachricht
	 */
	@FXML
	private Button sendeText;
	
	//---------------------------Text-Felder-------------------------------------------------------------------//
	/**
	 * hier kann der Spieler eínen Namen eingeben, den er im Spiel hat.
	 */
	@FXML
	private TextField spielerName;
	
	/**
	 * Ein Feld zum eingeben einer IP Addresse
	 */
	@FXML
	private TextField addresseIP;
	
	/**
	 * ein Feld indem ein Spieler eine Chatnachrcht verfassen kann
	 */
	@FXML
	private TextField nachricht; 
	
	//-----------------------------VBox------------------------------------------------------------------------------//
	
	
	/**
	 * In dieser Box sind alle Spieler aufgelistet, die sich einen neuen Spiel begetreten sind
	 */
	@FXML
	private VBox spielerImSpiel;
	
	/**
	 * Hier befinden sich Nachrichten der Spieler innerhab eines Warteraum eines neuen Spieles. 
	 */
	@FXML 
	private VBox chat;
	
	/**
	 * In dieser Box befinden sich eingabe Moegligkeiten fuer eine IP-Adresse 
	 */
	@FXML 
	private VBox spielBeitretenBox; 
	
	//-------------------------HBox-----------------------------------------//
	
	/**
	 * obere Menuleiste 
	 */
	@FXML 
	private HBox menuLeiste;
	
	//------------------------------------------------------------------------------------------------------//
	
	/**
	 * Informationen zur eingabe einer IP Adresse
	 */
	@FXML 
	private Label infoIPAddresse; 
		
	/**
	 * scrollPane in dem sich Nachrichten des Chats befinden
	 */
	@FXML
	private ScrollPane scrollPaneChat; 
		
	/**
	 * in diesem ScrolPane befindet sich eine Box mit einer Liste von den Spielern die das Spiel beigetreten sind.
	 * dies wird nur verwendet um die Liste sichtbar oder unsichtbar zu machen. 
	 */
	@FXML
	private ScrollPane spielerImSpielScrollPane;
	
	/**
	 * Box in der sich der Chat befindet. Wird benötigt um die Sichtbarkeit zu vereandern
	 */
	@FXML
	private VBox chatBox;
	
	/**
	 * Dieser Client wird angelegt, wenn ein Spieler eine Spiel erstellt oder beitritt
	 */
	private SpielerClient client;
	
	/**
	 * Repraesentiert den Zustand in dem ich die Scene fuer diesesm Controller befinden
	 */
	private NetzwerkSceneZustand zustand;
	
	/**
	 * Eine Liste mit den Spielern die sich ein einem Warteraum befinden
	 */
	private ArrayList<WarteraumEinstellungenSpieler> warteraumListe = new ArrayList<WarteraumEinstellungenSpieler>(); 	
	
	/**
	 * die ID, die der Spieler hat, um seine Einstellungen wiederzu erkennen
	 */
	private int eigenerSpielerID;
	
	/**
	 * wenn eine Spieler einen Spiel erstellt bekommt er einen Server.
	 */
	private StartNetzwerkspielServer server;
	/**
	 * initialisiere den FXMLNetzwerkController
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{}
	
	/**
	 * behandelt Events der Buttons
	 */
	@FXML
	private void actionButton(ActionEvent e)
	{
		if (e.getSource() == spielErstellenButton)
		{
			//erstellt ein Server
			server = new StartNetzwerkspielServer();
			//ein Client wird erstellt

				try 
				{
					erstelleClient(InetAddress.getLocalHost().getHostAddress());
				} 
				catch (UnknownHostException e1) 
				{
					
				}
				catch (IOException e1) 
				{
					System.err.println("Fehler beim erstellen des Clients vom Host");
					e1.printStackTrace();
				}
				neuerZustand(NetzwerkSceneZustand.IM_WARTERAUM_EINES_SPIELS);
		
		}
		
		else if (e.getSource() == spielLadenButton)
		{
			System.out.println("die Ladefunktion ist noch nicht verfügbar");
		}
		
		else if (e.getSource() == spielBeitretenButton)
		{
			neuerZustand(NetzwerkSceneZustand.MIT_SPIEL_VERBINDEN);
		}
		else if (e.getSource() == connect)
		{
			try 
			{
//TODO			erstelleClient(addresseIP.getText());
				erstelleClient("localhost"); // fuer Testzwecke
				neuerZustand(NetzwerkSceneZustand.IM_WARTERAUM_EINES_SPIELS);
			} 
			catch (IOException e1)
			{
				System.err.println("Die Verbindung zum Server konnte nicht aufgebaut werden");
				neuerZustand(NetzwerkSceneZustand.MIT_SPIEL_VERBINDEN);
			}
		
		}
		else if(e.getSource() == sendeText)
		{
			client.sendeAnweisungZumServer(ServerBefehle.getChatnachricht() ,spielerName.getText()+":  "+nachricht.getText());
			nachricht.clear();
		}
		else if (e.getSource() == starteSpielButton)
		{
			System.out.println("Funktion starte Spiel noch nicht verfügbar");
		}
		else if (e.getSource() == zurueckButton)
		{
			switch (zustand) 
			{
				case START:
					StageController.getInstance().setScene(new FXMLLoader(getClass().getResource("fxml/Hauptmenu.fxml")));
				break;

				case IM_WARTERAUM_EINES_SPIELS:
					neuerZustand(NetzwerkSceneZustand.START);
				break;
				case WIRD_VERBUNDEN:;
				case MIT_SPIEL_VERBINDEN:				
					neuerZustand(NetzwerkSceneZustand.START);
					break;
			}
		}
	}
	
	/**
	 * Diese Methode verwaltet die Sichtbarkeit des Buttons andere Noods in abhaengigkeit 
	 * des Zustandes. Ein Zustand steht fuer die Sichtbarkeit von Buttons. 
	 * @param zustand 
	 */
	private void neuerZustand(NetzwerkSceneZustand zustand)
	{
		this.zustand = zustand;
		
		switch (zustand) 
		{
			case START:
			spielerImSpielScrollPane.setVisible(false);
			chatBox.setVisible(false);
			menuLeiste.setVisible(true);
			spielBeitretenBox.setVisible(false);
			zurueckButton.setText("zurück zum Hauptmenü");
			client.close();
			if (server != null)
				server.close();	
			warteraumListe.clear();
			break;

			case IM_WARTERAUM_EINES_SPIELS:
				spielerImSpielScrollPane.setVisible(true);
				chatBox.setVisible(true);
				menuLeiste.setVisible(false);
				spielBeitretenBox.setVisible(false);
				zurueckButton.setText("Spiel austreten");
			break;
			
			case MIT_SPIEL_VERBINDEN:
				spielBeitretenBox.setVisible(true);
				menuLeiste.setVisible(false);
				zurueckButton.setText("Abbrechen");
				connect.setDisable(false);
				addresseIP.setDisable(false);
				break;
			case WIRD_VERBUNDEN:
				connect.setDisable(true);
				addresseIP.setDisable(true);
				break;
		}
	}
	
	
	/**
	 * Spieler verscuht sich mit einem Server zu verbinden
	 */
	private void erstelleClient(String addresse) throws IOException
	{
		try 
		{
			if (InetAddress.getByName(addresse).isReachable(5000))
			{
				client = new SpielerClient(InetAddress.getByName(addresse));
				spielBeitretenBox.setVisible(false);			
				spielerImSpielScrollPane.setVisible(true);
				chatBox.setVisible(true);
			}
			else
			{
				throw new IOException();
			}
		} 
		catch (UnknownHostException e1) 
		{
			System.err.println("Es konnte keine Verbindung zum Host hergestellt werden");
			e1.printStackTrace();
		} 
	}
	
	
	/**
	 * fuegt einen neuen Spieler in den Warteraum hinzu
	 */
	public void addNeuenSpieler(String ankommendeAnweisung)
	{	
		NetzwerkspielEingangsraumController instance = this; 
		
		Platform.runLater(new Runnable()
		{
			//die erste Anweisug mit einem "/" getrennt, ist der Name
	        @Override
	        public void run() 
	        {
	        	String[] anweisungen = ankommendeAnweisung.split("/") ;
	        	WarteraumEinstellungenSpieler spieler = null;
	        	
	        	spieler = new WarteraumEinstellungenSpieler(anweisungen[0], instance);
	        	
	        	if (anweisungen[1] == "andererSpieler")
	        	{
	        		spieler.setAndererSpieler();
	        	}
	    		
	    		if (anweisungen[2] == "bereit")
	    		{
	    			spieler.setBereit(true);
	    		}
	    		else 
	    		{
	    			spieler.setBereit(false);
	    		}
	
	    		//Die Zahl des geweahlten Teams ("0,1,2,3,4,5")...
	    		spieler.selecedTeam(Integer.valueOf(anweisungen[3]));

	    		spieler.setSpielerID(Integer.valueOf(anweisungen[4]));

	    		
	    		warteraumListe.add(spieler);
	    		spielerImSpiel.getChildren().add(spieler.getRoot());
	        }
	      });
	}
	
	
	/**
	 * Zeigt neue Einstellungen eines Andern Spielers an.
	 * @param neueEinstellungen Beispiel ("192.104.03.111/bereit/4") ("Addrese des Spielers / Spieler ist spielbereit / Team ")
	 */
	private void veraendereZustandEinstellungenSpieler(String neueEinstellungen)
	{	
		Platform.runLater(new Runnable()
		{
			//die erste Anweisug mit einem "/" getrennt, ist der Name
		    @Override
		    public void run() 
		    {
		    	String[] anweisung = neueEinstellungen.split("/");
		    	
		    	for (int i = 0 ; warteraumListe.size() > i ; i++)
		    	{		
		    		if (Integer.valueOf(anweisung[0]) == warteraumListe.get(i).getSpielerID())
		    		{	  			
		    			if (anweisung[1].equals("bereit"))
		    			{
		    				warteraumListe.get(i).setBereit(true);
		    			}
		    			else
		    			{
		    				warteraumListe.get(i).setBereit(false);
		    			}
		    			warteraumListe.get(i).selecedTeam(Integer.valueOf(anweisung[2]));
		    			break;
		    		}
		    	}
		    }
		});	
	}
	

	/**
	 * fuegt eine Nachricht im Chat ein
	 * @param nachricht
	 */
	public void setChatNachricht(String nachricht)
	{
		Platform.runLater(new Runnable() 
		{
	        @Override
	        public void run() 
	        {
	    		chat.getChildren().add(new Label(nachricht));
	        }
	      });
	}
	
	
	/**
	 * 
	 * @param bereit
	 */
	public void veraendereZustand()
	{
		Platform.runLater(new Runnable() 
		{
	        @Override
	        public void run() 
	        {
	        	String anweisung  = "";
	        	WarteraumEinstellungenSpieler eigenerSpieler = null;
	        	
	        	for (int i = 0; warteraumListe.size() > i ; i++)
	        	{
	        		if (eigenerSpielerID ==  warteraumListe.get(i).getSpielerID())
	        		{
	        			anweisung =String.valueOf(warteraumListe.get(i).getSpielerID());
	        			eigenerSpieler = warteraumListe.get(i);
	        		}
	        	}
	
	    		if (eigenerSpieler.isBereit() == true)
	    		{
	    			anweisung = anweisung+"/bereit/"+eigenerSpieler.getSelecedTeam();
	    		}
	    		else
	    		{
	    			anweisung = anweisung+"/nichtbereit/"+eigenerSpieler.getSelecedTeam();
	    		}
	    		client.sendeAnweisungZumServer(ServerBefehle.getEinstellungenaendern() , anweisung);
	        }
	      });
	}

	
	/**
	 * Dieser Client wird angelegt, wenn ein Spieler eine Spiel erstellt oder beitritt
	 * @author Dennis
	 *
	 */
	private class SpielerClient implements Runnable
	{
		/**
		 * Client des Spielers. 
		 * Der Client ist mit dem Host des Spiels verbunden und wird zum senden von 
		 * Anweisungen und Informationen verwendet
		 */
		private Socket clientSenden; 
		
		/**
		 * Diser Client ist der Client vom SpielServer. Von diesem bekommt der Client des Spielers Anweisungen
		 */
		private Socket clientEmfaenger; 
		
		
		/**
		 * DeamonThread der Befehle von Server verarbeitet
		 */
		private Thread clientThread;
		
		/**
		 * sendet Informationen zum Serever
		 */
		private PrintWriter writer;
		
		/**
		 * das ist eine Server vom Client. Dieser fuehrt befehle vom Hauptservre aus.
		 */
		private ServerSocket serverEmpfaenger;
		
		
		/**
		 * erstellt ein neuen Spielerclent
		 * @param address
		 */
		public SpielerClient(InetAddress address)
		{
			try 
			{
				this.clientSenden = new Socket(address , Config.getPort());		
				String[] informationenVomServer = (new BufferedReader(new InputStreamReader(this.clientSenden.getInputStream())).readLine().split("/"));
				serverEmpfaenger = new ServerSocket(Integer.valueOf(informationenVomServer[0]));
				eigenerSpielerID = Integer.valueOf(informationenVomServer[1]); 
				this.clientEmfaenger = serverEmpfaenger.accept();
				initVerbindung(address);
				
				System.out.println("Verbindung mit dem Server war erfolgreich");
				
			}
				catch (IOException e) 
			{
				System.err.println("Es konnte keine Verbindung zum Server hergestellt werden");
				e.printStackTrace();
			} 
		}
		
		
		/**
		 * hier werden Daten ausgetauscht um das eintreten in einem Warteraum zu ermoeglichen
		 */
		private void initVerbindung(InetAddress address) throws IOException
		{	
			clientThread = new Thread(this);
			clientThread.setDaemon(true);
			clientThread.start();
			
			writer = new PrintWriter(clientSenden.getOutputStream());
			
			writer.println(ServerBefehle.getAddNeuerNetzwerkspieler());
			writer.flush();
			writer.println(spielerName.getText()+"/"+eigenerSpielerID);
			writer.flush();
		}
		
		
		/**
		 * schließt den Client
		 */
		private void close()
		{
			try 
			{
				clientThread.interrupt();
				clientSenden.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
		/**
		 * empaengt Befehle vom Server
		 */
		@Override
		public void run() 
		{
			String befehl;
			
			try 
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(this.clientEmfaenger.getInputStream()));
				
				while ((befehl = reader.readLine()) != null)
				{
					switch (befehl) 
					{
					case "chatnachricht":
						setChatNachricht(reader.readLine());
						break;
					case "addNeuerNetzwerkspieler" :
						addNeuenSpieler(reader.readLine());
						break;	
					case "getNameFromClient":
						writer.println(ServerBefehle.getSendename());
						writer.flush();
						writer.println(spielerName.getText());
						writer.flush();
						break;
					case "einstellungenAendern":
						veraendereZustandEinstellungenSpieler(reader.readLine());
						break;

					default:
						System.err.println("Ein unbekanner Befehl wurde uebermittelt");
						break;
					}
					
				}
			} 
			catch (IOException e) 
			{
					System.err.println("Die Verbindung wurde unterbrochen");
			}	
		}	
		
		
		/**
		 * sende Nachricht zum Server
		 * @param nachricht
		 */
		public void sendeAnweisungZumServer(String artDerAnweisung, String nachricht)
		{
		
			try 
			{
				PrintWriter writer = new PrintWriter(clientSenden.getOutputStream());
				writer.println(artDerAnweisung);
				writer.flush();
				
				switch (artDerAnweisung)
				{
					case "chatnachricht":
						writer.println(nachricht);	
					break;
					case "einstellungenAendern":
						writer.println(nachricht);
					break;	
				}
				writer.flush();
			}
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		}
	}
}
