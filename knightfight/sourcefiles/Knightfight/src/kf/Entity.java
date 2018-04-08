package kf;

import javafx.scene.canvas.GraphicsContext;

public interface Entity {
	public void render(GraphicsContext gc);

	public int getX();

	public int getY();

	public double getXVelocity();

	public double getYVelocity();

	public String getID();

	public boolean isAffectedByGravity();
}
