package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class CampStage extends Place {

	public CampStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(200, 496, 450, 600-491);
		p.setImage(_clear);
		_platforms.add(p);
		_bg = new Image("shovelforest.jpg");
		_song = "/sounds/shovelsong.mp3";
		_c1x = 225;
		_c2x = 575;
	}

}
