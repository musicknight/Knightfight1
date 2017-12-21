package kf;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface Hitbox extends Entity {
	public void render(GraphicsContext gc);

	public boolean isGone();

	public boolean checkCollide();

	public int getX();

	public int getY();

	public double getKnockback();

	public Character getCharacter();

	public double getDamage();

	public void setIsGone(boolean set);

	public void setXVelocity(double x);

	public void setYVelocity(double y);

	public boolean isDissappearOnHit();

	public void setDissappearOnHit(boolean s);

	public void setHOrientation(boolean s);

	public boolean getHOrientation();

	public void setBounces(boolean b);

	public void setImage(Image i);
	
	public double getWidth();
	
	public double getHeight();
	
	public void setX(int x);
	
	public void setY(int y);
	
	

}
