package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.characters.CharacterImpl;

public class YellowChar extends CharacterImpl {

	private boolean _attack3 = false;
	private double _holder;

	public YellowChar(String ID) {
		super(ID);
		_image = new Image("yellowbox.jpg");
		_width = 50;
		_height = 50;
		_damagefactor = 1;
		_speedfactor = 1;

	}

	public void render(GraphicsContext gc) {
		if (_attack3) {
			executeAttack3();
		}
		super.render(gc);

	}

	public void attack1() {
		int s;
		double v;
		if (_facing.equals("right")) {
			v = 5;/// d
			s = 50;
		} else {
			v = -5;
			s = -40;
		}
		Hitbox attack = new HitboxImpl("Fireball", this, false, _x + s, _y + 6, 40, 40, v, 0, 25, 25);
		// System.out.println("attack added");
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("Fireball") && a.getCharacter().equals(this)) {

				return;
			}
		}
		TheGame._attacks.add(attack);
	}

	public void attack2() {
		double v;
		int s;
		double xv;
		if (_facing.equals("right")) {
			v = 7;
			s = 50;
			xv = -8;
		} else {
			v = -7;
			s = -62;
			xv = 8;
		}
		Hitbox attack = new HitboxImpl("rock", this, true, _x + s, _y - 30, 70, 70, v, -10, 20, 30);
		attack.setBounces(true);
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("rock") && a.getCharacter().equals(this)) {

				return;
			}
		}
		TheGame._attacks.add(attack);
		_xvelocity = xv;
		_xtumbling = true;
	}

	@Override
	public void attack3() {
		_image = new Image("yellowcharge.png");
		_attack3 = true;
		_counter = 0;
		_xvelocity = 0;
		_canact = false;
		_holder = _damage;
	}

	public void executeAttack3() {
		int v = -1;
		if (_facing.equals("right")) {
			v = 1;
		}
		if (_holder != _damage && _counter < 35) {
			_attack3 = false;
			_image = new Image("yellowbox.jpg");

		}

		Hitbox attack = new CharLinkedHitbox("yellowcharge", this, Math.abs(_xvelocity * 1.5),
				Math.abs(_xvelocity * 1.5));
		List<Hitbox> remove = new ArrayList<Hitbox>();
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("yellowcharge") && a.getCharacter().equals(this)) {
				remove.add(a);
			}
		}
		for (Hitbox a : remove) {
			TheGame._attacks.remove(a);
		}
		if (_counter == 35) {
			TheGame._attacks.add(attack);
			_xvelocity = v * 45;
			_xtumbling = true;

		}
		if (_counter >= 35) {
			TheGame._attacks.add(attack);
			int i = 0;
			for (Hitbox a : TheGame._attacks) {
				if (a.checkCollide() && !_otherchar.isImmune()) {
					_xvelocity = 0;
				}
			}
		}
		if (_counter >= 35 && _xvelocity == 0) {
			TheGame._attacks.remove(attack);
			attack.setIsGone(true);

			_image = new Image("yellowbox.jpg");
			_attack3 = false;
			return;
		}

	}

	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public void respawn() {
		_attack3 = false;
		_image = new Image("yellowbox.jpg");
		_canact = true;
		super.respawn();
	}

	@Override
	public void attackU() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getStockImage() {
		return new Image("yellowstock.png");
	}
}
