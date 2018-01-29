package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FrankChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _canattack3;
	private int _cd1;
	//counts how many shots from attack1 have been shot between cooldowns
	private int _timesshot = 0;
	
	
	public FrankChar(String ID) {
		super(ID);
		_width = 45;
		_height = 60;
		_speedfactor = 1;
		_damagefactor = 1;
		if(_facing.equals("right")) {
			_image = new Image("frank/frank.gif");
		} else {
			_image = new Image("frank/frankleft.gif");
		}
		
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku){
		_width = 45;
		_height = 60;
			if(_facing.equals("right")) {
			_image = new Image("frank/frank.gif");
		} else {
			_image = new Image("frank/frankleft.gif");
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
		if(_onplatform) {
			_canattack3 = true;
		}
	}

	@Override
	public Image getStockImage() {
		return new Image("frank/stock.gif");
	}

	@Override
	public void attack1() {
		
		if(_cd1 == 0){
			if(_timesshot == 0) {
				_timesshot = 1;
			} else if(_timesshot == 1) {
				_timesshot = 0;
				_cd1 = 18;
			} 
			_attack1 = true;
			_width = 75;
			_counter = 0;
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("frank/pellet1.gif");
				d = 75;
				v = 13;
			} else {
				_image = new Image("frank/pellet1left.gif");
				d = -16;
				v = -13;
			}
			TheGame._attacks.add(new HitboxImpl("pellet", this, false, _x+d, _y + 20, 16, 12, v, 0, 5,4, new Image("frank/pellet.png")));
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
	}
	
	public void executeAttack1() {
		if(_counter == 20 || _attack2 || _attack3 || _attacku) {
			
			_attack1 = false;
			_width = 45;
			return;
		}
		_width = 75;
		if(_facing.equals("right")) {
			_image = new Image("frank/pellet1.gif");
		} else {
			_image = new Image("frank/pellet1left.gif");
		}
	}

	@Override
	public void attack2() {
		_counter = 0;
		_width = 60;
		_canact = false;
		_xvelocity = 0;
		_attack2 = true;
		if(_facing.equals("right")) {
			_image = new Image("frank/punch1.gif");
		} else {
			_image = new Image("frank/punch1left.gif");
		}
	}
	
	public void executeAttack2() {
		if(_counter == 10) {
			_width = 75;
			int x;
			if(_facing.equals("right")) {
				_xvelocity = 14;
				_image = new Image("frank/punch2.gif");
				x=51;
			} else {
				_xvelocity = -14;
				_image = new Image("frank/punch2left.gif");
				x=0;
			}
			TheGame._attacks.add(new OffsetHitbox("bluepunch", this, x, 18, 21, 14, 20, 15, _clear));
			_xtumbling = true;
			TheGame.playSound("/frank/sounds/frank2.wav");
			
		}
		if(_counter > 10 && _xvelocity == 0) {
			_attack2 = false;
			_canact = true;
			
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("bluepunch") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);

			}
			
		}
	}

	@Override
	public void attack3() {
		if(_canattack3){
		_counter = 0;
		_y -= 16;
		_height = 75;
		_width = 52;
		_yvelocity = -20;
		_xvelocity = 0;
		_attack3 = true;
		_canact = false;
		_canattack3 = false;
		if(_facing.equals("right")) {
			_image = new Image("frank/jump1.gif");
		} else {
			_image = new Image("frank/jump1left.gif");
		}
		TheGame.playSound("/frank/sounds/frank3.wav");
		}
	
		
	}
	
	public void executeAttack3() {
		if(_counter == 12) {
			_gravity = false;
			_yvelocity = 0;
			_width = 45;
			int d;
			if(_facing.equals("right")) {
				_image = new Image("frank/jump2.gif");
				d = 30;
			} else {
				_image = new Image("frank/jump2left.gif");
				d = 3;
			}
			TheGame._attacks.add(new HitboxImpl("skybolt", this, false, _x + d, _y + 50, 28, 28, 0, 15, 15, 15, new Image("frank/skybolt.png")));
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
		
		if(_counter == 15) {
			
			_width = 52;
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("frank/jump3.gif");
				d = 48;
				v = 5;
			} else {
				_image = new Image("frank/jump3left.gif");
				d = -24;
				v = -5;
			}
			TheGame._attacks.add(new HitboxImpl("skybolt", this, false, _x + d, _y + 40, 28, 28, v, 15, 15, 15, new Image("frank/skybolt.png")));
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
		if(_counter == 18) {
			
			_width = 57;
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("frank/jump4.gif");
				d = 57;
				v = 10;
			} else {
				_image = new Image("frank/jump4left.gif");
				d = -28;
				v = -10;
			}
			TheGame._attacks.add(new HitboxImpl("skybolt", this, false, _x + d, _y + 30, 28, 28, v, 15, 15, 15, new Image("frank/skybolt.png")));
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
		if(_counter == 21) {
			
			_width = 60;
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("frank/jump5.gif");
				d = 60;
				v = 15;
			} else {
				_image = new Image("frank/jump5left.gif");
				d = -28;
				v = -15;
			}
			TheGame._attacks.add(new HitboxImpl("skybolt", this, false, _x + d, _y + 20, 28, 28, v, 15, 15, 15, new Image("frank/skybolt.png")));
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
		if(_counter == 24) {
			
			_width = 66;
			int d;
			double v;
			if(_facing.equals("right")) {
				_image = new Image("frank/jump6.gif");
				d = 66;
				v = 20;
			} else {
				_image = new Image("frank/jump6left.gif");
				d = -28;
				v = -20;
			}
			TheGame._attacks.add(new HitboxImpl("skybolt", this, false, _x + d, _y + 10, 28, 28, v, 0, 15, 15, new Image("frank/skybolt.png")));
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
		if(_counter == 28) {
			_attack3 = false;
			_gravity = true;
			_canact = true;
			
		}
		
	}

	@Override
	public void attackU() {
	if(_ultcharge >= 150) {
		_counter = 0;
		_y -= 16;
		_height = 75;
		_width = 45;
		_yvelocity = -20;
		_xvelocity = 0;
		_attacku = true;
		_canact = false;
		_immune = true;
		_ultcharge = 0;
		if(_facing.equals("right")) {
			_image = new Image("frank/jump2.gif");
		} else {
			_image = new Image("frank/jump2left.gif");
		}
	}
		
	}
	public void executeAttackU() {
		if(_counter == 12) {
			_gravity = false;
			_yvelocity = 0;
			
			
			
		}
		if(_counter % 3 == 0) {
			int d;
			if(_facing.equals("right")) {
				_image = new Image("frank/jump2.gif");
				d = 30;
			} else {
				_image = new Image("frank/jump2left.gif");
				d = 3;
			}
			Hitbox attack = new HitboxImpl("skybolt", this, false, _x + d, _y + 50, 28, 28, 0, 15, 15, 15, new Image("frank/skybolt.png"));
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
			TheGame.playSound("/frank/sounds/frank1.wav");
		}
		if(_counter == 200) {
			_immune = false;
			_canact = true;
			_attacku = false;
			_gravity = true;
		}
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}
	
	@Override
	public void pressLeft() {
		if(!_attacku) {
			super.pressLeft();
		} else {
			_xvelocity = -4.5;
		}
	}
	@Override
	public void pressRight() {
		if(!_attacku) {
			super.pressRight();
		} else {
			_xvelocity = 4.5;
		}
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd1 > 0) {
			_cd1--;
		}
	}

}
