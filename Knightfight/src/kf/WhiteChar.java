package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class WhiteChar extends CharacterImpl {
	private boolean _attack2 = false;
	private boolean _charging1 = false;
	private double _charge1 = 1;
	private Hitbox _chargeshot1;

	public WhiteChar(String ID) {
		super(ID);
		_image = new Image("whitebox.jpg");
		_width = 50;
		_height = 50;
		_damagefactor = 1;
		_speedfactor = 1;

	}

	@Override
	public void render(GraphicsContext gc) {

		if (_attack2) {
			executeAttack2();
		}
		if (_charging1) {
			executeAttack3();
		}
		super.render(gc);

	}

	@Override
	public void attack1() {
		double v;
		int s;
		if (_facing.equals("right")) {
			v = 13;/// d
			s = 50;
		} else {
			v = -13;
			s = -15;
		}
		Hitbox attack = new HitboxImpl("Laser", this, false, _x + s, _y + 18, 15, 15, v, 0, 2, 10);
		// System.out.println("attack added");
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("Laser") && a.getCharacter().equals(this)) {

				return;
			}
		}
		TheGame._attacks.add(attack);
	}

	public void attack2() {
		_counter = 0;
		_attack2 = true;
	}

	public void executeAttack2() {
		if (_attack2) {
			_canact = false;
			_xvelocity = 0;
			// i += 1;
			// _counter += 1;
			int d = 0;

			if (_facing.equals("right")) {
				d = 50;

			} else {
				d = -45;

			}
			Hitbox attack = new MeleeHitbox("punch", this, _x + d, _y + 10, 45, 28, 20, 40);
			// PreHitbox preattack = new PreHitbox(new Image("whitebox.jpg"), _x
			// + d, _y + 10, 45, 28, "prepunch");
			if (_counter == 6) {
				// TheGame._preattacks.clear();
				TheGame._attacks.add(attack);

			}
			if (_counter == 9) {
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("punch") && a.getCharacter().equals(this)) {
						TheGame._attacks.remove(a);
					}
					System.out.println(a);
				}

				attack.setIsGone(true);
				_canact = true;
				_attack2 = false;

			}

		}
	}

	@Override
	public void attack3() {
		if (!_charging1) {
			_charging1 = true;
			_canact = false;
			_xvelocity = 0;
		} else {
			_charging1 = false;
			_canact = true;
			_chargeshot1.setXVelocity(_facing.equals("right") ? 20 : -20);
			TheGame._attacks.add(_chargeshot1);
			_charge1 = 1;
		}
	}

	public void executeAttack3() {

		_charge1 += 2;
		Hitbox attack = null;
		if (_xtumbling || _ytumbling) {
			_charging1 = false;
			_charge1 = 1;

		}

		int d;
		if (_facing.equals("right")) {
			d = 25;
			attack = _chargeshot1 = new HitboxImpl("chargeshot1", this, false, _x + d,
					(int) (_y + 30 - (_charge1 * .35)), _charge1 * .6, _charge1 * .6, 20.0, 0.0, _charge1 / 4,
					_charge1 / 4);
		} else {
			d = 10;

			attack = _chargeshot1 = new HitboxImpl("chargeshot1", this, false, (int) (_x + d - (_charge1 * .45)),
					(int) (_y + 30 - (_charge1 * .35)), _charge1 * .6, _charge1 * .6, 20.0, 0.0, _charge1 / 4,
					_charge1 / 4);
		}
		_chargeshot1 = attack;
		List<Hitbox> remove = new ArrayList<Hitbox>();
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("chargeshot1") && a.getCharacter().equals(this)) {
				remove.add(a);
			}
		}
		for (Hitbox a : remove) {
			TheGame._attacks.remove(a);
		}
		if (_charge1 <= 150) {
			TheGame._attacks.add(_chargeshot1);
		} else {

			_charging1 = false;
			_canact = true;
			_chargeshot1.setXVelocity(_facing.equals("right") ? 20 : -20);
			TheGame._attacks.add(_chargeshot1);
			_charge1 = 1;
		}

	}

	public boolean isCharging1() {
		return _charging1;
	}

	public Color getColor() {
		return Color.WHITE;
	}

	@Override
	public void respawn() {
		_charging1 = false;
		_charge1 = 0;
		_canact = true;
		super.respawn();
	}

	@Override
	public void attackU() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getStockImage() {
		return new Image("whitestock.png");
	}

}
