package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MermaidChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _reflected = false;
	private int _cd3;

	public MermaidChar(String ID) {
		super(ID);
		if (_facing.equals("right")) {
			_image = new Image("mermaid/ariel.png");
		} else {
			_image = new Image("mermaid/arielleft.png");
		}
		_width = 20;
		_height = 60;
		_damagefactor = 1;
		_speedfactor = 1.05;
	}

	@Override
	public Image getStockImage() {
		return new Image("mermaid/stock.png");
	}

	@Override
	public void render(GraphicsContext gc) {
		if (!_attack1 && !_attack2 && !_attack3 && !_attacku && _xvelocity == 0) {
			_width = 20;
			if (_facing.equals("right")) {
				_image = new Image("mermaid/ariel.png");
			} else {
				_image = new Image("mermaid/arielleft.png");
			}
		} else if (!_attacku && _xvelocity != 0) {
			_width = 27;
			if (_facing.equals("right")) {
				_image = new Image("mermaid/move.png");
			} else {
				_image = new Image("mermaid/moveleft.png");
			}
		}
		if (_attack1) {
			executeAttack1();
		}
		if (_attack2) {
			executeAttack2();
		}
		if (_attack3) {
			executeAttack3();
		}
		if (_attacku) {
			executeAttackU();
		}

		super.render(gc);
	}

	@Override
	public void attack1() {
		_attack1 = true;
		_canact = false;
		_counter = 0;
		_width = 40;
		_xvelocity = 0;
		if (_facing.equals("right")) {
			_image = new Image("mermaid/bubble1.png");
		} else {
			_image = new Image("mermaid/bubble1left.png");
		}
	}

	public void executeAttack1() {
		if (_xtumbling) {

			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("bubble") && a.getCharacter().equals(this)) {
					remove.add(a);
					// return;
				}
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			_attack1 = false;
		}
		if (_counter == 10) {
			_width = 27;
			if (_facing.equals("right")) {
				_image = new Image("mermaid/bubble2.png");
			} else {
				_image = new Image("mermaid/bubble2left.png");
			}

		}
		if (_counter == 17) {
			_width = 54;
			int x;
			int v;
			if (_facing.equals("right")) {
				_image = new Image("mermaid/bubble3.png");
				x = 54;
				v = 6;
			} else {
				_image = new Image("mermaid/bubble3left.png");
				x = -45;
				v = -6;
			}
			Hitbox attack = new HitboxImpl("bubble", this, true, _x + x, _y + 15, 45, 45, v, -14, 17, 18,
					new Image("mermaid/bubble.png"));
			attack.setBounces(true);
			TheGame._attacks.add(attack);
		}
		if (_counter == 30) {
			_attack1 = false;
			_canact = true;

		}

	}

	@Override
	public void attack2() {
		_canact = false;
		_attack2 = true;
		_counter = 0;
		_xvelocity = 0;
		_width = 40;
		if (_facing.equals("right")) {
			_image = new Image("mermaid/flail1.png");
		} else {
			_image = new Image("mermaid/flail1left.png");
		}
	}

	public void executeAttack2() {
		if (_xtumbling) {

			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("flail") && a.getCharacter().equals(this)) {
					remove.add(a);

				}
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			_attack2 = false;
		}
		if (_counter == 17) {
			_width = 80;
			if (_facing.equals("right")) {
				_image = new Image("mermaid/flail2.png");
			} else {
				_image = new Image("mermaid/flail2left.png");
				_x -= 50;
			}
			Hitbox attack = new CharLinkedHitbox("flail", this, 33, 16);
			TheGame._attacks.add(attack);
		}
		if (_counter == 24) {
			if (_facing.equals("left")) {
				_x += 50;
			}
			_attack2 = false;
			_canact = true;
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("flail") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
				// s System.out.println(a);
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
		}
	}

	@Override
	public void attack3() {
		if (_cd3 == 0) {
			_attack3 = true;
			_canact = false;
			_xvelocity = 0;
			_counter = 0;
			_width = 27;
			_cd3 = 60;
			_reflected = false;
			if (_facing.equals("right")) {
				_image = new Image("mermaid/fish1.png");
			} else {
				_image = new Image("mermaid/fish1left.png");
			}
		}

	}

	public void executeAttack3() {
		Hitbox attack;

		if (_counter == 17) {

			if (_facing.equals("right")) {
				attack = new HitboxImpl("fish", this, false, 849, 350, 50, 50, -25, 0, 19, 24,
						new Image("mermaid/fishleft.png"));
			} else {
				attack = new HitboxImpl("fish", this, false, 0, 350, 50, 50, 25, 0, 19, 24,
						new Image("mermaid/fish.png"));
			}
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
		}
		if (_counter == 27) {
			_canact = true;

		}
		if (_counter > 17) {
			int i = 0;
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("fish") && a.getCharacter().equals(this)) {
					i++;
				}
			}
			if (i == 0) {
				_attack3 = false;
				_canact = true;

			}
		}

		if (_counter > 17 && !_reflected) {
			List<Hitbox> toadd = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("fish")) {
					attack = a;
					if (attack.getXVelocity() < 0 && attack.getX() <= _x + 20) {
						attack.setIsGone(true);
						attack = new HitboxImpl("fish", this, false, _x + 28, 350, 50, 50, 25, 0, 25, 28,
								new Image("mermaid/fish.png"));
						_reflected = true;
					

					} else if (attack.getXVelocity() > 0 && attack.getX() >= _x) {
						attack.setIsGone(true);
						attack = new HitboxImpl("fish", this, false, _x - 50, 350, 50, 50, -25, 0, 25, 28,
								new Image("mermaid/fishleft.png"));
						_reflected = true;
					

					}
					toadd.add(attack);
					break;

				}
			}
			for (Hitbox a : toadd) {
				TheGame._attacks.add(a);
			}

		}

	}

	@Override
	public void attackU() {
		if(_ultcharge >= 150) {
		_attacku = true;
		_canact = false;
		_counter = 0;
		_y-=141;
		_width = 200;
		_height = 200;
		_xvelocity = 0;

		_immune = true;
		_ultcharge = 0;
		if (_facing.equals("right")) {
			_image = new Image("mermaid/wave.png");
			_x -= 140;
		} else {
			_image = new Image("mermaid/waveleft.png");
		}
		}
	}

	public void executeAttackU() {
		if(_counter == 10) {
			if (_facing.equals("right")) {
				_xvelocity = 20;
			} else {
				_xvelocity = -20;
			}
			Hitbox attack = new CharLinkedHitbox("wave", this, 70, 70);
			TheGame._attacks.add(attack);
		}
	if(_facing.equals("right")){
		if(_x >= 600) {
			List<Hitbox> remove = new ArrayList<Hitbox>();
			for (Hitbox a : TheGame._attacks) {
				if (a.getID().equals("wave") && a.getCharacter().equals(this)) {
					remove.add(a);
				}
			}
			for (Hitbox a : remove) {
				TheGame._attacks.remove(a);
			}
			_attacku = false;
			_canact = true;
			_immune = false;
			_xvelocity = 0;
			_y += 139;
			_x += 100;
			_width = 20;
			_height = 60;
			
		}
		} else {
			if(_x <= 100) {
				List<Hitbox> remove = new ArrayList<Hitbox>();
				for (Hitbox a : TheGame._attacks) {
					if (a.getID().equals("wave") && a.getCharacter().equals(this)) {
						remove.add(a);
					}
				}
				for (Hitbox a : remove) {
					TheGame._attacks.remove(a);
				}
				_attacku = false;
				_canact = true;
				_immune = false;
				_xvelocity = 0;
				_y += 139;
				_x+= 70;
				_width = 20;
				_height = 60;
				
			}
		}
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public void incrementCounter() {
		if (_cd3 > 0) {
			_cd3--;
		}
		super.incrementCounter();
	}

}
