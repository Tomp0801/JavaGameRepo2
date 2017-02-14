package draufsicht.netzwerk;

/**
 * in dieser Klasse werden ServerBefehle geepichert, auf die ein Server reagieren kann
 * 
 * @author Dennis
 *
 */
public class ServerBefehle 
{
	/**
	 * versendet eine Chatnachricht
	 */
	private final static String chatnachricht = "chatnachricht";
	
	/**
	 * fuegt einen neuen Netzwerkspieler hinzu
	 */
	private final static String addNeuerNetzwerkspieler = "addNeuerNetzwerkspieler";
	
	/**
	 * uebergibt den neuen Spieler die Namen des Clients der gefragt wurde
	 */
	private final static String getNameFromClient = "getNameFromClient";
	
	/**
	 * sendt den Namen zum Server
	 */
	private final static String sendeName = "sendeName";
	
	/**
	 * wird verwendt, wenn die Einstllungen von einem Cliet vreaendert wurden. 
	 */
	private final static String einstellungenAendern = "einstellungenAendern";

	private ServerBefehle(){};
	
	public final static String getChatnachricht()
	{
		return chatnachricht;
	}
	
	public final static String getAddNeuerNetzwerkspieler()
	{
		return addNeuerNetzwerkspieler;
	}
	
	public static String getGetnamefromclient()
	{
		return getNameFromClient;
	}
	
	public static String getSendename()
	{
		return sendeName;
	}
	
	public static String getEinstellungenaendern() {
		return einstellungenAendern;
	}
}
