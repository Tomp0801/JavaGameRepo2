package spielctr.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Interface für Objekte, die im xml Format abgespeichert werden sollen
 * 
 * die Methoden serializeData und deserializeDataFrom müssen implementiert werden
 * die Methode deepCopy muss dann implementiert werden, wenn die Methode deserializeArrayList verwendet werdens soll
 * 
 * @author Thomas
 *
 */
public interface Serializable {	
	/**
	 * Serialisiert das Objekt im xml Format zu einem String
	 * verwendet dazu die Methode serializeData und fügt dem String den NAMEN SEINER KLASSE hinzu
	 * Hinweis: Diese Methode sollte zum Speichern eines Eigenständigen Objekts verwendet werden
	 * von einem Objekt, das ein Member eines anderen Objekts ist, sollten nur die Inhalte mit serialiteData() gespeichert werden
	 * 
	 * @return das serialisierte Objekt als String
	 */
	public default String serialize() {
		String s = "<" + this.getClass() + ">";
		s += this.serializeData();
		s += "</" + this.getClass() + ">";
		return s;
	}
	
	/**
	 * Serialisiert die Daten des Objekts im xml Format zu einem String
	 * der Name der Klasse wird NICHT hinzugefügt
	 * Hinweis: Diese Methode sollte dann aufgerufen werden, wenn ein Member-Objekt des zu serialisierenden
	 * Objekts serialisiert werden soll
	 * 
	 * @return die Daten des Objekts als String
	 */
	public String serializeData();
	
	/**
	 * deserialisiert dieses Objekt und überschreibt alle eingeschlossenen Daten mit den aus dem String extrahierten Daten
	 * HINWEIS: die Methode sucht in dem xml String das zuerst vorkommende <[dieseKlasse]></[dieseKlasse]> Objekt
	 * 
	 * @param objectString der String im xml Format, aus dem das Objekt deserialisiert wird
	 */
	public default void deserializeFrom(String objectString) {
		objectString = findValue(objectString, this.getClass().toString(), 0);
		deserializeDataFrom(objectString);
	}
	
	/**
	 * deserialisiert die Daten dieses Objekts und überschreibt alle eingeschlossenen Daten mit den aus dem String extrahierten Daten
	 * HINWEIS: es werden direkt die Daten in dem String gesucht
	 * das heißt, dass der String NUR die Daten dieses Objekts enthalten sollte
	 * 
	 * @param objectString der String im xml Format, aus dem das Objekt deserialisiert wird
	 */
	public void deserializeDataFrom(String objectString);
	
	/**
	 * Sucht in dem gegebenen String nach dem Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @param start Startindex für die Suche
	 * @return den gefundenen Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 */
	public static String findValue(String objectString, String keyName, int start) {
		String value = null;
		int begin, end = -1;

		if (start >= 0) {
			begin = objectString.indexOf("<"+keyName+">", start);
			if (begin != -1) {
				end = objectString.indexOf("</"+keyName+">", start);
			}

			if (begin != -1 && end != -1) {
				begin = begin + 2 + keyName.length();
				value = objectString.substring(begin, end);
				value = value.trim();
			}
		}

		return value;
	}

	/**
	 * Sucht in dem gegebenen String nach dem Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return den gefundenen Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 */
	public static String findValue(String string, String keyName) {
		return findValue(string, keyName, 0);
	}
	
	/**
	 * Sucht in dem gegebenen String nach allen Werten mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return ein Iterator über alle gefundenen Werte, jeweils in getrimmter String-Form
	 */
	public static Iterator<String> findAllValues(String string, String keyName) {
		ArrayList<String> values = new ArrayList<String>();
		int startIndex = string.indexOf("<" + keyName + ">");
		String value;

		while (startIndex < string.length()) {
			value = findValue(string, keyName, startIndex);
			if (value != null) {
				values.add("<" + keyName + ">" + value + "</" + keyName + ">");
				startIndex = string.indexOf("</"+keyName+">", startIndex) + keyName.length()+3;		//\t<keyname>value</keyname>\r\n
			}
			else {		//kein value gefunden -> abbrechen
				startIndex = string.length();
			}
		}

		return values.iterator();
	}
	
	/**
	 * serialisiert einen einzelnen Wert in xml Form, wie folgt:
	 * \t<[name]>[wert]</[name]>\r\n
	 * 
	 * @param name Bezeichnung für den Wert
	 * @param value den Wert in einen String umgewandelt
	 * @return den in xml-Form serialisierten Wert
	 */
	public static String serializeValue(String name, String value) {
		String ret = "\t<" + name + ">";
		ret += value;
		ret += "</" + name + ">\r\n";
		return ret;
	}
	
	/**
	 * Serialisiert alle Objekte einer Collection von Serializable-Objekten nacheinander in xml Form
	 *
	 * @param collection die Collection, die serialisiert werden soll
	 * @param collectionName Bezeichnung für die Colection
	 * 
	 * @return die serialisierte Collection als String
	 */
	public static <T extends Serializable> String serializeCollection(Collection<T> collection, String collectionName) {
		Iterator<T> iter = collection.iterator();
		String ret = "";
		
		ret = "\t<" + collectionName + ">";		
		while (iter.hasNext()) {
			ret += iter.next().serialize();
		}
		ret += "</" + collectionName + ">\r\n";

		return ret;
	}
	
	/**
	 * Serialisiert alle Objekte einer Collection von primitiven Datentypen oder Strings nacheinander in xml Form
	 * 
	 * @param collection die Collection, die serialisiert werden soll
	 * @param collectionName Bezeichnung für die Colection
	 * @return die serialisierte Collection als String
	 */
	public static <T> String serializePrimitiveCollection(Collection<T> collection, String collectionName) {
		Iterator<T> iter = collection.iterator();
		String ret = "";
		
		ret = "\t<" + collectionName + ">";		
		while (iter.hasNext()) {
			ret += "<value>" + iter.next() + "</value>";
		}
		ret += "</" + collectionName + ">\r\n";

		return ret;
	}
	
	/**
	 * deserialisiert aus einem xml String alle Objekte mit der angegebenen Bezeichnung und wandelt sie in
	 * Objekt T der Klasse des übergebenen emptyObjects um. Die erstellten Objekte werde in einer ArrayList gesammelt
	 * HINWEIS: diese Methode kann NUR verwendet werden, wenn die Methode deepCopy überschrieben wird!
	 * 
	 * @param dataString String aus dem Objekte deserialisiert werden 
	 * @param keyName Bezeichnung für die Objekte 
	 * @param emptyObject leeres Objekt der Klasse, die auch der zu deserialisierenden Objekte entspricht
	 * @return eine ArrayList mit alle deserialisierten Objekten
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> ArrayList<T> deserializeArrayList(String dataString, String keyName, T emptyObject) {
		ArrayList<T> collection = new ArrayList<T>();
		
		dataString = findValue(dataString, keyName);
		
		Iterator<String> iter = findAllValues(dataString, emptyObject.getClass().getSimpleName());
		
		while (iter.hasNext()) {
			emptyObject.deserializeDataFrom(iter.next());
			collection.add((T)emptyObject.deepCopy());
		}
		
		return collection;
	}

	/**
	 * Kopiert alle für die deserialisierung relevanten Daten des Objekts in ein neu erstelltes Objekt
	 * HINWEIS: diese Methode muss überschrieben werden, wenn deserializeArrayList verwendet werden soll
	 * Hinweis2: ich weiß, es gibt auch clone(), aber damit hat es irgendwie nicht funktioniert, weil clone() den Typ Object zurückgibt
	 * @return eine Kopie des aktuellen Objekts
	 */
	public default <T extends Serializable> T deepCopy() {
		return null;
	}
	
	/**
	 * Sucht in dem gegebenen String nach dem Integer-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @param start Startindex für die Suche 
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static int findIntValue(String string, String keyName, int start) throws NumberFormatException {
		return Integer.parseInt(findValue(string, keyName, start));
	}
	
	/**
	 * Sucht in dem gegebenen String nach dem Integer-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static int findIntValue(String string, String keyName) throws NumberFormatException {
		return Integer.parseInt(findValue(string, keyName, 0));
	}
	
	/**
	 * Sucht in dem gegebenen String nach dem Float-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @param start Startindex für die Suche 
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static float findFloatValue(String string, String keyName, int start) throws NumberFormatException {
		return Float.parseFloat(findValue(string, keyName, start));
	}
	
	/**
	 * Sucht in dem gegebenen String nach dem Float-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static float findFloatValue(String string, String keyName) throws NumberFormatException {
		return Float.parseFloat(findValue(string, keyName, 0));
	}
	
	/**
	 * Sucht in dem gegebenen String nach dem Double-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @param start Startindex für die Suche 
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static double findDoubleValue(String string, String keyName, int start) throws NumberFormatException {
		return Double.parseDouble(findValue(string, keyName, start));
	}

	/**
	 * Sucht in dem gegebenen String nach dem Double-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static double findDoubleValue(String string, String keyName) throws NumberFormatException {
		return Double.parseDouble(findValue(string, keyName, 0));
	}

	/**
	 * Sucht in dem gegebenen String nach dem Bool-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @param start Startindex für die Suche 
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static boolean findBoolValue(String string, String keyName, int start) throws NumberFormatException {
		return Boolean.parseBoolean(findValue(string, keyName, start));
	}
	
	/**
	 * Sucht in dem gegebenen String nach dem Bool-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static boolean findBoolValue(String string, String keyName) throws NumberFormatException {
		return Boolean.parseBoolean(findValue(string, keyName, 0));
	}

	/**
	 * Sucht in dem gegebenen String nach dem Long-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @param start Startindex für die Suche 
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static long findLongValue(String string, String keyName, int start) throws NumberFormatException {
		return Long.parseLong(findValue(string, keyName, start));
	}

	/**
	 * Sucht in dem gegebenen String nach dem Long-Wert mit dem angegebenen Namen
	 * @param objectString xml-String, in dem gesucht wird
	 * @param keyName Bezeichnung des Wertes
	 * @return den gefundenen Integer-Wert in getrimmter String-Form oder null, wenn die Bezeichnung nicht gefunden wurde
	 *
	 * @throws NumberFormatException wenn Fehler beim parsen
	 */
	public static long findLongValue(String string, String keyName) throws NumberFormatException {
		return Long.parseLong(findValue(string, keyName, 0));
	}
}

