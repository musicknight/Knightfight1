package cd.chars;

import java.util.ArrayList;
import java.util.List;

import cd.AnimatedHitbox;
import cd.Hitbox;
import cd.HitboxImpl;
import cd.MeleeHitbox;
import cd.OffsetHitbox;
import cd.TheGame;
import cd.bosses.Boss;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Ninja1Char extends CDCharacter {
	
	private int _cd1;
	private int _cd2;
	private boolean _attack1;
	private boolean _attack2;
	private String _skin;
	private int _starcd;
	private Hitbox _star;
	
	public Ninja1Char(String ID, String s) {
	super(ID);
	_width = 50;
	_height = 50;
	_skin = s;
	_speedfactor = 1.1;
	_damagefactor = 0;
	_spritecount = 4;
	_sprites.add(new Image("ninja/" + _skin + "/run1.png"));
	_sprites.add(new Image("ninja/" + _skin + "/run2.png"));
	_sprites.add(new Image("ninja/" + _skin + "/run3.png"));
	_sprites.add(new Image("ninja/" + _skin + "/run4.png"));
	if(_skin.equals("sprites") || _skin.equals("black")) {
		_starcd = 7;
	}
	if(_skin.equals("red")) {
		_starcd = 14;
	}
	if(_skin.equals("green")) {
		_starcd = 7;
		_lives = 4;
	}
	if(_skin.equals("yellow")) {
		_starcd = 5;
		_lives = 2;
		_speedfactor = 1.4;
	}
	if(_skin.equals("rock")) {
		_starcd = 20;
	}
	if(_skin.equals("rock2")) {
		_starcd = 20;
	}
	if(_skin.equals("spike")) {
		_starcd = 25;
	}
	if(_skin.equals("laser")) {
		_starcd = 25;
	}
	if(_skin.equals("dragon")) {
		_starcd = 15;
	}
	
	
	
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_attack1){
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_dying) {
			executeDeath();
		}
	}

	@Override
	public Image getStockImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attack1() {
		if(_cd1 == 0 && !_attack1){
		_attack1 = true;
		_counter = 0;
		}
	}
	
	public void executeAttack1() {
		if( _counter == 0 && !_skin.equals("spike")){
			_cd1 = _starcd;
			if(_skin.equals("sprites") || _skin.equals("yellow") || _skin.equals("black")){
				List<Image> i = new ArrayList<Image>();
				i.add(new Image("ninja/" + _skin + "/star1.png"));
				i.add(new Image("ninja/" + _skin + "/star2.png"));
				_star = new AnimatedHitbox("shuriken", this, false, _x+45, _y+25, 21, 21, 16, 0, 0, 10, i, 3);
				_attack1 = false;
				TheGame.playSound("/ninja/sounds/throw.wav");
			}
			if(_skin.equals("red")){
				List<Image> i = new ArrayList<Image>();
				i.add(new Image("ninja/" + _skin + "/star1.png"));
				i.add(new Image("ninja/" + _skin + "/star2.png"));
				_star = new AnimatedHitbox("shuriken", this, false, _x+45, _y+5, 42, 42, 9, 0, 0, 18, i, 4);
				_attack1 = false;
				TheGame.playSound("/ninja/sounds/throw.wav");
			}
			if(_skin.equals("green")){
				List<Image> i = new ArrayList<Image>();
				i.add(new Image("ninja/" + _skin + "/star1.png"));
				i.add(new Image("ninja/" + _skin + "/star2.png"));
				_star = new AnimatedHitbox("shuriken", this, false, _x+45, _y+25, 21, 21, 16, 0, 0, 7, i, 3);
				_attack1 = false;
				TheGame.playSound("/ninja/sounds/throw.wav");
			}
			if(_skin.equals("rock")) {
				_star = new HitboxImpl("shuriken", this, true, _x+45, _y+5, 40, 40, 8, -15, 0, 12, new Image("ninja/rock/star1.png"));
				_star.setBounces(true);
				_attack1 = false;
				TheGame.playSound("/rockboss/sounds/shot.wav");
			}
			if(_skin.equals("rock2")) {
				_star = new HitboxImpl("shuriken", this, true, _x+45, _y+5, 40, 40, 10, -20, 0, 13, new Image("ninja/rock2/star1.png"));
				_star.setBounces(true);
				_attack1 = false;
				TheGame.playSound("/rockboss/sounds/shot.wav");
			}
			if(_skin.equals("dragon")){
				_star = new HitboxImpl("shuriken", this, false, _x+45, _y-6, 62, 50, 9, 0, 0, 20, new Image("dragonboss/beam/shot2.png"));
				_attack1 = false;
				TheGame.playSound("/dragonboss/sounds/ball.wav");
			}
			if(_star != null){
			TheGame._attacks.add(_star);
			}
		}
		if(_skin.equals("spike") &&  _counter % 5 == 0) {
			TheGame._attacks.add(new HitboxImpl("shuriken", this, false, _x+45, _y+1, 37, 48, 15, 0, 0, 12, new Image("spikeboss/spikes/right.png")));
			TheGame.playSound("/spikeboss/sounds/shot.wav");
		}
		if(_counter == 10 && _skin.equals("spike")) {
			_attack1 = false;
			_cd1 = _starcd;
		}
		if(_counter >= 0 && _skin.equals("laser")) {
			makeHLaser((int)_counter, "laser");
		}
		if(_counter == 33 && _skin.equals("laser")){
			_attack1 = false;
			_cd1 = _starcd;
		}
		
	}

	@Override
	public void attack2() {
		if(_cd2 == 0) {
		_counter = 0;
		_canact = false;
		_attack2 = true;
		if(_skin.equals("black")){
		_immune = true;
		_spritecount = 1;
		_spriteindex = 0;
		_sprites.clear();
		_sprites.add(new Image("ninja/black/dash.png"));
		}
		_xvelocity = 18;
		_cd1 = 10;
		_cd2 = 50;
		_yvelocity = 0;
		_gravity = false;
		
		}
		
	}
	
	public void executeAttack2() {
		if(_counter == 10) {
			_xvelocity = 0;
			_canact = true;
			_immune = false;
			_gravity = true;
			_attack2 = false;
			if(_skin.equals("black")) {
				_spritecount = 4;
				_sprites.clear();
				_sprites.add(new Image("ninja/" + _skin + "/run1.png"));
				_sprites.add(new Image("ninja/" + _skin + "/run2.png"));
				_sprites.add(new Image("ninja/" + _skin + "/run3.png"));
				_sprites.add(new Image("ninja/" + _skin + "/run4.png"));
			}
		}
		
	}

	@Override
	public void attack3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackU() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd1>0) {
			_cd1--;
		}
		if(_cd2>0) {
			_cd2--;
		}
	}
	
	public void executeDeath() {
		_animate = false;
		_image = new Image("ninja/" + _skin + "/dead.png");
		if(_y == 392) {
			_x -= 15;
		}
	}
	
	@Override
	public void respawn() {
		
	}
	private void makeHLaser(int c, String s) {
		if(c == 0) {
			TheGame.playSound("/botboss/sounds/charge1.wav");
		}
		if(c < 20) {
			TheGame._gc.drawImage(new Image("botboss/lasers/hpre.png"), _x+26, _y-2, 900, 22);
		}
		if(c == 20) {
			Hitbox a = new OffsetHitbox(s, this, 26, -2, 900, 22, 0, 3, new Image("botboss/lasers/h1.png"));
			a.setDissappearOnHit(false);
			TheGame._attacks.add(a);
			TheGame.playSound("/botboss/sounds/shot.wav");
		}
		if(c == 33) {
			TheGame.clearHitboxes(s, this);
		}
	}

	@Override
	public Boss getSubBoss() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
