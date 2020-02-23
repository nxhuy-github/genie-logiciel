package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * Classe gerant un joueur
 *
 */
public class Player extends Character {

	boolean isDead = false;
	/**
	 * Constructeur du Joueur
	 *
	 * @param gc
	 *            ContextGraphic dans lequel on va afficher le joueur
	 * @param color
	 *            couleur du joueur
	 * @param yInit
	 *            position verticale
	 */
	boolean moving = true;

	public Player(GraphicsContext gc, String color, int xInit, int yInit, String side,
			String image) {
		super(gc, color, xInit, yInit, side, image);
	}

	/**
	 * Deplacement du joueur vers la gauche, on cantonne le joueur sur le
	 * plateau de jeu
	 */
	@Override
	public void moveLeft() {
		if (!isDead) {
			if (x > 10 && x < 520) {
				spriteAnimate();
				x -= step;
			} else {
				x = (x == 10) ? 10 + step : 520 - step;
			}
		}
	}

	/**
	 * Deplacement du joueur vers la droite
	 */
	@Override
	public void moveRight() {
		if (!isDead) {
			if (x > 10 && x < 520) {
				spriteAnimate();
				x += step;
			} else {
				x = (x == 10) ? 10 + step : 520 - step;
			}
		}
	}

	/**
	 * Rotation du joueur vers la gauche
	 */
	@Override
	public void turnLeft() {
		if (!isDead) {
			if (angle > 0 && angle < 180) {
				angle += 1;
			} else {
				angle += 1;
			}
		}

	}

	/**
	 * Rotation du joueur vers la droite
	 */
	@Override
	public void turnRight() {
		if (!isDead) {
			if (angle > 0 && angle < 180) {
				angle -= 1;
			} else {
				angle -= 1;
			}
		}
	}

	@Override
	public void shoot() {
		if (!isDead) {
			if (shoot == false) {
				sprite.playShoot();
				shoot = true;
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					@Override
					public void run() {
						vBall.add(new Projectile(graphicsContext, x, y, angle,
								directionArrow, side));
						shoot = false;
					}
				}, 2200);
			}
		}
	}

	/**
	 * Deplacement en mode boost
	 */
	@Override
	public void boost() {
		if (!isDead) {
			x += step * 2;
			spriteAnimate();
		}
	}

	@Override
	public void spriteAnimate() {
		if (!isDead) {
			// System.out.println("Animating sprite");
			if (!sprite.isRunning) {
				sprite.playContinuously();
			}
			sprite.setX(x);
			sprite.setY(y);
		}
	}

	Player getPlayer() {
		return this;
	}

	@Override
	public Vector<Projectile> getBall() {
		Vector<Projectile> tmp = new Vector<Projectile>();
		tmp.addAll(vBall);
		vBall.clear();
		return tmp;
	}

	@Override
	public void isDead() {
		isDead = true;
	}

	@Override
	public void autoMoveTurnShoot() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dead() {
		if (!isDead) {
			// TODO Auto-generated method stub
			this.sprite.playDead();
		}
	}

}
