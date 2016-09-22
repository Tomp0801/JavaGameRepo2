package view.weltraum;

import java.awt.Toolkit;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Dennis
 */
public class KameraSternensystem extends Kamera2
{
	/**
	 * dieser int Array ist zum navigieren in einem Sternensystem. (x , y , z) die koaardinaten gehort zu der Sektion die angesprochen wird.
	 */
	private int[] sektion = new int[3]; 
	
	/**
	 * Alle Sterne die sich in der Reichweite einer Sektion befinden werden gezeichnet und sind anklickbar 
	 */
	private int SEKTIONSIZE = 1000; 
	
	/**
	 * zuer behandlung von Events 
	 */
	private Scene scene; 
	
	public KameraSternensystem(Scene scene,double sizeSystem)
	{
		initEventBehandlung();
		this.getKamera().setTranslateZ(-100);
		this.getKamera().setTranslateX(-Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		this.getKamera().setTranslateY(-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
	}

	private void initEventBehandlung() 
	{
		this.scene.setOnKeyTyped(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				int x = 0;
				int y = 0;
				int z = 0;
				
				switch (event.getCode())
				{
					case W:
						z++;
						break;
					case S:
						z--;
						break;
					case A:
						x--;
						break;
					case D:
						x++;
						break;
					case Q:
						y--;
						break;
					case E:
						y++;
						break;
					default:
						break;
				}
				
				setNewPosition(sektion[0]+x, sektion[1]+y, sektion[2]+z);	
			}		
		});		
	}
	
	private void setNewPosition(int x, int y, int z)
	{
//		Path path = new Path();
//		path.getElements().add(new MoveTo(0 ,0 , 0));
//		path.getElements().add(new )
//		
//		PathTransition bewegung = new PathTransition(Duration.seconds(2) ,path, this.getKamera());
//
//		bewegung.play();
		
		this.setPosition(x*SEKTIONSIZE, y*SEKTIONSIZE, z*SEKTIONSIZE);
	}
}
