package map;

import java.util.ArrayList;

import himmelskoerper.Himmelskoerper;
import himmelskoerper.Stern;
import javafx.geometry.Point3D;

/**
 * Eine Sektion hat eine unver�nderbare Gr��e, dazu ein Binom. 
 * Ein Binom ist eine Art, wie diese Sektion aufgebaut ist. 
 * Zum Beispiel k�nnte eine Sektion einen gro�en Nebel beinhalten oder ein gro�es Asteroidenfeld 
 * oder extrem viele/wenige Planeten. 
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
	
//	/**
//	 * eine Liste mit den Himmelskoerpern die sich in dier Sektion befinden
//	 */
//	private ArrayList<Himmelskoerper> himmelskoerper = new ArrayList<Himmelskoerper>();
	
	/**
	 * Sterne die sich in dieser Sektion befinden
	 */
	private ArrayList<Stern> stern = new ArrayList<Stern>(); 
	
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
	
	
//	/**
//	 * 
//	 * @return
//	 */
//	public ArrayList<Himmelskoerper> getHimmelskoerper()
//	{
//		return himmelskoerper;
//	}
	
	/**
	 * gibt einen Stern zurueck
	 * @return
	 */
	public ArrayList<Stern> getStern()
	{
		return this.stern; 
	}
	
}
