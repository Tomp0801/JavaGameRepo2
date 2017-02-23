package gameMaker.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Das Menu bietet mehrere Funktionen an und wird oben auf der Scene angezeigt
 * @author Dennis
 */
public class MenuGameMaker extends MenuBar{
	
	MenuGameMaker(){
		//------------------Datei-------------------------------------//
		Menu datei = new Menu("Datei");
		MenuItem neu = new MenuItem("neu");
		neu.setOnAction(e -> RegionMakerView.getInstance().erstelleNeueRegion());
		MenuItem laden = new MenuItem("laden");
		laden.setOnAction(e -> RegionMakerView.getInstance().ladeAndereRegion());
		MenuItem speichern = new MenuItem("speichern");
		speichern.setOnAction(e -> RegionMakerView.getInstance().speicherRegion());
		MenuItem speichernAls = new MenuItem("speichern unter");
		speichernAls.setOnAction(e -> RegionMakerView.getInstance().speicherUnterNameRegion());
		datei.getItems().addAll(neu,laden,speichern,speichernAls);
		//--------------------------Region---------------------------//
		//Einstellungen zur Region zu ¥m Beipiel die Groeﬂe
		Menu regionEinstellungMenu = new Menu("Region");
		MenuItem size = new MenuItem("Grˆﬂe");
		size.setOnAction(e -> RegionMakerView.getInstance().einstellungRegionGroeﬂe());
		regionEinstellungMenu.getItems().add(size);
		//------------------Hilfe------------------------------------//
		Menu hilfe = new Menu("Hilfe");
		MenuItem tutorial = new MenuItem("Tutorial"); //Funktion noch nicht vorhanden
		hilfe.getItems().addAll(tutorial);
		this.getMenus().addAll(datei,regionEinstellungMenu,hilfe);
	}

}
