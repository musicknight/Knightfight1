package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class WilyStage extends Place {

	public WilyStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(139, 434, 586, 166);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("wily.png");
		_song = "/sounds/wilysong.mp3";
		_c1x = 180;
		_c2x = 625;
		_c1y = 434;
		_c2y = 434;
	}

}
