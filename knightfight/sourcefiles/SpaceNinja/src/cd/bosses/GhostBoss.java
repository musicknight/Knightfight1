package cd.bosses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cd.AnimatedHitbox;
import cd.Hitbox;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GhostBoss extends Boss {

	
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	private int _attack2var;
	private int _attack4stage;
	private List<Image> _shot = new ArrayList<Image>();

	public GhostBoss() {
		super(900, 600, "ghostboss");
		_health = 1000;
		_width =(int) (145 * 1.5);
		_height =(int)( 134 * 1.5);
		_staticimage = new Image("ghostboss/port2.png");
		_shot.add(new Image("ghostboss/shot/1.png"));
		_shot.add(new Image("ghostboss/shot/2.png"));
		_shot.add(new Image("ghostboss/shot/3.png"));
		_shot.add(new Image("ghostboss/shot/4.png"));
		_shot.add(new Image("ghostboss/shot/5.png"));
		_shot.add(new Image("ghostboss/shot/6.png"));
		_shot.add(new Image("ghostboss/shot/7.png"));
		_shot.add(new Image("ghostboss/shot/8.png"));
		_shot.add(new Image("ghostboss/shot/9.png"));
		_shot.add(new Image("ghostboss/shot/10.png"));
	}
	
	@Override
	public void render(GraphicsContext gc) {
		
		super.render(gc);
		if(_counter2 == 0 && !_dead) {
			if(_spawning) {
				TheGame.setText(new Image("ghostboss/text/1.png"));
			} else if(!_dead) {
				Random r = new Random();
				int i = r.nextInt(5) + 1;
				
				TheGame.setText(new Image("ghostboss/text/" + i + ".png"));
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
		if(_attack4) {
			executeAttack4();
		}
		
		if(_health == 0 && !_dead) {
			_dead = true;
			TheGame.setText(new Image("ghostboss/text/6.png"));
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_attack4 = false;
			_counter2 = 0;
			_staticimage = new Image("ghostboss/dead.png");
		}
		if(_dead) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_attack4 = false;
			if(_counter2 == 30) {
				_staticimage = new Image("ghostboss/port1.png");
				TheGame.playSound("/ghostboss/sounds/port1.wav");
			}
			if(_counter2 == 34) {
				_staticimage = new Image("ghostboss/port2.png");	
			}
			if(_counter2 == 38) {
				_staticimage = new Image("clear.png");
			}
		}
		if(_counter1 < 40 && !_dead) {
			_yvelocity = 0;
			if(_counter % 3 == 0){
			if(_counter1 < 20) {
				_y -=1;
			} else {
				_y += 1;
			}
			}
		} else {
			_counter1 = 0;
		}
		
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
			if(TheGame._character1.getLives() <= 0) {
				_won = true;
				_staticimage = new Image("ghostboss/won.png");
				TheGame.setText(new Image("ghostboss/text/won.png"));
			}
			if(_counter3 == 120 && !_won) {
				Random r = new Random();
				int i = r.nextInt(4);
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
					_attack2var = r.nextInt(3);
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
		_counter2 = 0;
		_x = 910;
		_y = 80;
		// resting _x is 607
		
	}
	
	public void executeSpawn() {
		if(_counter2 == 20) {
			_x = 607;
			_y = 80;
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter2 == 24) {
			_staticimage = new Image("ghostboss/port1.png");
			
		}
		if(_counter2 == 28) {
			_staticimage = new Image("ghostboss/1.png");
			_spawning = false;
		}
	}
	
	public void attack1() {
		_counter4 = 0;
		_attack1 = true;
		_immune = true;
		_staticimage = new Image("ghostboss/port1.png");
		TheGame.playSound("/ghostboss/sounds/port1.wav");
	}
	public void executeAttack1() {
		if(_counter4 == 4) {
			_staticimage = new Image("ghostboss/port2.png");	
		}
		if(_counter4 == 8) {
			_staticimage = new Image("clear.png");
		}
		if(_counter4 == 9) {
			_x = 0;
			_y = 250;
		}
		if(_counter4 == 18) {
			_staticimage = new Image("ghostboss/port2.png");
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter4 == 22) {
			_staticimage = new Image("ghostboss/port1.png");
		}
		if(_counter4 == 26) {
			_staticimage = new Image("ghostboss/1.png");
			_immune = false;
		}
		if(_counter4 >= 50 && _counter4 % 100 == 0) {
			Hitbox a = new AnimatedHitbox("spiral", this, false, 0, _y+ 50, 150, 150, 5, 0, 0, 1, _shot, 4);
			TheGame._attacks.add(a);
			TheGame.playSound("/ghostboss/sounds/long.wav");
		}
		if(_counter4 == 430) {
			_immune = true;
			_staticimage = new Image("ghostboss/port1.png");
			TheGame.playSound("/ghostboss/sounds/port1.wav");
		}
		if(_counter4 == 434) {
			_staticimage = new Image("ghostboss/port2.png");
		}
		if(_counter4 == 438) {
			_staticimage = new Image("clear.png");
		}
		if(_counter4 == 439) {
			_x = 607;
			_y = 80;
		}
		if(_counter4 == 442) {
			_staticimage = new Image("ghostboss/port2.png");
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter4 == 446) {
			_staticimage = new Image("ghostboss/port1.png");
		}
		if(_counter4 == 450) {
			_staticimage = new Image("ghostboss/1.png");
			_attack1 = false;
			_counter3 = 0;
			_immune = false;
		}
	}
	public void attack2() {
		_counter4 = 0;
		_attack2 = true;
		_immune = true;
		_staticimage = new Image("ghostboss/port1.png");
		TheGame.playSound("/ghostboss/sounds/port1.wav");
	}
	public void executeAttack2() {
		if(_counter4 == 4) {
			_staticimage = new Image("ghostboss/port2.png");	
		}
		if(_counter4 == 8) {
			_staticimage = new Image("clear.png");
		}
		if(_counter4 == 9) {
			_y = 160;
		}
		if(_counter4 == 18) {
			_staticimage = new Image("ghostboss/port2.png");
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter4 == 22) {
			_staticimage = new Image("ghostboss/port1.png");
		}
		if(_counter4 == 26) {
			_staticimage = new Image("ghostboss/1.png");
			_immune = false;
		}
		if(_counter4 >= 50 && _counter4 < 120) {
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 0, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 300, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 600, 290, 150, 150);
		}
		if(_counter4 == 120) {
			Hitbox a = new AnimatedHitbox("spiral", this, false, 0, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new AnimatedHitbox("spiral", this, false, 300, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			Hitbox c = new AnimatedHitbox("spiral", this, false, 600, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			c.setCircle(true);
			c.setDissappearOnHit(false);
			TheGame._attacks.add(c);
			TheGame.playSound("/ghostboss/sounds/short.wav");
		}
		if(_counter4 == 170) {
			TheGame.clearHitboxes("spiral", this);
		}
		if(_counter4 > 170 && _counter4 < 240) {
			int i = 0;
			int j = 0;
			int k = 0;
			if(_attack2var == 0) {
				i = 0;
				j = 150;
				k = 300;
				//gap at 450
			}
			if(_attack2var == 1) {
				i = 450;
				j = 150;
				k = 300;
				//gap at 0 
			}
			if(_attack2var == 2) {
				i = 450;
				j = 150;
				k = 0;
				//gap at 300
			}
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), i, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), j, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), k, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 600, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 750, 290, 150, 150);
		}
		if(_counter4 == 240) {
			int i = 0;
			int j = 0;
			int k = 0;
			if(_attack2var == 0) {
				i = 0;
				j = 150;
				k = 300;
				//gap at 450
			}
			if(_attack2var == 1) {
				i = 450;
				j = 150;
				k = 300;
				//gap at 0 
			}
			if(_attack2var == 2) {
				i = 450;
				j = 150;
				k = 0;
				//gap at 300
			}
			Hitbox a = new AnimatedHitbox("spiral", this, false, i, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new AnimatedHitbox("spiral", this, false, j, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			Hitbox c = new AnimatedHitbox("spiral", this, false, k, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			c.setCircle(true);
			c.setDissappearOnHit(false);
			TheGame._attacks.add(c);
			Hitbox d = new AnimatedHitbox("spiral", this, false, 600, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			d.setCircle(true);
			d.setDissappearOnHit(false);
			TheGame._attacks.add(d);
			Hitbox e = new AnimatedHitbox("spiral", this, false, 750, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			e.setCircle(true);
			e.setDissappearOnHit(false);
			TheGame._attacks.add(e);
			TheGame.playSound("/ghostboss/sounds/short.wav");
		}
		if(_counter4 == 290) {
			TheGame.clearHitboxes("spiral", this);
		}
		if(_counter4 > 290 && _counter4 < 360) {
			int i = 0;
			int j = 0;
			int k = 0;
			if(_attack2var == 0) {
				i = 450;
				j = 150;
				k = 300;
				//gap at 0
			}
			if(_attack2var == 1) {
				i = 450;
				j = 150;
				k = 0;
				//gap at 300 
			}
			if(_attack2var == 2) {
				i = 300;
				j = 150;
				k = 0;
				//gap at 450
			}
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), i, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), j, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), k, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 600, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 750, 290, 150, 150);
		}
		if(_counter4 == 360) {
			int i = 0;
			int j = 0;
			int k = 0;
			if(_attack2var == 0) {
				i = 450;
				j = 150;
				k = 300;
				//gap at 0
			}
			if(_attack2var == 1) {
				i = 450;
				j = 150;
				k = 0;
				//gap at 300 
			}
			if(_attack2var == 2) {
				i = 300;
				j = 150;
				k = 0;
				//gap at 450
			}
			Hitbox a = new AnimatedHitbox("spiral", this, false, i, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new AnimatedHitbox("spiral", this, false, j, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			Hitbox c = new AnimatedHitbox("spiral", this, false, k, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			c.setCircle(true);
			c.setDissappearOnHit(false);
			TheGame._attacks.add(c);
			Hitbox d = new AnimatedHitbox("spiral", this, false, 600, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			d.setCircle(true);
			d.setDissappearOnHit(false);
			TheGame._attacks.add(d);
			Hitbox e = new AnimatedHitbox("spiral", this, false, 750, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			e.setCircle(true);
			e.setDissappearOnHit(false);
			TheGame._attacks.add(e);
			TheGame.playSound("/ghostboss/sounds/short.wav");
		}
		if(_counter4 == 410) {
			TheGame.clearHitboxes("spiral", this);
		}
		if(_counter4>410 && _counter4 < 480) {
			int i = 0;
			int j = 0;
			int k = 0;
			if(_attack2var == 0) {
				i = 0;
				j = 150;
				k = 450;
				//gap at 300
			}
			if(_attack2var == 1) {
				i = 0;
				j = 150;
				k = 300;
				//gap at 450
			}
			if(_attack2var == 2) {
				i = 450;
				j = 150;
				k = 300;
				//gap at 0
			}
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), i, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), j, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), k, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 600, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 750, 290, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 0, 140, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 150, 140, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 300, 140, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 450, 140, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 600, 140, 150, 150);
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 750, 140, 150, 150);
		}
		if(_counter4 == 480) {
			int i = 0;
			int j = 0;
			int k = 0;
			if(_attack2var == 0) {
				i = 0;
				j = 150;
				k = 450;
				//gap at 300
			}
			if(_attack2var == 1) {
				i = 0;
				j = 150;
				k = 300;
				//gap at 450
			}
			if(_attack2var == 2) {
				i = 450;
				j = 150;
				k = 300;
				//gap at 0
		
			}
			Hitbox a = new AnimatedHitbox("spiral", this, false, i, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			a.setCircle(true);
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			Hitbox b = new AnimatedHitbox("spiral", this, false, j, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			b.setCircle(true);
			b.setDissappearOnHit(false);
			TheGame._attacks.add(b);
			Hitbox c = new AnimatedHitbox("spiral", this, false, k, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			c.setCircle(true);
			c.setDissappearOnHit(false);
			TheGame._attacks.add(c);
			Hitbox d = new AnimatedHitbox("spiral", this, false, 600, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			d.setCircle(true);
			d.setDissappearOnHit(false);
			TheGame._attacks.add(d);
			Hitbox e = new AnimatedHitbox("spiral", this, false, 750, 290, 150, 150, 0, 0, 0, 1, _shot, 4);
			e.setCircle(true);
			e.setDissappearOnHit(false);
			TheGame._attacks.add(e);
			Hitbox ab = new AnimatedHitbox("spiral", this, false, 0, 140, 150, 150, 0, 0, 0, 1, _shot, 4);
			ab.setCircle(true);
			ab.setDissappearOnHit(false);
			TheGame._attacks.add(ab);
			Hitbox bb = new AnimatedHitbox("spiral", this, false, 150, 140, 150, 150, 0, 0, 0, 1, _shot, 4);
			bb.setCircle(true);
			bb.setDissappearOnHit(false);
			TheGame._attacks.add(bb);
			Hitbox cb = new AnimatedHitbox("spiral", this, false, 300, 140, 150, 150, 0, 0, 0, 1, _shot, 4);
			cb.setCircle(true);
			cb.setDissappearOnHit(false);
			TheGame._attacks.add(cb);
			Hitbox db = new AnimatedHitbox("spiral", this, false, 600, 140, 150, 150, 0, 0, 0, 1, _shot, 4);
			db.setCircle(true);
			db.setDissappearOnHit(false);
			TheGame._attacks.add(db);
			Hitbox eb = new AnimatedHitbox("spiral", this, false, 750, 140, 150, 150, 0, 0, 0, 1, _shot, 4);
			eb.setCircle(true);
			eb.setDissappearOnHit(false);
			TheGame._attacks.add(eb);
			Hitbox fb = new AnimatedHitbox("spiral", this, false, 450, 140, 150, 150, 0, 0, 0, 1, _shot, 4);
			fb.setCircle(true);
		    fb.setDissappearOnHit(false);
			TheGame._attacks.add(fb);
			TheGame.playSound("/ghostboss/sounds/short.wav");
		}
		if(_counter4 == 530) {
			TheGame.clearHitboxes("spiral", this);
		}
		if(_counter4 == 530) {
			_immune = true;
			_staticimage = new Image("ghostboss/port1.png");
			TheGame.playSound("/ghostboss/sounds/port1.wav");
		}
		if(_counter4 == 534) {
			_staticimage = new Image("ghostboss/port2.png");
		}
		if(_counter4 == 538) {
			_staticimage = new Image("clear.png");
		}
		if(_counter4 == 539) {
			_x = 607;
			_y = 80;
		}
		if(_counter4 == 542) {
			_staticimage = new Image("ghostboss/port2.png");
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter4 == 546) {
			_staticimage = new Image("ghostboss/port1.png");
		}
		if(_counter4 == 550) {
			_staticimage = new Image("ghostboss/1.png");
			_attack2 = false;
			_counter3 = 0;
			_immune = false;
		}
	}
	public void attack3() {
		_attack3 = true;
		_counter4 = 0;
		_immune = true;
		_staticimage = new Image("ghostboss/port1.png");
		TheGame.playSound("/ghostboss/sounds/port1.wav");
	}
	public void executeAttack3() {
		if(_counter4 == 4) {
			_staticimage = new Image("ghostboss/port2.png");	
		}
		if(_counter4 == 8) {
			_staticimage = new Image("clear.png");
		}
		if(_counter4 >= 30 && _counter4 < 100) {
			TheGame._gc.drawImage(new Image("ghostboss/shot/pre.png"), 0, 60, 900, 382);
		}
		if(_counter4 == 100) {
			Hitbox fb = new AnimatedHitbox("spiral", this, false, 0, 60, 900, 382, 0, 0, 0, 1, _shot, 3);
			fb.setCircle(true);
		    fb.setDissappearOnHit(false);
			TheGame._attacks.add(fb);
			TheGame.playSound("/ghostboss/sounds/short.wav");
		}
		if(_counter4 == 170) {
			TheGame.clearHitboxes("spiral", this);
		}
		if(_counter4 == 179) {
			_x = 607;
			_y = 80;
		}
		if(_counter4 == 180) {
			_staticimage = new Image("ghostboss/port2.png");
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter4 == 184) {
			_staticimage = new Image("ghostboss/port1.png");
		}
		if(_counter4 == 188) {
			_staticimage = new Image("ghostboss/1.png");
			_attack3 = false;
			_counter3 = 0;
			_immune = false;
		}
	}
	
	public void attack4() {
		_attack4 = true;
		_counter4 = 0;
		_immune = true;
		_staticimage = new Image("ghostboss/port1.png");
		TheGame.playSound("/ghostboss/sounds/port1.wav");
	}
	
	public void executeAttack4() {
		
	
		if(_counter4 == 0) {
			_staticimage = new Image("ghostboss/port1.png");
			TheGame.playSound("/ghostboss/sounds/port1.wav");
		}
		if(_counter4 == 4) {
			_staticimage = new Image("ghostboss/port2.png");	
		}
		if(_counter4 == 8) {
			_staticimage = new Image("clear.png");
		}
		if(_counter4 == 9) {
			if(_attack4stage == 0){
			_x = 50;
			}
			if(_attack4stage == 1) {
				_x = 607;
				_y = 250;
			}
			if(_attack4stage == 2) {
				_x =  330;
				_y = 80;
			}
			if(_attack4stage == 3) {
				_x = 10;
				_y = 250;
			}
			if(_attack4stage == 4) {
				_x = 607;
				_y = 80;
			}
			if(_attack4stage == 5) {
				_x = 330;
				_y = 250;
			}
			if(_attack4stage == 6) {
				_x = 607;
				_y = 80;
			}
			
		}
		if(_counter4 == 18) {
			_staticimage = new Image("ghostboss/port2.png");
			TheGame.playSound("/ghostboss/sounds/port2.wav");
		}
		if(_counter4 == 22) {
			_staticimage = new Image("ghostboss/port1.png");
		}
		if(_counter4 == 26) {
			_staticimage = new Image("ghostboss/1.png");
			_immune = false;
			if(_attack4stage == 6) {
				_attack4 = false;
				_counter3 = 0;
				_attack4stage = 0;
			}
		}
		if(_counter4 == 46) {
			if(_attack4stage == 0) {
				Hitbox fb = new AnimatedHitbox("spiral", this, false, _x+85, _y+100, 60, 60, 5, 3, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
			}
			if(_attack4stage == 1) {
				Hitbox fb = new AnimatedHitbox("spiral", this, false, _x+85, _y+100, 60, 60, -6, 0, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
			}
			if(_attack4stage == 2) {
				Hitbox fb = new AnimatedHitbox("spiral", this, false, _x+85, _y+100, 60, 60, 0, 6, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
			}
			if(_attack4stage == 3) {
				Hitbox fb = new AnimatedHitbox("spiral", this, false, _x+85, _y+100, 60, 60, 6, 0, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
			}
			if(_attack4stage == 4) {
				Hitbox fb = new AnimatedHitbox("spiral", this, false, _x+85, _y+100, 60, 60, -5, 3, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
			}
			if(_attack4stage == 5) {
				Hitbox fb = new AnimatedHitbox("spiral", this, false, -59, _y+100, 60, 60, 6, 0, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
				fb = new AnimatedHitbox("spiral", this, false, 899, _y+100, 60, 60, -6, 0, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
				fb = new AnimatedHitbox("spiral", this, false, 0, 0, 60, 60, 5, 3, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
				fb = new AnimatedHitbox("spiral", this, false, 899, 0, 60, 60, -5, 3, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
				fb = new AnimatedHitbox("spiral", this, false,420, 0, 60, 60, 0, 6, 0, 1, _shot, 3);
				fb.setCircle(true);
			    fb.setDissappearOnHit(false);
				TheGame._attacks.add(fb);
			}
			TheGame.playSound("/ghostboss/sounds/short.wav");
		}
		if(_counter4 == 66) {
			_attack4stage++;
			_counter4 = -1;
		}
	}
}
