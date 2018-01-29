package kf;

import javafx.scene.image.Image;

public class ExplodingHitbox extends HitboxImpl {
	private MeleeHitbox _m;
	
	public ExplodingHitbox(String ID, Character c, boolean gravity, int x, int y, double width, double height,
			double xvelocity, double yvelocity, double knockback, double damage, Image image, MeleeHitbox m) {
		super(ID, c, gravity, x, y, width, height, xvelocity, yvelocity, knockback, damage, image);
		_m = m;
		
	}
	
	public void explode() {
		int x =  (int) (_x - ((_m.getWidth() - _width) / 2));
		int y =  (int) (_y - ((_m.getHeight() - _height) / 2));
		_m.setX(x);
		_m.setY(y);
		TheGame._attacks.remove(this);
		
		TheGame._attacks.add(_m);
	}

}
