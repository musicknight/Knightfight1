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

public class PhantomChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private int _dashcharges = 3;
	private boolean _canattack3;
	private int _ultx;
	
	public PhantomChar(String ID) {
		super(ID);
		_width = (int)(35*1.5);
		_height = (int)(37*1.5);
		_speedfactor = 1.2;
		_damagefactor = 1.2;
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku && !_intro){
			_width = (int)(35*1.5);
			_height = (int)(37*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/phantom.png");
			} else {
				_image = new Image("phantom/phantomleft.png");
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
		if(_onplatform) {
			_dashcharges = 3;
			_canattack3 = true;
		}
		
	}
	
	@Override
	public Image getStockImage() {
		return new Image("phantom/stock.png");
	}

	@Override
	public void attack1() {
		_attack1 = true;
		_canact = false;
		_counter = 6;
		_xvelocity = 0;
		_width = (int)(36*1.5);
		_height = (int)(30*1.5);
		_y+=9;
		if(_facing.equals("right")) {
			_image = new Image("phantom/attack1/throw1.png");
		} else {
			_image = new Image("phantom/attack1/throw1left.png");
		}
		
	}
	
	public void executeAttack1() {
		if(_xtumbling) {
			_canact = true;
			_attack1 = false;
		}
		if(_counter == 2) {
			_width = (int)(36*1.5);
			_height = (int)(34*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw2.png");
			} else {
				_image = new Image("phantom/attack1/throw2left.png");
			}
		}
		if(_counter == 4) {
			_width = (int)(35*1.5);
			_height = (int)(37*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw3.png");
			} else {
				_image = new Image("phantom/attack1/throw3left.png");
			}
		}
		if(_counter == 6) {
			_width = (int)(31*1.5);
			_height = (int)(38*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw4.png");
			} else {
				_image = new Image("phantom/attack1/throw4left.png");
				_x+=6;
			}
			TheGame.playSound("/phantom/sounds/throw1.wav");
		}
		if(_counter == 8) {
			_width = (int)(42*1.5);
			_height = (int)(45*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw5.png");
				_x-=16;
			} else {
				_image = new Image("phantom/attack1/throw5left.png");
			}
		}
		if(_counter == 10) {
			_width = (int)(45*1.5);
			_height = (int)(45*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw6.png");
				_x-=4;
			} else {
				_image = new Image("phantom/attack1/throw6left.png");
			}
		}
		if(_counter == 12) {
			_width = (int)(42*1.5);
			_height = (int)(45*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw7.png");
				_x+=4;
			} else {
				_image = new Image("phantom/attack1/throw7left.png");
			}
		}
		if(_counter == 14) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw8.png");
				_x+=4;
			} else {
				_image = new Image("phantom/attack1/throw8left.png");
			}
		}
		if(_counter == 16) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw9.png");
			} else {
				_image = new Image("phantom/attack1/throw9left.png");
			}
		}
		if(_counter == 26) {
			_width = (int)(51*1.5);
			_height = (int)(37*1.5);
			_y+=12;
			int i;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw10.png");
				_x+=10;
				i = (int)(40*1.5);
				v = 23;
			} else {
				_image = new Image("phantom/attack1/throw10left.png");
				_x-=20;
				i = -(int)(33*1.5) + 16;
				v = -23;
			}
			List<Image> attacks = new ArrayList<Image>();
			attacks.add(new Image("phantom/attack1/shuriken1.png"));
			attacks.add(new Image("phantom/attack1/shuriken2.png"));
			attacks.add(new Image("phantom/attack1/shuriken3.png"));
			Hitbox attack = new AnimatedHitbox("phanstar", this, false, _x+i, _y+21, (int)(33*1.5), 24, v, 0, 15, 22, attacks, 2);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/phantom/sounds/throw2.wav");
		}
		if(_counter == 29) {
			_width = (int)(45*1.5);
			_height = (int)(37*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/attack1/throw11.png");
			} else {
				_image = new Image("phantom/attack1/throw11left.png");
				_x+=9;
			}
		}
		if(_counter == 35) {
			_attack1 = false;
			_canact = true;
			
			
		}
	}

	@Override
	public void attack2() {
		if(_dashcharges >0 ){
		_attack2 = true;
		_counter = 0;
		_xvelocity = 0;
		_canact = false;
		_width = (int)(45*1.5);
		_height = (int)(33*1.5);
		if(_facing.equals("right")) {
			_image = new Image("phantom/dash1.png");
			_x-=15;
			_xvelocity = 20;
		} else {
			_image = new Image("phantom/dash1left.png");
			_xvelocity = -20;
		}
		_yvelocity = -9;
		_xtumbling = true;
		TheGame.playSound("/phantom/sounds/slash.wav");
		}
		
	}
	
	public void executeAttack2() {
		if(_counter == 2) {
			_width = (int)(44*1.5);
			_height = (int)(31*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/dash2.png");
			} else {
				_image = new Image("phantom/dash2left.png");
			}
		}
		if(_counter == 4) {
			_width = (int)(68*1.5);
			_height = (int)(40*1.5);
			int i;
			if(_facing.equals("right")) {
				_image = new Image("phantom/dash3.png");
				i = (int)(25*1.5);
			} else {
				_image = new Image("phantom/dash3left.png");
				_x-=32;
				i = 0;
			}
			Hitbox attack = new OffsetHitbox("phandash", this, i, 0,(int)(43*1.5), 60, 20, 18, _clear);
			if(_facing.equals("right")) {
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			
			_dashcharges--;
		}
		if(_counter == 6) {
			_width = (int)(58*1.5);
			_height = (int)(44*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/dash4.png");
			} else {
				_image = new Image("phantom/dash4left.png");
				_x+=15;
			}
		}
		if(_counter == 8) {
			_width = (int)(45*1.5);
			_height = (int)(50*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/dash5.png");
			} else {
				_image = new Image("phantom/dash5left.png");
				_x+=19;
			}
		}
		if(_counter == 10) {
			_width = (int)(41*1.5);
			_height = (int)(50*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/dash5.png");
				_x+=6;
			} else {
				_image = new Image("phantom/dash5left.png");
			}
			TheGame.clearHitboxes("phandash", this);
		}
		if(_counter == 18) {
			_attack2 = false;
			_canact = true;
		}
	}

	@Override
	public void attack3() {
		if(_canattack3){
		_attack3 = true;
		_counter = 0;
		_canact = false;
		_xvelocity = 0;
		_width = (int)(25*1.5);
		_height = (int)(48*1.5);
		_image = new Image("phantom/jump1.png");
		_yvelocity = -15;
		TheGame.playSound("/phantom/sounds/jump1.wav");
		}
		
		
	}
	
	public void executeAttack3() {
		if(_counter == 3) {
			_width = (int)(39*1.5);
			_height = (int)(49*1.5);
			_image = new Image("phantom/jump2.png");
			_x-=10;
			_canattack3 = false;
			Hitbox attack1 = new HitboxImpl("kunai2", this, false,_x+15, _y+17, (int)(9*1.5), (int)(17*1.5), 0, 15, 10, 10, new Image("phantom/kunai2.png"));
			Hitbox attack2 = new HitboxImpl("kunai1", this, false,_x+15, _y+17, (int)(13*1.5), (int)(13*1.5), -15, 15, 10, 10, new Image("phantom/kunai1.png"));
			Hitbox attack3 = new HitboxImpl("kunai1", this, false,_x+15, _y+17, (int)(13*1.5), (int)(13*1.5), 15, 15, 10, 10, new Image("phantom/kunai3.png"));
			TheGame._attacks.add(attack1);
			TheGame._attacks.add(attack2);
			TheGame._attacks.add(attack3);
			TheGame.playSound("/phantom/sounds/jump2.wav");
		}
		
		if(_counter == 6){
			_width = (int)(51*1.5);
			_height = (int)(45*1.5);
			_image = new Image("phantom/jump3.png");
			_x-=9;
		}
		if(_counter == 15) {
			_attack3 = false;
			_canact = true;
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150) {
		_attacku = true;
		_immune = true;
		_counter = 0;
		_canact = false;
		_ultcharge = 0;
		_canstore = false;
		_storedxv = 0;
		_xvelocity = 0;
		_width = (int)(32*1.5);
		_height = (int)(39*1.5);
		_image = new Image("phantom/ult/ult1.png");
		_dontmove = true;
		}
	}
	
	public void executeAttackU() {
		_immune = true;
		if(_counter == 2) {
			_width = (int)(30*1.5);
			_height = (int)(39*1.5);
			_image = new Image("phantom/ult/ult2.png");
		}
		if(_counter == 4) {
			_width = (int)(29*1.5);
			_height = (int)(40*1.5);
			_image = new Image("phantom/ult/ult3.png");
		}
		if(_counter == 6) {
			_width = (int)(40*1.5);
			_height = (int)(62*1.5);
			_image = new Image("phantom/ult/ult4.png");
			if(_facing.equals("left")) {
				_x-=16;
			}
			TheGame.playSound("/phantom/sounds/slash.wav");
		}
		if(_counter == 8) {
			_image = new Image("phantom/ult/ult5.png");
		}
		if(_counter == 10) {
			_width = (int)(25*1.5);
			_height = (int)(63*1.5);
			_image = new Image("phantom/ult/ult6.png");
			if(_facing.equals("left")) {
				_x+=22;
			}
		}
		if(_counter == 12) {
			_width = (int)(37*1.5);
			_height = (int)(63*1.5);
			_image = new Image("phantom/ult/ult7.png");
			if(_facing.equals("left")) {
				_x-=18;
			}
		}
		if(_counter == 18) {
			_yvelocity = -35;
			TheGame.playSound("/phantom/sounds/ult2.wav");
		}
		if(_counter == 60) {
			_yvelocity = 0;
			_gravity = false;
			_width = (int)(36*1.5);
			_height = (int)(41*1.5);
			_image = new Image("phantom/ult/ult8.png");
			_x = 450 - ((int)(36*1.5)/2);
			_y = 0;
		}
		if(_counter >= 60 && _counter % 2 == 0 && _counter < 250) {
			Hitbox attack;
			
			_ultx+=_xvelocity;
			if(_ultx == 0){
			 attack = new HitboxImpl("kunai2", this, false,_x+15, _y+17, (int)(9*1.5), (int)(17*1.5), 0, 20, 10, 10, new Image("phantom/kunai2.png"));
			} else if (_ultx > 0) {
				attack = new HitboxImpl("kunai1", this, false,_x+15, _y+17, (int)(13*1.5), (int)(13*1.5), _ultx , 20, 10, 10, new Image("phantom/kunai3.png"));
			} else {
			 attack = new HitboxImpl("kunai1", this, false,_x+15, _y+17, (int)(13*1.5), (int)(13*1.5), _ultx, 20, 10, 10, new Image("phantom/kunai1.png"));
			}
			TheGame._attacks.add(attack);
			
		}
		if(_counter >= 60 && _counter % 5 == 0 && _counter < 250) {
			TheGame.playSound("/phantom/sounds/ultthrow.wav");
		}
		if(_counter == 270) {
			_gravity = true;
			_immune = false;
			_canact = true;
			_dontmove = false;
			_attacku = false;
			_canstore =true;
		}
		
	}

	@Override
	public Color getColor() {
		return Color.GRAY;
	}
	@Override
	public void pressRight() {
		if(!_attacku) {
			super.pressRight();
		} else if(_counter >=60) {
			_xvelocity=2.5;
		}
	}
	@Override
	public void pressLeft() {
		if(!_attacku) {
			super.pressLeft();
		} else if(_counter >=60) {
			_xvelocity=-2.5;
		}
	}
	
	@Override
	public void doIntro() {
		if(_counter == 0) {
			_width = (int)(48*1.5);
			_height = (int)(55*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro1.gif");
			} else {
				_image = new Image("phantom/intro/intro1left.gif");
			}
		}
		if(_counter == 3) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro2.gif");
			} else {
				_image = new Image("phantom/intro/intro2left.gif");
			}
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro3.gif");
			} else {
				_image = new Image("phantom/intro/intro3left.gif");
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro4.gif");
			} else {
				_image = new Image("phantom/intro/intro4left.gif");
			}
			TheGame.playSound("/phantom/sounds/slash.wav");
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro5.gif");
			} else {
				_image = new Image("phantom/intro/intro5left.gif");
			}
		}
		if(_counter == 15) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro6.gif");
			} else {
				_image = new Image("phantom/intro/intro6left.gif");
			}
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image("phantom/intro/intro7.gif");
			} else {
				_image = new Image("phantom/intro/intro7left.gif");
			}
		}
		if(_counter == 48) {
			_width = (int)(35*1.5);
			_height = (int)(37*1.5);
			if(_facing.equals("right")) {
				_image = new Image("phantom/phantom.png");
			} else {
				_image = new Image("phantom/phantomleft.png");
			}
			_y+=27;
		}
	}

}
