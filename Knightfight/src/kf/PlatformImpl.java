package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PlatformImpl implements Platform {
	private Image _image = new Image("purplebox.jpg");
	private Rectangle _rect = new Rectangle(150, 400, 600, 500);

	public void render(GraphicsContext gc) {
		gc.drawImage(_image, _rect.getX(), _rect.getY(), _rect.getWidth(), _rect.getHeight());
	}
}
