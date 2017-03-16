package ressource.view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import obersteEbene.controller.Random;
import ressource.model.Material;

public class MaterialGrafics {
	private Material model;
	
	private Image[] dotImages;
	
	private Background background;
	
	private Random prng;

	
	public MaterialGrafics(Material model) {
		this.model = model;
		
		prng = new Random(model.hashCode());
		
		makeDotImages();
		
		background = new Background(new BackgroundFill(model.getColor(), CornerRadii.EMPTY, Insets.EMPTY));
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
		return dotImages[prng.random(0, dotImages.length - 1)];
	}
	
	/**
	 * Gibt einen Hintergrund zurück mit der Farbe dieses Materials
	 * @return
	 */
	public Background asBackground() {
		return background;
	}
}
