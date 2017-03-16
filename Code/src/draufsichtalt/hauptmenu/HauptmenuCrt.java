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
 * Das Startmenu. Dies ist das erste was der Spiele zusehen bekommt.
 * 
 * @author Dennis
 *
 */
public class HauptmenuCrt implements Runnable //, Initializable
{
//	/**
//	 * drehende Erdkugel 
//	 */
//	@FXML
//	private Sphere erdkugel; 
//		
//	/**
//	 * Hintergrund der Scene
//	 */
//	@FXML
//	private Canvas hintergrund; 
//	
//	/**
//	 * Eine Box mit MenuItems
//	 */
//	@FXML
//	private VBox startMenuBox;
//	
//	/**
//	 * Ein Button um ein Einzelspielerspiel zustarten
//	 */
//	@FXML
//	private Button einzelspielerButton;
//	
//	/**
//	 * Ein Button um ein Mehrspielerspiel zu starten
//	 */
//	@FXML
//	private Button mehrspielerButton;
//	
//	/**
//	 * ein Button um die Einstellungen zu oeffnen
//	 */
//	@FXML
//	private Button einstellungenButton;
//	
//	/**
//	 * Ein Buttun um ein Zusatzmenu zu oeffnen
//	 */
//	@FXML
//	private Button mehrButton;
//	
//	/**
//	 * Ein Button um das Spiel zu beenden
//	 */
//	@FXML
//	private Button endeButton;
//	
//	@FXML
//	private StackPane root;
	
	
	private Thread thread;	
	
	/**
	 * instance vom Hauptmenu
	 */
	private static HauptmenuCrt instance;
	
	/**
	 * Die View vom Startmenu
	 */
	private static Startmenu startmenu = new Startmenu(); 
	
	private HauptmenuCrt(){
		instance = this;
	}
	
	public static HauptmenuCrt getInstance(){
		if (instance == null){
			new HauptmenuCrt();
		}
		return instance; 
	}
	
	
//	/**
//	 * initialisiert den Controller
//	 */
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) 
//	{
		//Musik startet
//		Musikspieler musik = Musikspieler.getInstance();
//		musik.starteMusik();
//
//		//Bild der Erde wird auf der Kugel gelegt
//        Image image = new Image("/draufsicht/view/hauptmenu/Erde.jpg"); 
//        PhongMaterial material1 = new PhongMaterial();
//        material1.setDiffuseMap(image);
//        erdkugel.setMaterial(material1);
//    
//        //schwarzer Hintergrund wird erstellt
//        hintergrund.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
//        hintergrund.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());  
//		GraphicsContext graphic = hintergrund.getGraphicsContext2D();
//		graphic.setFill(Color.BLACK);
//		graphic.fillRect(0, 0, hintergrund.getWidth(), hintergrund.getHeight());	
		
		
//		thread = new Thread(this);
//		thread.setDaemon(true);
//		thread.start();		
//	}

	
	public void start(){
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();	
	}
	
	/**
	 * das Drehen der Erdkugel
	 */
	@Override
	public void run() 
	{
//		int radius=0;
//		boolean zoom = true;
//		long rotation = 0; 
//		erdkugel.setRotationAxis(Rotate.Y_AXIS);
//		while(true)
//		{
//			try 
//			{
//				Thread.sleep(80);
//				if (zoom == true)
//				{
//					erdkugel.setRadius(radius);
//					radius++;
//				}
//				
//				erdkugel.setRotate(rotation);
//				rotation++; 
//				
//				//ist das maximale Wert von der Variable rotation erreiccht, dann setze rotation auf 0
//				if (rotation == Long.MAX_VALUE)
//					rotation = 0; 
//				
//				
//			} catch (InterruptedException e) 
//			{
//				
//			}
//			
//			if (radius >= 200)
//			{
//				zoom = false;;
//			}
//		}		
		this.startmenu.run(thread);
	}
	
	public void actionButton(ActionEvent e)
	{
		if (e.getSource() == startmenu.getEinzelspielerButton())
		{
			this.actionButtonEinzelspieler();
		}
		else if (e.getSource() == startmenu.getMehrspielerButton())
		{
			this.actionButtonMehrspieler();
		}
		else if (e.getSource() == startmenu.getEinstellungenButton())
		{
			this.actionButtonEinstellungen();
		}
		else if (e.getSource() == startmenu.getMehrButton())
		{
			this.actionButtonMehr();
		}
		else if (e.getSource() == startmenu.getEndeButton())
		{
			this.actionButtonEnde();
		}
	}
	
	public void actionButtonEinzelspieler(){
		System.out.println("Einzelspielerfunktion noch nicht Vorhanden");
		System.out.println("Demo spiel wird gestartet");
		//der Thread mit der sich die Erdkugel dreht wird hier beendet solte beachtet werden..
		this.thread.interrupt();
		GameManager.getInstance().starteSpiel(Options.worldSeed);
	}
	public void actionButtonMehrspieler(){	
		//erstellt eine neue Scene
		StageController.getInstance().setScene(new FXMLLoader(getClass().getResource("fxml/NetzwerkspielEingangsraum.fxml")));
	}
	public void actionButtonEinstellungen(){	
		System.out.println("Einstellungen noch nicht Vorhanden");
	}
	public void actionButtonMehr(){	
		System.out.println("Mehrfunktion noch nicht Vorhanden");
	}
	public void actionButtonEnde(){	
		System.out.println("Spiel wurde beendet");
		System.exit(-1);
	}
	
//	/**
//	 * verwaltet die ActionEvents der Buttons 
//	 */
//	@FXML
//	public void actionButton(ActionEvent e)
//	{
//		if (e.getSource() == einzelspielerButton)
//		{
////			System.out.println("Einzelspielerfunktion noch nicht Vorhanden");
////			System.out.println("Demo spiel wird gestartet");
////			//der Thread mit der sich die Erdkugel dreht wird hier beendet solte beachtet werden..
////			this.thread.interrupt();
////			GameManager.getInstance().starteSpiel(Options.worldSeed);
//		}
//		
////		else if (e.getSource() == mehrspielerButton)
////		{
////			//erstellt eine neue Scene
////			StageController.getInstance().setScene(new FXMLLoader(getClass().getResource("fxml/NetzwerkspielEingangsraum.fxml")));
////		}
////		else if (e.getSource() == einstellungenButton)
////		{
////			System.out.println("Einstellungen noch nicht Vorhanden");
////		}
////		else if (e.getSource() == mehrButton)
////		{
////			System.out.println("Mehrfunktion noch nicht Vorhanden");
////		}
////		else if (e.getSource() == endeButton)
////		{
////			System.out.println("Spiel wurde beendet");
////			System.exit(-1);
////		}
//	}

	public static synchronized Startmenu getStartmenu() {
		return startmenu;
	}

	public static synchronized void setStartmenu(Startmenu startmenu) {
		HauptmenuCrt.startmenu = startmenu;
	}
}
