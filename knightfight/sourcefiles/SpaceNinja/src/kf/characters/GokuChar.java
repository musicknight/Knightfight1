package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.Hitbox;
import kf.MeleeHitbox;
import kf.OffsetHitbox;
import kf.TheGame;

public class GokuChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private double _holder;
	private int _cd1;
	private int _cd2;
	private int _cd3;
	private int _ucounter;
	// checks if the punch hitbox is gone
	boolean _punchgone = true;

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
		if(_cd2 == 0) {
			int x;
			if(_ID.equals("one")){
				x = 50;
			} else {
				x = 140;
			}
			TheGame._gc.drawImage(new Image("goku/vanish1.png"), x, 160, 25, 25);
		}
		if(_cd3 == 0) {
			int x;
			if(_ID.equals("one")){
				x = 50;
			} else {
				x = 140;
			}
			TheGame._gc.drawImage(new Image("goku/kame.png"), x + 26, 160, 25, 25);
		}
		
	}

	@Override
	public Image getStockImage() {
		return new Image("goku/stock.png");
	}

	@Override
	public void attack1() {
		if (_cd1 == 0 && !_attack2) {
			_attack1 = true;
			_counter = 0;
			_width = 48;
			_height = 74;

			_holder = _damage;
			_canact = false;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/kick1.png");
				} else {
					_image = new Image("goku/kick1left.png");
				}
				_cd1 = 55;
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ukick1.png");
				} else {
					_image = new Image("goku/ukick1left.png");
				}
			}
			TheGame.playSound("/goku/sounds/goku1.wav");
		}

	}

	public void executeAttack1() {

		if (_counter < 8) {
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
		
			_width = 50;
			_height = 70;
			_attack1 = false;
			_canact = false;
			return;
		}
		if (_counter == 8) {
			_y += 2;
			_width = 62;
			_height = 70;
			
			if (!_attacku) {
				int x;
				if (_facing.equals("right")) {
					_image = new Image("goku/kick2.png");
					_xvelocity = 15;
					x=50;
				} else {
					_image = new Image("goku/kick2left.png");
					_xvelocity = -15;
					x=0;
					_x -=10;
				}
				Hitbox attack = new OffsetHitbox("gokupunch", this, x, 32, 22, 6, 27, 16, _clear);
				//_xtumbling = true;
				TheGame._attacks.add(attack);
			} else {
				int x;
				if (_facing.equals("right")) {
					_image = new Image("goku/ukick2.png");
					_xvelocity = 23;
					x=50;
				} else {
					_image = new Image("goku/ukick2left.png");
					_xvelocity = -23;
					x=0;
					_x-=10;
				}
				Hitbox attack = new OffsetHitbox("gokupunch", this, x, 32, 22, 6, 50, 50, _clear);
				//_xtumbling = true;
				TheGame._attacks.add(attack);
				
			}
			
		}
		if(_counter == 12) {
			TheGame.playSound("/goku/sounds/goku2.wav");
		}
		
		
		if (_counter == 28) {

			_width = 50;
			_xvelocity = 0;
			_attack1 = false;
			_canact = true;
			TheGame.clearHitboxes("gokupunch", this);
		}
	}

	@Override
	public void attack2() {

		if (_cd2 == 0 && !_attack3) {
			_immune = true;
			_attack2 = true;
			_counter = 0;
			_cd3 = 45;
			_image = new Image("goku/vanish1.png");
			_cd2 = 140;
			TheGame.playSound("/goku/sounds/goku3.wav");
		}

	}

	public void executeAttack2() {
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
		if (_cd3 == 0 && !_attack2 && !_attack3) {
			_counter = 0;
			_attack3 = true;
			_canact = false;

			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge1.png");
				} else {
					_image = new Image("goku/charge1left.png");
				}

				_cd3 = 175;
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge1.png");
				} else {
					_image = new Image("goku/ucharge1left.png");
				}
			}
			TheGame.playSound("/goku/sounds/goku4.wav");
		}

	}

	public void executeAttack3() {
		_xvelocity = 0;
		_yvelocity = 0;
		_canact = false;
			_otherchar.setGravity(true);
		
		if (_counter == 25) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha1", this, _x + 50, _y - 15, 246, 100, 10, 1,
							new Image("goku/kamehameha1.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha1", this, _x - 246, _y - 15, 246, 100, 10, 1,
							new Image("goku/kamehameha1left.png"));
				}
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha1", this, _x + 50, _y - 15, 246, 100, 18, 3,
							new Image("goku/ukamehameha1.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha1", this, _x - 246, _y - 15, 246, 100, 18, 3,
							new Image("goku/ukamehameha1left.png"));
				}
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			}
		}
		if(_counter == 27) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha2", this, _x + 50, _y - 15, 300, 100, 10, 1,
							new Image("goku/kamehameha2.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha2", this, _x - 300, _y - 15, 300, 100, 10, 1,
							new Image("goku/kamehameha2left.png"));
				}
				TheGame.clearHitboxes("kamehameha1", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha2", this, _x + 50, _y - 15, 300, 100, 18, 3,
							new Image("goku/ukamehameha2.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha2", this, _x - 300, _y - 15, 300, 100, 18, 3,
							new Image("goku/ukamehameha2left.png"));
				}
				TheGame.clearHitboxes("kamehameha1", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			}
		}
		if(_counter == 29) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha3", this, _x + 50, _y - 15, 360, 100, 10, 1,
							new Image("goku/kamehameha3.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha3", this, _x - 360, _y - 15, 360, 100, 10, 1,
							new Image("goku/kamehameha3left.png"));
				}
				TheGame.clearHitboxes("kamehameha2", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha3", this, _x + 50, _y - 15, 360, 100, 18, 3,
							new Image("goku/ukamehameha3.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha3", this, _x - 360, _y - 15, 360, 100, 18, 3,
							new Image("goku/ukamehameha3left.png"));
				}
				TheGame.clearHitboxes("kamehameha2", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			}
		}
		if(_counter == 31) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha4", this, _x + 50, _y - 15, 418, 100, 10, 1,
							new Image("goku/kamehameha4.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha4", this, _x - 418, _y - 15, 418, 100, 10, 1,
							new Image("goku/kamehameha4left.png"));
				}
				TheGame.clearHitboxes("kamehameha3", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha4", this, _x + 50, _y - 15, 418, 100, 18, 3,
							new Image("goku/ukamehameha4.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha4", this, _x - 418, _y - 15, 418, 100, 18, 3,
							new Image("goku/ukamehameha4left.png"));
				}
				TheGame.clearHitboxes("kamehameha3", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			}
		}
		if(_counter == 33) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha5", this, _x + 50, _y - 15, 506, 100, 10, 1.5,
							new Image("goku/kamehameha5.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha5", this, _x - 506, _y - 15, 506, 100, 10, 1.5,
							new Image("goku/kamehameha5left.png"));
				}
				TheGame.clearHitboxes("kamehameha4", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha5", this, _x + 50, _y - 15, 506, 100, 18, 3,
							new Image("goku/ukamehameha5.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha5", this, _x - 506, _y - 15, 506, 100, 18, 3,
							new Image("goku/ukamehameha5left.png"));
				}
				TheGame.clearHitboxes("kamehameha4", this);
				attack.setDissappearOnHit(false);
				attack.setFreezeY(true);
				TheGame._attacks.add(attack);
			}
		}
		if (_counter == 60 && false) {
			Hitbox attack;
			if (!_attacku) {
				if (_facing.equals("right")) {
					_image = new Image("goku/charge2.png");
					attack = new MeleeHitbox("kamehameha", this, _x + 50, _y - 15, 506, 100, 20, 2,
							new Image("goku/kamehameha5.png"));
				} else {
					_image = new Image("goku/charge2left.png");
					attack = new MeleeHitbox("kamehameha", this, _x - 506, _y - 15, 506, 100, 20, 2,
							new Image("goku/kamehameha5left.png"));
					TheGame.clearHitboxes("kamehameha5", this);
					TheGame._attacks.add(attack);
				}
			} else {
				if (_facing.equals("right")) {
					_image = new Image("goku/ucharge2.png");
					attack = new MeleeHitbox("kamehameha", this, _x + 50, _y - 15, 500, 100, 20, 2,
							new Image("goku/ukamehameha5.png"));
				} else {
					_image = new Image("goku/ucharge2left.png");
					attack = new MeleeHitbox("kamehameha", this, _x - 500, _y - 15, 500, 100, 20, 2,
							new Image("goku/ukamehameha5left.png"));
					TheGame.clearHitboxes("kamehameha5", this);
					TheGame._attacks.add(attack);
				}
			}
		}

		if (_counter == 61) {
			_otherchar.setGravity(true);
			TheGame.clearHitboxes("kamehameha", this);
			TheGame.clearHitboxes("kamehameha5", this);
			

		}
		if(_counter == 82) {
			
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
			TheGame.playSound("/goku/sounds/gokuu.wav");
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
