package ressource.model;

import java.util.ArrayList;
import java.util.Iterator;

public interface Serializable {	
	public default String serialize() {
		String s = "<" + this.getClass() + ">";
		s += this.serializeData();
		s += "</" + this.getClass() + ">";
		return s;
	}
	
	public String serializeData();
	
	public default void deserializeFrom(String objectString) {
		objectString = findValue(objectString, this.getClass().toString(), 0);
		deserializeDataFrom(objectString);
	}
	
	public void deserializeDataFrom(String objectString);
	
	
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

	static String findValue(String string, String keyName) {
		return findValue(string, keyName, 0);
	}
	
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
	
	public static String serializePrimitive(String name, String value) {
		String ret = "\t<" + name + ">";
		ret += value;
		ret += "</" + name + ">\r\n";
		return ret;
	}
}
