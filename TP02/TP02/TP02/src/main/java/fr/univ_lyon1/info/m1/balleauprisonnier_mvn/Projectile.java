package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

public class Projectile extends ImageView{
	private double speed, x, y;
	public Image direction;
	private ImageView directionBall;
	private double angle;
	private String side;
	
	GraphicsContext graphicsContext;
	
	public Projectile(GraphicsContext gc, double xPlayer, double yPlayer, double a, Image arrow, String side){
		x = xPlayer + 5.0 + 5.0/3;
		if(side == "top"){
			y = yPlayer + arrow.getHeight();	
		} else{
			y = yPlayer - arrow.getHeight()/4;
		}
		
		speed = 10.0;
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
		if(side == "bottom"){
			x -= speed * Math.sin(-Math.toRadians(angle));
			y -= speed * Math.cos(Math.toRadians(angle));
//			System.out.print("Bot: ");
//			System.out.println(x + " " + y);
		}else{
			x += speed * Math.sin(-Math.toRadians(angle));
			y += speed * Math.cos(-Math.toRadians(angle));
//			System.out.print("Top: ");
//			System.out.println(x + " " + y);
		}
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
}
