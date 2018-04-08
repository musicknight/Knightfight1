package cd.bosses;

import java.util.Random;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RockBoss extends Boss {

	private boolean _locked;
	private boolean _unlocked;
	private Hitbox _body = new CharLinkedHitbox("rockbody", this, 0, 1);
	private int _attack1var;
	//how many times an attack has been done in a row
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	// true if stunned the player
	private boolean _stunned;
	
	
	public RockBoss() {
		super(900, 600, "rockboss");
		_width = 278;
		_height = 132;
		_health = 1000;
		_body = new CharLinkedHitbox("rockbody", this, 0, 1);
		_staticimage = new Image("rockboss/sprites/1.png");
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(TheGame._character1.getLives() == 0) {
			_won = true;
		}
		if(_counter2 == 0 && !_won && !_dead) {
			
				Random r = new Random();
				int i = r.nextInt(4);
				if (i==0) {
					i = 1;
				}
				if(i==1) {
					i = 2;
				}
				if(i==2) {
					i = 7;
				}
				if(i==3) {
					i = 8;
				}
				TheGame.setText(new Image("rockboss/text/" + i + ".png"));
			
			
			
		}
		if(_counter2 == 150) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(_health <= 760 && _health >= 740) {
			TheGame.setText(new Image("rockboss/text/3.png"));
			_counter2 = 1;
		}
		if(_health <= 510 && _health >= 490) {
			TheGame.setText(new Image("rockboss/text/6.png"));
			_counter2 = 1;
		}
		if(_health <= 260 && _health >= 240) {
			TheGame.setText(new Image("rockboss/text/5.png"));
			_counter2 = 1;
		}
		if(_health == 0) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_dead = true;
		}
		if(_dead) {
			_staticimage = new Image("rockboss/sprites/dead.png");
			TheGame.setText(new Image("rockboss/text/dead.png"));
			_xvelocity = 0;
			_yvelocity = 2;
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
		}
		if(_health > 750) {
			_staticimage = new Image("rockboss/sprites/1.png");
		} else if(_health > 500) {
			_staticimage = new Image("rockboss/sprites/hurt1.png");
		} else if(_health > 250) {
			_staticimage = new Image("rockboss/sprites/hurt2.png");
		} else if(_health > 0) {
			_staticimage = new Image("rockboss/sprites/hurt3.png");
		}
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
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
			if(_counter1 < 30) {
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
			if(_won) {
				_staticimage = new Image("rockboss/sprites/victory.png");
				TheGame.setText(new Image("rockboss/text/won.png"));
			}
			if(_counter3 == 120 && !_won) {
				Random r = new Random();
				int i = r.nextInt(3);
				_counter3 = 0;
				if(_repeat1 == 2) {
					i = 2;
				}
				if(_repeat2 == 2) {
					i = 1;
				}
				if(_repeat3 == 2) {
					i = 0;
				}
				if(_repeatno1 == 4) {
					i = 1;
				}
				if(_repeatno2 == 4) {
					i = 0;
				}
				if(_repeatno3 == 4) {
					i = 2;
				}
				if(i == 1) {
					_attack1var = r.nextInt(3);
					attack1();
					_repeat1++;
					_repeat2 = 0;
					_repeat3 = 0;
					_repeatno1 = 0;
					_repeatno2++;
					_repeatno3++;
				}
				if(i == 0) {
					attack2();
					_repeat2++;
					_repeat1 = 0;
					_repeat3 = 0;
					_repeatno1++;
					_repeatno2 = 0;
					_repeatno3++;
				}
				if(i == 2) {
					attack3();
					_repeat3++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3 = 0;
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
		// resting _x is 607
		_xvelocity = -3;
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 100) {
			_xvelocity = 0;
			_spawning = false;
		}
	}
	
	public void attack1() {
		_attack1 = true;
		_yvelocity = -10;
		_counter4 = 0;
	}
	
	public void executeAttack1() {
		if(_counter4 == 4) {
			_yvelocity = 0;
		}
		if(_counter4 == 14) {
			_yvelocity = 2;
		}
		if(_health > 250) {
		if(_counter4 > 14 && _counter4 % 12 == 0 && _counter4 < 110) {
			Hitbox a = null;
			if(_attack1var == 0){
				a = (new HitboxImpl("rockblast", this, true, _x+115, _y+78, 60, 60, -3 - (_counter4 / 4), 30, 0, 1, new Image("rockboss/sprites/blast.png")));
			}
			if(_attack1var == 1){
				a = (new HitboxImpl("rockblast", this, true, _x+115, _y+78, 60, 60, -1 - (_counter4 / 2), 30, 0, 1, new Image("rockboss/sprites/blast.png")));	
			}
			if(_attack1var == 2) {
				a = (new HitboxImpl("rockblast", this, true, _x+115, _y+78, 60, 60, -25 + (_counter4 / 6), 30, 0, 1, new Image("rockboss/sprites/blast.png")));
			}
			
			a.setBounces(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/rockboss/sounds/shot.wav");
			
		}
		} else {
			if(_counter4 > 14 && _counter4 % 6 == 0 && _counter4 < 110) {
				Hitbox a = null;
				if(_attack1var == 0){
					a = (new HitboxImpl("rockblast", this, true, _x+115, _y+78, 60, 60, -3 - (_counter4 / 4), 30, 0, 1, new Image("rockboss/sprites/blast.png")));
				}
				if(_attack1var == 1){
					a = (new HitboxImpl("rockblast", this, true, _x+115, _y+78, 60, 60, -1 - (_counter4 / 2), 30, 0, 1, new Image("rockboss/sprites/blast.png")));	
				}
				if(_attack1var == 2) {
					a = (new HitboxImpl("rockblast", this, true, _x+115, _y+78, 60, 60, -25 + (_counter4 / 6), 30, 0, 1, new Image("rockboss/sprites/blast.png")));
				}
				
				a.setBounces(true);
				TheGame._attacks.add(a);
				TheGame.playSound("/rockboss/sounds/shot.wav");
			}
		}
		if(_counter4 == 120) {
			_yvelocity = 0;
		}
		if(_counter4 >= 135 && _y > 110) {
			_yvelocity = -11;
		}
		if(_counter4 > 135 && _y < 110) {
			_y=110;
		}
		if(_y==110 && _counter4 > 135) {
			_yvelocity = 0;
			_attack1 = false;
			_counter3 = 0;
			if(_health < 250) {
				_counter3 = 40;
			}
		}
	}
	
	public void attack2() {
		_attack2 = true;
		_counter4 = 0;
		_xvelocity = 8;
	}
	
	public void executeAttack2() {
		if(_counter4 > 40 && _x+(_width / 2) - 50 > TheGame._character1.getX() && !_locked && !_unlocked) {
			_xvelocity = -12;
		}
		if(_x+(_width / 2) - 25 <= TheGame._character1.getX() && !_unlocked && _counter1 < 160) {
			_xvelocity = 0;
			_x = TheGame._character1.getX() - (_width / 2) + 25;
			if(!_locked) {
				_counter1 = 0;
				_xvelocity = 0;
				_locked = true;
			}
		}
		if(_locked) {
		if(_counter < 232) {
			_xvelocity = 0;
		}
		if(_counter1 < 160) {
			_x = TheGame._character1.getX() - (_width / 2) + 25;
			_xvelocity = 0;
		}
		if(_counter1 == 160) {
			_unlocked = true;
			_xvelocity = 0;
			_counter1 = 168;
			
		}
		if(_counter1 == 185) {
			_yvelocity = 30;
			
		}
		if(_counter1 == 192) {
			_yvelocity = 0;
			TheGame.playSound("/rockboss/sounds/slam.wav");
		}
		// easy variant attack ending
		if(_health > 250) {
		if(_counter1 == 232) {
			_yvelocity = -15;
		}
		
		if(_counter1 > 232 && _y < 110) {
			_y=110;
			_yvelocity = 0;
			_xvelocity = 15;
			
		}
		if(_counter1 >= 232 && _x >= 607) {
			_x = 607;
			_y = 110;
			_xvelocity = 0;
			_unlocked = false;
			_locked = false;
			_attack2 = false;
			_counter3 = 0;
		}
		
		} else {
		// hard attack variant
		if(_counter1 == 199) {
			_yvelocity = -30;
		}
		if(_counter1 == 206) {
			_yvelocity = 30;
		}
		if(_counter1 == 213) {
			_yvelocity = -30;
			TheGame.playSound("/rockboss/sounds/slam.wav");
		}
		if(_counter1 == 220) {
			_yvelocity = 30;
		}
		if(_counter1 == 227) {
			_yvelocity = -30;
			TheGame.playSound("/rockboss/sounds/slam.wav");
		}
		if(_counter1 == 234) {
			_yvelocity = 30;
		}
		if(_counter1 == 241) {
			_yvelocity = 0;
			TheGame.playSound("/rockboss/sounds/slam.wav");
		}
		if(_counter1 == 251) {
			_yvelocity = -15;;
		}
		if(_counter1 > 251 && _y < 110) {
			_y=110;
			_yvelocity = 0;
			_xvelocity = 15;
		}
		if(_counter1 > 251 && _x >= 607) {
			_x = 607;
			_y = 110;
			_xvelocity = 0;
			_unlocked = false;
			_locked = false;
			_attack2 = false;
			_counter3 = 40;
		}
		}
		}
	}
	
	public void attack3() {
		_attack3 = true;
		_yvelocity = -30;
		_counter4 = 0;
	}
	public void executeAttack3() {
		if(_counter4 == 15) {
			_yvelocity = 30;
		}
		if(_counter4 == 37) {
			_yvelocity = 0;
			TheGame.playSound("/rockboss/sounds/slam.wav");
			if(TheGame._character1.getY() == 392) {
				TheGame._character1.setCanAct(false);
				TheGame._character1.setXVelocity(0);
				TheGame._character1.setYVelocity(0);
				_stunned = true;
			}
		}
		if(_stunned) {
			TheGame._gc.drawImage(new Image("ninja/stun.png"), TheGame._character1.getX() - 10, TheGame._character1.getY());
			TheGame._gc.drawImage(new Image("ninja/stun.png"), TheGame._character1.getX() + TheGame._character1.getWidth(), TheGame._character1.getY());
		}
		if(_counter4 == 42) {
			Hitbox 	a = (new HitboxImpl("stunblast", this, false, _x+115, _y+70, 60, 60, -25 , 0, 0, 1, new Image("rockboss/sprites/blast.png")));
			TheGame._attacks.add(a);
			TheGame.playSound("/rockboss/sounds/shot.wav");
		}
		if(_health < 250 && _counter4 > 42 && _counter4 < 65  && _counter4 % 3 == 0) {
			Hitbox 	a = (new HitboxImpl("stunblast", this, false, _x+115, _y+70, 60, 60, -25 , 0, 0, 1, new Image("rockboss/sprites/blast.png")));	
			TheGame._attacks.add(a);
			TheGame.playSound("/rockboss/sounds/shot.wav");
		}
		if(_counter4 == 57) {
			_stunned = false;
			TheGame._character1.setCanAct(true);
			_yvelocity = -11;
		}
		
			
		
		if(_counter4 > 57 && _y < 110) {
			_y=110;
		}
		if(_y==110 && _counter4 > 57) {
			_yvelocity = 0;
			_attack3 = false;
			_counter3 = 0;
			if(_health < 250) {
				_counter3 = 40;
			}
		}
	}
	
}
