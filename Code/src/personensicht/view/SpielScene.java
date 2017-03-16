package personensicht.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import personensicht.crt.spielablauf.Spielcontroller;
import personensicht.crt.viewCrt.SpieloberflaecheCrt;
import personensicht.model.welt.Spielzustand;
import personensicht.view.inventar.InventarView;
import personensicht.view.spieler.PersoenlicheSpielerInfoView;
import personensicht.view.spieler.SpielerInformationenView;
import personensicht.view.welt.Navigation;
import personensicht.view.welt.Umgebunsbeschreibung;

/**
 * 
 * @author Dennis
 * @version Alpha 1.0
 *
 */
public class SpielScene 
{
	/**
	 * Scene der Spielwelt
	 */
	private Scene scene;
	
	/**
	 * Root der Scene. Vom BoarderPane wird die Mitte, die linke Seite und die untere Seite verwendet.
	 * Auf der Linkenseite befinden sich Informationen, so wie einige Buttons. In der Mitte befindet sich einige Elemente 
	 * die das Spielgeschehen darstellen. 
	 * Unten befinden sich weiter Informationen ueber das Spielgeschehen
	 * 
	 */
	private BorderPane root = new BorderPane();
	private VBox linkeSeite = new VBox();
		
	public SpielScene()
	{		
		linkeSeite.setAlignment(Pos.TOP_CENTER);
		linkeSeite.setSpacing(60);
		linkeSeite.setStyle("-fx-background-color: BLUE");
		linkeSeite.getChildren().addAll(new SpielerInformationenView(), new SteuerungDesZentrumsV());
		root.setLeft(linkeSeite);	
		scene = new Scene(root);
	}
	
	public Scene getScene()
	{
		return scene;
	}	

	/**
	 * setzt ein Feld auf die Mitte
	 */
	public void setMittelfeld(Node node)
	{
		this.root.setCenter(node);
	}
	
	/**
	 * Beinhaltet Buttons, die das Zentrum des Boarderpans der Scene unterschiedliche Nods zuteilt
	 * Dieses Element ist an der Linken Seite angeordnet
	 * 
	 * @author Dennis
	 * @version 1.0 Alpha
	 * 
	 * Dieser Klasse ist veraltet und wird spaeter geloescht. 
	 */
	public class SteuerungDesZentrumsV extends VBox
	{
		public SteuerungDesZentrumsV()
		{
			Button ortsbeschreibung = new Button("Der Ort");
			ortsbeschreibung.setOnAction(new EventHandler<ActionEvent>() { // veraltet
				@Override
				public void handle(ActionEvent event) {
//					System.out.println(Spielzustand.getOrt()); veraltet
//					SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(new OrtBeschreibung(Spielzustand.getOrt().getBeschreibung()));
				}
			});
			Button inDerUmgebung = new Button("Die Umgebung");
			inDerUmgebung.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(new Umgebunsbeschreibung(Spielzustand.getOrt()));
					
				}
			});
			Button navigation = new Button("Navigation");
			navigation.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(new Navigation(Spielzustand.getOrt()));		
				}
			});
			Button inventar = new Button("Inventar");
			inventar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(new InventarView(Spielcontroller.getInstance().getSpieler().getInventar()));
				}
			});
			Button spielerInfos = new Button("Persönliches");
			spielerInfos.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					SpieloberflaecheCrt.getInstanceOfViewSpieloberflaeche().setMittelfeld(new PersoenlicheSpielerInfoView(Spielcontroller.getInstance().getSpieler()));
				}
			});
			
			
			Font font = new Font(10);
			ortsbeschreibung.setFont(font);
			inDerUmgebung.setFont(font);
			navigation.setFont(font);
			inventar.setFont(font);
			spielerInfos.setFont(font);
				
			ortsbeschreibung.setPrefWidth(140);
			inDerUmgebung.setPrefWidth(140);
			navigation.setPrefWidth(140);
			inventar.setPrefWidth(140);
			spielerInfos.setPrefWidth(140);
			
			this.getChildren().addAll(ortsbeschreibung,inDerUmgebung,inventar,spielerInfos,navigation);
			this.setEffect(new DropShadow());
			this.setAlignment(Pos.TOP_CENTER);
		}
	}

}
