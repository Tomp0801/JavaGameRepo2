package personensicht.view.gameObjekte;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import personensicht.model.gameObjekte.Tisch;
import personensicht.view.Shape3DZusatzMethoden;

public class TischV extends GameObjektV
{

	public final static int MAXSIZE_X = 1000; 
	public final static int MAXSIZE_Y = 1000; 
	public final static int MAXSIZE_Z = 400; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	/**
	 * die Tischplatte
	 */
	private VBox root = new VBox();
	
	private Box tischplatte = new Box();
	/**
	 * vier Tischbeine
	 */
	private Box[] tischBeine = new Box[4];
	
	public TischV(Tisch model)
	{
		this.setNode(this.root);
		this.root.setAlignment(Pos.TOP_LEFT);
		this.root.setRotationAxis(Rotate.X_AXIS);
		this.root.setRotate(-90);
		this.root.prefHeightProperty().bindBidirectional(model.getHeight());
		this.root.prefWidthProperty().bindBidirectional(model.getWidth());
		this.root.translateZProperty().bindBidirectional(model.getDepth());
		this.root.layoutXProperty().bindBidirectional(model.getLayoutX());
		this.root.layoutYProperty().bindBidirectional(model.getLayoutY());
		intiTischplatte(model);
		initTischbeine(model);
	}

	private void intiTischplatte(Tisch model){
		this.tischplatte.widthProperty().bindBidirectional(model.getTischplatte()[0]);   	//X
		this.tischplatte.heightProperty().bindBidirectional(model.getTischplatte()[2]);  	//Y
		this.tischplatte.depthProperty().bindBidirectional(model.getTischplatte()[1]);  	//Z
		this.tischplatte.layoutXProperty().bindBidirectional(model.getTischplatte()[3]); 	//LayoutX
		this.tischplatte.layoutYProperty().bindBidirectional(model.getTischplatte()[5]);  	//LayoutY
		this.tischplatte.translateZProperty().bindBidirectional(model.getTischplatte()[4]); //LayoutZ
		this.root.getChildren().add(tischplatte);
	}
	
	private void initTischbeine(Tisch model){
		AnchorPane tischBeinPane = new AnchorPane();
		tischBeinPane.prefHeightProperty().bindBidirectional(tischplatte.heightProperty());
		tischBeinPane.prefWidthProperty().bindBidirectional(tischplatte.widthProperty());
//		tischBeinPane.setRotationAxis(Rotate.X_AXIS);
//		tischBeinPane.setRotate(-90);
//		
		for (int i = 0; tischBeine.length > i ; i++){ 
			// Property zuordnen
//			tischBeine[i].setRotationAxis(Rotate.X_AXIS);
//			tischBeine[i].setRotate(-90);
			tischBeine[i] = new Box();
			tischBeine[i].widthProperty().bindBidirectional(model.getTischBeineSize()[i][0]);	   //X
			tischBeine[i].heightProperty().bindBidirectional(model.getTischBeineSize()[i][2]);	   //Y
			tischBeine[i].depthProperty().bindBidirectional(model.getTischBeineSize()[i][1]);	   //Z
			tischBeine[i].layoutXProperty().bindBidirectional(model.getTischBeineSize()[i][3]);	   //LayoutX
			tischBeine[i].layoutYProperty().bindBidirectional(model.getTischBeineSize()[i][5]);	   //LayoutY
			tischBeine[i].translateZProperty().bindBidirectional(model.getTischBeineSize()[i][4]); //LayoutZ
			tischBeinPane.getChildren().add(tischBeine[i]);
		}	
//			HBox tischBeineLinks = new HBox();
//			tischBeineLinks.getChildren().addAll(tischBeine[0], tischBeine[1]);
//			tischBeineLinks.setRotationAxis(Rotate.Y_AXIS);
//			tischBeineLinks.setRotate(90);
//			HBox tischBeineRechts = new HBox();
//			tischBeineRechts.setRotationAxis(Rotate.Y_AXIS);
//			tischBeineRechts.setRotate(90);
//			tischBeineRechts.getChildren().addAll(tischBeine[2], tischBeine[3]);
//			HBox alleTischBeine = new HBox(tischBeineLinks, tischBeineRechts);
			
			this.root.getChildren().add(tischBeinPane);
	}
	
	


	public synchronized Box[] getTischBeine() {
		return tischBeine;
	}
	public synchronized void setTischBeine(Box[] tischBeine) {
		this.tischBeine = tischBeine;
	}
}
