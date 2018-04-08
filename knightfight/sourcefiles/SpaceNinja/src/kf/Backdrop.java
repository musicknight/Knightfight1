package kf;

import cd.EntityImpl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Backdrop extends EntityImpl {
	int _width;
	int _height;
	Image _image;
	public Backdrop(int x, int y, int width, int height, String ID, Image i) {
		super(x, y, ID);
		_width = width;
		_height = height;
		_image = i;
	}
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(_image, _x, _y, _width, _height);
		
	}

}
