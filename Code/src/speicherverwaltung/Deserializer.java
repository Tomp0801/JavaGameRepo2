package speicherverwaltung;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import map.BodenMaterial;

/**
 * Klasse zum lesen gespeicherter Dateien aus einer Datei
 * 
 * @author Thomas
 *
 */
public class Deserializer {
	private static ArrayList<BodenMaterial> bodenschaetze;
	private static ArrayList<BodenMaterial> bodentypen;
	
	private static void init() {
		bodenschaetze = deserializeBodenMaterial("src/speicherverwaltung/bodenschaetze");
		bodentypen = deserializeBodenMaterial("src/speicherverwaltung/bodentypen");
	}
	
	/**
	 * Lieﬂt Objekte des Typs BodenMaterial aus einer Datei
	 * @param path der DateiPfad 
	 * @return Liste der BodenMaterialen die inder Datei gefunden wurden 
	 */
	private static ArrayList<BodenMaterial> deserializeBodenMaterial(String path) {
		FileInputStream fstream;
		ObjectInputStream ostream;
		ArrayList<BodenMaterial> bodenMaterial = new ArrayList<BodenMaterial>();
		boolean eof = false;
		
		try {
			fstream = new FileInputStream(path);
			ostream = new ObjectInputStream(fstream);
			
			//auslesen, bis zum ende der Datei
			while (!eof) {
				try {
					bodenMaterial.add((BodenMaterial)ostream.readObject());	//in array Schreiben
				} catch (EOFException eofExcept) {	//Ende der Datei erreicht
					ostream.close();
					eof = true;	
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return bodenMaterial;
	}

	/**
	 * @return the bodenschaetze
	 */
	public static ArrayList<BodenMaterial> getBodenschaetze() {
		if (bodenschaetze == null) {	//initialisieren, falls noch nicht geschehen
			init();
		}
		return bodenschaetze;
	}

	/**
	 * @return the bodentypen
	 */
	public static ArrayList<BodenMaterial> getBodentypen() {
		if (bodentypen == null) {		//initialisieren, falls noch nicht geschehen
			init();
		}
		return bodentypen;
	}
}
