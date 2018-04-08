package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.AnimatedHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.OffsetHitbox;
import kf.TheGame;

public class NarutoChar extends CharacterImpl {
	private List<Image> _sprites = new ArrayList<Image>();
	private List<Image> _spritesleft = new ArrayList<Image>();
	private List<NarutoChar> _clones = new ArrayList<NarutoChar>();
	public boolean _attack1;
	public boolean _attack1a;
	public boolean _attack2;
	public boolean _attack3;
	private boolean _isclone;
	private boolean _canattack1 = true;
	private boolean _canattack2 = true;
	private boolean _canattack3 = true;
	private boolean _ultstance;
	private int _akunaicharges = 2;
	private double _holder;
	private int _ultcounter = 0;
	// cycles through the sprites
	private int _spritecounter = 0;
	// the index of the current sprite
	private int _spriteindex = 0;
	private int _cd3;
	
	
	public NarutoChar(String ID) {
		super(ID);
		_width = (int)(43*1.2);
		_height = (int)(58*1.2);
		_damagefactor = 1.1;
		_speedfactor = 1.2;
		_sprites.add(new Image("naruto/naruto1.png"));
		_sprites.add(new Image("naruto/naruto2.png"));
		_sprites.add(new Image("naruto/naruto3.png"));
		_sprites.add(new Image("naruto/naruto4.png"));
		_spritesleft.add(new Image("naruto/naruto1left.png"));
		_spritesleft.add(new Image("naruto/naruto2left.png"));
		_spritesleft.add(new Image("naruto/naruto3left.png"));
		_spritesleft.add(new Image("naruto/naruto4left.png"));
		
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack1a && !_attack2 && !_attack3 && !_intro && !_ultstance) {
			_width = (int)(43*1.2);
			_height = (int)(58*1.2);
			if(_facing.equals("right")) {
				if(_spritecounter % 5 == 0) {
					_image = _sprites.get(_spriteindex);
					if(_spriteindex < 3) {
						_spriteindex++;
					} else {
						_spriteindex = 0;
					}
				}
			} else {
				if(_spritecounter % 5 == 0) {
					_image = _spritesleft.get(_spriteindex);
					if(_spriteindex < 3) {
						_spriteindex++;
					} else {
						_spriteindex = 0;
					}
				}
			}
		}
		if(_isclone) {
			System.out.println(_attack1);
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_attack1a) {
			executeAttack1A();
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
		if(_onplatform) {
			_akunaicharges = 2;
		}
	}
	
	@Override
	public Image getStockImage() {
		return new Image("naruto/stock.png");
	}

	@Override
	public void attack1() {
		if(!_attack2 && !_attack3){
		_attack1 = true;
		_canact = false;
		_xvelocity = 0;
	
		_counter = 0;
		_width = (int)(36*1.2);
		_height = (int)(57*1.2);
		if (_facing.equals("right")) {
			_image = new Image("naruto/attack1/gthrow1.png");
		} else {
			_image = new Image("naruto/attack1/gthrow1left.png");
		}
		}
		
	}
	
	public void executeAttack1() {
		if(_xtumbling) {
			_attack1 = false;
			_canact = true;
		}
		if(_counter == 9) {
			_width = (int)(55*1.2);
			_height = (int)(52*1.2);
			int x;
			double v;
			Image i;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack1/gthrow2.png");
				x = (int)(33*1.2);
				v = 20;
				i = new Image("naruto/attack1/gkunai.png");
			} else {
				_image = new Image("naruto/attack1/gthrow2left.png");
				_x-=(int)(19*1.2);
				x = (int)(-11*1.2);
				v = -20;
				i = new Image("naruto/attack1/gkunaileft.png");
			}
			Hitbox attack = new HitboxImpl("narutokunai", this, false, _x+x, _y+(int)(19*1.2), (int)(32*1.2), (int)(11*1.2), v, 0, 6, 10, i);
			if (_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/naruto/sounds/throw.wav");
			TheGame.playSound("/naruto/sounds/throwvoice.wav");
		}
		if(_counter == 11) {
			_width = (int)(53*1.2);
			_height = (int)(47*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack1/gthrow3.png");
			} else {
				_image = new Image("naruto/attack1/gthrow3left.png");
			}
		}
		if(_counter == 13) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack1/gthrow4.png");
			} else {
				_image = new Image("naruto/attack1/gthrow4left.png");
			}
		}
		if(_counter == 15) {
			_width = (int)(46*1.2);
			_height = (int)(47*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack1/gthrow5.png");
				_x+=10;
			} else {
				_image = new Image("naruto/attack1/gthrow5left.png");
			}
		}
		if(_counter == 24) {
			_canact = true;
			_attack1 = false;
			if (_facing.equals("right")) {
				_x-=10;
			} else {
				_x+=(int)(19*1.2);
			}
		}
		
	}
	
	public void attack1a() {
		if(_akunaicharges > 0 && !_attack2 && !_attack3){
		_attack1a = true;
		_canact = false;
		
		
		_counter = 0;
		_width = (int)(52*1.2);
		_height = (int)(58*1.2);
		_akunaicharges--;
		if (_facing.equals("right")) {
			_image = new Image("naruto/attack1/athrow1.png");
		} else {
			_image = new Image("naruto/attack1/athrow1left.png");
		}
		}
		
	}
	
	public void executeAttack1A() {
		if(_counter == 9){
			int x;
			double v;
			Image i;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack1/athrow2.png");
				_xvelocity = -5;
				x = (int)(35*1.2);
				v = Math.sqrt(200);
				i = new Image("naruto/attack1/akunai.png");
			} else {
				_image = new Image("naruto/attack1/athrow2left.png");
				_xvelocity = 5;
				x = (int)(-7*1.2);
				v = -Math.sqrt(200);
				i = new Image("naruto/attack1/akunaileft.png");
			}
			_yvelocity = -5;
			_xtumbling = true;
			Hitbox attack = new HitboxImpl("narutokunai", this, false, _x+x, _y+(int)(24*1.2), (int)(23*1.2), (int)(23*1.2), v, Math.sqrt(200), 6, 10, i);
			if (_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/naruto/sounds/throw.wav");
			TheGame.playSound("/naruto/sounds/throwvoice.wav");
	
		}
		if(_counter == 12) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack1/athrow3.png");
			} else {
				_image = new Image("naruto/attack1/athrow3left.png");
			}
		}
		if(_counter == 21) {
			_attack1a = false;
			_canact = true;
		}
	}
	@Override
	public void attack2() {
		if(!_attack1 && !_attack3 && !_attack2){
		_attack2 = true;
		_canact = false;
		_xvelocity = 0;
		_holder = _damage;
		
		_counter = 0;
		_width = (int)(41*1.2);
		_height = (int)(57*1.2);
		_y+=1;
		if (_facing.equals("right")) {
			_image = new Image("naruto/attack2/punch1.png");
		} else {
			_image = new Image("naruto/attack2/punch1left.png");
		}
		}
	}
	
	public void executeAttack2() {
		if(_holder != _damage) {
			TheGame.clearHitboxes("napunch", this);
			TheGame.clearHitboxes("nahit", this);
			TheGame.clearHitboxes("naslam", this);
			_canact = true;
			_attack2 = false;
		}
		
		if(_counter == 6) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/punch2.png");
				_xvelocity = 15;
			} else {
				_image = new Image("naruto/attack2/punch2left.png");
				_xvelocity = -15;
			}
			TheGame.playSound("/naruto/sounds/hit1.wav");
			TheGame.playSound("/naruto/sounds/hit.wav");
			_xtumbling = true;
		}
		if(_counter == 9) {
			_width = (int)(62*1.2);
			int x;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/punch3.png");
				x = (int)(35*1.2);
			} else {
				_image = new Image("naruto/attack2/punch3left.png");
				x = 0;
			}
			Hitbox attack = new OffsetHitbox("napunch", this, x, 0, (int)(27*1.2), (int)(57*1.2), 7, 10, _clear);
			attack.setSetKnockback(true);
			if (_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 12) {
			_width = (int)(56*1.2);
			_height = (int)(58*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/punch4.png");
			} else {
				_image = new Image("naruto/attack2/punch4left.png");
			}
			
		}
		if(_counter == 18) {
			_width = (int)(40*1.2);
			_height = (int)(58*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/hit1.png");
			} else {
				_image = new Image("naruto/attack2/hit1left.png");
			}
			TheGame.clearHitboxes("napunch", this);
		}
		if(_counter == 21) {
			_width = (int)(51*1.2);
			_height = (int)(57*1.2);
			int x;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/hit2.png");
				x = (int)(35*1.2);
				_xvelocity = 9;
			} else {
				_image = new Image("naruto/attack2/hit2left.png");
				_xvelocity = -9;
				x = 0;
			}
			_xtumbling = true;
			Hitbox attack = new OffsetHitbox("nahit", this, x, 0, (int)(27*1.2), (int)(57*1.2), 7, 10, _clear);
			attack.setSetKnockback(true);
			if (_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/naruto/sounds/hit2.wav");
			TheGame.playSound("/naruto/sounds/hit.wav");
		}
		if(_counter == 24) {
			_width = (int)(56*1.2);
			_height = (int)(46*1.2);
			_y+=(int)(11*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/hit3.png");
			} else {
				_image = new Image("naruto/attack2/hit3left.png");
			}
		}
		if(_counter == 27) {
			_width = (int)(57*1.2);
			_height = (int)(50*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/hit4.png");
			} else {
				_image = new Image("naruto/attack2/hit4left.png");
			}
			TheGame.clearHitboxes("nahit", this);
		}
		if(_counter == 32) {
			_width = (int)(41*1.2);
			_height = (int)(56*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/slam1.png");
			} else {
				_image = new Image("naruto/attack2/slam1left.png");
			}
		}
		if(_counter == 35) {
			_width = (int)(56*1.2);
			_height = (int)(52*1.2);
			_y+=4;
			int x;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/slam2.png");
				_xvelocity = 9;
				x = (int)(35*1.2);
			} else {
				_image = new Image("naruto/attack2/slam2left.png");
				_xvelocity = -9;
				x = 0;
			}
			_xtumbling = true;
			Hitbox attack = new OffsetHitbox("naslam", this, x, 0, (int)(27*1.2), (int)(57*1.2), 15, 10, _clear);
			if (_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/naruto/sounds/hit3.wav");
		}
		if(_counter == 38) {
			_width = (int)(57*1.2);
			_height = (int)(54*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/slam3.png");
			} else {
				_image = new Image("naruto/attack2/slam3left.png");
			}
		}
		if(_counter == 41) {
			_width = (int)(49*1.2);
			_height = (int)(49*1.2);
			_y+=(int)(5*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack2/slam4.png");
			} else {
				_image = new Image("naruto/attack2/slam4left.png");
			}
			TheGame.clearHitboxes("naslam", this);
			TheGame.playSound("/naruto/sounds/hit.wav");
		}
		if(_counter == 55) {
			_attack2 = false;
			_canact = true;
		}
	}

	@Override
	public void attack3() {
		if(_cd3 == 0 && !_attack1 && !_attack2){
		_attack3 = true;
		_canact = false;
		_xvelocity = 0;
		_counter = -3;
		_cd3 = 70;
		
		_width = (int)(55*1.2);
		_height = (int)(55*1.2);
		if (_facing.equals("right")) {
			_image = new Image("naruto/attack3/shot1.png");
		} else {
			_image = new Image("naruto/attack3/shot1left.png");
		}
		TheGame.playSound("/naruto/sounds/chargevoice.wav");
		}
	}
	
	public void executeAttack3() {
		if(_xtumbling) {
			_canact = true;
			_attack3 = false;
		}
		if(_counter == 2) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot2.png");
			} else {
				_image = new Image("naruto/attack3/shot2left.png");
			}
			TheGame.playSound("/naruto/sounds/charge.wav");
			
		}
		if(_counter == 4) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot3.png");
			} else {
				_image = new Image("naruto/attack3/shot3left.png");
			}
		}
		if(_counter == 6) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot4.png");
			} else {
				_image = new Image("naruto/attack3/shot4left.png");
			}
		}
		if(_counter == 8) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot5.png");
			} else {
				_image = new Image("naruto/attack3/shot5left.png");
			}
		}
		if(_counter == 10) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot6.png");
			} else {
				_image = new Image("naruto/attack3/shot6left.png");
			}
		}
		if(_counter == 12) {
			_width = (int)(65*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot7.png");
				_x+=7;
			} else {
				_image = new Image("naruto/attack3/shot7left.png");
				_x-= 20;
			}
		}
		if(_counter == 14) {
			_width = (int)(76*1.2);
			_height = (int)(52*1.2);
			_y+=4;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot8.png");
			} else {
				_image = new Image("naruto/attack3/shot8left.png");
				_x-= 20;
			}
		}
		if(_counter == 16) {
			_width = (int)(105*1.2);
			_height = (int)(64*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot9.png");
			} else {
				_image = new Image("naruto/attack3/shot9left.png");
				_x-= 35;
			}
		}
		if(_counter == 18) {
			_width = (int)(87*1.2);
			_height = (int)(68*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot10.png");
			} else {
				_image = new Image("naruto/attack3/shot10left.png");
				_x+= 20;
			}
		}
		if(_counter == 20) {
			_width = (int)(107*1.2);
			_height = (int)(63*1.2);
			_y+=5;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot11.png");
			} else {
				_image = new Image("naruto/attack3/shot11left.png");
				_x-= 20;
			}
		}
		if(_counter == 22) {
			_width = (int)(126*1.2);
			_height = (int)(69*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot12.png");
				_x-=15;
			} else {
				_image = new Image("naruto/attack3/shot12left.png");
				_x-= 12;
			}
		}
		if(_counter == 24) {
			_width = (int)(150*1.2);
			_height = (int)(85*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot13.png");
				_x-=15;
			} else {
				_image = new Image("naruto/attack3/shot13left.png");
				_x-= 12;
			}
		}
		if(_counter == 26) {
			_width = (int)(60*1.2);
			_height = (int)(52*1.2);
			_y+=(int)(35*1.2);
			List<Image> images = new ArrayList<Image>();
			int x;
			double v;
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot14.png");
				_x+=30;
				images.add(new Image("naruto/attack3/ball1.png"));
				images.add(new Image("naruto/attack3/ball2.png"));
				images.add(new Image("naruto/attack3/ball3.png"));
				images.add(new Image("naruto/attack3/ball4.png"));
				x=(int)(27*1.2);
				v=10;
			} else {
				_image = new Image("naruto/attack3/shot14left.png");
				_x+= 69;
				images.add(new Image("naruto/attack3/ball1left.png"));
				images.add(new Image("naruto/attack3/ball2left.png"));
				images.add(new Image("naruto/attack3/ball3left.png"));
				images.add(new Image("naruto/attack3/ball4left.png"));
				x=(int)(-70*1.2);
				v=-10;
			}
			Hitbox attack = new AnimatedHitbox("rasengan", this,false, _x+x, _y-(int)(27*1.2), (int)(98*1.2), (int)(79*1.2), v, 0, 32, 20, images, 2);
			//attack.setDissappearOnHit(false);
			if (_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/naruto/sounds/ball.wav");
			
		}
		if(_counter == 26) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot15.png");
			} else {
				_image = new Image("naruto/attack3/shot15left.png");
			}
		}
		if(_counter == 28) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot16.png");
			} else {
				_image = new Image("naruto/attack3/shot16left.png");
			}
		}
		if(_counter == 30) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/attack3/shot17.png");
			} else {
				_image = new Image("naruto/attack3/shot17left.png");
			}
		}
		if(_counter == 45) {
			_attack3 = false;
			_canact = true;
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150){
		_ultcounter = 0;
		_ultcharge = 0;
		_attacku = true;
		_immune = true;
		_attacku = true;
		
		_ultstance = true;
		_canact = false;
		if (_facing.equals("right")) {
			_image = new Image("naruto/ult/ult.png");
		} else {
			_image = new Image("naruto/ult/ultleft.png");
		}
		TheGame.playSound("/naruto/sounds/ultvoice.wav");
		}
	}
	
	public void executeAttackU() {
		if(_ultcounter == 17) {
			NarutoChar c1 = new NarutoChar(_ID);
			NarutoChar c2 = new NarutoChar(_ID);
			NarutoChar c3 = new NarutoChar(_ID);
			NarutoChar c4 = new NarutoChar(_ID);
			_clones.add(c1);
			_clones.add(c2);
			_clones.add(c3);
			_clones.add(c4);
			for(NarutoChar n : _clones){
				n.makeClone();
			}
			c1.setX(_x-150);
			c2.setX(_x-75);
			c3.setX(_x+75);
			c4.setX(_x+150);
		}
		if(_ultcounter < 301 & _ultcounter > 17){
			_clones.get(0).setX(_x-150);
			_clones.get(1).setX(_x-75);
			_clones.get(2).setX(_x+75);
			_clones.get(3).setX(_x+150);
			for(NarutoChar n : _clones) {
				//n.setXVelocity(_xvelocity);
				if(!n._attack1a && !n._attack2){
					n.setXTumbling(false);
				}
				n.setFacing(_facing);
				n.setY(_y);
				
				n.render(TheGame._gc);
				if(!n.isOnPlatform()) {
					n.setYVelocity(n.getYVelocity()+TheGame._gravity);
				}
				n.move();
				n.incrementCounter();
				n.setImage(_image);
			}
			}
		if(_ultcounter >= 15 && _ultcounter < 19) {
			_haspriority = true;
			TheGame._gc.setFill(Color.WHITE);
			TheGame._gc.fillRect(0, 0, 900, 600);
		}
		if(_ultcounter == 15){

			TheGame.playSound("/naruto/sounds/charge.wav");
		}
		if(_ultcounter == 25) {
			_canact = true;
			_ultstance = false;
		}
		
		if(_ultcounter >= 300 && _ultcounter < 304){
			TheGame._gc.setFill(Color.WHITE);
			TheGame._gc.fillRect(0, 0, 900, 600);
		}
		if(_ultcounter == 300){

			TheGame.playSound("/naruto/sounds/charge.wav");
		}
		if(_ultcounter == 301) {
			_clones.clear();
		}
		if(_ultcounter == 305) {
			_attacku = false;
			_immune = false;
		}
		
	}

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		_spritecounter++;
		_ultcounter++;
		if(_cd3 > 0) {
			_cd3--;
		}
	}
	
	@Override
	public void pressAttack1() {
		if(_canjump1) {
			if(_attacku) {
				if(_canact){
				for(NarutoChar n : _clones) {
					n.attack1();
				}
			}
			}
			super.pressAttack1();
		} else {
			if(_canact) {
				if(_attacku) {
					for(NarutoChar n : _clones) {
						n.attack1a();
					}
				}
				attack1a();
			}
		}
	}
	
	@Override
	public void doIntro() {
		if(_counter == 0) {
			_width = (int)(50*1.2);
			_height = (int)(88*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro1.png");
			} else {
				_image = new Image("naruto/intro/intro1left.png");
			}
		}
		if(_counter == 6) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro2.png");
			} else {
				_image = new Image("naruto/intro/intro2left.png");
			}
			TheGame.playSound("/naruto/sounds/hit1.wav");
		}
		if(_counter == 8) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro3.png");
			} else {
				_image = new Image("naruto/intro/intro3left.png");
			}
		}
		if(_counter == 10) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro4.png");
			} else {
				_image = new Image("naruto/intro/intro4left.png");
			}
			TheGame.playSound("/naruto/sounds/hit.wav");
		}
		if(_counter == 16) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro5.png");
			} else {
				_image = new Image("naruto/intro/intro5left.png");
			}
		}
		if(_counter == 18) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro6.png");
			} else {
				_image = new Image("naruto/intro/intro6left.png");
			}
		}
		if(_counter == 20) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro7.png");
			} else {
				_image = new Image("naruto/intro/intro7left.png");
			}
		}
		if(_counter == 22) {
			if (_facing.equals("right")) {
				_image = new Image("naruto/intro/intro8.png");
			} else {
				_image = new Image("naruto/intro/intro8left.png");
			}
		}
		if(_counter == 49) {
			_width = (int)(43*1.2);
			_height = (int)(58*1.2);
			_y+=(30*1.2);
			if (_facing.equals("right")) {
				_image = new Image("naruto/naruto1.png");
			} else {
				_image = new Image("naruto/naruto1left.png");
			}
		}
	}
	
	public void makeClone() {
		_isclone = true;
	}
	
	@Override 
	public void pressAttack2() {
		super.pressAttack2();
			if(_attacku) {
				for(NarutoChar n : _clones) {
					if(n.isCanAct()){
					n.attack2();
					}
			}	}
		
	}
	@Override 
	public void pressAttack3() {
		super.pressAttack3();
		if(_attacku) {
			for(NarutoChar n : _clones) {
				if(n.isCanAct()){
				n.attack3();
				}
		}
	}

}
}
