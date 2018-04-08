package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class MoonStage extends Place {

	public MoonStage(Character c1, Character c2) {
		super(c1, c2);
		Platform plat = new PlatformImpl();
		plat.setImage(new Image("moon.jpg"));
		_platforms.add(plat);
		_bg = new Image("earth.png");
		_song = "/sounds/moon.mp3";
		_c1x = 225;
		_c2x = 625;
	}

}
