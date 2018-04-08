package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.ExplodingHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.MeleeHitbox;
import kf.Platform;
import kf.TheGame;

public class GengarChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private int _cd3;
	private double _charge;
	private ExplodingHitbox _gunk = new ExplodingHitbox("gunk", this, false, 0, 0, 0, 0, 0, 0, 0, 0, new Image("gengar/gunk1.png"), new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25));
	private boolean _gunkex = false;
	private int _gunkcounter;
	private int _ulty = 41;
	
	

	public GengarChar(String ID) {
		super(ID);
		if (_facing.equals("right")) {
			_image = new Image("gengar/gengar.png");
		} else {
			_image = new Image("gengar/gengarleft.png");
		}
		_width = 80;
		_height = 90;
		_speedfactor = 0.85;
		_damagefactor = 0.75;
		
	}

	@Override 
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku) {
			if (_facing.equals("right")) {
				_image = new Image("gengar/gengar.png");
			} else {
				_image = new Image("gengar/gengarleft.png");
			}
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
		if(_attacku) {
			executeAttackU();
		}
		if(_gunkex){
		for(Platform p : TheGame.getPlatforms()){
			if(_gunk.getX() > p.getX() && _gunk.getX() < (p.getX() + p.getWidth())|| _gunk.isGone() && _counter >5){
			if(_gunk.getY() + _gunk.getHeight() >= p.getY() || _gunk.isGone() && _counter >5) {
			_gunk.explode();
			_gunkex = false;
			_gunkcounter = 0;
			TheGame.playSound("/gengar/sounds/gengar1.wav");
		}
		}
		}
		}
		if(_gunkcounter == 30) {
			
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("gunk2") && a.getCharacter().equals(this)) {
					remove.add(a);
					
					
				}
		
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
		}
	}
	
	
	@Override
	public Image getStockImage() {
		
		return new Image("gengar/stock.png");
	}

	@Override
	public void attack1() {
		boolean b = true;
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals("shadowball") && a.getCharacter().equals(this)) {
				b = false;
			}
			
		}
		if(b){
		_xvelocity = 0;
		if(!_attack1 && _charge<30) {
			_attack1 = true;
			if (_facing.equals("right")) {
				_image = new Image("gengar/ball1.png");
			} else {
				_image = new Image("gengar/ball1left.png");
			}
			_counter = 0;
			_canact = false;
			
		} else if ( _charge < 8) {
			_attack1 = false;
			_canact = true;
			Hitbox attack;
			if (_facing.equals("right")) {
				attack = new HitboxImpl("shadowball", this, false, _x + 80, _y + 35, 35, 20, 23, 0, 3, _charge * 1.5, new Image("gengar/ballw.png"));
			} else {
				attack = new HitboxImpl("shadowball", this, false, _x - 35, _y + 35, 35, 20, -23, 0, 3, _charge * 1.5, new Image("gengar/ballwleft.png"));
			}
			TheGame._attacks.add(attack);
			_charge = 0;
			TheGame.playSound("/gengar/sounds/gengar1.wav");
		}else if ( _charge < 17) {
			_attack1 = false;
			_canact = true;
			Hitbox attack;
			if (_facing.equals("right")) {
				attack = new HitboxImpl("shadowball", this, false, _x + 80, _y + 30, 60, 30, 18, 0, 10, _charge * 1.5, new Image("gengar/ballm.png"));
			} else {
				attack = new HitboxImpl("shadowball", this, false, _x - 60, _y + 30, 60, 30, -18, 0, 10, _charge * 1.5, new Image("gengar/ballmleft.png"));
			}
			TheGame._attacks.add(attack);
			_charge = 0;
			TheGame.playSound("/gengar/sounds/gengar1.wav");
		}else {
			_attack1 = false;
			_canact = true;
			Hitbox attack;
			if (_facing.equals("right")) {
				_image = new Image("gengar/maxcharge.png");
				attack = new HitboxImpl("shadowball", this, false, _x + 80, _y + 15, 120, 60, 16, 0, 25, _charge * 1.7, new Image("gengar/balls.png"));
			} else {
				_image = new Image("gengar/maxchargeleft.png");
				attack = new HitboxImpl("shadowball", this, false, _x - 120, _y + 15, 120, 60, -16, 0, 25, _charge * 1.7, new Image("gengar/ballsleft.png"));
			}
			TheGame._attacks.add(attack);
			_charge = 0;
			TheGame.playSound("/gengar/sounds/gengar1.wav");
		}
		}
		
		
	}
	public void executeAttack1() {
		if(_xtumbling) {
			_charge = 0;
			_canact = true;
			_attack1 = false;
		}
		
		if(_charge < 17) {
		_charge = _charge + 0.5;
		} else {
			if (_facing.equals("right")) {
				_image = new Image("gengar/maxcharge.png");
			} else {
				_image = new Image("gengar/maxchargeleft.png");
			}
		}
		if(_charge == 17) {
			_attack1 = false;
			_canact = true;
			Hitbox attack;
			if (_facing.equals("right")) {
				_image = new Image("gengar/maxcharge.png");
				attack = new HitboxImpl("shadowball", this, false, _x + 80, _y + 15, 120, 60, 16, 0, 25, _charge * 1.7, new Image("gengar/balls.png"));
			} else {
				_image = new Image("gengar/maxchargeleft.png");
				attack = new HitboxImpl("shadowball", this, false, _x - 120, _y + 15, 120, 60, -16, 0, 25, _charge * 1.7, new Image("gengar/ballsleft.png"));
			}
			TheGame._attacks.add(attack);
			_charge = 0;
			TheGame.playSound("/gengar/sounds/gengar1.wav");
		}
		
	
		
		
	}

	@Override
	public void attack2() {
		_attack2 = true;
		_counter = 0;
		_xvelocity = 0;
		_canact = false;
		if (_facing.equals("right")) {
			_image = new Image("gengar/lick.png");
		} else {
			_image = new Image("gengar/lickleft.png");
		}
		
	}
	
	public void executeAttack2() {
		if(_counter == 3) {
			if (_facing.equals("right")) {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x+80, _y+58, 32, 32, 15, 10, new Image("gengar/lick1.png") ));
			} else {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x - 32, _y+58, 32, 32, 15, 10, new Image("gengar/lick1.png") ));
			}
			TheGame.playSound("/gengar/sounds/gengar2.wav");
			
		}
		if(_counter == 6) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("lick") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			if (_facing.equals("right")) {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x+80, _y+50, 32, 40, 15, 8, new Image("gengar/lick2.png") ));
			} else {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x - 32, _y+50, 32, 40, 15, 8, new Image("gengar/lick2.png") ));
			}
		}
		if(_counter == 9) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("lick") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			if (_facing.equals("right")) {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x+80, _y+42, 32, 48, 15, 8, new Image("gengar/lick3.png") ));
			} else {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x - 32, _y+42, 32, 48, 15, 8, new Image("gengar/lick3.png") ));
			}
		}
		if(_counter == 12) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("lick") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			if (_facing.equals("right")) {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x+80, _y+34, 32, 56, 15, 8, new Image("gengar/lick4.png") ));
			} else {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x - 32, _y+34, 32, 56, 15, 8, new Image("gengar/lick4.png") ));
			}
		}
		if(_counter == 15) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("lick") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			if (_facing.equals("right")) {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x+80, _y+26, 32, 64, 15, 8, new Image("gengar/lick5.png") ));
			} else {
				TheGame._attacks.add(new MeleeHitbox("lick", this, _x - 32, _y+26, 32, 64, 15, 8, new Image("gengar/lick5.png") ));
			}
		}
		if (_counter == 18) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("lick") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			_attack2 = false;
			_canact = true;
		}
	}

	@Override
	public void attack3() {
		
		
		if(_cd3 == 0){
		_attack3 = true;
		_counter = 0;
		_canact = false;
		_xvelocity = 0;
		_cd3 = 50;
		if (_facing.equals("right")) {
			_image = new Image("gengar/gunk.png");
		} else {
			_image = new Image("gengar/gunkleft.png");
		}
		}
		
		
	}
	
	public void executeAttack3() {
		if(_counter == 5) {
			double v;
			int d;
			if (_facing.equals("right")) {
				d = 80;
				v = 7;
			} else {
				d = -32;
				v = -7;
			}
			MeleeHitbox pgunk = new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25, new Image("gengar/gunk2.png"));
			pgunk.setHOrientation(true);
			_gunk = new ExplodingHitbox("gunk", this, true, _x+d, _y + 29, 32, 32, v, -15, 0, 0, new Image("gengar/gunk1.png"), pgunk);
			TheGame._attacks.add(_gunk);
			_canact = true;
			_attack3 = false;
			_gunkex = true;
			TheGame.playSound("/gengar/sounds/gengar3.wav");
		}
		
		
	}
	

	@Override
	public void attackU() {
		if(_ultcharge >= 0){
		_attacku = true;
		_canact = false;
		_counter = 0;
		_width = 0;
		_height = 0;
		_ultcharge = 0;
		}
		//TheGame._rendering = false;
		
		
		
	}

	public void executeAttackU() {
		_xvelocity = 0;
		_yvelocity = 0;
		_otherchar.setXVelocity(0);
		_otherchar.setYVelocity(0);
		_otherchar.setCanAct(false);
		double v;
		int x;
		Image i;
		if (_facing.equals("right")) {
			v = 25;
			x = 0;
			i = new Image("gengar/balls.png");
		} else {
			v = -25;
			x = 819;
			i = new Image("gengar/ballsleft.png");
		}
		if(_counter == 0) {
			TheGame._gc.drawImage(new Image("whitebox.jpg"), 0, 0, 1000, 1000);
			TheGame.playSound("/gengar/sounds/gengar5.wav");
		}
		if (_counter >= 1 && _counter < 31) {
			TheGame._gc.setFill(Color.BLACK);
			TheGame._gc.fillRect( 0, 0, 1000, 1000);
			
		}
		if(_counter == 400) {
			if (_facing.equals("right")) {
				
				x = 0;
			} else {
				
				x = 819;
			}
			Hitbox attack = new MeleeHitbox("flash", this, x, 0, 0, 0, 20, 0 );
			attack.setHOrientation(true);
			_otherchar.hit(attack);
			TheGame._gc.drawImage(new Image("whitebox.jpg"), 0, 0, 1000, 1000);
			_attacku = false;
			_canact = true;
			_width = 80;
			_height = 90;
			_otherchar.setCanAct(true);
			TheGame.playSound("/gengar/sounds/gengar5.wav");
		}
		if(_counter > 31  && _counter < 400) {
			TheGame._gc.setFill(Color.BLACK);
			TheGame._gc.fillRect( 0, 0, 1000, 1000);
			TheGame._gc.drawImage(new Image("gengar/face.png"), 110, 30, 680, 540);
			
		}
		if(_counter == 60) {
			TheGame.playSound("/gengar/sounds/gengar4.wav");
		}
		if(_counter >= 60 && _counter < 90) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty += 20;
			
		}
		if(_counter >= 90 && _counter < 120) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty -= 20;
			
		}
		if(_counter >= 120 && _counter < 150 ) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty += 20;
			
		}
		if(_counter >= 150 && _counter < 180 ) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty -= 20;
			
		}
		if(_counter >= 180 && _counter < 210) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty += 20;
			
		}
		if(_counter >= 210 && _counter < 240) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty -= 20;
			
		}
		if(_counter >= 240 && _counter < 270) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty += 20;
			
		}
		if(_counter >= 270 && _counter < 300) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty -= 20;
			
		}
		if(_counter >= 300 && _counter < 330) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty += 20;
			
		}
		if(_counter >= 330 && _counter < 360) {
			TheGame._attacks.add(new HitboxImpl("ultball", this, false, x, _ulty, 80, 40, v, 0, 0, 5, i));
			_ulty -= 20;
			
		}
		
		//System.out.println(_otherchar.getDamage() / 2);
		_otherchar.render(TheGame._gc);
		//TheGame._gc.clearRect(0, 0, 1000, 1000);
	}

	@Override
	public Color getColor() {
		return Color.PURPLE;
	}
	
	@Override 
	public void pressLeft() {
		if(!_attack1) {
			super.pressLeft();
		} else {
			_attack1 = false;
			_canact = true;
			super.pressLeft();
		}
	}
	@Override 
	public void pressRight() {
		if(!_attack1) {
			super.pressRight();
		} else {
			_attack1 = false;
			_canact = true;
			super.pressRight();
		}
	}
	@Override
	public void pressAttack1() {
		if(!_attack1) {
			super.pressAttack1();
		} else {
		attack1();
		}
	}
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		_gunkcounter++;
		if(_cd3 > 0) {
			_cd3--;
		}
	}
	@Override
	public void respawn() {
		super.respawn();
		_charge = 0;
		_attack1 = false;
		_attack2 = false;
		_attack3 = false;
	}
	

}
