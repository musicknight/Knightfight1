package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class TowerStage extends Place {

	public TowerStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(183, 373, 559, 227);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("tower.png");
		_song = "/sounds/shovelsong2.mp3";
		_c1x = 220;
		_c2x = 625;
		_c1y = 373;
		_c2y = 373;
	}

}
