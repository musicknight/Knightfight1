package cd;

import cd.bosses.Boss;
import javafx.scene.canvas.GraphicsContext;

public interface Entity {
	public void render(GraphicsContext gc);

	public int getX();

	public int getY();

	public double getXVelocity();

	public double getYVelocity();

	public String getID();

	public boolean isAffectedByGravity();
	
	public int getWidth();
	
	public int getHeight();
	
	public boolean isImmune();
	
	public boolean isCircle();

	public Boss getSubBoss();
}
