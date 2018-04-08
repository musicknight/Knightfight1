package cd.bosses;

import cd.Hitbox;
import cd.HitboxImpl;
import cd.TheGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TootBoss extends Boss {

	private boolean _fightstarted;
	private int _text = 1;
	private int _attack1var;
	private boolean _raged;
	
	
	public TootBoss() {
		super(607, 200, "tootboss");
		_health = 500;
		_width = 100;
		_height = 100;
		_staticimage = new Image("tootboss/1.png");
		_counter2 = -20;
		_immune = true;
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(_health == 0 && !_dead) {
			_dead = true;
			_staticimage = new Image("tootboss/dead.png");
			TheGame.setText(new Image("tootboss/text/18.png"));
			_yvelocity = 2;
		}
		if(!_attack1) {
			if(TheGame._character1.getLives() == 0) {
				_won = true;
				_staticimage = new Image("tootboss/won.png");
				TheGame.setText(new Image("tootboss/text/won.png"));
			}
			
		}
		
		if(_counter2 == 0) {
			if(_text == 15) {
				_fightstarted = true;
				_immune = false;
				_counter3 = 0;
			}
			if(!_fightstarted){
			if(_text != 13){
			TheGame.setText(new Image("tootboss/text/" + _text + ".png"));
			} else {
				String a = "b";
				if(TheGame._character1.getLives() == 2){
					a = "a";
				}
				TheGame.setText(new Image("tootboss/text/" + _text +  a + ".png"));	
			}
			}
			
		}
		if(_fightstarted) {
			if(_counter3 == 120 && !_won && !_attack1 && !_dead) {
				attack1();
			}
			if(_health < 200 && !_raged && !_attack1) {
				_raged = true;
				TheGame.setText(new Image("tootboss/text/17.png"));	
				_staticimage = new Image("tootboss/mad.png");
			}
		}
		if(_counter2 == 240 && _text != 12) {
			TheGame.stopText();
			_text++;
			if(!_fightstarted){
			_counter2 = -20;
			}
		}
		if(_counter2 == 240 && _text == 12) {
			TheGame.stopText();
			attack1();
			_text++;
		}
		if(_attack1) {
			executeAttack1();
		}
	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		
	}
	
	public void attack1() {
		_attack1 = true;
		_counter4 = 0;
		_yvelocity = 5;
		TheGame.playSound("/tootboss/sounds/drop.wav");
		
	}
	
	public void executeAttack1() {
		
		if(_counter4 == 30) {
			_yvelocity = 0;
		}
		if(_counter4 == 45) {
			if(!_raged){
			if(_attack1var == 0) {
				TheGame.setText(new Image("tootboss/text/15.png"));
				_attack1var = 1;
			} else {
				TheGame.setText(new Image("tootboss/text/16.png"));
				_attack1var = 0;
			}
			}
			Hitbox a = new HitboxImpl("shot", this, false, _x, _y+40, 20, 20, -7, 0, 0, 1, new Image("tootboss/shot.png"));
			a.setCircle(true);
			TheGame._attacks.add(a);
			TheGame.playSound("/tootboss/sounds/shot.wav");
		}
		if(_raged) {
			if(_counter4 > 45 && _counter % 5 == 0 && _counter4 < 85) {
				Hitbox a = new HitboxImpl("shot", this, false, _x, _y+40, 20, 20, -7, 0, 0, 1, new Image("tootboss/shot.png"));
				a.setCircle(true);
				TheGame._attacks.add(a);
				TheGame.playSound("/tootboss/sounds/shot.wav");
			}
		}
		if(_counter4 == 70) {
			_yvelocity = -5;
		}
		if(_counter4 == 100) {
			_yvelocity = 0;
			_attack1 = false;
			_counter2 = -20;
			_counter3 = 0;
			TheGame.stopText();
			
		}
	}

}
