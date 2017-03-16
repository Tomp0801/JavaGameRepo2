package draufsicht.view.hauptmenu;

import draufsicht.controller.StageController;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Ladebildschirm
{
	/**
	 * der Thread der das Ladesymbol zum rotieren bringt
	 */
	private Thread rotationThread;
	
	/**
	 * die Instance des Ladebildschirms
	 */
	private static Ladebildschirm instance; 
	
	private Label ladeSymbol = new Label("SpaceProgramm");
	
	/**
	 * Konstruktor des Ladebildschirms
	 */
	private Ladebildschirm() 
	{
		
		instance = this;
		
		ladeSymbol.setFont(new Font(40));
		ladeSymbol.setTextFill(Color.RED);
		
	
		rotationThread = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(2),ladeSymbol);
				
				fadeTransition1.setToValue(0);
//				int i = 0;
//				while (true)
//				{
//					try 
//					{
//						Thread.sleep(80);
//					} catch (InterruptedException e) 
//					{
//						e.printStackTrace();
//					}
//					
//					ladeSymbol.setScaleY(i);
//					ladeSymbol.setTranslateZ(ladeSymbol.getTranslateZ()+i);
//					i=i++;
//					
//					if (ladeSymbol.getTranslateZ() >= 200 )
//					{
//						i = i-3;
//					}		
//				}
			}
		});
		
		rotationThread.setDaemon(true);
	}
	
	/**
	 * 
	 * @return die Instance vom Ladebildschirm
	 */
	public static Ladebildschirm getInstance()
	{
		if (instance == null)
		{
			new Ladebildschirm();
		}
		return instance;
	}
	
	
	/**
	 * oeffnet den Ladebildschirm
	 */
	public void oeffneLadebildschrim()
	{
		StackPane root = new StackPane(ladeSymbol);
		root.setStyle("-fx-background-color: BLACK");
		StageController.getInstance().setScene(new Scene(root));
		rotationThread.start();
	}
	
	/**
	 * wird der Ladebildschrim beendet sollte diese Methode aufgerufen werden um den Thread zu beenden
	 */
	public void closeRotationThread()
	{
		this.rotationThread.interrupt();
	}
}
