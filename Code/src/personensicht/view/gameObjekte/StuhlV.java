package personensicht.view.gameObjekte;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import personensicht.model.gameObjekte.Stuhl;

public class StuhlV extends GameObjektV
{
	
	public final static int MAXSIZE_X = 200; 
	public final static int MAXSIZE_Y = 200; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 	
	
	private Box lehne = new Box();
	private VBox root = new VBox(); 
	private Box sitz = new Box(); 
	private Box[] beine = new Box[4];
			
	public StuhlV(Stuhl model)
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
		initLehne(model);
		initStuhl(model);
		initBeine(model);
	}

	private void initLehne(Stuhl model){
		//erstellt die Node
		//passt Size an Model an
		this.lehne.widthProperty().bindBidirectional(model.getSitzSize()[0]);   	//X
		this.lehne.heightProperty().bindBidirectional(model.getSitzSize()[2]);  	//Y
		this.lehne.depthProperty().bindBidirectional(model.getSitzSize()[1]);  	    //Z
		this.lehne.layoutXProperty().bindBidirectional(model.getSitzSize()[3]); 	//LayoutX
		this.lehne.layoutYProperty().bindBidirectional(model.getSitzSize()[5]);  	//LayoutY
		this.lehne.translateZProperty().bindBidirectional(model.getSitzSize()[4]);  //LayoutZ
		this.root.getChildren().add(lehne);
		//ordent die Node am root an.
		this.root.getChildren().add(lehne);
	}
		
	private void initStuhl(Stuhl model){
		this.sitz.widthProperty().bindBidirectional(model.getSitzSize()[0]);   	//X
		this.sitz.heightProperty().bindBidirectional(model.getSitzSize()[2]);  	//Y
		this.sitz.depthProperty().bindBidirectional(model.getSitzSize()[1]);  	    //Z
		this.sitz.layoutXProperty().bindBidirectional(model.getSitzSize()[3]); 	//LayoutX
		this.sitz.layoutYProperty().bindBidirectional(model.getSitzSize()[5]);  	//LayoutY
		this.sitz.translateZProperty().bindBidirectional(model.getSitzSize()[4]);  //LayoutZ
		this.root.getChildren().add(sitz);
	}
	
	private void initBeine(Stuhl model){
		AnchorPane tischBeinPane = new AnchorPane();
		tischBeinPane.prefHeightProperty().bindBidirectional(sitz.heightProperty());
		tischBeinPane.prefWidthProperty().bindBidirectional(sitz.widthProperty());
		
		for (int i = 0; beine.length > i ; i++){ 
			// Property zuordnen
			beine[i] = new Box();
			beine[i].widthProperty().bindBidirectional(model.getStuhlBeineSize()[i][0]);	   //X
			beine[i].heightProperty().bindBidirectional(model.getStuhlBeineSize()[i][2]);	   //Y
			beine[i].depthProperty().bindBidirectional(model.getStuhlBeineSize()[i][1]);	   //Z
			beine[i].layoutXProperty().bindBidirectional(model.getStuhlBeineSize()[i][3]);	   //LayoutX
			beine[i].layoutYProperty().bindBidirectional(model.getStuhlBeineSize()[i][5]);	   //LayoutY
			beine[i].translateZProperty().bindBidirectional(model.getStuhlBeineSize()[i][4]); //LayoutZ
			tischBeinPane.getChildren().add(beine[i]);
		}				
			this.root.getChildren().add(tischBeinPane);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public StuhlV(Stuhl model){
//		this.setNode(this.root);
////		this.root.heightProperty().bindBidirectional(model.getHeight());
////		this.root.widthProperty().bindBidirectional(model.getWidth());
//////		this.root.depthProperty().bindBidirectional(model.getDepth());
////		this.root.layoutXProperty().bindBidirectional(model.getLayoutX());
////		this.root.layoutYProperty().bindBidirectional(model.getLayoutY());
//		
//		//erstellt eine Lehne
//		initLehne(model);
//		initStuhl(model);
//		initBeine(model);
//	}
//
//	private void initLehne(Stuhl model){
//		//erstellt die Node
//		//passt Size an Model an
//		//TODO
//		//ordent die Node am root an.
//		this.root.getChildren().add(lehne);
//	}
//	
//	private void initStuhl(Stuhl model){
//		//erstelle Node		
//		//passt die BoxSize dem Model an	
//		//TODO
//		//erstellt die Beine
//	}
//	
//	private void initBeine(Stuhl model){
//	
//		//erstellt die Nods
//		AnchorPane stuhlAnchorPane = new AnchorPane();
//		for (int i = 0; 4 > i ; i++){
//			beine[i] = new Box();
//			//Size dem Model anpassen
//			//TODO
//			//setzte das Bein auf das AnchorPane
//			stuhlAnchorPane.getChildren().add(beine[i]);
//		}
//		
//		//ordne das AnchorPane unter dem sitzt an.
//		this.root.getChildren().add(stuhlAnchorPane);
//	}
}
