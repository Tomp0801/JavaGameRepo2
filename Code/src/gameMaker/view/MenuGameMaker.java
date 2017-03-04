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
		neu.setOnAction(e -> RegionMakerV.getInstance().erstelleNeueRegion());
		MenuItem laden = new MenuItem("laden");
		laden.setOnAction(e -> RegionMakerV.getInstance().ladeAndereRegion());
		MenuItem speichern = new MenuItem("speichern");
		speichern.setOnAction(e -> RegionMakerV.getInstance().fastSave());
		MenuItem speichernAls = new MenuItem("speichern unter");
		speichernAls.setOnAction(e -> RegionMakerV.getInstance().fileChooserSave());
		datei.getItems().addAll(neu,laden,speichern,speichernAls);
		//--------------------------Region---------------------------//
		//Einstellungen zur Region zu �m Beipiel die Groe�e
		Menu regionEinstellungMenu = new Menu("Region");
		MenuItem size = new MenuItem("Gr��e");
		size.setOnAction(e -> RegionMakerV.getInstance().einstellungRegionGroe�e());
		regionEinstellungMenu.getItems().add(size);
		//------------------Hilfe------------------------------------//
		Menu hilfe = new Menu("Hilfe");
		MenuItem tutorial = new MenuItem("Tutorial"); //Funktion noch nicht vorhanden
		hilfe.getItems().addAll(tutorial);
		this.getMenus().addAll(datei,regionEinstellungMenu,hilfe);
	}

}
