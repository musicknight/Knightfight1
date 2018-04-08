package kf;

import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import kf.characters.BuuChar;
import kf.characters.Character;
import kf.characters.CloudChar;
import kf.characters.EnchantChar;
import kf.characters.FefnirChar;
import kf.characters.FrankChar;
import kf.characters.GengarChar;
import kf.characters.GokuChar;
import kf.characters.JadenChar;
import kf.characters.LeviChar;
import kf.characters.MermaidChar;
import kf.characters.MetaChar;
import kf.characters.NarutoChar;
import kf.characters.NinjaChar;
import kf.characters.PhantomChar;
import kf.characters.SansChar;
import kf.characters.ShovelChar;
import kf.characters.SpecterChar;
import kf.characters.ZeroChar;
import kf.stages.CampStage;
import kf.stages.DarkMindStage;
import kf.stages.DrawciaStage;
import kf.stages.EmeraldStage;
import kf.stages.FDStage;
import kf.stages.FountainStage;
import kf.stages.GiygasStage;
import kf.stages.MoonStage;
import kf.stages.Place;
import kf.stages.PokemonStage;
import kf.stages.SansStage;
import kf.stages.TowerStage;
import kf.stages.WilyStage;

public class TheGame extends Application {
	public static void main(String[] args) {
		new JFXPanel();
		launch(args);
	}

	public static Character _character1;
	public static Character _character2;
	public static Place _place;
	private static Canvas _canvas = new Canvas(900, 600);
	

	public static GraphicsContext _gc = _canvas.getGraphicsContext2D();
	private Stage _stage;
	private Scene _scene;
	private AnimationTimer _animationTimer;
	public static Set<Hitbox> _attacks = new HashSet<Hitbox>();
	public static Set<Backdrop> _backdrops = new HashSet<Backdrop>();
	public static List<Platform> _platforms = new ArrayList<Platform>();
	private boolean _player1picked = false;
	private boolean _player2picked = false;
	private boolean _stageselected = false;
	private static boolean _closed = false;
	//character buttons
	private Button _whiteboxselect = new Button("select");
	private Button _yellowboxselect = new Button("select");
	private Button _sansselect = new Button("select");
	private Button _shovelselect = new Button("select");
	private Button _cloudselect = new Button("select");
	private Button _gokuselect = new Button("select");
	private Button _arielselect = new Button("select");
	private Button _gengarselect = new Button("select");
	private Button _frankselect = new Button("select");
	private Button _jadenselect = new Button("select");
	private Button _ninjaselect = new Button("select");
	private Button _zeroselect = new Button("select");
	private Button _specterselect = new Button("select");
	private Button _fefnirselect = new Button("select");
	private Button _metaselect = new Button("select");
	private Button _phantomselect = new Button("select");
	private Button _enchantselect = new Button("select");
	private Button _leviselect = new Button("select");
	private Button _narutoselect = new Button("select");
	private Button _buuselect = new Button("select");
	//stage buttons
	private Button _emeraldselect = new Button("select");
	private Button _moonselect = new Button("select");
	private Button _finalselect = new Button("select");
	private Button _giygasselect = new Button("select");
	private Button _shovelfselect = new Button("select");
	private Button _undertselect = new Button("select");
	private Button _kirbyselect = new Button("select");
	private Button _kirby2select = new Button("select");
	private Button _kirby3select = new Button("select");
	private Button _pokemonselect = new Button("select");
	private Button _wilyselect = new Button("select");
	private Button _towerselect = new Button("select");
	//game start button
	private Button _start = new Button("start");
	//next/prev page button
	private Button _next = new Button(">");
	private Button _previous = new Button("<");
	private Group _root1 = new Group();
	public static boolean _rendering = true;
	private int _page = 1;
	private boolean _menusongstarted= false;
	private boolean _titlesongstarted = false;
	private boolean _gamestarted = false;
	
	private Button _replaybutton = new Button();

	public static double _gravity = 0.8;
	
	private static BasicPlayer _player = new BasicPlayer();

	public void start(Stage stage1) {
		FXMLLoader loader = new
		 FXMLLoader(this.getClass().getResource("watchout.fxml"));
		 loader.setController(this);
		
		stage1.setTitle("Knightfight");
		_stage = stage1;
		TheGame m = this;
		_stage.setOnCloseRequest(m::closeWindow);
		_root1 = new Group();
		_scene = new Scene(_root1);
		_scene.setFill(Color.BLACK);
		stage1.setScene(_scene);
		_root1.getChildren().add(_canvas);
		// character selection screen

		// _player1picked = true;

		// _player1picked = true;
		// Group root = new Group();
		// _scene = new Scene(root);
		// _scene.setFill(Color.BLACK);
		// stage1.setScene(_scene);

		// root.getChildren().add(_canvas);

		final long startNanoTime = System.nanoTime();

		_animationTimer = new AnimationTimer() {

			private long _last = 0;

			public void handle(long currentNanoTime) {
				if(_rendering) {
				_gc.clearRect(0, 0, 1000, 1000);
				}
				if(!_gamestarted){
					if(!_titlesongstarted){
						playStageSong("/sounds/title.mp3");
						_titlesongstarted = true;
					}
					
					_gc.drawImage(new Image("titlescreen.png"), 0, 0, 900, 600);
					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial Black", 100));
					_gc.fillText("KNIGHTFIGHT", 50, 150);
					_gc.setFont(Font.font("Arial Black", 20));
					_gc.fillText("by musicknight", 360, 200);
					if (!_root1.getChildren().contains(_start)) {
						_start.setMinWidth(100);
						_start.setMinHeight(50);
						_start.setLayoutX(400);
						_start.setLayoutY(300);
						_root1.getChildren().add(_start);

						_start.setOnMousePressed(m::handleButtonPress);
					}
				
				}else if (!_player1picked) {
					if(!_menusongstarted){
					playStageSong("/sounds/menu.mp3");
					_menusongstarted = true;
					}
					if(_menusongstarted && _player.getStatus()!=0 && !_closed){
						//playStageSong("/sounds/menu.mp3");
					}
						
					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial Black", 40));

					_gc.fillText("PLAYER 1, CHOOSE YOUR CHARACTER", 20, 60);
					if(_page == 1){
						_root1.getChildren().remove(_previous);
						if (!_root1.getChildren().contains(_next)) {
							_next.setMinWidth(50);
							_next.setMinHeight(100);
							_next.setLayoutX(850);
							_next.setLayoutY(300);
							_root1.getChildren().add(_next);

							_next.setOnMousePressed(m::handleButtonPress);
						}
					// zero
					_gc.setFill(Color.RED);
					_gc.drawImage(new Image("zero/zero.gif"), 100, 130, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("zero", 106, 120);
					if (!_root1.getChildren().contains(_zeroselect)) {
						_zeroselect.setMinWidth(50);
						_zeroselect.setMinHeight(25);
						_zeroselect.setLayoutX(100);
						_zeroselect.setLayoutY(180);
						_root1.getChildren().add(_zeroselect);

						_zeroselect.setOnMousePressed(m::handleButtonPress);
					}

					// ninja
					_gc.setFill(Color.PINK);
					_gc.drawImage(new Image("ninja/ninja.png"), 250, 130, 50, 50);
					_gc.fillText("ninja kirby", 228, 120);
					if (!_root1.getChildren().contains(_ninjaselect)) {

						_ninjaselect.setMinWidth(50);
						_ninjaselect.setMinHeight(25);
						_ninjaselect.setLayoutX(250);
						_ninjaselect.setLayoutY(180);
						_root1.getChildren().add(_ninjaselect);
						_ninjaselect.setOnMousePressed(m::handleButtonPress);
					}
					// sans
					_gc.setFill(Color.WHITE);
					_gc.drawImage(new Image("sans.png"), 400, 130, 50, 50);
					_gc.setFont(Font.font("Comic Sans MS", 20));
					_gc.fillText("sans", 405, 120);
					if (!_root1.getChildren().contains(_sansselect)) {

						_sansselect.setMinWidth(50);
						_sansselect.setMinHeight(25);
						_sansselect.setLayoutX(400);
						_sansselect.setLayoutY(180);
						_root1.getChildren().add(_sansselect);
						_sansselect.setOnMousePressed(m::handleButtonPress);

					}
					// shovel
					_gc.setFill(Color.AQUA);
					_gc.drawImage(new Image("shovel1right.png"), 550, 130, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("shovel knight", 515, 120);
					if (!_root1.getChildren().contains(_shovelselect)) {

						_shovelselect.setMinWidth(50);
						_shovelselect.setMinHeight(25);
						_shovelselect.setLayoutX(550);
						_shovelselect.setLayoutY(180);
						_root1.getChildren().add(_shovelselect);
						_shovelselect.setOnMousePressed(m::handleButtonPress);

					}
					// cloud
					_gc.setFill(Color.GRAY);
					_gc.drawImage(new Image("cloud/cloud.png"), 700, 130, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("cloud", 700, 120);
					if (!_root1.getChildren().contains(_cloudselect)) {

						_cloudselect.setMinWidth(50);
						_cloudselect.setMinHeight(25);
						_cloudselect.setLayoutX(700);
						_cloudselect.setLayoutY(180);
						_root1.getChildren().add(_cloudselect);
						_cloudselect.setOnMousePressed(m::handleButtonPress);

					}
					// goku
					_gc.setFill(Color.ORANGE);
					_gc.drawImage(new Image("goku/goku.png"), 100, 330, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("goku", 100, 320);
					if (!_root1.getChildren().contains(_gokuselect)) {
						_gokuselect.setMinWidth(50);
						_gokuselect.setMinHeight(25);
						_gokuselect.setLayoutX(100);
						_gokuselect.setLayoutY(380);
						_root1.getChildren().add(_gokuselect);

						_gokuselect.setOnMousePressed(m::handleButtonPress);
					}
					// ariel
					_gc.setFill(Color.RED);
					_gc.drawImage(new Image("mermaid/stock.png"), 250, 330, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("ariel", 250, 320);
					if (!_root1.getChildren().contains(_arielselect)) {
						_arielselect.setMinWidth(50);
						_arielselect.setMinHeight(25);
						_arielselect.setLayoutX(250);
						_arielselect.setLayoutY(380);
						_root1.getChildren().add(_arielselect);

						_arielselect.setOnMousePressed(m::handleButtonPress);
					}
					// gengar
					_gc.setFill(Color.PURPLE);
					_gc.drawImage(new Image("gengar/stock.png"), 400, 330, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("gengar", 395, 320);
					if (!_root1.getChildren().contains(_gengarselect)) {
						_gengarselect.setMinWidth(50);
						_gengarselect.setMinHeight(25);
						_gengarselect.setLayoutX(400);
						_gengarselect.setLayoutY(380);
						_root1.getChildren().add(_gengarselect);

						_gengarselect.setOnMousePressed(m::handleButtonPress);
					}
					// frank
					_gc.setFill(Color.BLUE);
					_gc.drawImage(new Image("frank/frank.gif"), 550, 330, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("frank", 553, 320);
					if (!_root1.getChildren().contains(_frankselect)) {
						_frankselect.setMinWidth(50);
						_frankselect.setMinHeight(25);
						_frankselect.setLayoutX(550);
						_frankselect.setLayoutY(380);
						_root1.getChildren().add(_frankselect);

						_frankselect.setOnMousePressed(m::handleButtonPress);
					}
					// jaden
					_gc.setFill(Color.DARKORANGE);
					_gc.drawImage(new Image("jaden/jaden.gif"), 700, 330, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("jaden", 703, 320);
					if (!_root1.getChildren().contains(_jadenselect)) {
						_jadenselect.setMinWidth(50);
						_jadenselect.setMinHeight(25);
						_jadenselect.setLayoutX(700);
						_jadenselect.setLayoutY(380);
						_root1.getChildren().add(_jadenselect);

						_jadenselect.setOnMousePressed(m::handleButtonPress);
					}
					// specter knight
					_gc.setFill(Color.DARKRED);
					_gc.drawImage(new Image("specter/stock.png"), 100, 520, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("specter knight", 69, 510);
					if (!_root1.getChildren().contains(_specterselect)) {
						_specterselect.setMinWidth(50);
						_specterselect.setMinHeight(25);
						_specterselect.setLayoutX(100);
						_specterselect.setLayoutY(570);
						_root1.getChildren().add(_specterselect);

						_specterselect.setOnMousePressed(m::handleButtonPress);
					}
					// fefnir
					_gc.setFill(Color.LIGHTPINK);
					_gc.drawImage(new Image("fefnir/fefnir.png"), 250, 520, 50, 50);
					_gc.fillText("fefnir", 250, 510);
					if (!_root1.getChildren().contains(_fefnirselect)) {

						_fefnirselect.setMinWidth(50);
						_fefnirselect.setMinHeight(25);
						_fefnirselect.setLayoutX(250);
						_fefnirselect.setLayoutY(570);
						_root1.getChildren().add(_fefnirselect);
						_fefnirselect.setOnMousePressed(m::handleButtonPress);
					}
					// metaknight
					_gc.setFill(Color.BLUE);
					_gc.drawImage(new Image("metaknight/meta.png"), 400, 520, 50, 50);
					_gc.fillText("meta knight", 377, 510);
					if (!_root1.getChildren().contains(_metaselect)) {

						_metaselect.setMinWidth(50);
						_metaselect.setMinHeight(25);
						_metaselect.setLayoutX(400);
						_metaselect.setLayoutY(570);
						_root1.getChildren().add(_metaselect);
						_metaselect.setOnMousePressed(m::handleButtonPress);
					}
					// phantom
					_gc.setFill(Color.GRAY);
					_gc.drawImage(new Image("phantom/phantom.png"), 550, 520, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("phantom", 535, 510);
					if (!_root1.getChildren().contains(_phantomselect)) {

						_phantomselect.setMinWidth(50);
						_phantomselect.setMinHeight(25);
						_phantomselect.setLayoutX(550);
						_phantomselect.setLayoutY(570);
						_root1.getChildren().add(_phantomselect);
						_phantomselect.setOnMousePressed(m::handleButtonPress);

					}
					// the enchantress
					_gc.setFill(Color.MAGENTA);
					_gc.drawImage(new Image("enchantress/select.png"), 700, 520, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("the enchantress", 660, 510);
					if (!_root1.getChildren().contains(_enchantselect)) {

						_enchantselect.setMinWidth(50);
						_enchantselect.setMinHeight(25);
						_enchantselect.setLayoutX(700);
						_enchantselect.setLayoutY(570);
						_root1.getChildren().add(_enchantselect);
						_enchantselect.setOnMousePressed(m::handleButtonPress);

					}
					} else {
						if (!_root1.getChildren().contains(_previous)) {
							_previous.setMinWidth(50);
							_previous.setMinHeight(100);
							_previous.setLayoutX(0);
							_previous.setLayoutY(300);
							_root1.getChildren().add(_previous);

							_previous.setOnMousePressed(m::handleButtonPress);
						}
						_root1.getChildren().remove(_sansselect);
						_root1.getChildren().remove(_shovelselect);
						_root1.getChildren().remove(_cloudselect);
						_root1.getChildren().remove(_gokuselect);
						_root1.getChildren().remove(_arielselect);
						_root1.getChildren().remove(_gengarselect);
						_root1.getChildren().remove(_frankselect);
						_root1.getChildren().remove(_jadenselect);
						_root1.getChildren().remove(_ninjaselect);
						_root1.getChildren().remove(_zeroselect);
						_root1.getChildren().remove(_specterselect);
						_root1.getChildren().remove(_fefnirselect);
						_root1.getChildren().remove(_metaselect);
						_root1.getChildren().remove(_phantomselect);
						_root1.getChildren().remove(_enchantselect);
						_root1.getChildren().remove(_next);
						// leviathan
						_gc.setFill(Color.LIGHTBLUE);
						_gc.drawImage(new Image("levi/levi.gif"), 100, 130, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("leviathan", 86, 120);
						if (!_root1.getChildren().contains(_leviselect)) {
							_leviselect.setMinWidth(50);
							_leviselect.setMinHeight(25);
							_leviselect.setLayoutX(100);
							_leviselect.setLayoutY(180);
							_root1.getChildren().add(_leviselect);

							_leviselect.setOnMousePressed(m::handleButtonPress);
						}
						// naruto
						_gc.setFill(Color.YELLOW);
						_gc.drawImage(new Image("naruto/naruto1.png"), 250, 130, 50, 50);
						_gc.fillText("naruto", 247, 120);
						if (!_root1.getChildren().contains(_narutoselect)) {

							_narutoselect.setMinWidth(50);
							_narutoselect.setMinHeight(25);
							_narutoselect.setLayoutX(250);
							_narutoselect.setLayoutY(180);
							_root1.getChildren().add(_narutoselect);
							_narutoselect.setOnMousePressed(m::handleButtonPress);
						}
						// buu
						_gc.setFill(Color.PINK);
						_gc.drawImage(new Image("buu/stance1.png"), 400, 130, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("buu", 405, 120);
						if (!_root1.getChildren().contains(_buuselect)) {

							_buuselect.setMinWidth(50);
							_buuselect.setMinHeight(25);
							_buuselect.setLayoutX(400);
							_buuselect.setLayoutY(180);
							_root1.getChildren().add(_buuselect);
							_buuselect.setOnMousePressed(m::handleButtonPress);

						}
					}
					
				} else if (!_player2picked) {
					// _animationTimer.start();
					// _gc.clearRect(0, 0, 1000, 1000);
					if(_menusongstarted && _player.getStatus()!=0 && !_closed){
						//playStageSong("/sounds/menu.mp3");
					}
					
					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial Black", 40));

					_gc.fillText("PLAYER 2, CHOOSE YOUR CHARACTER", 20, 60);
					if(_page == 1){
						_root1.getChildren().remove(_previous);
						if (!_root1.getChildren().contains(_next)) {
							_next.setMinWidth(50);
							_next.setMinHeight(100);
							_next.setLayoutX(850);
							_next.setLayoutY(300);
							_root1.getChildren().add(_next);

							_next.setOnMousePressed(m::handleButtonPress);
						}
						// zero
						_gc.setFill(Color.RED);
						_gc.drawImage(new Image("zero/zero.gif"), 100, 130, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("zero", 106, 120);
						if (!_root1.getChildren().contains(_zeroselect)) {
							_zeroselect.setMinWidth(50);
							_zeroselect.setMinHeight(25);
							_zeroselect.setLayoutX(100);
							_zeroselect.setLayoutY(180);
							_root1.getChildren().add(_zeroselect);

							_zeroselect.setOnMousePressed(m::handleButtonPress);
						}

						// ninja
						_gc.setFill(Color.PINK);
						_gc.drawImage(new Image("ninja/ninja.png"), 250, 130, 50, 50);
						_gc.fillText("ninja kirby", 228, 120);
						if (!_root1.getChildren().contains(_ninjaselect)) {

							_ninjaselect.setMinWidth(50);
							_ninjaselect.setMinHeight(25);
							_ninjaselect.setLayoutX(250);
							_ninjaselect.setLayoutY(180);
							_root1.getChildren().add(_ninjaselect);
							_ninjaselect.setOnMousePressed(m::handleButtonPress);
						}
						// sans
						_gc.setFill(Color.WHITE);
						_gc.drawImage(new Image("sans.png"), 400, 130, 50, 50);
						_gc.setFont(Font.font("Comic Sans MS", 20));
						_gc.fillText("sans", 405, 120);
						if (!_root1.getChildren().contains(_sansselect)) {

							_sansselect.setMinWidth(50);
							_sansselect.setMinHeight(25);
							_sansselect.setLayoutX(400);
							_sansselect.setLayoutY(180);
							_root1.getChildren().add(_sansselect);
							_sansselect.setOnMousePressed(m::handleButtonPress);

						}
						// shovel
						_gc.setFill(Color.AQUA);
						_gc.drawImage(new Image("shovel1right.png"), 550, 130, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("shovel knight", 515, 120);
						if (!_root1.getChildren().contains(_shovelselect)) {

							_shovelselect.setMinWidth(50);
							_shovelselect.setMinHeight(25);
							_shovelselect.setLayoutX(550);
							_shovelselect.setLayoutY(180);
							_root1.getChildren().add(_shovelselect);
							_shovelselect.setOnMousePressed(m::handleButtonPress);

						}
						// cloud
						_gc.setFill(Color.GRAY);
						_gc.drawImage(new Image("cloud/cloud.png"), 700, 130, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("cloud", 700, 120);
						if (!_root1.getChildren().contains(_cloudselect)) {

							_cloudselect.setMinWidth(50);
							_cloudselect.setMinHeight(25);
							_cloudselect.setLayoutX(700);
							_cloudselect.setLayoutY(180);
							_root1.getChildren().add(_cloudselect);
							_cloudselect.setOnMousePressed(m::handleButtonPress);

						}
						// goku
						_gc.setFill(Color.ORANGE);
						_gc.drawImage(new Image("goku/goku.png"), 100, 330, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("goku", 100, 320);
						if (!_root1.getChildren().contains(_gokuselect)) {
							_gokuselect.setMinWidth(50);
							_gokuselect.setMinHeight(25);
							_gokuselect.setLayoutX(100);
							_gokuselect.setLayoutY(380);
							_root1.getChildren().add(_gokuselect);

							_gokuselect.setOnMousePressed(m::handleButtonPress);
						}
						// ariel
						_gc.setFill(Color.RED);
						_gc.drawImage(new Image("mermaid/stock.png"), 250, 330, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("ariel", 250, 320);
						if (!_root1.getChildren().contains(_arielselect)) {
							_arielselect.setMinWidth(50);
							_arielselect.setMinHeight(25);
							_arielselect.setLayoutX(250);
							_arielselect.setLayoutY(380);
							_root1.getChildren().add(_arielselect);

							_arielselect.setOnMousePressed(m::handleButtonPress);
						}
						// gengar
						_gc.setFill(Color.PURPLE);
						_gc.drawImage(new Image("gengar/stock.png"), 400, 330, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("gengar", 395, 320);
						if (!_root1.getChildren().contains(_gengarselect)) {
							_gengarselect.setMinWidth(50);
							_gengarselect.setMinHeight(25);
							_gengarselect.setLayoutX(400);
							_gengarselect.setLayoutY(380);
							_root1.getChildren().add(_gengarselect);

							_gengarselect.setOnMousePressed(m::handleButtonPress);
						}
						// frank
						_gc.setFill(Color.BLUE);
						_gc.drawImage(new Image("frank/frank.gif"), 550, 330, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("frank", 553, 320);
						if (!_root1.getChildren().contains(_frankselect)) {
							_frankselect.setMinWidth(50);
							_frankselect.setMinHeight(25);
							_frankselect.setLayoutX(550);
							_frankselect.setLayoutY(380);
							_root1.getChildren().add(_frankselect);

							_frankselect.setOnMousePressed(m::handleButtonPress);
						}
						// jaden
						_gc.setFill(Color.DARKORANGE);
						_gc.drawImage(new Image("jaden/jaden.gif"), 700, 330, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("jaden", 703, 320);
						if (!_root1.getChildren().contains(_jadenselect)) {
							_jadenselect.setMinWidth(50);
							_jadenselect.setMinHeight(25);
							_jadenselect.setLayoutX(700);
							_jadenselect.setLayoutY(380);
							_root1.getChildren().add(_jadenselect);

							_jadenselect.setOnMousePressed(m::handleButtonPress);
						}
						// specter knight
						_gc.setFill(Color.DARKRED);
						_gc.drawImage(new Image("specter/stock.png"), 100, 520, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("specter knight", 69, 510);
						if (!_root1.getChildren().contains(_specterselect)) {
							_specterselect.setMinWidth(50);
							_specterselect.setMinHeight(25);
							_specterselect.setLayoutX(100);
							_specterselect.setLayoutY(570);
							_root1.getChildren().add(_specterselect);

							_specterselect.setOnMousePressed(m::handleButtonPress);
						}
						// fefnir
						_gc.setFill(Color.LIGHTPINK);
						_gc.drawImage(new Image("fefnir/fefnir.png"), 250, 520, 50, 50);
						_gc.fillText("fefnir", 250, 510);
						if (!_root1.getChildren().contains(_fefnirselect)) {

							_fefnirselect.setMinWidth(50);
							_fefnirselect.setMinHeight(25);
							_fefnirselect.setLayoutX(250);
							_fefnirselect.setLayoutY(570);
							_root1.getChildren().add(_fefnirselect);
							_fefnirselect.setOnMousePressed(m::handleButtonPress);
						}
						// metaknight
						_gc.setFill(Color.BLUE);
						_gc.drawImage(new Image("metaknight/meta.png"), 400, 520, 50, 50);
						_gc.fillText("meta knight", 377, 510);
						if (!_root1.getChildren().contains(_metaselect)) {

							_metaselect.setMinWidth(50);
							_metaselect.setMinHeight(25);
							_metaselect.setLayoutX(400);
							_metaselect.setLayoutY(570);
							_root1.getChildren().add(_metaselect);
							_metaselect.setOnMousePressed(m::handleButtonPress);
						}
						// phantom
						_gc.setFill(Color.GRAY);
						_gc.drawImage(new Image("phantom/phantom.png"), 550, 520, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("phantom", 535, 510);
						if (!_root1.getChildren().contains(_phantomselect)) {

							_phantomselect.setMinWidth(50);
							_phantomselect.setMinHeight(25);
							_phantomselect.setLayoutX(550);
							_phantomselect.setLayoutY(570);
							_root1.getChildren().add(_phantomselect);
							_phantomselect.setOnMousePressed(m::handleButtonPress);

						}
						// the enchantress
						_gc.setFill(Color.MAGENTA);
						_gc.drawImage(new Image("enchantress/select.png"), 700, 520, 50, 50);
						_gc.setFont(Font.font("Arial", 20));
						_gc.fillText("the enchantress", 660, 510);
						if (!_root1.getChildren().contains(_enchantselect)) {

							_enchantselect.setMinWidth(50);
							_enchantselect.setMinHeight(25);
							_enchantselect.setLayoutX(700);
							_enchantselect.setLayoutY(570);
							_root1.getChildren().add(_enchantselect);
							_enchantselect.setOnMousePressed(m::handleButtonPress);

						}
					} else {
						if (!_root1.getChildren().contains(_previous)) {
						_previous.setMinWidth(50);
						_previous.setMinHeight(100);
						_previous.setLayoutX(0);
						_previous.setLayoutY(300);
						_root1.getChildren().add(_previous);

						_previous.setOnMousePressed(m::handleButtonPress);
					}
					_root1.getChildren().remove(_sansselect);
					_root1.getChildren().remove(_shovelselect);
					_root1.getChildren().remove(_cloudselect);
					_root1.getChildren().remove(_gokuselect);
					_root1.getChildren().remove(_arielselect);
					_root1.getChildren().remove(_gengarselect);
					_root1.getChildren().remove(_frankselect);
					_root1.getChildren().remove(_jadenselect);
					_root1.getChildren().remove(_ninjaselect);
					_root1.getChildren().remove(_zeroselect);
					_root1.getChildren().remove(_specterselect);
					_root1.getChildren().remove(_fefnirselect);
					_root1.getChildren().remove(_metaselect);
					_root1.getChildren().remove(_phantomselect);
					_root1.getChildren().remove(_enchantselect);
					_root1.getChildren().remove(_next);
					// leviathan
					_gc.setFill(Color.LIGHTBLUE);
					_gc.drawImage(new Image("levi/levi.gif"), 100, 130, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("leviathan", 86, 120);
					if (!_root1.getChildren().contains(_leviselect)) {
						_leviselect.setMinWidth(50);
						_leviselect.setMinHeight(25);
						_leviselect.setLayoutX(100);
						_leviselect.setLayoutY(180);
						_root1.getChildren().add(_leviselect);

						_leviselect.setOnMousePressed(m::handleButtonPress);
					}
					// naruto
					_gc.setFill(Color.YELLOW);
					_gc.drawImage(new Image("naruto/naruto1.png"), 250, 130, 50, 50);
					_gc.fillText("naruto", 247, 120);
					if (!_root1.getChildren().contains(_narutoselect)) {

						_narutoselect.setMinWidth(50);
						_narutoselect.setMinHeight(25);
						_narutoselect.setLayoutX(250);
						_narutoselect.setLayoutY(180);
						_root1.getChildren().add(_narutoselect);
						_narutoselect.setOnMousePressed(m::handleButtonPress);
					}
					// buu
					_gc.setFill(Color.PINK);
					_gc.drawImage(new Image("buu/stance1.png"), 400, 130, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("buu", 405, 120);
					if (!_root1.getChildren().contains(_buuselect)) {

						_buuselect.setMinWidth(50);
						_buuselect.setMinHeight(25);
						_buuselect.setLayoutX(400);
						_buuselect.setLayoutY(180);
						_root1.getChildren().add(_buuselect);
						_buuselect.setOnMousePressed(m::handleButtonPress);

					}
				}
				} else if (!_stageselected) {
					_root1.getChildren().remove(_sansselect);
					_root1.getChildren().remove(_shovelselect);
					_root1.getChildren().remove(_cloudselect);
					_root1.getChildren().remove(_gokuselect);
					_root1.getChildren().remove(_arielselect);
					_root1.getChildren().remove(_gengarselect);
					_root1.getChildren().remove(_frankselect);
					_root1.getChildren().remove(_jadenselect);
					_root1.getChildren().remove(_ninjaselect);
					_root1.getChildren().remove(_zeroselect);
					_root1.getChildren().remove(_specterselect);
					_root1.getChildren().remove(_fefnirselect);
					_root1.getChildren().remove(_metaselect);
					_root1.getChildren().remove(_phantomselect);
					_root1.getChildren().remove(_enchantselect);
					_root1.getChildren().remove(_leviselect);
					_root1.getChildren().remove(_narutoselect);
					_root1.getChildren().remove(_buuselect);
					_root1.getChildren().remove(_previous);
					_root1.getChildren().remove(_next);
					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial Black", 40));
					_gc.fillText("SELECT A STAGE", 20, 60);
					// emerald
					_gc.setFill(Color.GREEN);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Emerald Cave", 100, 200);
					if (!_root1.getChildren().contains(_emeraldselect)) {
						_emeraldselect.setMinWidth(50);
						_emeraldselect.setMinHeight(25);
						_emeraldselect.setLayoutX(140);
						_emeraldselect.setLayoutY(210);
						_root1.getChildren().add(_emeraldselect);

						_emeraldselect.setOnMousePressed(m::handleButtonPress);
					}
					// moon
					_gc.setFill(Color.GRAY);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Moon", 340, 200);
					if (!_root1.getChildren().contains(_moonselect)) {
						_moonselect.setMinWidth(50);
						_moonselect.setMinHeight(25);
						_moonselect.setLayoutX(340);
						_moonselect.setLayoutY(210);
						_root1.getChildren().add(_moonselect);

						_moonselect.setOnMousePressed(m::handleButtonPress);
					}
					// final destination
					_gc.setFill(Color.PURPLE);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Final Destination", 490, 200);
					if (!_root1.getChildren().contains(_finalselect)) {
						_finalselect.setMinWidth(50);
						_finalselect.setMinHeight(25);
						_finalselect.setLayoutX(540);
						_finalselect.setLayoutY(210);
						_root1.getChildren().add(_finalselect);

						_finalselect.setOnMousePressed(m::handleButtonPress);
					}
					// giygas
					_gc.setFill(Color.DARKRED);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Giygas' Dimension", 680, 200);
					if (!_root1.getChildren().contains(_giygasselect)) {
						_giygasselect.setMinWidth(50);
						_giygasselect.setMinHeight(25);
						_giygasselect.setLayoutX(740);
						_giygasselect.setLayoutY(210);
						_root1.getChildren().add(_giygasselect);

						_giygasselect.setOnMousePressed(m::handleButtonPress);
					}
					// shovel knight's camp
					_gc.setFill(Color.DARKGREEN);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Shovel Knight's Camp", 60, 400);
					if (!_root1.getChildren().contains(_shovelfselect)) {
						_shovelfselect.setMinWidth(50);
						_shovelfselect.setMinHeight(25);
						_shovelfselect.setLayoutX(140);
						_shovelfselect.setLayoutY(410);
						_root1.getChildren().add(_shovelfselect);

						_shovelfselect.setOnMousePressed(m::handleButtonPress);
					}
					// sans battle
					_gc.setFill(Color.WHITE);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Sans' Battle", 310, 400);
					if (!_root1.getChildren().contains(_undertselect)) {
						_undertselect.setMinWidth(50);
						_undertselect.setMinHeight(25);
						_undertselect.setLayoutX(340);
						_undertselect.setLayoutY(410);
						_root1.getChildren().add(_undertselect);

						_undertselect.setOnMousePressed(m::handleButtonPress);
					}
					// drawcia's world
					_gc.setFill(Color.PURPLE);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Drawcia's World", 495, 400);
					if (!_root1.getChildren().contains(_kirbyselect)) {
						_kirbyselect.setMinWidth(50);
						_kirbyselect.setMinHeight(25);
						_kirbyselect.setLayoutX(540);
						_kirbyselect.setLayoutY(410);
						_root1.getChildren().add(_kirbyselect);

						_kirbyselect.setOnMousePressed(m::handleButtonPress);
					}
					// dark mind's realm
					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Dark Mind's Realm", 685, 400);
					if (!_root1.getChildren().contains(_kirby2select)) {
						_kirby2select.setMinWidth(50);
						_kirby2select.setMinHeight(25);
						_kirby2select.setLayoutX(740);
						_kirby2select.setLayoutY(410);
						_root1.getChildren().add(_kirby2select);

						_kirby2select.setOnMousePressed(m::handleButtonPress);
					}
					// pokemon stadium
					_gc.setFill(Color.LIGHTGREEN);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Pokemon Stadium", 80, 565);
					if (!_root1.getChildren().contains(_pokemonselect)) {
						_pokemonselect.setMinWidth(50);
						_pokemonselect.setMinHeight(25);
						_pokemonselect.setLayoutX(140);
						_pokemonselect.setLayoutY(575);
						_root1.getChildren().add(_pokemonselect);

						_pokemonselect.setOnMousePressed(m::handleButtonPress);
					}
					// fountain of dreams
					_gc.setFill(Color.WHITE);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Fountain of Dreams", 280, 565);
					if (!_root1.getChildren().contains(_kirby3select)) {
						_kirby3select.setMinWidth(50);
						_kirby3select.setMinHeight(25);
						_kirby3select.setLayoutX(340);
						_kirby3select.setLayoutY(575);
						_root1.getChildren().add(_kirby3select);

						_kirby3select.setOnMousePressed(m::handleButtonPress);
					}
					// dr. wily's castle
					_gc.setFill(Color.BLUE);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Dr. Wily's Castle", 490, 565);
					if (!_root1.getChildren().contains(_wilyselect)) {
						_wilyselect.setMinWidth(50);
						_wilyselect.setMinHeight(25);
						_wilyselect.setLayoutX(540);
						_wilyselect.setLayoutY(575);
						_root1.getChildren().add(_wilyselect);

						_wilyselect.setOnMousePressed(m::handleButtonPress);
					}
					// tower of fate
					_gc.setFill(Color.CHARTREUSE);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("Tower of Fate", 700, 565);
					if (!_root1.getChildren().contains(_towerselect)) {
						_towerselect.setMinWidth(50);
						_towerselect.setMinHeight(25);
						_towerselect.setLayoutX(740);
						_towerselect.setLayoutY(575);
						_root1.getChildren().add(_towerselect);

						_towerselect.setOnMousePressed(m::handleButtonPress);
					}
				} else {
					_character1.setOtherChar(_character2);
					_character2.setOtherChar(_character1);
					_root1.getChildren().remove(_emeraldselect);
					_root1.getChildren().remove(_moonselect);
					_root1.getChildren().remove(_finalselect);
					_root1.getChildren().remove(_giygasselect);
					_root1.getChildren().remove(_shovelfselect);
					_root1.getChildren().remove(_undertselect);
					_root1.getChildren().remove(_kirbyselect);
					_root1.getChildren().remove(_kirby2select);
					_root1.getChildren().remove(_kirby3select);
					_root1.getChildren().remove(_pokemonselect);
					_root1.getChildren().remove(_wilyselect);
					_root1.getChildren().remove(_towerselect);
					for(Backdrop b : _backdrops) {
						b.render(_gc);
					}
					for(Platform p: _platforms) {
						p.render(_gc);
					}
					if(_place != null){
					_place.render(_gc);
					}
					_gc.setFont(Font.font("Arial", 20));
					_gc.setFill(_character1.getColor());
					
					String damage1 = (Math.round(((_character1.getDamage()) * .5) - .5)) + "%";
					String damage2 = (Math.round(((_character2.getDamage()) * .5) - .5)) + "%";
					String ult1;
					String ult2;
					if (_character1.getUltCharge() < 150) {
						ult1 = "U: " + (int) (_character1.getUltCharge() / 1.5) + "%";
					} else {
						ult1 = "U: READY";
					}
					if (_character2.getUltCharge() < 150) {
						ult2 = "U: " + (int) (_character2.getUltCharge() / 1.5) + "%";
					} else {
						ult2 = "U: READY";
					}
					_gc.fillText("Player 1", 50, 75);
					_gc.fillText(damage1, 50, 100);
					_gc.fillText(ult1, 50, 150);
					_gc.setFill(_character2.getColor());
					_gc.fillText("Player 2", 140, 75);
					_gc.fillText(damage2, 140, 100);
					_gc.fillText(ult2, 140, 150);
					printStocks();
					if(_character1.isHasPriority()) {
						_character2.render(_gc);
						_character2.move();
						_character1.render(_gc);
						_character1.move();
					} else {
						_character1.render(_gc);
						_character1.move();
						_character2.render(_gc);
						_character2.move();
					}
					
					
					_character1.incrementCounter();
					_character2.incrementCounter();
					List<Hitbox> attackstoremove = new ArrayList<Hitbox>();
					for (Hitbox a : _attacks) {
						a.render(_gc);
						if (a.isAffectedByGravity()) {
							a.setYVelocity(a.getYVelocity() + _gravity);
						}
						// System.out.println(a);
						if (a.checkCollide() == true) {
							if (a.getCharacter().getID().equals("one")) {
								_character2.hit(a);
							}
							if (a.getCharacter().getID().equals("two")) {
								_character1.hit(a);
							}
						}
						if (a.isGone()) {
							attackstoremove.add(a);
						}
					}
					// for (PreHitbox p : _preattacks) {
					// p.render(_gc);
					// System.out.println(p);

					// }
					for (Hitbox a : attackstoremove) {
						_attacks.remove(a);
					}
					if (!(_character1.isOnPlatform()) && _character1.isGravity()) {
						_character1.setYVelocity(_character1.getYVelocity() + _gravity);
					}
					if (!(_character2.isOnPlatform()) && _character2.isGravity()) {
						_character2.setYVelocity(_character2.getYVelocity() + _gravity);
					}

					long t = (currentNanoTime - _last);
					_scene.setOnKeyPressed(m::handleKeyPress);
					_scene.setOnKeyReleased(m::handleKeyRelease);
					if (_character1.isGone() || _character2.isGone()) {
						// _gc.clearRect(0, 0, 1000, 1000);
						if (_character1.isGone()) {
							if (_character1.getLives() == 0) {
								_gc.setFill(Color.YELLOW);
								_gc.fillText("Player 2 Wins!", 400, 200);
							} else {
								//_character1.respawn();
								//_character1.die();
							}
						} else if (_character2.isGone()) {
							if (_character2.getLives() == 0) {
							
								_gc.setFill(Color.WHITE);
								_gc.fillText("Player 1 Wins!", 400, 200);
							} else {
								//_character2.respawn();
								//_character2.die();

							}
						}
						if (_character1.getLives() == 0 || _character2.getLives() == 0) {
							printStocks();

							_replaybutton.setMinWidth(200);
							_replaybutton.setMinHeight(100);
							_replaybutton.setLayoutX(360);
							_replaybutton.setLayoutY(300);
							_root1.getChildren().add(_replaybutton);
							_replaybutton.setText("Play again?");

							if (_character1.getLives() == 0) {
								_gc.setFill(_character2.getColor());
								_gc.fillText("Player 2 Wins!", 400, 200);

							}

							if (_character2.getLives() == 0) {

								_gc.setFill(_character1.getColor());
								_gc.fillText("Player 1 Wins!", 400, 200);
							}

							_replaybutton.setOnMousePressed(m::handleButtonPress);
							_animationTimer.stop();
						}
					}

					if (t > 500000000) {
						_last = currentNanoTime;
						// _character1.incrementCounter();

					}
				}
			}
		};
		if (_player2picked) {

		}
		_animationTimer.start();
		stage1.show();

	}

	public void handleKeyPress(KeyEvent event) {

	

			if (event.getCode().toString().equals("SLASH")) {
				_character2.pressJump();

			}
			if (event.getCode().toString().equals("I")) {
				_character2.pressUp();

			}
			if (event.getCode().toString().equals("K")) {
				_character2.pressDown();

			}
			if (event.getCode().toString().equals("L")) {
					_character2.pressRight();
				

			}
			if (event.getCode().toString().equals("J")) {
					_character2.pressLeft();
		

			}
			if (event.getCode().toString().equals("U")) {
				_character2.pressAttack1();

			}
			if (event.getCode().toString().equals("O")) {
				_character2.pressAttack2();
			}
			if (event.getCode().toString().equals("Y")) {
				_character2.pressAttackU();
			}

			if (event.getCode().toString().equals("H")) {
				_character2.pressAttack3();
			}
		
			if (event.getCode().toString().equals("E")) {
				_character1.pressAttack1();
			}
			if (event.getCode().toString().equals("Q")) {
				_character1.pressAttack2();
			}
			if (event.getCode().toString().equals("R")) {
				_character1.pressAttackU();
			}

			if (event.getCode().toString().equals("SHIFT")) {
				_character1.pressJump();

			}
			if (event.getCode().toString().equals("D")) {
					_character1.pressRight();
				

			}
			if (event.getCode().toString().equals("A")) {
					_character1.pressLeft();
				
			}
			if (event.getCode().toString().equals("W")) {
				_character1.pressUp();

			}
			if (event.getCode().toString().equals("S")) {
				_character1.pressDown();

			}

			if (event.getCode().toString().equals("F")) {
				_character1.pressAttack3();
		
			}

	}

	public void handleKeyRelease(KeyEvent event) {

		if (event.getCode().toString().equals("L")) {
			_character2.releaseRight();
			// System.out.println("moving right");
		}
		if (event.getCode().toString().equals("J")) {
			_character2.releaseLeft();
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("D")) {
			_character1.releaseRight();
			// System.out.println("moving right");
		}
		if (event.getCode().toString().equals("A")) {
			_character1.releaseLeft();
			
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("W")) {
			if (!_character1.isXTumbling()) {
				_character1.releaseUp();
			}
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("S")) {
			if (!_character1.isXTumbling()) {
				_character1.releaseDown();
			}
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("I")) {
			if (!_character2.isXTumbling()) {
				_character2.releaseUp();
			}
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("K")) {
			if (!_character2.isXTumbling()) {
				_character2.releaseDown();
			}
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("SHIFT")) {
			if (!_character1.isXTumbling()) {
				_character1.releaseJump();
			}
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("SLASH")) {
			if (!_character2.isXTumbling()) {
				_character2.releaseJump();
			}
			// System.out.println("moving left");
		}

	}

	public void handleButtonPress(MouseEvent click) {

		if (click.getSource().equals(_start)) {
           _gamestarted = true;
           _root1.getChildren().remove(_start);

		}
		if(click.getSource().equals(_next)) {
			_page = 2;
		}
		if(click.getSource().equals(_previous)) {
			_page = 1;
		}
		if (click.getSource().equals(_whiteboxselect)) {

			if (!_player1picked) {
				_character1 = new WhiteChar("one");
				_player1picked = true;
				_page = 1;

			} else {
				_character2 = new WhiteChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_yellowboxselect)) {
			if (!_player1picked) {
				_character1 = new YellowChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new YellowChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_sansselect)) {
			if (!_player1picked) {
				_character1 = new SansChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new SansChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_shovelselect)) {
			if (!_player1picked) {
				_character1 = new ShovelChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new ShovelChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_cloudselect)) {
			if (!_player1picked) {
				_character1 = new CloudChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new CloudChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_gokuselect)) {
			if (!_player1picked) {
				_character1 = new GokuChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new GokuChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_arielselect)) {
			if (!_player1picked) {
				_character1 = new MermaidChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new MermaidChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_gengarselect)) {
			if (!_player1picked) {
				_character1 = new GengarChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new GengarChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_frankselect)) {
			if (!_player1picked) {
				_character1 = new FrankChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new FrankChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_jadenselect)) {
			if (!_player1picked) {
				_character1 = new JadenChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new JadenChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_ninjaselect)) {
			if (!_player1picked) {
				_character1 = new NinjaChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new NinjaChar("two");

				_player2picked = true;

			}
			
		}
		if (click.getSource().equals(_zeroselect)) {
			if (!_player1picked) {
				_character1 = new ZeroChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new ZeroChar("two");

				_player2picked = true;

			}
			
		}
		if (click.getSource().equals(_specterselect)) {
			if (!_player1picked) {
				_character1 = new SpecterChar("one", 0);
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new SpecterChar("two", 0);

				_player2picked = true;

			}
			
		}
		if (click.getSource().equals(_fefnirselect)) {
			if (!_player1picked) {
				_character1 = new FefnirChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new FefnirChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_metaselect)) {
			if (!_player1picked) {
				_character1 = new MetaChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new MetaChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_phantomselect)) {
			if (!_player1picked) {
				_character1 = new PhantomChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new PhantomChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_enchantselect)) {
			if (!_player1picked) {
				_character1 = new EnchantChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new EnchantChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_leviselect)) {
			if (!_player1picked) {
				_character1 = new LeviChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new LeviChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_narutoselect)) {
			if (!_player1picked) {
				_character1 = new NarutoChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new NarutoChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_buuselect)) {
			if (!_player1picked) {
				_character1 = new BuuChar("one");
				_player1picked = true;
				_page = 1;
			} else {
				_character2 = new BuuChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_replaybutton)) {
			_stage.close();
			Stage sad = new Stage();
			this.start(sad);
			// _gc.clearRect(0, 0, 1000, 1000);
			_menusongstarted = false;
			_player1picked = false;
			_player2picked = false;
			_stageselected = false;
			_page = 1;
			_backdrops.clear();
			_attacks.clear();
			_platforms.clear();
			_gravity = 0.8;
			_root1.getChildren().remove(_replaybutton);
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        
		}
		if(click.getSource().equals(_emeraldselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform p = new PlatformImpl(125, 400, 225, 200);
			Platform p2 = new PlatformImpl(525, 500, 225, 100);

			_place = new EmeraldStage(_character1, _character2);
			_stageselected = true;
			_place.start();
		}
		if(click.getSource().equals(_moonselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new MoonStage(_character1, _character2);
			_gravity = 0.5;
			_stageselected = true;
			_place.start();
			
		}
		if(click.getSource().equals(_finalselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new FDStage(_character1, _character2);
			_stageselected = true;
			_place.start();
		}
		if(click.getSource().equals(_giygasselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			_place = new GiygasStage(_character1, _character2);
			_stageselected = true;
			_place.start();
		}
		if(click.getSource().equals(_shovelfselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new CampStage(_character1, _character2);
			_stageselected = true;
			_place.start();			
		}
		if(click.getSource().equals(_undertselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			_place = new SansStage(_character1, _character2);
			_stageselected = true;
			_place.start();			
		}
		if(click.getSource().equals(_kirbyselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new DrawciaStage(_character1, _character2);
			_stageselected = true;
			_place.start();	
		}
		if(click.getSource().equals(_kirby2select)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new DarkMindStage(_character1, _character2);
			_stageselected = true;
			_place.start();	
		}
		if(click.getSource().equals(_pokemonselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new PokemonStage(_character1, _character2);
			_stageselected = true;
			_place.start();	
		}
		if(click.getSource().equals(_kirby3select)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new FountainStage(_character1, _character2);
			_stageselected = true;
			_place.start();	
		}
		if(click.getSource().equals(_wilyselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new WilyStage(_character1, _character2);
			_stageselected = true;
			_place.start();	
		}
		if(click.getSource().equals(_towerselect)) {
			try {
				_player.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_place = new TowerStage(_character1, _character2);
			_stageselected = true;
			_place.start();	
		}

	}

	private void printStocks() {
		if (_character1.getLives() == 3) {

			_gc.drawImage(_character1.getStockImage(), 80, 120, 10, 10);
		}
		if (_character1.getLives() >= 2) {

			_gc.drawImage(_character1.getStockImage(), 65, 120, 10, 10);
		}
		if (_character1.getLives() >= 1) {

			_gc.drawImage(_character1.getStockImage(), 50, 120, 10, 10);
		}
		if (_character2.getLives() == 3) {
			_gc.drawImage(_character2.getStockImage(), 170, 120, 10, 10);

		}
		if (_character2.getLives() >= 2) {

			_gc.drawImage(_character2.getStockImage(), 155, 120, 10, 10);
		}
		if (_character2.getLives() >= 1) {

			_gc.drawImage(_character2.getStockImage(), 140, 120, 10, 10);
		}
	}
	public static void clearHitboxes(String ID, Character c) {
		List<Hitbox> remove = new ArrayList<Hitbox>();
		for (Hitbox a : TheGame._attacks) {
			if (a.getID().equals(ID) && a.getCharacter().equals(c)) {
				remove.add(a);
			}
		}
		for (Hitbox a : remove) {
			TheGame._attacks.remove(a);
		}
	}
	public static List<Platform> getPlatforms() {
		if(_place != null) {
			return _place.getPlatforms();
		}
		return _platforms;
	}
	
	public void closeWindow(WindowEvent w) {
		_closed = true;
		try {
			_player.stop();
			
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_stage.close();
	}

 public static void playStageSong(String url) {

	 new Thread(new Runnable() {
		 public void run() {
		try {
			String pathToMp3 = System.getProperty("user.dir") + url;
			
			_player.open((TheGame.class.getResource(url)));
			 _player.play();
			 
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
	 }).start();
	 
 }
 public static int getPlayerState() {
	 return _player.getStatus();
 }
 public static boolean getClosed() {
	 return _closed;
 }
 public static synchronized void playSound(final String url) {
	  new Thread(new Runnable() {
	  // The wrapper thread is unnecessary, unless it blocks on the
	  // Clip finishing; see comments.
	    public void run() {
	      try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	          TheGame.class.getResource(url));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	    }
	  }).start();
	}

}
