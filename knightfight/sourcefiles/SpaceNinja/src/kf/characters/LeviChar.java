package kf.characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kf.AnimatedHitbox;
import kf.CharLinkedHitbox;
import kf.Hitbox;
import kf.HitboxImpl;
import kf.TheGame;

public class LeviChar extends CharacterImpl {
	private boolean _attack1;
	private boolean _attack2;
	private boolean _attack3;
	private boolean _canattack3 = true;
	private boolean _canattack2 = true;
	private boolean _canattack1 = true;
	private int _dashcharges = 2;
	private int _cd2 = 0;
	private String _ultdirection;
	private List<Image> _sprites = new ArrayList<Image>();
	private List<Image> _spritesleft = new ArrayList<Image>();
	private List<Image> _ultshots = new ArrayList<Image>();
	private List<Image> _ultshotsl = new ArrayList<Image>();
	private List<Image> _ult = new ArrayList<Image>();
	private List<Image> _ultl = new ArrayList<Image>();
	private int _ultcounter;
	private int _spriteindex;
	// which list to use for ult
	private List<Image> _ultd;
	private List<Image> _shotd;
	private int _ultx = 0;
	private int _spritecounter = 0;
	
	public LeviChar(String ID) {
		super(ID);
		_width = (int)(33*1.5);
		_height = (int)(52*1.5);
		_damagefactor = 1.1;
		_speedfactor = 1.1;
		_ultshots.add(new Image("levi/ult/shot1.gif"));
		_ultshots.add(new Image("levi/ult/shot2.gif"));
		_ultshots.add(new Image("levi/ult/shot3.gif"));
		_ultshots.add(new Image("levi/ult/shot4.gif"));
		_ultshotsl.add(new Image("levi/ult/shot1left.gif"));
		_ultshotsl.add(new Image("levi/ult/shot2left.gif"));
		_ultshotsl.add(new Image("levi/ult/shot3left.gif"));
		_ultshotsl.add(new Image("levi/ult/shot4left.gif"));
		_ult.add(new Image("levi/ult/ult2.gif"));
		_ult.add(new Image("levi/ult/ult3.gif"));
		_ult.add(new Image("levi/ult/ult4.gif"));
		_ult.add(new Image("levi/ult/ult5.gif"));
		_ultl.add(new Image("levi/ult/ult2left.gif"));
		_ultl.add(new Image("levi/ult/ult3left.gif"));
		_ultl.add(new Image("levi/ult/ult4left.gif"));
		_ultl.add(new Image("levi/ult/ult5left.gif"));
		_sprites.add(new Image("levi/stance/1.gif"));
		_sprites.add(new Image("levi/stance/2.gif"));
		_sprites.add(new Image("levi/stance/3.gif"));
		_sprites.add(new Image("levi/stance/4.gif"));
		_spritesleft.add(new Image("levi/stance/1left.gif"));
		_spritesleft.add(new Image("levi/stance/2left.gif"));
		_spritesleft.add(new Image("levi/stance/3left.gif"));
		_spritesleft.add(new Image("levi/stance/4left.gif"));
	}

	
	
	@Override
	public void render(GraphicsContext gc) {
		super.render(gc);
		if(!_attack1&&!_attack2&&!_attack3&&!_attacku&& !_intro) {
			_width = (int)(33*1.5);
			_height = (int)(52*1.5);
			if(_facing.equals("right")) {
				if(_spritecounter % 7 == 0) {
					_image = _sprites.get(_spriteindex);
					if(_spriteindex < 3) {
						_spriteindex++;
					} else {
						_spriteindex = 0;
					}
				}
			} else {
				if(_spritecounter % 7 == 0) {
					_image = _spritesleft.get(_spriteindex);
					if(_spriteindex < 3) {
						_spriteindex++;
					} else {
						_spriteindex = 0;
					}
				}
			}
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
			_dashcharges = 2;
		}
		
	}
	
	@Override
	public Image getStockImage() {
		return new Image("levi/stock.gif");
	}

	@Override
	public void attack1() {
		if(_canattack1 && !_attack1 && !_attack3){
		_canact = false;
		_xvelocity = 0;
		_counter = 0;
		_attack1 = true;
		_width = (int)(41*1.5);
		_height = (int)(55*1.5);
		if(_facing.equals("right")){
			_image = new Image("levi/attack1/shot1.gif");
		} else {
			_image = new Image("levi/attack1/shot1left.gif");
		}
		}
	}
	
	public void executeAttack1() {
		if(_xtumbling) {
			_attack1 = false;
			_canact = true;
		}
		if(_counter == 2) {
			_width = (int)(37*1.5);
			_height = (int)(44*1.5);
			_y+=(int)(11*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot2.gif");
			} else {
				_image = new Image("levi/attack1/shot2left.gif");
			}
			TheGame.playSound("/levi/sounds/shot.wav");
			
		}
		
		if(_counter == 4) {
			_width = (int)(62*1.5);
			_height = (int)(40*1.5);
			_y+=(int)(4*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot3.gif");
				_x-=(int)(20*1.5);
			} else {
				_image = new Image("levi/attack1/shot3left.gif");
			}
		}
		if(_counter == 6) {
			_width = (int)(40*1.5);
			_height = (int)(74*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot4.gif");
				_x+=(int)(20*1.5);
			} else {
				_image = new Image("levi/attack1/shot4left.gif");
			}
		}
		if(_counter == 8) {
			_width = (int)(44*1.5);
			_height = (int)(62*1.5);
			_y+=18;
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot5.gif");
			} else {
				_image = new Image("levi/attack1/shot5left.gif");
			}
		}
		if(_counter == 10) {
			_width = (int)(60*1.5);
			_height = (int)(42*1.5);
			_y+=30;
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot6.gif");
			} else {
				_image = new Image("levi/attack1/shot6left.gif");
				_x-=10;
			}
		}
		if(_counter == 20) {
			int x;
			double v;
			List<Image> i = new ArrayList<Image>();
			if(_facing.equals("right")){
				x = (int)(31*1.5);
				v = 10;
				i.add(new Image("levi/attack1/drag1.gif"));
				i.add(new Image("levi/attack1/drag2.gif"));
				i.add(new Image("levi/attack1/drag3.gif"));
				i.add(new Image("levi/attack1/drag2.gif"));
			} else {
				x = (int)(-55*1.5);
				v = -10;
				i.add(new Image("levi/attack1/drag1left.gif"));
				i.add(new Image("levi/attack1/drag2left.gif"));
				i.add(new Image("levi/attack1/drag3left.gif"));
				i.add(new Image("levi/attack1/drag2left.gif"));
			}
			Hitbox attack = new AnimatedHitbox("icedragon", this, false, _x+x, _y+1, (int)(90*1.5), (int)(24*1.5), v, 0, 20, 20, i, 3);
			
			if(_facing.equals("right")){
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/levi/sounds/drag.wav");
			
			
		}
		if(_counter == 40) {
			_attack1 = false;
			_canact = true;
		}
	}

	@Override
	public void attack2() {
		if(_dashcharges > 0 && _cd2 == 0 && !_attack3 && _canattack2){
		_canact = false;
		_counter = 0;
		_attack2 = true;
		_cd2 = 30;
		_dashcharges--;
		_canattack3 = true;
	    _width = (int)(45*1.5);
	    _height = (int)(41*1.5);
	    if(_facing.equals("right")){
			_image = new Image("levi/attack2/dash1.gif");
			_xvelocity = 16;
		} else {
			_image = new Image("levi/attack2/dash1left.gif");
			_xvelocity = -16;
		}
	    TheGame.playSound("/levi/sounds/dash.wav");
		}
	}
	
	public void executeAttack2() {
		_yvelocity = 0;
		if(_xtumbling) {
			_xvelocity = 0;
			_canact = true;
			_attack2 = false;
		}
		if(_counter == 3) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack2/dash2.gif");
			} else {
				_image = new Image("levi/attack2/dash2left.gif");
			}
		}
		if(_counter == 6) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack2/dash3.gif");
			} else {
				_image = new Image("levi/attack2/dash3left.gif");
			}
		}
		if(_counter == 13){
			_xvelocity = 0;
			_attack2 = false;
			_canact = true;
		}
	}

	@Override
	public void attack3() {
		if(_canattack3 && !_attack1 && !_attack2){
		_canact = false;
		_counter = 0;
		_xvelocity = 0;
		_attack3 = true;
		_width = (int)(44*1.5);
		_height = (int)(46*1.5);
		if(_facing.equals("right")){
			_image = new Image("levi/attack3/jump1.gif");
			_x-=10;
		} else {
			_image = new Image("levi/attack3/jump1left.gif");
		}
		TheGame.playSound("/levi/sounds/jump.wav");
		}
	}
	
	public void executeAttack3() {
		if(_counter == 2) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack3/jump2.gif");
			} else {
				_image = new Image("levi/attack3/jump2left.gif");
			}
		}
		if(_counter == 4) {
			_width = (int)(91*1.5);
			_height = (int)(91*1.5);
			_y-=(int)(45*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack3/jump3.gif");
				_xvelocity = 20;
			} else {
				_image = new Image("levi/attack3/jump3left.gif");
				_xvelocity = -20;
			}
			_yvelocity = -15;
			_xtumbling = true;
			
			Hitbox attack = new CharLinkedHitbox("levijump", this, 18, 20);
			if(_facing.equals("right")){
				attack.setForceRight(true);
			} else {
				attack.setForceLeft(true);
			}
			TheGame._attacks.add(attack);

			_canattack3 = false;
		}
		if(_counter == 7) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack3/jump4.gif");
			} else {
				_image = new Image("levi/attack3/jump4left.gif");
			}
			_canattack3 = false;
		}
		if(_counter == 10) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack3/jump5.gif");
			} else {
				_image = new Image("levi/attack3/jump5left.gif");
			}
			_canattack3 = false;
		}
		if(_counter == 13) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack3/jump6.gif");
			} else {
				_image = new Image("levi/attack3/jump6left.gif");
			}
			Hitbox attack = new HitboxImpl("ljumpshot", this, false, _x+(int)(33*1.5), _y+(int)(75*1.5), (int)(26*1.5), (int)(16*1.5), 0 , 17, 12, 12, new Image("levi/attack3/shot.gif"));
			attack.setHOrientation(true);
			TheGame._attacks.add(attack);
		}
		if(_counter == 16) {
			_width = (int)(76*1.5);
			_height = (int)(48*1.5);
			_y+=30;
			if(_facing.equals("right")){
				_image = new Image("levi/attack3/jump7.gif");
			} else {
				_image = new Image("levi/attack3/jump7left.gif");
			}
			TheGame.clearHitboxes("levijump", this);
		}
		if(_counter == 30){
			_attack3 = false;
			_canact = true;
		}
		
		
	}

	@Override
	public void attackU() {
		if(_ultcharge >=150){
		_counter = 0;
		_attacku = true;
		_ultdirection = _facing;
		_canattack1 = false;
		_ultcharge = 0;
		_canattack2 = false;
		_canattack3 = false;
		_width = (int)(43*1.5);
		_height = (int)(56*1.5);
		_immune = true;
		TheGame.playSound("/levi/sounds/ult1.wav");
		if(_ultdirection.equals("right")) {
			_ultd = _ult;
			_shotd = _ultshots;
			_ultx = 45;
		} else {
			_ultd = _ultl;
			_shotd = _ultshotsl;
			_ultx = -15;
		}
		}
	}
	
	public void executeAttackU() {
		if(_counter % 3 == 0 && _counter < 300) {
				_image = _ultd.get(_spriteindex);
			if(_spriteindex < 3) {
					_spriteindex++;
			} else {
					_spriteindex = 0;
			}
		}
		if(_counter >= 50 && _counter % 9 == 0 && _counter < 300) {
			Hitbox attack = new AnimatedHitbox("leviultshot", this, false, _x+_ultx, _y+27, 31, 21, 13, 0, 18, 18,_shotd,3 );
			if(_ultdirection.equals("right")) {
				attack.setForceRight(true);
				
			} else {
				attack.setForceLeft(true);
				attack.setXVelocity(-13);
			}
			TheGame._attacks.add(attack);
			TheGame.playSound("/levi/sounds/ult.wav");
		}
		if(_counter == 300) {
			_attacku = false;
			_canattack1 = true;
			_canattack2 = true;
			_canattack3 = true;
			_immune = false;
		}
		
	}

	@Override
	public Color getColor() {
		return Color.LIGHTBLUE;
	}
	
	@Override
	public void incrementCounter() {
		super.incrementCounter();
		if(_cd2 > 0) {
			_cd2--;
		}
		if(_attacku) {
			_ultcounter++;
		}
		_spritecounter++;
	}
	@Override
	public void doIntro() {
		if(_counter == 0) {
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot1.gif");
			} else {
				_image = new Image("levi/attack1/shot1left.gif");
			}
		}
		if(_counter == 2) {
			_width = (int)(37*1.5);
			_height = (int)(44*1.5);
			_y+=(int)(11*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot2.gif");
			} else {
				_image = new Image("levi/attack1/shot2left.gif");
			}
			TheGame.playSound("/levi/sounds/shot.wav");
			
		}
		
		if(_counter == 4) {
			_width = (int)(62*1.5);
			_height = (int)(40*1.5);
			_y+=(int)(4*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot3.gif");
				_x-=(int)(20*1.5);
			} else {
				_image = new Image("levi/attack1/shot3left.gif");
			}
		}
		if(_counter == 6) {
			_width = (int)(40*1.5);
			_height = (int)(74*1.5);
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot4.gif");
				_x+=(int)(20*1.5);
			} else {
				_image = new Image("levi/attack1/shot4left.gif");
			}
		}
		if(_counter == 8) {
			_width = (int)(44*1.5);
			_height = (int)(62*1.5);
			_y+=18;
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot5.gif");
			} else {
				_image = new Image("levi/attack1/shot5left.gif");
			}
		}
		if(_counter == 10) {
			_width = (int)(60*1.5);
			_height = (int)(42*1.5);
			_y+=30;
			if(_facing.equals("right")){
				_image = new Image("levi/attack1/shot6.gif");
			} else {
				_image = new Image("levi/attack1/shot6left.gif");
				_x-=10;
			}
		}
	}

}
