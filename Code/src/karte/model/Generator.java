package karte.model;

import java.util.ArrayList;


import javafx.geometry.Point2D;
import obersteEbene.controller.Random;
import ressource.model.Material;

public class Generator {
	
	private Material grundMaterial;
	
	private int radius = 1;
		
	private Random prng;
	
	private int width;
	private int height;
	private float[][] floatMap;
	
	private ArrayList<Material> materialien;
	private ArrayList<Float> gewichtungen;
	
	public Generator(Random prng, Material grundMaterial) {
		materialien = new ArrayList<Material>();
		gewichtungen = new ArrayList<Float>();
		
		this.grundMaterial = grundMaterial;
		this.prng = prng;
	}
	
	public Map generateMap(int width, int height) {
		floatMap = new float[width][height];
		this.width = width;
		this.height = height;
		float rand;
		
		//zuf‰llige Wert von 0 bis 1
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				rand = prng.random(0f, 1f);
				floatMap[x][y] = rand;
			}
		}
		
		Map map = translateToMap();
		return map;
	}
	
	public void addMaterial(Material material, float gewichtung) {
		materialien.add(material);
		gewichtung = (gewichtung) / 100f;
		for (int i = 0; i < gewichtungen.size(); i++) {		//alle bisherigen Gewichtungen hinzuaddieren
			gewichtung += gewichtungen.get(i);
		}
		gewichtungen.add(gewichtung);
	}
	
	
	private Point2D getRandomPoint() {
		int x = prng.random(0, width-1);
		int y = prng.random(0, height-1);
		return new Point2D(x, y);
	}
	
	public Map smoothMap() {
		float mostCommon;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				mostCommon = getMostCommonSurrounding(x, y);
				floatMap[x][y] = mostCommon;
			}
		}
	
		return translateToMap();
	}
	
	private Map translateToMap() {
		Map map = new Map(width, height);
		int i;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				i = 0;
				while (i < gewichtungen.size() && floatMap[x][y] > gewichtungen.get(i)) {
					i++;
				}
				if (i == gewichtungen.size()) {
					map.getFeld(x, y).setBodenMaterial(grundMaterial);					
				} else {
					map.getFeld(x, y).setBodenMaterial(materialien.get(i));
				}
			}
		}
		
		return map;
	}
	
	private void fillBlanks(Map map) {
		Feld feld;
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				feld = map.getFeld(x, y);
				if (feld.getBodenMaterial() == null) feld.setBodenMaterial(grundMaterial);
			}
		}
	}
	
	private float getSurroundingFieldCount(int posX, int posY) {
		float count = 0;
		
		for (int x = posX - radius; x <= posX + radius; x++) {
			for (int y = posY - radius; y <= posY + radius; y++) {
				if (x >= 0 && x < width && y >= 0 && y < height) {		//in dem inneren Map bereich
					if (y != posY || x != posX) {		//nicht field mitz‰hlen
						count += floatMap[x][y];
					}
				} else {		//Randbereiche, auﬂerhalb der Karte
					count += 0.5;
				}
			}
		}
		
		return count;
	}
	
	private float getMostCommonSurrounding(int posX, int posY) {
		int[] counts = new int[materialien.size()+1];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}
		
		int index;
		
		for (int x = posX - radius; x <= posX + radius; x++) {
			for (int y = posY - radius; y <= posY + radius; y++) {
				if (x >= 0 && x < width && y >= 0 && y < height) {		//in dem inneren Map bereich
					if (y != posY || x != posX) {		//nicht field mitz‰hlen
						//der Zahlenbereich, der zutrifft, wird hochgez‰hlt
						index = 0;
						while (index < gewichtungen.size() && floatMap[x][y] > gewichtungen.get(index)) {
							index++;
						}
						counts[index]++;		//wenn kein Zahlenbereich, Grundmaterial
					}
				} else {		//Randbereiche, auﬂerhalb der Karte
					//nichts hochz‰hlen
				}
			}
		}
		
		//Maximum aus counts suchen
		int max = 0;
		index = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > max) {
				max = counts[i];
				index = i;
			}
		}
		
		
		if (index == materialien.size()) {
			return gewichtungen.get(index-1) + 1; 
		} else {
			return gewichtungen.get(index);
		}
	}
}
