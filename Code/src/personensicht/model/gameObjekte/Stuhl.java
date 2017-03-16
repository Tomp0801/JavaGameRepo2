package personensicht.model.gameObjekte;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import personensicht.view.gameObjekte.StuhlV;

public class Stuhl extends GameObjekt
{

	/**
	 * groeße der Stuhlbeine diese Variavlen sind zum Speichern und laden (Bein 1, Bein 2, Bein 3, Bein 4)(x,y,z,layoutX,layoutY, layoutZ)
	 */
	private int[][] stuhlBeineSizeSerializ = new int[4][6];
	
	/**
	 * groeße der Stuhlbeine (Bein 1, Bein 2, Bein 3, Bein 4)(x,y,z,layoutX,layoutY, layoutZ)
	 */
	private transient SimpleIntegerProperty[][] stuhlBeineSize = new SimpleIntegerProperty[4][6];
	
	/**
	 * Size vom Sitz zum laden und spechern (x,y,z,layoutX, layoutY, layoutZ)
	 */
	private int[] sitzSerializ = new int[6];
	
	/**
	 * sitz size (x,y,z,layoutX, layoutY, layoutZ)
	 */
	private transient SimpleIntegerProperty[] sitzSize = new SimpleIntegerProperty[6];
	
	/**
	 * Size vom Sitz zum laden und spechern (x,y,z,layoutX, layoutY, layoutZ)
	 */
	private int[] lehneSerializ = new int[6];
	
	/**
	 * sitz size (x,y,z,layoutX, layoutY, layoutZ)
	 */
	private transient SimpleIntegerProperty[] lehneSize = new SimpleIntegerProperty[6];
	
	
	public Stuhl()
	{
		super(GameObjektType.Stuhl);
		initSimpleProperty();
	}

	private void initSimpleProperty(){
											      //default size
		sitzSize[0]  = new SimpleIntegerProperty(50);  //X
		sitzSize[1]  = new SimpleIntegerProperty(50);  //Y
		sitzSize[2]  = new SimpleIntegerProperty(10);  //Z
		sitzSize[3]  = new SimpleIntegerProperty(-sitzSize[0].get()/2);   //LayoutX
		sitzSize[4]  = new SimpleIntegerProperty(-sitzSize[1].get()/2);   //LayoutY
		sitzSize[5]  = new SimpleIntegerProperty(-sitzSize[2].get()/2);   //LayoutZ
		lehneSize[0] = new SimpleIntegerProperty(10);   //X
		lehneSize[1] = new SimpleIntegerProperty(10);   //Y
		lehneSize[2] = new SimpleIntegerProperty(10);   //Z
		lehneSize[3] = new SimpleIntegerProperty(-sitzSize[0].get()/2);
		lehneSize[4] = new SimpleIntegerProperty(-sitzSize[1].get()/2);
		lehneSize[5] = new SimpleIntegerProperty(-sitzSize[2].get()/2);
		
		for (int i = 0; stuhlBeineSize.length > i; i++){
														//Default Size
			stuhlBeineSize[i][0] = new SimpleIntegerProperty(10);  //X
			stuhlBeineSize[i][1] = new SimpleIntegerProperty(10);  //Y
			stuhlBeineSize[i][2] = new SimpleIntegerProperty(50);  //Z
			stuhlBeineSize[i][5] = new SimpleIntegerProperty(-stuhlBeineSize[i][2].get()/2);      //LayoutZ
		}
		
		stuhlBeineSize[0][3] = new SimpleIntegerProperty(+stuhlBeineSize[0][0].get()/2);   			 			//LayoutX  
		stuhlBeineSize[0][4] = new SimpleIntegerProperty(-stuhlBeineSize[0][1].get()/2);   						//LayoutY 
		stuhlBeineSize[1][3] = new SimpleIntegerProperty(-stuhlBeineSize[1][0].get()/2+sitzSize[0].get());  	 //LayoutX 
		stuhlBeineSize[1][4] = new SimpleIntegerProperty(-stuhlBeineSize[1][1].get()/2);   						//LayoutY 
		stuhlBeineSize[2][3] = new SimpleIntegerProperty(+stuhlBeineSize[2][0].get()/2);   						//LayoutX 
		stuhlBeineSize[2][4] = new SimpleIntegerProperty(+stuhlBeineSize[2][1].get()/2-sitzSize[1].get());   	//LayoutY
		stuhlBeineSize[3][3] = new SimpleIntegerProperty(-stuhlBeineSize[3][0].get()/2+sitzSize[0].get());   	//LayoutX 
		stuhlBeineSize[3][4] = new SimpleIntegerProperty(+stuhlBeineSize[3][1].get()/2-sitzSize[1].get());   	//LayoutY
	}
	
	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node ladeNodeObjekt() {
		this.setNodeObjekt(new StuhlV(this)); 
		return this.getNodeObjekt().getNode();
	}

	@Override
	public void serializ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserializ() {
		// TODO Auto-generated method stub
		
	}

	public synchronized int[][] getStuhlBeineSizeSerializ() {
		return stuhlBeineSizeSerializ;
	}

	public synchronized SimpleIntegerProperty[][] getStuhlBeineSize() {
		return stuhlBeineSize;
	}

	public synchronized int[] getSitzSerializ() {
		return sitzSerializ;
	}

	public synchronized SimpleIntegerProperty[] getSitzSize() {
		return sitzSize;
	}

	public synchronized int[] getLehneSerializ() {
		return lehneSerializ;
	}

	public synchronized SimpleIntegerProperty[] getLehneSize() {
		return lehneSize;
	}

	public synchronized void setStuhlBeineSizeSerializ(int[][] stuhlBeineSizeSerializ) {
		this.stuhlBeineSizeSerializ = stuhlBeineSizeSerializ;
	}

	public synchronized void setStuhlBeineSize(SimpleIntegerProperty[][] stuhlBeineSize) {
		this.stuhlBeineSize = stuhlBeineSize;
	}

	public synchronized void setSitzSerializ(int[] sitzSerializ) {
		this.sitzSerializ = sitzSerializ;
	}

	public synchronized void setSitzSize(SimpleIntegerProperty[] sitzSize) {
		this.sitzSize = sitzSize;
	}

	public synchronized void setLehneSerializ(int[] lehneSerializ) {
		this.lehneSerializ = lehneSerializ;
	}

	public synchronized void setLehneSize(SimpleIntegerProperty[] lehneSize) {
		this.lehneSize = lehneSize;
	}
}
