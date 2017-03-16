package draufsicht.speicherverwaltung;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Klasse zum lesen gespeicherter Dateien aus einer Datei
 * 
 * @author Thomas
 *
 */
public class Deserializer {
//	private static ArrayList<BodenMaterial> bodenschaetze;
//	private static ArrayList<BodenMaterial> bodentypen;
	
	public Deserializer() {
//		bodenschaetze = deserializeArrayList("src/speicherverwaltung/bodenschaetze");
//		bodentypen = deserializeArrayList("src/speicherverwaltung/bodentypen");
	}
	
	/**
	 * Lieﬂt Objekte des Typs BodenMaterial aus einer Datei
	 * Datei Muss die entsprechende Art von Objekten beinhalten TODO: entsprechende Exception
	 * 
	 * @param path der DateiPfad 
	 * @return Liste der BodenMaterialen die inder Datei gefunden wurden 
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> deserializeArrayList(String path) {
		FileInputStream fstream;
		ObjectInputStream ostream;
		ArrayList<T> list = new ArrayList<T>();
		boolean eof = false;
		
		try {
			fstream = new FileInputStream(path);
			ostream = new ObjectInputStream(fstream);
			
			//auslesen, bis zum ende der Datei
			while (!eof) {
				try {
					list.add((T) ostream.readObject());	//in array Schreiben
				} catch (EOFException eofExcept) {	//Ende der Datei erreicht
					ostream.close();
					eof = true;	
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	/**
	 * @return the bodenschaetze
	 */
//	public ArrayList<BodenMaterial> getBodenschaetze() {
//		if (bodenschaetze == null) {	//initialisieren, falls noch nicht geschehen
//			init();
//		}
//		return bodenschaetze;
//	}
//
//	/**
//	 * @return the bodentypen
//	 */
//	public ArrayList<BodenMaterial> getBodentypen() {
//		if (bodentypen == null) {		//initialisieren, falls noch nicht geschehen
//			init();
//		}
//		return bodentypen;
//	}
}
