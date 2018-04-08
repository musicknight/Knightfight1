package cd.bosses;

import java.util.ArrayList;
import java.util.Random;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TwinsBoss extends Boss {

	private Hitbox _body = new CharLinkedHitbox("twin1bod", this, 0, 1);
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private Hitbox _bounce;
	private Random _random = new Random();
	private int _attack2var;
	private int _attack3var;
	private boolean _changeform;
	private boolean _form2;
	private boolean _attack5;
	private boolean _attack6;
	
	public TwinsBoss() {
		super(815, 328, "twinsboss");
		_width = (int)(86 * 0.6);
		_height = (int)(192*0.6);
		_health = 1500;
		_sprites = new ArrayList<Image>();
		_sprites.add(new Image("twinsboss/a1.png"));
		_sprites.add(new Image("twinsboss/a2.png"));
		_rate = 5;
		_body= new CharLinkedHitbox("twin1bod", this, 0, 1);
		_subboss = new Twin2();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_subboss!=null){
		_subboss.render(gc);
		_subboss.incrementCounter();
		}
		if(_counter2 == 0 && !_dead && !_won) {
			if(!_dead) {
				Random r = new Random();
				int i = r.nextInt(6) + 1;
				if(_form2) {
					i = r.nextInt(2) + 8;
				}
				
				TheGame.setText(new Image("twinsboss/text/" + i + ".png"));
			} 
			
		}
		if(_counter2 == 150) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(!TheGame._attacks.contains(_body)) {
			TheGame._attacks.add(_body);
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
		if(_attack4) {
			executeAttack4();
		}
		if(_attack5) {
			executeAttack5();
		}
		if(_attack6) {
			executeAttack6();
		}
		if(_changeform) {
			changeForm();
		}
		if(_health == 0 && !_dead) {
			_width =  (int)(192*0.6);
			_height = (int)(86*0.6);
			_y+=(int)(106*0.6);
			_staticimage = new Image("twinsboss/bdead.png");
			_xvelocity = -16;
			_dead = true;
			TheGame.setText(new Image("twinsboss/text/dead.png"));
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_attack6 && !_dead && !_changeform) {
			if(_health < 600 && !_changeform && !_form2) {
				_changeform = true;
				_counter4 = 0;
				_counter2 = 1;
				TheGame.setText(new Image("twinsboss/text/7.png"));
			}
			if(TheGame._character1.getLives() <= 0 && !_won) {
				if(!_form2) {
					_sprites.clear();
					_sprites.add(new Image("twinsboss/awon1.png"));
					_sprites.add(new Image("twinsboss/awon2.png"));
					_subboss.switchSprites();
					TheGame.setText(new Image("twinsboss/text/won1.png"));
				} else {
					_sprites.clear();
					_sprites.add(new Image("twinsboss/bwin1.png"));
					_sprites.add(new Image("twinsboss/bwin2.png"));
					TheGame.setText(new Image("twinsboss/text/won2.png"));
				}
				_counter2 = 1;
				_won = true;
				
			}
			if(_counter3 == 120 && !_won) {
				
				int i = _random.nextInt(3);
				if(_form2) {
					i = _random.nextInt(2);
				}
				_counter3 = 0;
				
				if(_repeat1 == 2) {
					i = 1;
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
					if(!_form2){
					attack1();
					} else {
					attack4();
					}
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
					_attack2var = _random.nextInt(2);
					attack2();
					} else {
					attack5();
					}
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
					if(!_form2){
					_attack3var = _random.nextInt(2);
					attack3();
					} else {
					attack6();
					}
					_repeat3++;
					_repeat1 = 0;
					_repeat2 = 0;
					_repeat4 = 0;
					_repeatno1++;
					_repeatno2++;
					_repeatno3=0;
					_repeatno4++;
					
				}
				
			}
		}
	}

	@Override
	public void spawn() {
		_counter2 = 1;
		TheGame.setText(new Image("twinsboss/text/spawn.png"));
		
	}
	
	public void attack1() {
		_attack1 = true;
		_subboss.attack1();
		_counter4 = 0;
		_staticimage = new Image("twinsboss/a3.png");
		
	}
	
	public void executeAttack1() {
		if(_counter4 >= 10 && _counter4 % 70 == 0) {
			Hitbox a = new HitboxImpl("ball", this, false, _x - 150, _y, 150, 150, -5, 0, 0, 1, new Image("twinsboss/shots/a1.png"));
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
		}
		if(_counter4 == 300) {
			_attack1 = false;
			_counter3 = 0;
			_staticimage = null;
		}
	}
	
	public void attack2() {
		_attack2 = true;
		_counter4 = -15;
		if(_attack2var == 0){
		_staticimage = new Image("twinsboss/a3.png");
		}
		_subboss.attack2(_attack2var);
	}
	
	public void executeAttack2() {
		if(_attack2var == 0){
		if(_counter4 == 20) {
			_bounce = new HitboxImpl("ball", this, false, _x - 70, _y+15, 75, 75, -20, 0, 0, 1, new Image("twinsboss/shots/c1.png"));
			_bounce.setCircle(true);
			_bounce.setDissappearOnHit(false);
			TheGame._attacks.add(_bounce);
		}
		if(_counter4 == 25) {
			_yvelocity = -20;
		}
		if(_counter4 == 30) {
			_yvelocity = 0;
		}
		if(_counter4 == 55) {
			_bounce.setXVelocity(18);
			_bounce.setYVelocity(-3);
		}
		if(_counter4 == 95) {
			_bounce.setYVelocity(0);
			_bounce.setXVelocity(-20);
		}
		if(_counter4 == 100) {
			_yvelocity = 20;
		}
		if(_counter4 == 105) {
			_yvelocity = 0;
		}
		if(_counter4 == 130) {
			_bounce.setXVelocity(18);
			_bounce.setYVelocity(3);
		}
		if(_counter4 == 170) {
			_bounce.setXVelocity(-20);
			_bounce.setYVelocity(0);
		}
		if(_counter4 == 205) {
			_bounce.setXVelocity(20);
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
			_counter3 = 0;
			_staticimage = null;
		}
		} else {
			if(_counter4 == 20) {
				_bounce = new HitboxImpl("ball", this, false, _subboss.getX()+_width, _subboss.getY()+15, 75, 75, 20, 0, 0, 1, new Image("twinsboss/shots/c1.png"));
				_bounce.setCircle(true);
				_bounce.setDissappearOnHit(false);
				TheGame._attacks.add(_bounce);
			}
			if(_counter4 == 55) {
				_bounce.setXVelocity(-18);
				_bounce.setYVelocity(-3);
				_staticimage = new Image("twinsboss/a3.png");
			}
			if(_counter4 == 60) {
				_yvelocity = -20;
			}
			if(_counter4 == 65) {
				_yvelocity = 0;
			}
			if(_counter4 == 95) {
				_bounce.setYVelocity(0);
				_bounce.setXVelocity(20);
			}
			
			if(_counter4 == 130) {
				_bounce.setXVelocity(-18);
				_bounce.setYVelocity(3);
			}
			if(_counter4 == 135) {
				_yvelocity = 20;
			}
			if(_counter4 == 140) {
				_yvelocity = 0;
			}
			if(_counter4 == 170) {
				_bounce.setXVelocity(20);
				_bounce.setYVelocity(0);
			}
			if(_counter4 == 205) {
				_bounce.setXVelocity(-20);
			}
			
			if(_counter4 == 250) {
				_attack2 = false;
				_counter3 = 0;
				_staticimage = null;
			}
		}
	}
	
	public void attack3() {
		if(_attack3var == 0) {
			_yvelocity = -20;
		}
		_attack3 = true;
		_counter4 = 0;
		_subboss.attack3(_attack3var);
	}
	
	public void executeAttack3() {
		if(_attack3var == 0) {
			if(_counter4 == 10) {
				_yvelocity = 0;
				_staticimage = new Image("twinsboss/a3.png");
			}
			if(_counter4 >= 10 && _counter4 < 45) {
				TheGame._gc.drawImage(new Image("twinsboss/shots/pre3.png"), -24, 176);
				TheGame._gc.drawImage(new Image("twinsboss/shots/pre3.png"), 60, 376);
			}
			if(_counter4 >= 45 && _counter4 % 6 == 0 && _counter4 < 70) {
				Hitbox a = new HitboxImpl("ball", this, false, _subboss.getX()+_width, _subboss.getY()+15, 75, 75, 20, 0, 0, 1, new Image("twinsboss/shots/b1.png"));
				Hitbox b = new HitboxImpl("ball", this, false, _x - 75, _y+15, 75, 75, -20, 0, 0, 1, new Image("twinsboss/shots/a1.png"));
				a.setCircle(true);
				b.setCircle(true);
				a.setDissappearOnHit(false);
				b.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
			}
			if(_counter4 == 90) {
				_yvelocity = 20;
			}
			if(_counter4 == 100) {
				_yvelocity = 0;
				_attack3 = false;
				_counter3 = 0;
				_staticimage = null;
			}
		} else {
			if(_counter4 == 10) {
				_yvelocity = 0;
				_staticimage = new Image("twinsboss/a3.png");
			}
			if(_counter4 >= 10 && _counter4 < 45) {
				TheGame._gc.drawImage(new Image("twinsboss/shots/pre3.png"), 60, 176);
				TheGame._gc.drawImage(new Image("twinsboss/shots/pre3.png"), -24, 376);
			}
			if(_counter4 >= 45 && _counter4 % 6 == 0 && _counter4 < 70) {
				Hitbox a = new HitboxImpl("ball", this, false, _subboss.getX()+_width, _subboss.getY()+15, 75, 75, 20, 0, 0, 1, new Image("twinsboss/shots/b1.png"));
				Hitbox b = new HitboxImpl("ball", this, false, _x - 75, _y+15, 75, 75, -20, 0, 0, 1, new Image("twinsboss/shots/a1.png"));
				a.setCircle(true);
				b.setCircle(true);
				a.setDissappearOnHit(false);
				b.setDissappearOnHit(false);
				TheGame._attacks.add(a);
				TheGame._attacks.add(b);
			}
			if(_counter4 == 101) {
				_attack3 = false;
				_counter3 = 0;
				_staticimage = null;
			}
		}
	}
	
	public void changeForm() {
		if(_counter4 == 1){
		_subboss.changeForm();
		_width =  (int)(192*0.6);
		_height = (int)(86*0.6);
		_y+=(int)(106*0.6);
		_staticimage = new Image("twinsboss/adead.png");
		_xvelocity = -16;
		}
		if(_counter4 == 70) {
			_width = (int)(86*0.6);
			_height= (int)(192*0.6);
			_sprites.clear();
			_sprites.add(new Image("twinsboss/b1.png"));
			_sprites.add(new Image("twinsboss/b2.png"));
			_staticimage = null;
			_subboss = null;
			_xvelocity = 0;
			_y = -50;
			_x = 815;
		}
		if(_counter4 == 80) {
			_yvelocity = 20;
		}
		if(_counter4 >= 80 && _y>= 328) {
			_yvelocity = 0;
			_y = 328;
			_changeform = false;
			_counter3 = 0;
			_form2 = true;
		}
		
	}
	
	
	
	public void attack4() {
		_attack4 = true;
		_counter4 = 0;
		_staticimage = new Image("twinsboss/b4.png");
		
	}
	
	public void executeAttack4() {
		if(_counter4 >= 30 && _counter4 % 5 == 0 && _counter4 < 85) {
			Hitbox b = new HitboxImpl("ball", this, false, _x - 75, _y+15, 75, 75, -20, 0, 0, 1, new Image("twinsboss/shots/b1.png"));
			
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
		}
		if(_counter4 == 90) {
			_staticimage = null;
			_counter3 = 50;
			_attack4 = false;
		}
	}
	
	public void attack5() {
		_attack5 = true;
		_counter4 = 0;
		_yvelocity = -20;
	}
	
	public void executeAttack5() {
		if(_counter4 == 5) {
			_yvelocity = 0;
			_staticimage = new Image("twinsboss/b4.png");
		}
		if(_counter4 >= 25 && _counter4 % 6 == 0 && _counter4 < 80) {
			Hitbox b = new HitboxImpl("ball", this, false, _x - 75, _y+15, 75, 75, -20, -4, 0, 1, new Image("twinsboss/shots/b1.png"));
			
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			b = new HitboxImpl("ball", this, false, _x - 75, _y+15, 75, 75, -20, 4, 0, 1, new Image("twinsboss/shots/b1.png"));
			
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
		}
		if(_counter4 == 90) {
			_yvelocity = 20;
		}
		if(_counter4 == 95) {
			_yvelocity = 0;
			_staticimage = null;
			_counter3 = 50;
			_attack5 = false;
		}
		
	}
	
	public void attack6() {
		
	}
	
	public void executeAttack6() {
		
	}
}
