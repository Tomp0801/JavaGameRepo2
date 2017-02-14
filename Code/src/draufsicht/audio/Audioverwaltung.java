package draufsicht.audio;

import java.io.FileNotFoundException;

import javafx.scene.media.AudioClip;

/**
 * 
 * @author Dennis
 *
 * Bietet Methoden zur Verwaltung des Audios an. 
 */
public class Audioverwaltung
{
	private Audioverwaltung()
	{}
	
	/**
	 * spielt eine Audiodatei ab
	 * 
	 * @param source der Weg der Audiodatei
	 * @throws FileNotFoundException Datei ist nicht vorhanden
	 */
	public static void spieleAudio(String source) throws FileNotFoundException
	{
		try 
		{
		new AudioClip(source).play();
		}
		catch(IllegalArgumentException e)
		{
			throw new FileNotFoundException();
		}
	}
}
