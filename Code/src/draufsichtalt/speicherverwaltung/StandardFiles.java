package draufsicht.speicherverwaltung;

import java.io.File;

/**
 * In dieser Klasse befinden sich alle Standard vorhandenen Speicherwege 
 * 
 * @author Dennis
 *
 */
public class StandardFiles 
{
	private StandardFiles()
	{}
	
	/**
	 * 
	 * @return File Audio/Musik/Hintergrund
	 */
	public static File getMusikHintergrund()
	{
		return new File("Audio/Musik/Hintergrund");
	}
	
	
	/**
	 * 
	 * @return File Audio/Musik/Kampf
	 */
	public static File getMusikKampf()
	{
		return new File("Audio/Musik/Kampf");
	}
}
