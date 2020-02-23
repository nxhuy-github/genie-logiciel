package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public abstract class Character {
	double x;       	// position horizontale du joueur
	double y; 	  		// position verticale du joueur
	double angle = 90; 	// rotation du joueur, devrait toujour Ãªtre en 0 et 180
	double step;    	// pas d'un joueur -> la vitesse du joueur
	String playerColor;
	  
	// On une image globale du joueur 
	Image directionArrow;
	Sprite sprite;
	ImageView PlayerDirectionArrow;
	  
	GraphicsContext graphicsContext;
	  
	Vector<Projectile> vBall;
	boolean shoot = false;
	String side;
	Image tilesheetImage;
	
	Character(GraphicsContext gc, String color, int xInit, int yInit, String side){
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

        tilesheetImage = new Image("assets/orc.png");
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
	
	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void turnLeft();
	public abstract void turnRight();
	public abstract void spriteAnimate();
	public abstract boolean isHitted(Projectile b);
	public abstract void boost();
	public abstract void shoot();
	public abstract Vector<Projectile> getBall();
	
}
