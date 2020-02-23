package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;

public class ComputerFactory implements CharacterFactory{
	@Override
	public Character createCharacter(GraphicsContext gc, String color, int xInit, int yInit, String side){
		Computer p = new Computer(gc, color, xInit, yInit, side);
		return p;
	}
}
