package gameMaker.view;

import javafx.scene.layout.AnchorPane;
import personensicht.view.welt.map.RegionV;

public class AnchorPaneRegion extends AnchorPane
{
	AnchorPaneRegion(){
		this.setPrefSize(RegionV.SIZEMIN*2,RegionV.SIZEMIN*2);
		this.setStyle("-fx-background-color: GREY");
		this.setMinSize(RegionV.SIZEMIN, RegionV.SIZEMIN);
		this.setMaxSize(RegionV.SIZEMAX, RegionV.SIZEMAX);
		System.out.println("regionPaner erstelt: "+this.getPrefHeight());
		this.setPickOnBounds(false);	
	}
}
