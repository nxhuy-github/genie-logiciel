package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;

public interface CharacterFactory {
	public Character createCharacter(GraphicsContext gc, String color, int xInit, int yInit, String side);
}
