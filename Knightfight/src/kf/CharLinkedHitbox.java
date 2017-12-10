package kf;

import javafx.scene.canvas.GraphicsContext;

public class CharLinkedHitbox extends HitboxImpl {
	private Character _character;

	public CharLinkedHitbox(String ID, Character c, double knockback, double damage) {
		super(ID, c, false, c.getX(), c.getY(), c.getWidth(), c.getHeight(), c.getXVelocity(), c.getYVelocity(),
				knockback, damage);
		_character = c;
	}

	@Override
	public void render(GraphicsContext gc) {
		_x += _character.getXVelocity();
		_y += _character.getYVelocity();
	}
}
