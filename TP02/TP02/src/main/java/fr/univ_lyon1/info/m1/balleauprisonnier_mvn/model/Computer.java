package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public class Computer extends Character {
	private boolean flag = true;
	Computer(GraphicsContext gc, String color, int xInit, int yInit, String side, String image){
		super(gc, color, xInit, yInit, side, image);
	}

	@Override
	public void moveLeft() {
		if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x -= 1.5 * step;
	    }else{
	    	x = (x == 10) ? 10 + 1.5 *step : 520 - 1.5 *step;
	    }
	}

	@Override
	public void moveRight() {
		if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x += 1.5 * step;
	    }else{
	    	x = (x == 10) ? 10 + 1.5 *step : 520 - 1.5 *step;
	    }
	}

	@Override
	public void turnLeft() {
		if (angle > 0 && angle < 180) 
	    {
	    	angle += 2;
	    }
	    else {
	    	angle += 2;
	    }
	}

	@Override
	public void turnRight() {
		if (angle > 0 && angle < 180) 
	    {
	    	angle -=2;
	    }
	    else {
	    	angle -= 2;
	    }
	}

	@Override
	public void shoot() {
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

	@Override
	public void autoMoveTurnShoot() {
		double random = Math.random() * 50 + 1;
		if(10 < x && flag == true){
			spriteAnimate();
			x += 1.5 * step;
			if(x < 1.5 * 300 && x > 1.5 * 200){
				shoot();
			}
			angle += 0.25;
			if(520 < x){
				flag = false;
				x = 520 - 1.5*step;
			}
			if(angle > 90){
				angle -=0.25;
			}
		}
		if(flag == false){
			spriteAnimate();
			x -= 1.5 * step;
			if(x < 0.5 * 300 && x > 0.5 * 200){
				shoot();
			}
			angle -= 0.25;
			if(x < 10){
				flag = true;
				x = 10 + 1.5*step;
			}
			if(angle < -90){
				angle +=0.25;
			}
		}
		
	}
    @Override
    public void isDead() {
        // TODO Auto-generated method stub

    }

	@Override
	public void dead() {
		// TODO Auto-generated method stub
		
	}

}