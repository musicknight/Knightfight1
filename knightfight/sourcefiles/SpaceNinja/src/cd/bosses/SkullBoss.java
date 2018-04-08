package cd.bosses;

import java.util.Random;

import cd.Backdrop;
import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SkullBoss extends Boss {

	private boolean _hattack1;
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private int _attack3var;
	private Hitbox _body = new CharLinkedHitbox("skullbody", this, 0, 1);
	private Backdrop _bd = new Backdrop(new Image("skullboss/2.png"), 900, 150, 186, 121);
	private boolean _form2;
	private Random _random = new Random();
	private Hitbox _line = null;
	private boolean _changeform;
	private boolean _stunned;
	private boolean _hattack2;

	public SkullBoss() {
		super(900, 600, "skullboss");
		_width = (int)(186*1.3);
		_height = (int)(121*1.3);
		_health = 1200;
		_staticimage = new Image("skullboss/1.png");
		_body = new CharLinkedHitbox("skullbody", this, 0, 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_subboss != null) {
			_subboss.render(gc);
			_subboss.incrementCounter();
		}
		if(!TheGame._attacks.contains(_body) && _health > 0) {
			TheGame._attacks.add(_body);
		}
		if(_counter2 == 0 && !_dead) {
			if(_spawning) {
				TheGame.setText(new Image("skullboss/text/spawn.png"));
			} else if(!_dead) {
				Random r = new Random();
				int i = r.nextInt(5) + 1;
				
				TheGame.setText(new Image("skullboss/text/" + i + ".png"));
			} 
			
		}
		if(_counter2 == 150) {
			TheGame.stopText();
			_counter2 = -500;
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
		if(_changeform) {
			changeForm();
		}
		if(_hattack1) {
			executeHandAttack1();
		}
		if(_health == 0 && !_dead) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_hattack1 = false;
			_hattack2 = false;
			_dead = true;
			_yvelocity = 4;
			_subboss.setDead(true);
			_subboss.setYVelocity(4);
			_bd.setImage(new Image("skullboss/dead.png"));
			TheGame.setText(new Image("skullboss/text/dead.png"));
			_counter4 = 0;
		}
		if(_dead) {
			if(_counter4 >= 0) {
				_bd.setY(150+(_counter4*4));
			}
		}
		if(TheGame._character1.getLives()<= 0 && !_won) {
			_won = true;
			
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_hattack1 && !_dead&& !_changeform) {
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
			if(_won) {
				if(!_form2) {
					_staticimage = new Image("skullboss/won1.png");
				} else {
					_bd.setImage(new Image("skullboss/won2.png"));
				}
				TheGame.setText(new Image("skullboss/text/won.png"));
			}
			if(_health < 500 && !_form2 && !_spawning && !_won) {
				_changeform = true;
				_counter4 = 0;
				TheGame.setText(new Image("skullboss/text/mad.png"));
				_counter2 = 1;
			}
			if(_counter3 == 90 && !_won ) {
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
					i = 0;
				}
				if(_repeatno2 == 4) {
					i = 1;
				}
				if(_repeatno3 == 4) {
					i = 2;
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
					if(!_form2){
					attack2();
					} else {
						handAttack1();
					}
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
					_attack3var = _random.nextInt(3) + 1;
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
		_y = 110;
		_x = 910;
		_spawning = true;
		_xvelocity = -4;
		_counter2 = 0;
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 80) {
			_xvelocity = 0;
			_spawning = false;
		}
	}
	
	public void attack1() {
		_yvelocity = 10;
		_counter4 = 0;
		_attack1 = true;
		if(_form2) {
			_subboss.attack2();
		}
	}
	
	public void executeAttack1() {
		if(_counter4 == 45) {
			_line = new HitboxImpl("line", this, false, 0, 442, 700, 125, 0, -10, 0, 1, new Image("skullboss/bone/line.png"));
			_line.setDissappearOnHit(false);
			TheGame._attacks.add(_line);
		}
		if(_counter4 == 55) {
			_line.setYVelocity(0);
		}
		if(_counter4 >= 15 && _counter4 <65) {
			_yvelocity = 0;
			TheGame._gc.drawImage(new Image("skullboss/bone/pre2.png"), 0, 438);
		}
		if(!_form2){
		if(_counter4 == 85) {
			_line.setYVelocity(10);
			_yvelocity = -10;						
		}
		if(_counter4 >= 85 && _y<= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack1 = false;
		}
		} else {
			if(_counter4 == 85) {
				_line.setYVelocity(10);
				_xvelocity = -5;
			}
			if(_counter4 == 90) {
				_yvelocity = -8;
			}
			if(_counter4 == 110) {
				_yvelocity =8;
			}
			if(_counter4 == 130) {
				_yvelocity = -8;
			}
			if(_counter4 == 150) {
				_yvelocity = 8;
			}
			if(_counter4 == 170) {
				_yvelocity = -8;
			}
			if(_counter4 == 190) {
				_yvelocity = 8;
			}
			if(_counter4 == 210) {
				_yvelocity = -8;
			}
			if(_counter4 == 260) {
				_xvelocity = 0;
				_y = -200;
				_x = 661;
				_yvelocity = 9;
			}
			if(_counter4 >= 260 && _y >= 110) {
				_y = 110;
				_counter3 = 0;
				_attack1 = false;
			}
			
		}
	}
	public void attack2() {
		_attack2 = true;
		_counter4 = 0;
		_yvelocity = 5;
	}
	public void executeAttack2() {
		if(_counter4 == 15) {
			_yvelocity = 0;
		}
		if(_counter4 >= 10 && _counter4 < 30) {
			TheGame._gc.drawImage(new Image("skullboss/bone/pre3.png"), 362, 438);
		}
		if(_counter4 >= 25 && _counter4 % 58 == 0 && _counter4 <= 300) {
			Hitbox a = new HitboxImpl("bone", this, false, 1, 1, 362, 60, 0, 6, 0, 1, new Image("skullboss/bone/boneh2.png"));
			a.setDissappearOnHit(false);
			Hitbox b = new HitboxImpl("bone", this, false, 362, 442, 362, 60, 0, -6, 0, 1, new Image("skullboss/bone/boneh2.png"));
			b.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame._attacks.add(b);
		}
		if(_counter4 == 300) {
			_yvelocity = -10;	
		}
		if(_counter4 >= 300 && _y<= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack2 = false;
		}
	}
	
	public void attack3() {
		_yvelocity = -15;
		_counter4 = 0;
		_attack3 = true;
	}
	
	public void executeAttack3() {
		if(_counter4 == 20) {
			_yvelocity = 0;
		}
		if(_counter4 >= 35 && _counter4 < 78) {
			if(_attack3var == 0 ) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre750.png"), 0, 60);
			} else if(_attack3var == 1) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre750.png"), 150, 60);
			} else if(_attack3var == 2) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre300.png"), 0, 60);
				TheGame._gc.drawImage(new Image("skullboss/bone/pre450.png"), 450, 60);
			} else if(_attack3var == 3) {
				TheGame._gc.drawImage(new Image("skullboss/bone/pre450.png"), 0, 60);
				TheGame._gc.drawImage(new Image("skullboss/bone/pre300.png"), 600, 60);
			}
		}
		if(_counter4 == 78) {
			
			 if(_attack3var == 1) {
				Hitbox a = new HitboxImpl("bone", this, true, 150, -90, 750, 150, 0, 0, 0, 1, new Image("skullboss/bone/750.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			} else if(_attack3var == 2) {
				Hitbox a = new HitboxImpl("bone", this, true, 0, -90, 300, 150, 0, 0, 0, 1, new Image("skullboss/bone/300.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("bone", this, true, 450, -90, 450, 150, 0, 0, 0, 1, new Image("skullboss/bone/450.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			} else if(_attack3var == 3) {
				Hitbox a = new HitboxImpl("bone", this, true, 0, -90, 450, 150, 0, 0, 0, 1, new Image("skullboss/bone/450.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				a = new HitboxImpl("bone", this, true, 600, -90, 300, 150, 0, 0, 0, 1, new Image("skullboss/bone/300.png"));
				a.setDissappearOnHit(false);
				TheGame._attacks.add(a);
			}
		}
		if(_counter4 == 85) {
			_yvelocity = 15;
		}
		if(_counter4 >= 85 && _y >= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_attack3 = false;
		}
	}
	
	public void changeForm() {
		if(_counter4 == 1) {
			_xvelocity = 25;
		}
		if(_counter4 == 25) {
			_xvelocity = 0;
		}
		if(_counter4 >= 25 && _counter4 <115) {
			TheGame._backdrops.add(_bd);
			_bd.setX((900 - ((_counter4-10)*5)));
			
		}
		if(_counter4 == 25) {
			_width = 130;
			_height = (int)(140*1.3);
			TheGame._attacks.remove(_body);
			_body = new CharLinkedHitbox("fistbody", this, 0, 1);
			_xvelocity = -5;
			_subboss = new SkullLeftHand();
			_subboss.spawn();
			_staticimage = new Image("skullboss/rfist.png");
		}
		if(_counter4 == 130) {
			_xvelocity = 0;
			// new resting x 661
			_counter3 = 0;
			_changeform = false;
			_form2 = true;
		}
		
		
	}
	
	public void handAttack1() {
		_xvelocity = 8;
		_subboss.attack1();
		_counter4 = 0;
		_hattack1 = true;
	}
	
	public void executeHandAttack1() {
		if(_counter4 == 15) {
			_xvelocity = 0;
		}
		if(_counter4 == 20) {
			_yvelocity = 20;
		}
		if(_counter4 == 28) {
			_yvelocity= 0;
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
		if(_counter4 == 35) {
			_xvelocity = -3;
		}
		if(_counter4 == 45) {
			_xvelocity = -20;
		}
		if(_counter4 == 59) {
			_xvelocity = 0;
			_stunned = false;
			TheGame._character1.setCanAct(true);
		}
		if(_counter4 == 95) {
			_xvelocity = 20;
		}
		if(_counter4 >= 95 && _x >= 661) {
			_x = 661;
			_xvelocity = 0;
			_yvelocity = -10;
		}
		if(_counter4 >= 95 && _y <= 110) {
			_y = 110;
			_yvelocity = 0;
			_counter3 = 0;
			_hattack1 = false;
		}
	}

}
