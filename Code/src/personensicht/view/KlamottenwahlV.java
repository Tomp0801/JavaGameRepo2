package personensicht.view;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape3D;
import javafx.scene.text.Font;
import javafx.util.converter.NumberStringConverter;
import personensicht.model.spieler.klamotten.Hose;
import personensicht.model.spieler.klamotten.Hut;
import personensicht.model.spieler.klamotten.Jacke;
import personensicht.model.spieler.klamotten.Klamotte;
import personensicht.model.spieler.klamotten.Schuhe;
import personensicht.model.spieler.klamotten.TShirt;
import personensicht.view.gameObjekte.lebewesen.MenschV;

/**
 * beinhaltet eine View, mit der sich Klamotten wie Hose, Jacke ect. bearbeiten lassen.
 * Farbe aendern und Qualitat aendern
 * Wird verwendet von der SpielfigurErstellenScene
 * koennte eventuell von weiteren Klassen benutzt werden
 * 
 * @author Dennis
 * @version Alpha 1.0
 *
 */
public class KlamottenwahlV extends VBox
{
	private static SimpleIntegerProperty maxGeld = new SimpleIntegerProperty(100); 
	private Hose hose = new Hose("Hose"); 
	private TShirt tshirt = new TShirt("T-Shirt");
	private Jacke jacke = new Jacke("Jacke");
	private Schuhe schuhe = new Schuhe("Schuhe");
	private Hut hut = new Hut("Hut");
	private TabPane root0 = new TabPane();
	
	public KlamottenwahlV(MenschV mensch)
	{
		Tab hut = new Tab("Hut"); 
		hut.setClosable(false);
		hut.setContent(initEinstellungen(this.hut));
		Tab oberteil = new Tab("Oberteil");
		oberteil.setClosable(false);
		oberteil.setContent(initEinstellungen(this.tshirt));
		
		Tab jacke = new Tab("Jacke"); 
		jacke.setClosable(false);
		ArrayList<Shape3D> list  = new ArrayList<Shape3D>();
		list.add(mensch.getKoerper());
		list.add(mensch.getRechterArm());
		list.add(mensch.getLinkerArm());
//		jacke.setContent(farbWahlVonShape3D(list, this.jacke));
//		jacke.setContent(initEinstellungen(this.jacke));
		VBox box2 = new VBox();
		box2.getChildren().addAll(Shape3DZusatzMethoden.farbWahlVonShape3D(list,this.jacke),initEinstellungen(this.jacke));
		box2.setSpacing(20);
		box2.setAlignment(Pos.TOP_CENTER);
		jacke.setContent(box2);
		
		Tab hose = new Tab("Hose"); 
		hose.setClosable(false);
		list  = new ArrayList<Shape3D>();
		list.add(mensch.getRechtesBein());
		list.add(mensch.getLinkesBein());
		VBox box = new VBox();
		box.getChildren().addAll(Shape3DZusatzMethoden.farbWahlVonShape3D(list,this.hose),initEinstellungen(this.hose));
		box.setSpacing(20);
		box.setAlignment(Pos.TOP_CENTER);
		hose.setContent(box);
		Tab schuhe = new Tab("Schuhe"); 
		schuhe.setClosable(false);
		schuhe.setContent(initEinstellungen(this.schuhe));
		root0.getTabs().addAll(hut, oberteil, jacke, hose, schuhe);
		Label geldAnzeige = new Label("Geld ");
		Bindings.bindBidirectional(geldAnzeige.textProperty(), maxGeld , new NumberStringConverter());
		geldAnzeige.setTextFill(Color.WHITE);
		geldAnzeige.setFont(new Font(18));
		this.setSpacing(10);
		this.setAlignment(Pos.TOP_CENTER);
		this.getChildren().addAll(geldAnzeige,root0);
	}

	public synchronized Hose getHose() {
		return hose;
	}

	public synchronized TShirt getTshirt() {
		return tshirt;
	}

	public synchronized Jacke getJacke() {
		return jacke;
	}

	public synchronized Schuhe getSchuhe() {
		return schuhe;
	}

	public synchronized Hut getHut() {
		return hut;
	}

	private static VBox initEinstellungen(Klamotte klamotte)
	{
		HBox eigenschaftenBox[] = new HBox[5]; 
		ProgressBar eigenschaftenWerteAnzeige[] = new ProgressBar[5]; 
		
		VBox eistellungMitLabel[] = new VBox[5];
		for (int i = 0; eistellungMitLabel.length > i ; i++)
		{
			eistellungMitLabel[i] = new VBox();
		}
		Label schmuck = new Label("Schmuck");
		schmuck.setTextFill(Color.WHITE);
		Label bemalung = new Label("Kriegsausrüstung");
		bemalung.setTextFill(Color.WHITE);
		Label feinheit = new Label("Feinheiten");
		feinheit.setTextFill(Color.WHITE);
		Label sauber = new Label("Sauberkeit");
		sauber.setTextFill(Color.WHITE);
		Label neu = new Label("Neu");
		neu.setTextFill(Color.WHITE);
		eistellungMitLabel[0].getChildren().add(schmuck);
		eistellungMitLabel[1].getChildren().add(bemalung);
		eistellungMitLabel[2].getChildren().add(feinheit);
		eistellungMitLabel[3].getChildren().add(sauber);
		eistellungMitLabel[4].getChildren().add(neu);
		
		VBox root = new VBox();
		for (int i = 0; eigenschaftenBox.length > i ; i++)
		{
			eigenschaftenWerteAnzeige[i] = new ProgressBar();
			eigenschaftenWerteAnzeige[i].setProgress(0);
			eigenschaftenBox[i] = new HBox();
			Button positiv = new Button("+");
			int r = i; 
			positiv.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					if (maxGeld.get()-1 >= 0)
					{
						if (eigenschaftenWerteAnzeige[r].getProgress() < 1)
						{
							eigenschaftenWerteAnzeige[r].setProgress( (eigenschaftenWerteAnzeige[r].getProgress()+0.1) );
							maxGeld.set(maxGeld.get()-1);
						}
					}
				}
			});
			Button negativ = new Button("-");
			negativ.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if (maxGeld.get()+1 < 100)
					{
						if (eigenschaftenWerteAnzeige[r].getProgress() > 0)
						{
							eigenschaftenWerteAnzeige[r].setProgress( (eigenschaftenWerteAnzeige[r].getProgress()-0.1) );
							maxGeld.set(maxGeld.get()+1);
						}
					}	
				}
			});
			
			eigenschaftenBox[i].getChildren().addAll(negativ,eigenschaftenWerteAnzeige[i],positiv); 
			eistellungMitLabel[i].getChildren().add(eigenschaftenBox[i]);
			root.getChildren().add(eistellungMitLabel[i]);
		}
		return root; 
	}
}
