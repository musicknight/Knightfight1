package cd.chars;

import cd.Hitbox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class CDCharacter extends CharacterImpl {

	protected int _lives;
	protected int _counter1;
	protected int _counter2;
	protected int _counter3;
	protected boolean _attackboost;
	protected int _abcounter;
	protected int _charge;
	// saves the image for attackboost
	protected Image _savedim;
	protected boolean _dying;
	
	public CDCharacter(String ID) {
		super(ID);
		_lives = 3;
		
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_attackboost) {
			executeAttackBoost();
		}
	}

	@Override
	public void hit(Hitbox h) {
		if(!_attackboost && !_immune){
		if(_lives > 1){
		_lives--;
		_attackboost = true;
		_immune = true;
		_abcounter = 0;
		_savedim = _image;
		System.out.println("here");
		} else {
			_lives--;
			_dying = true;
			_canact = false;
			_xvelocity = 0;
		}
		}
	}
	
	public void executeAttackBoost() {
		
		if(_abcounter % 4 == 0 || _abcounter % 4 == 1) {
			_image = _clear;
		} else {
			_image = _savedim;
		}
		if(_abcounter == 50) {
			_immune = false;
			_attackboost = false;
			_image = _savedim;
		}
	}

	@Override
	public Image getStockImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	abstract public void attack1();

	@Override
	abstract public void attack2();

	@Override
	abstract public void attack3();

	@Override
	abstract public void attackU();

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		_counter1++;
		_counter2++;
		_counter3++;
		if(_attackboost) {
			_abcounter++;
		}
	}
	
	public int getLives() {
		return _lives;
	}
	
	public void addCharge(int i) {
		_charge+= i;
	}
	
	public void setImmune(boolean  b) {
		_immune = b;
	}

}
