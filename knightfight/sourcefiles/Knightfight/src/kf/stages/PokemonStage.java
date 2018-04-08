package kf.stages;

import javafx.scene.image.Image;
import kf.Platform;
import kf.PlatformImpl;
import kf.characters.Character;

public class PokemonStage extends Place {

	public PokemonStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(130, 400, 646, 202);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("stadium.png");
		_song = "/sounds/pokemon.mp3";
		_c1x = 225;
		_c2x = 625;
		_c1y = 400;
		_c2y = 400;
	}

}
