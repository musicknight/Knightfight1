package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class NinjaChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _ducking;
	private boolean _countered = false;
	private int _cd1 = 0;
	private int _cd3 = 0;
	private int _ultcounter = 220;
	private int _countercounter = 10;
	private boolean _poofed = false;
	private boolean _hitwithult = false;
	private boolean _ulting = false;
	private boolean _ultstarted = false;
	private CharLinkedHitbox _strikeu = new CharLinkedHitbox("strikeu", this, 0, 0);
	
	public NinjaChar(String ID) {
		super(ID);
		_width = 50;
		_height = 50;
		if(_facing.equals("right")) {
			_image = new Image("ninja/ninja.png");
		} else {
			_image = new Image("ninja/ninjaleft.png");
		}
		_speedfactor = 1.2;
		_damagefactor = 1.1;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku){
			if(_facing.equals("right")) {
				if(_ducking) {
					_image = new Image("ninja/duck.png");
				} else {
					_image = new Image("ninja/ninja.png");
				}
			
			
			} else {
				if(_ducking) {
					_image = new Image("ninja/duckleft.png");
				} else {
					_image = new Image("ninja/ninjaleft.png");
				}
			}
			TheGame.clearHitboxes("strike",	this);
			TheGame.clearHitboxes("counterstrike",	this);
			if(!_ducking) {
			_width = 50;
			_height = 50;
			}
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attacku) {
			executeAttackU();
		}
		if(_cd3 == 0) {
			int x;
			if(_ID.equals("one")){
				x = 50;
			} else {
				x = 140;
			}
			TheGame._gc.drawImage(new Image("ninja/counter1.png"), x, 160, 25, 25);
		}
	}
	

	@Override
	public Image getStockImage() {
		return new Image("ninja/stock.png");
	}

	@Override
	public void attack1() {
		
		
		if(_cd1 == 0){
		_canact = false;
		_xvelocity = 0;
		_counter = 0;
		_attack1 = true;
		if(_facing.equals("right")) {
			_image = new Image("ninja/shuriken1.png");
			} else {
			_image = new Image("ninja/shuriken1left.png");
			}
		_y-=2;
		_width = 60;
		_height = 52;
		_cd1 = 38;
		}
		
	}
	
	public void executeAttack1() {
		
		if(_xtumbling) {
			_attack1 = false;
			_canact =true;
			if(_height == 48) {
				_y -=2;
			}
			
		}
		if(_counter == 4) {
			if(_facing.equals("right")) {
				_image = new Image("ninja/shuriken2.png");
				} else {
				_image = new Image("ninja/shuriken2left.png");
				}
			_y+=4;
			_width = 74;
			_height = 48;
		}
		if(_counter == 5) {
			if(_facing.equals("right")) {
				_image = new Image("ninja/shuriken3.png");
				} else {
				_image = new Image("ninja/shuriken3left.png");
				}
			_y-=2;
			_width = 84;
			_height = 50;
		}
		if(_counter == 6) {
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("ninja/shuriken4.png");
				d = 54;
				v = 22;
				} else {
				_image = new Image("ninja/shuriken4left.png");
				d = -40;
				v=-22;
				}
			_width = 54;
			TheGame._attacks.add(new HitboxImpl("shuriken", this, false, _x+d, _y+10, 40, 40, v, 0, 8, 14, new Image("ninja/shuriken.png")));
			
		}
		if(_counter == 7) {
			if(_facing.equals("right")) {
				_image = new Image("ninja/shuriken5.png");
				} else {
				_image = new Image("ninja/shuriken5left.png");
				}
			_y-=6;
			_width = 56;
			_height = 56;
		}
		if(_counter == 8) {
			if(_facing.equals("right")) {
				_image = new Image("ninja/shuriken6.png");
				} else {
				_image = new Image("ninja/shuriken6left.png");
			}
			}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("ninja/shuriken7.png");
				} else {
				_image = new Image("ninja/shuriken7left.png");
				}
			
		}
		if(_counter == 14) {
			_canact = true;
			_attack1 = false;
			_y += 6;
		}
		
	}

	@Override
	public void attack2() {
		_xvelocity = 0;
		_canact = false;
		_counter = 0;
		_attack2 = true;
		_y-=4;
		_width = 44;
		_height = 54;
		if(_facing.equals("right")) {
			_image = new Image("ninja/strike1.png");
		} else {
			_image = new Image("ninja/strike1left.png");
		}
		
	}
	
	public void executeAttack2() {
		if(_counter == 5) {
			int x;
			if(_facing.equals("right")) {
				_image = new Image("ninja/strike2.png");
				_xvelocity = 18;
				_xtumbling = true;
				x = 64;
			} else {
				_image = new Image("ninja/strike2left.png");
				//_x-=36;
				_xvelocity = -18;
				_xtumbling = true;
				x=0;
			}
			_width = 80;
			_height = 50;
			_y += 4;
			TheGame._attacks.add(new OffsetHitbox("strike", this, _x+x, _y+34, 16, 16, 8, 16, _clear));
			
		}
		if(_xvelocity == 0 && _counter > 8) {
			_attack2 = false;
			_canact = true;
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("strike") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
		}
	}

	@Override
	public void attack3() {
		if(_cd3 == 0) {
		_canact = false;
		_attack3 = true;
		_immune = true;
		_countered = false;
		_xvelocity = 0;
		_poofed = false;
		_dodged = false;
		
		_counter = 0;
		_cd3 = 90;
		if(_facing.equals("right")) {
			_image = new Image("ninja/counter1.png");
		} else {
			_image = new Image("ninja/counter1left.png");
		}
		}
		
	}
	
	public void executeAttack3() {
		if(_dodged && !_poofed) {
			_image = new Image("ninja/counter2.png");
			_countercounter = 0;
			_poofed = true;
			_dodged = false;
		}
		
		if(!_countered) {
			_yvelocity = 0;
		}
		if(_countercounter == 8) {
			int d;
			double v;
			int x;
			if(_otherchar.getX() > _x) {
				_image = new Image("ninja/strike2left.png");
				d = _otherchar.getX() + _otherchar.getWidth() + 6;
				v = -24;
				x = 0;
				_facing = "left";
			} else {
				_image = new Image("ninja/strike2.png");
				d = _otherchar.getX() - _width - 6;
				v = 24;
				x = 64;
				_facing = "right";
				
			}
			
			
			_width = 80;
			_x = d;
			
			if(_otherchar.isOnPlatform()) {
				_y = 340;
			} else {
				_y = _otherchar.getY();
			}
			_xvelocity = v;
			_xtumbling = true;
			_countered = true;
			TheGame._attacks.add(new OffsetHitbox("counterstrike", this, _x+x, _y+34, 16, 16, 24, 20, _clear));
			
		}
		if(_countered && _xvelocity == 0) {
			_canact = true;
			_attack3 = false;
			_immune = false;
		}
		if(!_poofed && _counter == 16) {
			_canact = true;
			_attack3 = false;
			_immune = false;
		}
		
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150) {
		_xvelocity = 0;
		_canact = false;
		_counter = 0;
		_attacku = true;
		_hitwithult = false;
		_haspriority = true;
		_ultcharge = 0;
		_immune = true;
		_y-=4;
		_width = 44;
		_height = 54;
		if(_facing.equals("right")) {
			_image = new Image("ninja/strike1u.png");
		} else {
			_image = new Image("ninja/strike1uleft.png");
		}
		}
		
		
		
	}
	
	public void executeAttackU() {
		if(_counter == 5) {
			if(_facing.equals("right")) {
				_image = new Image("ninja/strike2u.png");
				_xvelocity = 30;
				
			} else {
				_image = new Image("ninja/strike2uleft.png");
				//_x-=36;
				_xvelocity = -30;
				
			}
			_width = 80;
			_height = 50;
			_y += 4;
			_strikeu = new CharLinkedHitbox("strikeu", this, 0, 0);
			TheGame._attacks.add(_strikeu);
			
		}
		if(_x <= 150 || _x + 80 >= 750 && _counter > 7) {
			_xvelocity = 0;
		}
		if(_strikeu.isGone() && _counter >=5) {
			_otherchar.setCanAct(false);
			_otherchar.setXVelocity(0);
			_otherchar.setYVelocity(0);
			_ulting = true;
		}
		if(_ulting && !_ultstarted) {
			_ultstarted = true;
			_ultcounter = 0;
		}
		int x =  (int) (_otherchar.getX() - ((80 - _otherchar.getWidth()) / 2));
		int y =  (int) (_otherchar.getY() - ((80 - _otherchar.getHeight()) / 2));
		if(_ultcounter >= 25 && _ultcounter < 30) {
			TheGame._gc.drawImage(new Image("ninja/slice1.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 30 && _ultcounter < 35) {
			TheGame._gc.drawImage(new Image("ninja/slice2.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 36 && _ultcounter < 40) {
			TheGame._gc.drawImage(new Image("ninja/slice3.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 41 && _ultcounter < 45) {
			TheGame._gc.drawImage(new Image("ninja/slice4.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 46 && _ultcounter < 50) {
			TheGame._gc.drawImage(new Image("ninja/slice5.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 51 && _ultcounter < 55) {
			TheGame._gc.drawImage(new Image("ninja/slice6.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 56 && _ultcounter < 60) {
			TheGame._gc.drawImage(new Image("ninja/slice2.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 61 && _ultcounter < 65) {
			TheGame._gc.drawImage(new Image("ninja/slice5.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 66 && _ultcounter < 70) {
			TheGame._gc.drawImage(new Image("ninja/slice3.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 71 && _ultcounter < 75) {
			TheGame._gc.drawImage(new Image("ninja/slice4.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 76 && _ultcounter < 80) {
			TheGame._gc.drawImage(new Image("ninja/slice5.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 81 && _ultcounter < 85) {
			TheGame._gc.drawImage(new Image("ninja/slice6.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 86 && _ultcounter < 90) {
			TheGame._gc.drawImage(new Image("ninja/slice1.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 91 && _ultcounter < 95) {
			TheGame._gc.drawImage(new Image("ninja/slice2.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 96 && _ultcounter < 100) {
			TheGame._gc.drawImage(new Image("ninja/slice3.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 101 && _ultcounter < 105) {
			TheGame._gc.drawImage(new Image("ninja/slice4.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 106 && _ultcounter < 110) {
			TheGame._gc.drawImage(new Image("ninja/slice5.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 111 && _ultcounter < 115) {
			TheGame._gc.drawImage(new Image("ninja/slice6.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 116 && _ultcounter < 120) {
			TheGame._gc.drawImage(new Image("ninja/slice2.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 121 && _ultcounter < 125) {
			TheGame._gc.drawImage(new Image("ninja/slice5.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 126 && _ultcounter < 130) {
			TheGame._gc.drawImage(new Image("ninja/slice3.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 131 && _ultcounter < 135) {
			TheGame._gc.drawImage(new Image("ninja/slice4.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 136 && _ultcounter < 140) {
			TheGame._gc.drawImage(new Image("ninja/slice5.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter >= 141 && _ultcounter < 145) {
			TheGame._gc.drawImage(new Image("ninja/slice6.png"), x, y, 80, 80);
			_otherchar.setDamage(_otherchar.getDamage() + 2);
		}
		if(_ultcounter == 220) {
			
			int i;
			if(_x < 400) {
				i = 900;
			} else {
				i = 0;
			}
			Hitbox attack = new MeleeHitbox("flash", this, i, 0, 0, 0, 50, 0 );
			attack.setHOrientation(true);
			_otherchar.hit(attack);
			TheGame._gc.drawImage(new Image("whitebox.jpg"), 0, 0, 1000, 1000);
			_attacku = false;
			_canact = true;
			_ulting = false;
			_immune = false;
			_ultstarted = false;
			_haspriority = false;
			_otherchar.setCanAct(true);
		}
		if(_counter > 5 && _xvelocity == 0 && !_ulting) {
			_attacku = false;
			_immune = false;
			_canact = true;
		}
		
	}

	@Override
	public Color getColor() {
		return Color.PINK;
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd1 > 0){
		_cd1--;
		}
		if(_cd3 > 0){
			_cd3--;
		}
		_countercounter++;
		_ultcounter++;
		
	}
	
	public void pressDown() {
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku && _canact){
			_height = 36;
			_xvelocity = 0;
			_y += 14;
			_ducking = true;
			_canact = false;
		}
	}
	public void releaseDown() {
		if(_ducking){
		_ducking = false;
		_y-=14;
		_height = 50;
		_canact = true;
		}
	}
 
}
