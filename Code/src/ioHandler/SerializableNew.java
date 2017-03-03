package ioHandler;

import java.io.Serializable;

public interface SerializableNew extends Serializable{
	/**
	 * macht die Modelklasse speicherbereit. Muss vor dem Speichern fuer jede verwendete Klasse 
	 * die gespeichert werden soll, ausgefuerht werden.
	 * Auchtung: zu beachten ist dabei, dass auch die Modelklassen, auf die das zu speicherende Objekt verweist 
	 * vorher diese Methode aufrufen muessen. 
	 */
	public abstract void serializ();
	
	/**
	 * lead die Modelkasse 
	 */
	public abstract void deserializ(); 
	
}
