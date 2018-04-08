package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.AnimatedHitbox;
import kf.Hitbox;
import kf.OffsetHitbox;
import kf.TheGame;

public class SpecterChar extends CharacterImpl {
	private List<Image> _sprites = new ArrayList<Image>();
	private List<Image> _spritesleft = new ArrayList<Image>();
	private List<Image> _ultsprites = new ArrayList<Image>();
	private List<Image> _ultspritesleft = new ArrayList<Image>();
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _ultattack;
	private int _ultattackcounter = 0;
	private int _attack1stage = 0;
	private int _cd3 = 0;
	private int _cd1 = 0;
	private String _ultfacing;
	private String _skin;
	
	private boolean _canattack3;
	// cycles through the sprites
			private int _spritecounter = 0;
			// the index of the current sprite
			private int _spriteindex = 0;
	
	
	public SpecterChar(String ID, int skin) {
		super(ID);
		if(skin == 0) {
			_skin = "specter/";
			
		}
		if(skin == 1){
			_skin = "specter/skin1/";
		}
		_width = (int)(53 *1.5);
		_height = (int)(34*1.5);
		_damagefactor = .9;
		_speedfactor = 1;
		_sprites.add(new Image(_skin + "stance/stance1.png"));
		_sprites.add(new Image(_skin + "stance/stance2.png"));
		_sprites.add(new Image(_skin + "stance/stance3.png"));
		_sprites.add(new Image(_skin + "stance/stance4.png"));
		_spritesleft.add(new Image(_skin + "stance/stance1left.png"));
		_spritesleft.add(new Image(_skin + "stance/stance2left.png"));
		_spritesleft.add(new Image(_skin + "stance/stance3left.png"));
		_spritesleft.add(new Image(_skin + "stance/stance4left.png"));
		_ultsprites.add(new Image(_skin + "ultstance/1.png"));
		_ultsprites.add(new Image(_skin + "ultstance/2.png"));
		_ultsprites.add(new Image(_skin + "ultstance/3.png"));
		_ultsprites.add(new Image(_skin + "ultstance/4.png"));
		_ultspritesleft.add(new Image(_skin + "ultstance/1left.png"));
		_ultspritesleft.add(new Image(_skin + "ultstance/2left.png"));
		_ultspritesleft.add(new Image(_skin + "ultstance/3left.png"));
		_ultspritesleft.add(new Image(_skin + "ultstance/4left.png"));
		
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 &&  !_attack3 && !_attacku && !_intro) {
			_width = (int)(53*1.5);
			_height = (int)(34*1.5);
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
		if(_attacku ) {
			if(!_ultattack && _counter >=48 && _counter < 300) {
				_width = (int)(68*1.5);
				_height = (int)(69*1.5);
				if(_facing.equals("right")) {
					if(_spritecounter % 4 == 0) {
						_image = _ultsprites.get(_spriteindex);
						if(_spriteindex < 3) {
							_spriteindex++;
						} else {
							_spriteindex = 0;
						}
					}
				} else {
					if(_spritecounter % 4 == 0) {
						_image = _ultspritesleft.get(_spriteindex);
						if(_spriteindex < 3) {
							_spriteindex++;
						} else {
							_spriteindex = 0;
						}
					}
				}
			}
			executeAttackU();
		}
		if(_ultattack) {
			executeUltAttack();
		}
		if(_onplatform) {
			_canattack3 = true;
		}
		
	}
	
	@Override
	public Image getStockImage() {
		return new Image(_skin + "stock.png");
	}

	@Override
	public void attack1() {
		if(!_attacku && _cd1 ==0){
		_counter = 4;
		_xvelocity = 0;
		_attack1 = true;
		_canact = false;
		_cd1 = 40;
		_y-=3;
		_width = (int)(51*1.5);
		_height = (int)(36*1.5);
		if(_facing.equals("right")) {
			_image = new Image(_skin + "slash1.png");
		} else {
			_image = new Image(_skin + "slash1left.png");
		}
	}
	}
	
	public void executeAttack1() {
		if(_xtumbling) {
			_attack1 = false;
			_canact = true;
		}
		if(_counter == 18) {
			_y+=3;
			_width = (int)(67*1.5);
			_height = (int)(33*1.5);
			int x;
			double v;
			if(_facing.equals("right")) {
				_image = new Image(_skin + "slash2.png");
				x=(int) (42*1.5);
				v = 12;
			} else {
				_image = new Image(_skin + "slash2left.png");
				_x-=24;
				x=0;
				v = -12;
			}
			List<Image> images = new ArrayList<Image>();
			images.add(new Image(_skin + "scythe1.png"));
			images.add(new Image(_skin + "scythe2.png"));
			images.add(new Image(_skin + "scythe3.png"));
			images.add(new Image(_skin + "scythe4.png"));
			images.add(new Image(_skin + "scythe5.png"));
			images.add(new Image(_skin + "scythe6.png"));
			images.add(new Image(_skin + "scythe7.png"));
			images.add(new Image(_skin + "scythe8.png"));
			Hitbox attack = new AnimatedHitbox("scythethrow", this, false, x+_x, _y-17, 68, 68, v, 0, 12, 20, images, 3);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/specter/sounds/specter1.wav");
		}
		if(_counter == 20) {
			_y-=1;
			_width = (int)(65*1.5);
			_height = (int)(34*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "slash3.png");
			} else {
				_image = new Image(_skin + "slash3left.png");
			}
		}
		if(_counter == 25) {
			_canact = true;
			if(_facing.equals("left")){
				_x+=24;
			}
			_attack1 = false;
		}
	}

	@Override
	public void attack2() {
		if(!_attacku){
		_counter = 0;
		_xvelocity = 0;
		_canact = false;
		_attack2 = true;
		_width = (int)(56*1.5);
		_height = (int)(36*1.5);
		if(_facing.equals("right")) {
			_image = new Image(_skin + "smash1.png");
		} else {
			_image = new Image(_skin + "smash1left.png");
		}
	}
	}
	
	public void executeAttack2() {
		if(_xtumbling) {
			_attack2 = false;
			_getxleft = true;
		}
		
		if(_counter == 12) {
			_width = (int)(87*1.5);
			_height = (int)(39*1.5);
			int x;
			if(_facing.equals("right")) {
				_image = new Image(_skin + "smash2.png");
				x=(int)(34*1.5);
			} else {
				_image = new Image(_skin + "smash2left.png");
				_x-=45;
				x=0;
				_getxleft = false;
			}
			Hitbox attack = new OffsetHitbox("specsmash", this, x, 15, (int)(53*1.5), (int)(29*1.5), 30, 30, _clear);
			TheGame._attacks.add(attack);
			TheGame.playSound("/specter/sounds/specter2.wav");
		}
		if(_counter == 14) {
			_width = (int)(82*1.5);
			_height = (int)(40*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "smash3.png");
			} else {
				_image = new Image(_skin + "smash3left.png");
			}
		}
		if(_counter == 16) {
			_width = (int)(76*1.5);
			_height = (int)(40*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "smash4.png");
			} else {
				_image = new Image(_skin + "smash4left.png");
				_x+=9;
			}
		}
		if(_counter == 18) {
			_width = (int)(75*1.5);
			_height = (int)(40*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "smash5.png");
			} else {
				_image = new Image(_skin + "smash5left.png");
				_x+=1;
			}
		}
		if(_counter == 20) {
			_width = (int)(55*1.5);
			_height = (int)(40*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "smash6.png");
			} else {
				_image = new Image(_skin + "smash6left.png");
				_x+=30;
			}
			TheGame.clearHitboxes("specsmash", this);
		}
		if(_counter == 30) {
			_attack2 = false;
			_canact = true;
			_getxleft = true;
			if(_facing.equals("left")) {
				_x+=5;
			}
		}
	}

	@Override
	public void attack3() {
		if(_attack1stage == 0 && _cd3 == 0 && !_attacku && _canattack3) {
			_counter = 0;
			_attack1stage = 1;
			_canact = false;
			_cd3 = 30;
			_xvelocity = 0;
			_attack3 = true;
			_width = (int)(58*1.5);
			_height = (int)(45*1.5);
			int x;
			if(_facing.equals("right")) {
				_image = new Image(_skin + "sliceup1.png");
				_xvelocity = 25;
				x = (int)(29*1.5);
			} else {
				_image = new Image(_skin + "sliceup1left.png");
				_x-=7;
				_xvelocity = -25;
				x=0;
			}
			Hitbox attack = new OffsetHitbox("specdash1", this, x, 0, (int)(29*1.5), (int)(35*1.5), 10, 13, _clear);
			TheGame._attacks.add(attack);
			TheGame.playSound("/specter/sounds/specter3.wav");
			_xtumbling = true;
			_yvelocity = -11;
		}
		if(_attack1stage == 1 && _counter >=6) {
			_attack1stage = 2;
			_counter = 0;
			_width = (int)(52*1.5);
			_height = (int)(43*1.5);
			int x;
			if(_facing.equals("right")) {
				_image = new Image(_skin + "slicedown1.png");
				_xvelocity = 25;
				x = (int)(29*1.5);
			} else {
				_image = new Image(_skin + "slicedown1left.png");
				_xvelocity = -25;
				x=0;
			}
			_xtumbling = true;
			_yvelocity = 8;
			TheGame.clearHitboxes("specdash1", this);
			Hitbox attack = new OffsetHitbox("specdash1", this, x, 0, (int)(29*1.5), (int)(35*1.5), 10, 13, _clear);
			TheGame._attacks.add(attack);
			TheGame.playSound("/specter/sounds/specter3.wav");
		}
	}
	
	public void executeAttack3() {
		if(_attack1stage == 1 ) {
			if(_counter == 3) {
				_width = (int)(60*1.5);
				_height = (int)(44*1.5);
				if(_facing.equals("right")) {
					_image = new Image(_skin + "sliceup2.png");
				} else {
					_image = new Image(_skin + "sliceup2left.png");
				}
			}
			if(_counter == 6) {
				_width = (int)(61*1.5);
				_height = (int)(43*1.5);
				if(_facing.equals("right")) {
					_image = new Image(_skin + "sliceup3.png");
				} else {
					_image = new Image(_skin + "sliceup3left.png");
				}
				_canattack3 = false;
			}
			if(_counter == 20) {
				TheGame.clearHitboxes("specdash1", this);
				_attack3 = false;
				_canact = true;
				_attack1stage = 0;
			}
		}
		if(_attack1stage == 2) {
			if(_counter == 3) {
				_width = (int)(65*1.5);
				_height = (int)(39*1.5);
				if(_facing.equals("right")) {
					_image = new Image(_skin + "slicedown2.png");
				} else {
					_image = new Image(_skin + "slicedown2left.png");
				}
			}
			if(_counter == 6) {
				_width = (int)(64*1.5);
				_height = (int)(42*1.5);
				if(_facing.equals("right")) {
					_image = new Image(_skin + "slicedown3.png");
				} else {
					_image = new Image(_skin + "slicedown3left.png");
				}
			}
			if(_counter == 9) {
				_attack3 = false;
				_canact = true;
				_attack1stage = 0;
				TheGame.clearHitboxes("specdash1", this);
			}
			
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150){
		pressJump();
		_counter = 0;
		_attacku = true;
		_ultcharge = 0;
		_xvelocity = 0;
		_canact = false;
		_immune = true;
		TheGame.playSound("/specter/sounds/specter6.wav");
		}
	}
	
	public void executeAttackU() {
		if(_counter == 22) {
			_image = new Image(_skin + "ult1.png");
			_width = (int)(102*1.5);
			_height = (int)(78*1.5);
			_yvelocity =0;
			_gravity = false;
			
		}
		if(_counter == 34) {
			_image = new Image(_skin + "ult2.png");
		}
		if(_counter == 36) {
			_image = new Image(_skin + "ult3.png");
		}
		if(_counter == 38) {
			_image = new Image(_skin + "ult4.png");
		}
		if(_counter == 40) {
			_image = new Image(_skin + "ult5.png");
		}
		if(_counter == 42) {
			_image = new Image(_skin + "ult6.png");
		}
		if(_counter == 44) {
			_image = new Image(_skin + "ult7.png");
		}
		if(_counter == 46) {
			_image = new Image(_skin + "ult8.png");
		}
		if(_counter == 48) {
			_image = new Image(_skin + "ult9.png");
			_canact = true;
		}
		
		if(_counter == 300) {
			_width = (int)(64*1.5);
			_height = (int)(61*1.5);
			_ultattack = false;
			_canact = false;
			_xvelocity = 0;
			_yvelocity = 0;
			_image = new Image("/specter/ultvanish1.png");
			TheGame.playSound("/specter/sounds/specter3.wav");
		}
		if(_counter == 302) {
			_image = new Image("/specter/ultvanish2.png");
		}
		if(_counter == 304) {
			_image = new Image("/specter/ultvanish3.png");
		}
		if(_counter == 306) {
			_image = new Image("/specter/ultvanish4.png");
		}
		if(_counter == 308) {
			_image = new Image("/specter/ultvanish5.png");
		}
		if(_counter == 310) {
			_image = new Image("/specter/ultvanish6.png");
		}
		if(_counter == 312) {
			_image = new Image("/specter/ultvanish7.png");
		}
		if(_counter == 314) {
			_image = new Image("/specter/ultvanish8.png");
		}
		if(_counter == 316) {
			_image = new Image("/specter/ultvanish9.png");
		}
		if(_counter == 318) {
			_image = new Image("/specter/ultvanish10.png");
		}
		if(_counter == 320) {
			_image = _clear;
		}
		if(_counter == 350) {
			_width = (int)(49*1.5);
			_height = (int)(45*1.5);
			_image = new Image("/specter/appear1.png");
			TheGame.playSound("/specter/sounds/specter3.wav");
		}
		if(_counter == 352) {
			_image = new Image("/specter/appear2.png");
		}
		if(_counter == 354) {
			_image = new Image("/specter/appear3.png");
		}
		if(_counter == 356) {
			_image = new Image("/specter/appear4.png");
			_gravity = true;
		}
		if(_counter == 358) {
			_image = new Image("/specter/appear5.png");
		}
		if(_counter == 360) {
			_image = new Image("/specter/appear6.png");
		}
		if(_counter == 362) {
			_image = new Image("/specter/appear7.png");
			_immune = false;
			_canact = true;
			_attacku = false;
			_getxleft = true;
		}
		
	}
	
	public void executeUltAttack() {
		if(_ultattackcounter == 0) {
			_width = (int)(161*1.5);
			_height = (int)(84*1.5);
			if(_facing.equals("right")) {
				_ultfacing = "right";
				_image = new Image(_skin + "ultslash1.png");
			} else {
				_ultfacing = "left";
				_image = new Image(_skin + "ultslash1left.png");
				_x-=(int)(85*1.5);
			}
			TheGame.playSound("/specter/sounds/specter4.wav");
		}
		if(_ultattackcounter == 16) {
			int x;
			if(_ultfacing.equals("right")) {
				_image = new Image(_skin + "ultslash2.png");
				x = (int)(77*1.5);
			} else {
				_image = new Image(_skin + "ultslash2left.png");
				x=0;
			}
			Hitbox attack = new OffsetHitbox("ultslash", this, x, 36, (int)(84*1.5), (int)(48*1.5), 40, 40, _clear);
			TheGame._attacks.add(attack);
			TheGame.playSound("/specter/sounds/specter5.wav");
		}
		if(_ultattackcounter == 19) {
			if(_ultfacing.equals("right")) {
				_image = new Image(_skin + "ultslash3.png");
			} else {
				_image = new Image(_skin + "ultslash3left.png");
			}
			TheGame.clearHitboxes("ultslash", this);
		}
		if(_ultattackcounter == 30) {
			
			_width = (int)(102*1.5);
			_height = (int)(78*1.5);
			if(_ultfacing.equals("right")) {
				_image = new Image(_skin + "ult9.png");
			} else {
				_image = new Image(_skin + "ult9left.png");
				_x+=(int)(85*1.5);
			}

			_ultattack = false;
			_getxleft = true;
		}
		
	}

	@Override
	public Color getColor() {
		return Color.DARKRED;
	}
	
	@Override
	public void pressAttack3() {
		if(!_attack3) {
			if(_attacku && !_ultattack && _canact){
				_ultattack = true;
				_ultattackcounter = 0;
				if(_facing.equals("left")){
				_getxleft = false;
				}
			} else {
			super.pressAttack3();
			}
		} else if(_attack1stage  == 1) {
			attack3();
		}
	}
	@Override
	public void pressAttack2() {
		if(!_attack3) {
			if(_attacku && !_ultattack && _canact){
				_ultattack = true;
				_ultattackcounter = 0;
				if(_facing.equals("left")){
					_getxleft = false;
					}
			} else {
			super.pressAttack2();
			}
		}

	}
	@Override
	public void pressAttack1() {
		if(!_attack3) {
			if(_attacku && !_ultattack && _canact){
				_ultattack = true;
				_ultattackcounter = 0;
				if(_facing.equals("left")){
					_getxleft = false;
					}
			} else {
			super.pressAttack1();
			}
		}

	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd3 > 0) {
			_cd3--;
		}
		if(_cd1 > 0) {
			_cd1--;
		}
		_ultattackcounter++;
		_spritecounter++;
	}
	
	@Override
	public void pressUp() {
		if (_attacku) {
			_yvelocity = -7;
		}
	}

	@Override
	public void pressDown() {
		if (_attacku) {
			_yvelocity = 7;
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
			_yvelocity = -7;
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
	@Override
	public void pressRight() {
		if (_attacku) {
			_xvelocity = 7;
			_facing = "right";
		} else {
			super.pressRight();
		}
	}
	@Override
	public void pressLeft() {
		if (_attacku) {
			_xvelocity = -7;
			_facing = "left";
		} else {
			super.pressLeft();
		}
	}
	
	@Override
	public void doIntro() {
		if(_counter == 0) {
			_width = (int)(83*1.5);
			_height = (int)(70*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro1.png");
			} else {
				_image = new Image(_skin + "intro/intro1left.png");
			}
			TheGame.playSound("/specter/sounds/specter6.wav");
		}
		if(_counter == 3) {
		if(_facing.equals("right")) {
			_image = new Image(_skin + "intro/intro2.png");
		} else {
			_image = new Image(_skin + "intro/intro2left.png");
		}
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro3.png");
			} else {
				_image = new Image(_skin + "intro/intro3left.png");
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro4.png");
			} else {
				_image = new Image(_skin + "intro/intro4left.png");
			}
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro5.png");
			} else {
				_image = new Image(_skin + "intro/intro5left.png");
			}
		}
		if(_counter == 15) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro6.png");
			} else {
				_image = new Image(_skin + "intro/intro6left.png");
			}
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro7.png");
			} else {
				_image = new Image(_skin + "intro/intro7left.png");
			}
		}
		if(_counter == 21) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro8.png");
			} else {
				_image = new Image(_skin + "intro/intro8left.png");
			}
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro9.png");
			} else {
				_image = new Image(_skin + "intro/intro9left.png");
			}
		}
		if(_counter == 27) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro10.png");
			} else {
				_image = new Image(_skin + "intro/intro10left.png");
			}
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image(_skin + "intro/intro11.png");
			} else {
				_image = new Image(_skin + "intro/intro11left.png");
			}
		}
		if(_counter == 35) {
			_width = (int)(53*1.5);
			_height = (int)(34*1.5);
			_y+=(int)(35*1.5);
			if(_facing.equals("right")) {
				_image = new Image(_skin + "stance/stance1.png");
				_x-=40;
			} else {
				_image = new Image(_skin + "stance/stance1left.png");
				_x+=40;
			}
		}
	}

}
