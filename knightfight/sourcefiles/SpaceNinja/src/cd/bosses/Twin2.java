package cd.bosses;

import java.util.ArrayList;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Twin2 extends Boss {

	private Hitbox _body = new CharLinkedHitbox("twin1bod", this, 0, 1);
	private int _attack2var;
	private int _attack3var;
	private boolean _changeform;
	
	public Twin2() {
		super(10, 328, "twin2");
		_width = (int)(86 * 0.6);
		_height = (int)(192*0.6);
		_health = 1200;
		_sprites = new ArrayList<Image>();
		_sprites.add(new Image("twinsboss/b1.png"));
		_sprites.add(new Image("twinsboss/b2.png"));
		_body= new CharLinkedHitbox("twin1bod", this, 0, 1);
		_rate = 5;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_changeform) {
			exeChangeForm();
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		
	}
	public void attack1() {
		_attack1 = true;
		_counter4 = 0;
		_staticimage = new Image("twinsboss/b3.png");
	}
	public void executeAttack1() {
		if(_counter4 >= 10 && _counter4 % 70 == 0) {
			Hitbox a = new HitboxImpl("ball", this, false, _x +_width, _y, 150, 150, 5, 0, 0, 1, new Image("twinsboss/shots/b1.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 300) {
			_attack1 = false;
			_staticimage = null;
		}
	}
	
	public void attack2(int var) {
		_counter4 = -15;
		_attack2 = true;
		if(var == 1){
		_staticimage = new Image("twinsboss/b3.png");
		}
		_attack2var = var;
	}
	
	public void executeAttack2() {
		if(_attack2var == 0){
		if(_counter4 == 60) {
			_yvelocity = -20;
			System.out.println("here");
		}
		if(_counter4 == 55) {
			_staticimage = new Image("twinsboss/b3.png");
		}
		if(_counter4 == 65) {
			_yvelocity = 0;
		}
		if(_counter4 == 135) {
			_yvelocity = 20;
		}
		if(_counter4 == 140) {
			_yvelocity = 0;
		}
		if(_counter4 == 250) {
			_attack2 = false;
			_staticimage = null;
		}
		} else {
			if(_counter4 == 25) {
				_yvelocity = -20;
			}
			if(_counter4 == 30) {
				_yvelocity = 0;
			}
			if(_counter4 == 100) {
				_yvelocity = 20;
			}
			if(_counter4 == 105) {
				_yvelocity = 0;
			}
			if(_counter4 == 235) {
				_yvelocity = -20;
			}
			if(_counter4 == 240) {
				_yvelocity = 0;
			}
			if(_counter4 == 245) {
				_yvelocity = 20;
			}
			if(_counter4 == 250) {
				_yvelocity = 0;
				_attack2 = false;
				_staticimage = null;
			}
		}
	}
	public void attack3(int var) {
		_attack3var = var;
		_counter4 = 0;
		_attack3 = true;
		if(_attack3var == 1) {
			_yvelocity = -20;
		}
	}
	
	public void executeAttack3() {
		if(_counter4 == 10) {
			_staticimage = new Image("twinsboss/b3.png");
			if(_attack3var == 1) {
				_yvelocity = 0;
			}
		}
		if(_counter4 == 90 && _attack3var == 1) {
			_yvelocity = 20;
		}
		if(_counter4 == 101) {
			_yvelocity = 0;
			_attack3 = false;
			_staticimage = null;
		}
	}
	
	public void changeForm() {
		_changeform = true;
		_counter4 = 0;
		_yvelocity = -20;
	}
	
	public void exeChangeForm() {
		
	}
	
	public void switchSprites() {
		_sprites.clear();
		_sprites.add(new Image("twinsboss/bwon1.png"));
		_sprites.add(new Image("twinsboss/bwon2.png"));
	}

}
