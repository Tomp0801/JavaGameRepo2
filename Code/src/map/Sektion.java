package map;

import java.util.ArrayList;

import himmelskoerper.Himmelskoerper;
import himmelskoerper.Stern;
import javafx.geometry.Point3D;

/**
 * Eine Sektion hat eine unveränderbare Größe, dazu ein Binom. 
 * Ein Binom ist eine Art, wie diese Sektion aufgebaut ist. 
 * Zum Beispiel könnte eine Sektion einen großen Nebel beinhalten oder ein großes Asteroidenfeld 
 * oder extrem viele/wenige Planeten. 
 * 
 * @author Dennis
 *
 */
public class Sektion 
{
	//TODO die Einstellung der groeße einer Sektion soll in einer Datei abgespeichert werden, in der sie einfach geaendert werden kann
	/**
	 * eine feste groeße einer Sektion 
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
	 * @return die groeße einer Sektion
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
