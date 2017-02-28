package karte.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import karte.model.Feld;

public class FeldGroup extends Group {
	private static final int WIDTH = 15;
	private static final int HEIGHT = 15;
	
	private Feld parent;
	
	private Rectangle boden;
	
	public FeldGroup(Feld feld) {
		parent = feld;
		this.setOnMouseClicked(feld);
		
		boden = new Rectangle(WIDTH, HEIGHT);
		boden.setFill(Color.BLACK);
		this.getChildren().add(boden);
	}
	
	public void addChild(Group child) {
		this.getChildren().add(child);
	}
	
	public void addChild(Node child) {
		this.getChildren().add(child);
	}
	
	public Feld getFeld() {
		return parent;
	}
	
	public Rectangle getBoden() {
		return boden;
	}
}
