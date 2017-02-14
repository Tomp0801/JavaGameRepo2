package draufsicht.view.aufbaumodus;

import java.awt.Toolkit;

import draufsicht.controller.StageController;
import draufsicht.map.Bereich;
import draufsicht.map.Karte;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Eine Karte eines Planeten
 * 
 * @author Dennis
 *
 */
public class PlanetenkarteBereiche extends AufbaumodusSichtweisePlaneten
{
	/**
	 * Eine Konstante die die minimale groeﬂe eines Canvas festlegt, die ein Bereich darstellt. 
	 */
	private final int MINSIZEBEREICH = 50;
			
	/**
	 * diese VBox wird dazu verwendet eine Reihe von HBoxen untereinander anzuordnen. 
	 */
	private VBox spaltenLayoutBox = new VBox();

	/**
	 * ein Array von HBoxen. Diese werden untereinander angeordnet um dort Canvas hineinzulegen, die 
	 * die Bereiche darstelen sollen.
	 */
	private HBox[] hBoxCanvasreihe;
	

	/**
	 * erstellt ein Objekt einer Planetenkarte
	 * @param karte
	 */
	public PlanetenkarteBereiche(Karte karte)
	{				
		//Die groeﬂe der Karte wird festgelgt.
		this.getSubSceneRoot().setPrefSize(karte.getBreite() * MINSIZEBEREICH +10, karte.getHoehe()*MINSIZEBEREICH+10);
		//koennen die Bereiche nicht den ganzen Bildschrim ausfuellen, dann wird die groeﬂe des Bereichs angepasst	
		if (this.getSubSceneRoot().getPrefWidth() < this.getScene().getWidth())
		{
			this.getSubSceneRoot().setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
			this.getSubSceneRoot().setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		}
		
		this.getSubSceneRoot().getChildren().add(spaltenLayoutBox);		
		spaltenLayoutBox.setPrefSize(this.getSubSceneRoot().getPrefWidth(), this.getSubSceneRoot().getPrefHeight());
		
		initKarte(karte);
	}

	
	/**
	 * hier wird die Karte geladen. Das Aussehen wird festgelegt und Canvas in HBoxes verteilt. 
	 * @param karte
	 */
	private void initKarte(Karte karte)
	{
		//setzt eine passende anzahl an HBoxen in eine VBox um diese anschlieﬂend mit Canvas zu fuellen die die Bereiche darstellen
		hBoxCanvasreihe = new HBox[karte.getHoehe()];
		
		for (int i = 0 ; karte.getHoehe() > i ; i++)
		{
			hBoxCanvasreihe[i] = new HBox();
			hBoxCanvasreihe[i].setPrefSize(this.getSubSceneRoot().getPrefWidth(), this.getSubSceneRoot().getPrefHeight());
			this.spaltenLayoutBox.getChildren().add(hBoxCanvasreihe[i]);	
		}
	
		for(int i = 0 ; karte.getHoehe() > i ; i++)
		{
			for (int j = 0; karte.getBreite() > j ; j++ )
			{
				Bereich bereich = karte.getBereich(i, j);
				
				Canvas canvas = new Canvas();
								
				canvas.setHeight( (double) (this.getSubSceneRoot().getPrefWidth()/karte.getBreite()) );
				canvas.setWidth(  (double) (this.getSubSceneRoot().getPrefWidth()/karte.getBreite()) );
				bereich.getAussehen(canvas);
				
				hBoxCanvasreihe[i].setPrefHeight(canvas.getHeight());
				hBoxCanvasreihe[i].getChildren().add(canvas);
				
				Bereich bereichFromCanvas = karte.getBereich(i, j);
				canvas.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent e) 
					{
						if (e.getClickCount() == 1)
						{
							setEffekt(canvas);
							loadInfo(bereichFromCanvas);
						}
						else if (e.getClickCount() == 2)
						{
							PlanetenkarteFelder felde = new PlanetenkarteFelder(bereichFromCanvas);
							StageController.getInstance().setScene(felde.getScene());
						}
					}			
				});
			}
		}
	}
	
	
	/**
	 * klickt ein Spieler auf ein Bereich, dann wird ein Effekt auf dessen gelegt.
	 * Zudem wird der Effekt eines anderen Canvas aufgehoben, falls ein andere Canvas ausgeweht wurde
	 * @param canvas
	 */
	private void setEffekt(Canvas canvas)
	{
		for (int i = 0; hBoxCanvasreihe.length > i ; i++)
		{
			for (int r = 0 ; hBoxCanvasreihe[i].getChildren().size() > r; r++)
			{
				if (hBoxCanvasreihe[i].getChildren().get(r) instanceof Canvas && hBoxCanvasreihe[i].getChildren().get(r).getEffect() != null)
				{
					hBoxCanvasreihe[i].getChildren().get(r).setEffect(null);
				}
			}
		}
		
		if (canvas.getEffect() != null)
		{
			canvas.setEffect(null);
		}
		else
		{
			canvas.setEffect(new Blend());
		}
	}
	
	
	/**
	 * lead informationen vom Bereich
	 * @param bereich
	 */
	private void loadInfo(Bereich bereich)
	{
		this.getController().clearInformationen();
		Label label = new Label("Default information");
		this.getController().setzeInformationen(label);
	}
}
