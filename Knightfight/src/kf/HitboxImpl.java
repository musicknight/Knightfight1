package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class HitboxImpl extends EntityImpl implements Hitbox {
	protected Image _image = null;
	protected Color _color = null;
	protected double _width;
	protected double _height;
	protected boolean _onplatform;
	protected boolean _bounces = false;
	protected double _knockback;
	protected double _damage;
	protected Character _character;
	protected boolean _collided;
	protected boolean _gone = false;
	protected boolean _dissappearonhit = true;
	// this decides whether the knockback is in relation to character or hitbox
	protected boolean _horientation = false;

	public HitboxImpl(String ID, Character c, boolean gravity, int x, int y, double width, double height,
			double xvelocity, double yvelocity, double knockback, double damage) {

		this(ID, c, gravity, x, y, width, height, xvelocity, yvelocity, knockback, damage, Color.RED);

	}

	public HitboxImpl(String ID, Character c, boolean gravity, int x, int y, double width, double height,
			double xvelocity, double yvelocity, double knockback, double damage, Color color) {
		super(x, y, ID);
		_character = c;
		_affectedbygravity = gravity;
		_width = width;
		_height = height;
		_xvelocity = xvelocity;
		_yvelocity = yvelocity;
		_knockback = knockback;
		_damage = damage;
		_color = color;

	}

	public HitboxImpl(String ID, Character c, boolean gravity, int x, int y, double width, double height,
			double xvelocity, double yvelocity, double knockback, double damage, Image image) {
		super(x, y, ID);
		_character = c;
		_affectedbygravity = gravity;
		_width = width;
		_height = height;
		_xvelocity = xvelocity;
		_yvelocity = yvelocity;
		_knockback = knockback;
		_damage = damage;
		_image = image;

	}

	public void render(GraphicsContext gc) {
		if (!(_gone)) {
			if (_image == null) {
				gc.setFill(_color);
				gc.fillRect(_x, _y, _width, _height);
			} else {
				gc.drawImage(_image, _x, _y, _width, _height);
			}
		}
		if (_y + _yvelocity > 600) {
			_gone = true;
		}
		if (_x + _xvelocity > 900 || _x + _xvelocity < 0) {
			_gone = true;
		}
		_y += _yvelocity;
		_x += _xvelocity;
		if ((_x > 100 && _x < 750 && _y + _yvelocity > 400 - _height)) {
			if (_bounces) {
				_yvelocity = -_yvelocity / 2;
				if (_xvelocity > 0) {
					if (_xvelocity < _xvelocity / 10) {
						_xvelocity = 0;
						_gone = true;

					} else if (_xvelocity < 3) {
						if (_xvelocity < 1) {
							_xvelocity = 0;
							_gone = true;
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
						_gone = true;

					} else if (xvelocity < 3) {
						if (xvelocity < 1) {
							_xvelocity = 0;
							_gone = true;

						} else {

							_xvelocity += .5;

						}
					} else {
						_xvelocity -= _xvelocity / 10;

					}

				}
			}
		}

	}

	public boolean checkCollide() {
		Character c = null;
		if (_character.getID().equals("one")) {
			c = TheGame._character2;
		}
		if (_character.getID().equals("two")) {
			c = TheGame._character1;
		}
		Rectangle ch = new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight());
		Rectangle h = new Rectangle(_x, _y, _width, _height);
		Shape intersect = Shape.intersect(ch, h);
		if (intersect.getBoundsInLocal().getWidth() != -1) {
			_collided = true;
			if (isDissappearOnHit() && !c.isImmune()) {
				_gone = true;
			}
			return true;
		} else {

			return false;
		}
	}

	public boolean isGone() {
		return _gone;
	}

	public double getKnockback() {
		return _knockback;
	}

	public Character getCharacter() {
		return _character;
	}

	public double getDamage() {
		return _damage;
	}

	public void setIsGone(boolean set) {
		_gone = set;
	}

	public void setXVelocity(double x) {
		_xvelocity = x;
	}

	public void setYVelocity(double y) {
		_yvelocity = y;
	}

	public boolean isDissappearOnHit() {
		return _dissappearonhit;
	}

	public void setDissappearOnHit(boolean s) {
		_dissappearonhit = s;
	}

	public void setHOrientation(boolean s) {
		_horientation = s;
	}

	public boolean getHOrientation() {
		return _horientation;
	}

	public void setBounces(boolean b) {
		_bounces = b;
	}

	public void setImage(Image i) {
		_image = i;
	}

}
