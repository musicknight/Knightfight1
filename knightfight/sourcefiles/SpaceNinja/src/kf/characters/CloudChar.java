package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.OffsetHitbox;
import kf.TheGame;

public class CloudChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _diving;
	private boolean _hitdive;
	private boolean _reset = false;
	private int _ultheight = 0;

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
			_width = 80;
			_height = 70;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
			if(_reset) {
				_y+=40;
				_reset = false;
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
		if (_counter == 12) {
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

			Hitbox attack = new HitboxImpl("slashprojectile", this, false, _x + s, _y - 5, 75, 75, v, 0, 10, 20, i);
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("slashprojectile") && a.getCharacter().equals(this)) {

					return;
				}
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud1.wav");
		}
		if (_counter == 17) {
			_y -= 41;
			_width = 60;
			_height = 110;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudslice3.png");
			} else {
				_image = new Image("cloud/cloudslice3left.png");
			}
		}
		if (_counter == 25) {
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
		_xvelocity = 0;

	}

	public void executeAttack2() {
		
		if (_counter == 0) {
			_width = 63;
			_height = 70;
			if (_facing.equals("right")) {
				_image = new Image("cloud/dash1.png");
			} else {
				_image = new Image("cloud/dash1left.png");
				_x += 23;
			}
		}
		if (_counter == 18) {
			//dash and hitbox start here
			_width = 77;
			_height = 49;
			_y += 21;
			if (_facing.equals("right")) {
				_image = new Image("cloud/dash2.png");
				_xvelocity = 39;
			} else {
				_image = new Image("cloud/dash2left.png");
				_xvelocity = -35;
			}
			_xtumbling = true;
			Hitbox attack = new OffsetHitbox("clouddash1", this, 20, 5, 44, 31, 20, 20, _clear);
			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud2.wav");
			
		}
		if (_counter == 22) {
			_width = 86;
			_height = 62;
			int x;
			if (_facing.equals("right")) {
				_image = new Image("cloud/dash3.png");
				x = 20;
			} else {
				_image = new Image("cloud/dash3left.png");

			}
			
					
		}
		if (_counter == 26) {
			_width = 70;
			_height = 100;
			if (_facing.equals("right")) {
				_image = new Image("cloud/dash4.png");
			} else {
				_image = new Image("cloud/dash4left.png");
				//_x-=23;
			}
		}
		if (_counter == 28) {
			_width = 62;
			_height = 110;
			if (_facing.equals("right")) {
				_image = new Image("cloud/dash5.png");
			} else {
				_image = new Image("cloud/dash5left.png");
				//_x-=23;
			}
		}
		if (_counter == 30) {
			_width = 43;
			_height = 110;
			if (_facing.equals("right")) {
				_image = new Image("cloud/dash6.png");
			} else {
				_image = new Image("cloud/dash6left.png");
				//_x-=23;
			}
			TheGame.clearHitboxes("clouddash1", this);
		}
		if(_counter == 50) {
			_reset = true;
			_attack2 = false;
			_canact = true;
			
		}
		
	}

	@Override
	public void attack3() {
		_attack3 = true;
		_yvelocity = -18;
		_canact = false;
		_y -= 31;
		_height = 100;
		_width = 45;
		_counter = 0;
		if (_facing.equals("right")) {
			_image = new Image("cloud/cloudjump1.png");
		} else {
			_image = new Image("cloud/cloudjump1left.png");
			_x+=20;
		}
		TheGame.playSound("/cloud/sounds/cloud3.wav");

	}

	public void executeAttack3() {

		if (_xtumbling && false) {
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloud.png");
			} else {
				_image = new Image("cloud/cloudleft.png");
			}
			_width = 80;
			_height = 70;
			_attack2 = false;
			_canact = true;
			TheGame.clearHitboxes("clouddive", this);
			return;
		}
		if (_onplatform && _diving) {
			_canact = true;
			_y -= 11;
			_height = 70;
			_width = 80;
			_x+=20;
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
		if (!_diving && _counter == 15) {
			_width = 105;
			_height = 60;
			_xvelocity = 0;
			int x;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudjump2.png");
				x=26;
			} else {
				_image = new Image("cloud/cloudjump2left.png");
				_x -= 60;
				x=0;
			}
			Hitbox attack = new OffsetHitbox("clouddive", this, x, 36, 78, 12, 25, 25, _clear);
			TheGame._attacks.add(attack);

			_yvelocity = 10;
			_diving = true;
			TheGame.playSound("/cloud/sounds/cloud4.wav");
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
			_ultheight = _y;
			if (_facing.equals("right")) {
				_image = new Image("cloud/cloudpreult.png");
			} else {
				_image = new Image("cloud/cloudpreultleft.png");
			}
			TheGame.playSound("/cloud/sounds/cloud5.wav");
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
			attack = new HitboxImpl("omnislash", this, false, 1, _ultheight, 53, 87, 30, 0, 25, 25, r);
		} else {
			attack = new HitboxImpl("omnislash", this, false, 840, _ultheight, 53, 87, -30, 0, 25, 25, l);
		}
		attack.setHOrientation(true);
		if (_counter == 20) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 30) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 40) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 50) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 60) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 70) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 80) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
		}
		if (_counter == 90) {

			TheGame._attacks.add(attack);
			TheGame.playSound("/cloud/sounds/cloud5.wav");
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
