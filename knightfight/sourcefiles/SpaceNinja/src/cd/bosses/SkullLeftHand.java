package cd.bosses;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SkullLeftHand extends Boss {

	private Hitbox _body = new CharLinkedHitbox("skullbody2", this, 0, 1);
	
	public SkullLeftHand() {
		super(900, 600, "lefthand");
		_width = 130;
		_height = (int)(140*1.3);
		_staticimage = new Image("skullboss/lfist.png");
		_body = new CharLinkedHitbox("skullbody2", this, 0, 1);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		
		if(_spawning) {
			executeSpawn();
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_dead){
			if(_counter1 < 29 && !_spawning) {
				_yvelocity = 0;
				if(_counter % 3 == 0){
				if(_counter1 < 15) {
					_y -=1;
				} else {
					_y += 1;
				}
				}
			} else {
				_counter1 = 0;
			}
		}
		
	}

	@Override
	public void spawn() {
		_x = -200;
		_y = 110;
		// resting x 125
		_spawning = true;
		_counter2 = 0;
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 10) {
			_xvelocity = 5;
		}
		if(_counter2 == 75) {
			_xvelocity = 0;
			_spawning  = false;
		}
	}
	
	public void attack1() {
		_counter4 = 0;
		_xvelocity = -8;
		_attack1 = true;
	}
	
	public void executeAttack1() {
		if(_counter4 == 15) {
			_xvelocity = 0;
		}
		if(_counter4 == 20) {
			_yvelocity = 20;
		}
		if(_counter4 == 28) {
			_yvelocity = 0;
		}
		if(_counter4 == 35) {
			_xvelocity = 3;
		}
		if(_counter4 == 45) {
			_xvelocity = 20;
		}
		if(_counter4 == 59) {
			_xvelocity = 0;
		}
		if(_counter4 == 95) {
			_xvelocity = -20;
		}
		if(_counter4 >= 95 && _x <= 125) {
			_x = 125;
			_xvelocity = 0;
			_yvelocity = -10;
		}
		if(_counter4 >= 95 && _y <= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack1 = false;
		}
	}
	
	public void attack2() {
		_counter4 = 0;
		_attack2 = true;
		_xvelocity = 24;
	}
	
	public void executeAttack2() {
		if(_counter4 == 15) {
			_xvelocity = 0;
		}
		if(_counter4 == 85) {
			_xvelocity = -5;
		}
		if(_counter4 == 90) {
			_yvelocity = 8;
		}
		if(_counter4 == 110) {
			_yvelocity = -8;
		}
		if(_counter4 == 130) {
			_yvelocity = 8;
		}
		if(_counter4 == 150) {
			_yvelocity = -8;
		}
		if(_counter4 == 170) {
			_yvelocity = 8;
		}
		if(_counter4 == 190) {
			_yvelocity = -8;
		}
		if(_counter4 == 210) {
			_yvelocity = 8;
		}
		if(_counter4 == 260) {
			_xvelocity = 0;
			_y = -200;
			_x = 125;
			_yvelocity = 9;
		}
		if(_counter4 >= 260 && _y >= 110) {
			_y = 110;
			_counter3 = 0;
			_attack2 = false;
		}
	}

}
