package view.aufbaumodus;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.Bereich;
import map.Feld;

public class PlanetenkarteFelder extends AufbaumodusSichtweisePlaneten
{

	/**
	 * Eine Konstante die die minimale groeße eines Canvas festlegt, die ein Feld darstellt. 
	 */
	private final int MINSIZEFELD = 50;
	
	/**
	 * diese VBox wird dazu verwendet eine Reihe von HBoxen untereinander anzuordnen. 
	 */
	private VBox spaltenLayoutBox = new VBox();

	/**
	 * ein Array von HBoxen. Diese werden untereinander angeordnet um dort Canvas hineinzulegen, die 
	 * die einzenlnen Felder darstelen sollen.
	 */
	private HBox[] hBoxCanvasreihe;
	
	public PlanetenkarteFelder(Bereich bereich)
	{
		this.getSubSceneRoot().setPrefSize(bereich.BREITE * MINSIZEFELD +10, bereich.HOEHE*MINSIZEFELD+10);
		this.getSubSceneRoot().getChildren().add(spaltenLayoutBox);	
		
		initFelder(bereich);
	}
	
	
	/**
	 * erstellt die Felder
	 * @param bereich
	 */
	private void initFelder(Bereich bereich)
	{
		//setzt eine passende anzahl an HBoxen in eine VBox um diese anschließend mit Canvas zu fuellen die die Bereiche darstellen
		hBoxCanvasreihe = new HBox[bereich.HOEHE];
				
		for (int i = 0 ; bereich.HOEHE > i ; i++)
		{
			hBoxCanvasreihe[i] = new HBox();
			hBoxCanvasreihe[i].setPrefSize(bereich.BREITE*MINSIZEFELD, MINSIZEFELD);
			this.spaltenLayoutBox.getChildren().add(hBoxCanvasreihe[i]);	
		}
			
		for(int i = 0 ; bereich.HOEHE > i ; i++)
		{
			for (int j = 0; bereich.BREITE > j ; j++ )
			{
				Feld feld = bereich.getFeld(i, j);
						
				Canvas canvas = new Canvas();
										
				canvas.setHeight(MINSIZEFELD );
				canvas.setWidth(MINSIZEFELD);
				feld.getAussehen(canvas);
						
				hBoxCanvasreihe[i].setPrefHeight(canvas.getHeight());
				hBoxCanvasreihe[i].getChildren().add(canvas);
			}
		}
	}
}
