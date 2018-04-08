package cd.bosses;

import java.util.ArrayList;
import java.util.List;

import cd.EntityImpl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import cd.Hitbox;
import cd.TheGame;

abstract public class Boss extends EntityImpl {
	
	protected int _health;
	protected Image _image;
	protected int _width;
	protected int _height;
	protected int _counter;
	protected int _counter1;
	protected int _counter2;
	protected int _counter3;
	protected int _counter4;
	protected boolean _attack1;
	protected boolean _attack2;
	protected boolean _attack3;
	protected boolean _attack4;
	protected boolean _attack5;
	protected boolean _spawning;
	protected boolean _immune;
	protected boolean _won;
	protected boolean _dead;
	protected boolean _songstopped;
	protected Boss _subboss;
	protected List<Image> _sprites = new ArrayList<Image>();
	// cycles through the sprites
			protected int _spritecounter = 0;
			// the index of the current sprite
			protected int _spriteindex = 0;
			protected int _spritecount = 4;
			protected int _rate;
			protected int _ratecounter;
			protected Image _staticimage;

	public Boss(int x, int y, String ID) {
		super(x, y, ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GraphicsContext gc) {
		if(_staticimage == null) {
		_image = _sprites.get(_ratecounter);
		//_counter++;
		if(_counter % _rate == 0) {
			if(_ratecounter < _sprites.size() -1) {
				_ratecounter++;
			} else {
				_ratecounter = 0;
			}
		}
		gc.drawImage(_image, _x, _y, _width, _height);
		} else {
			gc.drawImage(_staticimage, _x, _y, _width, _height);
		}
		if(_dead && !_songstopped) {
			try {
				TheGame._player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_songstopped = true;
		}
		
		_x += _xvelocity;
		_y += _yvelocity;
	}
	
	public void hit(Hitbox h) {
		if(_health > 0 && !_immune) {
		_health -= h.getDamage();
		TheGame._character1.addCharge((int)h.getDamage());
		}
		if(_health < 0) {
			_health = 0;
		}
	}
	public void incrementCounter() {
		_counter++;
		_counter1++;
		_counter2++;
		_counter3++;
		_counter4++;
	}
	
	public abstract void spawn();
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public int getHealth() {
		return _health;
	}
	public boolean isDead() {
		return _dead;
	}
	public boolean isWon() {
		return _won;
	}
	public Boss getSubBoss() {
		return _subboss;
	}
	public void setXVelocity(double x) {
		_xvelocity = x;
	}
	public void setYVelocity(double y) {
		_yvelocity = y;
	}

	public void attack1() {
		// TODO Auto-generated method stub
		
	}
	public void attack2() {
		// TODO Auto-generated method stub
		
	}
	public void attack3() {
		// TODO Auto-generated method stub
		
	}

	public void setDead(boolean b) {
		_dead = b;
		
	}

	public void attack2(int var) {
		// TODO Auto-generated method stub
		
	}

	public void attack3(int var) {
		// TODO Auto-generated method stub
		
	}

	public void changeForm() {
		// TODO Auto-generated method stub
		
	}

	public void switchSprites() {
		// TODO Auto-generated method stub
		
	}

}
