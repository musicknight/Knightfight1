package cd;

import javafx.scene.canvas.GraphicsContext;

public abstract class EntityImpl implements Entity {
	protected int _x;
	protected int _y;
	protected double _xvelocity;
	protected double _yvelocity;
	protected String _ID;
	protected boolean _affectedbygravity;
	protected boolean _circle = false;

	public EntityImpl(int x, int y, String ID) {
		_x = x;
		_y = y;
		_ID = ID;

	}

	public abstract void render(GraphicsContext gc);

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public double getXVelocity() {
		return _xvelocity;

	}

	public double getYVelocity() {
		return _yvelocity;
	}

	public String getID() {
		return _ID;
	}

	public boolean isAffectedByGravity() {
		return _affectedbygravity;
	}
	
	public int getWidth() {
		return 0;
	}
	public int getHeight() {
		return 0;
	}
	
	public boolean isImmune() {
		return false;
	}
	public boolean isCircle() {
		return _circle;
	}
}
