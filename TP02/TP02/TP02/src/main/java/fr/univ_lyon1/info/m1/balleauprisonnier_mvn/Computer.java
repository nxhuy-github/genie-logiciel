package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;
import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
public class Computer extends Character {
	Computer(GraphicsContext gc, String color, int xInit, int yInit, String side){
		super(gc, color, xInit, yInit, side);
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spriteAnimate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isHitted(Projectile b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void boost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Projectile> getBall() {
		// TODO Auto-generated method stub
		return null;
	}
}