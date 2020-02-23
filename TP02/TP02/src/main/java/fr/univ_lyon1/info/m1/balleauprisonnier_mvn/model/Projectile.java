package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import java.awt.geom.Rectangle2D;

import javafx.scene.Node;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

public class Projectile extends ImageView implements Hitable{
	private double speed, x, y;
	public Image direction;
	private ImageView directionBall;
	private double angle;
	private String side;
	boolean moving = true;
	GraphicsContext graphicsContext;


	public Projectile(GraphicsContext gc, double xPlayer, double yPlayer, double a, Image arrow, String side){
		x = xPlayer + 5.0 + 5.0/3;
		if(side == "top"){
			y = yPlayer + arrow.getHeight();
		} else{
			y = yPlayer - arrow.getHeight()/4;
		}
		speed = 8.0;
		angle = a;
		this.side = side;

		graphicsContext = gc;

		direction = new Image("assets/ball.png");
		this.setImage(direction);

		directionBall = new ImageView();
		directionBall.setImage(direction);
		directionBall.setFitWidth(10);
		directionBall.setPreserveRatio(true);
        directionBall.setSmooth(true);
        directionBall.setCache(true);
	}



	public void display(){
		graphicsContext.save();
		graphicsContext.drawImage(direction, x, y);
		graphicsContext.restore();
	}

	public void fly(){
	    if(!moving)
	    {
	        x = x+2;
	        y = y+2;
	    }
	    else {
	        if(side == "bottom"){
	            x -= speed * Math.sin(-Math.toRadians(angle));
	            y -= speed * Math.cos(Math.toRadians(angle));
	        }else{
	            x += speed * Math.sin(-Math.toRadians(angle));
	            y += speed * Math.cos(-Math.toRadians(angle));
	        }
	    }
	    super.setX(x);
	    super.setY(y);
	}

	public void setStop()
	{
	    moving = false;
	}

	public double getx(){
		return x;
	}

	public double gety(){
		return y;
	}


	public double getWidthImage(){
		return direction.getWidth();
	}

	public double getHeightImage(){
		return direction.getHeight();
	}

    @Override
    public boolean isHitted(ImageView b) {
        double width = this.getWidthImage();
        double height = this.getHeightImage();
        Rectangle2D rp = new Rectangle2D.Double(this.x, this.y, width, height);
        Rectangle2D rb = new Rectangle2D.Double(b.getX(), b.getY(), b.getFitHeight(), b.getFitWidth());
        if(rp.intersects(rb)){
            return true;
        }else{
            return false;
        }

    }
}
