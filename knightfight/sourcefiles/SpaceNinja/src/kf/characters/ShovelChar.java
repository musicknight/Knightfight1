package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.CharLinkedHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.MeleeHitbox;
import kf.OffsetHitbox;
import kf.TheGame;

public class ShovelChar extends CharacterImpl {
	private boolean _attack2;
	private boolean _attack3;

	private double _holder;
	private Image _diveimage = new Image("dive.png");
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
		if (_cd != 0 && !_intro) {

			if (_width == 50) {
				_image = f;
			}
			_width = (int) ((int) 50 * 1.3);

		}
		if (_cd == 0 && !_attack2 && !_attack3 && !_attacku&&!_intro) {
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
		if (!_attack2 && !_attack3 && !_attacku && !_intro) {
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
				v = 14;/// d
				s = 50;
			} else {
				v = -14;
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
			TheGame.playSound("/sounds/shovel1.wav");
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
		TheGame.clearHitboxes("dive", this);

		if (!_onplatform) {
			TheGame._attacks.add(attack);

		}
		for (Hitbox a : TheGame._attacks) {
			if (a.checkCollide() && !_otherchar.isImmune()) {
				_y = _otherchar.getY() - _height;
				_yvelocity = -17;
				TheGame._attacks.add(attack);
				TheGame.playSound("/sounds/shovel2.wav");
			}
		}
		if (_onplatform) {
			TheGame.clearHitboxes("dive", this);
			_attack2 = false;
		}

	}

	@Override
	public void attack3() {
		if (true) {
			_attack2 = false;
			TheGame.clearHitboxes("dive", this);
			_attack3 = true;
			_width = (int)(36*1.65);
			_height = (int)(32*1.65);
			if (_facing.equals("right")) {
				_image = new Image("shovel/smash1.png");
			} else {
				_image = new Image("shovel/smash1left.png");
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
				TheGame.clearHitboxes("shovelsmash", this);
			}	
			
			if(_counter == 4) {
				if (_facing.equals("right")) {
					_image = new Image("shovel/smash2.png");
				} else {
					_image = new Image("shovel/smash2left.png");
				}
			}
			if(_counter == 8) {
				if (_facing.equals("right")) {
					_image = new Image("shovel/smash3.png");
				} else {
					_image = new Image("shovel/smash3left.png");
				}
			}
			if(_counter == 17) {
				_width = (int)(86*1.65);
				_height = (int)(33*1.65);
				int x;
				if (_facing.equals("right")) {
					_image = new Image("shovel/smash4.png");
					_x-=(int)(20*1.65);
					x = (int)(45*1.65);
				} else {
					_image = new Image("shovel/smash4left.png");
					_x-=(int)(31*1.65);
					x = 0;
				}
				Hitbox attack = new OffsetHitbox("shovelsmash", this, x, 0, (int)(41*1.65), (int)(33*1.65), 34, 30, _clear);
				if (_facing.equals("right")) {
					attack.setForceRight(true);
				} else {
					attack.setForceLeft(true);
				}
				TheGame._attacks.add(attack);
				TheGame.playSound("/sounds/shovel3.wav");
				
			}
			if(_counter == 20) {
				if (_facing.equals("right")) {
					_image = new Image("shovel/smash5.png");
				} else {
					_image = new Image("shovel/smash5left.png");
				}
				TheGame.clearHitboxes("shovelsmash" , this);
			}
			if(_counter == 22) {
				if (_facing.equals("right")) {
					_image = new Image("shovel/smash6.png");
				} else {
					_image = new Image("shovel/smash6left.png");
				}
			}
			if(_counter == 38) {
				_attack3 = false;
				
				if (_facing.equals("right")) {
					_image = new Image("shovel/smash5.png");
					_x+=(int)(20*1.65);
				} else {
					_image = new Image("shovel/smash5left.png");
					_x+=(int)(31*1.65);
				}
				_width = 50;
				_height = 50;
				_canact = true;
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
			TheGame.playSound("/sounds/shovel4.wav");
		}

		if (_counter == 18) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 325, 75, 75, -23, 0, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 28) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 1, 1, 75, 75, 23, 23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 38) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 0, 75, 75, -23, 23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 48) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 599, 75, 75, -23, -23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 58) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 599, 75, 75, 23, -23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 68) {

			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 325, 75, 75, 23, 0, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}

		if (_counter == 78) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 325, 75, 75, -23, 0, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 88) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 1, 1, 75, 75, 23, 23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 98) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 0, 75, 75, -23, 23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 108) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 874, 599, 75, 75, -23, -23, 25, 25, l));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}
		if (_counter == 118) {
			Hitbox a = (new HitboxImpl("shieldknight,", this, false, 0, 599, 75, 75, 23, -23, 25, 25, r));
			a.setHOrientation(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/sounds/shovel4.wav");
		}

		if (_counter == 130) {
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
	
	@Override
	public void doIntro() {
		if(_counter ==0) {
			_width = (int)(35*1.7);
			_height = (int)(47*1.7);
			if (_facing.equals("right")) {
				_image = new Image("shovel/sintro1.png");
			} else {
				_image = new Image("shovel/sintro1left.png");
			}
			TheGame.playSound("/sounds/sintro.wav");
		}
		if(_counter == 5){
			if (_facing.equals("right")) {
				_image = new Image("shovel/sintro2.png");
			} else {
				_image = new Image("shovel/sintro2left.png");
			}
		}
		if(_counter == 10){
			if (_facing.equals("right")) {
				_image = new Image("shovel/sintro3.png");
			} else {
				_image = new Image("shovel/sintro3left.png");
			}
		}
		if(_counter == 15){
			if (_facing.equals("right")) {
				_image = new Image("shovel/sintro4.png");
			} else {
				_image = new Image("shovel/sintro4left.png");
			}
		}
		if(_counter == 49) {
			
			if (_facing.equals("right")) {
				_image = new Image("shovel1right.png");
				
			} else {
				_image = new Image("shovel1left.png");
				
			}
			_width = 50;
			_height = 50;
			_y+=30;
		}
	}
}
