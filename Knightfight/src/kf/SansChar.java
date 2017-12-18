package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SansChar extends CharacterImpl {
	private boolean _attack3 = false;
	private boolean _attack2 = false;
	private double _holder = 0;

	public SansChar(String ID) {
		super(ID);
		_image = new Image("sans.jpg");
		_width = 50;
		_height = 75;
		_damagefactor = .9;
		_speedfactor = .9;
	}

	@Override
	public void render(GraphicsContext gc) {

		if (_attack3) {
			executeAttack3();
		}
		if (_attack2) {
			executeAttack2();
		}
		if (_attacku) {
			executeAttackU();
		}

		super.render(gc);


	}

	@Override
	public void attack1() {
		_counter = 0;
		_cd = 20;
		double v;
		int s;
		if (_facing.equals("right")) {
			/// d
			s = 50;
		} else {

			s = -100;
		}
		Hitbox attack = new HitboxImpl("Bone", this, true, _x + s, _y - 175, 100, 30, 0, 0, 25, 20,
				new Image("bone.png"));
	

		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("Bone") && a.getCharacter().equals(this)) {

				return;
			}
		}
		TheGame._attacks.add(attack);

	}

	@Override
	public void attack2() {
		if (!_attack2) {
			if (_facing.equals("right")) {
				_xvelocity = 7;
			} else {
				_xvelocity = -7;
			}
			_xtumbling = true;
			_attack2 = true;
			_counter = 0;
			_holder = _damage;
		}

	}

	public void executeAttack2() {
		if (_attack2) {

			// i += 1;
			// _counter += 1;
			if (_holder != _damage) {
				_attack2 = false;

				List<Hitbox> remove = new ArrayList<Hitbox>();
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("smallbone") && a.getCharacter().equals(this)) {
						remove.add(a);

					}
				}
				for (Hitbox a : remove) {
					TheGame._attacks.remove(a);
				}
				return;
			}
			int d = 0;

			if (_facing.equals("right")) {
				d = 50;

			} else {
				d = -30;

			}
			Hitbox attack = new MeleeHitbox("smallbone", this, _x + d, _y + 25, 30, 13, 7, 13, new Image("bone.png"));
			// PreHitbox preattack = new PreHitbox(new Image("whitebox.jpg"), _x
			// + d, _y + 10, 45, 28, "prepunch");
			if (_counter == 10) {
				// TheGame._preattacks.clear();
				TheGame._attacks.add(attack);

			}
			if (_counter == 14) {
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("smallbone") && a.getCharacter().equals(this)) {
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

	public void attack3() {
		_counter = 0;
		_attack3 = true;
		_image = new Image("charge.png");

	}

	public void executeAttack3() {
		if (_attack3) {
			_canact = false;

			if (!_xtumbling) {
				_xvelocity = 0;
			}
			if (_xtumbling) {
				_image = new Image("sans.jpg");
				_width = 50;

				List<Hitbox> remove = new ArrayList<Hitbox>();
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("blaster") && a.getCharacter().equals(this)) {
						remove.add(a);
						// return;
					}
				}
				for (Hitbox a : remove) {
					TheGame._attacks.remove(a);
				}
				_attack3 = false;

				return;
			}
			// i += 1;
			// _counter += 1;
			int d = 0;
			int p = 0;
			int w = 0;
			if (_facing.equals("right")) {
				d = 50;
				p = _x;
				w = 900;

			} else {
				d = -45;
				p = 0;
				w = _x + 38;

			}
			Hitbox attack = new MeleeHitbox("blaster", this, p + d, _y + 10, w, 50, 25, 10, Color.WHITE);
			attack.setDissappearOnHit(false);

			if (_counter == 30) {
				// TheGame._preattacks.clear();

				TheGame._attacks.add(attack);
				_width = 70;
				if (_facing.equals("right")) {
					_image = new Image("gaster.png");
				} else {
					_image = new Image("gasterleft.png");
				}

			}
			if (_counter == 35) {
				List<Hitbox> remove = new ArrayList<Hitbox>();
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("blaster") && a.getCharacter().equals(this)) {
						remove.add(a);
					}
					// s System.out.println(a);
				}
				for (Hitbox a : remove) {
					TheGame._attacks.remove(a);
				}

				// System.out.println("here");
				attack.setIsGone(true);

			}
			if (_counter == 58) {
				_canact = true;
				_attack3 = false;
				_width = 50;
				_image = new Image("sans.jpg");
			}

		}
	}

	public void attackU() {
		if (_ultcharge >= 150) {
			_attacku = true;
			_canact = false;
			_immune = true;
			_y -= 50;
			_width = 125;
			_height = 125;
			_counter = 0;
			_ultcharge = 0;
			if (_facing.equals("right")) {
				_image = new Image("gaster.png");
			} else {
				_image = new Image("gasterleft.png");
			}
		}

	}

	public void executeAttackU() {
		_yvelocity = 0;
		_xvelocity = 0;
		int d;
		int p;
		int w;
		if (_facing.equals("right")) {
			d = 125;
			p = _x;
			w = 900;

		} else {
			d = -35;
			p = 0;
			w = _x + 38;
		}
		Hitbox attack = new MeleeHitbox("ublaster", this, p + d, _y + 12, w, 100, 3, 2.5, Color.WHITE);
		attack.setDissappearOnHit(false);
		Hitbox attack1 = new MeleeHitbox("ublaster1", this, p + d, _y + 51, w, 25, 3, 2.5, Color.WHITE);
		attack1.setDissappearOnHit(false);
		Hitbox attack2 = new MeleeHitbox("ublaster2", this, p + d, _y + 32, w, 50, 3, 2.5, Color.WHITE);
		attack2.setDissappearOnHit(false);

		if (_counter == 20) {

			TheGame._attacks.add(attack1);

		}
		if (_counter == 30) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("ublaster1") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				// s System.out.println(a);
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}

			TheGame._attacks.add(attack2);
		}
		if (_counter == 40) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("ublaster2") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				// s System.out.println(a);
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			TheGame._attacks.add(attack);
		}
		if (_counter == 70) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("ublaster") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				// s System.out.println(a);
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			TheGame._attacks.add(attack2);

		}
		if (_counter == 80) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("ublaster2") && a.getCharacter().equals(this)) {
					remove.add(a);
				}

			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			TheGame._attacks.add(attack1);

		}
		if (_counter == 90) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("ublaster1") && a.getCharacter().equals(this)) {
					remove.add(a);
				}

			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
		}
		if (_counter == 100) {
			_canact = true;
			_attacku = false;
			_image = new Image("sans.jpg");
			_width = 50;
			_height = 75;
			_immune = false;

		}

	}

	@Override
	public Color getColor() {

		return Color.WHITE;
	}

	@Override
	public Image getStockImage() {
		return new Image("sansstock.jpg");
	}

	@Override
	public void respawn() {

		_attack2 = false;
		_attack3 = false;
		super.respawn();
		_canact = true;
		_image = new Image("sans.jpg");
		_width = 50;
		_height = 75;
	}
}
