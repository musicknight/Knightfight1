package kf.stages;

import java.util.Random;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class FountainStage extends Place {

	public FountainStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(131, 342, 620, 258);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("fountain.png");
		Random r = new Random();
		int i = r.nextInt(2);
		if(i == 1){
			_song = "/sounds/kirbysong4.mp3";
		} else {
			_song = "/sounds/kirbysong5.mp3";
		}
		_c1x = 180;
		_c2x = 625;
		_c1y = 342;
		_c2y = 342;
	}

}
