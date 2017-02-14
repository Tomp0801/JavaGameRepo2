package personensicht.model.gameObjekte.lebewesen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import personensicht.model.aktionen.Aktion;
import personensicht.model.aktionen.Aktionstyp;

/**
 *  Ein Gespraech besteht aus einem Text, der von dem Gegenueber gesagt wurde, und deren Antwortmoegligkeiten.
 *  Die Antwortmoegligkeiten verweisen auf einen Text, (die Reaktion des gegenuebers), mit wiederrum weiteren
 *  Antwortmoegligkeiten.
 *  Die Information zu diesen Texten, befinden sich ein einer Textdatei, dessen path im Konstruktor uebergeben wird
 *  
 *  Beispiel fuer den Aufbau einer Textdatei:
 *  
 *  6 #Anzahl an den Texten, die es gibt. 
 *  text beliebger länge end/2,3,4,5,6 #verweist auf die Antwortmögligkeiten
 *  text beliebger länge end/
 *  text beliebger länge end/
 *  text beliebger länge end/
 *  text beliebger länge end/
 *  text beliebger länge end/
 *  antwort end/3
 *  antwort end/
 *  antwort end/4
 *  antwort end/5
 *  antwort end/5
 *  antwort end/7
 *  antwort end/
 *  antwort end/
 *  
 * 
 * @author Dennis
 *
 */
public class GespraechAktion extends Aktion
{
	/**
	 * beschreibt den Weg zur Datei, die das Gespraech des Menschen beinhaltet
	 */
	private String path;
	private ArrayList<TextGespraech> texte = new ArrayList<TextGespraech>();
	private ArrayList<AntwortGespraech> antwortListe= new ArrayList<AntwortGespraech>();
	
	public GespraechAktion(String path)
	{
		super(Aktionstyp.reden);
		this.path = path; 
		this.setName("reden");
		
		BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(new File(path)));
	            String line = br.readLine();
	            int size = Integer.valueOf(line);
	            	//nehme die erste Zeile. Diese gibt an wieviel Textdatein es gibt
	                for (int i = 0; size > i;i++)
	                {
	                	line = br.readLine();
	                	String text = "";
	                	if (line.contains("end/"))
	                	{
		                    String t[] = line.split("end/");
		                    text = text+t[0];
		                    //verweist auf die antort
		                    int[] verweisID = null;
		                    if (t.length > 1)
		                    {
			                   String verweis[]= t[1].split(",");
			                   verweisID = new int[verweis.length];
				               for (int j = 0; verweis.length > j ; j++)
				               {
				            	   verweisID[j] = Integer.valueOf(verweis[j]);
				               }
		                    }
		                    
		                    TextGespraech textGe = new TextGespraech(i,text,verweisID);
		                	texte.add(textGe);
	                	}
	                	else
	                	{
	                		text = text+line;
	                	}
	                	
	                }  
	                int i = 0;
	            while((line = br.readLine()) != null) 
		        {
	            	String text = "";
                	if (line.contains("end/"))
                	{
	                    String t[] = line.split("end/");
	                    text = text+t[0];
	                    //verweist auf die Antwort der Antwort
	                    int[] verweisID = null;
	                    if (t.length > 1)
	                    {
		                   String verweis[]= t[1].split(",");
		                   verweisID = new int[verweis.length];
			               for (int j = 0; verweis.length > j ; j++)
			               {
			            	   verweisID[j] = Integer.valueOf(verweis[j]);
			               }
	                    }
	                    
	                    AntwortGespraech antwort = new AntwortGespraech(i,text,verweisID);
	                	antwortListe.add(antwort);
                	}
                	else
                	{
                		text = text+line;
                	}	            	
	            	i++;
	            } 
	        }
	            catch(FileNotFoundException e)
	            {
	            e.printStackTrace();
	        } catch(IOException e) 
	        {
	            e.printStackTrace();
	        } finally {
	            if(br != null) {
	                try {
	                    br.close();
	                } catch(IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	}

	public synchronized ArrayList<TextGespraech> getTexte() {
		return texte;
	}

	public synchronized ArrayList<AntwortGespraech> getAntwortListe() {
		return antwortListe;
	}
}
