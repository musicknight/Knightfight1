package kf.stages;

import java.util.Random;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class GiygasStage extends Place {

	public GiygasStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(100, 400, 140, 200);
		Platform p2 = new PlatformImpl(240, 375, 140, 225);
		Platform p3 = new PlatformImpl(380, 350, 140, 250);
		Platform p4 = new PlatformImpl(520, 375, 140, 225);
		Platform p5 = new PlatformImpl(660, 400, 140, 200);
		p.setImage(new Image("darkred.jpg"));
		p2.setImage(new Image("darkred.jpg"));
		p3.setImage(new Image("darkred.jpg"));
		p4.setImage(new Image("darkred.jpg"));
		p5.setImage(new Image("darkred.jpg"));
		_platforms.add(p);
		_platforms.add(p2);
		_platforms.add(p3);
		_platforms.add(p4);
		_platforms.add(p5);
		_bg = new Image("giygasstage.gif");
		Random r = new Random();
		int i = r.nextInt(2);
		if(i == 1){
			
			_song = "/sounds/giygas2.mp3";
		} else {
			_song = "/sounds/giygas.mp3";
		}
		_c1x = 225;
		_c2x = 585;
	}

}
