package spielmodel.ressource.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import spielctr.controller.Serializable;


public class LoadTest {
	
	
	public static void main(String[] args) {
		
		//Material.initStaticMaterials("src/spielmodel/ressource/model/Materialien.xml");
		
		for (int i = 0; i < Material.materialien.size(); i++) {
			System.out.println(Material.materialien.get(i).getName());
			System.out.println(Material.materialien.get(i).getColor().toString());
		}
		
	}
}
