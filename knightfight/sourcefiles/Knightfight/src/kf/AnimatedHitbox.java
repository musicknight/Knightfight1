package kf;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import kf.characters.Character;

public class AnimatedHitbox extends HitboxImpl {
	private List<Image> _images;
	private int _rate;
	private int _ratecounter;
	private int _counter;
	
	public AnimatedHitbox(String ID, Character c, boolean gravity, int x, int y, double width, double height,
			double xvelocity, double yvelocity, double knockback, double damage, List<Image> images, int rate) {
		super(ID, c, gravity, x, y, width, height, xvelocity, yvelocity, knockback, damage, images.get(0));
		_images = images;
		_rate = rate;
	}
	@Override
	public void render(GraphicsContext gc) {
		_image = _images.get(_ratecounter);
		super.render(gc);
		_counter++;
		if(_counter % _rate == 0) {
			if(_ratecounter < _images.size() -1) {
				_ratecounter++;
			} else {
				_ratecounter = 0;
			}
		}
		
	}

}
