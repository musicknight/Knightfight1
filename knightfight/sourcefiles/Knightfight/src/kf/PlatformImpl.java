package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class PlatformImpl implements Platform {
	private Image _image = new Image("platform.png");
	private Rectangle _rect = new Rectangle(150, 400, 600, 200);
	private int _x = 150;
	private int _y = 400;
	private int _width = 600;
	private int _height = 200;
	
	public PlatformImpl(int x, int y, int width, int height) {
		_x = x;
		_y = y;
		_width = width;
		_height = height;
		_rect = new Rectangle(150, 400, 600, 200);
	}
	
	public PlatformImpl() {
		this(150, 400, 600, 200);
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(_image, _x, _y, _width, _height);
	}
	
	public void setImage(Image i) {
		_image = i;
	}

	@Override
	public int getX() {
		return _x;
	}

	@Override
	public int getY() {
		return _y;
	}

	@Override
	public int getWidth() {
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}
}
