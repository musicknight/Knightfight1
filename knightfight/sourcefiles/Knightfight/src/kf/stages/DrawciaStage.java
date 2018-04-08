package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class DrawciaStage extends Place {

	public DrawciaStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(29*3, 164*3, 243*3, 72);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("kirbystage.png");
		_song = "/sounds/kirbysong.mp3";
		_c1x = 225;
		_c2x = 625;
		_c1y = 164*3;
		_c2y = 164*3;
	}

}
