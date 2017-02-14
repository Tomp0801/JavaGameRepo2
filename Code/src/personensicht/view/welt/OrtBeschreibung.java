package personensicht.view.welt;

import javafx.scene.control.TextArea;

public class OrtBeschreibung extends TextArea
{
	public OrtBeschreibung(String beschreibung)
	{
		this.setWrapText(true);
		this.setText(beschreibung);
	}
}
