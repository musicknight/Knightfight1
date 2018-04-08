package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.AnimatedHitbox;
import kf.CharLinkedHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.TheGame;

public class EnchantChar extends CharacterImpl {
	
	private List<Image> _sprites = new ArrayList<Image>();
	private List<Image> _spritesleft = new ArrayList<Image>();
	private List<Image> _ultsprites1 = new ArrayList<Image>();
	private List<Image> _ultsprites2 = new ArrayList<Image>();
	private List<Image> _ultsprites3 = new ArrayList<Image>();
	private List<Image> _ultsprites4 = new ArrayList<Image>();
	private List<Image> _shotsprites = new ArrayList<Image>();
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _canattack1 = true;
	private boolean _canattack2 = true;
	private boolean _canattack3 = true;
	// cycles through the sprites
	private int _spritecounter = 0;
	// the index of the current sprite
	private int _spriteindex = 0;
	// cooldown on attack2 
	private int _counter2 = 0;
	// if attack2 has hit the stage
	private boolean _landed = false;
	
	

	public EnchantChar(String ID) {
		super(ID);
		_width = (int)(32*1.3);
		_height = (int)(61*1.3);
		_damagefactor = 1;
		_speedfactor = 1.1;
		_sprites.add(new Image("enchantress/enchant1.png"));
		_sprites.add(new Image("enchantress/enchant2.png"));
		_sprites.add(new Image("enchantress/enchant3.png"));
		_sprites.add(new Image("enchantress/enchant4.png"));
		_spritesleft.add(new Image("enchantress/enchant1left.png"));
		_spritesleft.add(new Image("enchantress/enchant2left.png"));
		_spritesleft.add(new Image("enchantress/enchant3left.png"));
		_spritesleft.add(new Image("enchantress/enchant4left.png"));
		_ultsprites1.add(new Image("enchantress/ult/pre1.png"));
		_ultsprites1.add(new Image("enchantress/ult/pre2.png"));
		_ultsprites1.add(new Image("enchantress/ult/pre3.png"));
		_ultsprites1.add(new Image("enchantress/ult/pre4.png"));
		_ultsprites2.add(new Image("enchantress/ult/pre5.png"));
		_ultsprites2.add(new Image("enchantress/ult/pre6.png"));
		_ultsprites2.add(new Image("enchantress/ult/pre7.png"));
		_ultsprites2.add(new Image("enchantress/ult/pre8.png"));
		_ultsprites3.add(new Image("enchantress/ult/ult1.png"));
		_ultsprites3.add(new Image("enchantress/ult/ult2.png"));
		_ultsprites3.add(new Image("enchantress/ult/ult3.png"));
		_ultsprites3.add(new Image("enchantress/ult/ult4.png"));
		_ultsprites4.add(new Image("enchantress/ult/hold1.png"));
		_ultsprites4.add(new Image("enchantress/ult/hold2.png"));
		_ultsprites4.add(new Image("enchantress/ult/hold3.png"));
		_ultsprites4.add(new Image("enchantress/ult/hold4.png"));
		_shotsprites.add(new Image("enchantress/ult/ball1.png"));
		_shotsprites.add(new Image("enchantress/ult/ball2.png"));
		_shotsprites.add(new Image("enchantress/ult/ball3.png"));
		_shotsprites.add(new Image("enchantress/ult/ball4.png"));
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku && !_intro) {
			_width = (int)(32*1.3);
			_height = (int)(61*1.3);
			_speedfactor = 1.1;
			if(_facing.equals("right")) {
				if(_spritecounter % 4 == 0) {
					_image = _sprites.get(_spriteindex);
					if(_spriteindex < 3) {
						_spriteindex++;
					} else {
						_spriteindex = 0;
					}
				}
			} else {
				if(_spritecounter % 4 == 0) {
					_image = _spritesleft.get(_spriteindex);
					if(_spriteindex < 3) {
						_spriteindex++;
					} else {
						_spriteindex = 0;
					}
				}
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
	}
	
	@Override
	public Image getStockImage() {
		return new Image("enchantress/stock.png");
	}

	@Override
	public void attack1() {
		if(_canattack1) {
			_canattack1 = false;
			_canattack2 = false;
			_canattack3 = false;
			_counter = 0;
			_attack1 = true;
			_width = (int)(52*1.3);
			_height = (int)(66*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot1.png");
			} else {
				_image = new Image("enchantress/shot1left.png");
			}
			TheGame.playSound("/enchantress/sounds/charge.wav");
		}
		
	}
	
	public void executeAttack1() {
		if(_xtumbling) {
			_attack1 = false;
			_canattack1 = true;
			_canattack2 = true;
			_canattack3 = true;
			if(_counter > 21 && _facing.equals("left")) {
				_x+=39;
			}
		}
		if(_counter == 3) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot2.png");
			} else {
				_image = new Image("enchantress/shot2left.png");
			}
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot3.png");
			} else {
				_image = new Image("enchantress/shot3left.png");
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot4.png");
			} else {
				_image = new Image("enchantress/shot4left.png");
			}
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot2.png");
			} else {
				_image = new Image("enchantress/shot2left.png");
			}
		}
		if(_counter == 15) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot3.png");
			} else {
				_image = new Image("enchantress/shot3left.png");
			}
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot4.png");
			} else {
				_image = new Image("enchantress/shot4left.png");
			}
		}
		if(_counter == 21) {
			_width = (int)(75*1.3);
			_height = (int)(61*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot5.png");
			} else {
				_image = new Image("enchantress/shot5left.png");
				_x-=(int)(30*1.3);
			}
		}
		if(_counter == 24) {
			int x;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot6.png");
				x = _width;
				v = 18;
			} else {
				_image = new Image("enchantress/shot6left.png");
				x = -(int)(25*1.3);
				v = -18;
			}
			Hitbox attack = new HitboxImpl("enchantbolt", this, false, _x+x, _y+(int)(18*1.3), (int)(25*1.3), (int)(25*1.3), v, 0, 18, 18, new Image("enchantress/ball.png"));
			TheGame._attacks.add(attack);
			TheGame.playSound("/enchantress/sounds/shot.wav");
		}
		if(_counter == 27) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot7.png");
			} else {
				_image = new Image("enchantress/shot7left.png");
			}
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot8.png");
			} else {
				_image = new Image("enchantress/shot8left.png");
			}
		}
		if(_counter == 35) {
			_canattack2 = true;
			_canattack3 = true;
			_canattack1 = true;
			
			_attack1 = false;
			if(_facing.equals("left")) {
				_x+= (int)(30*1.3);
			}
		}
		
	}

	@Override
	public void attack2() {
		if(_canattack2) {
			_xvelocity = 0;
			_canact = false;
			_attack2 = true;
			_counter = 0;
			_yvelocity = -12;
			_landed = false;
			_width = (int)(45*1.3);
			_height = (int)(61*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/dash1.png");
			} else {
				_image = new Image("enchantress/dash1left.png");
			}
		}
		
	}


	public void executeAttack2() {
		if(_xtumbling) {
			TheGame.clearHitboxes("enchantdash", this);
			_attack2 = false;
		}
		if(_counter == 22) {
			_width = (int)(62*1.3);
			_height = (int)(54*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/dash2.png");
				_xvelocity = 19;
			} else {
				_image = new Image("enchantress/dash2left.png");
				_xvelocity = -19;
			}
			_yvelocity = 15;
			Hitbox attack = new CharLinkedHitbox("enchantdash", this, 25, 20);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/enchantress/sounds/dash.wav");
			
		}
		if(_counter == 26){
		if(_facing.equals("right")) {
			_image = new Image("enchantress/dash3.png");
		} else {
			_image = new Image("enchantress/dash3left.png");
		}
		}
		if(_onplatform && !_landed && _counter > 3) {
			_xvelocity = 0;
			_yvelocity = -7;
			_counter2 = 0;
			_landed = true;
			_width = (int)(44*1.3);
			_height = (int)(47*1.3);
			TheGame.clearHitboxes("enchantdash", this);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/dash4.png");
			} else {
				_image = new Image("enchantress/dash4left.png");
			}
		}
		if(_counter2 == 30) {
			_attack2 = false;
			_canact = true;
			TheGame.clearHitboxes("enchantdash", this);
		}
		
		
	}
	
	public void attack3() {
		if(_canattack3) {
			_counter = 0;
			_attack3 = true;
			_xvelocity = 0;
			_canact = false;
			_width = (int)(52*1.3);
			_height = (int)(66*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot1.png");
			} else {
				_image = new Image("enchantress/shot1left.png");
			}
			TheGame.playSound("/enchantress/sounds/charge.wav");
		}
	}
	
	public void executeAttack3() {
		if(_xtumbling) {
			_attack3 = false;
			_canact = true;
		}
		if(_counter == 3) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot2.png");
			} else {
				_image = new Image("enchantress/shot2left.png");
			}
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot3.png");
			} else {
				_image = new Image("enchantress/shot3left.png");
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/shot4.png");
			} else {
				_image = new Image("enchantress/shot4left.png");
			}
		}
		if(_counter == 12) {
			_width = (int)(67*1.3);
			_height = (int)(77*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/up1.png");
			} else {
				_image = new Image("enchantress/up1left.png");
			}
		}
		if(_counter == 15) {
			int x;
			if(_facing.equals("right")) {
				_image = new Image("enchantress/up2.png");
				x = (int)(50*1.3);
			} else {
				_image = new Image("enchantress/up2left.png");
				x = (int)((0*1.3));
			}
			Hitbox attack = new HitboxImpl("upbolt", this, true, _x+x, _y-(int)(7*1.3), (int)(25*1.3), (int)(25*1.3), 0, -19, 18, 18, new Image("enchantress/ball.png"));
			if(_facing.equals("right")) {
				attack.setXVelocity(3);
			} else {
				attack.setXVelocity(-3);
			}
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
			TheGame.playSound("/enchantress/sounds/up.wav");
		}
		if(_counter == 16) {
			int x;
			if(_facing.equals("right")) {
			
				x = (int)(25*1.3);
			} else {
				x = (int)((25*1.3));
			}
			Hitbox attack = new HitboxImpl("upbolt", this, true, _x+x, _y-(int)(7*1.3), (int)(25*1.3), (int)(25*1.3), 0, -19, 18, 18, new Image("enchantress/ball.png"));
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
		}
		if(_counter == 17) {
			int x;
			if(_facing.equals("right")) {
			
				x = (int)(0*1.3);
			} else {
				x = (int)((50*1.3));
			}
			Hitbox attack = new HitboxImpl("upbolt", this, true, _x+x, _y-(int)(7*1.3), (int)(25*1.3), (int)(25*1.3), 0, -19, 18, 18, new Image("enchantress/ball.png"));
			if(_facing.equals("right")) {
				attack.setXVelocity(-3);
			} else {
				attack.setXVelocity(3);
			}
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
		}
		if(_counter == 19) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/up3.png");
			} else {
				_image = new Image("enchantress/up3left.png");
			}
		}
		if(_counter == 23) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/up4.png");
			} else {
				_image = new Image("enchantress/up4left.png");
			}
		}
		if(_counter == 33) {
			_attack3 = false;
			_canact = true;
			_height = (int)(61*1.3);
			_y+=(int)(15*1.3);
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150){
			_counter = 0;
			_canact = false;
			_attacku = true;
			_gravity = false;
			_immune = true;
			_ultcharge = 0;
			_xvelocity = 0;
			_yvelocity = -2;
			_width = (int)(43*1.3);
			_height = (int)(63*1.3);
			_haspriority = true;
			TheGame.playSound("/enchantress/sounds/ult1.wav");
		}
	}
	
	public void executeAttackU() {
		_canact = false;
		if(_counter % 4 == 0 && _counter <=40) {
			_image = _ultsprites1.get(_spriteindex);
			if(_spriteindex < 3) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		if(_counter == 42) {
			_width = (int)(62*1.3);
			_height = (int)(55*1.3);
		}
		if(_counter % 4 == 0 && _counter >40 && _counter <= 80) {
			
			_image = _ultsprites2.get(_spriteindex);
			if(_spriteindex < 3) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		if(_counter == 80) {

			TheGame.playSound("/enchantress/sounds/flash.wav");
		}
		if(_counter > 80 &&  _counter < 90) {
			TheGame._gc.setFill(Color.WHITE);
			TheGame._gc.fillRect(0, 0, 900, 600);
		} 
		if(_counter == 85) {
			_yvelocity = 0;
			_x = 360;
			_y = 150;
			_width = (int)(176*1.3);
			_height = (int)(198*1.3);
			_image = new Image("enchantress/ult/ult1.png");
		}
		if(_counter >= 90 && _counter % 4 == 0 && _counter < 140) {
			_image = _ultsprites3.get(_spriteindex);
			if(_spriteindex < 3) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		if(_counter == 145) {
			_image = new Image("enchantress/ult/hand1.png");
		}
		if(_counter == 150) {
			_image = new Image("enchantress/ult/hand2.png");
		}
		if(_counter == 155) {
			_image = new Image("enchantress/ult/hand3.png");
		}
		if(_counter == 160) {
			_image = new Image("enchantress/ult/hand4.png");
		}
		if(_counter == 165) {
			_image = new Image("enchantress/ult/hand5.png");
		}
		if(_counter >= 170 && _counter % 4 == 0 && _counter < 580){
			_image = _ultsprites4.get(_spriteindex);
			if(_spriteindex < 3) {
				_spriteindex++;
			} else {
				_spriteindex = 0;
			}
		}
		if(_counter >= 170 && _counter < 550){
			double x = (double)(((double)_counter) / 30);
			System.out.println(x);
			Hitbox attack = new AnimatedHitbox("enchantultbolt", this, false,_x+ (int)(107*1.3), _y+(int)(38*1.3), (int)(21*1.3), (int)(21*1.3), 7*Math.cos(1.5*x), 7*Math.sin(1.5*x), 8, 8,
					_shotsprites, 3);
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
			if(_counter % 5 == 0) {
				TheGame.playSound("/enchantress/sounds/ultshot.wav");
			}
		}
		if(_counter == 580) {
			TheGame.playSound("/enchantress/sounds/flash.wav");
		}
		if(_counter > 580 && _counter < 590) {
			TheGame._gc.setFill(Color.WHITE);
			TheGame._gc.fillRect(0, 0, 900, 600);
		}
		if(_counter == 585) {
			_gravity = true;
			_immune = false;
			_attacku = false;
			_canact = true;
			_haspriority = false;
		}
	
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}
	
	@Override 
	public void incrementCounter() {
		super.incrementCounter();
		_spritecounter++;
		_counter2++;
	}
	
	@Override
	public void pressRight() {
		if(!_attack1) {
			super.pressRight();
		} else {
			_xvelocity = 5.5;
		}
	}
	@Override
	public void pressLeft() {
		if(!_attack1) {
			super.pressLeft();
		} else {
			_xvelocity = -5.5;
		}
	}
	
	@Override
	public void doIntro() {
		if(_counter == 0) {
			_width = (int)(50*1.3);
			_height = (int)(47*1.3);
			_y+=(int)(14*1.3);
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro1.png");
			} else {
				_image = new Image("enchantress/intro1left.png");
			}
			TheGame.playSound("/enchantress/sounds/intro.wav");
		}
		if(_counter == 4) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro2.png");
			} else {
				_image = new Image("enchantress/intro2left.png");
			}
		}
		if(_counter == 8) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro3.png");
			} else {
				_image = new Image("enchantress/intro3left.png");
			}
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro4.png");
			} else {
				_image = new Image("enchantress/intro4left.png");
			}
		}
		if(_counter == 16) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro1.png");
			} else {
				_image = new Image("enchantress/intro1left.png");
			}
		}
		if(_counter == 20) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro2.png");
			} else {
				_image = new Image("enchantress/intro2left.png");
			}
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro3.png");
			} else {
				_image = new Image("enchantress/intro3left.png");
			}
		}
		if(_counter == 28) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro4.png");
			} else {
				_image = new Image("enchantress/intro4left.png");
			}
		}
		if(_counter == 32) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro1.png");
			} else {
				_image = new Image("enchantress/intro1left.png");
			}
		}
		if(_counter == 36) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro2.png");
			} else {
				_image = new Image("enchantress/intro2left.png");
			}
		}
		if(_counter == 40) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro3.png");
			} else {
				_image = new Image("enchantress/intro3left.png");
			}
		}
		if(_counter == 44) {
			if(_facing.equals("right")) {
				_image = new Image("enchantress/intro4.png");
			} else {
				_image = new Image("enchantress/intro4left.png");
			}
		}
	}

}
