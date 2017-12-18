package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GokuChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private double _holder;
	private int _cd1;
	private int _cd2;
	private int _cd3;
	private int _ucounter;

	public GokuChar(String ID) {
		super(ID);
		if (_facing.equals("right")) {
			_image = new Image("goku/goku.png");
		} else {
			_image = new Image("goku/gokuleft.png");
		}
		_width = 50;
		_height = 70;
		_speedfactor = 1.1;
		_damagefactor = 1.07;
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if (!_attack1 && !_attack2 && !_attack3 && !_attacku) {
			if (_facing.equals("right")) {
				_image = new Image("goku/goku.png");
			} else {
				_image = new Image("goku/gokuleft.png");
			}
		}
		if (_attacku) {
			executeAttackU();
			if (!_attack1 && !_attack2 && !_attack3 && _ucounter >= 10 && _ucounter < 360) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
		}

		if (_attack1) {
			executeAttack1();
		}
		if (_attack2) {
			executeAttack2();
		}
		if (_attack3) {
			executeAttack3();
		}
	}

	@Override
	public Image getStockImage() {
		return new Image("goku/stock.png");
	}

	@Override
	public void attack1() {
		if (_cd1 == 0) {
			_attack1 = true;
			_counter = 0;
			_y -= 31;
			_width = 120;
			_height = 100;

			_holder = _damage;
			_canact = false;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/punch1.png");
				} else {
					_image = new Image("goku/punch1left.png");
				}
				_cd1 = 55;
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/upunch1.png");
				} else {
					_image = new Image("goku/upunch1left.png");
				}
			}
		}

	}

	public void executeAttack1() {

		if (_counter < 15) {
			_xvelocity = 0;
			_yvelocity = 0;
		}
		if (_holder != _damage) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("gokupunch") && a.getCharacter().equals(this)) {
					remove.add(a);
				}

			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			if (_counter < 15) {
				_y += 29;

			}
			_width = 50;
			_height = 70;
			_attack1 = false;
			_canact = false;
			return;
		}
		if (_counter == 15) {
			_y += 29;
			_width = 70;
			_height = 70;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/punch2.png");
					_xvelocity = 20;
				} else {
					_image = new Image("goku/punch2left.png");
					_xvelocity = -20;
				}
				Hitbox attack = new CharLinkedHitbox("gokupunch", this, 27, 16);
				_xtumbling = true;
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/upunch2.png");
					_xvelocity = 40;
				} else {
					_image = new Image("goku/upunch2left.png");
					_xvelocity = -40;
				}
				Hitbox attack = new CharLinkedHitbox("gokupunch", this, 50, 50);
				_xtumbling = true;
				TheGame._attacks.add(attack);
			}
		}
		if (_counter == 39) {

			_width = 50;
			_attack1 = false;
			_canact = true;
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("gokupunch") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				// s System.out.println(a);
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
		}
	}

	@Override
	public void attack2() {

		if (_cd2 == 0) {
			_immune = true;
			_attack2 = true;
			_canact = false;
			_counter = 0;
			_image = new Image("goku/vanish1.png");
			_cd2 = 100;
		}

	}

	public void executeAttack2() {
		_xvelocity = 0;
		_yvelocity = 0;
		if (_counter == 3) {
			_image = new Image("goku/vanish2.png");
		}
		if (_counter == 5) {
			_image = new Image("goku/vanish3.png");
		}
		if (_counter == 25) {
			_immune = false;
			_attack2 = false;
			_canact = true;
		}

	}

	@Override
	public void attack3() {
		if (_cd3 == 0) {
			_counter = 0;
			_attack3 = true;
			_canact = false;

			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge1.png");
				} else {
					_image = new Image("goku/charge1left.png");
				}

				_cd3 = 125;
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge1.png");
				} else {
					_image = new Image("goku/ucharge1left.png");
				}
			}
		}

	}

	public void executeAttack3() {
		_xvelocity = 0;
		_yvelocity = 0;
		if (_counter == 25) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha", this, _x + 50, _y - 15, 500, 100, 10, 2,
							new Image("goku/kamehameha.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha", this, _x - 500, _y - 15, 500, 100, 10, 2,
							new Image("goku/kamehamehaleft.png"));
				}
				attack.setDissappearOnHit(false);
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha", this, _x + 50, _y - 15, 500, 100, 18, 3,
							new Image("goku/ukamehameha.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha", this, _x - 500, _y - 15, 500, 100, 18, 3,
							new Image("goku/ukamehamehaleft.png"));
				}
				attack.setDissappearOnHit(false);
				TheGame._attacks.add(attack);
			}
		}
		if (_counter == 49) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha", this, _x + 50, _y - 15, 500, 100, 20, 2,
							new Image("goku/kamehameha.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha", this, _x - 500, _y - 15, 500, 100, 20, 2,
							new Image("goku/kamehamehaleft.png"));
					TheGame._attacks.add(attack);
				}
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha", this, _x + 50, _y - 15, 500, 100, 20, 2,
							new Image("goku/ukamehameha.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha", this, _x - 500, _y - 15, 500, 100, 20, 2,
							new Image("goku/ukamehamehaleft.png"));
					TheGame._attacks.add(attack);
				}
			}
		}

		if (_counter == 50) {

			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("kamehameha") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				// s System.out.println(a);
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			_attack3 = false;
			_canact = true;

		}
	}

	@Override
	public void attackU() {
		if (_ultcharge >= 150) {
			_attacku = true;
			_ucounter = 0;
			_y -= 31;
			_width = 120;
			_height = 100;
			_immune = true;
			_canact = false;
			_gravity = false;
			_xvelocity = 0;
			_yvelocity = 0;
			_ultcharge = 0;

			if (_facing.equals("right")) {
				_image = new Image("goku/ult.png");
			} else {
				_image = new Image("goku/ultleft.png");
			}
		}
	}

	public void executeAttackU() {
		if (_ucounter == 10) {
			_canact = true;
			_width = 50;
			_height = 70;
			_y += 30;
		}
		if (!_attack1 && !_attack2 && !_attack3) {
			if (_ucounter == 360) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 362) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 364) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 366) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 368) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 370) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 372) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 374) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 376) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 378) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 380) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 382) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 384) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 386) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 388) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 390) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 392) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 394) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
			if (_ucounter == 396) {
				if (_facing.equals("right")) {
					_image = new Image("goku/gokuu.png");
				} else {
					_image = new Image("goku/gokuuleft.png");
				}
			}
			if (_ucounter == 398) {
				if (_facing.equals("right")) {
					_image = new Image("goku/goku.png");
				} else {
					_image = new Image("goku/gokuleft.png");
				}
			}
		}
		if (_ucounter == 400) {
			_attacku = false;
			_immune = false;
			_gravity = true;
		}
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public void incrementCounter() {
		if (_cd1 > 0) {
			_cd1--;
		}
		if (_cd2 > 0) {
			_cd2--;
		}
		if (_cd3 > 0) {
			_cd3--;
		}
		_ucounter++;
		super.incrementCounter();

	}

	@Override
	public void pressUp() {
		if (_attacku) {
			_yvelocity = -5.5;
		}
	}

	@Override
	public void pressDown() {
		if (_attacku) {
			_yvelocity = 5.5;
		}
	}

	public void releaseUp() {
		if (_attacku) {
			_yvelocity = 0;
		}
	}

	public void releaseDown() {
		if (_attacku) {
			_yvelocity = 0;
		}
	}

	public void pressJump() {
		if (_attacku) {
			_yvelocity = -5.5;
		} else {
			super.pressJump();
		}
	}

	@Override
	public void releaseJump() {
		if (_attacku) {
			_yvelocity = 0;
		}
	}

}
