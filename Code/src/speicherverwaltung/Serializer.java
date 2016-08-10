package speicherverwaltung;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import map.BodenMaterial;

/**
 * Klasse zum schreiben von Objekten in eine Datei, um diese dann für das Spiel 
 * verwenden zu können
 * 
 * @author Thomas
 *
 */
public class Serializer {
	public static void main(String[] args) {
		
		RessourcenObjekte ressourcen = new RessourcenObjekte();
		
		serializeBodenMaterial("src/speicherverwaltung/bodenschaetze", ressourcen.getBodenschaetze());
		serializeBodenMaterial("src/speicherverwaltung/bodentypen", ressourcen.getBodentypen());

	}

	private static void serializeBodenMaterial(String path, ArrayList<BodenMaterial> objects) {
		FileOutputStream fstream;
		ObjectOutputStream ostream;
		
		try {
			fstream = new FileOutputStream(path);
			ostream = new ObjectOutputStream(fstream);
			
			for (int i = 0; i < objects.size(); i++) {
				ostream.writeObject(objects.get(i));
			}
			ostream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
