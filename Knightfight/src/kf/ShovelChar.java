package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ShovelChar extends CharacterImpl {
	private boolean _attack2;
	private boolean _attack3;

	private double _holder;
	private Image _diveimage = new Image("dive.jpg");
	private Image _chargeimage = new Image("shovelcharge.png");
	private Image _hitimage = new Image("shovelhit.png");

	public ShovelChar(String ID) {
		super(ID);
		_image = new Image("shovel1right.png");
		_width = 50;
		_height = 50;
		_damagefactor = 1.12;
		_speedfactor = 1.12;

	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		Image f = new Image("fireballspriteright.png");
		Image r = new Image("shovel1right.png");
		if (_facing.equals("left")) {
			f = new Image("fireballspriteleft.png");
			r = new Image("shovel1left.png");
		}
		if (_cd != 0) {

			if (_width == 50) {
				_image = f;
			}
			_width = (int) ((int) 50 * 1.3);

		}
		if (_cd == 0 && !_attack2 && !_attack3 && !_attacku) {
			_width = 50;
			_height = 50;
			_image = r;

		}
		if (_attack2) {

			if (!_image.equals(_diveimage)) {
				_image = new Image("dive.jpg");
			}
			_height = 60;
			_width = 40;
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
	public void move() {
		super.move();
		if (!_attack2 && !_attack3 && !_attacku) {
			if (_facing.equals("right") && _cd == 0) {
				_image = new Image("shovel1right.png");
				_width = 50;
			} else if (_cd == 0) {
				_image = new Image("shovel1left.png");
				_height = 50;
			}
		}
	}

	@Override
	public void attack1() {

		if (!_attack2) {
			double v;
			int s;
			_counter = 0;
			_cd = 10;
			if (_facing.equals("right")) {
				v = 9;/// d
				s = 50;
			} else {
				v = -9;
				s = -15;
			}
			Hitbox attack = new HitboxImpl("flare", this, false, _x + s, _y + 14, 30, 30, v, 0, 6, 15,
					new Image("fireball.png"));
			// System.out.println("attack added");
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("flare") && a.getCharacter().equals(this)) {

					return;
				}
			}
			TheGame._attacks.add(attack);
		}

	}

	@Override
	public void attack2() {
		if (!_onplatform) {
			_image = _diveimage;
			_attack2 = true;
			_holder = _damage;

		}
	}

	public void executeAttack2() {
		Hitbox attack = new CharLinkedHitbox("dive", this, 13, 14);
		List<Hitbox> remove = new ArrayList<Hitbox>();
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("dive") && a.getCharacter().equals(this)) {
				remove.add(a);
			}
		}
		for (Hitbox a : remove) {
			TheGame._attacks.remove(a);
		}

		if (!_onplatform) {
			TheGame._attacks.add(attack);

		}
		for (Hitbox a : TheGame._attacks) {
			if (a.checkCollide() && !_otherchar.isImmune()) {
				_yvelocity = -17;
			}
		}
		if (_onplatform) {
			_attack2 = false;
		}

	}

	@Override
	public void attack3() {
		if (!_attack2) {
			_attack3 = true;
			if (_facing.equals("right")) {
				_image = _chargeimage;
			} else {
				_image = new Image("shovelchargeleft.png");
			}
			_counter = 0;
		}

	}

	public void executeAttack3() {
		if (_attack3) {
			_canact = false;

			if (!_xtumbling) {
				_xvelocity = 0;
			}
			if (_xtumbling) {
				if (_facing.equals("right")) {
					_image = new Image("shovel1right.png");
				} else {
					_image = new Image("shovel1left.png");
				}
				_attack3 = false;
				List<Hitbox> remove = new ArrayList<Hitbox>();
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("shovel") && a.getCharacter().equals(this)) {
						remove.add(a);
						// return;
					}
				}
				for (Hitbox a : remove) {
					TheGame._attacks.remove(a);
				}
			}
			int d = 0;
			int p = 0;
			int w = 0;
			Image charge = null;
			Image hit = null;
			Image shovel = null;
			if (_facing.equals("right")) {
				d = 50;
				p = _x;
				charge = _chargeimage;
				hit = new Image("shovelhit.png");
				shovel = new Image("shovelimage.jpg");
			} else {
				d = -75;
				p = _x;
				charge = new Image("shovelchargeleft.png");
				hit = new Image("shovelhitleft.png");
				shovel = new Image("shovelimageleft.jpg");
			}
			Hitbox attack = new MeleeHitbox("shovel", this, p + d, _y + 15, 75, 20, 35, 30, shovel);

			if (_counter == 30) {
				// TheGame._preattacks.clear();
				_image = hit;
				TheGame._attacks.add(attack);

			}
			if (_counter == 57) {
				List<Hitbox> remove = new ArrayList<Hitbox>();
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("shovel") && a.getCharacter().equals(this)) {
						TheGame._attacks.remove(a);
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
				if (_facing.equals("right")) {
					_image = new Image("shovel1right.png");
				} else {
					_image = new Image("shovel1left.png");
				}

			}
		}
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.AQUA;
	}

	@Override
	public void attackU() {
		if (_ultcharge >= 150) {
			_ultcharge = 0;
			_canact = false;
			_attacku = true;
			_attack2 = false;
			_attack3 = false;
			_immune = true;
			_counter = 0;
			_y -= 21;
			_height = 70;
			_image = new Image("shovelult.png");
		}

	}

	public void executeAttackU() {
		_xvelocity = 0;
		_yvelocity = 0;
		Image r = new Image("shieldknight.png");
		Image l = new Image("shieldknightleft.png");
		if (_counter == 8) {

			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 325, 75, 75, 23, 0, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}

		if (_counter == 18) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 325, 75, 75, -23, 0, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 28) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 1, 1, 75, 75, 23, 23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 38) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 0, 75, 75, -23, 23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 48) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 599, 75, 75, -23, -23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 58) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 599, 75, 75, 23, -23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 68) {

			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 325, 75, 75, 23, 0, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}

		if (_counter == 78) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 325, 75, 75, -23, 0, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 98) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 1, 1, 75, 75, 23, 23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 108) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 0, 75, 75, -23, 23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 118) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 599, 75, 75, -23, -23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}
		if (_counter == 128) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 599, 75, 75, 23, -23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
		}

		if (_counter == 140) {
			_attacku = false;
			_immune = false;
			_canact = true;
		}
	}

	@Override
	public Image getStockImage() {
		return new Image("shovelstock.png");
	}

	@Override
	public void respawn() {
		_attack2 = false;
		_attack3 = false;
		super.respawn();
		_canact = true;
	}
}
