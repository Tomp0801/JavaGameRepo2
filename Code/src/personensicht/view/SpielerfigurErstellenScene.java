package personensicht.view;



import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import personensicht.crt.spielablauf.Spielcontroller;
import personensicht.model.spieler.Aussehen;
import personensicht.model.spieler.Geschlecht;
import personensicht.model.spieler.Spieler;
import personensicht.model.spieler.klamotten.Klamotte;
import personensicht.view.gameObjekte.lebewesen.MenschV;

/**
 * Diese Scene, wird zum Erstellen der Spielfigur verwendet.
 * 
 * @author Dennis
 * @version alpha 1.0
 *
 */
public class SpielerfigurErstellenScene 
{
	/**
	 * verwendete Scene
	 */
	private Scene scene;
	
	/**
	 * verwendete Root der Scene
	 */
	private StackPane root = new StackPane();;
	
	/**
	 * erstellt die Scene
	 */
	public SpielerfigurErstellenScene()
	{ 	
		
		
		HBox namensEingabe = new HBox(); 
		TextField namensFeld = new TextField("Name");
		namensFeld.setStyle("-fx-background-color:  transparent");
		Label eingabeName = new Label("Gebe dein Name ein"); 
		eingabeName.setOpacity(0.8);
		eingabeName.setTextFill(Color.WHITE);
		Font font1 = new Font("Verdana", 70);
		eingabeName.setFont(font1);
		Font font2 = new Font("Verdana", 30);
		namensFeld.setFont(font2);
		namensEingabe.getChildren().addAll(eingabeName, namensFeld);
		namensEingabe.setSpacing(10);
		namensEingabe.setAlignment(Pos.CENTER);
		
		HBox namesEingabeBox = new HBox(namensEingabe); 
		namesEingabeBox.setSpacing(200);	
		namesEingabeBox.setAlignment(Pos.TOP_CENTER);
		
		Image img = new Image(getClass().getResource("weltraum.jpg").toExternalForm());
        ImageView hintergrund = new ImageView(img);
        hintergrund.setScaleX(2);
        hintergrund.setScaleY(2);
        hintergrund.setTranslateZ(0);
        
        VBox geschlechterBox = new VBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton maenlich = new RadioButton("mänlich");
        maenlich.setTextFill(Color.WHITE);
        RadioButton weiblich = new RadioButton("weiblich");
        weiblich.setTextFill(Color.WHITE);
        maenlich.setToggleGroup(group);
        weiblich.setToggleGroup(group);
        geschlechterBox.setAlignment(Pos.TOP_CENTER);
        geschlechterBox.setSpacing(6);
        geschlechterBox.getChildren().addAll(maenlich,weiblich);

		Label großeBezeichnung = new Label("Körpergröße: ");
		Label großeAnzeige = new Label("1.80 ");
		großeAnzeige.setTextFill(Color.WHITE);
		großeBezeichnung.setTextFill(Color.WHITE);
		HBox labelBox = new HBox();
		labelBox.getChildren().addAll(großeBezeichnung,großeAnzeige);
		labelBox.setSpacing(2);
		labelBox.setAlignment(Pos.TOP_CENTER);
		TextField eingabe = new TextField("1.80");
		eingabe.setAlignment(Pos.CENTER);
		eingabe.setMaxWidth(100);
		Button bestaetigen = new Button("Bestätigen");
		bestaetigen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String wert = eingabe.getText();
				wert = wert.replace(",", ".");
				wert = wert.trim();
				try 
				{
					double i = Double.valueOf(wert);
					if (i > 2.5 || i < 0.5)
						i = 1.8;
					großeAnzeige.setText(""+i);
				} catch (NumberFormatException e) 
				{
					eingabe.setText("1.80");
					großeAnzeige.setText("1.80");
				}				
			}
		});
		VBox koerperGroße = new VBox();
		koerperGroße.getChildren().addAll(labelBox,eingabe,bestaetigen);
		koerperGroße.setSpacing(12);
		koerperGroße.setAlignment(Pos.TOP_CENTER);
		
        MenschV mensch = new MenschV();
		Button spielStart = new Button("starte Spiel");
		spielStart.setPrefSize(200, 60);
		VBox hautFarbenBox = new VBox();
		Label hautfarbe = new Label("Hautfarbe");
		hautfarbe.setTextFill(Color.WHITE);
		Shape3DZusatzMethoden.hintergundFarbeSetzen(mensch.getKopf(), Color.WHITE);
		Slider blauTon = new Slider();
		Slider rotTon = new Slider();
		Slider gruenTon = new Slider();	
		blauTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				Shape3DZusatzMethoden.hintergundFarbeSetzen(mensch.getKopf(),new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1) );
			}
		});
		rotTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				 Shape3DZusatzMethoden.hintergundFarbeSetzen(mensch.getKopf(),new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1) );
			}
		});
		gruenTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				 Shape3DZusatzMethoden.hintergundFarbeSetzen(mensch.getKopf(),new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1) );
			}
		});
		
		VBox farbBox = new VBox();
		farbBox.getChildren().addAll(blauTon, rotTon, gruenTon);
		hautFarbenBox.getChildren().addAll(hautfarbe , farbBox);
		hautFarbenBox.setSpacing(10);
		VBox rechteBox = new VBox();
		rechteBox.setAlignment(Pos.TOP_CENTER);
		rechteBox.setSpacing(40);
		rechteBox.getChildren().addAll(hautFarbenBox, koerperGroße, geschlechterBox, spielStart);
		
		KlamottenwahlV klamottenwahl = new KlamottenwahlV(mensch);	
		klamottenwahl.setMinWidth(250);
		klamottenwahl.setMaxWidth(250);
		rechteBox.setMinWidth(250);
		rechteBox.setMaxWidth(250);
		BorderPane boarderPane = new BorderPane();
	
		SubScene subScene = new SubScene(mensch.getMensch(), 500, 500, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.TRANSPARENT);
		boarderPane.setCenter(subScene);
		boarderPane.setLeft(klamottenwahl);
		boarderPane.setRight(rechteBox);	
		
		VBox frontNods = new VBox(namesEingabeBox, boarderPane);
		frontNods.setAlignment(Pos.TOP_CENTER);
		frontNods.setSpacing(60);
		
		root.getChildren().addAll(hintergrund, frontNods);
		scene = new Scene(root); 
		
		
		spielStart.setOnAction(new EventHandler<ActionEvent>() 
		{	
			@Override
			public void handle(ActionEvent event) 
			{
				String name = "default";
				if (namensFeld.getText().isEmpty() == false)					
				{
					name = namensFeld.getText();
				}
				Geschlecht geschlecht;
				if (maenlich.isSelected())
				{
					geschlecht = Geschlecht.maenlisch;
				}
				else
				{
					geschlecht = Geschlecht.weiblich;
				}
				
				Klamotte klamotte[] = new Klamotte[5];
				klamotte[0] = klamottenwahl.getHut();
				klamotte[1] = klamottenwahl.getTshirt();
				klamotte[2] = klamottenwahl.getJacke();
				klamotte[3] = klamottenwahl.getHose();
				klamotte[4] = klamottenwahl.getSchuhe();
				Aussehen aussehen = new Aussehen(Double.valueOf(großeAnzeige.getText()), new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1) ,klamotte);
				Spieler spieler = new Spieler(name,aussehen,geschlecht,18);
				Spielcontroller.getInstance().spielStarten(spieler);
			}
		});		
	}
	
	public Scene getScene()
	{
		return scene;
	}
}
