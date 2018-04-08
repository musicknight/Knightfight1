package kf.stages;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import kf.Platform;
import kf.TheGame;
import kf.characters.Character;

public abstract class Place {
	protected List<Platform> _platforms = new ArrayList<Platform>();
	protected String _song;
	protected Image _bg;
	protected Character _c1;
	protected Character _c2;
	protected int _c1x = 625;
	protected int _c2x = 225;
	protected int _c1y = 400;
	protected int _c2y = 400;
	protected int _counter;
	protected boolean _starting;
	protected Image _clear = new Image("clear.png");
	
	public Place(Character c1, Character c2){
		_c1 = c1;
		_c2 = c2;
	}
	
	
	public void render(GraphicsContext gc) {
		gc.drawImage(_bg, 0, 0, 900, 600);
		for(Platform p : _platforms) {
			p.render(gc);
		}
		
		
		if(_starting) {
			if(_counter < 40) {
				gc.setFill(Color.RED);
				gc.setFont(Font.font("Arial Black", 100));
				gc.fillText("READY?", 230, 250);
				
			}
			if(_counter == 0) {
				TheGame.playSound("/sounds/ready.wav");
			}
			if(_counter >= 40 && _counter < 50 ) {
				gc.setFill(Color.RED);
				gc.setFont(Font.font("Arial Black", 100));
				gc.fillText("GO!", 340, 250);
				
			}
			if(_counter == 40) {
				TheGame.playSound("/sounds/go.wav");

				TheGame.playStageSong(_song);
			}
			if(_counter == 50){
				_c1.stopIntro();
				_c2.stopIntro();
			}
			if(_counter > 40 && TheGame.getPlayerState() != 0 && !TheGame.getClosed()) {
				//TheGame.playStageSong(_song);
			}
		}
		_counter++;
		
	}
	public void start() {
		_counter = 0;
		_c1.setX(_c1x);
		_c2.setX(_c2x);
		_c1.setY(_c1y-_c2.getHeight());
		_c2.setY(_c2y-_c2.getHeight());
		_c1.intro();
		_c2.intro();
		_starting = true;
	}
	
	public List<Platform> getPlatforms() {
		return _platforms;
	}
}
