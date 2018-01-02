package kf;

import javafx.scene.image.Image;

public class OffsetHitbox extends HitboxImpl {
	public OffsetHitbox(String ID, Character c, int xoffset, int yoffset, int width, int height, double knockback, double damage) {
		super(ID, c, false, c.getX()+xoffset, c.getY()+yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage);
		_character = c;
	}
	public OffsetHitbox(String ID, Character c, int xoffset, int yoffset, int width, int height, double knockback, double damage, Image i) {
		super(ID, c, false, c.getX()+xoffset, c.getY()+yoffset, width, height, c.getXVelocity(), c.getYVelocity(),
				knockback, damage, i);
		_character = c;
	}
}
