package draufsicht.speicherverwaltung;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Klasse zum schreiben von Objekten in eine Datei, um diese dann für das Spiel 
 * verwenden zu können
 * 
 * @author Thomas
 *
 */
public class Serializer {
	public Serializer () {
		
//		RessourcenObjekte ressourcen = new RessourcenObjekte();
//		
//		serializeArray("src/speicherverwaltung/bodenschaetze", ressourcen.getBodenschaetze());
//		serializeArray("src/speicherverwaltung/bodentypen", ressourcen.getBodentypen());

	}

	public <T> void serializeArray(String path, ArrayList<T> objects) {
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
