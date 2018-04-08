package cd;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import cd.bosses.Boss;
import cd.chars.Character;

public class MeleeHitbox extends HitboxImpl {

	public MeleeHitbox(String ID, Character c, int x, int y, int width, int height, double knockback, double damage) {
		super(ID, c, false, x, y, width, height, 0, 0, knockback, damage);

	}

	public MeleeHitbox(String ID, Character c, int x, int y, int width, int height, double knockback, double damage,
			Color color) {
		super(ID, c, false, x, y, width, height, 0, 0, knockback, damage, color);

	}

	public MeleeHitbox(String ID, Character c, int x, int y, int width, int height, double knockback, double damage,
			Image image) {
		super(ID, c, false, x, y, width, height, 0, 0, knockback, damage, image);

	}
	public MeleeHitbox(String ID, Boss c, int x, int y, int width, int height, double knockback, double damage,
			Image image) {
		super(ID, c, false, x, y, width, height, 0, 0, knockback, damage, image);

	}

	@Override
	public void render(GraphicsContext gc) {
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
