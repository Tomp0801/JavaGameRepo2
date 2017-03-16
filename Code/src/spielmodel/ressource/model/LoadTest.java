package spielmodel.ressource.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class LoadTest {
	
	
	public static void main(String[] args) {
		
		ArrayList<Material> materialien = new ArrayList<Material>();

		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("src/ressource/model/Materialien.xml"));
			String fileText = "";
			String line = bReader.readLine();
			
			while (line != null) {
				fileText += line;
				line = bReader.readLine();
			}
			
			for (Iterator<String> it = Serializable.findAllValues(fileText, "Material"); it.hasNext(); ) {
				Material m = new Material();
				m.deserializeDataFrom(it.next());
				materialien.add(m);
			}
			
			bReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		for (int i = 0; i < materialien.size(); i++) {
			System.out.println(materialien.get(i).getName());
			System.out.println(materialien.get(i).getColor().toString());
		}
		
	}
}
