package map;

import java.util.ArrayList;

import himmelskoerper.Himmelskoerper;
import himmelskoerper.Stern;
import javafx.geometry.Point3D;

/**
 * 
 * @author Dennis
 *
 */
public class Sektion 
{
	//TODO die Einstellung der groe�e einer Sektion soll in einer Datei abgespeichert werden, in der sie einfach geaendert werden kann
	/**
	 * eine feste groe�e einer Sektion 
	 */
	private final int size = 5000;

	
	/**
	 * erstellt eine neue Sektion
	 */
	public Sektion()
	{
		//TODO Sektions Generrator koennte zum Beispiel hier eingebaut werden. 
	}
	
	
	/**
	 * 
	 * @return die groe�e einer Sektion
	 */
	public int getSize()
	{
		return size;
	}
}