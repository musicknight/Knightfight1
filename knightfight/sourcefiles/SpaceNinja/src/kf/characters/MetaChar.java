package kf.characters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.OffsetHitbox;
import kf.TheGame;

public class MetaChar extends CharacterImpl {
	private int _jumpcounter = 0;
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _fly;
	//sees if attack3 hit something
	private boolean _bounced = false;
	private int _flycounter = 0;
	private double _holder = 0;
	
	
	public MetaChar(String ID){
		super(ID);
		_width = (int)(42*1.5);
		_height = (int)(32*1.5);
		_damagefactor = 1.2;
		_speedfactor = 1.15;
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku && !_fly && !_intro) {
			_width = (int)(42*1.5);
			_height = (int)(32*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/meta.png");
			} else {
				_image = new Image("metaknight/metaleft.png");
			}
		}
		if(_fly) {
			executeFly();
		}
		if(_attack1) {
			executeAttack1();
			if(_counter >= 14 && _counter < 16) {
				if(_facing.equals("right")){
				TheGame._gc.drawImage(new Image("metaknight/attack1/down4a.png"), _x, _y, (int)(73*1.5), (int)(49*1.5));
				} else {
					TheGame._gc.drawImage(new Image("metaknight/attack1/down4aleft.png"), _x +_width - (int)(73*1.5), _y, (int)(73*1.5), (int)(49*1.5));
				}
			}
			if(_counter >= 16 && _counter < 18) {
				if(_facing.equals("right")){
				TheGame._gc.drawImage(new Image("metaknight/attack1/down5a.png"), _x, _y, (int)(72*1.5), (int)(49*1.5));
				} else {
					TheGame._gc.drawImage(new Image("metaknight/attack1/down5aleft.png"), _x +_width - (int)(72*1.5), _y, (int)(72*1.5), (int)(49*1.5));
				}
			}
			if(_counter >= 18 && _counter < 20) {
				if(_facing.equals("right")){
				TheGame._gc.drawImage(new Image("metaknight/attack1/down6a.png"), _x, _y+20, (int)(40*1.5), (int)(33*1.5));
				} else {
					TheGame._gc.drawImage(new Image("metaknight/attack1/down6aleft.png"), _x +_width - (int)(40*1.5), _y+20, (int)(40*1.5), (int)(33*1.5));
				}
			}
			if(_counter >= 20 && _counter < 22) {
				if(_facing.equals("right")){
				TheGame._gc.drawImage(new Image("metaknight/attack1/down7a.png"), _x, _y, (int)(36*1.5), (int)(33*1.5));
				} else {
					TheGame._gc.drawImage(new Image("metaknight/attack1/down7aleft.png"), _x +_width - (int)(36*1.5), _y, (int)(36*1.5), (int)(33*1.5));
				}
			}
			if(_counter >= 22 && _counter < 24) {
				if(_facing.equals("right")){
				TheGame._gc.drawImage(new Image("metaknight/attack1/down8a.png"), _x, _y, (int)(32*1.5), (int)(36*1.5));
				} else {
					TheGame._gc.drawImage(new Image("metaknight/attack1/down8aleft.png"), _x +_width - (int)(32*1.5), _y, (int)(32*1.5), (int)(36*1.5));
				}
			}
			if(_counter >= 22 && _counter < 24) {
				if(_facing.equals("right")){
				TheGame._gc.drawImage(new Image("metaknight/attack1/down9.png"), _x, _y, (int)(31*1.5), (int)(36*1.5));
				} else {
					TheGame._gc.drawImage(new Image("metaknight/attack1/down9left.png"), _x +_width - (int)(31*1.5), _y, (int)(31*1.5), (int)(36*1.5));
				}
			}
			if(_counter >= 24 && _counter < 26) {
				if(_facing.equals("right")){
					TheGame._gc.drawImage(new Image("metaknight/attack1/down5a.png"), _x, _y, (int)(72*1.5), (int)(49*1.5));
					} else {
						TheGame._gc.drawImage(new Image("metaknight/attack1/down5aleft.png"), _x +_width - (int)(72*1.5), _y, (int)(72*1.5), (int)(49*1.5));
					}
			}

		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attacku) {
			executeAttackU();
		}
		if(_onplatform) {
			_jumpcounter = 0;
		}
	}
	
	
	@Override
	public Image getStockImage() {
		return new Image("metaknight/stock.png");
	}

	@Override
	public void attack1() {
		_holder = _damage;
		_attack1 = true;
		_counter = 5;
		_xvelocity = 0;
		_canact = false;
		_y-=10;
		_width = (int)(33*1.5);
		_height = (int)(39*1.5);
		if(_facing.equals("right")) {
			_image = new Image("metaknight/attack1/down1.png");
		} else {
			_image = new Image("metaknight/attack1/down1left.png");
			_x+=9;
		}
		
		
	}
	
	public void executeAttack1() {
		if(_holder != _damage ){
			_canact = true;
			_attack1 = false;
		}
		
		if(_counter == 10) {
			_width = (int)(40*1.5);
			_height = (int)(31*1.5);
			_y+=10;
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/down2.png");
				_xvelocity =33;
				_x+=3;
			} else {
				_image = new Image("metaknight/attack1/down2left.png");
				_x-=11;
				_xvelocity = -33;
			}
			_xtumbling = true;
		}
		if(_counter == 12){
			_y -= 4;
			_width = (int)(43*1.5);
			_height = (int)(34*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/down2.5.png");
			} else {
				_image = new Image("metaknight/attack1/down2.5left.png");
				
			}
		}
		if(_counter == 14){
			_width = (int)(55*1.5);
			_height = (int)(34*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/down3.png");
			} else {
				_image = new Image("metaknight/attack1/down3left.png");
				_x-=18;
				
			}
		}
		if(_counter == 16){
			_y-=4;
			_width = (int)(32*1.5);
			_height = (int)(36*1.5);
			int x;
			if(_facing.equals("right")) {
				x = (int)(28*1.5);
			} else {
				x = _width - (int)(73*1.5);
				_x+=(int)(23*1.5);
				
			}
			_image = _clear;
			Hitbox attack = new OffsetHitbox("metaslash1", this, x, 0, (int)(45*1.5), (int)(49*1.5), 15, 12, _clear);
			attack.setSetKnockback(true);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/metaknight/sounds/slash.wav");
		}
		if(_counter == 18){
			_width = (int)(35*1.5);
			_height = (int)(36*1.5);
			if(_facing.equals("right")) {
			} else {
				_x-=4;
				
			}
		}
		if(_counter == 20){
			_width = (int)(31*1.5);
			_height = (int)(24*1.5);
			_y+=18;
			if(_facing.equals("right")) {
			} else {
				_x-=6;
				
			}
			TheGame.clearHitboxes("metaslash1", this);
		}
		if(_counter == 24){
			_y-=18;
			_width = (int)(32*1.5);
			_height = (int)(36*1.5);
			int x;
			if(_facing.equals("right")) {
				x = (int)(28*1.5);
			} else {
				x=_width - (int)(73*1.5);
				//_x+=(int)(23*1.5);
				
			}
			Hitbox attack = new OffsetHitbox("metaslash2", this, x, 0, (int)(45*1.5), (int)(49*1.5), 15, 12, _clear);
			attack.setSetKnockback(true);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/metaknight/sounds/slash.wav");
		}
		if(_counter == 26){
			_width = (int)(42*1.5);
			_height = (int)(35*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/up2.png");
			} else {
				_image = new Image("metaknight/attack1/up2left.png");
				_x-=10;
				
			}
			TheGame.clearHitboxes("metaslash2", this);
		}
		if(_counter == 34){
			_width = (int)(29*1.5);
			_height = (int)(25*1.5);
			_y+=14;
			int x;
			double v;
			Image i;
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/foward1.png");
				x = _width - 5;
				v = 20;
				i = new Image("metaknight/attack1/sword.png");
			} else {
				_image = new Image("metaknight/attack1/foward1left.png");
				_x+=19;
				x = -(int)(42*1.5) + 5;
				v = -20;
				i = new Image("metaknight/attack1/swordleft.png");
			}
			TheGame._attacks.add(new HitboxImpl("metaswordbolt", this, false,_x+x, _y+5, (int)(42*1.5), (int)(15*1.5), v, 0, 20, 12, i));
			TheGame.playSound("/metaknight/sounds/swordbolt.wav");
		}
		
		if(_counter == 42) {
			_canact = true;
			_attack1 = false;
			
		}
	}

	@Override
	public void attack2() {
		if(!_attack1){
		_attack2 = true;
		_canact = false;
		_counter = 0;
		_xvelocity = 0;
		_y-=10;
		_width = (int)(32*1.5);
		_height = (int)(33*1.5);
		if(_facing.equals("right")) {
			_image = new Image("metaknight/smash1.png");
		} else {
			_image = new Image("metaknight/smash1left.png");
			_x+=10;
		}
		}
		
	}
	
	public void executeAttack2() {
		if(_xtumbling) {
			_attack2 = false;
			_canact = true;
		}
		
		if (_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("metaknight/smash2.png");
			} else {
				_image = new Image("metaknight/smash2left.png");
				_x+=10;
			}
		}
		if(_counter == 12){
			_y-=4;
			_width = (int)(32*1.5);
			_height = (int)(36*1.5);
			int x;
			_image = _clear;
			if(_facing.equals("right")) {
				x = (int)(28*1.5);
				
			} else {
				x = _width - (int)(73*1.5);
				
			}
			TheGame.playSound("/metaknight/sounds/wind.wav");
		}
		if(_counter >= 12 && _counter < 14) {
			if(_facing.equals("right")){
			TheGame._gc.drawImage(new Image("metaknight/attack1/down4a.png"), _x, _y, (int)(73*1.5), (int)(49*1.5));
			} else {
				TheGame._gc.drawImage(new Image("metaknight/attack1/down4aleft.png"), _x +_width - (int)(73*1.5), _y, (int)(73*1.5), (int)(49*1.5));
			}
		}
		if(_counter == 14) {
			_y+=14;
			_width = (int)(74*1.5);
			_height = (int)(24*1.5);
			int x;
			if(_facing.equals("right")) {
				_image = new Image("metaknight/smash3.png");
				x = 16;
			} else {
				_image = new Image("metaknight/smash3left.png");
				_x-=63;
				x = 0;
			}
			Hitbox attack = new OffsetHitbox("metasmash", this, x, 7, (int)(63*1.5), (int)(16*1.5), 28, 25, _clear);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/metaknight/sounds/strike.wav");
		}
		
		if(_counter == 16) {
			_width = (int)(73*1.5);
			_height = (int)(24*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/smash4.png");
			} else {
				_image = new Image("metaknight/smash4left.png");
				_x+=1;
			}
		}
		if(_counter == 18) {
			_width = (int)(83*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/smash5.png");
			} else {
				_image = new Image("metaknight/smash5left.png");
				_x-=15;
			}
		}
		if(_counter == 20) {
			if(_facing.equals("right")) {
				_image = new Image("metaknight/smash6.png");
			} else {
				_image = new Image("metaknight/smash6left.png");
			}
		}
		if(_counter == 22) {
			_width = (int)(58*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/smash7.png");
			} else {
				_image = new Image("metaknight/smash7left.png");
				_x+=(int)((83-58)*1.5);
			}
			TheGame.clearHitboxes("metasmash", this);
		}
		if(_counter == 35) {
			_canact = true;
			_attack2 = false;
			if(_facing.equals("left")) {
				_x+=15;
			}
		}
		
	}

	@Override
	public void attack3() {
		if(!_attack1){
		_attack3 = true;
		_counter = 0;
		_canact = false;
		
		if(_facing.equals("right")) {
			_image = new Image("metaknight/attack1/down2.5.png");
		} else {
			_image = new Image("metaknight/attack1/down2.5left.png");
			
		}
		}
		
	}
	

	public void executeAttack3() {
		if(_counter == 2) {
			_width = (int)(40*1.5);
			_height = (int)(31*1.5);
			_y+=10;
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/down2.png");
				_xvelocity =20;
				_yvelocity = 16;
				_x+=3;
			} else {
				_image = new Image("metaknight/attack1/down2left.png");
				_x-=11;
				_xvelocity = -20;
				_yvelocity = 16;
			}
			_xtumbling = true;
		}
		if(_counter == 4){
			_y -= 4;
			_width = (int)(43*1.5);
			_height = (int)(34*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/down2.5.png");
			} else {
				_image = new Image("metaknight/attack1/down2.5left.png");
				
			}
		}
		if(_counter == 6){
			_width = (int)(55*1.5);
			_height = (int)(34*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/attack1/down3.png");
			} else {
				_image = new Image("metaknight/attack1/down3left.png");
				_x-=18;
				
			}
			TheGame.playSound("/metaknight/sounds/slash.wav");
		}
		if(_counter == 8){
			_y-=4;
			_width = (int)(32*1.5);
			_height = (int)(36*1.5);
			int x;
			if(_facing.equals("right")) {
				x = (int)(28*1.5);
			} else {
				x = _width - (int)(73*1.5);
				_x+=(int)(23*1.5);
				
			}
			_image = _clear;
			Hitbox attack = new OffsetHitbox("metajumpslash1", this, x, 0, (int)(45*1.5), (int)(49*1.5), 15, 12, _clear);
		
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
		}
		
		if(_counter >= 6 && _counter < 8) {
			if(_facing.equals("right")){
			TheGame._gc.drawImage(new Image("metaknight/attack1/down4a.png"), _x, _y, (int)(73*1.5), (int)(49*1.5));
			} else {
				TheGame._gc.drawImage(new Image("metaknight/attack1/down4aleft.png"), _x +_width - (int)(73*1.5), _y, (int)(73*1.5), (int)(49*1.5));
			}
		}
		if(_counter >= 8 && _counter < 10) {
			if(_facing.equals("right")){
			TheGame._gc.drawImage(new Image("metaknight/attack1/down5a.png"), _x, _y, (int)(72*1.5), (int)(49*1.5));
			} else {
				TheGame._gc.drawImage(new Image("metaknight/attack1/down5aleft.png"), _x +_width - (int)(72*1.5), _y, (int)(72*1.5), (int)(49*1.5));
			}
		}
		if(_counter >= 8 && _counter <10) {
			int i = 0;
			for(Hitbox a : TheGame._attacks) {
				if(a.getID().equals("metajumpslash1") && a.getCharacter().equals(this)){
					i = 1;
				}
			}
			if (i == 0) {
				if(_facing.equals("right")){
						_xvelocity = -9.5;
					} else {
						_xvelocity = 9.5;
					}
				_yvelocity = -10;
				_xtumbling = true;
				//_attack3 = false;
				//_canact = true;
			}
		}
		if(_counter == 10) {
				_width = (int)(31*1.5);
				_height = (int)(24*1.5);
				_y+=18;
				if(_facing.equals("right")) {
				} else {
					_x-=6;
					
				}
				TheGame.clearHitboxes("metajumpslash1", this);
			
		}
		if(_counter >= 10 && _counter < 26) {
			if(_facing.equals("right")){
			TheGame._gc.drawImage(new Image("metaknight/attack1/down9.png"), _x, _y, (int)(31*1.5), (int)(36*1.5));
			} else {
				TheGame._gc.drawImage(new Image("metaknight/attack1/down9left.png"), _x +_width - (int)(31*1.5), _y, (int)(31*1.5), (int)(36*1.5));
			}
		}
		if(_counter == 25) {
			_attack3 = false;
			_canact = true;
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150){
		_attacku = true;
		_canact = false;
		_ultcharge = 0;
		_counter = 0;
		_xvelocity = 0;
		_immune = true;
		_width = (int)(40*1.5);
		_height = (int)(23*1.5);
		if(_facing.equals("right")) {
			_image = new Image("metaknight/ult1.png");
		} else {
			_image = new Image("metaknight/ult1left.png");
		}
		}
		
	}
	
	public void executeAttackU() {
		if(_counter == 10) {
			_width = (int)(51*1.5);
			_height = (int)(24*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/ult2.png");
				_x-=16;
			} else {
				_image = new Image("metaknight/ult2left.png");
			}
		}
		if(_counter == 12) {
			_width = (int)(61*1.5);
			_height = (int)(26*1.5);
			int x;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("metaknight/ult3.png");
				_x+=16;
				x = 5;
				v = 20;
			} else {
				_image = new Image("metaknight/ult3left.png");
				_x-=21;
				x = 900 - (int)(66*1.5);
				v = -20;
			}
			Hitbox attack = (new HitboxImpl("metatornado", this, false,x, 0, (int)(64*1.5), (int)(528*1.5), v, 0, 10, 9, new Image("metaknight/tornado.png")));
			attack.setDissappearOnHit(false);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/metaknight/sounds/ult.wav");
		}
		if(_counter == 14) {
			_width = (int)(62*1.5);
			_height = (int)(26*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/ult4.png");
			} else {
				_image = new Image("metaknight/ult4left.png");
				_x-=1;
			}
		}
		if(_counter == 16) {
			_width = (int)(61*1.5);
			_height = (int)(26*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/ult5.png");
			} else {
				_image = new Image("metaknight/ult5left.png");
				_x-=1;
			}
		}
		if(_counter == 65) { 
			_canact = true;
			_attacku = false;
			_immune = false;
		}
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}
	
	@Override
	public void pressJump() {
		if(!_fly) {
			super.pressJump();
		} else if (_flycounter > 9) {
			jump2();
			if(_facing.equals("right")) {
				_x+=9;
			} else {
				_x-=9;
			}
		}
	}
	
	@Override
	public void jump2() {
		if(_jumpcounter <= 3) {
			_fly = true;
			_flycounter = 0;
			_jumpcounter++;
			//_canact = false;
		}
	}
	
	public void executeFly() {
	    if(_attack1 || _attack2 || _attack3 || _attacku) {
	    	_fly = false;
	    }
		if(_flycounter == 0) {
			_width = (int)(44*1.5);
			_height = (int)(49*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/jump1.png");
				_x-=18;
			} else {
				_image = new Image("metaknight/jump1left.png");
				_x+=18;
			}
			_y-=(int)(17*1.5);
		}
		if(_flycounter == 2) {
			_width = (int)(55*1.5);
			_height = (int)(42*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/jump2.png");
				_x-=9;
			} else {
				_image = new Image("metaknight/jump2left.png");
				_x-=7;
			}
			_y+=(int)(7*1.5);
			TheGame.playSound("/metaknight/sounds/flap.wav");
		}
		if(_flycounter == 4) {
			_width = (int)(50*1.5);
			_height = (int)(44*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/jump3.png");
				_x+=7;
			} else {
				_image = new Image("metaknight/jump3left.png");
				
			}
			_y-=3;
		}
		if(_flycounter == 6) {
			_width = (int)(63*1.5);
			_height = (int)(43*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/jump4.png");
				_x-=9;
			} else {
				_image = new Image("metaknight/jump4left.png");
				_x-=9;
			}
			_yvelocity = -13;
		}
		if(_flycounter == 8) {
			_width = (int)(49*1.5);
			_height = (int)(43*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/jump5.png");
				_x+=10;
			} else {
				_image = new Image("metaknight/jump5left.png");
				_x+=10;
			}
		}
		if(_flycounter == 10) {
			_width = (int)(39*1.5);
			_height = (int)(42*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/jump6.png");
				_x+=9;
			} else {
				_image = new Image("metaknight/jump6left.png");
				_x+=6;
			}
		}
		if(_flycounter == 15) {
			if(_facing.equals("right")) {
				_x+=9;
			} else {
				_x-=9;
			}
			_fly = false;
			_canact = true;
			
		}
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		_flycounter++;
	}
	
	@Override
	public void pressRight() {
		if(!_fly) {
			super.pressRight();
		} else {
			_xvelocity = 5.5*_speedfactor;
			_facing = "right";
		}
	}
	@Override
	public void pressLeft() {
		if(!_fly) {
			super.pressLeft();
		} else {
			_xvelocity = -5.5*_speedfactor;
			_facing = "left";
			}
	}
	
	@Override
	public void doIntro() {
		if(_counter == 0){
			_width = (int)(39*1.5);
			_height = (int)(41*1.5);
				_image = new Image("metaknight/intro/intro1.png");
		}
		if(_counter == 7) {
				_image = new Image("metaknight/intro/intro2.png");
				TheGame.playSound("/metaknight/sounds/flap.wav");
		}
		if(_counter == 9) {
				_image = new Image("metaknight/intro/intro3.png");
		}
		if(_counter == 11) {
				_image = new Image("metaknight/intro/intro4.png");
		}
		if(_counter == 13){
			if(_facing.equals("right")) {
				_image = new Image("metaknight/intro/intro5.png");
			} else {
				_image = new Image("metaknight/intro/intro5left.png");
			}
		}
		if(_counter == 15){
			if(_facing.equals("right")) {
				_image = new Image("metaknight/intro/intro6.png");
			} else {
				_image = new Image("metaknight/intro/intro6left.png");
			}
		}
		if(_counter == 17){
			if(_facing.equals("right")) {
				_image = new Image("metaknight/intro/intro7.png");
			} else {
				_image = new Image("metaknight/intro/intro7left.png");
			}
		}
		if(_counter == 19) {
			_width = (int)(42*1.5);
			_height = (int)(32*1.5);
			if(_facing.equals("right")) {
				_image = new Image("metaknight/meta.png");
			} else {
				_image = new Image("metaknight/metaleft.png");
			}
			_y+=14;
		}
	}
	
}