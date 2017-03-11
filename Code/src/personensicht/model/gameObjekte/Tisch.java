package personensicht.model.gameObjekte;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import personensicht.view.gameObjekte.TischV;

public class Tisch extends GameObjekt
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5233562144231390530L;

	/**
	 * groeße der Tischbeine diese Variavlen sind zum Speichern und laden (Bein 1, Bein 2, Bein 3, Bein 4)(x,y,z,layoutX,layoutY, layoutZ)
	 */
	private int[][] tischBeineSizeSerializ = new int[4][6];
	
	/**
	 * groeße der Tischbeine (Bein 1, Bein 2, Bein 3, Bein 4)(x,y,z,layoutX,layoutY, layoutZ)
	 */
	private transient SimpleIntegerProperty[][] tischBeineSize = new SimpleIntegerProperty[4][6];
	
	/**
	 * Size der Tischplatte zum laden und spechern (x,y,z,layoutX, layoutY, layoutZ)
	 */
	private int[] tischplatteSerializ = new int[6];
	
	/**
	 * tischplatten size (x,y,z,layoutX, layoutY, layoutZ)
	 */
	private transient SimpleIntegerProperty[] tischplatte = new SimpleIntegerProperty[6];
	
	
	public Tisch() {
		super(GameObjektType.Tisch);
		initSimpleProperty();
		this.setDepth( -tischplatte[2].get()-tischBeineSize[0][2].get());
	}

	private void initSimpleProperty(){
		//default size
		tischplatte[0] = new SimpleIntegerProperty(60);  //X
		tischplatte[1] = new SimpleIntegerProperty(60);  //Y
		tischplatte[2] = new SimpleIntegerProperty(10);  //Z
		tischplatte[3] = new SimpleIntegerProperty(-tischplatte[0].get()/2);   //LayoutX
		tischplatte[4] = new SimpleIntegerProperty(-tischplatte[1].get()/2);   //LayoutY
		tischplatte[5] = new SimpleIntegerProperty(-tischplatte[2].get()/2);   //LayoutZ

		for (int i = 0; tischBeineSize.length > i; i++){
														//Default Size
			tischBeineSize[i][0] = new SimpleIntegerProperty(10);  //X
			tischBeineSize[i][1] = new SimpleIntegerProperty(10);  //Y
			tischBeineSize[i][2] = new SimpleIntegerProperty(60);  //Z
			tischBeineSize[i][5] = new SimpleIntegerProperty(-tischBeineSize[i][2].get()/2);      //LayoutZ
		}
		
		tischBeineSize[0][3] = new SimpleIntegerProperty(+tischBeineSize[0][0].get()/2);   			 			//LayoutX  
		tischBeineSize[0][4] = new SimpleIntegerProperty(-tischBeineSize[0][1].get()/2);   						//LayoutY 
		tischBeineSize[1][3] = new SimpleIntegerProperty(-tischBeineSize[1][0].get()/2+tischplatte[0].get());   //LayoutX 
		tischBeineSize[1][4] = new SimpleIntegerProperty(-tischBeineSize[1][1].get()/2);   						//LayoutY 
		tischBeineSize[2][3] = new SimpleIntegerProperty(+tischBeineSize[2][0].get()/2);   						//LayoutX 
		tischBeineSize[2][4] = new SimpleIntegerProperty(+tischBeineSize[2][1].get()/2-tischplatte[1].get());   //LayoutY
		tischBeineSize[3][3] = new SimpleIntegerProperty(-tischBeineSize[3][0].get()/2+tischplatte[0].get());   //LayoutX 
		tischBeineSize[3][4] = new SimpleIntegerProperty(+tischBeineSize[3][1].get()/2-tischplatte[1].get());   //LayoutY
	}
	
	
	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub	
	}

	
	@Override
	public Node ladeNodeObjekt() {
		this.setNodeObjekt(new TischV(this)); 
		return this.getNodeObjekt().getNode();
	}
	
	public synchronized void setTischBeineSize(int index, int x, int y, int z) {
		//Modeleinstllung
		this.tischBeineSize[index][0].setValue(x); 
		this.tischBeineSize[index][1].setValue(y);
		this.tischBeineSize[index][2].setValue(z);
	}

	public synchronized void setTischplatte(int x, int y, int z) {
		//Modeleinstllung
		this.tischplatteSerializ[0] = x;
		this.tischplatteSerializ[1] = y;
		this.tischplatteSerializ[2] = z;
	}

	public synchronized int[][] getTischBeineSizeSerializ() {
		return tischBeineSizeSerializ;
	}

	public synchronized SimpleIntegerProperty[][] getTischBeineSize() {
		return tischBeineSize;
	}

	public synchronized int[] getTischplatteSerializ() {
		return tischplatteSerializ;
	}

	public synchronized void setTischBeineSizeSerializ(int[][] tischBeineSizeSerializ) {
		this.tischBeineSizeSerializ = tischBeineSizeSerializ;
	}

	public synchronized void setTischBeineSize(SimpleIntegerProperty[][] tischBeineSize) {
		this.tischBeineSize = tischBeineSize;
	}

	public synchronized void setTischplatteSerializ(int[] tischplatteSerializ) {
		this.tischplatteSerializ = tischplatteSerializ;
	}

	public synchronized SimpleIntegerProperty[] getTischplatte() {
		return tischplatte;
	}

	public synchronized void setTischplatte(SimpleIntegerProperty[] tischplatte) {
		this.tischplatte = tischplatte;
	}

	@Override
	public void serializ() {
		for(int i = 0; tischBeineSize.length >i ;i++){
			for(int r = 0; tischBeineSize[i].length > r; r++){
				tischBeineSizeSerializ[i][r] = tischBeineSize[i][r].get();
			}
		}
		for (int i = 0; tischplatte.length > i; i++){
			tischplatteSerializ[i] = tischplatte[i].get();
		}	
		this.setLayoutXSerializ(this.getLayoutX().get());
		this.setLayoutYSerializ(this.getLayoutY().get());
		this.setWidthSerializ(this.getWidth().get());
		this.setHeightSerializ(this.getHeight().get());
		this.setDepthSerializ(this.getDepth().get());
	}

	@Override
	public void deserializ() {
		this.tischBeineSize = new SimpleIntegerProperty[4][6];
		this.tischplatte = new SimpleIntegerProperty[6];
		initSimpleProperty();
		for(int i = 0; tischBeineSizeSerializ.length >i ;i++){
			for(int r = 0; tischBeineSizeSerializ[i].length > r; r++){
				tischBeineSize[i][r].setValue(tischBeineSizeSerializ[i][r]);
			}
		}
		for (int i = 0; tischplatteSerializ.length > i; i++){
			tischplatte[i].setValue(tischplatteSerializ[i]);
		}
		this.setLayoutX(this.getLayoutXSerializ());
		this.setLayoutY(this.getLayoutYSerializ());
		this.setWidth(this.getWidthSerializ());
		this.setHeight(this.getHeightSerializ());
		this.setDepth(this.getDepthSerializ());
		
	}
}
