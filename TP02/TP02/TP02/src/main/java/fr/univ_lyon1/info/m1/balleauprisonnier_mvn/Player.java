package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;


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
public class Player  extends Character
{
	  /**
	   * Constructeur du Joueur
	   * 
	   * @param gc ContextGraphic dans lequel on va afficher le joueur
	   * @param color couleur du joueur
	   * @param yInit position verticale
	   */
	  Player(GraphicsContext gc, String color, int xInit, int yInit, String side)
	  {
		super(gc, color, xInit, yInit, side);
	  }

	  /**
	   *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	   */
	  @Override
	  public void moveLeft() 
	  {	    
	    if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x -= step;
	    }
	  }

	  /**
	   *  Deplacement du joueur vers la droite
	   */
	  @Override
	  public void moveRight() 
	  {
	    if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x += step;
	    }
	  }

	  
	  /**
	   *  Rotation du joueur vers la gauche
	   */
	  @Override
	  public void turnLeft() 
	  {
	    if (angle > 0 && angle < 180) 
	    {
	    	angle += 1;
	    }
	    else {
	    	angle += 1;
	    }

	  }

	  
	  /**
	   *  Rotation du joueur vers la droite
	   */
	  @Override
	  public void turnRight() 
	  {
	    if (angle > 0 && angle < 180) 
	    {
	    	angle -=1;
	    }
	    else {
	    	angle -= 1;
	    }
	  }

	  @Override
	  public void shoot(){
		if(shoot == false){
			sprite.playShoot();
			shoot = true;
			Timer time = new Timer();
			time.schedule(new TimerTask(){
				@Override
				public void run() {
					vBall.add(new Projectile(graphicsContext, x, y, angle, directionArrow, side));
					shoot = false;
				}
			}, 2200);
		}
	  	
	  }
	  
	  /**
	   *  Deplacement en mode boost
	   */
	  @Override
	  public void boost() 
	  {
	    x += step*2;
		  spriteAnimate();
	  }

	  @Override
	  public void spriteAnimate(){
	  	  //System.out.println("Animating sprite");
		  if(!sprite.isRunning) {sprite.playContinuously();}
		  sprite.setX(x);
		  sprite.setY(y);
	  }
	  
	  
	  @Override
	  public boolean isHitted(Projectile b){
		  double height = 64;
		  double width = 64;
		  Rectangle2D rp = new Rectangle2D.Double(this.x, this.y, width, height);
		  Rectangle2D rb = new Rectangle2D.Double(b.getx(), b.gety(), b.getWidthImage(), b.getHeightImage());    
		  if(rp.intersects(rb)){
			  //System.out.println("Hitted");
			  return true;
		  }else{
			  return false;  
		  }
		  
	  }
	  
	  Player getPlayer(){
		  return this;
	  }
	  
	  @Override
	  public Vector<Projectile> getBall(){
		  Vector<Projectile> tmp = new Vector<Projectile>();
		  tmp.addAll(vBall);
		  vBall.clear();
		  return tmp;
	  }
	  
	  
}
