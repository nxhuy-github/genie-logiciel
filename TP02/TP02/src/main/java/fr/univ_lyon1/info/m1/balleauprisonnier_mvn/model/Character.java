package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public abstract class Character implements Hitable{
	double x;       	// position horizontale du joueur
	double y; 	  		// position verticale du joueur
	double angle = 90; 	// rotation du joueur, devrait toujour Ãªtre en 0 et 180
	double step;    	// pas d'un joueur -> la vitesse du joueur
	String playerColor;

	// On une image globale du joueur
	Image directionArrow;
	public Sprite sprite;
	ImageView PlayerDirectionArrow;

	GraphicsContext graphicsContext;

	Vector<Projectile> vBall;
	boolean shoot = false;
	String side;
	Image tilesheetImage;

	Character(GraphicsContext gc, String color, int xInit, int yInit, String side, String image){
		x = xInit;
		y = yInit;
		this.side = side;
		graphicsContext = gc;
	    playerColor=color;
	    angle = 0;
	    step = 2;	    // Pour commencer les joueurs ont une vitesse / un pas fixe
        vBall = new Vector<Projectile>();

	    if(side=="top"){
        	directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}

        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);

        tilesheetImage = new Image(image);
        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
        sprite.setX(x);
        sprite.setY(y);
	}



	public void display(){
		graphicsContext.save(); // saves the current state on stack, including the current transform
	    rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
		graphicsContext.drawImage(directionArrow, x, y);
		graphicsContext.restore(); // back to original state (before rotation)
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		  Rotate r = new Rotate(angle, px, py);
		  gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	
	public void spriteAnimate(){
		if(!sprite.isRunning) {
			sprite.playContinuously();
		}
		  sprite.setX(x);
		  sprite.setY(y);
	}
	
	public void boost(){
		 x += step*2;
		 spriteAnimate();
	}
	
	public Vector<Projectile> getBall(){
		Vector<Projectile> tmp = new Vector<Projectile>();
		tmp.addAll(vBall);
		vBall.clear();
		return tmp;
	}

	@Override
	public boolean isHitted(ImageView b)
	{
	        double width = 64;
	        double height = 64;
	        Rectangle2D rp = new Rectangle2D.Double(this.x, this.y, width/2, height/2);
	        Rectangle2D rb = new Rectangle2D.Double(b.getX(), b.getY(),64/2, 64/2);
	        if(rp.intersects(rb)){
	            return true;
	        }else{
	            return false;
	        }

	}
	public double getx()
	{
	    return this.x;
	}

	public double gety()
    {
        return this.y;
    }


	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void turnLeft();
	public abstract void turnRight();
	public abstract void shoot();
	public abstract void autoMoveTurnShoot();
	public abstract void isDead();
	public abstract void dead();
}
