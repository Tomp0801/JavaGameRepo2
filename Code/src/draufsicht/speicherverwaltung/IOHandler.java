package draufsicht.speicherverwaltung;

import java.util.ArrayList;

public class IOHandler 
{	
	/**
	 * zum speichern in Dateien
	 */
	private Serializer serializer;
	
	/**
	 * zum auslesen von Dateien
	 */
	private Deserializer deserializer;

	/**
	 * Pfad zu allen Dateien
	 */
	private String defaultPath; 
	
	private static IOHandler instance;
	
	/**
	 * privater Konstruktor
	 */
	private IOHandler()
	{
		instance = this;
		
		serializer = new Serializer();
		deserializer = new Deserializer();
		this.defaultPath = "src/speicherverwaltung/";
	}
	
	/**
	 * Speichert eine ArrayList von Objekten in einer Datei ab
	 * @param filename Name der Datei, die zum speichern verwendet werden soll
	 * @param list ArrayList der Objekte, die abgespeichert werden sollen
	 */
	public <T> void saveArrayList(String filename, ArrayList<T> list)
	{
		serializer.serializeArray(defaultPath + filename, list);
	}
	
	/**
	 * List eine Datei aus und gibt die Objekte in einer ArrayList zurück
	 * @param filename Name der Datei, die ausgelesen werden soll 
	 * @return eine ArrayList mit den Objekten, die in der Datei gespeichert sind
	 */
	public <T> ArrayList<T> readArrayList(String filename)
	{
		return deserializer.deserializeArrayList(defaultPath + filename);
	}
	
	/**
	 * gibt den Standard Pfad zurück
	 * @return den Pfad, in dem Dateien gespeichert und gesucht werden
	 */
	public String getDefaultPath()
	{
		return defaultPath;
	}
	
	/**
	 * gibt einen statischen IOHandler zurück
	 * @return eine Instanz auf einen IOHandler
	 */
	public static IOHandler getInstance()
	{
		if (instance == null)
		{
			new IOHandler();
		}
		return instance;
	}
}
