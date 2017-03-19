package draufsicht.model;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Point2D;
import spielctr.controller.Random;
import spielmodel.ressource.model.Material;

public class Generator {
	
	private Material grundMaterial;
	
	private int radius = 1;
	private int smoothIterations;
	private int minimumNeighbors;
	
	private Random prng;
	
	private int width;
	private int height;
	private int[][] intMap;
	
	private ArrayList<Material> materialien;
	private ArrayList<Integer> gewichtungen;
	
	private HashMap<Material, Float> bodenschaetze;

	
	public Generator(Random prng, Material grundMaterial) {
		materialien = new ArrayList<Material>();
		gewichtungen = new ArrayList<Integer>();
		bodenschaetze = new HashMap<Material, Float>();
		
		smoothIterations = 20;
		minimumNeighbors = 3;
		
		this.grundMaterial = grundMaterial;
		this.prng = prng;
	}
	
	public void generateMap(int width, int height) {
		intMap = new int[width][height];
		this.width = width;
		this.height = height;
		int rand;
		
		//zufällige Wert von 0 bis 1
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				rand = prng.random(0, 100);
				int index = 0;
				while (index < gewichtungen.size() && rand > gewichtungen.get(index)) {
					index++;
				}
				
				intMap[x][y] = index;
			}
		}
	}
	
	public void addMaterial(Material material, int gewichtung) {
		materialien.add(material);
		int gesamt = 0;
		for (int g : gewichtungen) {
			gesamt += g;
		}
		gewichtungen.add(gewichtung + gesamt);
	}
	
	public void smoothMapRandom() {
		Point2D rand;
		
		smoothIterations = width * height;
		
		for (int i = 0; i < smoothIterations; i++) {
			rand = getRandomPoint();
			int x = Math.round((float)rand.getX());
			int y = Math.round((float)rand.getY());
			smoothField(x, y);
		}
		
		
	}
	
	public void smoothMap() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				smoothField(x, y);
			}
		}
	}
	
	private void smoothField(int x, int y) {
		if (getSurroundingCount(x, y) < minimumNeighbors) {
			int mostCommon = getMostCommonSurrounding(x, y);
			intMap[x][y] = mostCommon;	
		}
	}
	
	public Map translateToMap() {
		Map map = new Map(width, height, prng.getSeed(), bodenschaetze);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (intMap[x][y] == materialien.size()) {
					map.initFeld(x, y, grundMaterial);
				} else {
					map.initFeld(x, y, materialien.get(intMap[x][y]));
				}
			}
		}
		
		return map;
	}
	
	private int getMostCommonSurrounding(int posX, int posY) {
		int[] counts = new int[materialien.size()+1];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}
		
		int index;
		
		for (int x = posX - radius; x <= posX + radius; x++) {
			for (int y = posY - radius; y <= posY + radius; y++) {
				if (x >= 0 && x < width && y >= 0 && y < height) {		//in dem inneren Map bereich
					if (y != posY || x != posX) {		//nicht field mitzählen
						counts[intMap[x][y]]++;		//wenn kein Zahlenbereich, Grundmaterial
					}
				} else {		//Randbereiche, außerhalb der Karte
					//nichts hochzählen
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
		
		return index;
	}
	
	private int getSurroundingCount(int posX, int posY) {
		int count = 0;
		
		for (int x = posX - radius; x <= posX + radius; x++) {
			for (int y = posY - radius; y <= posY + radius; y++) {
				if (x >= 0 && x < width && y >= 0 && y < height) {		//in dem inneren Map bereich
					if (y != posY || x != posX) {		//nicht field mitzählen
						if (intMap[x][y] == intMap[posX][posY]) {
							count++;
						}
					}
				}
			}
		}
		
		return count;
	}
	
	
	
	private Point2D getRandomPoint() {
		int x = prng.random(0, width-1);
		int y = prng.random(0, height-1);
		return new Point2D(x, y);
	}
	
	public void setSmoothIterations(int iterations) {
		this.smoothIterations = iterations;
	}
	
	public void setSmoothRadius(int radius) {
		this.radius = radius;
	}
	
	public void setMinimumNeightbors(int min) {
		this.minimumNeighbors = min;
	}
	
	public void addBodenschatz(Material bodenschatz, float vorkommenswahrscheinlichkeit) {
		this.bodenschaetze.put(bodenschatz, vorkommenswahrscheinlichkeit * bodenschatz.getBodenVorkommen() / 100f);
	}
	
	public void setBodenschaetze(HashMap<Material, Float> bodenschaetze) {
		this.bodenschaetze = bodenschaetze;
	}
	
	private float getSurroundingFieldCount(int posX, int posY) {
		float count = 0;
		
		for (int x = posX - radius; x <= posX + radius; x++) {
			for (int y = posY - radius; y <= posY + radius; y++) {
				if (x >= 0 && x < width && y >= 0 && y < height) {		//in dem inneren Map bereich
					if (y != posY || x != posX) {		//nicht field mitzählen
						count += intMap[x][y];
					}
				} else {		//Randbereiche, außerhalb der Karte
					count += 0.5;
				}
			}
		}
		
		return count;
	}
}
