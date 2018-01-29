package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface Platform {
	public void render(GraphicsContext gc);
	public void setImage(Image i);
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
}