package cd;

import javafx.scene.canvas.GraphicsContext;
import cd.bosses.Boss;
import cd.chars.Character;

public class CharLinkedHitbox extends HitboxImpl {
	private Entity _character;

	public CharLinkedHitbox(String ID, Character c, double knockback, double damage) {
		super(ID, c, false, c.getX(), c.getY(), c.getWidth(), c.getHeight(), c.getXVelocity(), c.getYVelocity(),
				knockback, damage);
		_character = c;
	}
	public CharLinkedHitbox(String ID, Boss c, double knockback, double damage) {
		super(ID, c, false, c.getX(), c.getY(), c.getWidth(), c.getHeight(), c.getXVelocity(), c.getYVelocity(),
				knockback, damage);
		_character = c;
	}

	@Override
	public void render(GraphicsContext gc) {
		_x = _character.getX();
		_y = _character.getY();
	}
}
