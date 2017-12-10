package kf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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

public class TheGame extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public static Character _character1 = new WhiteChar("one");
	public static Character _character2 = new YellowChar("two");
	private static Canvas _canvas = new Canvas(900, 600);
	private int _score = 0;

	public static GraphicsContext _gc = _canvas.getGraphicsContext2D();
	private Stage _stage;
	private Scene _scene;
	private AnimationTimer _animationTimer;
	private Platform _platform = new PlatformImpl();
	public static Set<Hitbox> _attacks = new HashSet<Hitbox>();
	private boolean _gamestarted = false;
	private boolean _player1picked = false;
	private boolean _player2picked = false;
	private Button _whiteboxselect = new Button("select");
	private Button _yellowboxselect = new Button("select");
	private Button _sansselect = new Button("select");
	private Button _shovelselect = new Button("select");
	private Button _cloudselect = new Button("select");
	private Button _gokuselect = new Button("select");
	private Group _root1 = new Group();

	private Button _replaybutton = new Button();
	// public static Set<PreHitbox> _preattacks = new HashSet<PreHitbox>();

	private double _gravity = 0.8;

	public void start(Stage stage1) {
		// FXMLLoader loader = new
		// FXMLLoader(this.getClass().getResource("watchout.fxml"));
		// loader.setController(this);
		stage1.setTitle("Knightfight");
		_stage = stage1;
		TheGame m = this;
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
				_gc.clearRect(0, 0, 1000, 1000);

				if (!_player1picked) {

					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial Black", 40));

					_gc.fillText("PLAYER 1, CHOOSE YOUR CHARACTER", 20, 60);

					// whitebox
					_gc.setFill(Color.WHITE);
					_gc.fillRect(100, 200, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("whitebox", 85, 190);
					if (!_root1.getChildren().contains(_whiteboxselect)) {
						_whiteboxselect.setMinWidth(50);
						_whiteboxselect.setMinHeight(25);
						_whiteboxselect.setLayoutX(100);
						_whiteboxselect.setLayoutY(250);
						_root1.getChildren().add(_whiteboxselect);

						_whiteboxselect.setOnMousePressed(m::handleButtonPress);
					}

					// yellowbox
					_gc.setFill(Color.YELLOW);
					_gc.fillRect(250, 200, 50, 50);
					_gc.fillText("yellowbox", 230, 190);
					if (!_root1.getChildren().contains(_yellowboxselect)) {

						_yellowboxselect.setMinWidth(50);
						_yellowboxselect.setMinHeight(25);
						_yellowboxselect.setLayoutX(250);
						_yellowboxselect.setLayoutY(250);
						_root1.getChildren().add(_yellowboxselect);
						_yellowboxselect.setOnMousePressed(m::handleButtonPress);
					}
					// sans
					_gc.setFill(Color.WHITE);
					_gc.drawImage(new Image("sans.jpg"), 400, 200, 50, 50);
					_gc.setFont(Font.font("Comic Sans MS", 20));
					_gc.fillText("sans", 405, 190);
					if (!_root1.getChildren().contains(_sansselect)) {

						_sansselect.setMinWidth(50);
						_sansselect.setMinHeight(25);
						_sansselect.setLayoutX(400);
						_sansselect.setLayoutY(250);
						_root1.getChildren().add(_sansselect);
						_sansselect.setOnMousePressed(m::handleButtonPress);

					}
					// shovel
					_gc.setFill(Color.AQUA);
					_gc.drawImage(new Image("shovel1right.png"), 550, 200, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("shovel knight", 515, 190);
					if (!_root1.getChildren().contains(_shovelselect)) {

						_shovelselect.setMinWidth(50);
						_shovelselect.setMinHeight(25);
						_shovelselect.setLayoutX(550);
						_shovelselect.setLayoutY(250);
						_root1.getChildren().add(_shovelselect);
						_shovelselect.setOnMousePressed(m::handleButtonPress);

					}
					// cloud
					_gc.setFill(Color.GRAY);
					_gc.drawImage(new Image("cloud/cloud.png"), 700, 200, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("cloud", 700, 190);
					if (!_root1.getChildren().contains(_cloudselect)) {

						_cloudselect.setMinWidth(50);
						_cloudselect.setMinHeight(25);
						_cloudselect.setLayoutX(700);
						_cloudselect.setLayoutY(250);
						_root1.getChildren().add(_cloudselect);
						_cloudselect.setOnMousePressed(m::handleButtonPress);

					}
					// goku
					_gc.setFill(Color.ORANGE);
					_gc.drawImage(new Image("goku/goku.png"), 100, 400, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("goku", 100, 390);
					if (!_root1.getChildren().contains(_gokuselect)) {
						_gokuselect.setMinWidth(50);
						_gokuselect.setMinHeight(25);
						_gokuselect.setLayoutX(100);
						_gokuselect.setLayoutY(450);
						_root1.getChildren().add(_gokuselect);

						_gokuselect.setOnMousePressed(m::handleButtonPress);
					}
				} else if (!_player2picked) {
					// _animationTimer.start();
					// _gc.clearRect(0, 0, 1000, 1000);
					_gc.setFill(Color.RED);
					_gc.setFont(Font.font("Arial Black", 40));

					_gc.fillText("PLAYER 2, CHOOSE YOUR CHARACTER", 20, 60);

					// whitebox

					_gc.setFill(Color.WHITE);
					_gc.fillRect(100, 200, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("whitebox", 85, 190);
					// yellowbox
					_gc.setFill(Color.YELLOW);
					_gc.fillRect(250, 200, 50, 50);
					_gc.fillText("yellowbox", 230, 190);
					// _animationTimer.stop();
					// sans
					_gc.setFill(Color.WHITE);
					_gc.drawImage(new Image("sans.jpg"), 400, 200, 50, 50);
					_gc.setFont(Font.font("Comic Sans MS", 20));
					_gc.fillText("sans", 405, 190);
					// shovel
					_gc.setFill(Color.AQUA);
					_gc.drawImage(new Image("shovel1right.png"), 550, 200, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("shovel knight", 515, 190);
					// cloud
					_gc.setFill(Color.GRAY);
					_gc.drawImage(new Image("cloud/cloud.png"), 700, 200, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("cloud", 700, 190);
					// goku
					_gc.setFill(Color.ORANGE);
					_gc.drawImage(new Image("goku/goku.png"), 100, 400, 50, 50);
					_gc.setFont(Font.font("Arial", 20));
					_gc.fillText("goku", 100, 390);

				} else {
					_character1.setOtherChar(_character2);
					_character2.setOtherChar(_character1);
					_root1.getChildren().remove(_whiteboxselect);
					_root1.getChildren().remove(_yellowboxselect);
					_root1.getChildren().remove(_sansselect);
					_root1.getChildren().remove(_shovelselect);
					_root1.getChildren().remove(_cloudselect);
					_root1.getChildren().remove(_gokuselect);
					_character1.render(_gc);
					_character2.render(_gc);
					_character2.move();
					_character1.move();
					_character1.incrementCounter();
					_character2.incrementCounter();
					_platform.render(_gc);
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
								_character1.respawn();
								_character1.die();
							}
						} else if (_character2.isGone()) {
							if (_character2.getLives() == 0) {
								_character2.die();
								_gc.setFill(Color.WHITE);
								_gc.fillText("Player 1 Wins!", 400, 200);
							} else {
								_character2.respawn();
								_character2.die();

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

		if (_character2.isCanAct()) {

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
				if (!_character2.isXTumbling()) {
					_character2.pressRight();
				}

			}
			if (event.getCode().toString().equals("J")) {

				if (!_character2.isXTumbling()) {
					_character2.pressLeft();
				}

			}
			if (event.getCode().toString().equals("O")) {
				_character2.pressAttack1();

			}
			if (event.getCode().toString().equals("U")) {
				_character2.pressAttack2();
			}
			if (event.getCode().toString().equals("P")) {
				_character2.pressAttackU();
			}

		}

		if (event.getCode().toString().equals("SEMICOLON")) {
			// checking if the character is whitebox
			if ((_character2.isCanAct() || _character2.isCharging1())
					&& _character2.getClass().toString().equals("class kf.WhiteChar")) {
				_character2.pressAttack3();
			} else if (_character2.isCanAct()) {
				_character2.pressAttack3();
			}

		}
		if (_character1.isCanAct()) {
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
				if (!_character1.isXTumbling()) {
					_character1.pressRight();
				}

			}
			if (event.getCode().toString().equals("A")) {
				if (!_character1.isXTumbling()) {
					_character1.pressLeft();
				}
			}
			if (event.getCode().toString().equals("W")) {
				_character1.pressUp();

			}
			if (event.getCode().toString().equals("S")) {
				_character1.pressDown();

			}

		}
		if (event.getCode().toString().equals("F")) {
			// checking if the character is whitebox
			if ((_character1.isCanAct() || _character1.isCharging1())
					&& _character1.getClass().toString().equals("class kf.WhiteChar")) {
				_character1.pressAttack3();
			} else if (_character1.isCanAct()) {
				_character1.pressAttack3();
			}
		}

	}

	public void handleKeyRelease(KeyEvent event) {

		if (event.getCode().toString().equals("L")) {
			if (!_character2.isXTumbling()) {
				_character2.setXVelocity(0);
			}
			// System.out.println("moving right");
		}
		if (event.getCode().toString().equals("J")) {
			if (!_character2.isXTumbling()) {
				_character2.setXVelocity(0);
			}
			// System.out.println("moving left");
		}
		if (event.getCode().toString().equals("D")) {
			if (!_character1.isXTumbling()) {
				_character1.setXVelocity(0);
			}
			// System.out.println("moving right");
		}
		if (event.getCode().toString().equals("A")) {
			if (!_character1.isXTumbling()) {
				_character1.setXVelocity(0);
			}
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

		if (click.getSource().equals(_whiteboxselect)) {

			if (!_player1picked) {
				_character1 = new WhiteChar("one");
				_player1picked = true;
				System.out.println("here");

			} else {
				_character2 = new WhiteChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_yellowboxselect)) {
			if (!_player1picked) {
				_character1 = new YellowChar("one");
				_player1picked = true;
			} else {
				_character2 = new YellowChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_sansselect)) {
			if (!_player1picked) {
				_character1 = new SansChar("one");
				_player1picked = true;
			} else {
				_character2 = new SansChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_shovelselect)) {
			if (!_player1picked) {
				_character1 = new ShovelChar("one");
				_player1picked = true;
			} else {
				_character2 = new ShovelChar("two");
				_player2picked = true;
			}
		}
		if (click.getSource().equals(_cloudselect)) {
			if (!_player1picked) {
				_character1 = new CloudChar("one");
				_player1picked = true;
			} else {
				_character2 = new CloudChar("two");

				_player2picked = true;

			}
		}
		if (click.getSource().equals(_gokuselect)) {
			if (!_player1picked) {
				_character1 = new GokuChar("one");
				_player1picked = true;
			} else {
				_character2 = new GokuChar("two");

				_player2picked = true;

			}
		}

		if (click.getSource().equals(_replaybutton)) {
			_stage.close();
			Stage sad = new Stage();
			this.start(sad);
			// _gc.clearRect(0, 0, 1000, 1000);

			_player1picked = false;
			_player2picked = false;
			_attacks.clear();
			_root1.getChildren().remove(_replaybutton);
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

}
