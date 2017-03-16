package draufsicht.view.hauptmenu;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

import draufsicht.audio.Musikspieler;
import draufsicht.controller.GameManager;
import draufsicht.controller.StageController;
import draufsicht.global.Options;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/**
 * 12.03.17 Versuch einen Fehler zu beheben, indem die fxml datei in code geschrieben wird. 
 * 
 * @author Dennis
 *
 */
public class Startmenu {
	/**
	 * drehende Erdkugel 
	 */
	private Sphere erdkugel = new Sphere(); 
		
	/**
	 * Hintergrund der Scene
	 */
	private Canvas hintergrund = new Canvas(); 
	
	private StackPane root = new StackPane();
	
	private Scene scene; 
	
	private Button einzelspielerButton; 
	private Button mehrspielerButton;
	private Button einstellungenButton;
	private Button mehrButton;
	private Button endeButton;
	
	public Startmenu() {
		//Box mit Buttons 
		VBox startMenuBox = new VBox();
		startMenuBox.setAlignment(Pos.CENTER_LEFT);
		startMenuBox.setSpacing(20);
		startMenuBox.setTranslateZ(-10);
//		startMenuBox.setPickOnBounds(true);
		startMenuBox.setStyle("-fx-background-color: TRANSPARENT");
		// Ein Button um ein Einzelspielerspiel zustarten
		einzelspielerButton = new Button("Einzelspieler ");
		einzelspielerButton.setPrefSize(200, 40);
		einzelspielerButton.setOnAction(e -> HauptmenuCrt.getInstance().actionButton(e));
		 // Ein Button um ein Mehrspielerspiel zu starten
		mehrspielerButton = new Button("Mehrspieler");
		mehrspielerButton.setPrefSize(200, 40);
		mehrspielerButton.setOnAction(e -> HauptmenuCrt.getInstance().actionButton(e));
		 // ein Button um die Einstellungen zu oeffnen
		einstellungenButton = new Button("Einstellungen");
		einstellungenButton.setPrefSize(200, 40);
		einstellungenButton.setOnAction(e -> HauptmenuCrt.getInstance().actionButton(e));
		// Ein Buttun um ein Zusatzmenu zu oeffnen
		mehrButton = new Button("mehr");
		mehrButton.setPrefSize(200, 40);
		mehrButton.setOnAction(e -> HauptmenuCrt.getInstance().actionButton(e));
		// Ein Button um das Spiel zu beenden
		endeButton = new Button("Beenden");
		endeButton.setPrefSize(200, 40);
		endeButton.setOnAction(e -> HauptmenuCrt.getInstance().actionButton(e));
		startMenuBox.getChildren().addAll(einzelspielerButton,mehrspielerButton,einstellungenButton,mehrButton,endeButton);
		initErde();
		root.getChildren().addAll(hintergrund, erdkugel, startMenuBox);
		this.scene = new Scene(root); 
	}
	
	private void initErde(){
		Image image = new Image("/draufsicht/view/hauptmenu/Erde.jpg"); 
	    PhongMaterial material1 = new PhongMaterial();
	    material1.setDiffuseMap(image);
	    erdkugel.setMaterial(material1);
	   
	    //schwarzer Hintergrund wird erstellt
	    hintergrund.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	    hintergrund.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());  
	    hintergrund.setTranslateZ(200);
		GraphicsContext graphic = hintergrund.getGraphicsContext2D();
		graphic.setFill(Color.BLACK);
		graphic.fillRect(0, 0, hintergrund.getWidth(), hintergrund.getHeight());
	}

	
	/**
	 * das Drehen der Erdkugel
	 */
	public void run(Thread thread) 
	{
		int radius=0;
		boolean zoom = true;
		long rotation = 0; 
		erdkugel.setRotationAxis(Rotate.Y_AXIS);
		while(thread.isInterrupted() == false)
		{
			try 
			{
				Thread.sleep(80);
				if (zoom == true)
				{
					erdkugel.setRadius(radius);
					radius++;
				}
				
				erdkugel.setRotate(rotation);
				rotation++; 
				
				//ist das maximale Wert von der Variable rotation erreiccht, dann setze rotation auf 0
				if (rotation == Long.MAX_VALUE)
					rotation = 0; 
				
				
			} catch (InterruptedException e) {}
			
			if (radius >= 200)
			{
				zoom = false;;
			}
		}		
	}

	public synchronized Scene getScene() {
		return scene;
	}

	public synchronized Button getEinzelspielerButton() {
		return einzelspielerButton;
	}

	public synchronized Button getMehrspielerButton() {
		return mehrspielerButton;
	}

	public synchronized Button getEinstellungenButton() {
		return einstellungenButton;
	}

	public synchronized Button getMehrButton() {
		return mehrButton;
	}

	public synchronized Button getEndeButton() {
		return endeButton;
	}
}
