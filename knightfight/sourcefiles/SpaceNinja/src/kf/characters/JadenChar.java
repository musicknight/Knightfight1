package kf.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.CharLinkedHitbox;
import kf.ExplodingHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.MeleeHitbox;
import kf.TheGame;

public class JadenChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private int _cd1;
	private ExplodingHitbox _fireball = new ExplodingHitbox("expfireball", this, false, 0, 0, 0, 0, 0, 0, 0, 0, new Image("gengar/gunk1.png"), new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25));
	private int _fireballcounter;
	private boolean _fireballex = false;
	private double _holder;
	//how many times attack3 has been used without touching ground
	private int _canattack3;
	//checks to see whether flamedive has hit the ground;
	private boolean _haslanded = false;
	
	public JadenChar(String ID) {
		super(ID);
		_width = 42;
		_height = 54;
		if(_facing.equals("right")) {
			_image = new Image("jaden/jaden.gif");
		} else {
			_image = new Image("jaden/jadenleft.gif");
		}
		_damagefactor = 1.2;
		_speedfactor = 1.1;
				
	}
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku && !_intro) {
			_width = 42;
			_height = 54;
			if(_facing.equals("right")) {
			_image = new Image("jaden/jaden.gif");
			} else {
				_image = new Image("jaden/jadenleft.gif");
			}
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2 ) {
			executeAttack2();
			_width = 36;
			_height = 45;
			_image = new Image("jaden/flamedive.png");
			_attack3 = false;
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attacku) {
			executeAttackU();
		}
		if(!_attack2) {
			_gravity = true;
		}
		if(_fireball.isGone() && _fireballex) {
			_fireball.explode();
			_fireballcounter = 0;
			_fireballex = false;
			TheGame.playSound("/jaden/sounds/jaden3.wav");
		}
		if(_fireballcounter == 30) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("explosion") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
		}
		if(_onplatform) {
			_canattack3 = 0;
		}
	}

	@Override
	public Image getStockImage() {
		return new Image("jaden/stock.gif");
	}

	@Override
	public void attack1() {
		if(!_attack3 && _cd1 == 0){
		_counter = 0;
		_cd1 = 40;
		_xvelocity = 0;
		_gravity = true;
		_canact = false;
		_attack1 = true;
		_width = 48;
		_height = 54;
		_holder = _damage;
		if(_facing.equals("right")) {
			_image = new Image("jaden/spin1.gif");
		} else {
			_image = new Image("jaden/spin1left.gif");
		}
		TheGame.playSound("/jaden/sounds/jaden1.wav");
	}
	}
	
	public void executeAttack1() {
		if(_holder != _damage) {
			_y -= 6;
			_canact = true;
			_attack1 = false;
		}
		if(_counter == 2) {
			_width = 45;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin2.gif");
			} else {
				_image = new Image("jaden/spin2left.gif");
			}
		}
		if(_counter == 4) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin3.gif");
			} else {
				_image = new Image("jaden/spin3left.gif");
			}
		}
		if(_counter == 6) {
			_height = 51;
			_width = 39;
			
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin4.gif");
			} else {
				_image = new Image("jaden/spin4left.gif");
			}
		}
		if(_counter == 8) {
			_y-=3;
			_width = 36;
			_height = 54;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin5.gif");
			} else {
				_image = new Image("jaden/spin5left.gif");
			}
		}
		if(_counter == 10) {
			_y-= 6;
			_width = 45;
			_height = 60;
			
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin6.gif");
			} else {
				_image = new Image("jaden/spin6left.gif");
			}
		}
		if(_counter == 12) {
			_width = 54;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin7.gif");
			} else {
				_image = new Image("jaden/spin7left.gif");
			}
		}
		if(_counter == 14) {
			_width = 51;
			_height = 54;
			_y+= 5;
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin8.gif");
				_xvelocity = -6;
				_xtumbling = true;
				d = 51;
				v = 15;
			} else {
				_image = new Image("jaden/spin8left.gif");
				_xvelocity = 6;
				_xtumbling = true;
				d = -32;
				v = -15;
			}
			MeleeHitbox exp = new MeleeHitbox("explosion", this, 0, 0,  77, 80, 25, 20, new Image("jaden/fire2.gif"));
			_fireball = new ExplodingHitbox("expfireball", this, false, _x+d, _y+15, 32, 32, v, 0, 0, 0, new Image("jaden/fire1.png"), exp);
			TheGame._attacks.add(_fireball);
			_fireballex = true;
			TheGame.playSound("/jaden/sounds/jaden2.wav");
		}
		if(_counter == 16) {
			_width = 60;
			_height = 51;
			_y+= 2;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin9.gif");
			} else {
				_image = new Image("jaden/spin9left.gif");
			}
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 20) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
		}
		if(_counter == 22) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 26) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 28) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 32) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 34) {
			_attack1 = false;
			_canact = true;
			_y-=4;
		}
	}

	@Override
	public void attack2() {
		if(!_onplatform){
		_canact = false;
		_xvelocity = 0;
		_haslanded = false;
		_gravity = false;
		_width = 36;
		_height = 45;
		_image = new Image("jaden/flamedive.png");
		_yvelocity = 20;
		_attack2 = true;
		TheGame._attacks.add(new CharLinkedHitbox("flamedive", this, 22, 14));
		}
		TheGame.playSound("/jaden/sounds/jaden4.wav");
	}
	public void executeAttack2() {
		if(_onplatform && !_haslanded) {
			_counter = 0;
			_gravity = true;
			_haslanded = true;
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("flamedive") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
		}
		if(_counter == 20) {
			_y -=9;
			_attack2 = false;
			_canact = true;
		}
	}

	@Override
	public void attack3() {
		if(_canattack3 < 2 && !_attack1){
		_canact = false;
		_attack3 = true;
		_yvelocity = -13;
		_counter = 0;
		_gravity = true;
		int d;
		double v;
		Image i;
		_canattack3++;
		if(_facing.equals("right")) {
			_image = new Image("jaden/jump1.gif");
			_xvelocity = -9;
			d = 48;
			v = 10;
			i = new Image("jaden/jumpfire.png");
		} else {
			_image = new Image("jaden/jump1left.gif");
			_xvelocity = 9;
			d = 0;
			v = -10;
			i = new Image("jaden/jumpfireleft.png");
		}
		_xtumbling = true;
		_width = 48;
		_height = 51;
		TheGame._attacks.add(new HitboxImpl("jumpfire", this, false, _x+d, _y+30, 40, 40, v, 10, 15, 15, i));
		TheGame.playSound("/jaden/sounds/jaden2.wav");
		}
	
	}
	
	
	public void executeAttack3() {
		if(_counter == 3) {
			_width = 48;
			_height = 51;
			if(_facing.equals("right")) {
				_image = new Image("jaden/jump1.gif");
			} else {
				_image = new Image("jaden/jump1left.gif");
			}
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/jump2.gif");
			} else {
				_image = new Image("jaden/jump2left.gif");
			}
		}
		if(_counter == 9) {
			_height = 45;
			if(_facing.equals("right")) {
				_image = new Image("jaden/jump3.gif");
			} else {
				_image = new Image("jaden/jump3left.gif");
			}
		}
		if(_counter == 12) {
			_width = 60;
			_height = 42;
			if(_facing.equals("right")) {
				_image = new Image("jaden/jump4.gif");
			} else {
				_image = new Image("jaden/jump4left.gif");
			}
		}
		if(_counter == 15) {
			_canact = true;
			_attack3 = false;
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150){
		_canact = false;
		_attacku = true;
		_immune = true;
		_counter = 0;
		_width = 48;
		_height = 54;
		_xvelocity = 0;
		_ultcharge = 0;
		if(_facing.equals("right")) {
			_image = new Image("jaden/spin1.gif");
		} else {
			_image = new Image("jaden/spin1left.gif");
		}
		}
		TheGame.playSound("/jaden/sounds/jaden1.wav");
		
	}
	
	public void executeAttackU() {
		_canact = false;
		Random r = new Random();
		Hitbox attack = new HitboxImpl("meteor", this, false, r.nextInt(830), -109,68, 109, 0, 7, 30, 30, new Image("jaden/meteor.gif"));
		if(_counter % 8 == 0 && _counter <= 136) {
			TheGame.playSound("/jaden/sounds/jaden2.wav");
		}
		
		if(_counter == 2) {
			_width = 45;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin2.gif");
			} else {
				_image = new Image("jaden/spin2left.gif");
			}
		}
		if(_counter == 4) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin3.gif");
			} else {
				_image = new Image("jaden/spin3left.gif");
			}
		}
		if(_counter == 6) {
			_height = 51;
			_width = 39;
			
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin4.gif");
			} else {
				_image = new Image("jaden/spin4left.gif");
			}
		}
		if(_counter == 8) {
			_y-=3;
			_width = 36;
			_height = 54;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin5.gif");
			} else {
				_image = new Image("jaden/spin5left.gif");
			}
		}
		if(_counter == 10) {
			_y-= 6;
			_width = 45;
			_height = 60;
			
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin6.gif");
			} else {
				_image = new Image("jaden/spin6left.gif");
			}
		}
		if(_counter == 12) {
			_width = 54;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin7.gif");
			} else {
				_image = new Image("jaden/spin7left.gif");
			}
		}
		if(_counter == 14) {
			_width = 51;
			_height = 54;
			_y+= 5;
			TheGame._attacks.add(attack);
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin8.gif");
				
			} else {
				_image = new Image("jaden/spin8left.gif");
				_xvelocity = 6;
				_xtumbling = true;
				
			}
			
		}
		if(_counter == 16) {
			_width = 60;
			_height = 51;
			_y+= 2;
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin9.gif");
			} else {
				_image = new Image("jaden/spin9left.gif");
			}
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 20) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
		}
		if(_counter == 22) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 24) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 26) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 28) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
		}
		if(_counter == 30) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 32) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 34) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 36) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
		}
		if(_counter == 38) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 40) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 42) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 44) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 46) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 48) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 50) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 52) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 54) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 56) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 58) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 60) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 62) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 64) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 66) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 68) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 70) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 72) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 74) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 76) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 78) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 80) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 82) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 84) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 86) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 88) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 90) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 92) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 94) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 96) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 98) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 100) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 102) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 104) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 106) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 108) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 110) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 112) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 114) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
		}
		if(_counter == 116) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 118) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
		}
		if(_counter == 120) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
		}
		if(_counter == 122) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 124) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 126) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 128) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 130) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin10.gif");
			} else {
				_image = new Image("jaden/spin10left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 132) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin11.gif");
			} else {
				_image = new Image("jaden/spin11left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 134) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin12.gif");
			} else {
				_image = new Image("jaden/spin12left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 136) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/spin13.gif");
			} else {
				_image = new Image("jaden/spin13left.gif");
			}
			TheGame._attacks.add(attack);
		}
		if(_counter == 220) {
			_canact = true;
			_y-=3;
			_immune = false;
			_attacku = false;
		}
	}

	@Override
	public Color getColor() {
		return Color.DARKORANGE;
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		_fireballcounter++;
		if(_cd1 > 0) {
			_cd1--;
		}
	}
	
	@Override
	public void respawn() {
		_attack2 = false;
		_attack1 = false;
		_attack3 = false;
		super.respawn();
		_gravity = true;
	}
	
	public void doIntro() {
		if(_counter == 0) {
			_width = (int)(39*1.5);
			if(_facing.equals("right")) {
				_image = new Image("jaden/intro1.gif");
			} else {
				_image = new Image("jaden/intro1left.gif");
			}
		}
		if(_counter == 3) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/intro2.gif");
			} else {
				_image = new Image("jaden/intro2left.gif");
			}
			TheGame.playSound("/jaden/sounds/jaden2.wav");
			
		}
		if(_counter == 6) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/intro3.gif");
			} else {
				_image = new Image("jaden/intro3left.gif");
			}
		}
		if(_counter == 9) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/intro4.gif");
			} else {
				_image = new Image("jaden/intro4left.gif");
			}
		}
		if(_counter == 12) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/intro5.gif");
			} else {
				_image = new Image("jaden/intro5left.gif");
			}
		}
		if(_counter == 15) {
			if(_facing.equals("right")) {
				_image = new Image("jaden/intro6.gif");
			} else {
				_image = new Image("jaden/intro6left.gif");
			}
		}
	}

}
