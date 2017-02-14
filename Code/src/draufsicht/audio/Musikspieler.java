package draufsicht.audio;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import draufsicht.speicherverwaltung.StandardFiles;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Ein Musikspier. Dieser spielt Musik im Spiel ab
 * 
 * @author Dennis
 */
public class Musikspieler
{
	/**
	 * Eine Liste von Strings, die einen Pfade einer Audiodatei repräsentieren
	 * Diese Liste beinhaltet Hindergrundsmusik aus dem Ordner Musik/Hintergrund
	 */
	private ArrayList<Media> hintergrundmusikListe; 
	
	
	/**
	 * Eine Liste von Strings, die einen Pfade einer Audiodatei repräsentieren
	 * Diese Liste beinhaltet dramatische kampfgrundsmusik aus dem Ordner Musik/Kampf
	 */
	private ArrayList<Media> kampfmusikListe;
	
	/**
	 * Die  aktuell verwendete Musikliste
	 */
	private ArrayList<Media> aktuelleMusikListe;
	
	/**
	 * damit sich die Lieder nicht steandig wiederholen werden die Nummern der gespielten Lieder 
	 * in diesem Array gespeichert
	 */
	private ArrayList<Integer> abgespielteMusik = new ArrayList<Integer>(); 
	
	/**
	 * Spielt die Musik ab
	 */
	private MediaPlayer mediaPlayer; 
	
	/* Im Spiel gibt es nur einen Musikspieler.
	 Diese Instance wird benätigt um sicherzustellen, das es nur einen Musikspieler geben kann
	 */
	/**
	 * instnace des Musikspielers
	 */
	private static Musikspieler instance; 
	
	
	/**
	 * erstelle eine Objekt vom Musikspieler
	 */
	private Musikspieler()
	{
		instance = this; 
		
		hintergrundmusikListe = erstelleMusiklisten(StandardFiles.getMusikHintergrund());
		kampfmusikListe = erstelleMusiklisten(StandardFiles.getMusikKampf());
		
		aktuelleMusikListe = hintergrundmusikListe; 
		
		wechselMusik();
	}
	
	
	/**
	 * erstellt eine Musikliste aus den Audiodatein in dem uebergebenen file. 
	 * 
	 * @param musikListe
	 * @param file
	 */
	private static ArrayList<Media> erstelleMusiklisten(File file)
	{
		File[] fileMusikListe = file.listFiles();
		
		ArrayList<Media> musikListe = new ArrayList<Media>(); 
		
		for (int i = 0 ; fileMusikListe.length > i; i++)
		{
			musikListe.add(new Media(fileMusikListe[i].toURI().toString()));
		}
		return musikListe; 
	}

	
	/**
	 * gibt die Instnace des Musikspielers zurück. Falls keine Vorhanden ist, wird eine erstellt
	 * @return die Instance de Musikspielers 
	 */
	public static Musikspieler getInstance()
	{
		if (instance == null)
		{
			new Musikspieler();
		}
		return instance; 
	}
	
	
	/**
	 * spielt die Musik ab
	 */
	public void starteMusik()
	{
		mediaPlayer.setAutoPlay(true);
	}
	
	
	private void wechselMusik()
	{
		//sucht eine zufaelig geweahte Musik
		int i = 0;
		for (int r = 0 ; aktuelleMusikListe.size() > r ; r++)
		{
			i = new Random().nextInt(aktuelleMusikListe.size());
			if (abgespielteMusik.contains(i) == false)
			{
				abgespielteMusik.add(i);
				break;
			}
			
			if (r == aktuelleMusikListe.size())
			{	
				abgespielteMusik.clear();
			}
		}
		
		mediaPlayer = new MediaPlayer(aktuelleMusikListe.get(i));	
		//TODO  
		mediaPlayer.setVolume(0.0);
		
		//ist die Musik zuende, wird der inhalt der run() Methode ausgeführt.
		mediaPlayer.setOnEndOfMedia(new Runnable()
		{
			//wechselt die Musik zufeallig
			@Override
			public void run()
			{
				wechselMusik();
				starteMusik();
			}
		});	
	}
	
	/**
	 * pausiert die Musik
	 */
	public void pausiereMusik()
	{
		mediaPlayer.pause();
	}
	
	/**
	 * beendet die Musik
	 */
	public void beendeMusik()
	{
		mediaPlayer.stop();
	}

	
	/**
	 * wechselt die Playlist TODO Methode muss noch ausformoleirt werden (17.07.2016)
	 */
	public void wechselMusiklist()
	{
		
	}
}
