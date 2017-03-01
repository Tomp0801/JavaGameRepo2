package gameMaker.view.einstellungGameObjekte;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import personensicht.model.gameObjekte.Bett;
import personensicht.view.gameObjekte.BettV;

public class EinstellungBett extends EinstellungGameObjekt
{
	private int erholungswert = 10;
	
	public EinstellungBett(Bett bett)
	{
		super(bett);
		initEigenschaften(bett);
		initFarwahl(bett);
	}
	
	private void initEigenschaften(Bett bett)
	{
	this.setName("Bett");

	HBox erholungBox = new HBox();
	erholungBox.setSpacing(6);
	erholungBox.setAlignment(Pos.CENTER);
	Label bedeutungLabel = new Label("Erholung");
	TextField textField = new TextField("  "+erholungswert+"  ");
	textField.focusedProperty().addListener(new ChangeListener<Boolean>()
	{	@Override public void changed(ObservableValue<? extends Boolean> arg0, Boolean alteEinstellung, Boolean neueEinstllung) {
			if (alteEinstellung == true && neueEinstllung == false)		{
				int i = Integer.valueOf(textField.getText());
				if (i < 10 || i > 100)	{
					textField.setText(""+erholungswert);
				}
			}	
		}	
	});
	erholungBox.getChildren().addAll(bedeutungLabel, textField);

	HBox laengeXBox = new HBox();
	laengeXBox.setSpacing(6);
	laengeXBox.setAlignment(Pos.CENTER);
	Label bedeutungLabelLaengeX = new Label("Länge");
	TextField textFieldLaenge = new TextField(50+"");
	textFieldLaenge.focusedProperty().addListener(new ChangeListener<Boolean>()
	{	
		@Override public void changed(ObservableValue<? extends Boolean> arg0, Boolean alteEinstellung, Boolean neueEinstllung) {
	
			if (alteEinstellung == true && neueEinstllung == false)		
			{
				int i = 50; 
				try
				{
					i = Integer.valueOf(textFieldLaenge.getText().trim());
				}
				catch (NumberFormatException e) 
				{
					textFieldLaenge.setText("50");
					i = 50; 
				}
				if (i > BettV.MAXSIZE_X)	
				{
					textFieldLaenge.setText(""+BettV.MAXSIZE_X);
					i = BettV.MAXSIZE_X;
				}
				else if (i < BettV.MINSIZE_X)
				{
					textFieldLaenge.setText(""+BettV.MINSIZE_X);
					i = BettV.MINSIZE_X;
				}
					
				bett.setWidth(i);
		}	
	}});
	laengeXBox.getChildren().addAll(bedeutungLabelLaengeX, textFieldLaenge);
	
	HBox breiteYBox = new HBox();
	breiteYBox.setSpacing(6);
	breiteYBox.setAlignment(Pos.CENTER);
	Label bedeutungLabelBreiteY = new Label("Breite");
	TextField textFieldBreiteY = new TextField(""+50);
	textFieldBreiteY.focusedProperty().addListener(new ChangeListener<Boolean>()
	{	@Override public void changed(ObservableValue<? extends Boolean> arg0, Boolean alteEinstellung, Boolean neueEinstllung) {
			if (alteEinstellung == true && neueEinstllung == false)		{
				int i = 50; 
				try
				{
					i = Integer.valueOf(textFieldBreiteY.getText().trim());
				}
				catch (NumberFormatException e) 
				{
					textFieldBreiteY.setText("50");
					i = 50; 
				}
				if (i < BettV.MINSIZE_Y)
				{
					textFieldBreiteY.setText(""+BettV.MINSIZE_Y);
					i = BettV.MINSIZE_Y; 
				}
				else if (i > BettV.MAXSIZE_Y)
				{
					textFieldBreiteY.setText(""+BettV.MAXSIZE_Y);
					i = BettV.MAXSIZE_Y; 
				}
				bett.setHeight(i);

		}}	
	});
	breiteYBox.getChildren().addAll(bedeutungLabelBreiteY, textFieldBreiteY);
	
	HBox hoheZBox = new HBox();
	hoheZBox.setSpacing(6);
	hoheZBox.setAlignment(Pos.CENTER);
	Label bedeutungLabelHoheZ = new Label("Hohe");
	TextField textFieldHoheZ = new TextField(20+"");
	textFieldHoheZ.focusedProperty().addListener(new ChangeListener<Boolean>()
	{	@Override public void changed(ObservableValue<? extends Boolean> arg0, Boolean alteEinstellung, Boolean neueEinstllung) {
			if (alteEinstellung == true && neueEinstllung == false)		{
				int i = 50; 
				try
				{
					i = Integer.valueOf(textFieldHoheZ.getText().trim());
				}
				catch (NumberFormatException e) 
				{
					textFieldHoheZ.setText("50");
					i = 50; 
				}
				if (i < BettV.MINSIZE_Z)	
				{
					textFieldHoheZ.setText(""+BettV.MINSIZE_Z);
					i = BettV.MINSIZE_Z; 
				}	
				else if (i > BettV.MAXSIZE_Z)
				{
					textFieldHoheZ.setText(""+BettV.MAXSIZE_Z);
					i = BettV.MAXSIZE_Z; 
				}
				bett.setDepth(i);
			}	
		}	
	});
	hoheZBox.getChildren().addAll(bedeutungLabelHoheZ, textFieldHoheZ);
	
	this.getRootEigenschaften().getChildren().addAll(erholungBox,laengeXBox,breiteYBox,hoheZBox);
	}

	private void initFarwahl(Bett bett)
	{
		TitledPane farbwahl = new TitledPane();
		farbwahl.setText("Farbe");
		VBox root = new VBox(); 
		root.setSpacing(6);
		root.setAlignment(Pos.TOP_CENTER);
		farbwahl.setContent(root);
		
		Slider blauTon = new Slider();
		Slider rotTon = new Slider();
		Slider gruenTon = new Slider();	
		
		blauTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				bett.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
			}
		});
		rotTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				bett.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
			}
		});
		gruenTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				bett.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
			}
		});
		
		root.getChildren().addAll(blauTon, rotTon, gruenTon);
		this.getChildren().add(farbwahl);	
	}
}
