package gameMaker.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Der gameMakerV ist die Scene, die verwendet wird, Regionen zu erstellen.
 * 
 * @author Dennis
 */
public class GameMakerV 
{
	/**
	 * Die Scene des GameMakerV
	 */
	private Scene scene; 
	
	/**
	 * root der Scene
	 */
	private BorderPane root = new BorderPane(); 
	
	/**
	 * falls aenderungen an der Region vorgenommen wurde, muss das durtyBit auf true gesetzt werden, um 
	 * bei eventuellen neu laden oder Beenden ect. Nach dem Speichern gefragt werden kann. 
	 */
	private Boolean durtyBit = false; 
	
	/**
	 * instance vom GameMakerV
	 */
	private static GameMakerV instance; 
	
	/**
	 * erstellt einen GameMakerV
	 */
	private GameMakerV(){
		//instance zuweisen
		instance = this; 
		//TODO
	}

	/**
	 * @return die Instance vom GameMaker
	 */
	public static GameMakerV getInstance(){
		if (instance == null)
			new GameMakerV();
		return instance;
	}
	
	/**
	 * 
	 * @return die Scene die verwendete wird. Wenn dies noch nicht geladen wurde, wird null zuruckgegeben
	 */
	public Scene getScne(){
		return scene; 
	}
	
	/**
	 * 
	 * @return root der Scene
	 */
	public BorderPane getRoot(){
		return this.root; 
	}
//-------------------------Methoden-zum-Speichern--------------------------------------------------------------------		
	/**
	 * Speichert die erstellte Region. 
	 * Kann nur ausgefuehrt werden, wenn der Name und path vorher bekannt sind
	 */
	private void quicklySave(){
		//TODO ein Button ruft diese Methode auf
		//speichert die Region in den standart Path
	}
	
	/**
	 * Speichert die erstellte Region. 
	 * 
	 */
	private void save(String name){
		//TODO ein Button ruft diese Methode auf
		//speichert die Region unter den Namen 
	}
//--------------------------------------------------------------------------------------------------------------------
}
