package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.controller;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.*;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.Character;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;

/**
 * Classe gerant le terrain de jeu.
 *
 */
public class Field extends Canvas {

	/** Couleurs possibles */
	String[] colorMap = new String[] { "blue", "green", "orange", "purple",
			"yellow" };
	/** Tableau traÃ§ant les evenements */
	ArrayList<String> input = new ArrayList<String>();
	SpriteAnimation explo;
	Vector<Character> vC = new Vector<Character>();
	int count = 0;
	final GraphicsContext gc;
	final int width;
	final int height;

	Vector<Vector<Projectile>> vBalls;

	Vector<Cloud> tabCloud;
	Walls wall;

	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 *
	 * @param scene
	 *            Scene principale du jeu a laquelle on va ajouter notre Canvas
	 * @param w
	 *            largeur du canvas
	 * @param h
	 *            hauteur du canvas
	 */
	public Field(Scene scene, int w, int h) {

		super(w, h);
		this.explo = new SpriteAnimation(new Image("assets/explosion.png"), 6,
				200, 300, 300);
		// explo.playContinuously();
		width = w;
		height = h;
		vBalls = new Vector<Vector<Projectile>>();
		tabCloud = new Vector<Cloud>();
		tabCloud.add(new Cloud(new Image("assets/cloud.png"), 100, 300));
		tabCloud.add(new Cloud(new Image("assets/cloud.png"), 400, 300));
		wall = new Walls(w, h);

		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);

		gc = this.getGraphicsContext2D();

		/** On initialise le terrain de jeu */
		CharacterFactory cF = new PlayerFactory();
		Character c = cF.createCharacter(gc, colorMap[0], w / 2, h - 100,
				"bottom", "assets/PlayerBlue.png");
		vC.add(c);
		c = cF.createCharacter(gc, colorMap[1], w / 2, 20, "top",
				"assets/PlayerRed.png");
		vC.add(c);

		for (int i = 0; i < 4; i++) {
			cF = new ComputerFactory();
			if (i % 2 == 0) {
				c = cF.createCharacter(gc, colorMap[0], w / (3 + i), h - 100,
						"bottom", "assets/PlayerBlue.png");
			} else {
				c = cF.createCharacter(gc, colorMap[1], w / (3 + i), 20, "top",
						"assets/PlayerRed.png");
			}
			vC.add(c);
		}

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				// only add once... prevent duplicates
				if (!input.contains(code))
					input.add(code);
			}
		});

		/**
		 * Event Listener du clavier quand une touche est relachee on l'enleve
		 * de la liste d'input
		 *
		 */
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});

		/**
		 *
		 * Boucle principale du jeu
		 *
		 * handle() est appelee a chaque rafraichissement de frame soit environ
		 * 60 fois par seconde.
		 *
		 */

		InputHandler iH = new InputHandler(input);

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// On nettoie le canvas a chaque frame
				gc.setFill(Color.LIGHTGRAY);
				gc.fillRect(0, 0, width, height);
				tabCloud.get(0).move();
				tabCloud.get(1).move();
				// Deplacement et affichage des joueurs

				for (int i = 0; i < vC.size(); i++) {
					Command cmd = iH.handleInput(i);
					if (cmd != null) {
						cmd.execute(vC.get(i));
					}
					vBalls.add(vC.get(i).getBall());
				}

				for (Character c : vC) {
					c.display();
				}

				for (Vector<Projectile> vBall : vBalls) {
					if (vBall != null) {
						for (Projectile b : vBall) {
							if (b != null) {
								if (b.getx() > 1000 || b.gety() > 1000
										|| b.getx() < -1000 || b.gety() < -1000) {
									b = null;
									vBall.remove(b);
								} else {
									b.fly();
									b.display();
								}
							}

						}
					}
				}
				for (int i = 0; i < vC.size(); i++) {
					for (int j = 0; j < vBalls.size(); j++) {
						if (i != j) {
							Vector<Projectile> vBall = vBalls.get(j);
							if (vBall != null) {
								for (Projectile b : vBall) {
									if (b != null) {
										if (tabCloud.get(0).isHitted(b)) {
											b.setStop();
										}
										if (tabCloud.get(1).isHitted(b)) {
											b.setStop();
										}
										if (vC.get(i).isHitted(b)) {
											b.setStop();
											vC.get(i).sprite.playDead();
											explo.playContinuously(vC.get(i)
													.getx(),
													vC.get(i).gety() - 20);
											vBalls.remove(j);
											vC.get(i).isDead();
										}
										if (wall.isHitted(b)) {
											explo.playContinuously(
													b.getx() - 30, b.gety());
											vBalls.remove(j);
										}
									}
								}
							}

						}
					}
				}

			}
		}.start();
	}

	public Walls getWall() {
		return wall;
	}

	public Vector getCloud() {
		return tabCloud;
	}

	public SpriteAnimation getAnimationSprite() {
		return this.explo;
	}

	public Vector<Character> getCharacters() {
		return this.vC;
	}

	public GraphicsContext getGc() {
		return gc;
	}

}
