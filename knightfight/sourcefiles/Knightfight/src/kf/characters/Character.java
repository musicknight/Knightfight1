package kf.characters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import kf.Hitbox;

public interface Character {
	public void move();

	public void render(GraphicsContext gc);

	public int getX();

	public int getY();

	public void setXVelocity(double x);

	public void setYVelocity(double y);

	public double getXVelocity();

	public double getYVelocity();

	public void jump1();

	public void jump2();

	public void incrementCounter();

	public boolean isOnPlatform();

	public boolean isCanJump1();

	public boolean isCanJump2();

	public String getID();

	public void attack1();

	public void attack2();

	public void attack3();

	public void attackU();

	public void hit(Hitbox h);

	public boolean isXTumbling();

	public boolean isYTumbling();

	public boolean isCanAct();

	public double getDamage();

	public void setFacing(String s);

	public boolean isCharging1();

	public boolean isGone();

	public void setX(int x);

	public void setY(int y);

	public void setDamage(double x);

	public void die();

	public int getLives();

	public void respawn();

	public Color getColor();

	public int getWidth();

	public int getHeight();

	public double getSpeedFactor();

	public void addUltCharge(double d);

	public double getUltCharge();

	public boolean isAttackU();

	public void setOtherChar(Character c);

	public Image getStockImage();

	public boolean isImmune();

	public boolean isGravity();

	public void pressUp();

	public void pressDown();

	public void pressRight();

	public void pressLeft();

	public void pressJump();

	public void pressAttack1();

	public void pressAttack2();

	public void pressAttack3();

	public void pressAttackU();

	public void releaseUp();

	public void releaseDown();

	public void releaseJump();
	
	public void releaseRight();
	
	public void releaseLeft();
	
	public void setCanAct(boolean b);
	
	public boolean isHasPriority();
	
	public int getRealX();
	
	public int getRealY();
	
	public void setCanStore(boolean b);
	public void setGravity(boolean b);
	
	public void intro();
	
	public void stopIntro();
	
	public void setCounter(double i);
	
	public void setXTumbling(boolean b);
	
	public void setImage(Image i);
}
