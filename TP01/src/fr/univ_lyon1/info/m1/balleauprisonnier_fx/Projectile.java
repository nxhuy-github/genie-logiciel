package fr.univ_lyon1.info.m1.balleauprisonnier_fx;


import javafx.animation.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Projectile extends ImageView{
	private double speed;
	private Image direction;
	private ImageView directionBall;
	
	GraphicsContext graphicsContext;
	
	public Projectile(GraphicsContext gc){
		graphicsContext = gc;
		speed = 1.0;
		direction = new Image("assets/ball.png");
		directionBall = new ImageView();
		directionBall.setImage(direction);
		directionBall.setFitWidth(10);
		directionBall.setPreserveRatio(true);
        directionBall.setSmooth(true);
        directionBall.setCache(true);
	}
	
	public void display(Player p, double a){
		graphicsContext.save();
		rotate(graphicsContext, a, p.x + p.directionArrow.getWidth() / 2, p.y + p.directionArrow.getHeight() / 2);
		graphicsContext.drawImage(direction, p.x + 5.0 + 5.0/3.0, p.y+p.directionArrow.getHeight());
		graphicsContext.restore();
	}
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
//		x = r.getMxx();
//		y = r.getMyy();
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}
}
