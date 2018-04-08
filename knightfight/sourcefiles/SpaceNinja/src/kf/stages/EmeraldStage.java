package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class EmeraldStage extends Place {

	public EmeraldStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(125, 400, 225, 200);
		Platform p2 = new PlatformImpl(525, 500, 225, 100);
		_platforms.add(p);
		_platforms.add(p2);
		_bg = new Image("cave.png");
		_song = "/sounds/cave.mp3";
		_c1x = 225;
		_c2x = 625;
		_c1y = 400;
		_c2y = 500;
	}

}
