package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CloudChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _diving;
	private boolean _hitdive;

	public CloudChar(String ID) {
		super(ID);
		_width = 80;
		_height = 70;
		_image = new Image("cloud/cloud.png");
		_speedfactor = .9;
		_damagefactor = .9;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (!_attack1 && !_attack2 && !_attack3 && !_attacku) {
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
		}
		super.render(gc);
		if (_attack1) {
			executeAttack1();
		}
		if (_attack2) {
			executeAttack2();
		}
		if (_attack3) {
			executeAttack3();
		}
		if (_attacku) {
			executeAttackU();
			
		}

	}

	@Override
	public void attack1() {
		_canact = false;
		_attack1 = true;
		_counter = 0;

	}

	public void executeAttack1() {
		if (!_xtumbling) {
			_xvelocity = 0;
		}
		if (_xtumbling) {
			_image = new Image("cloud/cloud.png");
			if (_counter >= 23 && _counter < 31) {
				_y += 39;
			}
			_width = 80;
			_height = 70;
			_attack1 = false;
			return;
		}
		if (_counter == 0) {
			_width = 70;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudslice1.png");
			} else {
				_image = new Image("cloud/cloudslice1left.png");
			}
		}
		if (_counter == 18) {
			_width = 85;
			double v;
			int s;
			Image i;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudslice2.png");
				s = 85;
				v = 20;
				i = new Image("cloud/cloudslash.gif");
			} else {
				_image = new Image("cloud/cloudslice2left.png");
				s = -50;
				v = -20;
				i = new Image("cloud/cloudslashleft.gif");
			}

			Hitbox attack = new HitboxImpl("slashprojectile", this, false, _x + s, _y + 10, 50, 45, v, 0, 10, 20, i);
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("slashprojectile") && a.getCharacter().equals(this)) {

					return;
				}
			}
			TheGame._attacks.add(attack);
		}
		if (_counter == 23) {
			_y -= 41;
			_width = 60;
			_height = 110;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudslice3.png");
			} else {
				_image = new Image("cloud/cloudslice3left.png");
			}
		}
		if (_counter == 31) {
			_attack1 = false;
			_canact = true;
			_width = 80;
			_height = 70;
			_y += 40;
		}
	}

	@Override
	public void attack2() {
		_canact = false;
		_attack2 = true;
		_counter = 0;

	}

	public void executeAttack2() {
		if (!_xtumbling) {
			_xvelocity = 0;
		}
		if (_xtumbling) {
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
			if (_counter < 30) {
				_y -= 9;
			}
			_width = 80;
			_height = 70;
			_attack2 = false;
			return;
		}
		if (_counter == 0) {
			_height = 60;
			_y += 9;
			_width = 84;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudprelight.png");
			} else {
				_image = new Image("cloud/cloudprelightleft.png");
			}
		}
		if (_counter == 20) {
			_width = 100;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudlight.png");
			} else {
				_image = new Image("cloud/cloudlightleft.png");
			}
			Hitbox attack = new HitboxImpl("cloudbolt", this, false,
					(_otherchar.getX() + _otherchar.getWidth() / 2) - 20, 1, 20, 100, 0, 16, 20, 20,
					new Image("cloud/cloudbolt.png"));
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("cloudbolt") && a.getCharacter().equals(this)) {

					return;
				}
			}
			TheGame._attacks.add(attack);
		}
		if (_counter == 30) {
			_width = 80;
			_y -= 9;
			_height = 70;
			_canact = true;
			_attack2 = false;

		}

	}

	@Override
	public void attack3() {
		_attack3 = true;
		_yvelocity = -17;
		_canact = false;
		_y -= 31;
		_height = 100;
		_width = 45;
		_counter = 0;
		if (_facing.equals("right")) {
			_image = new Image("cloud/cloudjump1.png");
		} else {
			_image = new Image("cloud/cloudjump1left.png");
		}

	}

	public void executeAttack3() {

		if (_xtumbling) {
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
			_width = 80;
			_height = 70;
			_attack2 = false;
			_canact = true;
			return;
		}
		if (_onplatform && _diving) {
			_canact = true;
			_y -= 11;
			_height = 70;
			_width = 80;
			_attack3 = false;
			_diving = false;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("clouddive") && a.getCharacter().equals(this)) {
					remove.add(a);

				}
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			return;
		}
		if (_yvelocity >= 0 && !_diving && _counter >= 3) {
			_width = 105;
			_height = 60;
			_xvelocity = 0;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudjump2.png");
			} else {
				_image = new Image("cloud/cloudjump2left.png");
				_x -= 60;
			}
			Hitbox attack = new CharLinkedHitbox("clouddive", this, 25, 25);
			TheGame._attacks.add(attack);

			_yvelocity = 10;
			_diving = true;
		}

	}

	@Override
	public void attackU() {
		if (_ultcharge >= 150) {
			_ultcharge = 0;
			_immune = true;
			_canact = false;
			_attacku = true;
			_counter = 0;
			_width = 70;

			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudpreult.png");
			} else {
				_image = new Image("cloud/cloudpreultleft.png");
			}
		}

	}

	public void executeAttackU() {
		Image r = new Image("cloud/cloudult.png");
		Image l = new Image("cloud/cloudultleft.png");
		_xvelocity = 0;
		_yvelocity = 0;
		if (_counter == 15) {
			_width = 0;
			_height = 0;
		}
		Hitbox attack;
		if (_facing.equals("right")) {
			attack = new HitboxImpl("omnislash", this, false, 1, 313, 53, 87, 30, 0, 25, 25, r);
		} else {
			attack = new HitboxImpl("omnislash", this, false, 840, 313, 53, 87, -30, 0, 25, 25, l);
		}
		attack.setHOrientation(true);
		if (_counter == 20) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 30) {

			TheGame._attacks.add(attack);

		}
		if (_counter == 40) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 50) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 60) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 70) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 80) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 90) {

			TheGame._attacks.add(attack);
		}
		if (_counter == 115) {
			_attacku = false;
			_immune = false;
			_canact = true;
			_width = 80;
			_height = 70;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
		}
	}

	@Override
	public Color getColor() {
		return Color.GRAY;
	}

	@Override
	public Image getStockImage() {
		return new Image("cloud/cloudstock.png");
	}

	@Override
	public void respawn() {
		_attack1 = false;
		_attack2 = false;
		_attack3 = false;
		super.respawn();
		_canact = true;
		_width = 80;
		_height = 70;
	}

}
