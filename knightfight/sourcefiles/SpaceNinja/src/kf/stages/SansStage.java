package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class SansStage extends Place {

	public SansStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(157, 356, 575, 356);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("undertalemap.png");
		_song = "/sounds/sanssong.mp3";
		_c1x = 200;
		_c2x = 625;
	}

}
