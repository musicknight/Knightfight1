package kf.stages;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import kf.Hitbox;
import kf.MeleeHitbox;
import kf.Platform;
import kf.PlatformImpl;
import kf.TheGame;
import kf.characters.Character;



public class DarkMindStage extends Place{

	private int _lasercounter = 0;
	
	public DarkMindStage(Character c1, Character c2) {
		super(c1, c2);
		Platform p = new PlatformImpl(47*3, 163*3, 206*3, 37*3);
		p.setImage(new Image("clear.png"));
		_platforms.add(p);
		_bg = new Image("darkmind.png");
		Random r = new Random();
		int i = r.nextInt(2);
		if(i == 1){
			_song = "/sounds/kirbysong2.mp3";
		} else {
			_song = "/sounds/kirbysong3.mp3";
		}
		_c1x = 215;
		_c2x = 625;
		_c1y=163*3;
		_c2y=163*3;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		_lasercounter++;
		if(_lasercounter == 500) {

			TheGame.playSound("/darkmind/sound.wav");
		}
		if(_lasercounter >= 500 && _lasercounter < 504) {
			Hitbox laser1 = new MeleeHitbox("stagelaser1", _c1, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser1.png"));
			Hitbox laser2 = new MeleeHitbox("stagelaser2", _c2, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser1.png"));
			TheGame._attacks.add(laser1);
			TheGame._attacks.add(laser2);
		}
		if(_lasercounter >= 504 && _lasercounter < 508) {
			TheGame.clearHitboxes("stagelaser1", _c1);
			TheGame.clearHitboxes("stagelaser2", _c2);
			Hitbox laser1 = new MeleeHitbox("stagelaser1", _c1, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser2.png"));
			Hitbox laser2 = new MeleeHitbox("stagelaser2", _c2, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser2.png"));
			TheGame._attacks.add(laser1);
			TheGame._attacks.add(laser2);
		}
		if(_lasercounter >= 508 && _lasercounter < 512) {
			TheGame.clearHitboxes("stagelaser1", _c1);
			TheGame.clearHitboxes("stagelaser2", _c2);
			Hitbox laser1 = new MeleeHitbox("stagelaser1", _c1, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser3.png"));
			Hitbox laser2 = new MeleeHitbox("stagelaser2", _c2, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser3.png"));
			TheGame._attacks.add(laser1);
			TheGame._attacks.add(laser2);
		}
		if(_lasercounter >= 512 && _lasercounter < 516) {
			TheGame.clearHitboxes("stagelaser1", _c1);
			TheGame.clearHitboxes("stagelaser2", _c2);
			Hitbox laser1 = new MeleeHitbox("stagelaser1", _c1, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser4.png"));
			Hitbox laser2 = new MeleeHitbox("stagelaser2", _c2, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser4.png"));
			TheGame._attacks.add(laser1);
			TheGame._attacks.add(laser2);
		}
		if(_lasercounter >= 516 && _lasercounter < 610) {
			TheGame.clearHitboxes("stagelaser1", _c1);
			TheGame.clearHitboxes("stagelaser2", _c2);
			Hitbox laser1 = new MeleeHitbox("stagelaser1", _c1, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser5.png"));
			Hitbox laser2 = new MeleeHitbox("stagelaser2", _c2, 432, 224, 46, 272, 20, 20, new Image("darkmind/laser5.png"));
			TheGame._attacks.add(laser1);
			TheGame._attacks.add(laser2);
		}
		if(_counter % 655 == 0) {
			TheGame.clearHitboxes("stagelaser1", _c1);
			TheGame.clearHitboxes("stagelaser2", _c2);
			_lasercounter = 0;
		}
		
	}

}
