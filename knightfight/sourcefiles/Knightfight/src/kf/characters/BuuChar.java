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

public class BuuChar extends CharacterImpl {
	private List<Image> _sprites = new ArrayList<Image>();
	private List<Image> _spritesleft = new ArrayList<Image>();
	private boolean _attack1;
	private boolean _attack2a;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _ultthrow;
	private boolean _canattack1 = true;
	private boolean _canattack2 = true;
	private boolean _canattack3 = true;
	private boolean _landed = false;
	private int _bombcounter;
	// cycles through the sprites
		private int _spritecounter = 0;
		// the index of the current sprite
		private int _spriteindex = 0;
	
	public BuuChar(String ID) {
		super(ID);
		_width = (int)(37*1.5);
		_height = (int)(46*1.5);
		_damagefactor = 1.1;
		_speedfactor = 1.1;
		_sprites.add(new Image("buu/stance1.png"));
		_sprites.add(new Image("buu/stance2.png"));
		_sprites.add(new Image("buu/stance3.png"));
		_sprites.add(new Image("buu/stance4.png"));
		_spritesleft.add(new Image("buu/stance1left.png"));
		_spritesleft.add(new Image("buu/stance2left.png"));
		_spritesleft.add(new Image("buu/stance3left.png"));
		_spritesleft.add(new Image("buu/stance4left.png"));
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_canact && _xvelocity > 0) {
			_facing = "right";
		}
		if(_canact && _xvelocity < 0) {
			_facing = "left";
		}
		if(!_attack1 && !_attack2 && !_attack2a && !_attack3 && !_attacku && !_intro) {
			_width = (int)(37*1.5);
			_height = (int)(46*1.5);
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
		if(_attack2a) {
			executeAttack2A();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attacku) {
			executeAttackU();
		}
		if(_onplatform) {
			_canattack3 = true;
		}
	}
	
	@Override
	public Image getStockImage() {
		return new Image("buu/stock.png");
		
	}

	@Override
	public void attack1() {
		_counter = 0;
		_canact = false;
		_attack1 = true;
		_xvelocity = 0;
		_width = (int)(32*1.5);
		_height = (int)(48*1.5);
		if(_facing.equals("right")) {
			_image = new Image("buu/attack1/shot1.png");
		} else {
			_image = new Image("buu/attack1/shot1left.png");
		}
	}
	
	public void executeAttack1() {
		if(_xtumbling) {
			_canact = true;
			_attack1 = false;
		}
		if(_counter == 3) {
			_width = (int)(37*1.5);
			_height = (int)(48*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot2.png");
			} else {
				_image = new Image("buu/attack1/shot2left.png");
				_x-=8;
			}
			TheGame.playSound("/buu/sounds/shotcharge.wav");
			TheGame.playSound("/buu/sounds/chargevoice.wav");
		}
		if(_counter == 6) {
			_width = (int)(29*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot3.png");
			} else {
				_image = new Image("buu/attack1/shot3left.png");
				_x+=12;
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot4.png");
			} else {
				_image = new Image("buu/attack1/shot4left.png");
			}
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot5.png");
			} else {
				_image = new Image("buu/attack1/shot5left.png");
			}
		}
		if(_counter == 18) {
			_width = (int)(46*1.5);
			_height = (int)(43*1.5);
			int x;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot6.png");
				x = (int)(43*1.5);
				v = 16;
			} else {
				_image = new Image("buu/attack1/shot6left.png");
				_x-=13;
				x = (int)(-17*1.5);
				v = -16;
			}
			Hitbox attack = new HitboxImpl("buubolt", this, false, _x+x, _y+3, 30, 33, v, 0, 25, 18, new Image("buu/attack1/shot.png"));
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/buu/sounds/shot.wav");
			TheGame.playSound("/buu/sounds/shotvoice.wav");
		}
		if(_counter == 21) {
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot7.png");
			} else {
				_image = new Image("buu/attack1/shot7left.png");
			}
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1/shot8.png");
			} else {
				_image = new Image("buu/attack1/shot8left.png");
				_x+=9;
			}
		}
		if(_counter == 47) {
			_canact = true;
			_attack1 = false;
		}
	}
	
	public void attack2A() {
		_counter = 0;
		_canact = false;
		_attack2a = true;
		_landed = false;
		_xvelocity = 0;
        _yvelocity = -4;
        _width = (int)(32*1.5);
		_height = (int)(48*1.5);
		if(_facing.equals("right")) {
			_image = new Image("buu/attack1a/slam1.png");
		} else {
			_image = new Image("buu/attack1a/slam1left.png");
		}
	}
	
	public void executeAttack2A() {
		if(_counter == 4) {
			 _width = (int)(34*1.5);
			_height = (int)(48*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam2.png");
			} else {
				_image = new Image("buu/attack1a/slam2left.png");
			}
		}
		if(_counter == 7) {
			 _width = (int)(39*1.5);
			_height = (int)(51*1.5);
			int x;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam3.png");
				x = (int)(17*1.5);
			} else {
				_image = new Image("buu/attack1a/slam3left.png");
				x = 0;
			}
			_yvelocity = 20;
			Hitbox attack = new OffsetHitbox("buuslam", this, x, 0, (int)(22*1.5), (int)(51*1.5), 16, 18, _clear);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);

			TheGame.playSound("/buu/sounds/slam.wav");
			TheGame.playSound("/buu/sounds/slamvoice.wav");
			
		}
		if(_counter == 11) {
			 _width = (int)(41*1.5);
			_height = (int)(51*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam4.png");
			} else {
				_image = new Image("buu/attack1a/slam4left.png");
			}
		}
		if(_counter == 15) {
			 _width = (int)(41*1.5);
			_height = (int)(45*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam5.png");
			} else {
				_image = new Image("buu/attack1a/slam5left.png");
			}
		}
		if(_counter == 19) {
			 _width = (int)(35*1.5);
			_height = (int)(45*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam6.png");
			} else {
				_image = new Image("buu/attack1a/slam6left.png");
			}
		}
		if(_counter == 23) {
			 _width = (int)(33*1.5);
			 TheGame.clearHitboxes("buuslam", this);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam7.png");
			} else {
				_image = new Image("buu/attack1a/slam7left.png");
			}
		}
		if(_onplatform && !_landed) {
			TheGame.clearHitboxes("buuslam", this);
			_landed = true;
			_counter = 200;
			_width = (int)(33*1.5);
			_height = (int)(45*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack1a/slam7.png");
			} else {
				_image = new Image("buu/attack1a/slam7left.png");
			}
		}
		if(_counter == 215) {
			_attack2a = false;
			_canact = true;
		}
	}

	@Override
	public void attack2() {
		_counter = 0;
		_canact = false;
		_attack2 = true;
		_xvelocity = 0;
		_width = (int)(33*1.5);
		_height = (int)(46*1.5);
		if(_facing.equals("right")) {
			_image = new Image("buu/attack2/whip1.png");
		} else {
			_image = new Image("buu/attack2/whip1left.png");
		}
		
	}
	
	public void executeAttack2() {
		if(_xtumbling) {
			_attack2 = false;
			_canact = true;
			TheGame.clearHitboxes("buuwhip", this);
		}
		if(_counter == 2){
			_width = (int)(38*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip2.png");
			} else {
				_image = new Image("buu/attack2/whip2left.png");
			}
		}
		if(_counter == 18){
			_width = (int)(42*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip3.png");
			} else {
				_image = new Image("buu/attack2/whip3left.png");
				_x-=6;
			}
			TheGame.playSound("/buu/sounds/whipvoice.wav");
		}
		if(_counter == 20){
			_width = (int)(70*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip4.png");
			} else {
				_image = new Image("buu/attack2/whip4left.png");
				_x-=(int)(28*1.5);
			}
		}
		if(_counter == 22){
			_width = (int)(119*1.5);
			int x;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip5.png");
				x = (int)(41*1.5);
			} else {
				_image = new Image("buu/attack2/whip5left.png");
				_x-=(int)(49*1.5);
				x = 0;
			}
			Hitbox attack = new OffsetHitbox("buuwhip", this, x, 0, (int)(78*1.5), (int)(46*1.5), 30, 22, _clear);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/buu/sounds/whip.wav");
		}
		if(_counter == 24){
			_width = (int)(75*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip6.png");
			} else {
				_image = new Image("buu/attack2/whip6left.png");
				_x+=(int)(44*1.5);
			}
			TheGame.clearHitboxes("buuwhip", this);
		}
		if(_counter == 26){
			_width = (int)(35*1.5);
			_height = (int)(48*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip7.png");
			} else {
				_image = new Image("buu/attack2/whip7left.png");
				_x+=(int)(40*1.5);
			}
		}
		if(_counter == 28){
			_width = (int)(37*1.5);
			_height = (int)(48*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack2/whip8.png");
			} else {
				_image = new Image("buu/attack2/whip8left.png");
			}
		}
		if(_counter == 35) {
			_canact = true;
			_attack2 = false;
		}
	}

	@Override
	public void attack3() {
		if(_canattack3){
		_counter = 0;
		_canact = false;
		_attack3 = true;
		_xvelocity = 0;
		_width = (int)(36*1.5);
		_height = (int)(41*1.5);
		if(_facing.equals("right")) {
			_image = new Image("buu/attack3/jump1.png");
		} else {
			_image = new Image("buu/attack3/jump1left.png");
		}
		}
		
	}
	
	public void executeAttack3() {
		if(_counter == 3) {
			_width = (int)(40*1.5);
			_height = (int)(41*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/attack3/jump2.png");
			} else {
				_image = new Image("buu/attack3/jump2left.png");
			}
		}
		if(_counter == 10) {
			_width = (int)(36*1.5);
			_height = (int)(45*1.5);
			_y-=6;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack3/jump3.png");
			} else {
				_image = new Image("buu/attack3/jump3left.png");
			}
		}
		if(_counter == 13) {
			_width = (int)(51*1.5);
			_height = (int)(47*1.5);
			_y-=3;
			int x;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack3/jump4.png");
				_xvelocity = 11;
				x = (int)(23*1.5);
			} else {
				_image = new Image("buu/attack3/jump4left.png");
				_x-=(int)(13*1.5);
				_xvelocity = -11;
				x = 0;
			}
			_yvelocity = -17;
			_xtumbling = true;
			Hitbox attack = new OffsetHitbox("buukick", this, x, 0, (int)(28*1.5), (int)(47*1.5), 18, 22, _clear);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/buu/sounds/kick.wav");
			TheGame.playSound("/buu/sounds/kickvoice.wav");
		}
		if(_counter == 16) {
			_width = (int)(35*1.5);
			_height = (int)(47*1.5);
			_y-=6;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack3/jump5.png");
			} else {
				_image = new Image("buu/attack3/jump5left.png");
				_x+=(int)(16*1.5);
			}
		}
		if(_counter == 19) {
			_width = (int)(35*1.5);
			_height = (int)(48*1.5);
			_y-=1;
			if(_facing.equals("right")) {
				_image = new Image("buu/attack3/jump6.png");
			} else {
				_image = new Image("buu/attack3/jump6left.png");
			}
			_canattack3 = false;
			
		}
		
		if(_counter == 25) {
			if(_facing.equals("right")) {
				_image = new Image("buu/attack3/jump7.png");
			} else {
				_image = new Image("buu/attack3/jump7left.png");
			}
			TheGame.clearHitboxes("buukick", this);
		}
		if(_counter == 30) {
			_canact = true;
			_attack3 = false;
			
		}
	}


	@Override
	public void attackU() {
		if(_ultcharge >= 0) {
		_attacku = true;
		_canact = false;
		_xvelocity = 0;
		_yvelocity = -15;
		_counter = 0;
		_ultcharge = 0;
		_haspriority = true;
		_immune = true;
		}
	}
	
	public void executeAttackU() {
		if(_counter >= 20 && _counter < 25) {
			TheGame._gc.setFill(Color.WHITE);
			TheGame._gc.fillRect(0, 0, 900, 600);
		}
		if(_counter == 20) {
			_yvelocity = 0;
			_gravity = false;
			TheGame.playSound("/buu/sounds/flash.wav");
		}
		if(_counter == 23) {
			_width = (int)(54*1.5);
			_height = (int)(48*1.45);
			if(_facing.equals("right")) {
				_image = new Image("buu/ult/ult1.png");
			} else {
				_image = new Image("buu/ult/ult1left.png");
			}
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image("buu/ult/ult2.png");
			} else {
				_image = new Image("buu/ult/ult2left.png");
			}
		}
		if(_counter >= 35 && _counter < 55) {
			int x;
			double v;
			if(_facing.equals("right")) {
				x = -60;
				v = 10;
			} else {
				x = -52;
				v = -10;
			}
			TheGame._gc.drawImage(new Image("buu/ult/bomb.png"), _x+x, _y-(int)(145*1.5), (int)(145*1.5), (int)(146*1.5));
		}
		if(_counter == 35) {
			TheGame.playSound("/buu/sounds/ultcharge.wav");
			TheGame.playSound("/buu/sounds/ultvoice1.wav");
		}
		if(_counter == 55) {
			int x;
			double v;
			if(_facing.equals("right")) {
				x = -60;
				v = 10;
				_image = new Image("buu/ult/ult1.png");
			} else {
				_image = new Image("buu/ult/ult1left.png");
				x = -52;
				v = -10;
			}
			Hitbox attack = new HitboxImpl("buuspiritbomb", this, false, _x+x, _y-(int)(145*1.5), (int)(145*1.5), (int)(146*1.5), v, 7, 5, 10, new Image("buu/ult/bomb.png"));
			attack.setDissappearOnHit(false);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/buu/sounds/ultshot.wav");
			TheGame.playSound("/buu/sounds/ultvoice2.wav");
		}
		if(_counter >= 125 && _counter < 130) {
			TheGame._gc.setFill(Color.WHITE);
			TheGame._gc.fillRect(0, 0, 900, 600);
		}
		if(_counter == 125) {
			TheGame.playSound("/buu/sounds/flash.wav");
		}
		if(_counter == 127) {
			_width = (int)(37*1.5);
			_height = (int)(46*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/stance1.png");
			} else {
				_image = new Image("buu/stance1left.png");
			}
		}
		if(_counter == 130) {
			_attacku = false;
			_gravity = true;
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
		_spritecounter++;
		if(_attacku) {
			_bombcounter++;
		}
	}
	
	@Override
	public void pressAttack2() {
		if(_canjump1 && _canattack3) {
			super.pressAttack2();
		} else if(_canact) {
			attack2A();
		}
	}
	
	public void doIntro() {
		if(_counter == 3) {
			_width = (int)(32*1.5);
			_height = (int)(71*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro1.png");
			} else {
				_image = new Image("buu/intro/intro1left.png");
			}
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro2.png");
			} else {
				_image = new Image("buu/intro/intro2left.png");
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro3.png");
			} else {
				_image = new Image("buu/intro/intro3left.png");
			}
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro4.png");
			} else {
				_image = new Image("buu/intro/intro4left.png");
			}
		}
		if(_counter == 15) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro5.png");
			} else {
				_image = new Image("buu/intro/intro5left.png");
			}
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro3.png");
			} else {
				_image = new Image("buu/intro/intro3left.png");
			}
		}
		if(_counter == 21) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro4.png");
			} else {
				_image = new Image("buu/intro/intro4left.png");
			}
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro5.png");
			} else {
				_image = new Image("buu/intro/intro5left.png");
			}
		}
		if(_counter == 27) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro3.png");
			} else {
				_image = new Image("buu/intro/intro3left.png");
			}
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro4.png");
			} else {
				_image = new Image("buu/intro/intro4left.png");
			}
		}
		if(_counter == 33) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro5.png");
			} else {
				_image = new Image("buu/intro/intro5left.png");
			}
		}
		if(_counter == 36) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro3.png");
			} else {
				_image = new Image("buu/intro/intro3left.png");
			}
		}
		if(_counter == 39) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro4.png");
			} else {
				_image = new Image("buu/intro/intro4left.png");
			}
		}
		if(_counter == 42) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro5.png");
			} else {
				_image = new Image("buu/intro/intro5left.png");
			}
		}
		if(_counter == 45) {
			if(_facing.equals("right")) {
				_image = new Image("buu/intro/intro2.png");
			} else {
				_image = new Image("buu/intro/intro2left.png");
			}
		}
		if(_counter == 48) {
			_width = (int)(37*1.5);
			_height = (int)(46*1.5);
			_y+=(int)(26*1.5);
			if(_facing.equals("right")) {
				_image = new Image("buu/stance1.png");
			} else {
				_image = new Image("buu/stance1left.png");
			}
		}
	}

}
