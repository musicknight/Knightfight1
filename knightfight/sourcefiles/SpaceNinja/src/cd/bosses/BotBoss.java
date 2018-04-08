package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.AnimatedHitbox;
import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.MeleeHitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BotBoss extends Boss {

	private int _repeat4;
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _rage1count;
	private int _rage2count;
	private boolean _begin3;
	private boolean _lasershot;
	private Hitbox _body = new CharLinkedHitbox("botbody", this, 0, 1);
	private boolean _spriteswitched;
	private int _i;
	private int _j;
	private int _k;
	private int _l;
	private int _m;
	private int _3phase;
	private int _n;
	private boolean _enraged;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	public BotBoss() {
		super(900, 600, "botboss");
		_health = 2000;
		_width = 210;
		_height = 180;
		_body = new CharLinkedHitbox("botbody", this, 0, 1);
		_staticimage = new Image("botboss/1.png");
		// TODO Auto-generated constructor stub
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_counter2 == 0 && !_dead && !_won && !_spawning) {
			
			Random r = new Random();
			int i = r.nextInt(5) + 2;
			TheGame.setText(new Image("botboss/text/" + i + ".png"));
		}
		
	if(_counter2 == 150) {
		TheGame.stopText();
		_counter2 = -500;
	}
		if(_health == 0 && !_dead) {
			_dead = true;
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_attack4 = false;
			_counter2 = 0;
			TheGame.setText(new Image("botboss/text/dead.png"));
			_staticimage = new Image("botboss/1.png");
		}
		if(_won) {
			_staticimage = new Image("botboss/won.png");
			TheGame.setText(new Image("botboss/text/won.png"));
		}
		if(_dead) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_attack4 = false;
			if(_counter2 == 3) {
				_staticimage = new Image("botboss/dead.png");
			}
			if(_counter2 == 13) {
				_staticimage = new Image("botboss/1.png");	
			}
			if(_counter2 == 16) {
				_staticimage = new Image("botboss/dead.png");
			}
			if(_counter2 == 36) {
				_yvelocity = 30;
				TheGame.playSound("/botboss/sounds/move.wav");
			}
		}
		if(_enraged && !_spriteswitched) {
			TheGame.setText(new Image("botboss/text/mad.png"));
			_staticimage = new Image("botboss/mad.png");
			_spriteswitched = true;
			_counter2 = 1;
		}
		if(_spawning) {
			executeSpawn();
		}
		if(_attack1){
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attack4) {
			executeAttack4();
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
			if(TheGame._character1.getLives() <= 0) {
				_won = true;
				//_staticimage = new Image("ghostboss/won.png");
			}
			if(!_enraged && _health < 600 ) {
				_enraged = true;
			}
			if(_counter3 == 40 && !_won) {
				Random r = new Random();
				int i = r.nextInt(4);
				_counter3 = 0;
				
				if(_repeat1 == 2) {
					i = 3;
				}
				if(_repeat2 == 2) {
					i = 2;
				}
				if(_repeat3 == 1) {
					i = 0;
				}
				if(_repeat4 == 2) {
					i = 1;
				}
				if(_repeatno1 == 5) {
					i = 0;
				}
				if(_repeatno2 == 5) {
					i = 1;
				}
				if(_repeatno3 == 5) {
					i = 2;
				}
				if(_repeatno4 == 5) {
					i = 3;
				}
				if(i == 0) {
					attack1();
					_repeat1++;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeat4 = 0;
					_repeatno1 = 0;
					_repeatno2++;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 1) {
					attack2();
					_repeat2++;
					_repeat1 = 0;
					_repeat3 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2=0;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 2) {
					int p = r.nextInt(4);
					_m = 435;
					if(p == 0) {
						_i = 20;
						_j = 400;
						_k = 200;
						_l = 300;
						_n = 315;
						
					}
					if(p == 1) {
						_i = 300;
						_j = 200;
						_k = 20;
						_l = 330;
						_n = 400;
						
					}
					if(p == 2) {
						_i = 400;
						_j = 200;
						_k = 100;
						_l = 30;
						_n = 400;
						
					}
					if(p == 3) {
						_i = 200;
						_j = 20;
						_k = 330;
						_l = 300;
						_n = 220;
						
					}
					attack3();
					_repeat3++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3=0;
					_repeatno4++;
				}
				if(i == 3) {
					attack4();
					_repeat4++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3++;
					_repeatno4=0;
				}
			}
		}
		
	}

	@Override
	public void spawn() {
		_spawning = true;
		_x = 910;
		// resting x = 630
		_y = 262;
		_counter2 = 0;
		TheGame.setText(new Image("botboss/text/" + 1 + ".png"));
		
	}
	public void executeSpawn() {
		if(_counter2 == 20){
			_xvelocity = -35;
			TheGame.playSound("/botboss/sounds/move.wav");
		}
		if(_counter2 == 28) {
			_xvelocity = 0;
			_spawning = false;	
		}
		
			
	}
	public void attack1() {
		_attack1 = true;
		_counter4 = 0;
		if(_health < 400) {
			_staticimage = new Image("botboss/madl.png");
		} else {
			_staticimage = new Image("botboss/1l.png");
		}
	}
	public void executeAttack1() {
		if(_counter4 >= 0 && _counter4 < 60) {
			TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), -150, _y+15, 900, 22);
			if(_counter4 == 1){
			TheGame.playSound("/botboss/sounds/charge2.wav");
			}
		}
		if(_counter4 >= 25 && _counter4 < 85) {
			TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, _y+65,900, 22);
			if(_counter4 == 25){
				TheGame.playSound("/botboss/sounds/charge2.wav");
				}
		}
		if(_counter4 >= 50 && _counter4 < 110) {
			TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, _y+115, 900, 22);
			if(_counter4 == 50){
				TheGame.playSound("/botboss/sounds/charge2.wav");
				}
		}
		if(_counter4 >= 75 && _counter4 < 135) {
			TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, _y+165, 900, 22);
			if(_counter4 == 75){
				TheGame.playSound("/botboss/sounds/charge2.wav");
				}
		}
		if(_counter4 == 60) {
			Hitbox a = new MeleeHitbox("laser1", this, -150, _y+15, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
		}
		if(_counter4 == 85) {
			Hitbox a = new MeleeHitbox("laser2", this, 0, _y+65, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
		}
		if(_counter4 == 110) {
			Hitbox a = new MeleeHitbox("laser3", this, 0, _y+115, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
		}
		if(_counter4 == 135) {
			Hitbox a = new MeleeHitbox("laser4", this, 0, _y+165, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
		}
		if(_counter4 == 75) {
			TheGame.clearHitboxes("laser1", this);
		}
		if(_counter4 == 100) {
			TheGame.clearHitboxes("laser2", this);
		}
		if(_counter4 == 125) {
			TheGame.clearHitboxes("laser3", this);
		}
		if(_counter4 == 150) {
			TheGame.clearHitboxes("laser4", this);
		}
		if(_counter4 >= 185 && _counter4 < 245) {
				TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), -150, _y+15, 900, 22);
				TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, _y+65,900, 22);
				TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, _y+115, 900, 22);
				TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, _y+165, 900, 22);
				if(_counter4 == 185){
					TheGame.playSound("/botboss/sounds/charge.wav");
					}
		}
		if(_counter4 == 245) {
			Hitbox a = new MeleeHitbox("laser1", this, -150, _y+15, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			a = new MeleeHitbox("laser2", this, 0, _y+65, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			a = new MeleeHitbox("laser3", this, 0, _y+115, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			 a = new MeleeHitbox("laser4", this, 0, _y+165, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
		}
		if(_counter4 == 260) {
			TheGame.clearHitboxes("laser1", this);
			TheGame.clearHitboxes("laser2", this);
			TheGame.clearHitboxes("laser3", this);
			TheGame.clearHitboxes("laser4", this);
		}
		if(_enraged && _counter4 == 270 && _rage1count < 3) {
			_counter4 =185;
			_rage1count++;
		}
		if(!_enraged || _rage1count == 3){
		if(_counter4 == 270) {
			_attack1 = false;
			_counter3 = 0;
			_rage1count = 0;
			if(_enraged) {
				_staticimage = new Image("botboss/mad.png");
			} else {
				_staticimage = new Image("botboss/1.png");
			}
		}
		}
	}
	
	public void attack2() {
		_counter4 = 0;
		_attack2 = true;
		if(!_enraged){
		_staticimage = new Image("botboss/2.png");
		} else {
			_staticimage = new Image("botboss/mad2.png");
		}
		TheGame.playSound("/botboss/sounds/open.wav");
		
		
	}
	
	public void executeAttack2(){
		if(_counter4 == 20) {
			List<Image> i = new ArrayList<Image>();
			i.add(new Image("botboss/gear/1.png"));
			i.add(new Image("botboss/gear/2.png"));
			i.add(new Image("botboss/gear/3.png"));
			Hitbox a = new AnimatedHitbox("gear", this, false, _x-167, _y, 180, 180, -7, 0, 0, 1, i, 4 );
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/gear.wav");
			
		}
		if(_enraged && _rage2count < 3 && _counter4 == 30) {
			System.out.println("enraged?");
			_counter4 = -20;
			_rage2count++;
		}
		if(!_enraged || _rage2count == 3){
		if(_counter4 == 30) {
			if(_enraged) {
				_staticimage = new Image("botboss/mad.png");
			} else {
			_staticimage = new Image("botboss/1.png");
			}
			TheGame.playSound("/botboss/sounds/close.wav");
			_counter3 = 0;
			_rage2count = 0;
			_attack2 = false;
		}
		}
	}
	public void attack3() {
		_counter4 = 0;
		_attack3 = true;
		
		if(_enraged) {
			_staticimage = new Image("botboss/madl.png");
		} else {
			_staticimage = new Image("botboss/1l.png");
		}
		
	}
	public void executeAttack3() {
		
		if(_counter4 >= 10 && _counter4 <60) {
			TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, 215, 900, 22);
			
			if(_enraged) {
				TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, 255, 900, 22);
				TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, 295, 900, 22);
			}
		}
		if(_counter4 == 10) {
			TheGame.playSound("/botboss/sounds/charge.wav");
		}
		if(_counter4 == 60) {
			 Hitbox a = new MeleeHitbox("laser1", this, 0, 215, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				TheGame.playSound("/botboss/sounds/shot.wav");
				if(_enraged){
				a = new MeleeHitbox("laser1", this, 0, 255, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				a = new MeleeHitbox("laser1", this, 0, 295, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				}
		}
		if(_counter4 >= 90 && _counter4 < 150 && _3phase <5) {
			if(_3phase == 0){
			TheGame._gc.drawImage(new Image("botboss/platform.png"), _i, _m);
			}
			if(_3phase == 1){
				TheGame._gc.drawImage(new Image("botboss/platform.png"), _j, _m);
			}
			if(_3phase == 2){
				TheGame._gc.drawImage(new Image("botboss/platform.png"), _k, _m);
			}
			if(_3phase == 3){
				TheGame._gc.drawImage(new Image("botboss/platform.png"), _l, _m);
			}
			if(_3phase == 4){
				TheGame._gc.drawImage(new Image("botboss/platform.png"), _n, _m);
			}
			
		}
		if(_counter4 == 120) {
			TheGame.playSound("/botboss/sounds/rise.wav");
		}
		if(_counter4 >= 120 && _counter4 < 129) {
			_m = _m -10;
			
			int cxl = TheGame._character1.getX();
			int cxr = TheGame._character1.getX() + TheGame._character1.getWidth();
			int cy = TheGame._character1.getY();
			if(_3phase == 0){
				if(cxl < _i + 150 && cxr > _i && cy > 345){
					TheGame._character1.setYVelocity(-15);
				}
			}
				if(_3phase == 1){
					if(cxl < _j + 150 && cxr > _j && cy > 345){
						TheGame._character1.setYVelocity(-15);
					}
				}
				if(_3phase == 2){
					if(cxl < _k + 150 && cxr > _k && cy > 345){
						TheGame._character1.setYVelocity(-15);
					}
				}
				if(_3phase == 3){
					if(cxl < _l + 150 && cxr > _l && cy > 345){
						TheGame._character1.setYVelocity(-15);
					}
					
				}
				if(_3phase == 4){
					if(cxl < _n + 150 && cxr > _n && cy > 345){
						TheGame._character1.setYVelocity(-15);
					}
				}
			
			
		}
		if(_counter4 == 140) {
			_3phase++;
			_m = 435;
			if(_3phase < 5){
			
			_counter4 = 90;
			}
		}
		if(_counter4 == 160) {
			TheGame.clearHitboxes("laser1", this);
			if(_enraged) {
				_staticimage = new Image("botboss/mad.png");
			} else {
				_staticimage = new Image("botboss/1.png");
			}
			
			_attack3 = false;
			_counter3 = 0;
			_3phase = 0;
		}
	}
	public void attack4() {
		_counter4 = 0;
		_attack4 = true;
		if(!_enraged){
			_staticimage = new Image("botboss/1l.png");
			} else {
				_staticimage = new Image("botboss/madl.png");
			}
	}
	
	public void executeAttack4() {
		if(_enraged) {
			_counter4++;
		}
		if(_counter4 >= 10) {
			makeVLaser(_counter4-10, 580, "laser1");
			makeVLaser(_counter4-10, 80, "laser11");
		}
		if(_counter4 >= 25) {
			makeVLaser(_counter4-25, 530, "laser2");
			makeVLaser(_counter4-25, 130, "laser10");
		}
		if(_counter4 >= 40) {
			makeVLaser(_counter4-40, 480, "laser3");
			makeVLaser(_counter4-40, 180, "laser9");
		}
		if(_counter4 >= 55) {
			makeVLaser(_counter4-55, 430, "laser4");
			makeVLaser(_counter4-55, 230, "laser8");
			makeHLaser(_counter4-55, 420, "laser12");
			makeHLaser(_counter4-55, 195, "laser13");
		}
		
		if(_counter4 >= 70) {
			makeVLaser(_counter4-70, 380, "laser5");
			makeVLaser(_counter4-70, 280, "laser7");
		}
		if(_counter4 == 170) {
			_attack4 = false;
			_counter3 = 0;
			if(_enraged) {
				_staticimage = new Image("botboss/mad.png");
			} else {
				_staticimage = new Image("botboss/1.png");
			}
		}
		
	}
	
private void makeVLaser(int c, int x, String s) {
	
	if(c == 0) {
		TheGame.playSound("/botboss/sounds/charge1.wav");
	}
	if(c < 60) {
		TheGame._gc.drawImage(new Image("botboss/lasers/vpre.png"), x, 60, 22, 900);
	}
	if(c == 60 || c == 61 ) {
		Hitbox a = new MeleeHitbox(s, this, x, 60, 22, 900, 0, 1, new Image("botboss/lasers/v1.png"));
		a.setDissappearOnHit(false);
		TheGame._attacks.add(a);
		if(!_lasershot){
		TheGame.playSound("/botboss/sounds/shot.wav");
		_lasershot = true;
		}
	}
	if(c == 62) {
		_lasershot = false;
	}
	if(c == 85 || c == 86) {
		TheGame.clearHitboxes(s, this);
		
	}
}
private void makeHLaser(int c, int y, String s) {
	if(c == 0) {
		TheGame.playSound("/botboss/sounds/charge1.wav");
	}
	if(c < 60) {
		TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), 0, y, 900, 22);
	}
	if(c == 60 || c == 61) {
		Hitbox a = new MeleeHitbox(s, this, 0, y, 900, 22, 0, 1, new Image("botboss/lasers/h1.png"));
		a.setDissappearOnHit(false);
		TheGame._attacks.add(a);
		TheGame.playSound("/botboss/sounds/shot.wav");
		if(!_lasershot){
			TheGame.playSound("/botboss/sounds/shot.wav");
			_lasershot = true;
			}
	}
	if(c == 62) {
		_lasershot = false;
	}
	if(c == 85 || c == 86) {
		TheGame.clearHitboxes(s, this);
		_lasershot = false;
	}
}

}
