package cd;

import javafx.scene.image.Image;

public class Backdrop {
	private Image _i;
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	
	public Backdrop(Image i, int x, int y, int w, int h) {
		_i = i;
		_x = x;
		_y = y;
		_width = w;
		_height = h;
	}
	
	public int getX() {
		return _x;
	}
	public int getY() {
		return _y;
	}
	public int getWidth() {
		return _width;
	}
	public int getHeight() {
		return _height;
	}
	public Image getImage() {
		return _i;
	}
	
	public void setX(int x) {
		_x = x;
	}
	public void setY(int y) {
		_y = y;
	}
	
	public void setImage(Image i) {
		_i = i;
	}
}
