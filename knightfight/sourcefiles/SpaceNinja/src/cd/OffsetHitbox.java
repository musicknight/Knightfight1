package cd;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import cd.bosses.Boss;
import cd.chars.Character;

public class OffsetHitbox extends HitboxImpl {
	private int _xoffset;
	private int _yoffset;
	
	public OffsetHitbox(String ID, Character c, int xoffset, int yoffset, int width, int height, double knockback, double damage) {
		super(ID, c, false, c.getRealX()+xoffset, c.getRealY()+yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage);
		_character = c;
		_xoffset = xoffset;
		_yoffset = yoffset;
	}
	public OffsetHitbox(String ID, Character c, int xoffset, int yoffset, int width, int height, double knockback, double damage, Image i) {
		super(ID, c, false, c.getRealX()+xoffset, c.getRealY()+yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage, i);
		_xoffset = xoffset;
		_yoffset = yoffset;
	}
	public OffsetHitbox(String ID, Boss c, int xoffset, int yoffset, int width, int height, double knockback, double damage, Image i) {
		super(ID, c, false, c.getX()+xoffset, c.getY()+yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage, i);
		_xoffset = xoffset;
		_yoffset = yoffset;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		_x = _character.getX()+_xoffset;
		_y = _character.getY()+_yoffset;
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
