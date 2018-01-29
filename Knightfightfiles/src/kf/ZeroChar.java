package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ZeroChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private int _attack1stage = 0;
	private int _cd1 = 0;
	private int _cd2 = 0;
	private int _cd3 = 0;
	private boolean _canattack3;
	private double _holder;
	private int _timesspun;
	
	//stores how far attack1 stage 2 displaced

	
	
	public ZeroChar(String ID) {
		super(ID);
		_width = 51;
		_height = 60;
		_speedfactor = 1.1;
		_damagefactor = 1;
		
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku){
			if(_facing.equals("right")) {
				_image = new Image("zero/zero.gif");
			} else {
				_image = new Image("zero/zeroleft.gif");
			}
			_width = 51;
			_height = 60;
			_getxleft = true;
		}
		if(_attack1 && !_attack2) {
			executeAttack1();
		}
		if(_attack2 && !_attack1) {
			executeAttack2();
		}
		if(_attack3 && !_attack1) {
			executeAttack3();
		}
		if(_attacku) {
			executeAttackU();
		}
		if(!_canattack3) {
			if(_onplatform) {
				_canattack3 = true;
			}
		}
	}
	
	@Override
	public Image getStockImage() {
		return new Image("zero/stock.gif");
	}

	@Override
	public void attack1() {
		if(!_attack2) {
		if(_attack1stage == 0 && _cd1 == 0) {
		_counter = 0;
		_attack1stage = 1;
		_canact = false;
		_xvelocity = 0;
		_cd1 = 50;
		_holder = _damage;
		_attack1 = true;
		_width = 48;
		_height = 54;
		_y+=6;
		
		if(_facing.equals("right")) {
			_image = new Image("zero/attack1/slash1.gif");
		} else {
			_image = new Image("zero/attack1/slash1left.gif");
			_getxleft = false;
		}
		
		}
		if(_attack1stage == 1 && _counter >= 14) {
			_counter = 0;
			_attack1stage = 2;
			int x;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slice1.gif");
				_xvelocity = 8;
				x = 54;
			} else {
				_image = new Image("zero/attack1/slice1left.gif");
				_xvelocity = -8;
				_x+=((_width - 108));
				x = 0;
			}
			_width = 108;
			_height = 63;
			_y -= 15;
			_xtumbling = true;
			TheGame.clearHitboxes("zslash2", this);
			Hitbox attack = new OffsetHitbox("zslice1", this, x, 0, 54, 48, 5, 3.5, _clear);
			attack.setSetKnockback(true);
			TheGame._attacks.add(attack);
			TheGame.playSound("/zero/sounds/zero1.wav");
		}
		if(_attack1stage == 2 && _counter >= 8) {
			_getxleft = true;
			_counter = 0;
			_attack1stage = 3;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/bolt1.gif");
				_xvelocity = 8;
				
			} else {
				_image = new Image("zero/attack1/bolt1left.gif");
				_xvelocity = -8;
				_x+=((_width - 105));
				
			}
			_y+=(_height - 57);
			_width = 105;
			_height = 57;
			_xtumbling = true;
			TheGame.playSound("/zero/sounds/zero1.wav");
		}
		}
	}
	
	public void executeAttack1() {
		if(_holder != _damage) {
			_y -= (60 - _height);
			TheGame.clearHitboxes("zslash1", this);
			TheGame.clearHitboxes("zslash2", this);
			TheGame.clearHitboxes("zslice1", this);
			_attack1stage = 0;
			_attack1 = false;
			_canact = true;
			_getxleft = true;
		}
		
		
		if(_attack1stage == 1) {
		if(_counter == 2) {
			_width = 54;
			_height = 60;
			_y-=6;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash2.gif");
			} else {
				_image = new Image("zero/attack1/slash2left.gif");
			}
		}
		if(_counter == 4) {
			_width = 57;
			_height = 63;
			_y-=3;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash3.gif");
			} else {
				_image = new Image("zero/attack1/slash3left.gif");
			}
			TheGame.playSound("/zero/sounds/zero1.wav");
		}
		if(_counter == 6) {
			//slash happens here
			_width = 120;
			_height = 90;
			_y-=27;
			int x;
			
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash4.gif");
				_xvelocity = 7;
				x = 70;
			} else {
				_image = new Image("zero/attack1/slash4left.gif");
				_x-=58;
				_xvelocity = -7;
				x = 0;
			}
			_xtumbling = true;
			Hitbox attack = new OffsetHitbox("zslash1", this, x, 0, 50, 90, 3, 3.5, _clear);
			attack.setSetKnockback(true);
			TheGame._attacks.add(attack);
		}
		if(_counter == 8) {
			int x;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash5.gif");
				x = 70;
			} else {
				_image = new Image("zero/attack1/slash5left.gif");
				x = 0;
			}
			
		}
		if(_counter == 10) {
			_width = 105;
			
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash6.gif");
				_x+=15;
				
			} else {
				_image = new Image("zero/attack1/slash6left.gif");
				
			}
			
		}
		if(_counter == 12) {
			_width = 93;
			_height = 48;
			_y+=42;
			int x;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash7.gif");
				x = 51;
			} else {
				_image = new Image("zero/attack1/slash7left.gif");
				_x+=12;
				x = 0;
			}
			TheGame.clearHitboxes("zslash1", this);
			Hitbox attack = new OffsetHitbox("zslash2", this, x, 30, 54, 12, 10, 7, _clear);
			attack.setSetKnockback(true);
			TheGame._attacks.add(attack);
		}
		if(_counter == 14) {
			_width = 75;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash8.gif");
			} else {
				_image = new Image("zero/attack1/slash8left.gif");
				_x+=18;
			}
			TheGame.clearHitboxes("zslash2", this);
			
		}
		if(_counter == 17) {
			_width = 78;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash9.gif");
			} else {
				_image = new Image("zero/attack1/slash9left.gif");
				_x-=3;
			}
		}
		if(_counter == 20) {
			_width = 60;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slash10.gif");
			} else {
				_image = new Image("zero/attack1/slash10left.gif");
				_x+=18;
			}
		}
		if(_counter == 23) {
			_attack1 = false;
			_canact = true;
			_cd1 = 20;
			_y-=12;
			_attack1stage = 0;
			if(_facing.equals("left")) {
				_x+= 13;
			}
		}
		}
		if(_attack1stage == 2) {
			if(_counter == 2){
				int x;
				if(_facing.equals("right")) {
					_image = new Image("zero/attack1/slice2.gif");
					x=54;
				} else {
					_image = new Image("zero/attack1/slice2left.gif");
					x=0;
				}
				
			}
		if(_counter == 4){
				
				if(_facing.equals("right")) {
				_image = new Image("zero/attack1/slice3.gif");
			} else {
				_image = new Image("zero/attack1/slice3left.gif");
				_x+=27;
			}
				_width = 81;
			
				
		}
		if(_counter == 6){
			
			if(_facing.equals("right")) {
			_image = new Image("zero/attack1/slice4.gif");
		} else {
			_image = new Image("zero/attack1/slice4left.gif");
			_x+=15;
		}
			_width = 66;
			TheGame.clearHitboxes("zslice1", this);
			
		}
		if(_counter == 8){
			if(_facing.equals("right")) {
			_image = new Image("zero/attack1/slice5.gif");
			} else {
			_image = new Image("zero/attack1/slice5left.gif");
			}
		}
		if(_counter == 11){
			
			if(_facing.equals("right")) {
			_image = new Image("zero/attack1/slice6.gif");
			} else {
			_image = new Image("zero/attack1/slice6left.gif");
			_x+=12;
			}
			_width = 54;
		}
		if(_counter == 14){
			
			if(_facing.equals("right")) {
			_image = new Image("zero/attack1/slice7.gif");
			} else {
			_image = new Image("zero/attack1/slice7left.gif");
			_x-=3;
			}
			_width = 57;
			_height = 66;
			_y-=3;
		}
		if(_counter == 17) {
			_attack1 = false;
			_attack1stage = 0;
			_canact = true;
			_y += 6;
		}
		}
		if(_attack1stage == 3) {
			if(_counter == 2){
				//projectile shoots out here
				int x;
				double v;
				Image i;
				if(_facing.equals("right")) {
				_image = new Image("zero/attack1/bolt2.gif");
				x=38;
				v=17;
				i = new Image("zero/attack1/bolt.gif");
				} else {
				_image = new Image("zero/attack1/bolt2left.gif");
				x = 50;
				v=-17;
				i = new Image("zero/attack1/boltleft.gif");
				}
				TheGame._attacks.add(new HitboxImpl("zbolt", this, false, _x+x, _y - 1, 34, 62, v, 0, 16, 24, i));
			}
			if(_counter == 4){
				_y+=3;
				_width = 75;
				_height = 54;
				
				if(_facing.equals("right")) {
				_image = new Image("zero/attack1/bolt3.gif");
				} else {
				_image = new Image("zero/attack1/bolt3left.gif");
				_x+=30;
				}
			}
			if(_counter == 6){
				_width = 78;
				if(_facing.equals("right")) {
				_image = new Image("zero/attack1/bolt4.gif");
				} else {
				_image = new Image("zero/attack1/bolt4left.gif");
				_x-=3;
				}
			}
			if(_counter == 9){
				_width = 66;
				if(_facing.equals("right")) {
				_image = new Image("zero/attack1/bolt5.gif");
				} else {
				_image = new Image("zero/attack1/bolt5left.gif");
				_x+=12;
				}
			}
			if(_counter == 12){
				_width = 69;
				if(_facing.equals("right")) {
				_image = new Image("zero/attack1/bolt6.gif");
				} else {
				_image = new Image("zero/attack1/bolt6left.gif");
				_x-=3;
				}
			}
			if(_counter == 15) {
				_attack1 = false;
				_attack1stage = 0;
				_canact = true;
				_y-=6;
				_cd1 = 20;
			}
		}
		
		
	}

	@Override
	public void attack2() {
		if(!_attack1 && _cd2 == 0) {
		_counter = 0;
		_canact = false;
		_attack2 = true;
		_xvelocity = 0;
		if(_facing.equals("right")) {
			_image = new Image("zero/attack2/smash1.gif");
		} else {
			_image = new Image("zero/attack2/smash1left.gif");
		}
		_y-=3;
		_height = 63;
		}
		TheGame.playSound("/zero/sounds/zero2.wav");
	}
	
	public void executeAttack2() {
		if(_counter == 16) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash2.gif");
			} else {
				_image = new Image("zero/attack2/smash2left.gif");
				_x-=3;
			}
			_width = 54;
			_height = 66;
			
			_y-=3;
			
		}
		if(_counter == 18) {
			//hitbox appears here
			int x;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash3.gif");
				x=58;
			} else {
				_image = new Image("zero/attack2/smash3left.gif");
				_x-=72;
				x=20;
			}
			_width = 126;
			_height = 96;
			_y-=30;
			Hitbox attack = new OffsetHitbox("zsmash", this, x, 0, 48, 96, 25, 24, _clear);
			TheGame._attacks.add(attack);
			
		}
		if(_counter == 20) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash4.gif");
			} else {
				_image = new Image("zero/attack2/smash4left.gif");
				_x-=3;
				
			}
			_width = 129;
			_height = 102;
			_y-=6;
		}
		if(_counter == 22) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash5.gif");
			} else {
				_image = new Image("zero/attack2/smash5left.gif");
				_x-=3;
			}
			_width = 132;
			_height = 105;
			_y-=3;
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash6.gif");
			} else {
				_image = new Image("zero/attack2/smash6left.gif");
				
			}
			_height = 111;
			_y-=6;
		}
		if(_counter == 26) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash7.gif");
			} else {
				_image = new Image("zero/attack2/smash7left.gif");
				_x-=3;
			}
			_width = 135;
			TheGame.clearHitboxes("zsmash", this);
		}
		if(_counter == 28) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash8.gif");
			} else {
				_image = new Image("zero/attack2/smash8left.gif");
				_x-=3;
			}
			_width = 138;
			_height = 108;
			_y+=3;
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash9.gif");
			} else {
				_image = new Image("zero/attack2/smash9left.gif");
				_x+=75;
			}
			_width = 63;
			_height = 48;
			_y+=60;
			
		}
		if(_counter == 32) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack2/smash9.gif");
			} else {
				_image = new Image("zero/attack2/smash9left.gif");
				_x+=3;
			}
			_width = 60;
		}
		if(_counter == 35) {
			_y-=12;
			_canact = true;
			_attack2 = false;
			_x+=6;
			_cd1 = 7;
			_cd2 = 7;
			_cd3 = 7;
		}
	}

	@Override
	public void attack3() {
		if(_cd3 == 0 && _canattack3 && !_attack1 && !_attack2) {
			_counter = 0;
			_xvelocity = 0;
			_cd3 = 60;
			_attack3 = true;
			_canact = false;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack3/jump1.gif");
			} else {
				_image = new Image("zero/attack3/jump1left.gif");
				_x-=36;
			}
			_width = 87;
			_height = 54;
			_y+=6;
		}
	}
	
	public void executeAttack3() {
		if(_counter == 3) {
			//1st hitbox appears here
			int x;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack3/jump2.gif");
				x = 36;
			} else {
				_image = new Image("zero/attack3/jump2left.gif");
				_x-=36;
				x = 0;
			}
			_yvelocity = -17;
			_height = 99;
			_y-=45;
			TheGame._attacks.add(new OffsetHitbox("zjump1", this, x, 0, 51, 99, 15, 20, _clear));
			TheGame.playSound("/zero/sounds/zero3.wav");
		}
		if(_counter == 7) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack3/jump3.gif");
			} else {
				_image = new Image("zero/attack3/jump3left.gif");
				_x+=3;
			}
			_width = 84;
			_height = 96;
			_y+=3;
			_canattack3 = false;
		}
		if(_counter == 11) {
			int x;
			if(_facing.equals("right")) {
				_image = new Image("zero/attack3/jump4.gif");
				x = 36;
			} else {
				_image = new Image("zero/attack3/jump4left.gif");
				_x+=3;
				x = 0;
			}
			_width = 75;
			TheGame.clearHitboxes("zjump1", this);
			TheGame._attacks.add(new OffsetHitbox("zjump2", this, x, 0, 75-36, 27, 15, 20, _clear));
		}
		if(_counter == 15) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack3/jump5.gif");
			} else {
				_image = new Image("zero/attack3/jump5left.gif");
				_x+=6;
			}
			_width = 69;
			TheGame.clearHitboxes("zjump2", this);
		}
		if(_counter == 19) {
			if(_facing.equals("right")) {
				_image = new Image("zero/attack3/jump6.gif");
			} else {
				_image = new Image("zero/attack3/jump6left.gif");
				_x+=21;
			}
			_width = 48;
			_height = 87;
			_y+=9;
			
		}
		if(_counter == 23) {
			_attack3 = false;
			_canact = true;
			
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150) {
			_immune = true;
			pressJump();
			_canact = false;
			_attacku = true;
			_timesspun = 0;
			_counter = 0;
			_ultcharge = 0;
		}
	}
	
	public void executeAttackU() {
		if(_counter == 17) {
			_image = new Image("zero/attacku/spin1.gif");
			_width = 135;
			_height = 135;
			if(_timesspun == 0) {
			_x -= 30;
			_y -= 30;
			}
			_gravity = false;
		}
		if(_counter == 19) {
			_image = new Image("zero/attacku/spin2.gif");
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));
			TheGame.playSound("/zero/sounds/zero1.wav");
		}
		if(_counter == 21) {
			_image = new Image("zero/attacku/spin3.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));

		}
		if(_counter == 23) {
			_image = new Image("zero/attacku/spin4.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));

		}
		if(_counter == 25) {
			_image = new Image("zero/attacku/spin5.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));

		}
		if(_counter == 27) {
			_image = new Image("zero/attacku/spin6.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));
			TheGame.playSound("/zero/sounds/zero1.wav");
		}
		if(_counter == 29) {
			_image = new Image("zero/attacku/spin7.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));

		}
		if(_counter == 31) {
			_image = new Image("zero/attacku/spin8.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));
		}
		if(_counter == 33) {
			_image = new Image("zero/attacku/spin9.gif");
			TheGame.clearHitboxes("zult", this);
			TheGame._attacks.add(new OffsetHitbox("zult", this, 30, 30, 75, 75, 30, 30, _clear));

			if(_timesspun < 5) {
				_timesspun++;
				_counter = 15;
			} else {
				_immune = false;
				_attacku = false;
				_canact = true;
				_gravity = true;
				TheGame.clearHitboxes("zult", this);
			}
			
		}
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}
	
	@Override
	public void pressAttack1() {
		if(!_attack1) {
			super.pressAttack1();
		} else if(_attack1stage == 1 || _attack1stage == 2) {
			attack1();
		}
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd1 > 0){
		_cd1--;
		}
		if(_cd3 > 0) {
			_cd3--;
		}
		if(_cd2 > 0){
			_cd2--;
			}
	}
	
	@Override
	public void respawn() {
		super.respawn();
		_canact = true;
		_attack1 = false;
		_attack2 = false;
		_attack3 = false;
		
	}
	
	
	
	@Override
	public void pressUp() {
		if(_attacku) {
			_yvelocity = -10;
		}
	}
	
	@Override
	public void releaseUp() {
		if(_attacku) {
			_yvelocity = 0;
		}
	}
	
	@Override
	public void pressDown() {
		if(_attacku) {
			_yvelocity = 10;
		}
	}
	
	@Override
	public void releaseDown() {
		if(_attacku) {
			_yvelocity = 0;
		}
	}
	@Override
	public void pressLeft() {
		if(_attacku) {
			_xvelocity = -10;
		} else {
			super.pressLeft();
		}
	}

	@Override
	public void pressRight() {
		if(_attacku) {
			_xvelocity = 10;
		} else {
			super.pressRight();
		}
	}
	

}
