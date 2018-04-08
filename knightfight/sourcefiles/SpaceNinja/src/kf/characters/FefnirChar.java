package kf.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.ExplodingHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.MeleeHitbox;
import kf.OffsetHitbox;
import kf.Platform;
import kf.TheGame;

public class FefnirChar extends CharacterImpl {

	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private ExplodingHitbox _gunk1 = new ExplodingHitbox("fefbomb1", this, false, 0, 0, 0, 0, 0, 0, 0, 0, new Image("fefnir/bomb.png"), new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25));
	private ExplodingHitbox _gunk2 = new ExplodingHitbox("fefbomb2", this, false, 0, 0, 0, 0, 0, 0, 0, 0, new Image("fefnir/bomb.png"), new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25));
	private ExplodingHitbox _gunk3 = new ExplodingHitbox("fefbomb3", this, false, 0, 0, 0, 0, 0, 0, 0, 0, new Image("fefnir/bomb.png"), new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25));
	private ExplodingHitbox _gunk4 = new ExplodingHitbox("fefbomb4", this, false, 0, 0, 0, 0, 0, 0, 0, 0, new Image("fefnir/bomb.png"), new MeleeHitbox("gunk2", this, 0, 0, 64, 60, 25, 25));
	private boolean _gunk1ex;
	private boolean _gunk2ex;
	private boolean _gunk3ex;
	private boolean _gunk4ex;
	private double _holder;
	private int _gunk1counter;
	private int _gunk2counter;
	private int _gunk3counter;
	private int _gunk4counter;
	private boolean _canattack3;
	private boolean _reset = false;
	private int _cd1;
	private int _ucounter;
	private boolean _keepulting;
	
	public FefnirChar(String ID) {
		super(ID);
		_width = (int)(50*1.6);
		_height = (int)(46*1.6);
		_speedfactor = .85;
		_damagefactor = .85;
		if(_facing.equals("right")){
			_image = new Image("fefnir/fefnir.png");
		} else {
			_image = new Image("fefnir/fefnirleft.png");
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1 && !_attack2 && !_attack3 && !_attacku&& !_intro) {
			_width = (int)(50*1.6);
			_height = (int)(46*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/fefnir.png");
			} else {
				_image = new Image("fefnir/fefnirleft.png");
			}
		}
		if(_reset) {
			_y+=30;
			_reset = false;
		}
		if(_attack1) {
			executeAttack1();
		}
		if(_attack2) {
			executeAttack2();
		}
		if(_attack3) {
			executeAttack3();
		}
		if(_attacku) {
			executeAttackU();
		}
		if(_onplatform) {
			_canattack3 = true;
		}
		if(_keepulting) {
			if(_ucounter % 3 == 0){
			Random r = new Random();
			TheGame._attacks.add(new HitboxImpl("ultdown", this, false, r.nextInt(830), 
					-20, (int)(28*1.6), (int)(32*1.6), 0, 15, 30, 30, new Image("fefnir/ult/ultdown.png")));
			}
			if(_ucounter >= 27) {
				Random r = new Random();
				TheGame._attacks.add(new HitboxImpl("ultdown", this, false, r.nextInt(830), 
						-20, (int)(28*1.6), (int)(32*1.6), 0, 15, 30, 30, new Image("fefnir/ult/ultdown.png")));
				
			}
			if(_ucounter == 33) {
				_keepulting = false;
			}
		
		}
		if(_gunk1ex){
			for(Platform p : TheGame.getPlatforms()){
				if(_gunk1.getX() > p.getX() && _gunk1.getX() < (p.getX() + p.getWidth())|| _gunk1.isGone() && _counter >5){
				if(_gunk1.getY() + _gunk1.getHeight() >= p.getY()- 18 || _gunk1.isGone() && _counter >5) {
				_gunk1.explode();
				_gunk1counter = 0;
				_gunk1ex = false;
				TheGame.playSound("/fefnir/explode1.wav");
			}
			}
			}
		}
		if(_gunk2ex){
			for(Platform p : TheGame.getPlatforms()){
				if(_gunk2.getX() > p.getX() && _gunk2.getX() < (p.getX() + p.getWidth())|| _gunk2.isGone() && _counter >5){
				if(_gunk2.getY() + _gunk2.getHeight() >= p.getY() - 18 || _gunk2.isGone() && _counter >5) {
				_gunk2.explode();
				_gunk2counter = 0;
				_gunk2ex = false;
				TheGame.playSound("/fefnir/explode1.wav");
			}
			}
			}
		}
		if(_gunk3ex){
			for(Platform p : TheGame.getPlatforms()){
				if(_gunk3.getX() > p.getX() && _gunk3.getX() < (p.getX() + p.getWidth())|| _gunk3.isGone() && _counter >5){
				if(_gunk3.getY() + _gunk3.getHeight() >= p.getY()- 18 || _gunk3.isGone() && _counter >5) {
				_gunk3.explode();
				_gunk3counter = 0;
				_gunk3ex = false;
				TheGame.playSound("/fefnir/explode1.wav");
			}
			}
			}
		}
		if(_gunk4ex){
			for(Platform p : TheGame.getPlatforms()){
				if(_gunk4.getX() > p.getX() && _gunk4.getX() < (p.getX() + p.getWidth())|| _gunk4.isGone() && _counter >5){
				if(_gunk4.getY() + _gunk4.getHeight() >= p.getY()- 18 || _gunk4.isGone() && _counter >5) {
				_gunk4.explode();
				_gunk4counter = 0;
				_gunk4ex = false;
				TheGame.playSound("/fefnir/explode.wav");
			}
			}
			}
		}
			
		
		if(_gunk1counter == 10) {
			TheGame.clearHitboxes("fefexp1", this);
		}
		if(_gunk2counter == 10) {
			TheGame.clearHitboxes("fefexp2", this);
		}
		if(_gunk3counter == 10) {
			TheGame.clearHitboxes("fefexp3", this);
		}
		if(_gunk4counter == 10) {
			TheGame.clearHitboxes("fefexp4", this);
		}
	}
	
	
	@Override
	public Image getStockImage() {
		return new Image("fefnir/stock.png");
	}

	@Override
	public void attack1() {
		if(_cd1 == 0){
		_xvelocity = 0;
		_canact = false;
		_counter = 0;
		_width = (int)(53*1.6);
		_height = (int)(46*1.6);
		_attack1 = true;
		_cd1 = 45;
		_holder = _damage;
		if(_facing.equals("right")){
			_image = new Image("fefnir/shot1.png");
		} else {
			_image = new Image("fefnir/shot1left.png");
		}
		TheGame.playSound("/fefnir/charge.wav");
		}
	}
	
	public void executeAttack1() {
		if(_damage != _holder) {
			_attack1 = false;
			_canact = true;
		}
		
		
		if(_counter == 2) {
			_width = (int)(53*1.6);
			_height = (int)(47*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/shot2.png");
			} else {
				_image = new Image("fefnir/shot2left.png");
			}
		}
		if(_counter == 20) {
			_width = (int)(52*1.6);
			_height = (int)(46*1.6);
			_y+=1;
			int x;
			double v;
			Image i;
			if(_facing.equals("right")){
				_image = new Image("fefnir/shot3.png");
				_xvelocity = -7;
				x = _width - 8;
				v = 15;
				i = new Image("fefnir/shot.png");
			} else {
				_image = new Image("fefnir/shot3left.png");
				_xvelocity = 7;
				x = -(int)(26*1.6);
				v = -15;
				i = new Image("fefnir/shotleft.png");
			}
			_xtumbling = true;
			Hitbox attack = new HitboxImpl("fefireball", this, false, _x+x, _y, (int)(32*1.6), (int)(28*1.6), v, 0, 16, 16, i);

			TheGame._attacks.add(attack);
			TheGame.playSound("/fefnir/shot.wav");
		}
		if(_counter == 25) {
			_width = (int)(53*1.6);
			_height = (int)(47*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/shot2.png");
			} else {
				_image = new Image("fefnir/shot2left.png");
			}
		}
		if(_counter == 30) {
			_width = (int)(52*1.6);
			_height = (int)(46*1.6);
			_y+=1;
			int x;
			double v;
			Image i;
			if(_facing.equals("right")){
				_image = new Image("fefnir/shot3.png");
				_xvelocity = -7;
				x = _width - 8;
				v = 15;
				i = new Image("fefnir/shot.png");
			} else {
				_image = new Image("fefnir/shot3left.png");
				_xvelocity = 7;
				x = -(int)(26*1.6);
				v = -15;
				i = new Image("fefnir/shotleft.png");
			}
			_xtumbling = true;
			Hitbox attack = new HitboxImpl("fefireball", this, false, _x+x, _y, (int)(32*1.6), (int)(28*1.6), v, 0, 16, 16, i);
		
			TheGame._attacks.add(attack);
			TheGame.playSound("/fefnir/shot.wav");
		}
		if(_counter == 32) {
			_width = (int)(50*1.6);
			_height = (int)(47*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/shot4.png");
			} else {
				_image = new Image("fefnir/shot4left.png");
			}
		}
		if(_counter == 34) {
			_width = (int)(48*1.6);
			_height = (int)(56*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/shot5.png");
			} else {
				_image = new Image("fefnir/shot5left.png");
			}
		}
		if(_counter == 42) {
			_canact = true;
			_attack1 = false;
			_reset = true;
		}
	}

	@Override
	public void attack2() {
		
		_attack2 = true;
		_canact = false;
		_xvelocity = 0;
		_holder = _damage;
		_counter = 0;
		_width = (int)(53*1.6);
		_height = (int)(46*1.6);
		if(_facing.equals("right")){
			_image = new Image("fefnir/dash1.png");
		} else {
			_image = new Image("fefnir/dash1left.png");
			_x-=4;
		}
		TheGame.playSound("/fefnir/claw.wav");
	}
	
	public void executeAttack2() {
		if(_damage != _holder) {
			_attack2 = false;
			_canact = true;
		}
		if(_counter == 3) {
			_width = (int)(56*1.6);
			_height = (int)(46*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/dash2.png");
			} else {
				_image = new Image("fefnir/dash2left.png");
				_x-=4;
			}
		}
		if(_counter == 6) {
			_width = (int)(49*1.6);
			_height = (int)(45*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/dash3.png");
			} else {
				_image = new Image("fefnir/dash3left.png");
				_x+=10;
			}
		}
		if(_counter == 20) {
			_width = (int)(61*1.6);
			_height = (int)(39*1.6);
			int x;
			if(_facing.equals("right")){
				_image = new Image("fefnir/dash4.png");
				_xvelocity = 30;
				x = (int)(41*1.6);
			} else {
				_image = new Image("fefnir/dash4left.png");
				_x-=18;
				_xvelocity = -30;
				x = 0;
			}
			_xtumbling = true;
			TheGame._attacks.add(new OffsetHitbox("fefdash", this, x, 0, (int)(20*1.6), (int)(22*1.6), 28, 22, _clear));
			TheGame.playSound("/fefnir/dash.wav");
		}
		if(_counter == 38) {
			_width = (int)(63*1.6);
			_height = (int)(40*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/dash5.png");
			} else {
				_image = new Image("fefnir/dash5left.png");
			}
		}
		if(_counter == 41) {
			_width = (int)(42*1.6);
			_height = (int)(40*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/dash6.png");
			} else {
				_image = new Image("fefnir/dash6left.png");
				_x+=20;
			}
			TheGame.clearHitboxes("fefdash", this);
		}
		if(_counter == 44) {
			_attack2 = false;
			_canact = true;
		}
		
	}

	@Override
	public void attack3() {
		if(_canattack3){
		_attack3 = true;
		_counter = -10;
		_xvelocity = 0;
		_canact = false;
		_width = (int)(52*1.6);
		_height = (int)(40*1.6);
		_y+=8;
		if(_facing.equals("right")){
			_image = new Image("fefnir/jump1.png");
		} else {
			_image = new Image("fefnir/jump1left.png");
		}
	}
	}
	
	public void executeAttack3() {
		if(_counter == 10) {
			_yvelocity = -16;
			_width = (int)(42*1.6);
			_height = (int)(52*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/jump2.png");
			} else {
				_image = new Image("fefnir/jump2left.png");
				_x+=16;
			}
			TheGame.playSound("/fefnir/jump.wav");
		}
		if(_counter == 20) {
			_yvelocity = 0;
			_gravity = false;
			_canattack3 = false;
		}
		if(_counter == 26) {
			int d;
			int v;
			if(_facing.equals("right")){
				d = (int)(29*1.6);
				v = 0;
			} else {
				d = 1;
				v = 0;
			}
			_gunk1ex = true;
			MeleeHitbox pgunk = new MeleeHitbox("fefexp1", this, 0, 0, (int)(33*1.6), (int)(31*1.6), 20, 10, new Image("fefnir/explosion.png"));
			//pgunk.setHOrientation(true);
			_gunk1 = new ExplodingHitbox("fefbomb1", this, true, _x+d, _y + (int)(43*1.6), 18, 12, v, 2, 0, 0, new Image("fefnir/bomb.png"), pgunk);
			TheGame._attacks.add(_gunk1);
			TheGame.playSound("/fefnir/lob.wav");
		}
		if(_counter == 29) {
			int d;
			int v;
			_width = (int)(49*1.6);
			_height = (int)(52*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/jump3.png");
				d = (int)(29*1.6) + 2;
				v = 5;
			} else {
				_image = new Image("fefnir/jump3left.png");
				_x-=10;
				d = 1;
				v = -5;
			}
			_gunk2ex = true;
			MeleeHitbox pgunk = new MeleeHitbox("fefexp2", this, 0, 0, (int)(33*1.6), (int)(31*1.6), 20, 10, new Image("fefnir/explosion.png"));
			//pgunk.setHOrientation(true);
			_gunk2 = new ExplodingHitbox("fefbomb2", this, true, _x+d, _y + (int)(43*1.6), 18, 12, v, 2, 0, 0, new Image("fefnir/bomb.png"), pgunk);
			TheGame._attacks.add(_gunk2);
			TheGame.playSound("/fefnir/lob.wav");
		}
		if(_counter == 32) {
			_width = (int)(49*1.6);
			_height = (int)(51*1.6);
			int d;
			double v;
			if(_facing.equals("right")){
				_image = new Image("fefnir/jump4.png");
				d = (int)(29*1.6) + 4;
				v = 10;
			} else {
				_image = new Image("fefnir/jump4left.png");
				d = 1;
				v = -10;
			}
			_gunk3ex = true;
			MeleeHitbox pgunk = new MeleeHitbox("fefexp3", this, 0, 0, (int)(33*1.6), (int)(31*1.6), 20, 10, new Image("fefnir/explosion.png"));
			//pgunk.setHOrientation(true);
			_gunk3 = new ExplodingHitbox("fefbomb3", this, true, _x+d, _y + (int)(43*1.6), 18, 12, v, 2, 0, 0, new Image("fefnir/bomb.png"), pgunk);
			TheGame._attacks.add(_gunk3);
			TheGame.playSound("/fefnir/lob.wav");
		}
		if(_counter == 35) {
			_width = (int)(49*1.6);
			_height = (int)(49*1.6);
			int d;
			double v;
			if(_facing.equals("right")){
				_image = new Image("fefnir/jump5.png");
				d = (int)(29*1.6) + 6;
				v = 15;
			} else {
				_image = new Image("fefnir/jump5left.png");
				d = 1;
				v = -15;
			}
			_gunk4ex = true;
			MeleeHitbox pgunk = new MeleeHitbox("fefexp4", this, 0, 0, (int)(33*1.6), (int)(31*1.6), 20, 10, new Image("fefnir/explosion.png"));
			//pgunk.setHOrientation(true);
			_gunk4 = new ExplodingHitbox("fefbomb4", this, true, _x+d, _y + (int)(43*1.6), 18, 12, v, 2, 0, 0, new Image("fefnir/bomb.png"), pgunk);
			TheGame._attacks.add(_gunk4);
			TheGame.playSound("/fefnir/lob.wav");
		}
		if(_counter == 40) {
			_gravity = true;
			_attack3 = false;
			_canact = true;
		}
	}

	@Override
	public void attackU() {
		if(_ultcharge >=150){
		_canact = false;
		_immune = true;
		_counter = 0;
		_ultcharge = 0;
		_attacku = true;
		_xvelocity = 0;
		_width = (int)(40*1.6);
		_height = (int)(41*1.6);
		_image = new Image("fefnir/ult/1.png");
		TheGame.playSound("/fefnir/ult/sound1.wav");
		}
	}
	
	public void executeAttackU() {
		if(_counter == 3) {
			_width = (int)(39*1.6);
			_height = (int)(46*1.6);
			_image = new Image("fefnir/ult/2.png");
		}if(_counter == 6) {
			_width = (int)(58*1.6);
			_height = (int)(46*1.6);
			_image = new Image("fefnir/ult/3.png");
		}if(_counter == 9) {
			_image = new Image("fefnir/ult/4.png");
		}if(_counter == 12) {
			_image = new Image("fefnir/ult/5.png");
		}
		if(_counter == 15) {
			_width = (int)(69*1.6);
			_height = (int)(46*1.6);
			_image = new Image("fefnir/ult/6.png");
		}
		if(_counter == 18) {
			_image = new Image("fefnir/ult/7.png");
		}
		if(_counter == 21) {
			_width = (int)(49*1.6);
			_height = (int)(42*1.6);
			_image = new Image("fefnir/ult/8.png");
		}
		if(_counter == 24) {
			_width = (int)(48*1.6);
			_height = (int)(43*1.6);
			_image = new Image("fefnir/ult/9.png");
			TheGame.playSound("/fefnir/ult/sound2.wav");
		}
		if(_counter == 27) {
			_width = (int)(51*1.6);
			_height = (int)(43*1.6);
			_image = new Image("fefnir/ult/10.png");
		}
		if(_counter == 30) {
			_width = (int)(37*1.6);
			_height = (int)(38*1.6);
			_image = new Image("fefnir/ult/11.png");
			_y+=7;
		}
		if(_counter == 33) {
			_width = (int)(37*1.6);
			_height = (int)(69*1.6);
			_image = new Image("fefnir/ult/12.png");
		}
		if(_counter == 36) {
			_width = (int)(37*1.6);
			_height = (int)(67*1.6);
			_image = new Image("fefnir/ult/13.png");
		}
		if(_counter >36 && _counter % 5 == 0) {
			TheGame.playSound("/fefnir/ult/shot.wav");
		}
		if(_counter == 39) {
			_width = (int)(37*1.6);
			_height = (int)(66*1.6);
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), _y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 42) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 45) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), _y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 48) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 51) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 54) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 57) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 60) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 63) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter >= 64 && _counter % 3 == 0){
			Random r = new Random();
			TheGame._attacks.add(new HitboxImpl("ultdown", this, false, r.nextInt(830), 
					-20, (int)(28*1.6), (int)(32*1.6), 0, 15, 30, 30, new Image("fefnir/ult/ultdown.png")));
		
		}
		if(_counter == 66) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 69) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 72) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 75) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 78) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 81) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 84) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 87) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 90) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
			
		}
		if(_counter == 93) {
			_image = new Image("fefnir/ult/14.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(-4*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 96) {
			_image = new Image("fefnir/ult/15.png");
			TheGame._attacks.add(new HitboxImpl("ultup", this, false, _x+(int)(13*1.6), 
					_y+(int)(-27*1.6), (int)(28*1.6), (int)(32*1.6), 0, -15, 30, 30, new Image("fefnir/ult/ultup.png")));
		}
		if(_counter == 99) {
			_width = (int)(37*1.6);
			_height = (int)(59*1.6);
			_y += 9;
			_image = new Image("fefnir/ult/16.png");
		}
		if(_counter == 102) {
			_width = (int)(51*1.6);
			_height = (int)(57*1.6);
			_y+=2;
			_image = new Image("fefnir/ult/17.png");
		}
		if(_counter == 105) {
			_width = (int)(51*1.6);
			_height = (int)(45*1.6);
			_y+=18;
			_image = new Image("fefnir/ult/18.png");
		}
		if(_counter == 108) {
			_immune = false;
			_canact = true;
			_attacku = false;
			_ucounter = 0;
			_keepulting = true;
		}
		
	}

	@Override
	public Color getColor() {
		return Color.LIGHTPINK;
	}
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd1 > 0) {
			_cd1--;
		}
		_gunk1counter++;
		_gunk2counter++;
		_gunk3counter++;
		_gunk4counter++;
		_ucounter++;
	}
	
	@Override
	public void doIntro() {
		if(_counter == 0) {
			_width = (int)(59*1.6);
			_height = (int)(58*1.6);
			if(_facing.equals("right")) {
				_image = new Image("fefnir/intro1.png");
			} else {
				_image = new Image("fefnir/intro1left.png");
				_x-=(int)(17*1.6);
			}
		}
		if(_counter == 10) {
			if(_facing.equals("right")) {
				_image = new Image("fefnir/intro2.png");
			} else {
				_image = new Image("fefnir/intro2left.png");
			}
		}
		if(_counter == 14) {
			if(_facing.equals("right")) {
				_image = new Image("fefnir/intro3.png");
			} else {
				_image = new Image("fefnir/intro3left.png");
			}
			TheGame.playSound("/fefnir/claw.wav");
		}
		if(_counter == 18) {
			if(_facing.equals("right")) {
				_image = new Image("fefnir/intro4.png");
			} else {
				_image = new Image("fefnir/intro4left.png");
			}
		}
		if(_counter == 40) {
			_width = (int)(50*1.6);
			_height = (int)(46*1.6);
			if(_facing.equals("right")){
				_image = new Image("fefnir/fefnir.png");
			} else {
				_image = new Image("fefnir/fefnirleft.png");
				_x+=(int)(17*1.6);
			}
			_y+=(int)(12*1.6);
		}
	}

}
