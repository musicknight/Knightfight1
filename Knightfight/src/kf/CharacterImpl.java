package kf;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class CharacterImpl extends EntityImpl implements Character {

	protected Image _image;
	protected boolean _dodged;
	protected Character _otherchar;
	protected double _counter = 0;
	protected double _spawnimmunecounter = 0;
	protected boolean _onplatform = false;
	protected boolean _canact = true;
	private boolean _canjump1 = true;
	private boolean _canjump2 = true;
	protected String _facing;
	protected double _damage = 1;
	protected boolean _xtumbling;
	protected boolean _ytumbling;
	protected boolean _canmovex = true;
	protected boolean _canmovey = true;
	protected int _width;
	protected int _height;
	protected boolean _immune;
	protected int _cd;
	protected double _damagefactor;
	protected double _speedfactor;
	protected double _ultcharge;
	protected boolean _attacku;
	protected boolean _gravity = true;
	//means this char should be rendered on top
	protected boolean _haspriority = false;
	//true = getX() is left x coordinate false = getX() is right x coordinate
	protected boolean _getxleft = true;
	private int _lives = 3;
	protected Image _clear = new Image("clear.png");
	protected int _restingwidth;
	protected int _restingheight;
	protected boolean _startedrespawning = false;

	public CharacterImpl(String ID) {
		super(0, 300, ID);
		_ID = ID;
		if (ID.equals("one")) {
			_x = 225;

			_facing = "right";
		}
		if (ID == "two") {
			_x = 625;

			_facing = "left";

		}
		_affectedbygravity = true;

	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(_image, _x, _y, _width, _height);
		if (_ID.equals("one")) {
			gc.setFont(Font.font("Arial", 20));
			gc.setFill(this.getColor());
			gc.fillText("1", _x + (_width / 2), _y - 27);
		} else {
			gc.setFont(Font.font("Arial", 20));
			gc.setFill(this.getColor());
			gc.fillText("2", _x + (_width / 2), _y - 27);
		}

		if (_immune) {

			if (_spawnimmunecounter == 60) {
				_immune = false;
			}
		}
		if (_cd != 0) {
			_canact = false;
			if (!_xtumbling) {
				_xvelocity = 0;
			}
			if (_xtumbling) {
				_cd = 0;
			}
			if (_counter == _cd) {
				_canact = true;
				_cd = 0;
			}
		}
		if(_canmovex) {
			_x += _xvelocity;
		}
		if(_canmovey) {
			_y+= _yvelocity;
		}
		if(_y > 600) {
			
			if(_lives >= 1) {
			if(!_startedrespawning) {
				_counter = 0;
				_startedrespawning = true;
				die();
				_canact = false;
				TheGame.playSound("/sounds/death.wav");
			}
			if(_counter == 30) {
				respawn();
				_startedrespawning = false;
			}
		}
		}


	}

	public void setXVelocity(double x) {
		_xvelocity = x;
	}

	public void setYVelocity(double y) {
		_yvelocity = y;
	}

	public void move() {

		if (_xtumbling) {
			if (_xvelocity > 0) {
				if (_xvelocity < _xvelocity / 10) {
					_xvelocity = 0;

				} else if (_xvelocity < 3) {
					if (_xvelocity < 1) {
						_xvelocity = 0;
						_xtumbling = false;
						_canact = true;

					} else {

						_xvelocity -= .5;

					}
				} else {
					_xvelocity -= _xvelocity / 10;

				}
			} else {
				double xvelocity = Math.abs(_xvelocity);
				if (xvelocity < xvelocity / 10) {
					_xvelocity = 0;

				} else if (xvelocity < 3) {
					if (xvelocity < 1) {
						_xvelocity = 0;
						_xtumbling = false;
						_canact = true;

					} else {

						_xvelocity += .5;

					}
				} else {
					_xvelocity -= _xvelocity / 10;

				}

			}
		}
		if (_ytumbling) {
			if (_yvelocity == 0) {
				_ytumbling = false;
			}
		}
		for(Platform p : TheGame.getPlatforms()){
			if ((_x + _width + _xvelocity < 900 && _x - 3 + _xvelocity > 0)) {
				if ((_y > p.getY() - _height && (_x + _xvelocity > p.getX() - _width && _x + _xvelocity < (p.getX() + p.getWidth())))) {
					_canmovex = false;
				} else {
					if(TheGame.getPlatforms().indexOf(p) ==0) {
					_canmovex = true;
					}
				}
			} else {
				if(TheGame.getPlatforms().indexOf(p) ==0) {
					_canmovex = false;
					}
			}
			
			if ((_x > p.getX() - _width && _x < (p.getX() + p.getWidth())&& _y > p.getY() - _height)) {
			
					if(_yvelocity < 0) {
					if(_canmovey == false){
						if(TheGame.getPlatforms().indexOf(p)==0){
						_canmovey = true;
						}
					}
					} else {
						_canmovey = false;
						_y = p.getY() - _height;
					}

	
			} else if ((_x > p.getX() - _width && _x < (p.getX() + p.getWidth()) && _y + _yvelocity > p.getY() - _height)) {
				_onplatform = true;
				_yvelocity = 0;
				_canjump1 = true;
				_canjump2 = true;
				_canmovey = false;
			} else {
				if(TheGame.getPlatforms().indexOf(p) ==0) {
					_onplatform = false;
				}
				if(TheGame.getPlatforms().indexOf(p) ==0) {
					_canmovey = true;
					
				}
			}
		}
		
		
		
	
	}

	public void jump1() {
		if (_canjump1) {
			_yvelocity = -13;
		}
		_canjump1 = false;
	}

	public void jump2() {
		if (_canjump2) {
			_yvelocity = -13;
		}
		_canjump2 = false;
	}

	public boolean isOnPlatform() {
		return _onplatform;
	}

	@Override
	public boolean isCanJump1() {
		// TODO Auto-generated method stub
		return _canjump1;
	}

	@Override
	public boolean isCanJump2() {
		// TODO Auto-generated method stub
		return _canjump2;
	}

	abstract public void attack1();

	abstract public void attack2();

	abstract public void attack3();

	abstract public void attackU();

	public void hit(Hitbox h) {
		// if(h.getOrientation.equals(horizontal) {
		if (!_immune) {
			_xtumbling = true;
			_ytumbling = true;
			_canact = false;
			_canjump2 = true;
			_xvelocity = 0;
			int xdirection = 0;
			int ydirection = 0;

			// if (h.getX() < _x) {
			xdirection = 1;
			// }
			// if (h.getX() > _x) {
			xdirection = -1;
			// }

			// if (h.getClass().toString().equals("class kf.MeleeHitbox")) {
			if (!h.getHOrientation()) {
				if (h.getCharacter().getX() < _x) {
					xdirection = 1;
				}
				if (h.getCharacter().getX() > _x) {
					xdirection = -1;
				}
			} else {
				if (h.getX() < _x) {
					xdirection = 1;
				}
				if (h.getX() > _x) {
					xdirection = -1;
				}
			}
			// }

			_damage += h.getDamage();
			if (!_attacku && !h.getCharacter().isAttackU()) {
				_ultcharge += h.getDamage() / 3;
				h.getCharacter().addUltCharge(h.getDamage() / 6);
			}
			if(!h.isSetKnockback()) {
				_xvelocity = _damagefactor * xdirection * h.getKnockback() * (0.006 * (_damage));
			} else {
				_xvelocity = xdirection * h.getKnockback();
			}
		} else {
			_dodged = true;
		}
		// _damage+= h.getDamage();
	}

	@Override
	public boolean isXTumbling() {
		return _xtumbling;
	}

	@Override
	public boolean isYTumbling() {
		return _ytumbling;
	}

	public boolean isCanAct() {
		return _canact;
	}
	
	public void setCanAct(boolean b) {
		_canact = b;
	}

	public double getDamage() {
		return _damage;
	}

	public void incrementCounter() {
		_counter++;
		_spawnimmunecounter++;

	}

	public void setFacing(String s) {
		_facing = s;
	}

	public boolean isGone() {
		return _y > 600;
	}

	public void setX(int x) {
		_x = x;

	}

	public void setY(int y) {
		_y = y;
	}

	public void setDamage(double x) {
		_damage = x;
	}

	public void die() {
		_lives--;
	}

	public int getLives() {
		return _lives;
	}

	public void respawn() {
		
		_damage = 1;
		_xvelocity = 0;
		_yvelocity = 0;
		_y = 300;
		if (_ID.equals("one")) {
			_x = 225;
		}
		if (_ID.equals("two")) {
			_x = 625;
		}
		_spawnimmunecounter = 0;
		_immune = true;
		//_ultcharge = 0;
		_canact = true;

	}

	@Override
	public boolean isCharging1() {
		// TODO Auto-generated method stub
		return false;
	}

	abstract public Color getColor();

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public double getSpeedFactor() {
		return _speedfactor;
	}

	public void addUltCharge(double d) {
		_ultcharge += d;
	}

	public double getUltCharge() {
		return _ultcharge;
	}

	public boolean isAttackU() {
		return _attacku;
	}

	public void setOtherChar(Character c) {
		_otherchar = c;
	}

	public boolean isImmune() {
		return _immune;
	}

	public boolean isGravity() {
		return _gravity;
	}

	public void pressUp() {

	}

	public void pressDown() {

	}

	public void pressRight() {
		if(_canact) {
		_xvelocity = 5.5 * _speedfactor;
		_facing = "right";
		}
	}

	public void pressLeft() {
		if(_canact) {
		_xvelocity = -5.5 * _speedfactor;
		_facing = "left";
		}
	}

	public void pressJump() {
		if(_canact) {
		if (_canjump1) {
			jump1();
		} else {
			jump2();
		}
		}
	}

	public void pressAttack1() {
		if(_canact) {
		attack1();
		}
	}

	public void pressAttack2() {
		if(_canact) {
		attack2();
		}
	}

	public void pressAttack3() {
		if(_canact) {
		attack3();
		}
	}

	public void pressAttackU() {
		if(_canact) {
		attackU();
		}
	}

	public void releaseUp() {

	}

	public void releaseDown() {

	}

	public void releaseJump() {

	}
	
	public boolean isHasPriority() {
		return _haspriority;
	}
	
	@Override
	public int getX() {
		if(_getxleft) {
			return _x;
		} else {
			return _x+_width;
		}
	}
	public int getRealX() {
		return _x;
	}
	public int getRealY() {
		return _y;
	}
	
}