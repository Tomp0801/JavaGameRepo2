package personensicht.view.gameObjekte.lebewesen;

import java.awt.MouseInfo;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import personensicht.model.spieler.Spieler;
import personensicht.view.Shape3DZusatzMethoden;

public class Mensch3D
{
	private VBox root = new VBox();
	private Sphere kopf;
	private Box koerper;
	private Box linkerArm;
	private Box rechterArm;
	private Box linkesBein;
	private Box rechtesBein;
		
	public Mensch3D()
	{
		kopf = new Sphere();
		kopf.setRadius(40);
		root.getChildren().add(kopf);
		koerper = new Box();
		koerper.setHeight(200);
		koerper.setWidth(90);
		koerper.setDepth(80);
		linkerArm = new Box();
		linkerArm.setWidth(18);
		linkerArm.setHeight(160);
		linkerArm.setDepth(18);
		linkerArm.setRotate(45);
		rechterArm = new Box();
		rechterArm.setWidth(18);
		rechterArm.setHeight(160);
		rechterArm.setDepth(18);
		rechterArm.setRotate(-45);	
		HBox mitte = new HBox();
		mitte.setSpacing(60);
		mitte.getChildren().addAll(linkerArm,koerper, rechterArm);
		mitte.setAlignment(Pos.TOP_CENTER);
		root.getChildren().add(mitte);
		linkesBein = new Box();
		linkesBein.setHeight(200);
		linkesBein.setDepth(40);
		linkesBein.setWidth(18);
		rechtesBein = new Box();
		rechtesBein.setDepth(18);
		rechtesBein.setHeight(200);
		rechtesBein.setWidth(18);
		HBox beine = new HBox();
		beine.setSpacing(20);
		beine.setAlignment(Pos.TOP_CENTER);
		beine.getChildren().addAll(linkesBein,rechtesBein);
		root.getChildren().add(beine);
		root.setSpacing(6);
		root.setAlignment(Pos.CENTER);
		initMenschRotation();
	}
	
	public Mensch3D(Spieler spieler)
	{
		this();
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.kopf, spieler.getAussehen().getHautfarbe());
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.koerper, spieler.getAussehen().getKlamotte()[2].getColor());
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.linkerArm, spieler.getAussehen().getKlamotte()[2].getColor());
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.linkesBein, spieler.getAussehen().getKlamotte()[3].getColor());
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.rechterArm, spieler.getAussehen().getKlamotte()[2].getColor());
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.rechtesBein, spieler.getAussehen().getKlamotte()[3].getColor());
	}

	public void initMenschRotation()
	{
		root.setRotationAxis( Rotate.Y_AXIS);        	
		root.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
        	double mouseOldX = 0; 
        	double mousePosX = 0;
        	double mouseDeltaX = 0;
        	
			@Override
			public void handle(MouseEvent event) 
			{
	        	mouseOldX = mousePosX;
                mousePosX = MouseInfo.getPointerInfo().getLocation().getX();
                mouseDeltaX = mousePosX - mouseOldX;    
                if (mouseDeltaX >= 8 || mouseDeltaX <= -8)
                {
                	mouseDeltaX = 0;
                }
                root.setRotate(root.getRotate()+mouseDeltaX);
			}
		});
	}
	
	public VBox getMensch()
	{
		return root;
	}

	public synchronized VBox getRoot() {
		return root;
	}

	public synchronized Sphere getKopf() {
		return kopf;
	}

	public synchronized Box getKoerper() {
		return koerper;
	}

	public synchronized Box getLinkerArm() {
		return linkerArm;
	}

	public synchronized Box getRechterArm() {
		return rechterArm;
	}

	public synchronized Box getLinkesBein() {
		return linkesBein;
	}

	public synchronized Box getRechtesBein() {
		return rechtesBein;
	}
}
