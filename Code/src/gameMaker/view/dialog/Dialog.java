package gameMaker.view.dialog;

import gameMaker.controll.StageCrt;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Dialog extends Stage{
	
	public Dialog() {
//		super(StageStyle.UNDECORATED);
        this.initOwner(StageCrt.getInstance().getStage());
		this.setResizable(false);
//		this.setAlwaysOnTop(true);
		this.centerOnScreen();
	}
	public abstract void uebernehmeAenderung();
}
