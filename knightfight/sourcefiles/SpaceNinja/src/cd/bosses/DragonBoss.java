package cd.bosses;

import java.util.Random;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.OffsetHitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DragonBoss extends Boss {

	private int _attack1var;
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private Hitbox _body = new CharLinkedHitbox("dragbody", this, 0, 1);
	private Hitbox _tail = null;
	private Hitbox _tail2 = null;
	private Random _random = new Random();
	private boolean _unlocked;
	private boolean _locked;
	private int _attack3var;
	private boolean _drewtail2;
	private boolean _spriteswitched;
	private boolean _enraged;
	public DragonBoss() {
		super(900, 600, "dragonboss");
		_width = 240 + 48;
		_height = 130 + 26;
		_health = 1000;
		_staticimage = new Image("dragonboss/1.png");
		_body = new CharLinkedHitbox("dragbody", this, 0, 1);
		
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(new Image("dragonboss/neck1.png"), _x+206, _y+60);
		if(TheGame._character1.getLives() <= 0) {
			_won = true;
		}
		if(_counter2 == 0 && !_dead) {
			if(_spawning) {
				TheGame.setText(new Image("dragonboss/text/spawn.png"));
			} else if(!_dead) {
				Random r = new Random();
				int i = r.nextInt(6) + 1;
				
				TheGame.setText(new Image("dragonboss/text/" + i + ".png"));
			} 
			
		}
		if(_counter2 == 150) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(_health == 0) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_dead = true;
			TheGame._attacks.remove(_tail);
			TheGame._attacks.remove(_tail2);
			_staticimage = new Image("dragonboss/dead.png");
			TheGame.setText(new Image("dragonboss/text/dead.png"));
			_yvelocity = 4;
		}
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
		if(_attack3) {
			executeAttack3();
		}
		if(!_spriteswitched && _health < 300) {
			_spriteswitched = true;
			_staticimage = new Image("dragonboss/mad.png");
			TheGame.setText(new Image("dragonboss/text/mad.png"));
			_counter2 = 1;
		}
		
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
			if(_health < 300) {
				_enraged = true;
			}
			if(_won) {
				_staticimage = new Image("dragonboss/won.png");
				TheGame.setText(new Image("dragonboss/text/won.png"));
			}
			if(_counter1 < 29) {
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
			if(_counter3 == 100 && !_won) {
				int i = _random.nextInt(3);
				_counter3 = 0;
				
				if(_repeat1 == 2) {
					i = 3;
				}
				if(_repeat2 == 2) {
					i = 2;
				}
				if(_repeat3 == 2) {
					i = 0;
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
				
				if(i == 0) {
					
					_attack1var = _random.nextInt(2);
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
					_repeatno2 =0;
					_repeatno3++;
					_repeatno4++;
				}
				if(i == 2) {
					_attack3var = _random.nextInt(2);
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
					//attack4();
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
		_counter2 = 0;
		_x = 910;
		_y = 110;
		// resting _x is 506
		_xvelocity = -4;
		
	}
	public void executeSpawn() {
		if(_counter2 == 100) {
			_xvelocity = 0;
			_spawning = false;
			System.out.println(_x);
		}
	}
	
	
	public void attack1() {
			_yvelocity = -15;
		
		
		_attack1 = true;
		_counter4 = 0;
	}
	public void executeAttack1() {
		if(_counter4 == 15) {
			_yvelocity = 13;
		}
		if(_counter4 >= 15 && _counter4 <60){
			TheGame._gc.drawImage(new Image("dragonboss/beam/prebeam.png"), _x-437, _y+91);
		}
		if(_counter4 == 38) {
			_yvelocity = 3;
		}
		if(_counter4 == 60) {
			Hitbox a = new OffsetHitbox("beam1", this, -437, 91, 609, 42, 0, 1, new Image("dragonboss/beam/beam.png"));
			a.setDissappearOnHit(false);
			Hitbox b = new OffsetHitbox("beam2", this, -437-98, 91-29, 100, 100, 0, 1, new Image("dragonboss/beam/ball.png"));
			b.setDissappearOnHit(false);
			b.setCircle(true);
			TheGame.playSound("/dragonboss/sounds/beam.wav");
			TheGame._attacks.add(a);
			TheGame._attacks.add(b);
		}
		if(_counter4 == 70) {
			_yvelocity = 0;
		}
		if(_counter4 == 80) {
			TheGame.clearHitboxes("beam1", this);
			TheGame.clearHitboxes("beam2", this);
		}
		if(_counter4 == 90 && _attack1var == 1) {
			_yvelocity = -1;
		}
		if(_counter4 >= 90 && _counter4 % 30 == 0 && _counter4 < 190) {
			Hitbox a = new HitboxImpl("fireball", this, false,  _x-5, _y+58, 124, 100, -10, 0, 0, 1, new Image("dragonboss/beam/shot.png"));
			a.setDissappearOnHit(false);
			TheGame.playSound("/dragonboss/sounds/ball.wav");
			a.setCircle(true);
			TheGame._attacks.add(a);
		}
		if(!_enraged){
		if(_counter4 == 200) {
			_yvelocity = -12;
		}
		if(_counter4 >= 200 && _y <= 110) {
			_y = 110;
			_counter3 = 0;
			_attack1 = false;
		}
		} else {
			if(_counter4 == 200) {
				if(_attack1var == 0) {
					_yvelocity = -14;
				} else {
					_yvelocity = 10;
				}
			}
			if(_counter4 == 215) {
				_yvelocity = 0;
				Hitbox a = new OffsetHitbox("beam1", this, -437, 91, 609, 42, 0, 1, new Image("dragonboss/beam/beam.png"));
				a.setDissappearOnHit(false);
				Hitbox b = new OffsetHitbox("beam2", this, -437-98, 91-29, 100, 100, 0, 1, new Image("dragonboss/beam/ball.png"));
				b.setDissappearOnHit(false);
				b.setCircle(true);
				TheGame.playSound("/dragonboss/sounds/beam.wav");
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
			}
			if(_counter4 == 225) {
				TheGame.clearHitboxes("beam1", this);
				TheGame.clearHitboxes("beam2", this);
			}
			if(_counter4 == 225) {
				if(_attack1var == 0) {
					_yvelocity = 14;
				} else {
					_yvelocity = -14;
				}
			}
			if(_counter4 == 240) {
				_yvelocity = 0;
				Hitbox a = new OffsetHitbox("beam1", this, -437, 91, 609, 42, 0, 1, new Image("dragonboss/beam/beam.png"));
				a.setDissappearOnHit(false);
				Hitbox b = new OffsetHitbox("beam2", this, -437-98, 91-29, 100, 100, 0, 1, new Image("dragonboss/beam/ball.png"));
				b.setDissappearOnHit(false);
				TheGame.playSound("/dragonboss/sounds/beam.wav");
				b.setCircle(true);
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
			}
			if(_counter4 == 250) {
				TheGame.clearHitboxes("beam1", this);
				TheGame.clearHitboxes("beam2", this);
			}
			if(_counter4 == 250) {
				if(_attack1var == 0) {
					_yvelocity = -7;
				} else {
					_yvelocity = 7;
				}
			}
			if(_counter4 == 265) {
				_yvelocity = 0;
				Hitbox a = new OffsetHitbox("beam1", this, -437, 91, 609, 42, 0, 1, new Image("dragonboss/beam/beam.png"));
				a.setDissappearOnHit(false);
				Hitbox b = new OffsetHitbox("beam2", this, -437-98, 91-29, 100, 100, 0, 1, new Image("dragonboss/beam/ball.png"));
				b.setDissappearOnHit(false);
				b.setCircle(true);
				TheGame.playSound("/dragonboss/sounds/beam.wav");
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
			}
			if(_counter4 == 275) {
				TheGame.clearHitboxes("beam1", this);
				TheGame.clearHitboxes("beam2", this);
			}
			if(_counter4 == 275) {
				_yvelocity = -12;
			}
			if(_counter4 >= 275 && _y <= 110) {
				_y = 110;
				_counter3 = 0;
				_attack1 = false;
			}
		}
		
	}
	public void attack2() {
		_attack2 = true;
		_counter4 = 0;
	}
	public void executeAttack2() {
		if(_counter4 > 20 && !_locked && !_unlocked) {
			_yvelocity = 10;
		}
		if(_y+(_height / 2) - 25 >= TheGame._character1.getY() && !_unlocked && _counter1 < 160) {
			_yvelocity = 0;
			_y = TheGame._character1.getY() - (_height / 2) + 25;
			if(!_locked) {
				_counter1 = 0;
				_yvelocity = 0;
				_locked = true;
			}
		}
		if(_locked) {
		if(_counter1 < 170) {
			_yvelocity = 0;
		}
		if(_counter1 < 100) {
			_y = TheGame._character1.getY() - (_height / 2) + 25;
			_xvelocity = 0;
		}
		if(_counter1 == 85) {
			_x+=50;
		}
		int i = 30;
		if(_health < 300) {
			i = 15;
		}
		if(_counter1 < 101 && _counter1 % i == 0) {
			Hitbox a = new HitboxImpl("fireball", this, false,  _x-5, _y+58, 124, 100, -18, 0, 0, 1, new Image("dragonboss/beam/shot.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/dragonboss/sounds/ball.wav");
		}
		if(_counter1 == 101) {
			_unlocked = true;
			_yvelocity = 0;
			_xvelocity = -30;
			TheGame.playSound("/dragonboss/sounds/tail.wav");
			
		}
		
		
		// easy variant attack ending
		
		if(_counter1 == 140) {
			_xvelocity = 15;
		}
		if(_counter1 > 170 && _x >= 506) {
			
			_x = 506;
			_xvelocity = 0;
			if(_y > 110) {
				_yvelocity = -15;
			} else {
				_yvelocity = 15;
			}
		}
		if(_counter1 > 170 && _y <= 110) {
			_y=110;
			_yvelocity = 0;
			_unlocked = false;
			_locked = false;
			_attack2 = false;
			_counter3 = 0;
		}
		
		}
	}
	public void attack3() {
		_attack3 = true;
		_counter4 = 0;
		_yvelocity = 10;
	}
	
	public void executeAttack3() {
		int x = 0;
		int y = 0;
		if(_attack3var == 0) {
			x = 30;
			y = 200;
		} else {
			x = 200;
			y = 30;
		}
			
		
		if(_counter4 == 15) {
			_yvelocity = 0;
		}
		if(_counter4 >= 20 && _counter4 % 50 == 0) {
			Hitbox a = new HitboxImpl("fireball", this, false,  _x-5, _y+58, 124, 100, -18, 0, 0, 1, new Image("dragonboss/beam/shot.png"));
			a.setDissappearOnHit(false);
			a.setCircle(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/dragonboss/sounds/ball.wav");
		}
		if(_counter4 >= 0 && _counter4 < 45) {
			TheGame._gc.drawImage(new Image("dragonboss/tail.png"), x, 386, 150, 600);
			
			if(_health < 300) {
				TheGame._gc.drawImage(new Image("dragonboss/tail.png"), y, 386, 150, 600);
				_drewtail2 = true;
				
			}
		}
		if(_counter4 == 45) {
			_tail = new HitboxImpl("tail", this, false, x, 386, 150, 600, 0, -15, 0, 1, new Image("dragonboss/tail.png"));
			_tail.setDissappearOnHit(false);
			TheGame._attacks.add(_tail);
			TheGame.playSound("/dragonboss/sounds/tail.wav");
			if(_drewtail2) {
				_tail2 = new HitboxImpl("tail", this, false, y, 386, 150, 600, 0, -15, 0, 1, new Image("dragonboss/tail.png"));
				_tail2.setDissappearOnHit(false);
				TheGame._attacks.add(_tail2);
			}
		}
		if(_counter4 == 65) {
			_tail.setYVelocity(0);
			if(_drewtail2) {
				_tail2.setYVelocity(0);
			}
		}
		if(_counter4 == 100) {
			_tail.setYVelocity(15);
			if(_drewtail2) {
				_tail2.setYVelocity(15);
			}
		}
		if(_counter4 == 110) {
			_yvelocity = -10;
		}
		if(_counter4 >= 110 && _y<=110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack3 = false;
			_drewtail2 = false;
		}
	} 

}
