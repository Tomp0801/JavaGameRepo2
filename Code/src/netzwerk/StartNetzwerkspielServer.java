package netzwerk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import view.hauptmenu.Config;

/**
 * ein Server zum aufbauen der Verbindungen zu andern Spielern und zum starten des Spiels
 * @author Dennis
 *
 */
public class StartNetzwerkspielServer implements Runnable 
{
	/**
	 * eine Liste mit allen Clients die sich verbinden
	 */
	private ArrayList<Socket> empfaengerClientList = new ArrayList<Socket>();
	
	/**
	 * eine Liste mit Clients die mit einem andern Server eines Spielers verbunden ist
	 */
	private ArrayList<Socket> senderClientList = new ArrayList<Socket>();
	
	/**
	 * eine List mit Outputstreams. Diese wird dazu verwendet um Nachrichten von einem Client zu empfangen
	 */
	private ArrayList<PrintWriter> writerList = new ArrayList<PrintWriter>();
	
	/**
	 * Gib einen neuen Clinet der sich verbindet eine id. Diese ID gibt es nur einmal.  
	 * So koennen die unterschiedlichen Clients erkannt werden
	 */
	private int idSpieler = 0;
	
//	/**
//	 * die Namen der Spieler, die sich in dem Warteraum befinden, werden in dieser ArrayList gespeichert 
//	 */
//	private ArrayList<String> namenDerSpieler = new ArrayList<String>();
	
	/**
	 * eine Liste, die den Zustand der Einstellungen repraesentieren
	 */
	private ArrayList<String[]> zustandEinstellung = new ArrayList<String[]>(); 
	
	/**
	 * die Ports die zu verfuegung stehen
	 */
	private static int port = Config.getPort(); 
	
	/**
	 * der Server
	 */
	private ServerSocket server;

	
	
	/**
	 * erstellt eine Objekt der StartNetzwerkspielServer Klasse 
	 */
	public StartNetzwerkspielServer()
	{
		//der Sever wird hier gestartet
		try 
		{
			server = new ServerSocket(Config.getPort());
			System.out.println("Server ist gestartet");		
		} 
		catch (IOException e) 
		{
			System.err.println("Server konnte nicht gestartet werden");
			e.printStackTrace();
		} 
		
		//erstelle ein Thread. dieser soll auf eingehende Verbindungen warten
		
		Thread netzwerkServerThread = new Thread(this);
		netzwerkServerThread.setDaemon(true);
		netzwerkServerThread.start();
	}
	
	
	/**
	 * sucht nach einer Verbindung
	 */
	@Override
	public void run() 
	{
		//wartet auf eingehende verbindungen und fuegt eine neuen Client der Liste hinzu
		while (true)
		{
			try
			{
				Socket newClientEmfaenger = server.accept();	
				initVerbindung(newClientEmfaenger);	
				System.out.println("Ein Spieler hat sich verbunden:  "+empfaengerClientList.get(empfaengerClientList.size()-1).getInetAddress());
			}
			catch (IOException e) 
			{
				System.err.println("Verbindung zum Server wurde unterbrochen");
				break;
			}
		}
	}
	
	
	/**
	 * der noetige Datenaustausch mit einem Neuen Spieler wird in dieser Methode erledigt
	 */
	private synchronized void initVerbindung(Socket newClientEmpfaenger) throws IOException
	{
		String[] einstellungen = {"name/" , "andererSpieler/" , "nichtBereit/" , "0/" , ""+idSpieler};
		idSpieler++;
		zustandEinstellung.add(einstellungen);
		
		empfaengerClientList.add(newClientEmpfaenger);
		port++;
		
		PrintWriter writerPort = new PrintWriter(newClientEmpfaenger.getOutputStream());
		writerPort.println(port+"/"+(idSpieler-1));
		writerPort.flush();
		
		Socket newClientSender = new Socket(newClientEmpfaenger.getInetAddress() , port);
		senderClientList.add(newClientSender );
		
		PrintWriter neuerWriter = new PrintWriter(newClientSender .getOutputStream());
		writerList.add(neuerWriter);
		
		Thread clientThread = new Thread(new ClientHandler(newClientEmpfaenger));
		clientThread.setDaemon(true);
		clientThread.start();
		
		// fuegt alle Spieler die sich im Warteraum befinden den neuen Spieler hinzu
		for (int i = 0; zustandEinstellung.size()-1 > i ; i++)
		{
			neuerWriter.println(ServerBefehle.getAddNeuerNetzwerkspieler());
			neuerWriter.flush();
			neuerWriter.println(zustandEinstellung.get(i)[0]+zustandEinstellung.get(i)[1]+zustandEinstellung.get(i)[2]+zustandEinstellung.get(i)[3]+zustandEinstellung.get(i)[4]);
			neuerWriter.flush();
			
		}	
	}
	
	
//-------------------------------ServerAnweisungen-------------------------------------------------------------//	
	/**
	 * Versendet eine Nachricht an den Clients die sich am Server angemeldet haben
	 * 
	 * @param message ist die Nachricht die zu den Spielern geschicht wird
	 */
	private synchronized void sendToAllClietChatMessage(String message)
	{
		for (int i = 0 ; writerList.size() > i ; i++)
		{
			writerList.get(i).println(ServerBefehle.getChatnachricht());
			writerList.get(i).flush();
			writerList.get(i).println(message);
			writerList.get(i).flush();
		}
	}
	
	
	/**
	 * fuegt einen neuen Spieler hinzu
	 * @param nameSpieler name gefolgt von der InetAddresse des Clients Beispiel: "Peter/143.242.231.002"
	 */
	private synchronized void addNeuenSpieler (String nameSpieler)
	{
//		namenDerSpieler.add(nameSpieler);
		String[] name = nameSpieler.split("/"); 
		
		for (int i = 0 ; zustandEinstellung.size() > i; i++)
		{
			if (zustandEinstellung.get(i)[zustandEinstellung.get(i).length-1] == name[1]); 
			{
				zustandEinstellung.get(i)[0] = name[0]+"/";
				zustandEinstellung.get(i)[4] = name[1];
			}
		}
		//fuegt den neuen Spieler in den Warteraum der andern Spieler hinzu
		for (int i = 0 ; writerList.size() > i ; i++)
		{
			writerList.get(i).println(ServerBefehle.getAddNeuerNetzwerkspieler());
			writerList.get(i).flush();
			
			String zustand = "";
			for (int r = 0; zustandEinstellung.get(i).length > r ; r++ )
			{
				zustand = zustand+zustandEinstellung.get(i)[r];
			}
			writerList.get(i).println(zustand);
			writerList.get(i).flush();
		}
	}
	
	
	/**
	 * schickt die veranderten Einstellungn zu den Clients
	 */
	private synchronized void veranderungEinstelungen(String aenderungen)
	{
		for (int i = 0 ; writerList.size() > i ; i++)
		{
			writerList.get(i).println(ServerBefehle.getEinstellungenaendern());
			writerList.get(i).flush();
			writerList.get(i).println(aenderungen);
			writerList.get(i).flush();
		}
	}
	
	/**
	 * eine Innereklasse um die Clients des Servers zu verwalten
	 * @author Dennis
	 */
	public class ClientHandler implements Runnable
	{
		/**
		 * Client der sich mit dem Hostserver verbunden hat
		 */
		private Socket clientEmpfangen;
		
		/**
		 * 
		 * @param client
		 */
		public ClientHandler(Socket client)
		{		
			this.clientEmpfangen = client; 
		}

		
		//wartet auf Anweisungen 
		@Override
		public void run() 
		{
			/**
			 * in diesem Attribut wird eine Anweisung von einem Client gespeichert, die ausgefuerht werden soll.
			 */
			String anweisung; 
			
			try 
			{
				BufferedReader reader = new BufferedReader (new InputStreamReader(this.clientEmpfangen.getInputStream()));
				
				while((anweisung = reader.readLine()) != null)
				{	
					switch (anweisung)
					{
						case "chatnachricht":
							sendToAllClietChatMessage(reader.readLine());
							break;
						case "addNeuerNetzwerkspieler" :
							addNeuenSpieler(reader.readLine());
							break;
						case "einstellungenAendern" :
							veranderungEinstelungen(reader.readLine());
							break;
					}		
				}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * schlieﬂt den Server
	 */
	public void close()
	{
		try 
		{
			server.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
