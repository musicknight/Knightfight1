package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class OffsetHitbox extends HitboxImpl {
	private int _xoffset;
	private int _yoffset;
	
	public OffsetHitbox(String ID, Character c, int xoffset, int yoffset, int width, int height, double knockback, double damage) {
		super(ID, c, false, xoffset, yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage);
		_character = c;
	}
	public OffsetHitbox(String ID, Character c, int xoffset, int yoffset, int width, int height, double knockback, double damage, Image i) {
		super(ID, c, false, xoffset, yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage, i);
		_character = c;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		_x += _character.getXVelocity();
		_y += _character.getYVelocity();
		if (!(_gone)) {
			if (_image == null) {
				gc.setFill(_color);
				gc.fillRect(_x, _y, _width, _height);
			} else {
				gc.drawImage(_image, _x, _y, _width, _height);
			}
		}
	}
}
