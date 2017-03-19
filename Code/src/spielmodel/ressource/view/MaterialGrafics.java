package spielmodel.ressource.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import spielctr.controller.Random;
import spielmodel.ressource.model.Material;

public class MaterialGrafics {
	private Material model;
	
	private Image[] dotImages;
		
	private Random prng;
	
	private ObjectProperty<Color> Color;
	
	public MaterialGrafics(Material model) {
		this.model = model;
		
		prng = new Random(model.hashCode());
		Color = new SimpleObjectProperty<Color>();
	}
	
	private void makeDotImages() {
		dotImages = new Image[3];
		
		int width = 50;
		int height = 50;
		
		Color color = model.getColor();
		PixelWriter writer;		
		WritableImage wImage;
		
		for (int i = 0; i < dotImages.length; i++) {
			wImage = new WritableImage(width, height);
			writer = wImage.getPixelWriter();
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (prng.randomBoolean(0.1f * model.getBodenVorkommen())) {
						writer.setColor(x, y, color);
					}
				}
			}
			
			dotImages[i] = wImage;
		}
	}
	
	public Material getModel() {
		return model;
	}
	
	public Image getDotImage() {
		if (dotImages == null) {
			makeDotImages();
		}
		return dotImages[prng.random(0, dotImages.length - 1)];
	}

	public ObjectProperty<Color> getColorProperty() {
		return Color;
	}
}
