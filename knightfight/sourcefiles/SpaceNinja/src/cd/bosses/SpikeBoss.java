package cd.bosses;

import java.util.ArrayList;
import java.util.Random;

import cd.CharLinkedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpikeBoss extends Boss{

	private Hitbox _body = new CharLinkedHitbox("rockbody", this, 0, 1);
	private int _attack2var;
	private int _attack4var;
	private int _repeat1;
	private int _repeat2;
	private int _repeat3;
	private int _repeat4;
	private boolean _spritesswitched;
	private boolean _spritesswitched2;
	private boolean _spritesswitched1;
	private int _repeatno1;
	private int _repeatno2;
	private int _repeatno3;
	private int _repeatno4;
	
	public SpikeBoss() {
		super(900, 600, "spikeboss");
		_health = 1000;
		_staticimage = null;
		_circle = true;
		_sprites = new ArrayList<Image>();
		_sprites.add(new Image("spikeboss/full1.png"));
		_sprites.add(new Image("spikeboss/full2.png"));
		_sprites.add(new Image("spikeboss/full3.png"));
		_sprites.add(new Image("spikeboss/full4.png"));
		_sprites.add(new Image("spikeboss/full5.png"));
		_sprites.add(new Image("spikeboss/full6.png"));
		_rate = 4;
		_width = 200;
		_height = 200;
		_body = new CharLinkedHitbox("spikebody", this, 0, 1);
		_body.setCircle(true);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_counter2 == 0 && !_won) {
			if(_health >= 300) {
				TheGame.setText(new Image("spikeboss/text/1.png"));
			} else if(_health > 0) {
				TheGame.setText(new Image("spikeboss/text/2.png"));
			} else {
				TheGame.setText(new Image("spikeboss/text/3.png"));
			}
			
		}
		if(_counter2 == 150) {
			TheGame.stopText();
			_counter2 = -500;
		}
		if(TheGame._character1.getLives() <= 0) {
			_won = true;
		}
		if(!TheGame._attacks.contains(_body) && _health > 0) {
			TheGame._attacks.add(_body);
		}
		if(_health == 0 && !_dead) {
			_attack1 = false;
			_attack2 = false;
			_attack3 = false;
			_counter2 = -1;
			_dead = true;
			_rate = 8;
			_sprites.clear();
			_sprites.add(new Image("spikeboss/dead/1.png"));
			_sprites.add(new Image("spikeboss/dead/2.png"));
			_sprites.add(new Image("spikeboss/dead/3.png"));
			_sprites.add(new Image("spikeboss/dead/4.png"));
			_sprites.add(new Image("spikeboss/dead/5.png"));
			_sprites.add(new Image("spikeboss/dead/6.png"));
		}
		if(_dead) {
			
			_xvelocity = 0;
			_yvelocity = 2;
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
		if(!_spritesswitched && _health < 300) {
			_sprites.clear();
			_sprites.add(new Image("spikeboss/hurt/1.png"));
			_sprites.add(new Image("spikeboss/hurt/2.png"));
			_sprites.add(new Image("spikeboss/hurt/3.png"));
			_sprites.add(new Image("spikeboss/hurt/4.png"));
			_sprites.add(new Image("spikeboss/hurt/5.png"));
			_sprites.add(new Image("spikeboss/hurt/6.png"));
			_counter2 = -1;
			_spritesswitched = true;
		}
		if(_attack1 || _attack2 || _attack3 || _attack4 || _attack5) {
			if(_rate == 1 && _counter3 % 10 == 0) {
				TheGame.playSound("/spikeboss/sounds/1spin.wav");
			}
		}
		if(!_attack1 && !_attack2 && !_attack3 && !_attack4 && !_attack5 && !_dead) {
		if(_counter1 < 40) {
			_yvelocity = 0;
			if(_counter % 3 == 0){
			if(_counter1 < 20) {
				_y -=2;
			} else {
				_y += 2;
			}
			}
		} else {
			_counter1 = 0;
		}
		if(_won){
			if(!_spritesswitched1) {
				_sprites.clear();
				_sprites.add(new Image("spikeboss/won/1.png"));
				_sprites.add(new Image("spikeboss/won/2.png"));
				_sprites.add(new Image("spikeboss/won/3.png"));
				_sprites.add(new Image("spikeboss/won/4.png"));
				_sprites.add(new Image("spikeboss/won/5.png"));
				_sprites.add(new Image("spikeboss/won/6.png"));
				_spritesswitched1 = true;
			}
		}
		if(_counter3 == 120 && !_won) {
			Random r = new Random();
			int i = r.nextInt(4);
			_counter3 = 0;
			
			if(_repeat1 == 2) {
				i = 3;
			}
			if(_repeat2 == 2) {
				i = 1;
			}
			if(_repeat3 == 2) {
				i = 0;
			}
			if(_repeat4 == 2) {
				i = 2;
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
				_attack2var = r.nextInt(2);
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
				_attack4var = r.nextInt(2);
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
		_y = 110;
		// resting _x is 607
		_xvelocity = -3;
		
	}
	
	public void executeSpawn(){
		if(_counter2 == 100) {
			_xvelocity = 0;
			_spawning = false;
			System.out.println(_x);
		}
	}
	
	public void attack1() {
		_rate = 1;
		_attack1 = true;
		_counter4 = -15;
	}
	public void executeAttack1() {
		if(_counter4 > 20 && _counter4 % 5 == 0) {

				TheGame.playSound("/spikeboss/sounds/shot.wav");
			Hitbox a = new HitboxImpl("spike", this, false, _x+23, _y+112, 44, 44, -15, 15, 0, 1, new Image("spikeboss/spikes/downl.png"));
			TheGame._attacks.add(a);
			if(_health < 300) {
				Hitbox b = new HitboxImpl("spike", this, false, _x+100-24, _y+150, 48, 37, 0, 21, 0, 1, new Image("spikeboss/spikes/down.png"));
				TheGame._attacks.add(b);
				Hitbox c = new HitboxImpl("spike", this, false, _x+30, _y+80, 37, 48, -21, -5, 0, 1, new Image("spikeboss/spikes/left.png"));
				TheGame._attacks.add(c);
				
			}
		}
		if(_counter4 == 80) {
			_xvelocity = -20;
		}
		if(_counter4 == 103) {
			_xvelocity = 0;
		}
		if(_counter4 == 108) {
			_xvelocity = 10;
		}
		if(_counter4 >= 108 && _x >= 607) {
			_xvelocity = 0;
			_x = 607;
			_rate = 4;
			_attack1 = false;
			_counter3 = 0;
		}
	}
	public void attack2() {
		_rate = 1;
		_xvelocity = -20;
		_counter4 = 0;
		_attack2 = true;
		_counter1 = 55;
	}
	public void executeAttack2() {
		int i = 20;
		if(_counter1 > 15 && _counter1 < 55 && _counter1 % 4 == 0) {

				TheGame.playSound("/spikeboss/sounds/shot.wav");
			
			Hitbox a = new HitboxImpl("spike", this, false, _x+100-24, _y+160, 48, 37, 0, 15, 0, 1, new Image("spikeboss/spikes/down.png"));
			TheGame._attacks.add(a);
			if(_health < 300) {
				Hitbox b = new HitboxImpl("spike", this, false, _x+23, _y+112, 44, 44, -15, 15, 0, 1, new Image("spikeboss/spikes/downl.png"));
				TheGame._attacks.add(b);
				Hitbox c = new HitboxImpl("spike", this, false, _x+124, _y+112, 44, 44, 15, 15, 0, 1, new Image("spikeboss/spikes/downr.png"));
				TheGame._attacks.add(c);
			}
		}
		if(_attack2var == 0){
		if(_counter4 == 30) {
			_xvelocity = 0;
			_counter1 = 0;
		}
		if(_counter4 == 90) {
			_xvelocity = i;
		}
		if(_counter4 == 100) {
			_xvelocity = 0;
			_counter1 = 0;
		}
		if(_counter4 == 160) {
			_xvelocity = i;
		}
		if(_counter4 == 185) {
			_xvelocity = 0;
			_counter1 = 0;
		}
		if(_counter4 == 245) {
			_xvelocity = -i;
		}
		if(_counter4 == 260) {
			_xvelocity = 0;
			_counter1 = 0;
		}
		if(_counter4 == 320) {
			_xvelocity = -i;
		}
		if(_counter4 == 330) {
			_xvelocity = 0;
			_counter1 = 0;
		}
		if(_counter4 == 390) {
			_xvelocity = 10;
			_rate = 4;
		}
		if(_counter4 >= 390 && _x >= 607) {
			_xvelocity = 0;
			_x = 607;
			_attack2 = false;
			_counter3 = 0;
		}
		}
		if(_attack2var == 1) {
			if(_counter4 == 15) {
				_xvelocity = 0;
				_counter1 = 0;
			}
			if(_counter4 == 75) {
				_xvelocity = i;
			}
			if(_counter4 == 85) {
				_xvelocity = 0;
				_counter1 = 0;
			}
			if(_counter4 == 145) {
				_xvelocity = -i;
			}
			if(_counter4 == 165) {
				_xvelocity = 0;
				_counter1 = 0;
			}
			if(_counter4 == 225) {
				_xvelocity = i;
			}
			if(_counter4 == 235) {
				_xvelocity = 0;
				_counter1 = 0;
			}
			if(_counter4 == 295) {
				_xvelocity = i;
			}
			if(_counter4 == 310) {
				_xvelocity = 0;
				_counter1 = 0;
			}
			if(_counter4 == 370) {
				_xvelocity = -i;
			}
			if(_counter4 == 383) {
				_xvelocity = 0;
				_counter1 = 0;
			}
			if(_counter4 == 443) {
				_rate = 4;
				_xvelocity = 10;
			}
			if(_counter4 >= 443 && _x >= 607) {
				_xvelocity = 0;
				_x = 607;
				_attack2 = false;
				_counter3 = 0;
			}
			
		}
		
		
		
	}
	
	public void attack3() {
		_attack3 = true;
		_yvelocity = 8;
		_xvelocity = 6;
		_counter4 = 0;
	}
	
	public void executeAttack3() {
		if(_counter4 == 22) {
			_yvelocity = 0;
			_xvelocity = 0;
			_rate = 1;
		}
		if(_counter4 == 62) {
			_xvelocity = -10;
			TheGame.playSound("/spikeboss/sounds/dash.wav");
		}
		if(_counter4 == 85) {
			_xvelocity = -30;
		}
		if(_health >= 300){
		if(_counter4 == 120) {
			_y = 110;
			_xvelocity = 30;
			TheGame.playSound("/spikeboss/sounds/dash2.wav");
		}
		if(_counter4 > 120 && _x >= 607) {
			_x = 607;
			_rate = 4;
			_xvelocity = 0;
			_counter3 = 0;
			_attack3 = false;
		}
		} else {
			if(_counter4 == 120) {
				_xvelocity = 30;
				TheGame.playSound("/spikeboss/sounds/dash2.wav");
			}
			if(_counter4 == 190) {
				_xvelocity = -30;
				TheGame.playSound("/spikeboss/sounds/dash2.wav");
			}
			if(_counter4 == 260) {
				_y = 110;
				_xvelocity = 30;
				TheGame.playSound("/spikeboss/sounds/dash2.wav");
			}
			if(_counter4 >= 260 && _x >=607) {
				_x = 607;
				_rate = 4;
				_xvelocity = 0;
				_counter3 = 0;
				_attack3 = false;
			}
		}
	}
	
	public void attack4() {
		_xvelocity = -10;
		_attack4 = true;
		_counter4 = 0;
	}
	
	public void executeAttack4() {
		if(_counter4 == 25) {
			_xvelocity = 0;
			_rate = 1;
		}
		if(_counter4 >= 40 && _counter4 % 4 == 0 && _counter4 < 250) {

				TheGame.playSound("/spikeboss/sounds/shot.wav");
			
			Hitbox a = new HitboxImpl("spike", this, false, _x+100-24, _y+160, 48, 37, 0, 15, 0, 1, new Image("spikeboss/spikes/down.png"));
			TheGame._attacks.add(a);
			
				Hitbox b = new HitboxImpl("spike", this, false, _x+23, _y+112, 44, 44, -25, 15, 0, 1, new Image("spikeboss/spikes/downl.png"));
				TheGame._attacks.add(b);
				Hitbox c = new HitboxImpl("spike", this, false, _x+124, _y+112, 44, 44, 25, 15, 0, 1, new Image("spikeboss/spikes/downr.png"));
				TheGame._attacks.add(c);
		}
		if(_counter4 == 60) {
			if(_attack4var == 0) {
				_xvelocity = -6;
			} else {
				_xvelocity = 6;
			}
		}
		if(_counter4 == 110) {
			_xvelocity = 0;
		}
		if(_counter4 == 130) {
			if(_attack4var == 1) {
				_xvelocity = -6;
			} else {
				_xvelocity = 6;
			}
		}
		if(_counter4 == 230) {
			_xvelocity = 0;
			System.out.println(_x);
		}
		if(_counter4 == 250) {
			_rate = 4;
			if(_attack4var == 0){
			_xvelocity = -10;
			} else {
				_xvelocity = 10;
			}
		}
		if(_attack4var == 0){
		if(_counter4 >= 250 && _x < 607) {
			_x = 607;
			_attack4 = false;
			_xvelocity = 0;
			_counter3 = 0;
		}
		} else {
			if(_counter4 >= 250 && _x > 607) {
				_x = 607;
				_attack4 = false;
				_xvelocity = 0;
				_counter3 = 0;
			}
		}
		
	}
	
	

}
