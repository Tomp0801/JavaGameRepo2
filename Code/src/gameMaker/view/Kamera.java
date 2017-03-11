package gameMaker.view;

import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Die Kamera kann mit der Tastertur gesteuert werden.
 * W,A,S,D bewegt de kamera nach vorne,links,unten,rechts und Q,E nach oben bzw. nach unten
 * @author Dennis
 *
 */
public class Kamera extends PerspectiveCamera
{
	private int speed = 15;
	
	/**
	 * erstellt eine Kamera
	 * @param scene wird verwendte um die Kamera zu steuern
	 */
	Kamera(Scene scene){
		Kamera kamera = this; 
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				
				if (event.getCode()== KeyCode.A){
					kamera.setLayoutX(kamera.getLayoutX()-speed);
				}
				else if(event.getCode()== KeyCode.S){
					kamera.setLayoutY(kamera.getLayoutY()+speed);
				}	
				else if(event.getCode()== KeyCode.D){
					kamera.setLayoutX(kamera.getLayoutX()+speed);
				}	
				else if(event.getCode()== KeyCode.W){
					kamera.setLayoutY(kamera.getLayoutY()-speed);
				}
				else if(event.getCode()== KeyCode.E){
					kamera.setTranslateZ(kamera.getTranslateZ()-speed);
				}	
				else if(event.getCode()== KeyCode.Q){
					kamera.setTranslateZ(kamera.getTranslateZ()+speed);
				}	
			}	
		});
	}

	public synchronized int getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}
}
