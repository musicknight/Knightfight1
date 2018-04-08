package cd;

import cd.Entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import cd.chars.Character;

public interface Hitbox extends Entity {
	public void render(GraphicsContext gc);

	public boolean isGone();

	public boolean checkCollide();

	public int getX();

	public int getY();

	public double getKnockback();

	public Entity getCharacter();

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
	
	public int getWidth();
	
	public int getHeight();
	
	public void setX(int x);
	
	public void setY(int y);
	
	public boolean isSetKnockback();
	
	public void setSetKnockback(boolean b);
	
	public void setForceRight(boolean b);
	
	public void setForceLeft(boolean b);
	
	public boolean isForceRight();
	
	public boolean isForceLeft();
	
	public boolean isFreezeY();
	
	public void setFreezeY(boolean b);
	
	public void setCircle(boolean b);
}
