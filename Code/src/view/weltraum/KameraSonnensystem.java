package view.weltraum;

import java.awt.MouseInfo;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Dies Klasse kann Nods mit der Maus zum rotieren bringen
 * dazu werden Nods einer Liste zugeordnet und alle Elemente dieser Liste werden dann bei 
 * gedrucktter Maustaste bewegt. 
 *  
 * @author Demix
 *
 */
public class KameraSonnensystem extends Kamera2
{
	/**
	 * eine Liste mit den Nodes die sich rotiert
	 */
	private ArrayList<Node> nodeList = new ArrayList<Node>();
	
	/**
	 * auf dieser Node werden MausEvents behandelt
	 */
	private Node eventNode; 

	/**
	 * zur rotation der Node um die X Achse
	 */
	private Rotate rotationX = new Rotate(0 , 0, 0, 0 , Rotate.X_AXIS);
	
	/**
	 * zur rotation der Node um die Y Achse
	 */
	private Rotate rotationY = new Rotate(0 , 0, 0, 0 , Rotate.Y_AXIS);
	
	/**
	 * die Kamera wird zum sumen verwendte. 
	 */
	private ParallelCamera kamera = new ParallelCamera();
			
	/**
	 * ertellt ein Objekt von NodeRotationkamera
	 */
	public KameraSonnensystem()
	{
		kamera.setTranslateZ(-4000);
	}
	
	/**
	 * erstellt ein Objekt von NodeRotationkamera 
	 * @param die Node rotiert sich und es werden MausEvents abgefangen mit der eine Rotation durchgefuehrt wird.
	 */
	public KameraSonnensystem(Node node)
	{
		this.addNode(node);
		setEventHandlerNode(node);
	}
	
	
	/**
	 * erstellt ein Objekt von NodeRotationkamera 
	 * @param nodeList mit nodes die sich rotieren sollen. Auf der erste Node in der Liste werden die MausEvents abgefangen
	 */
	public KameraSonnensystem(ArrayList<Node> nodeList)
	{
		this.addNode(nodeList); 
		setEventHandlerNode(nodeList.get(0));
	}
	
	
	/**
	 * fuegt eine Node hinzu die rotieren soll
	 * @param node
	 */
	public void addNode(Node node)
	{
		this.nodeList.add(node);
		node.getTransforms().addAll(rotationX , rotationY);
	}
	
	
	/**
	 * fuegt eine liste von Nodes die sich rotieren sollen hinzu
	 * @param nodeList
	 */
	public void addNode(ArrayList<Node> nodeList)
	{
		for (int i = 0; nodeList.size() > i ; i++)
			this.addNode(nodeList.get(i));
	}
	
	
	/**
	 * setzt oder ersetzt das Element auf den die Mousevents zur
	 * Rotation des Nods behandelt werden sollen
	 * 
	 * @param eventNode
	 */
	public void setEventHandlerNode(Node eventNode)
	{   	
		this.eventNode = eventNode; 
		
		this.eventNode.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent e)
			{
				if (e.getSource() == KeyCode.W)
				{
					doZoomen(-1);
				}
				else if (e.getSource() == KeyCode.S)
				{
					doZoomen(1);
				}
			}
		});
		
		this.eventNode.setOnMouseDragged(new EventHandler<MouseEvent>() 
	    {
	      	double mouseOldX = 0; 
	       	double mouseOldY = 0;
	       	
        	double mousePosX = 0;
        	double mousePosY = 0;
	        	
	        
        	double mouseDeltaX = 0;
	        double mouseDeltaY = 0;
	        	
			@Override
			public void handle(MouseEvent event) 
			{
		       	mouseOldX = mousePosX;
	            mouseOldY = mousePosY;
	            mousePosX = MouseInfo.getPointerInfo().getLocation().getX();
	            mousePosY = MouseInfo.getPointerInfo().getLocation().getY();
	                
	            mouseDeltaX = mousePosX - mouseOldX;
	            mouseDeltaY = mousePosY - mouseOldY;
	                   
	            if (mouseDeltaX >= 8 || mouseDeltaX <= -8)
	            {
	              	mouseDeltaX = 0;
	            }
	            if (mouseDeltaY >= 5 || mouseDeltaY <= -5  )
	            {
	              	mouseDeltaY = 0;
	            }
	                
					rotation(mouseDeltaX , mouseDeltaY );
			}
		});	
	}
	
	
	/**
	 * rotiert die Node
	 */
	public void rotation(double mouseDeltaY , double mouseDeltaX)
	{	
		for (int i = 0 ; nodeList.size() > i ; i++)
		{
			this.rotationX.setAngle(rotationX.getAngle() - mouseDeltaX/1);
			this.rotationY.setAngle(rotationY.getAngle() + mouseDeltaY/1);
		}
	}
	
	
	/**
	 * setzt einen Punkt um den sich die Node drehen soll
	 * @param drehpunkt
	 */
	protected void setDrehpunkt(Point3D drehpunkt)
	{
		rotationX.setPivotX(drehpunkt.getX());
		rotationX.setPivotY(drehpunkt.getY());
		rotationX.setPivotZ(drehpunkt.getZ());
		
		rotationY.setPivotX(drehpunkt.getX());
		rotationY.setPivotY(drehpunkt.getY());
		rotationY.setPivotZ(drehpunkt.getZ());
		
		ArrayList<FadeTransition> newPosi = new ArrayList<FadeTransition>();
		
		for (int i = 0; nodeList.size() > i ; i++)
		{
			newPosi.add(new FadeTransition(Duration.millis(1000), nodeList.get(i)));
		}	
		
		for (int i = 0; newPosi.size() > i ; i++)
		{
			newPosi.get(i).play();
		}
	}
	
	
	/**
	 * setzt einen Punkt um den sich die Node drehen soll
	 * @param drehpunkt
	 */
	protected void setDrehpunkt(double posiX , double posiY , double posiZ)
	{
		this.setDrehpunkt(new Point3D(posiX , posiY , posiZ));
	}
	
	
	/**
	 * gibt die Kamera wieder, die ran und weg sumt
	 * @return
	 */
	protected ParallelCamera getKamera()
	{
		return this.kamera;
	}
	
	
	/**
	 * zoomt naher ran oder weiter weg. kommt gant auf die uebergebenen Werte drauf an
	 * @param zoome
	 */
	private void doZoomen(double zoome)
	{
		this.kamera.setTranslateZ(this.kamera.getTranslateZ()+zoome);
	}
}
