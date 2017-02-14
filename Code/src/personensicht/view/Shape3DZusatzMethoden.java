package personensicht.view;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import personensicht.model.spieler.klamotten.Klamotte;

/**
 * Beinhaltet Methoden die fuer Shape3D-Nods verwendet werden koennen
 * @author Dennis
 * @version Alpha 1.0
 *
 */
public class Shape3DZusatzMethoden 
{
	/**
	 * erstellt eine VBox mit 3 slider. Mit diesen laesst sich die Farbe der einzelnen Shape3D veraendern
	 * 
	 * @param node
	 * @return
	 */
	public static VBox farbWahlVonShape3D(Shape3D node, Klamotte klamotte)
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(node, Color.WHITE);
		Slider blauTon = new Slider();
		Slider rotTon = new Slider();
		Slider gruenTon = new Slider();	
		blauTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				klamotte.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
				Shape3DZusatzMethoden.hintergundFarbeSetzen(node,klamotte.getColor() );
			}
		});
		rotTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				klamotte.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
				Shape3DZusatzMethoden.hintergundFarbeSetzen(node,klamotte.getColor() );
			}
		});
		gruenTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				klamotte.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
				Shape3DZusatzMethoden.hintergundFarbeSetzen(node,klamotte.getColor() );
			}
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(blauTon, rotTon, gruenTon);
		return root; 
	}
	
	/**
	 * erstellt eine VBox mit 3 slider. Mit diesen laesst sich die Farbe der einzelnen Shape3D veraendern
	 * @param node
	 * @return
	 */
	public static VBox farbWahlVonShape3D(ArrayList<Shape3D> node, Klamotte klamotte)
	{
		PhongMaterial hintergrund = new PhongMaterial();
        hintergrund.setDiffuseColor(Color.WHITE);
        for (int i = 0; node.size() > i; i++)
        	node.get(i).setMaterial(hintergrund);
		
        Slider blauTon = new Slider();
		Slider rotTon = new Slider();
		Slider gruenTon = new Slider();	
		
		blauTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				klamotte.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
				hintergrund.setDiffuseColor(klamotte.getColor());
				 for (int i = 0; node.size() > i; i++)
			        	node.get(i).setMaterial(hintergrund);
			}
		});
		rotTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				
				klamotte.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
				hintergrund.setDiffuseColor(klamotte.getColor());
				 for (int i = 0; node.size() > i; i++)
			        	node.get(i).setMaterial(hintergrund);
			}
		});
		gruenTon.setOnMouseMoved(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{	
				klamotte.setColor(new Color(rotTon.getValue()/100, gruenTon.getValue()/100, blauTon.getValue()/100, 1));
				hintergrund.setDiffuseColor(klamotte.getColor());
				 for (int i = 0; node.size() > i; i++)
			        	node.get(i).setMaterial(hintergrund);
			}
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(blauTon, rotTon, gruenTon);
		return root; 
	}

	public static void hintergundFarbeSetzen(Shape3D node, Color farbe)
	{
		PhongMaterial hintergrund = new PhongMaterial();
        hintergrund.setDiffuseColor(farbe);
        node.setMaterial(hintergrund);
	}
}
